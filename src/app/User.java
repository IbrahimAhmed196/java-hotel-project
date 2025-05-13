package app;


import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private final int id;  // made final since ID shouldn't change
    private static int idCounter = 1;
    private String name;
    private String email;
    private String password;

    // Constructor with validation
    public User(String name, String email, String password)
            throws InvalidEmailException, InvalidPasswordException {
        this.id = idCounter++;
        setName(name);
        setEmail(email);  // using setter for validation
        setPassword(password);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    protected String getPassword() {
        return password;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (email == null ||   email.trim().isEmpty() || !email.contains(".") || !email.contains("@") )  {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
        this.email = email;
    }

    public void setPassword(String password) throws InvalidPasswordException {
        if (password == null || password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters");
        }
        this.password = password;
    }

    // Abstract methods
    public abstract boolean login(String enteredPassword);
    public abstract void logout();
}
 class Admin extends User {
    public Admin(String name, String email, String password)
            throws InvalidEmailException, InvalidPasswordException {
        super(name, email, password);
    }

    @Override
    public boolean login(String enteredPassword) {
        // In real system, might have stronger admin password requirements
        boolean success = enteredPassword != null && enteredPassword.equals(getPassword());
        System.out.println(getName() + " (Admin) " + (success ? "successfully" : "failed to") + " log in.");
        return success;
    }

    @Override
    public void logout() {
        System.out.println(getName() + " (Admin) logged out.");
    }


}
class Customer extends User {
    private final List<Booking> bookings;
    private final List<Payment> paymentMethods;

    public Customer(String name, String email, String password)
            throws InvalidEmailException, InvalidPasswordException {
                super(name, email, password);
                this.bookings = new ArrayList<>();
                this.paymentMethods = new ArrayList<>();
    }

    // Return defensive copies instead of direct references
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Payment> getPaymentMethods() {
        return new ArrayList<>(paymentMethods);
    }

    public void addBooking(Booking booking) {
        if (booking != null) {
            bookings.add(booking);
        }
    }

    public void addPaymentMethods(Payment method) {
        if (method != null) {
            paymentMethods.add(method);
        }
    }

    @Override
    public boolean login(String enteredPassword) {
        boolean success = enteredPassword != null && enteredPassword.equals(getPassword());
        System.out.println(getName() + " (Customer) " + (success ? "successfully" : "failed to") + " log in.");
        return success;
    }

    @Override
    public void logout() {
        System.out.println(getName() + " (Customer) logged out.");
    }
}
 class InvalidEmailException extends Exception {
    private static final String DEFAULT_MESSAGE = "Invalid Email provided";
    //creates an exception with default message


    public InvalidEmailException() {
        super(DEFAULT_MESSAGE);
    }
    public InvalidEmailException(String message) {

        super(message);
    }

}
 class InvalidPasswordException extends Exception{
    private static final String DEFAULT_MESSAGE = "Invalid password provided";

    //creates an exception with default message

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }


    public InvalidPasswordException(String message) {
        super(message);
    }

}