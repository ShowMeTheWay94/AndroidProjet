package com.example.basti.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CaracteristiquesClasse extends AppCompatActivity {
    //Déclaration des variables
    String classe;
    int valeurForceBase;
    int valeurDefenseBase;
    int valeurPointVieBase;
    int valeurPointMagieBase;
    int cptPotionForce;
    int cptPotionRouge;
    int cptPotionBleue;
    int valeurForce;
    int valeurDefense;
    int valeurPointVie;
    int valeurPointMagie;
    int posX;
    int posY;
    int nbrPerle;
    int niveau;

    //Déclaration des TextView pour les caractéristiques
    TextView force;
    TextView defense;
    TextView pointVie;
    TextView pointMagie;

    //Déclaration du TextView pour les niveaux
    TextView niveaux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caracteristiques_classe);

        //Déclaration de la classe
        Intent intent = getIntent();
        classe = intent.getStringExtra("classe");
        valeurForceBase = intent.getIntExtra("forceBase",0);
        valeurDefenseBase = intent.getIntExtra("defenseBase",0);
        valeurPointVieBase = intent.getIntExtra("pointVieBase",0);
        valeurPointMagieBase = intent.getIntExtra("pointMagieBase",0);
        cptPotionForce = intent.getIntExtra("potionForce",0);
        cptPotionRouge = intent.getIntExtra("potionRouge",0);
        cptPotionBleue = intent.getIntExtra("potionBleue",0);
        valeurForce = intent.getIntExtra("force",0);
        valeurDefense = intent.getIntExtra("defense",0);
        valeurPointVie = intent.getIntExtra("pointVie",0);
        valeurPointMagie = intent.getIntExtra("pointMagie",0);
        posX = intent.getIntExtra("posX",0);
        posY = intent.getIntExtra("posY",0);
        nbrPerle = intent.getIntExtra("perle",0);
        niveau = intent.getIntExtra("niveau",0);

        //Récupération du titre
        TextView titre = (TextView)findViewById(R.id.titreCaracteristiques);
        titre.setText(classe);

        //Récupération informations de la classe
        ImageView image = (ImageView)findViewById(R.id.image);
        TextView description = (TextView)findViewById(R.id.description);
        force = (TextView)findViewById(R.id.force);
        defense = (TextView)findViewById(R.id.defense);
        pointVie = (TextView)findViewById(R.id.pointVie);
        pointMagie = (TextView)findViewById(R.id.pointMagie);

        switch (classe){
            case "Chevalier" : image.setImageResource(R.drawable.chevalier);
                description.setText(R.string.descriptionChevalier);
                break;
            case "Guerrier" : image.setImageResource(R.drawable.guerrier);
                description.setText(R.string.descriptionGuerrier);
                break;
            case "Magicien(ne)" : image.setImageResource(R.drawable.magicienne);
                description.setText(R.string.descriptionMagicien);
                break;
        }

        //Affichage des caractéristiques
        force.setText(String.valueOf(valeurForceBase));
        defense.setText(String.valueOf(valeurDefenseBase));
        pointVie.setText(String.valueOf(valeurPointVieBase));
        pointMagie.setText(String.valueOf(valeurPointMagieBase));

        //Récupération du bouton retour
        Button btnRetour = (Button)findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(retour);

        //Récupération du TextView pour les niveaux
        niveaux = (TextView)findViewById(R.id.niveau);
        niveaux.setText("Niveau : " + String.valueOf(niveau));

        //Récupération des boutons pour ajouter aux caractéristiques
        Button ajoutForce = (Button)findViewById(R.id.ajoutForce);
        Button ajoutPointVie = (Button)findViewById(R.id.ajoutPointVie);
        Button ajoutDefense = (Button)findViewById(R.id.ajoutDefense);
        Button ajoutPointMagie = (Button)findViewById(R.id.ajoutPointMagie);
        ajoutForce.setOnClickListener(ajouterForce);
        ajoutPointVie.setOnClickListener(ajouterPointVie);
        ajoutDefense.setOnClickListener(ajouterDefense);
        ajoutPointMagie.setOnClickListener(ajouterPointMagie);
    }

    //Listener pour retourner dans l'écran de jeu
    private View.OnClickListener retour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CaracteristiquesClasse.this,EcranJeu.class);
            intent.putExtra("classe",classe);
            intent.putExtra("forceBase",valeurForceBase);
            intent.putExtra("defenseBase",valeurDefenseBase);
            intent.putExtra("pointVieBase",valeurPointVieBase);
            intent.putExtra("pointMagieBase",valeurPointMagieBase);
            intent.putExtra("force",valeurForce);
            intent.putExtra("defense",valeurDefense);
            intent.putExtra("pointVie",valeurPointVie);
            intent.putExtra("pointMagie",valeurPointMagie);
            intent.putExtra("cptPotionForce",cptPotionForce);
            intent.putExtra("cptPotionRouge",cptPotionRouge);
            intent.putExtra("cptPotionBleue",cptPotionBleue);
            intent.putExtra("posX",posX);
            intent.putExtra("posY",posY);
            intent.putExtra("perle",nbrPerle);
            intent.putExtra("niveau",niveau);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener ajouterForce = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(niveau > 0){
                valeurForceBase++;
                valeurForce++;
                niveau--;
                force.setText(String.valueOf(valeurForceBase));
                niveaux.setText("Niveau : " + String.valueOf(niveau));
            }
            else{
                Toast.makeText(v.getContext(),R.string.manqueNiveau,Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener ajouterPointVie = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(niveau > 0){
                valeurPointVieBase++;
                valeurPointVie++;
                niveau--;
                pointVie.setText(String.valueOf(valeurPointVieBase));
                niveaux.setText("Niveau : " + String.valueOf(niveau));
            }
            else{
                Toast.makeText(v.getContext(),R.string.manqueNiveau,Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener ajouterDefense = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(niveau > 0){
                valeurDefenseBase++;
                valeurDefense++;
                niveau--;
                defense.setText(String.valueOf(valeurDefenseBase));
                niveaux.setText("Niveau : " + String.valueOf(niveau));
            }
            else{
                Toast.makeText(v.getContext(),R.string.manqueNiveau,Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener ajouterPointMagie = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(niveau > 0){
                valeurPointMagieBase++;
                valeurPointMagie++;
                niveau--;
                pointMagie.setText(String.valueOf(valeurPointMagieBase));
                niveaux.setText("Niveau : " + String.valueOf(niveau));
            }
            else{
                Toast.makeText(v.getContext(),R.string.manqueNiveau,Toast.LENGTH_SHORT).show();
            }
        }
    };
}
