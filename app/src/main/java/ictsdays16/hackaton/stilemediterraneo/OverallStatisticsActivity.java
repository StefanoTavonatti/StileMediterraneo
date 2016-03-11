package ictsdays16.hackaton.stilemediterraneo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ictsdays16.hackaton.stilemediterraneo.datamanager.DBManager;

public class OverallStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_statistics);
        DBManager db = new DBManager(this);
        Cursor list = db.overall();

        LinearLayout box = (LinearLayout) findViewById(R.id.overallBox);
        if(list.moveToFirst())
            do{
                String food =list.getString(list.getColumnIndex("nome"));
                int count = list.getInt(list.getColumnIndex("porzioni"));
                TextView text = new TextView(this);
                text.setText(food+": "+count+ " porzioni.");
                text.setTextSize(20f);
                box.addView(text);
            }while(list.moveToNext());
    }

    public void returnHome(View v) {
        finish();
    }
}
