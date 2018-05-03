package software_eng;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
    private int taskTimeCompleted;
    private Date dateDue;
    private String dateComplete = "0";
    
    public Task(int taskID, String taskType, String taskTitle, String taskNotes, int taskDuration, String taskAssigned, boolean taskCompleted, String taskPriority, int taskTimeCompleted, Date dateDue)
    {
        setTaskID(taskID);
        setTaskType(taskType);
        setTaskNotes(taskNotes);
        setTaskTitle(taskTitle);
        setTaskDuration(taskDuration);
        setTaskAssigned(taskAssigned);
        setTaskCompleted(taskCompleted);
        setTaskPriority(taskPriority);
        setTaskTimeCompleted(taskTimeCompleted);
        setDateDue(dateDue);
        
        //If complete date has been set
        if(taskTimeCompleted > 0) {
        	 //Convert the date integer to the correct string
        	 String str = Integer.toString(taskTimeCompleted);

             int fullInt = Integer.parseInt(str);
             String yearChar = str.substring(0,3);
             int first3 = Integer.parseInt(yearChar);
             int year = first3 + 1900;
             String monthChar = str.substring(3,4);
             int monthInt = Integer.parseInt(monthChar);
             monthInt += 1;
             String dayChar = str.substring(4,6);
             int dayInt = Integer.parseInt(dayChar);
             
             String date = year + "-" + monthInt + "-" + dayInt;
             
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
             try {
				java.util.Date dateType = formatter.parse(date);
	            dateComplete = formatter.format(dateType);
             } catch (ParseException e) {
				e.printStackTrace();
			}
        }
    }
    
    public Task()
    {
    	
    }
    
    public int generateTaskID() {
    	Database db = new Database();
    	db.connect();
    	List<Task> tasks = db.pullTasks();
    	ArrayList<Integer> taskIDs = new ArrayList<Integer>();
    	for(Task task: tasks) {
    		taskIDs.add(task.getTaskID());
    	}
    	return Collections.max(taskIDs)+1;
    }
    public Date getDateDue() {
    	return dateDue;
    }
    
    public void setDateDue(Date dateDue) {
    	this.dateDue = dateDue;
    }
    
    public int getTaskTimeCompleted() {
    	return taskTimeCompleted;
    }
    
    public String getTaskDateCompleted() {
    	return dateComplete;
    }
    
    public void setTaskTimeCompleted(int taskTimeCompleted)
    {
    	this.taskTimeCompleted = taskTimeCompleted;
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