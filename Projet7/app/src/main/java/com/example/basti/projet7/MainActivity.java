package com.example.basti.projet7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Création des editText pour le login et mot de passe et du textView pour l'erreur
    EditText login;
    EditText mdp;
    TextView txtConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des editText et du textView selon leur id
        login = (EditText)findViewById(R.id.login);
        mdp = (EditText)findViewById(R.id.mdp);
        txtConnexion = (TextView)findViewById(R.id.txtConnexion);

        //Click sur le bouton de connexion
        Button btnConnexion = (Button)findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            //Appel à la tâche asynchrone
            Connexion connexion = new Connexion();
            connexion.doInBackground(login.getText().toString(),mdp.getText().toString());
        }
    };

    //Tâche asynchrone
    public class Connexion{
        protected void doInBackground(String pseudo,String motDePasse) {
            if(pseudo.equals("baba") && motDePasse.equals("baba")){
                Intent intent = new Intent(MainActivity.this, ChoixPersonnage.class);
                startActivity(intent);
                txtConnexion.setText("");
                login.setText("");
                mdp.setText("");
            }
            else{
                txtConnexion.setText(R.string.erreurConnexion);
            }
        }
    }
}
