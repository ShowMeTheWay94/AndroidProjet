package com.example.basti.projet5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMenu = (Button)findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MainActivity.this, ChoixPersonnage.class);
            startActivity(intent);
        }
    };
}
