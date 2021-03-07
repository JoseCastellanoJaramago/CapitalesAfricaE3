package com.example.africajava;

import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    HashMap<String, String> listaPaises = new HashMap<>();
    int aciertos = 0; //Variable para almacenar los aciertos
    int fallos = 0; //Variable para almacenar los fallos
    int i = 0; //Variable para recorrer todos los paises de la base de datos
    String pais = ""; //Variable para almacenar el pais que se muestra
    String respuesta = ""; //Variable para almacenar la respuesta dada
    ArrayList<Integer> randomList = new ArrayList<>(); //Lista para hacer aleatoria la lista de paises
    DbHandler dbHandler = new DbHandler(this); //Objeto de la clase DbHandler

    public void insertarPaises(){ //Función para insertar los paises en la base de datos
        dbHandler.insertData(1, "Angola","Luanda");
        dbHandler.insertData(2, "Argelia","Argel");
        dbHandler.insertData(3, "Benín","Porto Novo");
        dbHandler.insertData(4, "Botswana","Gaborone");
        dbHandler.insertData(5, "Burkina Faso","Uagadugú");
        /*dbHandler.insertData(6, "Burundi","Uagadugú");
        dbHandler.insertData(7, "Cabo Verde","Praia");
        dbHandler.insertData(8, "Camerún","Yaundé");
        dbHandler.insertData(9, "Chad","N'Djamena");
        dbHandler.insertData(10, "Comoras","Moroni");
        dbHandler.insertData(11, "Congo","Brazzaville");
        dbHandler.insertData(12, "Costa de Marfil","Yamoussoukro");
        dbHandler.insertData(13, "Djibouti","Djibouti");
        dbHandler.insertData(14, "Egipto","El Cairo");
        dbHandler.insertData(15, "Eritrea","Asmara");
        dbHandler.insertData(16, "Etiopía","Adís Abeba");
        dbHandler.insertData(17, "Gabón","Libreville");
        dbHandler.insertData(18, "Gambia","Banjul");
        dbHandler.insertData(19, "Ghana","Accra");
        dbHandler.insertData(20, "Guinea","Conakry");
        dbHandler.insertData(21, "Guinea Bissau","Bissau");
        dbHandler.insertData(22, "Guinea Ecuatorial","Malabo");
        dbHandler.insertData(23, "Kenia","Nairobi");
        dbHandler.insertData(24, "Lesoto","Maseru");
        dbHandler.insertData(25, "Liberia","Monrovia");
        dbHandler.insertData(26, "Libia","Tripoli");
        dbHandler.insertData(27, "Madagascar","Antananarivo");
        dbHandler.insertData(28, "Malawi","Lilongüe");
        dbHandler.insertData(29, "Mali","Bamako");
        dbHandler.insertData(30, "Marruecos","Rabat");
        dbHandler.insertData(31, "Mauricio","Port Louis");
        dbHandler.insertData(32, "Mauritania","Nuakchot");
        dbHandler.insertData(33, "Mozambique","Maputo");
        dbHandler.insertData(34, "Namibia","Windhoek");
        dbHandler.insertData(35, "Niger","Niamey");
        dbHandler.insertData(36, "Nigeria","Abuja");
        dbHandler.insertData(37, "R. Centroafricana","Bangui");
        dbHandler.insertData(38, "R.D. del Congo","Kinshasa");
        dbHandler.insertData(39, "Ruanda","Kigali");
        dbHandler.insertData(40, "Tomé y Príncipe","Santo Tomé");
        dbHandler.insertData(41, "Senegal","Dakar");
        dbHandler.insertData(42, "Seychelles","Victoria");
        dbHandler.insertData(43, "Sierra Leona","Freetown");
        dbHandler.insertData(44, "Somalia","Mogadiscio");
        dbHandler.insertData(45, "Sudáfrica","Ciudad del Cabo");
        dbHandler.insertData(46, "Sudán","Jartum");
        dbHandler.insertData(47, "Sudán del Sur","Yuba");
        dbHandler.insertData(48, "Swazilandilandia","Mbabane");
        dbHandler.insertData(49, "Tanzania","Dodoma");
        dbHandler.insertData(50, "Togo","Lomé");
        dbHandler.insertData(51, "Túnez","Túnez");
        dbHandler.insertData(52, "Uganda","Kampala");
        dbHandler.insertData(53, "Zambia","Lusaka");
        dbHandler.insertData(54, "Zimbabue","Harare");*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView labelEmpezar = (TextView) findViewById(R.id.labelEmpezar);
        labelEmpezar.setText(R.string.label_empezar);
        dbHandler.deleteAll();
        insertarPaises();

        ImageButton btnCambiar = (ImageButton) findViewById(R.id.btnCambiar);
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup capitales = (RadioGroup) findViewById(R.id.capitales);
                if (capitales.getVisibility() == View.INVISIBLE){ //Comprueba que el radio group no sea visible para comenzar el juego

                    listaPaises = hashmap(); //Se añade a la variable todos los paises y capitales

                    for (int j = 0; j < listaPaises.size(); j++) {// Hace una lista del numero total de paises
                        randomList.add(j);
                    }
                    Collections.shuffle(randomList);// Hace que la lista sea aleatoria

                    capitales.setVisibility(View.VISIBLE); //Hace que el radio group sea visible

                    muestraInicio();
                }else{
                    pasaSiguiente();
                }
            }
        });
    }


    public void muestraInicio(){ //Función que genera el pais y las capitales
        if (i != randomList.size()){ //Comprueba que no se hayan mostrado todos los paises
            TextView labelEmpezar = (TextView) findViewById(R.id.labelEmpezar);
            if (i == randomList.size()-1) {
                Toast(2);
                labelEmpezar.setText(R.string.label_verResultados);
            } else {
                labelEmpezar.setText(R.string.label_cambiar);
            }

            pais = (String) listaPaises.keySet().toArray()[randomList.get(i)]; //Obtiene el pais teniendo en cuenta la lista aleatoria
            TextView txtPais = (TextView) findViewById(R.id.txtPais);
            txtPais.setText(pais);

            // Hace una lista de numeros para almacenar los números de las capitales
            ArrayList<Integer> randomList1 = new ArrayList<>();
            randomList1.add(i); //Le añade la capital correspondiente al pais para que siempre se muestre la respuesta correcta

            // Hace una lista para almacenar numeros repetidos para que las capitales sean diferentes
            ArrayList<Integer> repetidos = new ArrayList<>();
            repetidos.add(i); //Le añade la capital correspondiente al pais para que siempre se muestre la respuesta correcta

            Random random = new Random();
            int num;
            for (int j = 0; j < 3; j++) {
                num = random.nextInt(listaPaises.size()); //Se generan numeros aleatorios para añadir a la lista
                if (repetidos.contains(num)){
                    j--;
                }else{ //Añade ese numero a las dos listas si no es repetido
                    randomList1.add(num);
                    repetidos.add(num);
                }
            }
            Collections.shuffle(randomList1); //Hace la lista aleatoria

            // Crea los objetos de los RadioButtons
            RadioButton rd1 = (RadioButton) findViewById(R.id.radioButton1);
            RadioButton rd2 = (RadioButton) findViewById(R.id.radioButton2);
            RadioButton rd3 = (RadioButton) findViewById(R.id.radioButton3);
            RadioButton rd4 = (RadioButton) findViewById(R.id.radioButton4);

            // Crea una lista con los radioButton y los hace aleatorio para que se muestren las 4 capitales de forma aleatoria
            ArrayList<RadioButton> rd = new ArrayList<>();
            rd.add(rd1);
            rd.add(rd2);
            rd.add(rd3);
            rd.add(rd4);
            Collections.shuffle(rd);

            // Asigna a cada radioButton una capital, incluyendo la correcta
            for (int j = 0; j < 4; j++) {
                rd.get(j).setText(listaPaises.values().toArray()[randomList.get(randomList1.get(j))].toString());
            }
            i++; //Aumenta la variable que cuenta los paises
        }else{
            reiniciar(); //Si se han mostrado todos los paises, se reinicia el juego
        }
    }

    public void pasaSiguiente() { //Hace que se cambie de pais y capitales
        RadioGroup capitales = (RadioGroup) findViewById(R.id.capitales);
        if (capitales.getCheckedRadioButtonId() == -1){ //Comprueba que se haya seleccionado una opción
            Toast(1);
        }else{
            checkAcierto();
            muestraInicio();
            capitales.clearCheck();
        }
    }

    public void checkAcierto() { //Comprueba que la respuesta sea correcta
        RadioGroup capitales = (RadioGroup) findViewById(R.id.capitales);
        int id = capitales.getCheckedRadioButtonId();
        RadioButton radio = (RadioButton) findViewById(id);

        respuesta = (String) radio.getText(); //Obtiene la respuesta seleccionada
        if (respuesta.equals(listaPaises.get(pais))){ //Compara la  respuesta con la capital correcta
            aciertos++; //Aumenta los aciertos si la respuesta es correcta
        }else{
            fallos++; //Aumenta los fallos si la respuesta es incorrecta
        }
    }

    public void reiniciar(){ //Función para reiniciar el juego
        Log.i("Resultados", "Botón Ver Resultados Pulsado"); //Muestra un mensaje de imformación

        Intent intent = new Intent(this, ResultadosActivity.class); //Objeto para llamar al Activity de los resultados.
        intent.putExtra("paises", String.valueOf(listaPaises.size())); //Le pasa como parametros el número de paises
        intent.putExtra("aciertos", String.valueOf(aciertos)); //Le pasa como parametros el número de aciertos
        intent.putExtra("fallos", String.valueOf(fallos)); //Le pasa como parametros el número de fallos
        startActivity(intent); //Comienza el activity

        TextView labelEmpezar = (TextView) findViewById(R.id.labelEmpezar);
        labelEmpezar.setText(R.string.label_empezar); //Muestra en el label el mensaje para volver a empezar

        TextView txtPais = (TextView) findViewById(R.id.txtPais);
        txtPais.setText(""); //Hace que el label del pais esté vacío

        RadioGroup capitales = (RadioGroup) findViewById(R.id.capitales);
        capitales.setVisibility(View.INVISIBLE); //Hace invisible el radioGroup
        i=0; //Reinicia el número de paises
        aciertos=0; //Reinicia el número de aciertos
        fallos=0; //Reinicia el número de fallos
    }

    public void Toast(int i){ //Muestra un toast al no seleccionar una respuesta
        if (i == 1){
            Toast toast = Toast.makeText(this, R.string.label_noCheck, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,1450);
            toast.show();
        }

        if (i == 2){
            Toast toast = Toast.makeText(this, R.string.label_ultimo, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,1450);
            toast.show();
        }

    }

    public HashMap<String, String> hashmap() { //Crea el hashmap que contiene todos los paises y las capitales
        HashMap<String, String> listaPaises = new HashMap<>();
        ArrayList<HashMap<String, String>> lista = new ArrayList<>();
        lista = dbHandler.paisCapital();
        for (int i = 0; i < lista.size(); i++) {
            listaPaises.put(lista.get(i).get("pais"), lista.get(i).get("capital"));
        }
        return listaPaises;
    }
}