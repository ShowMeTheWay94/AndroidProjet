package com.example.basti.projet4;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Déclaration du tableau à deux dimensions (labyrinthe)
    int labyrinthe[][] = { {3,3,3,3,3,3,3,3,3,3,3,3} ,
            {3,3,3,3,3,3,3,3,3,3,3,3} ,
            {3,3,3,1,1,1,1,2,2,3,3,3} ,
            {3,3,3,1,2,2,1,2,2,3,3,3} ,
            {3,3,3,1,2,2,1,1,1,3,3,3} ,
            {3,3,3,1,1,1,1,2,1,3,3,3} ,
            {3,3,3,3,3,3,3,3,3,3,3,3} ,
            {3,3,3,3,3,3,3,3,3,3,3,3}};

    //Position du joueur
    int initX = 4;
    int initY = 6;

    //Insérer la valeur du tableau dans une variable temporaire
    int tmp = labyrinthe[initX][initY];

    //Déclaration des compteurs pour les potions
    int cptPotionForce = 2;
    int cptPotionRouge = 2;
    int cptPotionBleue = 2;

    //Récupération des boutons pour les potions
    Button potionForce;
    Button potionRouge;
    Button potionBleue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    valeurForce.setText(String.valueOf(Integer.parseInt(valeurForce.getText().toString())+1));
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
                    valeurPv.setText(String.valueOf(Integer.parseInt(valeurPv.getText().toString())+10));
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
                    valeurPm.setText(String.valueOf(Integer.parseInt(valeurPm.getText().toString())+10));
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
                case R.id.haut: if(initX > 1 && initX < labyrinthe.length-1 && labyrinthe[initX-1][initY] == 1){
                    labyrinthe[initX][initY] = tmp;
                    initX = initX - 1;
                    tmp = labyrinthe[initX][initY];
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementHaut,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.gauche: if(initY > 2 && initY < labyrinthe[initX].length-2 && labyrinthe[initX][initY-1] == 1){
                    labyrinthe[initX][initY] = tmp;
                    initY = initY - 1;
                    tmp = labyrinthe[initX][initY];
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementGauche,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.droite: if(initY > 2 && initY < labyrinthe[initX].length-2 && labyrinthe[initX][initY+1] == 1){
                    labyrinthe[initX][initY] = tmp;
                    initY = initY + 1;
                    tmp = labyrinthe[initX][initY];
                    labyrinthe[initX][initY] = 4;
                    initLabyrinthe();
                }
                else{
                    Toast.makeText(v.getContext(),R.string.deplacementDroite,Toast.LENGTH_SHORT).show();
                }
                    break;
                case R.id.bas: if(initX > 1 && initX < labyrinthe.length-1 && labyrinthe[initX+1][initY] == 1){
                    labyrinthe[initX][initY] = tmp;
                    initX = initX + 1;
                    tmp = labyrinthe[initX][initY];
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
                }
                row.addView(image);
            }
        }
    }
}
