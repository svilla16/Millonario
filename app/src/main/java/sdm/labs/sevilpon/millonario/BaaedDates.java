package sdm.labs.sevilpon.millonario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class BaaedDates extends SQLiteOpenHelper {

    private static BaaedDates instance;

    public synchronized static BaaedDates getInstance(Context context) {
        if (instance == null) {
            instance = new BaaedDates(context.getApplicationContext());
        }
        return instance;
    }

    BaaedDates(Context context) {

        super(context, "millonario_database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE puntuaciones " + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, score INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<datos> lista()
    { ArrayList<datos> resultado = new ArrayList<datos>();
        datos item;
        datos listas;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query("puntuaciones", new String[]{"name", "score"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            item = new datos();
            item.setnombres(cursor.getString(0));
            item.setpuntuacions(cursor.getInt(1));
            resultado.add(item);
        }
        cursor.close();
        database.close();
        for(int i =0; i< resultado.size(); i++)
        {
             listas = resultado.get(i);
        }
        return resultado;
    }


    public void addpuntuaciones(String text, int numero) {
        try {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
            values.put("name",text);
        values.put("score", numero);
            database.insert("puntuaciones", null, values);
        database.close();}
        catch (SQLiteException e) {e.printStackTrace();}
    }


    public void clearAllpuntuaciones() {


        SQLiteDatabase database = getWritableDatabase();
        database.delete("puntuaciones", null, null);
        database.close();
    }

    public void deletepuntuaciones(String text) {


        SQLiteDatabase database = getWritableDatabase();
        database.delete("puntuaciones", "name=?", new String[]{text});
        database.close();
    }
}

