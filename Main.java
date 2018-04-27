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
    	user = new User();

    	//new CaretakerSchedule2();
    	//new loginGUI();
    	//new addUserGUI();
    	new taskInformation();
   }
}
