/**
 * User class
 * @author Daniel Parr
 */

import java.util.ArrayList;


public class User {
	private int userID;
	private String username;
	private String passwordHash;
	private String firstName;
	private String surname;
	private int dateOfBirth;
	private String gender;
	private String accountType;
	
	public User(int userID, String username, String passwordHash, String firstName, String surname,
			int dateOfBirth, String gender, String accountType)
	{
		this.userID = userID;
		this.username = username;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.accountType = accountType;
	}
	
	/*getters*/
	
	/**
	 * get the user ID
	 * @return userID
	 */
	public int getUserID()
	{
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
	 * gets date of birth
	 * @return dateOfBirth
	 */
	public int getDateOfBirth()
	{
		return dateOfBirth;
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
	public void setUserID(int userID)
	{
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
	 * sets the dateOfBirth of a user
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(int dateOfBirth)
	{
		this.dateOfBirth=dateOfBirth;
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
	
	public void displayTasks(ArrayList<User> users)
    {
        for(User user : users)
        {
            System.out.println("UserID: " + user.getUserID());
        }
    }
}
