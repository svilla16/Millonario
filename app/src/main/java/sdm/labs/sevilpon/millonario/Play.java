package sdm.labs.sevilpon.millonario;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Play extends AppCompatActivity{
    //Array global que contendra todas las preguntas y sus datos
    ArrayList<Question> arrayQuestions = new ArrayList<>(15);
    ArrayList<Integer> arrayMoney = new ArrayList<>(15);

    //Variables del DOM que recibiran valor al crearse
    Button boton1;
    Button boton2;
    Button boton3;
    Button boton4;

    TextView pregunta;
    TextView dineroActual;
    TextView numPregActual;

    //Pregunta actual (Variable interna para llevar control del objeto Question a usar)
    int pregActual = 0;

    int dineroGanado = 0;

    // Constants XML Tags
    //Etiqueta TAG
    private static final String PREGUNTA = "question";

    //Atributos del TAG
    private static final String NUMBER = "number";
    private static final String TEXT = "text";
    private static final String ANSWER1 = "answer1";
    private static final String ANSWER2 = "answer2";
    private static final String ANSWER3 = "answer3";
    private static final String ANSWER4 = "answer4";
    private static final String RIGHT = "right";
    private static final String AUDIENCE = "audience";
    private static final String PHONE = "phone";
    private static final String FIFTY1 = "fifty1";
    private static final String FIFTY2 = "fifty2";



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Asignacion de los elementos del DOM
        boton1 = (Button) findViewById(R.id.button1);
        boton2 = (Button) findViewById(R.id.button2);
        boton3 = (Button) findViewById(R.id.button3);
        boton4 = (Button) findViewById(R.id.button4);
        pregunta = (TextView) findViewById(R.id.lv_QuestionGame);

        dineroActual = (TextView) findViewById(R.id.lv_money);
        numPregActual = (TextView) findViewById(R.id.lv_questionNumber);

        /*si no existe el archivo se termina ejecucion*/
        XmlResourceParser parser = getResources().getXml(R.xml.questions);
        if (parser == null) {
            //Se volvera al menu inicial
            Toast.makeText(Play.this, R.string.error_questions_charge, Toast.LENGTH_LONG).show();
        }else{
            arrayQuestions =  readXmlFile(parser);
            /*for (int i= 0; i < arrayQuestions.size(); i++) {
                Log.d("Contenido array " + i, arrayQuestions.get(i).text);
            }*/

            arrayMoney.add(0);
            arrayMoney.add(100);
            arrayMoney.add(200);
            arrayMoney.add(300);
            arrayMoney.add(500);
            arrayMoney.add(1000);
            arrayMoney.add(2000);
            arrayMoney.add(4000);
            arrayMoney.add(8000);
            arrayMoney.add(16000);
            arrayMoney.add(32000);
            arrayMoney.add(64000);
            arrayMoney.add(125000);
            arrayMoney.add(250000);
            arrayMoney.add(500000);
            arrayMoney.add(1000000);

            siguientePregunta();



            boton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    accionesBotones(v);
                }
            });

            boton2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    accionesBotones(v);
                }
            });

            boton3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    accionesBotones(v);
                }
            });

            boton4.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    accionesBotones(v);
                }
            });

        }

    }

    /*
        Reads the contents of the XML file and displays in the screen
    */
    private ArrayList<Question> readXmlFile(XmlResourceParser parser) {
        //ArrayList a devolver
        ArrayList<Question> listapreguntas = new ArrayList(15);

        // Hold references to View
        TextView tv;
        EditText et = null;

        int event;

        //Creacion de las variables que crearan el objeto
        String number = "";
        String text = "";
        String answer1 = "";
        String answer2 = "";
        String answer3 = "";
        String answer4 = "";
        String right = "";
        String audience = "";
        String phone = "";
        String fifty1 = "";
        String fifty2 = "";

        try {

            // Keep reading from the XML file until an End of Document tag is reached
            while ((event = parser.next()) != XmlPullParser.END_DOCUMENT) {

                // The action to perform depends on the event read
                switch (event) {

                    // Start of tag
                    case XmlPullParser.START_TAG:

                        // From tag
                        if (parser.getName().equalsIgnoreCase(PREGUNTA)) {

                            //Recogemos los parametros de la pregunta del XML para montar un objeto question
                            number = parser.getAttributeValue(null, NUMBER);
                            text = parser.getAttributeValue(null, TEXT);
                            answer1 = parser.getAttributeValue(null, ANSWER1);
                            answer2 = parser.getAttributeValue(null, ANSWER2);
                            answer3 = parser.getAttributeValue(null, ANSWER3);
                            answer4 = parser.getAttributeValue(null, ANSWER4);
                            right = parser.getAttributeValue(null, RIGHT);
                            audience = parser.getAttributeValue(null, AUDIENCE);
                            phone = parser.getAttributeValue(null, PHONE);
                            fifty1 = parser.getAttributeValue(null, FIFTY1);
                            fifty2 = parser.getAttributeValue(null, FIFTY2);


                            //Creamos un objeto de tipo question para devolver con los datos
                            Question question = new Question(number, text, answer1, answer2, answer3, answer4, right, audience, phone, fifty1, fifty2);


                            //Añadimos objeto al array
                            listapreguntas.add(question);
                            /*Log.d("PREGUNTA: " , question.text.toString());*/
                        }
                        break;

                    // Text within a tag
                    case XmlPullParser.TEXT:
                        // Display the value of the associated Text in the corresponding EditText
                        et.setText(parser.getText());
                        break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return listapreguntas;
        }


    }

    public void  accionesBotones(View v){
        Question preguntaActual = arrayQuestions.get(pregActual);
        String right = preguntaActual.getRight();
        Button b_respuesta = (Button) v;

        Log.d("ID V: ", String.valueOf(v.getId()));
        Log.d("ID Boton 1: ", String.valueOf(R.id.button1));

        switch (v.getId()){
            case (R.id.button1):
                if(right.equals("1")){
                    b_respuesta.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerCorrect, Toast.LENGTH_SHORT).show();
                    pregActual++;
                    //TODO: Tiempo de espera para que se vean los cambios en la pantalla

                    siguientePregunta();
                }else{
                    b_respuesta.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerInCorrect, Toast.LENGTH_SHORT).show();
                    terminarJuego();
                }

                break;
            case (R.id.button2):
                if(right.equals("2")){
                    b_respuesta.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerCorrect, Toast.LENGTH_SHORT).show();
                    pregActual++;
                    //TODO: Tiempo de espera para que se vean los cambios en la pantalla

                    siguientePregunta();
                }else{
                    b_respuesta.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerInCorrect, Toast.LENGTH_SHORT).show();
                }

                break;
            case (R.id.button3):
                if(right.equals("3")){
                    b_respuesta.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerCorrect, Toast.LENGTH_SHORT).show();
                    pregActual++;
                    //TODO: Tiempo de espera para que se vean los cambios en la pantalla

                    siguientePregunta();
                }else{
                    b_respuesta.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerInCorrect, Toast.LENGTH_SHORT).show();
                }

                break;
            case (R.id.button4):
                if(right.equals("4")){
                    b_respuesta.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerCorrect, Toast.LENGTH_SHORT).show();
                    pregActual++;
                    //TODO: Tiempo de espera para que se vean los cambios en la pantalla

                    siguientePregunta();
                }else{
                    b_respuesta.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                    Toast.makeText(Play.this, R.string.l_answerInCorrect, Toast.LENGTH_SHORT).show();
                }

                break;
        }
        b_respuesta.setBackgroundResource(android.R.drawable.btn_default);

        /*String right = arrayQuestions.get(pregActual).getRight();
        Button b_respuesta = (Button) v;
        String respuesta = b_respuesta.getText().toString();
        if(respuesta.equals(right)){
            b_respuesta.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
            try {
                wait(1000);
            }catch (Exception e){e.printStackTrace();}

            //Se volvera al menu inicial
            Toast.makeText(Play.this, R.string.l_answerCorrect, Toast.LENGTH_LONG).show();
            pregActual++;
            siguientePregunta();

        }else{
            b_respuesta.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
            Toast.makeText(Play.this, R.string.l_answerInCorrect, Toast.LENGTH_LONG).show();
            pregActual = 0;
        }*/
    }

    private void siguientePregunta(){
        pregunta.setText(arrayQuestions.get(pregActual).getText());

        boton1.setText(arrayQuestions.get(pregActual).getAnswer1());
        boton2.setText(arrayQuestions.get(pregActual).getAnswer2());
        boton3.setText(arrayQuestions.get(pregActual).getAnswer3());
        boton4.setText(arrayQuestions.get(pregActual).getAnswer4());

        if(pregActual<14)dineroActual.setText(String.valueOf(arrayMoney.get(pregActual+1)));
        if(pregActual<14)numPregActual.setText(String.valueOf(pregActual+1));
        if(pregActual == 5 ||pregActual == 10) dineroGanado = arrayMoney.get(pregActual-1);
    }

    public void terminarJuego(){
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Fin del juego")
                .setMessage("Esta es tu puntuacion:" + "<br/>" + "Dinero ganado y puntuación: " + dineroGanado + "<br/>" + "preguntas correctas: " + (pregActual-1) )
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //TODO: Guardar datos en db
                        resetearValores();
                        finishActivity(0);
                    }
                })
                /*.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void resetearValores(){
        pregActual = 0;
        dineroGanado = 0;

    }

    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        getMenuInflater().inflate(R.menu.menuplay, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) //Los iconos del menu que acciones hacen
    {
        if(item.getItemId() == android.R.id.home)
        {return super.onOptionsItemSelected(item);}

        return true;
    }

    protected void onPause()  { ///
        //Guarda los datos en el XML
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("puntuacion", arrayMoney.get(pregActual+1));
        editor.apply();//
        super.onPause();
    }










}
