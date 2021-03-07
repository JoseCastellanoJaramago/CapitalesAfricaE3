package com.example.africajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent = getIntent();
        String paises = intent.getStringExtra("paises"); //Obtiene como parámetro el número de paises
        String aciertos = intent.getStringExtra("aciertos"); //Obtiene como parámetro el número de aciertos
        String fallos = intent.getStringExtra("fallos"); //Obtiene como parámetro el número de fallos

        TextView nPaises = (TextView) findViewById(R.id.txt_nPaises);
        TextView nAciertos = (TextView) findViewById(R.id.txt_nAciertos);
        TextView nFallos = (TextView) findViewById(R.id.txt_nFallos);
        Button btnVolver = (Button) findViewById(R.id.btnVolver);

        nPaises.setText(paises); //Muestra los paises en el label correspondiente
        nAciertos.setText(aciertos); //Muestra los aciertos en el label correspondiente
        nFallos.setText(fallos); //Muestra los fallos en el label correspondiente

        btnVolver.setOnClickListener(new View.OnClickListener(){//Al pulsar el botón volver, vuelve al AtivityMain para volver a comenzar con el juego

            @Override
            public void onClick(View v) {
                ResultadosActivity.this.setIntent(new Intent((Context)ResultadosActivity.this, MainActivity.class));
                ResultadosActivity.this.startActivity(ResultadosActivity.this.getIntent());
            }
        });
    }
}