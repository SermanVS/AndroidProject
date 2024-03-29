package com.example.serge.timemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskManager";
    private static final String TABLE_TASKS = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_TASK = "task";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TASK + " TEXT,"
                + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        onCreate(db);
    }

    @Override
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK, task.getTask());

        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    @Override
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK, task.getTask());

        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
    }

    @Override
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?", new String[] { String.valueOf(task.getID()) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, null, null);
        db.close();
    }

    @Override
    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setTask(cursor.getString(1));
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        return taskList;
    }
}