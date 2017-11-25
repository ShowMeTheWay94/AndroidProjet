package com.example.basti.projet7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ChoixPersonnage extends AppCompatActivity {
    //CheckBox classe
    CheckBox chevalier;
    CheckBox guerrier;
    CheckBox magicien;

    //Valeur classe joueur
    TextView forceChevalier;
    TextView forceGuerrier;
    TextView forceMagicien;
    TextView pointVieChevalier;
    TextView pointVieGuerrier;
    TextView pointVieMagicien;
    TextView defenseChevalier;
    TextView defenseGuerrier;
    TextView defenseMagicien;
    TextView pointMagieChevalier;
    TextView pointMagieGuerrier;
    TextView pointMagieMagicien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_personnage);

        //Récupération des checkbox
        chevalier = (CheckBox)findViewById(R.id.checkboxChevalier);
        guerrier = (CheckBox)findViewById(R.id.checkboxGuerrier);
        magicien = (CheckBox)findViewById(R.id.checkboxMagicien);

        //Récupération des textViews
        forceChevalier = (TextView)findViewById(R.id.forceChevalier);
        forceGuerrier = (TextView)findViewById(R.id.forceGuerrier);
        forceMagicien = (TextView)findViewById(R.id.forceMagicien);
        pointVieChevalier = (TextView)findViewById(R.id.pointVieChevalier);
        pointVieGuerrier = (TextView)findViewById(R.id.pointVieGuerrier);
        pointVieMagicien = (TextView)findViewById(R.id.pointVieMagicien);
        defenseChevalier = (TextView)findViewById(R.id.defenseChevalier);
        defenseGuerrier = (TextView)findViewById(R.id.defenseGuerrier);
        defenseMagicien = (TextView)findViewById(R.id.defenseMagicien);
        pointMagieChevalier = (TextView)findViewById(R.id.pointMagieChevalier);
        pointMagieGuerrier = (TextView)findViewById(R.id.pointMagieGuerrier);
        pointMagieMagicien = (TextView)findViewById(R.id.pointMagieMagicien);

        //Récupération du bouton et attribution du listener
        Button btnConfirmer = (Button)findViewById(R.id.confirmer);
        btnConfirmer.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            //Création de l'intent pour passer à l'écran de jeu
            Intent intent = new Intent(ChoixPersonnage.this, EcranJeu.class);

            //Récupération des informations de la classe sélectionnée
            if(chevalier.isChecked()){
                intent.putExtra("force",Integer.parseInt(forceChevalier.getText().toString()));
                intent.putExtra("forceBase",Integer.parseInt(forceChevalier.getText().toString()));
                intent.putExtra("defense",Integer.parseInt(defenseChevalier.getText().toString()));
                intent.putExtra("defenseBase",Integer.parseInt(defenseChevalier.getText().toString()));
                intent.putExtra("pointVie",Integer.parseInt(pointVieChevalier.getText().toString()));
                intent.putExtra("pointVieBase",Integer.parseInt(pointVieChevalier.getText().toString()));
                intent.putExtra("pointMagie",Integer.parseInt(pointMagieChevalier.getText().toString()));
                intent.putExtra("pointMagieBase",Integer.parseInt(pointMagieChevalier.getText().toString()));
                intent.putExtra("classe","Chevalier");
                startActivity(intent);
            }else if(guerrier.isChecked()){
                intent.putExtra("force",Integer.parseInt(forceGuerrier.getText().toString()));
                intent.putExtra("forceBase",Integer.parseInt(forceGuerrier.getText().toString()));
                intent.putExtra("defense",Integer.parseInt(defenseGuerrier.getText().toString()));
                intent.putExtra("defenseBase",Integer.parseInt(defenseGuerrier.getText().toString()));
                intent.putExtra("pointVie",Integer.parseInt(pointVieGuerrier.getText().toString()));
                intent.putExtra("pointVieBase",Integer.parseInt(pointVieGuerrier.getText().toString()));
                intent.putExtra("pointMagie",Integer.parseInt(pointMagieGuerrier.getText().toString()));
                intent.putExtra("pointMagieBase",Integer.parseInt(pointMagieGuerrier.getText().toString()));
                intent.putExtra("classe","Guerrier");
                startActivity(intent);
            }else if(magicien.isChecked()){
                intent.putExtra("force",Integer.parseInt(forceMagicien.getText().toString()));
                intent.putExtra("forceBase",Integer.parseInt(forceMagicien.getText().toString()));
                intent.putExtra("defense",Integer.parseInt(defenseMagicien.getText().toString()));
                intent.putExtra("defenseBase",Integer.parseInt(defenseMagicien.getText().toString()));
                intent.putExtra("pointVie",Integer.parseInt(pointVieMagicien.getText().toString()));
                intent.putExtra("pointVieBase",Integer.parseInt(pointVieMagicien.getText().toString()));
                intent.putExtra("pointMagie",Integer.parseInt(pointMagieMagicien.getText().toString()));
                intent.putExtra("pointMagieBase",Integer.parseInt(pointMagieMagicien.getText().toString()));
                intent.putExtra("classe","Magicien(ne)");
                startActivity(intent);
            }
            else{
                Toast.makeText(v.getContext(),R.string.selectionClasse,Toast.LENGTH_SHORT).show();
            }
        }
    };
}
