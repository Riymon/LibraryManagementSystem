import java.util.Hashtable;

public class UserManager {
    private Hashtable<String, User> users;

    public UserManager() {
        users = new Hashtable<>();
    }

    public void addUser(String userID, String name) {
        if (users.containsKey(userID)) {
            System.out.println("User already exists.");
        } else {
            users.put(userID, new User(userID, name));
            System.out.println("User added successfully.");
        }
    }

    public User getUser(String userID) {
        return users.get(userID);
    }

    public boolean userExists(String userID) {
        return users.containsKey(userID);
    }
    public String getUsernameByID(String userID) {
        if (users.containsKey(userID)) {
            return users.get(userID).getName(); // Return the name of the user
        } else {
            return null; // Return null if the userID does not exist
        }
    }
    public String toString(){
        return users.toString();
    }
    
}
  
    


