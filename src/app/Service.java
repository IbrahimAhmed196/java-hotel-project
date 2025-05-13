package app;




class InvalidServiceException extends Exception {
    public InvalidServiceException(String message) {
        super(message);
    }
}
public abstract class Service {
    protected int serviceId;
    protected String name;
    protected String description;
    protected double price;

    public Service(int serviceId, String name, String description, double price) throws InvalidServiceException {
        if (price < 0) {
            throw new InvalidServiceException("Price cannot be negative for service: " + name);
        }
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public abstract double useService();

    public void displayDetails() {
        System.out.println("[" + name + "] " + description + " - $" + price);
    }

}
class RoomService extends Service {
     String mealType;

    public RoomService(int serviceId, String name, String description, double price, String mealType)
            throws InvalidServiceException {
        super(serviceId, name, description, price);
        if (mealType == null || mealType.isEmpty()) {
            throw new InvalidServiceException("Meal type must not be empty.");
        }
        this.mealType = mealType;
    }

    @Override
    public double useService() {
        System.out.println("Room Service: Serving " + mealType);
        return price;
    }
}
class LaundryService extends Service {
     int clothesCount;

    public LaundryService(int serviceId, String name, String description, double price, int clothesCount)
            throws InvalidServiceException {
        super(serviceId, name, description, price);
        if (clothesCount < 0) {
            throw new InvalidServiceException("Clothes count must be non-negative.");
        }
        this.clothesCount = clothesCount;
    }

    @Override
    public double useService() {
        System.out.println("Laundry Service: Washing " + clothesCount + " clothes");
        return price + (clothesCount * 1.5);
    }
}
class SpaService extends Service {
     String spaPackage;

    public SpaService(int serviceId, String name, String description, double price, String spaPackage)
            throws InvalidServiceException {
        super(serviceId, name, description, price);
        if (spaPackage == null || spaPackage.isEmpty()) {
            throw new InvalidServiceException("Spa package must not be empty.");
        }
        this.spaPackage = spaPackage;
    }

    @Override
    public double useService() {
        System.out.println("Spa Service: Providing " + spaPackage + " package");
        return price;
    }
}