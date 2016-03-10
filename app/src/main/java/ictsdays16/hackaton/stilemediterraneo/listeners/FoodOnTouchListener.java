package ictsdays16.hackaton.stilemediterraneo.listeners;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by stefano on 10/03/16.
 */
public class FoodOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}
