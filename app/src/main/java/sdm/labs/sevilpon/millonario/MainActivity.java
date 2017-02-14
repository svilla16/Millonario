package sdm.labs.sevilpon.millonario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {    switch (item.getItemId()) {
        case R.id.activity_credits:
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
            break;
    }
        return true;
    }
    public void botones (View v) //Programa para los botones
    {
        switch(v.getId()) //Identificador del boton selecionado
        {
            case R.id.Play: //Play
                Intent intent = new Intent(this, Play.class);
                startActivity(intent);
                break;
            case R.id.Scores: //Scores
                Intent intent2 = new Intent(this, Scores.class);
                startActivity(intent2);
                break;
            case R.id.Settings://Settings
                Intent intent3 = new Intent(this, Settings.class);
                startActivity(intent3);
                break;
        }

    }
}
