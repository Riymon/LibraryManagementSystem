public class BookRequest1 {
    private String requesterName;
    private String bookTitle;
    private String dateOfRequest;

    public BookRequest1(String requesterName, String bookTitle, String dateOfRequest) {
        this.requesterName = requesterName;
        this.bookTitle = bookTitle;
        this.dateOfRequest = dateOfRequest;
    }

    public void display() {
        System.out.println("Requester: " + requesterName + ", Book Title: " + bookTitle + ", Date of Request: " + dateOfRequest);
    }
    public String getRequestname(){
        return requesterName;
    }
    public String getBookTItle(){
        return bookTitle;
    }
    public String getDateofRequest(){
        return dateOfRequest;
    }
    
    @Override
    public String toString() {
        return "BookRequest{" +
                "Requester Name: '" + getRequestname() + '\n' +
                "Book Title='" + getBookTItle() + '\n' +
                "Date of Rquest ='" + getDateofRequest() + '\n' +
                '}';
    }
}
