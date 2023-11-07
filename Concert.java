 /**
 * This is the Concert class and it extends Event class
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class Concert extends Event {

    /**
     * Taken from Caleb
     * Constructs a Concert object with the specified event details.
     *
     * @param eventID       The unique identifier for the concert event.
     * @param type          The type of the event.
     * @param name          The name or title of the concert event.
     * @param date          The date of the concert event.
     * @param time          The time of the concert event.
     * @param ticket        The ticket details for the concert event.
     * @param venue         The venue information for the concert event.
     * @param fireworksPlanned Whether fireworks are planned for the concert.
     * @param fireworksCost The cost associated with fireworks for the concert event.
     * @param eventCost     The cost of the concert event.
     */
    public Concert(int eventID, String eventType, String name, String date, String time, Double vipPrice, Double goldPrice, Double silverPrice, 
    Double bronzePrice, Double generalAdmissionPrice, Venue venue){
        super(eventID, eventType, name, date, time, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice, venue);
    }

}