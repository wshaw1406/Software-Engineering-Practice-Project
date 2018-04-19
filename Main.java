package software_eng;

import java.util.*;

/**
 *
 * @author Will
 */
public class Main {
    
    public static void main(String args[]) {
		//new taskInformation();
    	Database db = new Database();
    	User user = db.pullSingleUser("tweed7");
    	System.out.println(user.getGender());
   }
}