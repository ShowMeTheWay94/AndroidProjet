package com.example.basti.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class EcranJeu extends AppCompatActivity {
    //Déclaration du tableau à deux dimensions (labyrinthe)
    int labyrinthe[][] = { {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3} ,
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3} ,
            {3,3,3,5,2,1,1,1,1,1,1,5,2,2,3,3,3} ,
            {3,3,3,1,1,1,2,1,2,5,2,1,2,2,3,3,3} ,
            {3,3,3,1,1,2,1,1,1,2,2,1,1,1,3,3,3} ,
            {3,3,3,1,1,2,1,1,1,1,2,1,1,1,3,3,3} ,
            {3,3,3,1,1,2,1,1,1,1,1,1,1,1,3,3,3} ,
            {3,3,3,1,1,2,5,1,1,2,2,1,1,1,3,3,3} ,
            {3,3,3,1,1,2,2,1,1,1,1,1,2,5,3,3,3} ,
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3} ,
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}};

    //Position du joueur
    int initX = 5;
    int initY = 9;

    //Déclaration des compteurs pour les potions
    int cptPotionForce = 2;
    int cptPotionRouge = 2;
    int cptPotionBleue = 2;

    //Déclaration des boutons pour les potions
    Button potionForce;
    Button potionRouge;
    Button potionBleue;

    //Déclaration des attributs
    int force;
    int defense;
    int pointVie;
    int pointMagie;
    int forceBase;
    int defenseBase;
    int pointVieBase;
    int pointMagieBase;

    //Déclaration de la classe
    String classe;

    //Déclaration du compteur de perle et du niveau du joueur
    int nbrPerle = 0;
    int niveau = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            forceBase = savedInstanceState.getInt("valeurForceBase");
            defenseBase = savedInstanceState.getInt("valeurDefenseBase");
            pointVieBase = savedInstanceState.getInt("valeurPointVieBase");
            pointMagieBase = savedInstanceState.getInt("valeurPointMagieBase");
            force = savedInstanceState.getInt("valeurForce");
            defense = savedInstanceState.getInt("valeurDefense");
            pointVie = savedInstanceState.getInt("valeurPointVie");
            pointMagie = savedInstanceState.getInt("valeurPointMagie");
            classe = savedInstanceState.getString("classe");
            cptPotionForce = savedInstanceState.getInt("cptPotionForce");
            cptPotionRouge = savedInstanceState.getInt("cptPotionRouge");
            cptPotionBleue = savedInstanceState.getInt("cptPotionBleue");
            initX = savedInstanceState.getInt("posX");
            initY = savedInstanceState.getInt("posY");
            nbrPerle = savedInstanceState.getInt("perle");
            niveau = savedInstanceState.getInt("niveau");
        }
        else{
            //Récupération de l'intent et des champs
            Intent intent = getIntent();
            forceBase = intent.getIntExtra("forceBase",0);
            defenseBase = intent.getIntExtra("defenseBase",0);
            pointVieBase = intent.getIntExtra("pointVieBase",0);
            pointMagieBase = intent.getIntExtra("pointMagieBase",0);
            force = intent.getIntExtra("force",0);
            defense = intent.getIntExtra("defense",0);
            pointVie = intent.getIntExtra("pointVie",0);
            pointMagie = intent.getIntExtra("pointMagie",0);
            classe = intent.getStringExtra("classe");
            cptPotionForce = intent.getIntExtra("cptPotionForce",cptPotionForce);
            cptPotionRouge = intent.getIntExtra("cptPotionRouge",cptPotionRouge);
            cptPotionBleue = intent.getIntExtra("cptPotionBleue",cptPotionBleue);
            initX = intent.getIntExtra("posX",initX);
            initY = intent.getIntExtra("posY",initY);
            nbrPerle = intent.getIntExtra("perle",nbrPerle);
            niveau = intent.getIntExtra("niveau",niveau);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_jeu);

        //Récupération des textViews et insertion des valeurs de la classe sélectionné
        TextView valeurForce = (TextView)findViewById(R.id.valeurForce);
        TextView valeurDefense = (TextView)findViewById(R.id.valeurDefense);
        TextView valeurPointVie = (TextView)findViewById(R.id.valeurPv);
        TextView valeurPointMagie = (TextView)findViewById(R.id.valeurPm);
        valeurForce.setText(String.valueOf(force));
        valeurDefense.setText(String.valueOf(defense));
        valeurPointVie.setText(String.valueOf(pointVie));
        valeurPointMagie.setText(String.valueOf(pointMagie));

        //Insérer le joueur sur la carte
        labyrinthe[initX][initY] = 4;

        initLabyrinthe();

        //Déclaration du bouton pour les potions
        Button potion = (Button)findViewById(R.id.potion);

        //Appel onClickListener du bouton potion
        potion.setOnClickListener(affichageBouton);

        //Récupération des flèches
        ImageView haut = (ImageView)findViewById(R.id.haut);
        ImageView gauche = (ImageView)findViewById(R.id.gauche);
        ImageView droite = (ImageView)findViewById(R.id.droite);
        ImageView bas = (ImageView)findViewById(R.id.bas);

        //Appel onClickListener des flèches
        haut.setOnClickListener(deplacement);
        gauche.setOnClickListener(deplacement);
        droite.setOnClickListener(deplacement);
        bas.setOnClickListener(deplacement);
    }

    private View.OnClickListener affichageBouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            potionForce = new Button(v.getContext());
            potionRouge = new Button(v.getContext());
            potionBleue = new Button(v.getContext());

            //Récupération du tableLayout pour les boutons
            TableLayout boutonPotion = (TableLayout)findViewById(R.id.boutonPotion);

            //Initialisation des attributs des boutons
            potionForce.setId(R.id.boutonForce);
            potionForce.setText("Potions force (reste " + cptPotionForce + ")");
            potionForce.setWidth(300);
            boutonPotion.addView(potionForce);
            potionForce.setOnClickListener(actionBouton);

            potionRouge.setId(R.id.boutonRouge);
            potionRouge.setText("Potions rouge (reste " + cptPotionRouge + ")");
            potionRouge.setWidth(300);
            boutonPotion.addView(potionRouge);
            potionRouge.setOnClickListener(actionBouton);

            potionBleue.setId(R.id.boutonBleu);
            potionBleue.setText("Potions bleue (reste " + cptPotionBleue + ")");
            potionBleue.setWidth(300);
            boutonPotion.addView(potionBleue);
            potionBleue.setOnClickListener(actionBouton);

            Button potion = (Button)findViewById(R.id.potion);
            potion.setVisibility(View.GONE);
        }
    };

    private View.OnClickListener actionBouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Switch sur les boutons de potion
            switch(v.getId()){
                case R.id.boutonForce : if(cptPotionForce > 0){
                    //Ajoute 1 de force
                    TextView valeurForce = (TextView)findViewById(R.id.valeurForce);
                    force++;
                    valeurForce.setText(String.valueOf(force));
                    //Compteur décrémenté
                    cptPotionForce--;
                    potionForce.setText("Potions force (reste " + cptPotionForce + ")");
                }
                else{
                    Toast.makeText(v.getContext(),R.string.manquePotionForce,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.boutonRouge : if(cptPotionRouge > 0){
                    //Ajoute 10 de pv
                    TextView valeurPv = (TextView)findViewById(R.id.valeurPv);
                    pointVie = pointVie + 10;
                    valeurPv.setText(String.valueOf(pointVie));
                    //Compteur décrémenté
                    cptPotionRouge--;
                    potionRouge.setText("Potions rouge (reste " + cptPotionRouge + ")");
                }
                else{
                    Toast.makeText(v.getContext(),R.string.manquePotionRouge,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.boutonBleu : if(cptPotionBleue > 0){
                    //Ajoute 10 de pm
                    TextView valeurPm = (TextView)findViewById(R.id.valeurPm);
                    pointMagie = pointMagie + 10;
                    valeurPm.setText(String.valueOf(pointMagie));
                    //Compteur décrémenté
                    cptPotionBleue--;
                    potionBleue.setText("Potions bleue (reste " + cptPotionBleue + ")");
                }
                else{
                    Toast.makeText(v.getContext(),R.string.manquePotionBleue,Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    };

    private View.OnClickListener deplacement = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Swtich sur les flèches
            switch (v.getId()) {
                case R.id.haut: if(labyrinthe[initX-1][initY] == 1 || labyrinthe[initX-1][initY] == 5){
                    if(labyrinthe[initX-1][initY] == 5){
                        nbrPerle++;
                        if(nbrPerle%3 == 0){
                            niveau++;
                        }
                    }
                    labyrinthe[initX][initY] = 1;
                    initX--;
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementHaut,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.gauche: if(labyrinthe[initX][initY-1] == 1 || labyrinthe[initX][initY-1] == 5){
                    if(labyrinthe[initX][initY-1] == 5){
                        nbrPerle++;
                        if(nbrPerle%3 == 0){
                            niveau++;
                        }
                    }
                    labyrinthe[initX][initY] = 1;
                    initY--;
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementGauche,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.droite: if(labyrinthe[initX][initY+1] == 1 || labyrinthe[initX][initY+1] == 5){
                    if(labyrinthe[initX][initY+1] == 5){
                        nbrPerle++;
                        if(nbrPerle%3 == 0){
                            niveau++;
                        }
                    }
                    labyrinthe[initX][initY] = 1;
                    initY++;
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementDroite,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.bas: if(labyrinthe[initX+1][initY] == 1 || labyrinthe[initX+1][initY] == 5){
                    if(labyrinthe[initX+1][initY] == 5){
                        nbrPerle++;
                        if(nbrPerle%3 == 0){
                            niveau++;
                        }
                    }
                    labyrinthe[initX][initY] = 1;
                    initX++;
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementBas,Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    };

    private void initLabyrinthe(){
        //Récupération du tableLayout(labyrinthe)
        TableLayout labyrintheLayout = (TableLayout) findViewById(R.id.labyrinthe);
        labyrintheLayout.removeAllViews();

        //Déclaration du tableRow et de l'image initialisé dans la boucle
        TableRow row;
        ImageView image;

        //Boucle initialisant le tableLayout (labyrinthe)
        for (int i = initX - 2; i <= initX + 2; i++) {
            row = new TableRow(this);
            labyrintheLayout.addView(row);
            for (int j = initY - 3; j <= initY + 3; j++) {
                image = new ImageView(this);
                switch (labyrinthe[i][j]) {
                    case 1:
                        image.setImageResource(R.drawable.tile_chemin_small);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.tile_arbre_small);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.tile_rocher_small);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.link_bas01);
                        image.setOnClickListener(caracteristiques);
                        break;
                    case 5:
                        image.setImageResource(R.drawable.perle);
                        break;
                }
                row.addView(image);
            }
        }
    }

    //Listener pour afficher les caractéristiques du joueur quand on clique dessus
    private View.OnClickListener caracteristiques = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EcranJeu.this,CaracteristiquesClasse.class);
            intent.putExtra("forceBase",forceBase);
            intent.putExtra("defenseBase",defenseBase);
            intent.putExtra("pointVieBase",pointVieBase);
            intent.putExtra("pointMagieBase",pointMagieBase);
            intent.putExtra("classe",classe);
            intent.putExtra("potionForce",cptPotionForce);
            intent.putExtra("potionRouge",cptPotionRouge);
            intent.putExtra("potionBleue",cptPotionBleue);
            intent.putExtra("force",force);
            intent.putExtra("defense",defense);
            intent.putExtra("pointVie",pointVie);
            intent.putExtra("pointMagie",pointMagie);
            intent.putExtra("posX",initX);
            intent.putExtra("posY",initY);
            intent.putExtra("perle",nbrPerle);
            intent.putExtra("niveau",niveau);
            startActivity(intent);
        }
    };

    //Sauvegarde des données
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("valeurForceBase",forceBase);
        outState.putInt("valeurDefenseBase",defenseBase);
        outState.putInt("valeurPointVieBase",pointVieBase);
        outState.putInt("valeurPointMagieBase",pointMagieBase);
        outState.putInt("valeurForce",force);
        outState.putInt("valeurDefense",defense);
        outState.putInt("valeurPointVie",pointVie);
        outState.putInt("valeurPointMagie",pointMagie);
        outState.putString("classe",classe);
        outState.putInt("cptPotionForce",cptPotionForce);
        outState.putInt("cptPotionRouge",cptPotionRouge);
        outState.putInt("cptPotionBleue",cptPotionBleue);
        outState.putInt("posX",initX);
        outState.putInt("posY",initY);
        outState.putInt("perle",nbrPerle);
        outState.putInt("niveau",niveau);
    }
}
