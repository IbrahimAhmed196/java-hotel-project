package app;




public class Room {
    private final int roomNumber;
    private String type; // "Single", "Double", "Suite"
    private double price;
    private boolean isAvailable;
    private final int capacity;

    public Room(int roomNumber, String type, double price, boolean isAvailable, int capacity) {
        if (roomNumber <= 0) throw new IllegalArgumentException("Room number must be positive");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (type == null || type.trim().isEmpty()) throw new IllegalArgumentException("Room type cannot be empty");
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");

        this.roomNumber = roomNumber;
        this.type = type.trim();
        this.price = price;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
    }

    // New methods for room booking management
    public void book() {
        if (!isAvailable) {
            throw new IllegalStateException("Room #" + roomNumber + " is already booked");
        }
        this.isAvailable = false;
    }

    public void checkout() {
        if (isAvailable) {
            throw new IllegalStateException("Room #" + roomNumber + " is not currently booked");
        }
        this.isAvailable = true;
    }

    // Getters
    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
    public int getCapacity() { return capacity; }

    // Setters
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    
    public int compareTo(Room other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return String.format("Room #%d - %s - $%.2f - %s - Capacity: %d",
                roomNumber,
                type,
                price,
                isAvailable ? "Available" : "Occupied",
                capacity);
    }
}
 class SingleRoom extends Room {
    private static final int CAPACITY = 1;

    public SingleRoom(int roomNumber, double price, boolean isAvailable) {
        super(roomNumber, "Single", price, isAvailable, CAPACITY);
    }
}
class DoubleRoom extends Room {
    private static final int CAPACITY = 2;

    public DoubleRoom(int roomNumber, double price, boolean isAvailable) {
        super(roomNumber, "Double", price, isAvailable, CAPACITY);
    }
}class SuiteRoom extends Room {
    private static final int CAPACITY = 4;

    public SuiteRoom(int roomNumber, double price, boolean isAvailable) {
        super(roomNumber, "Suite", price, isAvailable, CAPACITY);
    }
}