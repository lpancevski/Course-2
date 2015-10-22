package com.example.course.explicit_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected static final int REQUEST_CODE = 0;
    private EditText editText = null;
    private Button button = null;
    private TextView returnedValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        editText = (EditText) findViewById(R.id.editText);
        returnedValue = (TextView) findViewById(R.id.returnedValue);

        button = (Button) findViewById(R.id.pushValue);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("pushedValue", editText.getText().toString());
                Answer answer = new Answer(1, true, "Some Text");
                intent.putExtra("pushedParcelable", answer);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            if (data.hasExtra("pushedBackValue")){
                returnedValue.setText(data.getExtras().getString("pushedBackValue"));
            } else {
                returnedValue.setText("NOTHING RETURNED");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
