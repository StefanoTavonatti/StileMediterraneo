package ictsdays16.hackaton.stilemediterraneo.datamanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stefano on 10/03/16.
 */
public class DBManager extends SQLiteOpenHelper {

    final static String DB_NAME="StileMediterraneo";
    final static int VERSION=1;

    private Context context;

    public DBManager(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ciboicona(" +
                " \"ID\" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " \"nome\" TEXT," +
                "\"URI\" TEXT  )");


        //db.execSQL("CREATE TABLE...");

        //db.execSQL("INSER INTO....");
    }

    public void insertData(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pizza\",\"cup\")");
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"anatra\",\"cup\")");
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pasta\",\"cup\")");
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"sushi\",\"cup\")");
    }

    public Cursor readData(){
        SQLiteDatabase db=getReadableDatabase();
        return db.query(true,"ciboicona",new String[]{"nome","uri"},"",null,"","","","");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
