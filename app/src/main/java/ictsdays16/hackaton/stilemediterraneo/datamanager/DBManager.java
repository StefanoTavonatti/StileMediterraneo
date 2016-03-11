package ictsdays16.hackaton.stilemediterraneo.datamanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

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
        Uri path = Uri.parse("android.resource://ictsdays16.hackaton.stilemediterraneo/drawable/" + "cup");

        String uriCup=path.toString();

        //ciboicona
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"frutta\",\""+uriCup+"\")");//1
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"verdura\",\""+uriCup+"\")");//2
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pesce\",\""+uriCup+"\")");//3
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pollame\",\""+uriCup+"\")");//4
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"carne rossa\",\""+uriCup+"\")");//5
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"patate\",\""+uriCup+"\")");//6
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"uova\",\""+uriCup+"\")");//7
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pane\",\""+uriCup+"\")");//8
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pizza\",\""+uriCup+"\")");//9
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"pasta\",\""+uriCup+"\")");//10
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"prodotti da forno\",\""+uriCup+"\")");//11
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"latte\",\""+uriCup+"\")");//12
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"latticini\",\""+uriCup+"\")");//13
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"dolci\",\""+uriCup+"\")");//14
        db.execSQL("INSERT INTO ciboicona(nome,URI) VALUES (\"vino\",\""+uriCup+"\")");//15

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
        return db.query(true,"ciboicona",new String[]{"nome","uri"},"",null,"","","","");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
