import java.util.*;


/**
 *
 * @author Will
 */
public class Main {
	
	public static ArrayList<Task> tasks = new ArrayList<>();
	public static ArrayList<User> users = new ArrayList<>();
	public static User user;
	public static Task task;
    public static void main(String args[]) {
    	user = new User();
    	new loginGUI();
    
   }
}
