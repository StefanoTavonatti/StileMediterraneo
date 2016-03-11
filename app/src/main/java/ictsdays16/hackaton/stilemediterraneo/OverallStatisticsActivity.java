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
        list.moveToFirst();
        LinearLayout box = (LinearLayout) findViewById(R.id.overallBox);
        for(int i=0; i<list.getCount()-1; i++) {
            list.move(i);
            String food = list.getString(0);
            int count = list.getInt(1);
            TextView text = new TextView(this);
            text.setText(food+": "+count+ " porzioni.");
            text.setTextSize(20f);
            box.addView(text);
        }
    }

    public void returnHome(View v) {
        finish();
    }
}
