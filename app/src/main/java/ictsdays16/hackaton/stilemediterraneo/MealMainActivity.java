package ictsdays16.hackaton.stilemediterraneo;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import ictsdays16.hackaton.stilemediterraneo.datamanager.DBManager;
import ictsdays16.hackaton.stilemediterraneo.layouts.FoodLinearLayout;
import ictsdays16.hackaton.stilemediterraneo.listeners.FoodOnTouchListener;

public class MealMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnDragListener {

    private Button dragBtn;
    private GridLayout menuReceiver;
    private GridLayout gridLayout;
    private SharedPreferences prefs;
    private Boolean iconLoaded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences("ictdays2016.hackathon.stileMediterraneo", MODE_PRIVATE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mangiare);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fabMeal = (FloatingActionButton) findViewById(R.id.mangiato);
        fabMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<menuReceiver.getChildCount(); i++) {
                    View v = menuReceiver.getChildAt(i);
                    if(v instanceof FoodLinearLayout) {
                        int id = ((FoodLinearLayout) v).getID();
                        DBManager db = new DBManager(getApplicationContext());
                        db.insertSingleMeal(id);
                    }
                }
                Snackbar.make(view, getResources().getString(R.string.mangiato_button), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridLayout= (GridLayout) findViewById(R.id.MealGridLayout);

        //dragBtn= (Button) findViewById(R.id.drag_btn);

        //findViewById(R.id.drag_btn).setOnTouchListener(new FoodOnTouchListener());

        //findViewById(R.id.drag_btn2).setOnTouchListener(new FoodOnTouchListener());

        //findViewById(R.id.view1).setOnTouchListener(new FoodOnTouchListener());

        menuReceiver= (GridLayout) findViewById(R.id.menu_receiver);

        menuReceiver.setOnDragListener(new View.OnDragListener() {
            //Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
            //Drawable normalShape = getResources().getDrawable(R.drawable.shape);

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        //v.setBackgroundDrawable(enterShape);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        //v.setBackgroundDrawable(normalShape);
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        Log.d("DRAG", "Dropped here " + v.getId() + " " + v.getParent().getClass().toString()+" ID: "+((FoodLinearLayout)event.getLocalState()).getID());
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        GridLayout container = (GridLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        //v.setBackgroundDrawable(normalShape);
                    default:
                        break;
                }
                return true;
            }
        });

        findViewById(R.id.MealMainLayout).setOnDragListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meal_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*remove settings
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.icons_credits) {
            Intent newIntent = new Intent(this,IconCreditsActivity.class);
            startActivity(newIntent);
        }
        else if (id == R.id.developers_credits) {
            Intent newIntent = new Intent(this,DevCreditsActivity.class);
            startActivity(newIntent);
        }
        else if (id == R.id.info_inside) {
            Intent newIntent = new Intent(this,InternalInfoActivity.class);
            startActivity(newIntent);
        }
        else if (id == R.id.info_link) {
            Intent newIntent = new Intent(this,ExternalLinkActivity.class);
            startActivity(newIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //v.setBackgroundDrawable(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                Log.d("DRAG", "Dropped here " + v.getId() + " " + v.getParent().getClass().toString());
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                GridLayout container = (GridLayout) findViewById(R.id.MealGridLayout);
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                //v.setBackgroundDrawable(normalShape);
            default:
                break;
        }
        return true;
    }

    public void  loadIcons(Bundle bundle){
        DBManager dbManager=new DBManager(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Cursor c=dbManager.readData();
        c.moveToFirst();
        //LinearLayout riga=new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //riga.setLayoutParams(params);
        for(int i=0;i<c.getCount();i++){

            Uri uri = Uri.parse(c.getString(1));
            InputStream is=null;
            try {
                is=getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                continue;
            }
            Bitmap bitmap= BitmapFactory.decodeStream(is);
            ImageView imageView=new ImageView(this);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, size.x / 7, size.x / 7, true));
            //imageView.setImageBitmap(bitmap); //DECOMMENTA QUI NEL CASO IL CARICAMNTO DELLE IMMAGINI FOSSE TROPPO LENTO E COMMENTA LA RIGA SOPRA


            FoodLinearLayout linearLayout=new FoodLinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(imageView);

            TextView textView=new TextView(this);
            textView.setText(c.getString(0));
            linearLayout.addView(textView);
            textView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            textView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);

            linearLayout.setID(c.getInt(2));

            linearLayout.setOnTouchListener(new FoodOnTouchListener());
            linearLayout.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
            linearLayout.setHorizontalGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
            //linearLayout.setLayoutParams(params);
            //
            // LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//            params.width=(gridLayout.getWidth()/gridLayout.getColumnCount());


            params.width=((int)(size.x/4.5));

            linearLayout.setLayoutParams(params);

            gridLayout.addView(linearLayout);
            c.moveToNext();
            /*if(i%4==0){
                gridLayout.addView(riga);
                riga=new LinearLayout(this);
                riga.setLayoutParams(params);
            }*/
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {

            prefs.edit().putBoolean("firstrun", false).commit();

            DBManager dbManager=new DBManager(this);
            dbManager.insertData();

            LinearLayout tutorial= (LinearLayout) findViewById(R.id.tutorial1);
            tutorial.setVisibility(View.VISIBLE);
        }
        if(!iconLoaded) {
            loadIcons(null);
            iconLoaded=true;
        }
    }

    public void onClickTutorial(View view){
        view.setVisibility(View.GONE);
    }
}
