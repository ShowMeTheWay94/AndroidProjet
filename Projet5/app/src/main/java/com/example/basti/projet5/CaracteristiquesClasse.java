package com.example.basti.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CaracteristiquesClasse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caracteristiques_classe);

        //Déclaration de la classe
        Intent intent = getIntent();
        String classe = intent.getStringExtra("classe");

        //Récupération du titre
        TextView titre = (TextView)findViewById(R.id.titreCaracteristiques);
        titre.setText(classe);

        //Récupération informations de la classe
        ImageView image = (ImageView)findViewById(R.id.image);

        switch (classe){
            case "Chevalier" : image.setImageResource(R.drawable.chevalier);
                break;
            case "Guerrier" : image.setImageResource(R.drawable.guerrier);
                break;
            case "Magicien(ne)" : image.setImageResource(R.drawable.magicienne);
                break;
        }
    }
}
