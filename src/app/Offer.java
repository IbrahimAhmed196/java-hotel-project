package app;




import java.util.Date;
import java.util.HashMap;



class ExpiredCardException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "The card has expired";
    public ExpiredCardException(){
        super(DEFAULT_MESSAGE);
    }
    public ExpiredCardException(String message){
        super(message);
    }
}
class InvalidCodeException extends RuntimeException {
      private static final String DEFAULT_MESSAGE = "Invalid Code provided";
    //creates an exception with default message

    public InvalidCodeException() {
        super(DEFAULT_MESSAGE);
    }
    public InvalidCodeException(String message) {

        super(message);
    }
}

public abstract class Offer {
     protected boolean isActive;
    protected double discountRate;
    public double getDiscountRate(){
        return discountRate;
    }
    public double applyOffer(double price)throws IllegalStateException,IllegalArgumentException{
        if(!isActive) throw new IllegalStateException("The Offer cannot be applied");
        if(price < 0) throw new IllegalArgumentException("the price must be a positive value");
        return price-(price*discountRate);
    }
    public abstract boolean checkOffer();
}
class SeasonalOffer extends Offer {
    private Date startDate;
    private Date endDate;
    public SeasonalOffer(double discountRate,Date startDate,Date endDate)throws IllegalArgumentException{
        if(discountRate < 0 || discountRate > 1) throw new IllegalArgumentException("Discount rate should be between 0 and 1");
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = this.checkOffer();
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    @Override
    public boolean checkOffer(){
        Date currentDate = new Date();
        if(currentDate.compareTo(startDate) < 0 || currentDate.compareTo(endDate) > 0) return false;
        else return true;
    }
    
}
class SpecialCodeOffer extends Offer{
    private String promoCode;
    private static HashMap<String, Double> availableCodes = new HashMap<>();
    public SpecialCodeOffer(String promoCode)throws IllegalArgumentException,InvalidCodeException{
        if(promoCode == null || promoCode.trim().isEmpty()) throw new IllegalArgumentException("There is no code");
        if(availableCodes.get(promoCode) == null) throw new InvalidCodeException();
        this.promoCode = promoCode;
        this.discountRate = availableCodes.get(promoCode);
        this.isActive = true;
    }
    public String getPromoCode(){
        return promoCode;
    }
    @Override
    public boolean checkOffer(){
        return isActive;
    }
    static public void addCode(String code,double discount)throws IllegalArgumentException,IllegalStateException{
        if(discount < 0 || discount > 1) throw new IllegalArgumentException("Discount rate should be between 0 and 1");
        if(code == null || code.trim().isEmpty()) throw new IllegalArgumentException("There is no code");
        if(availableCodes.get(code) != null) throw new IllegalStateException ("Code already exist");
        availableCodes.put(code, discount);
    }
    static public void removeCode(String code)throws IllegalArgumentException{
        if(code == null || code.trim().isEmpty()) throw new IllegalArgumentException("There is no code");
        if(availableCodes.get(code) == null) throw new IllegalStateException ("Code already doesn't exist");
        availableCodes.remove(code);
    }
    /*
    The two methods above are for the admin to insert and remove promocodes
    */
}