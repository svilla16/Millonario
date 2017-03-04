package sdm.labs.sevilpon.millonario;

import android.content.Intent;
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
import java.util.List;

public class Scores extends AppCompatActivity {
    TabHost tabHost;
    String[] local;
    String[] locals;
    ArrayAdapter adapter;
    ListView listView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

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
        local = getResources().getStringArray(R.array.localn);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, local);
        listView.setAdapter(adapter);
        //puntuaciones local
        ListView listViews = (ListView) findViewById(R.id.localp);
        locals = getResources().getStringArray(R.array.localp);
        ArrayAdapter<String> adapters = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locals);
        listViews.setAdapter(adapters);


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
        linearLayout.removeAllViews();

        return true;
    }


    
}
