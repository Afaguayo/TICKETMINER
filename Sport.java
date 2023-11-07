/**
 * The `Sport` class represents sporting events and extends the `Event` class.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class Sport extends Event {

    /**
     * Default constructor for the `Sport` class.
     */
    public Sport() {}

    /**
     * Parameterized constructor for the `Sport` class.
     *
     * @param eventID              The unique identifier for the sport event.
     * @param eventType            The type of the event.
     * @param name                 The name of the sport event.
     * @param date                 The date of the event.
     * @param time                 The time of the event.
     * @param vipPrice             The price of VIP tickets.
     * @param goldPrice            The price of Gold tickets.
     * @param silverPrice          The price of Silver tickets.
     * @param bronzePrice          The price of Bronze tickets.
     * @param generalAdmissionPrice The price of General Admission tickets.
     * @param venue                The venue where the event takes place.
     */
    public Sport(int eventID, String eventType, String name, String date, String time, Double vipPrice, Double goldPrice, Double silverPrice,
                 Double bronzePrice, Double generalAdmissionPrice, Venue venue) {
        super(eventID, eventType, name, date, time, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice, venue);
    }
}
