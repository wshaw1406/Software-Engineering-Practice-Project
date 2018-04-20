package software_eng;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Will
 */
public class Task {
    private int taskID;
    private String taskType;
    private String taskNotes;
    private String taskTitle;
    private int taskDuration;
    private String taskAssigned;
    private boolean taskCompleted;
    private ArrayList<Task> tasks;
    private String taskPriority;
    
    public Task(int taskID, String taskType, String taskTitle, String taskNotes, int taskDuration, String taskAssigned, boolean taskCompleted, String taskPriority)
    {
        setTaskID(taskID);
        setTaskType(taskType);
        setTaskNotes(taskNotes);
        setTaskTitle(taskTitle);
        setTaskDuration(taskDuration);
        setTaskAssigned(taskAssigned);
        setTaskCompleted(taskCompleted);
        setTaskPriority(taskPriority);
    }
    
    public int generateTaskID() {
    	Database db = new Database();
    	db.connect();
    	int tempCheck = 0;
    	List<Task> tasks = db.pullTasks();
    	for(Task task: tasks) {
    		if(task.getTaskID() > tempCheck) {
    			tempCheck = task.getTaskID();
    		} else {
    			return task.getTaskID()+1;
    		}
    	}
    	return 0;
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
    public int getTaskDuration() {
        return taskDuration;
    }

    /**
     * @param taskTime the taskTime to set
     */
    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }
    /**
     * @return the taskAssigned
     */
    public String getTaskAssigned() {
        return taskAssigned;
    }

    /**
     * @param taskAssigned the taskAssigned to set
     */
    public void setTaskAssigned(String taskAssigned) {
        this.taskAssigned = taskAssigned;
    }
    
    /**
     * @return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
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