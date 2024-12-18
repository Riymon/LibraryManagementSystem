public class BookManager {
    private String[] books; // Array to store book titles
    private int bookCount;  // Tracks the current number of books

    public BookManager() {
        books = new String[100]; // Initialize with a fixed size (can be resized if needed)
        bookCount = 0;
    }

    // Check if a book exists in the inventory
    public boolean bookExists(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    // Add a new book to the inventory
    public void addBook(String title) {
        if (bookExists(title)) {
            System.out.println("Book already exists in the inventory.");
            return;
        }

        // Check if the array is full and resize if needed
        if (bookCount == books.length) {
            resizeArray();
        }

        books[bookCount++] = title; // Add book and increment count
    }

    // Remove a book from the inventory
    public void removeBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].equalsIgnoreCase(title)) {
                // Shift all subsequent books to fill the gap
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--bookCount] = null; // Decrease count and clear the last entry
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found in the inventory.");
    }

    // Helper method to resize the array when full
    private void resizeArray() {
        String[] newBooks = new String[books.length * 2]; // Double the size
        System.arraycopy(books, 0, newBooks, 0, books.length);
        books = newBooks;
    }

    // Display all books in the inventory
    public void displayBooks() {
        if (bookCount == 0) {
            System.out.println("No books available in the inventory.");
            return;
        }

        System.out.println("Books in Inventory:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("- " + books[i]);
        }
    }
    public void initializeBooks(){
        addBook("Noli");
        addBook("Test");
    }
}
