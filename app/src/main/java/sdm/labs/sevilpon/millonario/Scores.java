package sdm.labs.sevilpon.millonario;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class Scores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
    }
    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        getMenuInflater().inflate(R.menu.menuscore, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) //Los iconos del menu que acciones hacen
    {
        if(item.getItemId() == android.R.id.home)
        {return super.onOptionsItemSelected(item);}
        return true;
    }

}
