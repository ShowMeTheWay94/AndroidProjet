package com.example.basti.projet3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableRow row;
        ImageView image;

        int labyrinthe[][] = { {3,3,3,3,3,3,3,3} ,
                               {3,1,1,1,1,2,2,3} ,
                               {3,1,2,2,1,2,2,3} ,
                               {3,1,2,2,1,1,1,3} ,
                               {3,1,1,1,1,2,1,3} ,
                               {3,3,3,3,3,3,3,3}};

        int caracteristiques[][] = { {R.string.force, R.string.valeurForce} ,
                                     {R.string.defense, R.string.valeurDefense} ,
                                     {R.string.pv, R.string.valeurPv} ,
                                     {R.string.pm, R.string.valeurPm}};

        TableLayout labyrintheLayout = (TableLayout)findViewById(R.id.labyrinthe);
        TableLayout caracteristiquesLayout = (TableLayout)findViewById(R.id.caracteristiques);

        for(int i = 0; i < 6; i++){
            row = new TableRow(this);
            labyrintheLayout.addView(row);
            for(int j = 0; j < 8; j++){
                image = new ImageView(this);
                switch (labyrinthe[i][j]){
                    case 1 : image.setImageResource(R.drawable.tile_chemin_small);
                        break;
                    case 2 : image.setImageResource(R.drawable.tile_arbre_small);
                        break;
                    case 3 : image.setImageResource(R.drawable.tile_rocher_small);
                        break;
                }
                row.addView(image);
            }
        }

        for(int i = 0; i < 4; i++){
            row = new TableRow(this);
            caracteristiquesLayout.addView(row);
            for(int j = 0; j < 2; j++){
                TextView tv = new TextView(this);
                tv.setText(caracteristiques[i][j]);
                row.addView(tv);
            }
        }
    }
}
