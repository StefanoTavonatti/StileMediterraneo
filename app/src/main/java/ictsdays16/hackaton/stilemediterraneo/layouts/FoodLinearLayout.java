package ictsdays16.hackaton.stilemediterraneo.layouts;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by stefano on 11/03/16.
 */
public class FoodLinearLayout extends LinearLayout {

    private int ID;

    public FoodLinearLayout(Context context) {
        super(context);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
