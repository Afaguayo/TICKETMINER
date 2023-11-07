/**
 * The `Festival` class represents a festival event and extends the `Event` class.
 * It provides specific properties and methods for managing festival events.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class Festival extends Event {

    /**
     * Default constructor for the `Festival` class.
     */
    public Festival() {}

    /**
     * Parameterized constructor for the `Festival` class.
     *
     * @param eventID              The unique identifier of the festival event.
     * @param eventType            The type of the event (e.g., "Festival").
     * @param name                 The name of the festival.
     * @param date                 The date of the festival.
     * @param time                 The time of the festival.
     * @param vipPrice             The price of VIP tickets for the festival.
     * @param goldPrice            The price of Gold tickets for the festival.
     * @param silverPrice          The price of Silver tickets for the festival.
     * @param bronzePrice          The price of Bronze tickets for the festival.
     * @param generalAdmissionPrice The price of General Admission tickets for the festival.
     * @param venue                The venue where the festival takes place.
     */
    public Festival(int eventID, String eventType, String name, String date, String time, Double vipPrice, Double goldPrice,
                    Double silverPrice, Double bronzePrice, Double generalAdmissionPrice, Venue venue) {
        super(eventID, eventType, name, date, time, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice, venue);
    }
}
