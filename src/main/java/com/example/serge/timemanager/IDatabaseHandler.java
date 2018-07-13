package com.example.serge.timemanager;

import java.util.List;

public interface IDatabaseHandler {
    public void addTask(Task task);
    public int getTasksCount();
    public int updateTask(Task task);
    public void deleteTask(Task task);
    public void deleteAll();
    public List<Task> getAllTasks();
}