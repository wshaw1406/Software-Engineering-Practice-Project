/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_eng;

import java.util.ArrayList;

/**
 *
 * @author Will
 */
public class Task {
    private String taskID;
    private String taskType;
    private String taskNotes;
    private String taskTitle;
    private int taskTime;
    private boolean taskAssigned;
    private boolean taskCompleted;
    private ArrayList<Task> tasks;
    private String taskPriority;
    
    public Task(String taskID, String taskType, String taskTitle, String taskNotes, int taskTime, boolean taskAssigned, boolean taskCompleted, String taskPriority)
    {
        setTaskID(taskID);
        setTaskType(taskType);
        setTaskNotes(taskNotes);
        setTaskTitle(taskTitle);
        setTaskTime(taskTime);
        setTaskAssigned(taskAssigned);
        setTaskCompleted(taskCompleted);
        setTaskPriority(taskPriority);
    }
    
    public String getTaskPriority() {
    	return taskPriority;
    }
    
    public void setTaskPriority(String taskPriority) {
    	this.taskPriority = taskPriority;
    }
    
    public boolean getTaskCompleted() {
    	return taskCompleted;
    }
    
    public void setTaskCompleted(boolean taskCompleted) {
    	this.taskCompleted = taskCompleted;
    }
    /**
     * @return the taskTime
     */
    public int getTaskTime() {
        return taskTime;
    }

    /**
     * @param taskTime the taskTime to set
     */
    public void setTaskTime(int taskTime) {
        this.taskTime = taskTime;
    }
    /**
     * @return the taskAssigned
     */
    public boolean getTaskAssigned() {
        return taskAssigned;
    }

    /**
     * @param taskAssigned the taskAssigned to set
     */
    public void setTaskAssigned(boolean taskAssigned) {
        this.taskAssigned = taskAssigned;
    }
    
    /**
     * @return the taskID
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the taskType
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * @param taskType the taskType to set
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * @return the taskNotes
     */
    public String getTaskNotes() {
        return taskNotes;
    }

    /**
     * @param taskNotes the taskNotes to set
     */
    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    /**
     * @return the taskTitle
     */
    public String getTaskTitle() {
        return taskTitle;
    }

    /**
     * @param taskTitle the taskTitle to set
     */
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    
    public void removeTask(Task task)
    {
        tasks.remove(task);
    }
    
    public static void displayTasks(ArrayList<Task> tasks)
    {
        for(Task task : tasks)
        {
            System.out.println("TaskID: " + task.getTaskID());
        }
    }
}
