package com.example.course.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.course.webservices.entity.ContactDetails;
import com.example.course.webservices.entity.ContactsAdapter;
import com.example.course.webservices.utilities.AsyncWSClient;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText address = null;
    private EditText name = null;
    private EditText surname = null;
    private EditText search = null;

    private Button btnAdd = null;
    private Button btnList = null;
    private ListView contactList = null;

    private List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
    private ContactsAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        contactList = (ListView) findViewById(R.id.contacts);
        adapter = new ContactsAdapter(contactDetailsList, this);

        contactList.setAdapter(adapter);

        address = (EditText) findViewById(R.id.address);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        search = (EditText) findViewById(R.id.search);

        btnAdd = (Button) findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    String emailString = address.getText().toString();
                    String nameString = name.getText().toString();
                    String numberString = surname.getText().toString();

                    if ("".equals(emailString) || "".equals(nameString) || "".equals(numberString)) {
                        Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        (AsyncWSClient.getInstance()).saveContactPost(emailString, nameString, numberString);
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                address.setText("");
                name.setText("");
                surname.setText("");
            }
        });

        btnList = (Button) findViewById(R.id.list);
        btnList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String searchString = search.getText().toString();
                    List<ContactDetails> listAllContactsByUser =
                            (AsyncWSClient.getInstance()).listAllContactsByUser(searchString);

                    if (listAllContactsByUser != null && listAllContactsByUser.size() > 0) {
                        contactDetailsList.clear();
                        contactDetailsList.addAll(listAllContactsByUser);
                        adapter.notifyDataSetChanged();
                    }

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
