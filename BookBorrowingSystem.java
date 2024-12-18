import javax.swing.*;

public class BookBorrowingSystem {
    private UserManager userManager;

    public BookBorrowingSystem() {
        userManager = new UserManager();

    }

    public void addUser() {
        String userID = JOptionPane.showInputDialog("Enter User ID:");
        String name = JOptionPane.showInputDialog("Enter User Name:");
        userManager.addUser(userID, name);
    }

    public void borrowBook() {
        String userID = JOptionPane.showInputDialog("Enter User ID:");

        if (!userManager.userExists(userID)) {
            JOptionPane.showMessageDialog(null, "User not found. Cannot borrow a book.");
            return;
        }

        User user = userManager.getUser(userID);
        if (!user.canBorrow()) {
            JOptionPane.showMessageDialog(null, "User is not allowed to borrow books.");
            return;
        }

        // Additional book borrowing logic can go here
        JOptionPane.showMessageDialog(null, "Book borrowed successfully by " + user.getName());
    }

    public static void main(String[] args) {
        BookBorrowingSystem system = new BookBorrowingSystem();

        system.addUser();
        system.borrowBook();
    }
}

