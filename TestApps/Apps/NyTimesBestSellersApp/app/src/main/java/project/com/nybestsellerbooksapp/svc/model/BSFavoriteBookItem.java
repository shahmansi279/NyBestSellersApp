package project.com.nybestsellerbooksapp.svc.model;

/**
 * Created by Mansi on 1/18/18.
 */

public class BSFavoriteBookItem {

    private String bookIsbn;
    private String bookTitle;
    private String bookReviewLink;


    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookReviewLink() {
        return bookReviewLink;
    }

    public void setBookReviewLink(String bookReviewLink) {
        this.bookReviewLink = bookReviewLink;
    }



}
