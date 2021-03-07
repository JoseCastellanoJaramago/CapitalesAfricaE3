package com.example.capitalesAfrica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var listaPaises = hashMapOf<String, String>()
    var aciertos = 0 //Variable para almacenar los aciertos
    var fallos = 0 //Variable para almacenar los fallos
    var i = 0 //Variable para recorrer todos los paises de la base de datos
    var pais = "" //Variable para almacenar el pais que se muestra
    var respuesta = "" //Variable para almacenar la respuesta dada
    lateinit var randomList: List<Int> //Lista para hacer aleatoria la lista de paises
    var dbHandler = DbHandler(this@MainActivity) //Objeto de la clase DbHandler

    fun insertarPaises() { //Función para insertar los paises en la base de datos
        dbHandler.insertData(1, "Angola","Luanda")
        dbHandler.insertData(2, "Argelia","Argel")
        dbHandler.insertData(3, "Benín","Porto Novo")
        dbHandler.insertData(4, "Botswana","Gaborone")
        dbHandler.insertData(5, "Burkina Faso","Uagadugú")
        /*dbHandler.insertData(6, "Burundi","Uagadugú")
        dbHandler.insertData(7, "Cabo Verde","Praia")
        dbHandler.insertData(8, "Camerún","Yaundé")
        dbHandler.insertData(9, "Chad","N'Djamena")
        dbHandler.insertData(10, "Comoras","Moroni")
        dbHandler.insertData(11, "Congo","Brazzaville")
        dbHandler.insertData(12, "Costa de Marfil","Yamoussoukro")
        dbHandler.insertData(13, "Djibouti","Djibouti")
        dbHandler.insertData(14, "Egipto","El Cairo")
        dbHandler.insertData(15, "Eritrea","Asmara")
        dbHandler.insertData(16, "Etiopía","Adís Abeba")
        dbHandler.insertData(17, "Gabón","Libreville")
        dbHandler.insertData(18, "Gambia","Banjul")
        dbHandler.insertData(19, "Ghana","Accra")
        dbHandler.insertData(20, "Guinea","Conakry")
        dbHandler.insertData(21, "Guinea Bissau","Bissau")
        dbHandler.insertData(22, "Guinea Ecuatorial","Malabo")
        dbHandler.insertData(23, "Kenia","Nairobi")
        dbHandler.insertData(24, "Lesoto","Maseru")
        dbHandler.insertData(25, "Liberia","Monrovia")
        dbHandler.insertData(26, "Libia","Tripoli")
        dbHandler.insertData(27, "Madagascar","Antananarivo")
        dbHandler.insertData(28, "Malawi","Lilongüe")
        dbHandler.insertData(29, "Mali","Bamako")
        dbHandler.insertData(30, "Marruecos","Rabat")
        dbHandler.insertData(31, "Mauricio","Port Louis")
        dbHandler.insertData(32, "Mauritania","Nuakchot")
        dbHandler.insertData(33, "Mozambique","Maputo")
        dbHandler.insertData(34, "Namibia","Windhoek")
        dbHandler.insertData(35, "Niger","Niamey")
        dbHandler.insertData(36, "Nigeria","Abuja")
        dbHandler.insertData(37, "R. Centroafricana","Bangui")
        dbHandler.insertData(38, "R.D. del Congo","Kinshasa")
        dbHandler.insertData(39, "Ruanda","Kigali")
        dbHandler.insertData(40, "Tomé y Príncipe","Santo Tomé")
        dbHandler.insertData(41, "Senegal","Dakar")
        dbHandler.insertData(42, "Seychelles","Victoria")
        dbHandler.insertData(43, "Sierra Leona","Freetown")
        dbHandler.insertData(44, "Somalia","Mogadiscio")
        dbHandler.insertData(45, "Sudáfrica","Ciudad del Cabo")
        dbHandler.insertData(46, "Sudán","Jartum")
        dbHandler.insertData(47, "Sudán del Sur","Yuba")
        dbHandler.insertData(48, "Swazilandilandia","Mbabane")
        dbHandler.insertData(49, "Tanzania","Dodoma")
        dbHandler.insertData(50, "Togo","Lomé")
        dbHandler.insertData(51, "Túnez","Túnez")
        dbHandler.insertData(52, "Uganda","Kampala")
        dbHandler.insertData(53, "Zambia","Lusaka")
        dbHandler.insertData(54, "Zimbabue","Harare")*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        labelEmpezar.setText(R.string.label_empezar)
        dbHandler.deleteAll()
        insertarPaises()
        btnCambiar.setOnClickListener(){

            if (!capitales.isVisible){ //Comprueba que el radio group no sea visible para comenzar el juego

                listaPaises = hashmap() //Se añade a la variable todos los paises y capitales

                // Hace una lista de numeros aleatorios sin que se repitan para recorrer los paises
                val s: MutableSet<Int> = mutableSetOf()
                while (s.size < listaPaises.size) { s.add((0 until listaPaises.size).random()) }
                randomList = s.toList()

                capitales.setVisibility(View.VISIBLE) //Hace que el radio group sea visible

                muestraInicio()
            }else{
                pasaSiguiente()
            }
        }
    }

    fun muestraInicio(){ //Función que genera el pais y las capitales

        if (i != randomList.size){ //Comprueba que no se hayan mostrado todos los paises

            if (i == randomList.size-1) {
                Toast(2)
                labelEmpezar.setText(R.string.label_verResultados)
            } else {
                labelEmpezar.setText(R.string.label_cambiar)
            }

            pais = listaPaises.keys.elementAt(randomList[i]) //Obtiene el pais teniendo en cuenta la lista aleatoria
            txtPais.setText(pais)

            // Hace otra lista de numeros aleatorios sin que se repitan para que muestren capitales aleatorias diferentes
            val s1: MutableSet<Int> = mutableSetOf()
            s1.add(i) //Le añade la capital correspondiente al pais para que siempre se muestre la respuesta correcta
            while (s1.size < 4) { s1.add((0 until listaPaises.size).random()) }
            val randomList1 = s1.toList()

            // Crea una lista con los radioButton y los hace aleatorio para que se muestren las 4 capitales de forma aleatoria
            val lista = arrayListOf(radioButton1, radioButton2, radioButton3, radioButton4)
            lista.shuffle()

            // Asigna a cada radioButton una capital, incluyendo la correcta
            for (j in 0..3){
                lista.get(j).setText(listaPaises.values.elementAt(randomList[randomList1[j]]).toString())
            }

            i++ //Aumenta la variable que cuenta los paises

        }else{
            reiniciar() //Si se han mostrado todos los paises, se reinicia el juego
        }
    }

    fun pasaSiguiente(){ //Hace que se cambie de pais y capitales
        if (capitales.getCheckedRadioButtonId() == -1) { //Comprueba que se haya seleccionado una opción
            Toast(1)
        }else{
            checkAcierto()
            muestraInicio()
            capitales.clearCheck()
        }
    }

    fun checkAcierto(){ //Comprueba que la respuesta sea correcta
        val id: Int = capitales.checkedRadioButtonId
        val radio: RadioButton = findViewById(id)
        respuesta = radio.text.toString() //Obtiene la respuesta seleccionada
        if (respuesta.equals(listaPaises.get(pais))){ //Compara la  respuesta con la capital correcta
            aciertos++ //Aumenta los aciertos si la respuesta es correcta
        }else{
            fallos++ //Aumenta los fallos si la respuesta es incorrecta
        }
    }

    fun reiniciar(){ //Función para reiniciar el juego
        Log.i("Resultados", "Botón Ver Resultados Pulsado") //Muestra un mensaje de imformación
        val intent = Intent(this@MainActivity, ResultadosActivity::class.java) //Objeto para llamar al Activity de los resultados.
        intent.putExtra("paises", (listaPaises.size).toString()) //Le pasa como parametros el número de paises
        intent.putExtra("aciertos", aciertos.toString()) //Le pasa como parametros el número de aciertos
        intent.putExtra("fallos", fallos.toString()) //Le pasa como parametros el número de fallos
        startActivity(intent) //Comienza el activity

        labelEmpezar.setText(R.string.label_empezar) //Muestra en el label el mensaje para volver a empezar
        txtPais.setText("") //Hace que el label del pais esté vacío
        capitales.setVisibility(View.INVISIBLE) //Hace invisible el radioGroup
        i=0 //Reinicia el número de paises
        aciertos=0 //Reinicia el número de aciertos
        fallos=0 //Reinicia el número de fallos
    }
    fun Toast(i: Int){ //Muestra un toast al no seleccionar una respuesta
        if (i == 1){
            val toastNull = Toast.makeText(applicationContext,R.string.label_noCheck, Toast.LENGTH_SHORT)
            toastNull.setGravity(Gravity.TOP or Gravity.START,220,1450)
            toastNull.show()
        }

        if (i == 2){
            val toastUltimo = Toast.makeText(applicationContext,R.string.label_ultimo, Toast.LENGTH_SHORT)
            toastUltimo.setGravity(Gravity.TOP or Gravity.START,220,1450)
            toastUltimo.show()
        }

    }

    fun hashmap(): HashMap<String, String> { //Crea el hashmap que contiene todos los paises y las capitales
        val listaPaises = hashMapOf<String, String>()
        val lp = dbHandler.paisCapital

        for (num in 0..lp.size-1) {
            listaPaises.put(dbHandler.paisCapital[num].values.elementAt(1), dbHandler.paisCapital[num].values.elementAt(0))
        }

        return listaPaises
    }
}