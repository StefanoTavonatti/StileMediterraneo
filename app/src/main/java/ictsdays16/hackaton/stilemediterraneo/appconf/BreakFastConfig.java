package ictsdays16.hackaton.stilemediterraneo.appconf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ictsdays16.hackaton.stilemediterraneo.MealMainActivity;
import ictsdays16.hackaton.stilemediterraneo.R;

public class BreakFastConfig extends AppCompatActivity {

    public static String CONFIGURATION_FINISHED="CONFIGURATION_FINISHED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_fast_config);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_breakfast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_ok_breakfast) {
            Intent intent=new Intent(this,MealMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(CONFIGURATION_FINISHED,true);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
