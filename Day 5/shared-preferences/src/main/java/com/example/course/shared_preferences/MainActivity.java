package com.example.course.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    private Boolean boolean1;
    private Boolean effectActive;

    private final String IS_ACTIVE = "isActive";
    private final String EFFECTIVE_ACTIVE = "effectiveActive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences =
                getSharedPreferences("defaultShared", Context.MODE_PRIVATE);
        editor = preferences.edit();

        boolean1 = preferences.getBoolean(IS_ACTIVE, false);
        System.out.println("Test isActive" + boolean1.toString());

        editor.putBoolean(IS_ACTIVE, true);
        editor.commit();

        boolean1 = preferences.getBoolean(IS_ACTIVE, false);
        System.out.println("Test isActive" + boolean1.toString());

        if (preferences.contains(EFFECTIVE_ACTIVE)) {
            effectActive = preferences.getBoolean(EFFECTIVE_ACTIVE, true);
            System.out.println("Test isEffectiveActive:" + effectActive.toString());
        } else {
            editor.putBoolean(EFFECTIVE_ACTIVE, true);
            editor.commit();

            effectActive = preferences.getBoolean(EFFECTIVE_ACTIVE, true);
            System.out.println("Test isEffectiveActive:" + effectActive.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
