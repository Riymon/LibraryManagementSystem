import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class LibrarySystem {
    private Queue<BookRequest2> borrowRequest;
    private Queue<BookRequest2> bookbarrowed;
    private UserManager userManager;
    private BookManager bookManager;
    private String returnedUsername;
    
    public LibrarySystem(){
        borrowRequest = new LinkedList<>();
        userManager = new UserManager();
        bookManager = new BookManager();
        bookbarrowed = new LinkedList<>();
        initializeBooks();

    }
    public void initializeBooks(){
        bookManager.addBook("Noli");
        bookManager.addBook("Test");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySystem().showMenu());
    }

    private void showMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10)); // 7 rows, 1 column, 10px spacing

        // Buttons for each action
        JButton registerButton = new JButton("Register");
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");
        JButton ApproveReqButton = new JButton("Approve Request");
        JButton addBookButton = new JButton("Add Book");
        JButton removeBookButton = new JButton("Remove Book");
        JButton searchBookButton = new JButton("Search Book");
        JButton exitButton = new JButton("Exit");
        

        // Add action listeners to buttons
        registerButton.addActionListener(e -> registerUser());
        ApproveReqButton.addActionListener(e -> approveReq());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());
        addBookButton.addActionListener(e -> addBook());
        removeBookButton.addActionListener(e -> removeBook());
        searchBookButton.addActionListener(e -> searchBook());
        exitButton.addActionListener(e -> exitSystem());

        // Add buttons to the panel
        panel.add(registerButton);
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(ApproveReqButton);
        panel.add(addBookButton);
        panel.add(removeBookButton);
        panel.add(searchBookButton);
        panel.add(exitButton);

        // Display the panel inside a JOptionPane
        JOptionPane.showMessageDialog(null, panel, "Library System Menu", JOptionPane.PLAIN_MESSAGE);
    }
    private void approveReq() {
        // Check if there are any pending requests
        if (borrowRequest.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No borrow requests pending approval.");
            return;
        }
    
        // Get the next request in the queue
        BookRequest2 request = borrowRequest.peek(); // Peek at the front of the queue
    
        if (request != null) {
            // Display request details
            JOptionPane.showMessageDialog(
            null,
            "Request by user: " + getReturendUsern() + ", " + request.toString() + " processed for review.",
            "Approval",
            JOptionPane.INFORMATION_MESSAGE
        );

    
            // Panel for approval decision
            String[] options = {"Approve", "Reject"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Do you want to approve this request?",
                    "Approval Request",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
    
            // Process decision
            if (choice == JOptionPane.YES_OPTION) {
                bookbarrowed.add(borrowRequest.poll()); // Remove the approved request from the queue

                JOptionPane.showMessageDialog(null, "Request approved!");
            } else if (choice == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Request rejected.");
            }
        }
    }
    public String getReturendUsern(){
        return returnedUsername;
    }
    public void setRetunedUsern(String returnedUsername) {
        this.returnedUsername = returnedUsername;
    }
    
    private void registerUser() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField();
        JLabel usernameLabel = new JLabel("Name:");
        JTextField usernameField = new JTextField();

        panel.add(userIDLabel);
        panel.add(userIDField);
        panel.add(usernameLabel);
        panel.add(usernameField);

        int result = JOptionPane.showConfirmDialog(
                null, panel, "Register User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String userID = userIDField.getText().trim();
            String username = usernameField.getText().trim();

            if (!userManager.userExists(userID)) {
                userManager.addUser(userID, username);
                JOptionPane.showMessageDialog(null, "User registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "User ID already exists.");
            }
        }

    }

    private void borrowBook() {
        //String userID;

           JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel userLabel = new JLabel("User ID:");
        JTextField userField = new JTextField();
        JLabel bookLabel = new JLabel("Book Title:");
        JTextField bookField = new JTextField();
        JLabel dateLabel = new JLabel("Date Borrowed (YYYY-MM-DD):");
        JTextField dateField = new JTextField();

        panel.add(userLabel);
        panel.add(userField);
        panel.add(bookLabel);
        panel.add(bookField);
        panel.add(dateLabel);
        panel.add(dateField);


        int result = JOptionPane.showConfirmDialog(
                null, panel, "Borrow Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String userID = userField.getText().trim();
            String bookTitle = bookField.getText().trim();
            String dateBorrowed = dateField.getText().trim();

            if (userManager.userExists(userID) && bookManager.bookExists(bookTitle)) {
                borrowRequest.add(new BookRequest2(userID, bookTitle, dateBorrowed));
                setRetunedUsern(userManager.getUsernameByID(userID));
                JOptionPane.showMessageDialog(null, "Book borrow procced to approval!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid user ID or book title.");
            }
        }
    }

    private void returnBook() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel userLabel = new JLabel("User ID:");
        JTextField userField = new JTextField();
        JLabel bookLabel = new JLabel("Book Title:");
        JTextField bookField = new JTextField();

        panel.add(userLabel);
        panel.add(userField);
        panel.add(bookLabel);
        panel.add(bookField);

        int result = JOptionPane.showConfirmDialog(
                null, panel, "Return Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String userID = userField.getText().trim();
            String bookTitle = bookField.getText().trim();

            if (bookbarrowed.removeIf(request -> request.getUserID().equals(userID) && request.getBookTitle().equals(bookTitle))) {
                JOptionPane.showMessageDialog(null, "Book returned successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No matching borrow request found.");
                boolean pendingRequest = bookbarrowed.stream().anyMatch(
                request -> request.getUserID().equals(userID) && request.getBookTitle().equals(bookTitle));

                if (!pendingRequest) 
                    JOptionPane.showMessageDialog(null, "Borrow request still pending approval.");
            }


        }
    }

    private void addBook() {
        String bookTitle = JOptionPane.showInputDialog(null, "Enter book title:", "Add Book", JOptionPane.PLAIN_MESSAGE);
        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            bookManager.addBook(bookTitle.trim());
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Book title cannot be empty.");
        }
    }

    private void removeBook() {
        String bookTitle = JOptionPane.showInputDialog(null, "Enter book title to remove:", "Remove Book", JOptionPane.PLAIN_MESSAGE);
        if (bookTitle != null && bookManager.bookExists(bookTitle.trim())) {
            bookManager.removeBook(bookTitle.trim());
            JOptionPane.showMessageDialog(null, "Book removed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Book not found.");
        }

    }

    private void searchBook() {
        String bookTitle = JOptionPane.showInputDialog(null, "Enter book title to search:", "Search Book", JOptionPane.PLAIN_MESSAGE);
        if (bookTitle != null && bookManager.bookExists(bookTitle.trim())) {
            JOptionPane.showMessageDialog(null, "Book exists.");
        } else {
            JOptionPane.showMessageDialog(null, "Book does not exist.");
        }
    }

    private void exitSystem() {
        JOptionPane.showMessageDialog(null, "Exiting the system. Goodbye!");
        System.exit(0);
    }
}
