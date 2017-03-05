package sdm.labs.sevilpon.millonario;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Play extends AppCompatActivity {
    ArrayList<Question> arrayQuestions = new ArrayList<>(15);

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
        Button boton1 = (Button) findViewById(R.id.button1);
        Button boton2 = (Button) findViewById(R.id.button2);
        Button boton3 = (Button) findViewById(R.id.button3);
        Button boton4 = (Button) findViewById(R.id.button4);
        TextView pregunta = (TextView) findViewById(R.id.lv_QuestionGame);

        /*si no existe el archivo se termina ejecucion*/
        XmlResourceParser parser = getResources().getXml(R.xml.questions);
        if (parser == null) {
            //Se volvera al menu inicial
            Toast.makeText(Play.this, R.string.error_questions_charge, Toast.LENGTH_LONG).show();
        }else{
            /*arrayQuestions =  */readXmlFile(parser);
            System.out.println("Lista obtenida" + arrayQuestions);
            /*pregunta.setText(arrayQuestions.get(0).getText());*/
        }

    }

    /*
        Reads the contents of the XML file and displays in the screen
    */
    private void readXmlFile(XmlResourceParser parser) {
        //ArrayList a devolver
        ArrayList<Question> listapreguntas = new ArrayList(15);
        System.out.print("Listapreguntas: " + listapreguntas);

        // Hold references to View
        TextView tv;
        EditText et = null;

        /*XmlPullParser parser = Xml.newPullParser();*/
        InputStreamReader reader = null;
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

            // Get a reader to the file in the application internal storage
            reader = new InputStreamReader(openFileInput("questions"));
            // Associate this reader to the XmlPullParser
            parser.setInput(reader);

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
            // Ensure that the Reader is closed (if opened)
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /*return listapreguntas;*/
    }







}
