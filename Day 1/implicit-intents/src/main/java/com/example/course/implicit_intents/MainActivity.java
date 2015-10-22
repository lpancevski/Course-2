package com.example.course.implicit_intents;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button call = null;
    private Button dial = null;
    private Button view = null;
    private Button capture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        call = (Button) findViewById(R.id.callIntent);
        dial = (Button) findViewById(R.id.dialIntent);
        view = (Button) findViewById(R.id.viewIntent);
        capture = (Button) findViewById(R.id.captureIntent);

        call.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri telNumber = Uri.parse("tel:022445525");
                Intent i = new Intent(Intent.ACTION_CALL, telNumber);
                startActivity(i);
            }
        });

        dial.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri telNumber = Uri.parse("tel:022445525");
                Intent i = new Intent(Intent.ACTION_DIAL, telNumber);
                startActivity(i);
            }
        });
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("http://www.google.com");
                Intent i = new Intent(Intent.ACTION_VIEW, url);
                startActivity(i);
            }
        });
        capture.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                Intent intentCoordinate = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:50.123,7.1434"));

                Intent intent2Contacts = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("content://contacts/people"));
                startActivity(i);

                // Will Open Maps Application
//                startActivity(intentCoordinate);

                // Contacts Application
//                startActivity(intent2Contacts);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
