/**
 * User class
 * @author Daniel Parr
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class User {
	private int userID;
	private String username;
	private String passwordHash;
	private String firstName;
	private String surname;
	private String gender;
	private String accountType;
	
	public User(int userID, String username, String passwordHash, String firstName, String surname,
			 String gender, String accountType)
	{
		this.userID = userID;
		this.username = username;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.surname = surname;
		this.gender = gender;
		this.accountType = accountType;
	}
	
	public User(){
		
	}
	
	 public static int generateUserID() {
	    	Database db = new Database();
	    	db.connect();
	    	List<User> users = db.pullUsers();
	    	ArrayList<Integer> userIDs = new ArrayList<Integer>();
	    	for(User user: users){
	    		userIDs.add(user.getUserID());
	    		}
	    	return Collections.max(userIDs)+1;
	    	}
	
	/*getters*/	
	/**
	 * gets userID
	 * @return userID
	 */
	public int getUserID(){
		return userID;
	}
	/**
	 * gets username
	 * @return username
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * gets password hash
	 * @return passwordHash
	 */
	public String getPasswordHash()
	{
		return passwordHash;
	}
	
	/**
	 * gets firstname
	 * @return firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * gets surname
	 * @return surname
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * gets gender
	 * @return gender
	 */
	public String getGender()
	{
		return gender;
	}
	
	/**
	 * gets account type
	 * @return accountType
	 */
	public String getAccountType()
	{
		return accountType;
	}
	
	/*setters*/
	/**
	 * sets the userID
	 * @param userID
	 */
	public void setUserID(int userID){
		this.userID=userID;
	}
	
	/**
	 * sets the username
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	/**
	 * sets the passwordHash
	 * @param passwordHash 
	 */
	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash=passwordHash;
	}
	
	/**
	 * sets first name
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	
	/**
	 * sets the surname
	 * @param surname
	 */
	public void setSurname(String surname)
	{
		this.surname=surname;
	}
	
	/**
	 * sets the gender of a user
	 * @param gender
	 */
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	
	/**
	 * sets the account type
	 * @param accountType
	 */
	public void setAccountType(String accountType)
	{
		this.accountType=accountType;
	}
	
	/**
	 * removes a user from the list of users
	 * @param user
	 */
	public void removeUser(User user)
	{
		user.removeUser(user);
	}
	
	public void displayUsers(ArrayList<User> users)
    {
        for(User user : users)
        {
            System.out.println("Username: " + user.getUsername());
        }
    }
}
