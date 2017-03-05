package sdm.labs.sevilpon.millonario;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Play extends AppCompatActivity {
    List<Question> arrayQuestions = Question.generateQuestionList();


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

        pregunta.setText(arrayQuestions.get(0).text);
        boton1.setText(arrayQuestions.get(0).answer1);
        boton2.setText(arrayQuestions.get(0).answer2);
        boton3.setText(arrayQuestions.get(0).answer3);
        boton4.setText(arrayQuestions.get(0).answer4);

        //Recuperar los datos guardados temporalmente del settings
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        EditText names=(EditText)findViewById(R.id.name);
        String namess = prefs.getString("nombre", "");
        String amigos = prefs.getString("amigo", "");
        String num=prefs.getString("ayuda", "");
        TextView guardado = (TextView) findViewById(R.id.prueba2);
        guardado.setText("Jugador: "+namess+"\n"+"Amigo: "+amigos+"\n"+"Nº de Ayudas: "+num+"\n");
    }
}
