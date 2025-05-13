package app;




import java.util.Date;



public interface Payment {
    public boolean pay(double amount);
}
class PayPalPayment implements Payment {
    private String paypalEmail;
    public PayPalPayment(String paypalEmail) throws InvalidEmailException {
        if(paypalEmail == null || paypalEmail.trim().isEmpty()) throw new InvalidEmailException("There is no Email");
        else if(paypalEmail.contains("@.")||paypalEmail.contains(" ")||!paypalEmail.contains(".")||!paypalEmail.contains("@")){
            throw new InvalidEmailException();
        }
        this.paypalEmail = paypalEmail;
    }

    public boolean pay(double amount)throws IllegalArgumentException{
        if(amount < 0) throw new IllegalArgumentException("the amount must be a positive value");
        return true; // assume the user have enough balance in their bank account
    }
}
class CreditCardPayment implements Payment{
    private String cardNumber;
    private Date expiryDate;
    /*
    - For the Date class the constructor is Date(int year, int month, int day);
    - the year parameter is a value that will be added to 1900 
    (for example to get year 2027 the input should be 127)
    -The months start from 0 so January = 0 and December = 11
    - Since the expiry Date on the card only contains the month and year put the day = 1
    */
    private String cardHolderName;
    private String CVV;
    public CreditCardPayment(String cardNumber,Date expiryDate,String cardHolderName,String CVV)throws IllegalArgumentException,ExpiredCardException{
        Date currentDate = new Date();
        if(cardNumber.length() != 16)throw new IllegalArgumentException("the card number is 16 digits");
        for(int i=0;i<cardNumber.length();i++){
            if(!Character.isDigit(cardNumber.charAt(i)))throw new IllegalArgumentException("the card number must only contain digits");
        }
        if(expiryDate.compareTo(currentDate) <= 0) throw new ExpiredCardException();
        if(cardHolderName == null || cardHolderName.trim().isEmpty()) throw new IllegalArgumentException("There is no name");
        if(CVV.length() != 3)throw new IllegalArgumentException("the CVV is 3 digits");
        for(int i=0;i<CVV.length();i++){
            if(!Character.isDigit(CVV.charAt(i)))throw new IllegalArgumentException("the CVV must only contain digits");
        }
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
        this.CVV = CVV;
    }
   
    public boolean pay(double amount)throws IllegalArgumentException{
        if(amount < 0) throw new IllegalArgumentException("the amount must be a positive value");
        return true; // assume the user have enough balance in their bank account
    }
}
