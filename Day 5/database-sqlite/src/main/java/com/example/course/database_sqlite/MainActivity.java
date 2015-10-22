package com.example.course.database_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.course.database_sqlite.entities.UserData;
import com.example.course.database_sqlite.entities.UserDataAdapter;
import com.example.course.database_sqlite.services.IMyUserDataCRUDDAO;
import com.example.course.database_sqlite.services.MyUserDataCRUDDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IMyUserDataCRUDDAO dataCRUDDAO = null;
    UserDataAdapter adapter;
    EditText name;
    EditText pass;

    List<UserData> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        name= (EditText) findViewById(R.id.txtName);
        pass= (EditText) findViewById(R.id.txtPass);

        adapter = new UserDataAdapter(this, users);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        // TODO Auto-generated method stub
        dataCRUDDAO = MyUserDataCRUDDAOImpl.getInstance(MainActivity.this);
    }

    public void onClickAddName(View view) {
        UserData data2 = new UserData();
        data2.setUserName(name.getText().toString());
        data2.setPassWord(pass.getText().toString());
        data2.setDeleted(0);
        dataCRUDDAO.createNewUserData(data2);
    }

    public void onClickRetrieveUsers(View view) {
        List<UserData> listOfUserDataObjects = dataCRUDDAO.findAllUserDataObjects();
        users.clear();
        users.addAll(listOfUserDataObjects);
        adapter.notifyDataSetChanged();
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
