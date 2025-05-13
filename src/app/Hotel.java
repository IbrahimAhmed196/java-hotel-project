package app;




import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dell
 */
public class Hotel {
     private List<Room> rooms;
    private List<Offer> offers;
    private List<Review> reviews;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Room-related methods
    public List<Room> searchAvailableRooms() throws HotelOperationException {
        try {
            List<Room> availableRooms = new ArrayList<>();
            for (Room room : rooms) {
                if (room.isAvailable()) {
                    availableRooms.add(room);
                }
            }
            return availableRooms;
        } catch (Exception e) {
            throw new HotelOperationException("Error searching for available rooms", e);
        }
    }

    public void addRoom(Room room) throws InvalidRoomException, HotelOperationException {
        if (room == null) {
            throw new InvalidRoomException("Room cannot be null");
        }
        try {
            if (rooms.contains(room)) {
                throw new InvalidRoomException("Room already exists in the hotel");
            }
            rooms.add(room);
        } catch (Exception e) {
            throw new HotelOperationException("Error adding room", e);
        }
    }

    public void removeRoom(Room room) throws RoomNotFoundException, HotelOperationException {
        if (room == null) {
            throw new RoomNotFoundException("Room cannot be null");
        }
        try {
            if (!rooms.remove(room)) {
                throw new RoomNotFoundException("Room not found in the hotel");
            }
        } catch (Exception e) {
            throw new HotelOperationException("Error removing room", e);
        }
    }

    // Offer-related methods
    public void addOffer(Offer offer) throws InvalidOfferException, HotelOperationException {
        if (offer == null) {
            throw new InvalidOfferException("Offer cannot be null");
        }
        try {
            if (offers.contains(offer)) {
                throw new InvalidOfferException("Offer already exists in the hotel");
            }
            offers.add(offer);
        } catch (Exception e) {
            throw new HotelOperationException("Error adding offer", e);
        }
    }

    public void removeOffer(Offer offer) throws OfferNotFoundException, HotelOperationException {
        if (offer == null) {
            throw new OfferNotFoundException("Offer cannot be null");
        }
        try {
            if (!offers.remove(offer)) {
                throw new OfferNotFoundException("Offer not found in the hotel");
            }
        } catch (Exception e) {
            throw new HotelOperationException("Error removing offer", e);
        }
    }

    // Review-related methods
    public void addReview(Review review) throws InvalidReviewException, HotelOperationException {
        if (review == null) {
            throw new InvalidReviewException("Review cannot be null");
        }
        try {
            reviews.add(review);
        } catch (Exception e) {
            throw new HotelOperationException("Error adding review", e);
        }
    }

    // Custom exceptions
    public static class HotelOperationException extends Exception {
        public HotelOperationException(String message) {
            super(message);
        }
        public HotelOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidRoomException extends Exception {
        public InvalidRoomException(String message) {
            super(message);
        }
    }

    public static class RoomNotFoundException extends Exception {
        public RoomNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidOfferException extends Exception {
        public InvalidOfferException(String message) {
            super(message);
        }
    }

    public static class OfferNotFoundException extends Exception {
        public OfferNotFoundException(String message) {
            super(message);
        }
    }

    // Getters
    public List<Room> getRooms() {
        return new ArrayList<>(rooms); // Return defensive copy
    }

    public List<Offer> getOffers() {
        return new ArrayList<>(offers); // Return defensive copy
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews); // Return defensive copy
    }
}

