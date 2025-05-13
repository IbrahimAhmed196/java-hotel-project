package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking {
     private int bookingId;
    private Customer customer;
    private Room room;
    private List<Service> services;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private BookingStatus status;
    private Offer offerApplied;

    public Booking(int bookingId, Customer customer, Room room, 
                  Date checkInDate, Date checkOutDate) throws InvalidBookingException {
        if (customer == null) {
            throw new InvalidBookingException("Customer cannot be null");
        }
        if (room == null) {
            throw new InvalidBookingException("Room cannot be null");
        }
        if (checkInDate == null || checkOutDate == null) {
            throw new InvalidBookingException("Dates cannot be null");
        }
        if (checkInDate.after(checkOutDate)) {
            throw new InvalidBookingException("Check-in date must be before check-out date");
        }

        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = new Date(checkInDate.getTime());
        this.checkOutDate = new Date(checkOutDate.getTime());
        this.services = new ArrayList<>();
        this.status = BookingStatus.CONFIRMED;
        this.totalPrice = calculatePrice();
    }

    public double calculatePrice() {
        try {
            double basePrice = room.getPrice() * getNumberOfNights();
            double servicesPrice = services.stream().mapToDouble(Service::useService).sum();
            
            double finalPrice = basePrice + servicesPrice;
            
            if (offerApplied != null) {
                finalPrice = offerApplied.applyOffer(finalPrice);
            }
            
            this.totalPrice = finalPrice;
            return finalPrice;
        } catch (Exception e) {
            throw new BookingOperationException("Error calculating booking price", e);
        }
    }

    public void addService(Service service) throws InvalidServiceException {
        if (service == null) {
            throw new InvalidServiceException("Service cannot be null");
        }
        if (status != BookingStatus.CONFIRMED) {
            throw new InvalidServiceException("Cannot add services to a cancelled or completed booking");
        }
        
        try {
            services.add(service);
            calculatePrice(); // Recalculate total price
        } catch (Exception e) {
            throw new BookingOperationException("Error adding service to booking", e);
        }
    }

    public void cancel() throws BookingOperationException {
        if (status == BookingStatus.CANCELLED) {
            throw new BookingOperationException("Booking is already cancelled");
        }
        if (new Date().after(checkInDate)) {
            throw new BookingOperationException("Cannot cancel booking after check-in date");
        }
        
        try {
            this.status = BookingStatus.CANCELLED;
            // Additional cancellation logic could go here
        } catch (Exception e) {
            throw new BookingOperationException("Error cancelling booking", e);
        }
    }

    // Helper method
    private long getNumberOfNights() {
        long diff = checkOutDate.getTime() - checkInDate.getTime();
        return diff / (1000 * 60 * 60 * 24); // Convert milliseconds to days
    }

    // Custom exceptions
    public static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    public static class BookingOperationException extends RuntimeException {
        public BookingOperationException(String message) {
            super(message);
        }
        public BookingOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidServiceException extends Exception {
        public InvalidServiceException(String message) {
            super(message);
        }
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public List<Service> getServices() {
        return new ArrayList<>(services); // Defensive copy
    }

    public Date getCheckInDate() {
        return new Date(checkInDate.getTime()); // Defensive copy
    }

    public Date getCheckOutDate() {
        return new Date(checkOutDate.getTime()); // Defensive copy
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Offer getOfferApplied() {
        return offerApplied;
    }

    public void setOfferApplied(Offer offer) throws InvalidOfferException {
        if (offer == null) {
            throw new InvalidOfferException("Offer cannot be null");
        }
        if (status != BookingStatus.CONFIRMED) {
            throw new InvalidOfferException("Cannot apply offer to a cancelled or completed booking");
        }
        this.offerApplied = offer;
        calculatePrice(); // Recalculate with new offer
    }
}

// Supporting enums and interfaces
enum BookingStatus {
    CONFIRMED, CANCELLED, COMPLETED
}
