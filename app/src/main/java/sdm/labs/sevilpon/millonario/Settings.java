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
import android.widget.Spinner;
import android.widget.TextView;

import static sdm.labs.sevilpon.millonario.R.id.ayudas;

public class Settings extends AppCompatActivity {
    private EditText names;
    private EditText textos;
    Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner spinnerCountShoes = (Spinner)findViewById(ayudas);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ayuda));
        spinnerCountShoes.setAdapter(spinnerCountShoesArrayAdapter);
        //Nombre
        names=(EditText)findViewById(R.id.name);
        //Amigo
        textos=(EditText)findViewById(R.id.texto);
        //Número de Ayudas
        gender=(Spinner)findViewById(R.id.ayudas);



    }
    protected void onPause()  {
        //Guarda los datos en el XML
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        String gen=gender.getSelectedItem().toString();
        editor.putString("nombre" , names.getText().toString());
        editor.putString("amigo" , textos.getText().toString());
        editor.putString("ayuda", gen);
        editor.apply();
        super.onPause();
    }

    protected void onResume() {
        //Saca los datos guardados por Pantalla
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        names.setText(prefs.getString("nombre" , ""));
        textos.setText(prefs.getString("amigo" , ""));
        String gen=prefs.getString("ayuda", "");
        TextView optiondisp = (TextView) findViewById(R.id.prueba);
        optiondisp.setText("Ayudas Nº:"+gen+"\n");


        super.onResume();
    }
}
