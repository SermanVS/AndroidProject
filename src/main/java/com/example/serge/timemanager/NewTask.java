package com.example.serge.timemanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class NewTask extends Activity implements OnClickListener {
    EditText form1;
    Button btnSubmit;
    Intent intent;
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        form1 = findViewById(R.id.editText1);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        Desktop.db.addTask(new Task(form1.getText().toString()));


    }
    @Override
    public void onClick(View view) {
        intent = new Intent(NewTask.this, Desktop.class);
        ContentValues cv = new ContentValues();
        String task = form1.getText().toString();

        startActivity(intent);



    }
}
