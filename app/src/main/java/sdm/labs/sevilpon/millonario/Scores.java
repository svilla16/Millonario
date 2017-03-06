package sdm.labs.sevilpon.millonario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scores extends AppCompatActivity {
    TabHost tabHost;
    String[] local;
    String[] locals;
    ArrayAdapter adapter;
    ListView listView;
    LinearLayout linearLayout;
    ArrayList<datos> listageneral;
    private BaaedDates sqllite;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        context = this;
        //Tab 1 - Local
        TabHost.TabSpec spec = host.newTabSpec("Local");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Local");
        host.addTab(spec);

        //Tab 2 - Friends
        spec = host.newTabSpec("Friends");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Friends");
        host.addTab(spec);
        //nombres local
        listView = (ListView) findViewById(R.id.localn);
        listageneral  = new ArrayList<datos>();
        listageneral.addAll(BaaedDates.getInstance(this).lista());
        //local = getResources().getStringArray(R.array.localn);
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, local);
        //local = getResources().getStringArray(values);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, listageneral);
        //adapter = new ArrayAdapter<datos>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);


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
        linearLayout=(LinearLayout) findViewById(R.id.tab1);
        //Borrar los datos de la base de datos
        sqllite = new BaaedDates(getApplicationContext());
        sqllite.clearAllpuntuaciones();
        linearLayout.removeAllViews();


        return true;
    }



}
