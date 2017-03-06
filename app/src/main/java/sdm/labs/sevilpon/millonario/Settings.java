package sdm.labs.sevilpon.millonario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import static sdm.labs.sevilpon.millonario.R.id.ayudas;
import static sdm.labs.sevilpon.millonario.R.id.tab1;

//Si cambias el AppCompatPreferenceActivity por AppCompatActivity funciona el activity_settings
public class Settings extends AppCompatActivity {
    private EditText names;
    private EditText textos;
    public int num;
    public String namess;
    public String amigos;
    public int punt;
    private TextView guardado;
    Spinner numero;
    private BaaedDates sqllite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Quitar esta linea para que funcione el activity_settings
        //addPreferencesFromResource(R.xml.preferences);

        setContentView(R.layout.activity_settings);
        Spinner spinners = (Spinner)findViewById(ayudas);
        ArrayAdapter<String> spinnersad= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ayuda));
        spinners.setAdapter(spinnersad);
        //Nombre
        names=(EditText)findViewById(R.id.name);
        //Amigo
        textos=(EditText)findViewById(R.id.texto);
        //Número de Ayudas
        numero=(Spinner)findViewById(R.id.ayudas);
    }
    protected void onPause()  {
        //Guarda los datos en el XML
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        num= numero.getSelectedItemPosition();
        editor.putString("nombre" , names.getText().toString());
        editor.putString("amigo" , textos.getText().toString());
        editor.putInt("ayuda", num);
        editor.putInt("puntuacion", 1000);
        editor.apply();
        super.onPause();
    }

    protected void onResume() {
        //Saca los datos guardados por Pantalla
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        names.setText(prefs.getString("nombre" , ""));
        textos.setText(prefs.getString("amigo" , ""));
        num=prefs.getInt("ayuda",0);
        numero.setSelection(num);
        amigos = prefs.getString("amigo", "");
        namess = prefs.getString("nombre", "");
        punt = prefs.getInt("puntuacion",0);
        Load(namess,punt);
        super.onResume();
    }

    public void Load(String namess, int punt)
    {   guardado = (TextView) findViewById(R.id.prueba);
        guardado.setText("Jugador:"+namess+"\n"+"Nº de Ayudas:"+num+"\n"+"Puntuacion:"+punt+"\n"
        +"Amigo:"+amigos+"\n");
        sqllite = new BaaedDates(getApplicationContext());
        sqllite.addpuntuaciones(namess,punt);
    }
}
