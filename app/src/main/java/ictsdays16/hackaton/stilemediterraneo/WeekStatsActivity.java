package ictsdays16.hackaton.stilemediterraneo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WeekStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_stats);
    }

    public void returnHome(View v) {
        finish();
    }
}
