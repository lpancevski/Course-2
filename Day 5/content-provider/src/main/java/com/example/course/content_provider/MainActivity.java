package com.example.course.content_provider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.course.content_provider.entities.PersonAdapter;
import com.example.course.content_provider.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Student> studentList = new ArrayList<>();
    PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);

        personAdapter = new PersonAdapter(this, studentList);
        listView.setAdapter(personAdapter);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText) findViewById(R.id.txtName)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText) findViewById(R.id.txtGrade)).getText().toString());

        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI,
                values);

        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG)
                .show();
    }

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.provider.College/students";
//		String URL = "content://contacts/people/3";
        Uri students = Uri.parse(URL);
//		Cursor c = managedQuery(students, null, null, null, "name");
        Cursor c = getContentResolver().query(students, null, null, null, "name");
        studentList.clear();
        if (c.moveToFirst()) {
            do {
                Student student = new Student();
                student.setName(c.getString(c.getColumnIndex(StudentsProvider.NAME)));
                student.setGrade(c.getString(c.getColumnIndex(StudentsProvider.GRADE)));
                studentList.add(student);
            } while (c.moveToNext());
        }
        personAdapter.notifyDataSetChanged();
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
