package sdm.labs.sevilpon.millonario;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class Scores extends AppCompatActivity {
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Local");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Local");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Friends");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Friends");
        host.addTab(spec);

    }
    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        getMenuInflater().inflate(R.menu.menuscore, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item, View v) //Los iconos del menu que acciones hacen
    {
        if(item.getItemId() == android.R.id.home)
        {return super.onOptionsItemSelected(item);}
        else {
            switch (v.getId()) {
                case R.id.tab1:
                    TextView texts = (TextView) findViewById(R.id.tet);
                    texts.setText(null);
                    break;
                case R.id.tab2:
                    TextView texts2 = (TextView) findViewById(R.id.tet);
                    texts2.setText(null);
                    break;
            }
        }
        return true;
    }

}
