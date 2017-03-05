package sdm.labs.sevilpon.millonario;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Play extends AppCompatActivity {
    //Array global que contendra todas las preguntas y sus datos
    ArrayList<Question> arrayQuestions = new ArrayList<>(15);
    ArrayList<String> arrayMoney = new ArrayList<>(15);

    //Pregunta actual
    int pregActual = 0;

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

    /*String number = null;
    String text = null;
    String answer1 = null;
    String answer2 = null;
    String answer3 = null;
    String answer4 = null;
    String right = null;
    String audience = null;
    String phone = null;
    String fifty1 = null;
    String fifty2= null;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Asignacion de los elementos del DOM
        Button boton1 = (Button) findViewById(R.id.button1);
        Button boton2 = (Button) findViewById(R.id.button2);
        Button boton3 = (Button) findViewById(R.id.button3);
        Button boton4 = (Button) findViewById(R.id.button4);
        TextView pregunta = (TextView) findViewById(R.id.lv_QuestionGame);

        TextView dineroActual = (TextView) findViewById(R.id.lv_money);
        TextView numPregActual = (TextView) findViewById(R.id.lv_questionNumber);
        /*numPregActual.setText(pregActual);*/

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

            pregunta.setText(arrayQuestions.get(pregActual).getText());

            boton1.setText(arrayQuestions.get(pregActual).getAnswer1());
            boton2.setText(arrayQuestions.get(pregActual).getAnswer2());
            boton3.setText(arrayQuestions.get(pregActual).getAnswer3());
            boton4.setText(arrayQuestions.get(pregActual).getAnswer4());

            boton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    accionesBotones(v.getId());
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

    private void  accionesBotones(int idBoton){
        Question preguntaActual = arrayQuestions.get(pregActual);
        Log.d("ID BOTON: ", String.valueOf(idBoton));
    }







}
