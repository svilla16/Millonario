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
    private int ayudass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner spinnerCountShoes = (Spinner)findViewById(ayudas);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ayuda));
        spinnerCountShoes.setAdapter(spinnerCountShoesArrayAdapter);
        names=(EditText)findViewById(R.id.name);
        textos=(EditText)findViewById(R.id.texto);
        ayudass = ((Spinner)findViewById(R.id.ayudas)).getSelectedItemPosition();

    }
    protected void onPause() {
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre" , names.getText().toString());
        editor.putString("amigo" , textos.getText().toString());
        editor.putInt("ayudas" , ayudass);
        editor.apply();
        super.onPause();
    }

    protected void onResume() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        names.setText(prefs.getString("nombre" , ""));
        textos.setText(prefs.getString("amigo" , ""));
        ayudass = prefs.getInt("ayuda", 2);

        super.onResume();
    }
}
