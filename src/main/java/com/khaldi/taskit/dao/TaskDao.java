package com.khaldi.taskit.dao;

import java.util.List;

import com.khaldi.taskit.model.Task;

public interface TaskDao {
	
	List<Task> getTasks(long userId);
	
	Task getTask(long id);
	
	Task addTask(Task task);
	
	Task updateTask(Task task);
	
	boolean deleteTask(long id);
	
	boolean deleteTasks(long userId);
	
	boolean shareTask(String email, Task task);
	
}
