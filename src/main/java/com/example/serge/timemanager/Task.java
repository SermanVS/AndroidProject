package com.example.serge.timemanager;

import java.util.List;

public class Task {

    int _id;
    String _task;

    public Task(){
    }

    public Task(String task){
        this._task = task;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getTask(){
        return this._task;
    }

    public void setTask(String task){
        this._task = task;
    }
}