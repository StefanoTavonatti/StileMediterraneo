package ictsdays16.hackaton.stilemediterraneo.datamanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.text.format.Time;

import java.util.ArrayList;
import java.util.HashMap;

import ictsdays16.hackaton.stilemediterraneo.R;

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
        //cibi visualizzati
        db.execSQL("CREATE TABLE ciboicona(" +
                " \"ID\" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " \"nome\" TEXT," +
                "\"URI\" TEXT  )");
        //cibi con punteggio
        db.execSQL("CREATE TABLE cibobase(" +
                " \"nome\" TEXT," +
                " \"ID\" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " \"porzioni_max\" INTEGER," +
                "\"valore\" INTEGER )");
        //mapping visualizzati/valorizzati
        db.execSQL("CREATE TABLE cibovalore(" +
                " \"ID_icona\" INTEGER," +
                " \"ID_base\" INTEGER," +
                " PRIMARY KEY(ID_icona, ID_base)," +
                " FOREIGN KEY(ID_icona) REFERENCES ciboicona(ID)," +
                " FOREIGN KEY(ID_base) REFERENCES cibobase(ID) )");
        //logging
        db.execSQL("CREATE TABLE pasti(" +
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT," +
                "\"cibobase\" INTEGER," +
                "\"timestamp\" INTEGER," +
                "FOREIGN KEY(cibobase) REFERENCES cibobase(ID) )");


        //db.execSQL("CREATE TABLE...");

        //db.execSQL("INSER INTO....");

    }

    public void insertData(){
        SQLiteDatabase db=getWritableDatabase();
        String defaultPath = "android.resource://ictsdays16.hackaton.stilemediterraneo/drawable/";

        //ciboicona
        Uri path = Uri.parse(defaultPath + "frutta");
        String iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"frutta\",\""+iconName+"\")");//1
        path = Uri.parse(defaultPath + "verdura");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"verdura\",\""+iconName+"\")");//2
        path = Uri.parse(defaultPath + "pesce");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pesce\",\""+iconName+"\")");//3
        path = Uri.parse(defaultPath + "pollame");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pollame\",\""+iconName+"\")");//4
        path = Uri.parse(defaultPath + "carne_rossa");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"carne rossa\",\""+iconName+"\")");//5
        path = Uri.parse(defaultPath + "patate");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"patate\",\""+iconName+"\")");//6
        path = Uri.parse(defaultPath + "uova");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"uova\",\""+iconName+"\")");//7
        path = Uri.parse(defaultPath + "pane");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pane\",\""+iconName+"\")");//8
        path = Uri.parse(defaultPath + "pizza");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pizza\",\""+iconName+"\")");//9
        path = Uri.parse(defaultPath + "pasta");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pasta\",\""+iconName+"\")");//10
        path = Uri.parse(defaultPath + "biscotti");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"prodotti da forno\",\""+iconName+"\")");//11
        path = Uri.parse(defaultPath + "latte");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"latte\",\""+iconName+"\")");//12
        path = Uri.parse(defaultPath + "formaggio");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"latticini\",\""+iconName+"\")");//13
        path = Uri.parse(defaultPath + "dolci");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"dolci\",\""+iconName+"\")");//14
        path = Uri.parse(defaultPath + "vino");
        iconName=path.toString();
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"vino\",\""+iconName+"\")");//15

        //cibobase
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"frutta\",35,1)");//1
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"verdura\",35,1)");//2
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"pesce\",6,3)");//3
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"pollame\",4,3)");//4
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"carne rossa\",1,3)");//5
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"patate\",3,3)");//6
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"uova\",3,3)");//7
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"cereali\",22,2)");//8
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"latte\",14,2)");//9
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"dolci\",3,3)");//10
        db.execSQL("INSERT INTO cibobase(nome, porzioni_max, valore) VALUES (\"frutta\",7,3)");//11

        //cibovalore
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (1,1)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (2,2)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (3,3)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (4,4)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (5,5)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (6,6)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (7,7)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (8,8)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (9,8)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (9,2)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (10,8)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (10,2)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (11,8)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (12,9)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (13,9)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (14,10)");
        db.execSQL("INSERT INTO cibovalore(ID_icona, ID_base) VALUES (15,11)");

        db.close();
    }

    public Cursor readData(){
        SQLiteDatabase db=getReadableDatabase();
        //ctrl+q -> doc
        return db.query(true,"ciboicona",new String[]{"nome","uri","ID"},"",null,"","","","");

    }

    public Cursor countWeekMeal(){
        // ritorna cibi base consumati dallo scorso lunedì: ID, porzioni, porzioni max
        SQLiteDatabase db=getReadableDatabase();
        return db.rawQuery("SELECT id, count(cibobase) AS porzioni, porzioni_max FROM cibobase JOIN pasti ON cibobase=cibobase.ID GROUP BY cibobase WHERE timestamp>+" + this.getMonday(), null);
    }

    //TODO: VERY VERY HARDCODED!
    public ArrayList<Integer> semafori(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        Cursor bases = this.countWeekMeal();
        HashMap<Integer, Integer> mapBases = new HashMap<Integer, Integer>();
        bases.moveToFirst();
        do {
            //map id with remaining meal
            mapBases.put(bases.getInt(0), (bases.getInt(2)-bases.getInt(1)));
        }  while(bases.moveToNext();
        SQLiteDatabase db=getReadableDatabase();

        int gap = 0;
        int col = Color.WHITE;
        for(int i=1; i<=15; i++) {
            Cursor ingredients = this.getBasicIngredients(i);
            ingredients.moveToFirst();
            do {
                gap+=mapBases.get(ingredients.getInt(0));
                if(gap<-1) {
                    col=Color.RED;
                }
                else if(gap<1) {
                    col=Color.YELLOW;
                }
                else if(gap>5) {
                    col = Color.GREEN;
                }
                gap=0;
                result.add(col);
            }  while(ingredients.moveToNext();
        }
        return result;
    }

    //da cibo icona a singoli cibi base
    private Cursor getBasicIngredients(int ID_icona) {
        SQLiteDatabase db=getReadableDatabase();
        //Cursor c = db.query(true, "cibovalore", new String[]{"ID_base"}, "ID_icona="+Integer.toString(ID_icona), null, "","","","");
        Cursor c = db.rawQuery("SELECT ID_base FROM cibovalore Where ID_icona="+Integer.toString(ID_icona), null);
        return c;
    }

    public boolean insertSingleMeal(int ID_icona) {
        Cursor basicIDs = getBasicIngredients(ID_icona);
        SQLiteDatabase db=getWritableDatabase();
        basicIDs.moveToFirst();
        String theQuery ="";
        for(int i=0; i<basicIDs.getCount(); i++) {
            String time = Long.toString(System.currentTimeMillis());
            basicIDs.move(i);
            int idBase = basicIDs.getInt(0);
            theQuery = "INSERT INTO pasti(cibobase, timestamp) VALUES (\""+Integer.toString(idBase)+"\",\""+time+"\")";
        }
        db.execSQL(theQuery);
        //false for errors TODO
        return true;
    }

    private long getMonday() {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c = db.query(true, "pasti", new String[]{"timestamp"}, "", null, "", "", "timestamp DESC", "");
        c.moveToNext();
        long milliSec = c.getLong(0);
        //sec*min*hour*days
        long weekMilliSec = 60*60*24*7;
        return milliSec-(milliSec%weekMilliSec);
    }

    public Cursor overall() {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c = db.rawQuery("SELECT nome, count(cibobase) AS porzioni FROM cibobase JOIN pasti ON cibobase=cibobase.ID GROUP BY cibobase ORDER BY porzioni DESC", null);
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
