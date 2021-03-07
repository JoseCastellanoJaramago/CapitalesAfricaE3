package com.example.capitalesAfrica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_resultados.*

class ResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val paises=intent.getStringExtra("paises") //Obtiene como parámetro el número de paises
        val aciertos=intent.getStringExtra("aciertos") //Obtiene como parámetro el número de aciertos
        val fallos=intent.getStringExtra("fallos") //Obtiene como parámetro el número de fallos

        txt_nPaises.setText(paises) //Muestra los paises en el label correspondiente
        txt_nAciertos.setText(aciertos) //Muestra los aciertos en el label correspondiente
        txt_nFallos.setText(fallos) //Muestra los fallos en el label correspondiente

        btnVolver.setOnClickListener(){//Al pulsar el botón volver, vuelve al AtivityMain para volver a comenzar con el juego
            intent = Intent(this@ResultadosActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}