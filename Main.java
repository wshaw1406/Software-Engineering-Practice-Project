package software_eng;

import java.util.*;


/**
 *
 * @author Will
 */
public class Main {
	
	public static ArrayList<Task> tasks = new ArrayList<>();
	public static ArrayList<User> users = new ArrayList<>();
	public static User user;
    
    public static void main(String args[]) {
    Database db = new Database();
    tasks = (ArrayList<Task>) db.pullTasks();
    users = (ArrayList<User>) db.pullUsers();
	//new CaretakerSchedule2();
    //	User user = db.pullSingleUser("tweed7");
    new loginGUI();
   }
}
