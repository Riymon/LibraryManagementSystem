public class BookRequest2{
    private String userID;
    private String bookTitle;
    private String dateBorrowed;

    public BookRequest2(String userID, String bookTitle, String dateBorrowed) {
        this.userID = userID;
        this.bookTitle = bookTitle;
        this.dateBorrowed = dateBorrowed;
    }

    public String getUserID() {
        return userID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public String toString() {
        return "UserID: " + userID + ", Book: " + bookTitle + ", Date: " + dateBorrowed;
    }
}
