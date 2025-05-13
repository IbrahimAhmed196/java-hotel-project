package app;



public abstract class Notification {
    private static  int  Notification_ID;
        private String message;
        
        abstract void  send();
        
        //getters
        public int get_Notification_ID(){
            return Notification_ID;
        }
        public String get_message(){
            return message;
        } 
      
        //setters
        public void set_Notification_ID(int Notification_ID){
            if (Notification_ID < 0) {
                throw new IllegalArgumentException("Notification ID cannot be negative.");
            }
            this.Notification_ID=Notification_ID;
        }
        public void set_message(String message){
            if (message == null || message.trim().isEmpty()) {
                throw new IllegalArgumentException("Message cannot be null or empty.");
            }
            this.message =message; 
        }
    }
class Email_Notification extends  Notification{
    //Constructor
    public Email_Notification(){
        try{
        set_Notification_ID(get_Notification_ID()+1);
        set_message("Thank you for booking whith us. " +'\n'+"Your booking details are:" +'\n');
        //get Email from Customer
        } catch (IllegalArgumentException e){
            System.out.println("Error in Email_Notification constructor: " + e.getMessage());
        }
    }
    @Override
    public void send(){
        // will use graphical inetrfernce in the future
        System.out.println(get_message());
    }
}
