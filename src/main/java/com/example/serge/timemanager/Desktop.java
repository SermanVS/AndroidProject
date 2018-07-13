package com.example.serge.timemanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.database.sqlite.*;

import java.util.List;

public class Desktop extends AppCompatActivity {

    TextView tView1, tView2, tView3, tView4;
    final String LOG_TAG = "myLogs";
    public static int count = 0;
    static DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        tView1 = findViewById(R.id.textView1);
        tView2 = findViewById(R.id.textView2);
        tView3 = findViewById(R.id.textView3);
        tView4 = findViewById(R.id.textView4);
        List<Task> tasks = db.getAllTasks();
        db = new DatabaseHandler(this);
        for (Task tk: tasks)   {
            tView1.setText(tk.getTask());
        }

    }
    public void onClickFirst (View view)
    {
        count++;
        Intent intent = new Intent(Desktop.this, NewTask.class);
        startActivity(intent);
    }
    /*public void textOnClick(View view)
    {
        Intent intent = new Intent(Desktop.this, NewTask.class);
        startActivity(intent);
    }*/

}
class DBHelper extends SQLiteOpenHelper{

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "task" + ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


