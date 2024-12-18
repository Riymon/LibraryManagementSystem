public class User {
    private String userID;
    private String name;
    private boolean canBorrow; // For account status

    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;
        this.canBorrow = true; // Default to true
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public boolean canBorrow() {
        return canBorrow;
    }

    public void setCanBorrow(boolean status) {
        this.canBorrow = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + getUserID() + '\'' +
                ", name='" + name + '\'' +
                ", canBorrow=" + canBorrow +
                '}';
    }
}

