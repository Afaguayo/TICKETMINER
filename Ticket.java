 /**
 * This is the Ticket class.
 * It represents a ticket for an event in a venue.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class Ticket {

    private Double price;
    private Venue venue;
    private Event event;

    /**
     * Default constructor for a Ticket.
     */
    public Ticket() {
    }

    /**
     * Constructs a Ticket with specific attributes.
     *
     * @param eventID   The ID of the event.
     * @param eventType The type of the event.
     * @param venueType The type of the venue.
     * @param price     The price of the ticket.
     * @param name      The name of the ticket.
     */
    public Ticket(String eventID, String eventType, String venueType, Double price, String name) {
        this.price = price;
    }

    /**
     * Gets the price of the ticket.
     *
     * @return The price of the ticket.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Prints information about the event and venue associated with the ticket.
     */
    public void printInfo() {
        System.out.println("Event Information:");
        System.out.println("=================");
        System.out.printf("ID: %-22s Type: %s\n", event.getEventID(), event.getEventType());
        System.out.printf("Name: %-20s Date: %s\n", event.getName(), event.getDate());
        System.out.printf("Time: %-20s Venue: %s\n", event.getTime(), venue.getVenueName());
        System.out.printf("VIP Price: %-15s Gold Price: %-10s\n", event.getVipPrice(), event.getGoldPrice());
        System.out.printf("Silver Price: %-12s Bronze Price: %-10s\n", event.getSilverPrice(), event.getBronzePrice());
        System.out.printf("General Admission Price: %-10s\n", event.getGeneralAdmissionPrice());
        System.out.println();
    }
}
