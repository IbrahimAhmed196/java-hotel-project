package app;




import java.util.Date;
    class InvalidReviewException extends Exception {
    public InvalidReviewException(String message) {
        super(message);
    }
}
public class Review {
   private int reviewId;
    private Customer customer;
    private int rating; // must be between 1 and 5
    private String comment;
    private Date date;

    public Review(int reviewId, Customer customer, int rating, String comment, Date date)
            throws InvalidReviewException {
        if (rating < 1 || rating > 5) {
            throw new InvalidReviewException("Rating must be between 1 and 5.");
        }
        if (comment == null || comment.trim().isEmpty()) {
            throw new InvalidReviewException("Comment cannot be empty.");
        }
        this.reviewId = reviewId;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
public int getReviewId() {
    return reviewId;
}

public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
}

public Customer getCustomer() {
    return customer;
}

public void setCustomer(Customer customer) {
    this.customer = customer;
}

public int getRating() {
    return rating;
}

public void setRating(int rating) throws InvalidReviewException {
    if (rating < 1 || rating > 5) {
        throw new InvalidReviewException("Rating must be between 1 and 5.");
    }
    this.rating = rating;
}

public String getComment() {
    return comment;
}

public void setComment(String comment) throws InvalidReviewException {
    if (comment == null || comment.trim().isEmpty()) {
        throw new InvalidReviewException("Comment cannot be empty.");
    }
    this.comment = comment;
}

public Date getDate() {
    return date;
}

public void setDate(Date date) {
    this.date = date;
}
  
    public void displayReview() {
        System.out.println("Review ID: " + reviewId);
        
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comment: " + comment);
        System.out.println("Date: " + date.toString());
    }
}