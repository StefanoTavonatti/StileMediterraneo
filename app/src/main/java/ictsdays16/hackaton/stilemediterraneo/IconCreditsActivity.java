package ictsdays16.hackaton.stilemediterraneo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IconCreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_credits);
    }

    public void returnHome(View v) {
        finish();
    }
}
