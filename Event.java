import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The `Event` class represents an abstract event with various properties and methods.
 * This class serves as a base class for specific event types.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public abstract class Event {
    
    private List<Ticket> purchasedTickets;
    private int eventID;
    private String eventType;
    private String name;
    private String date;
    private String time;
    private Double vipPrice;
    private Double goldPrice;
    private Double silverPrice;
    private Double bronzePrice;
    private Double generalAdmissionPrice;
    private Venue venue;

    /**
     * Default constructor for the `Event` class. Creates an empty event object with no attributes set.
     */
    public Event() {
    }

    /**
     * Constructor for the `Event` class with detailed event properties.
     * 
     * @param purchasedTickets List of purchased tickets.
     * @param eventID Event ID.
     * @param eventType Event type.
     * @param name Event name.
     * @param date Event date.
     * @param time Event time.
     * @param vipPrice VIP ticket price.
     * @param goldPrice Gold ticket price.
     * @param silverPrice Silver ticket price.
     * @param bronzePrice Bronze ticket price.
     * @param generalAdmissionPrice General admission ticket price.
     * @param venue The venue where the event takes place.
     */
    public Event(List<Ticket> purchasedTickets, int eventID, String eventType, String name, String date, String time, Double vipPrice, Double goldPrice, Double silverPrice, Double bronzePrice, Double generalAdmissionPrice, Venue venue) {
        this.purchasedTickets = purchasedTickets;
        this.eventID = eventID;
        this.eventType = eventType;
        this.name = name;
        this.date = date;
        this.time = time;
        this.vipPrice = vipPrice;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.bronzePrice = bronzePrice;
        this.generalAdmissionPrice = generalAdmissionPrice;
        this.venue = venue;
    }

    /**
     * Constructor for the `Event` class with minimal event properties.
     * 
     * @param eventID Event ID.
     * @param eventType Event type.
     * @param name Event name.
     */
    public Event(int eventID, String eventType, String name) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.name = name;
    }

    /**
     * Constructor for the `Event` class with detailed event properties.
     * 
     * @param eventID Event ID.
     * @param eventType Event type.
     * @param name Event name.
     * @param date Event date.
     * @param time Event time.
     * @param vipPrice VIP ticket price.
     * @param goldPrice Gold ticket price.
     * @param silverPrice Silver ticket price.
     * @param bronzePrice Bronze ticket price.
     * @param generalAdmissionPrice General admission ticket price.
     * @param venue The venue where the event takes place.
     */
    public Event(int eventID, String eventType, String name, String date, String time, Double vipPrice, Double goldPrice, Double silverPrice, Double bronzePrice, Double generalAdmissionPrice, Venue venue) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.name = name;
        this.date = date;
        this.time = time;
        this.vipPrice = vipPrice;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.bronzePrice = bronzePrice;
        this.generalAdmissionPrice = generalAdmissionPrice;
        this.venue = venue;
    }

    /**
     * Get the event's ID.
     * 
     * @return The event ID.
     */
    public int getEventID() {
        return this.eventID;
    }

    /**
     * Set the event's ID.
     * 
     * @param eventID The event ID to set.
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * Get the event's type.
     * 
     * @return The event type.
     */
    public String getEventType() {
        return this.eventType;
    }

    /**
     * Set the event's type.
     * 
     * @param eventType The event type to set.
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Get the event's name.
     * 
     * @return The event name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the event's name.
     * 
     * @param name The event name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the event's date.
     * 
     * @return The event date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Set the event's date.
     * 
     * @param date The event date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the event's time.
     * 
     * @return The event time.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Set the event's time.
     * 
     * @param time The event time to set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the VIP ticket price for the event.
     * 
     * @return The VIP ticket price.
     */
    public Double getVipPrice() {
        return this.vipPrice;
    }

    /**
     * Set the VIP ticket price for the event.
     * 
     * @param vipPrice The VIP ticket price to set.
     */
    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    /**
     * Get the gold ticket price for the event.
     * 
     * @return The gold ticket price.
     */
    public Double getGoldPrice() {
        return this.goldPrice;
    }

    /**
     * Set the gold ticket price for the event.
     * 
     * @param goldPrice The gold ticket price to set.
     */
    public void setGoldPrice(Double goldPrice) {
        this.goldPrice = goldPrice;
    }

    /**
     * Get the silver ticket price for the event.
     * 
     * @return The silver ticket price.
     */
    public Double getSilverPrice() {
        return this.silverPrice;
    }

    /**
     * Set the silver ticket price for the event.
     * 
     * @param silverPrice The silver ticket price to set.
     */
    public void setSilverPrice(Double silverPrice) {
        this.silverPrice = silverPrice;
    }

    /**
     * Get the bronze ticket price for the event.
     * 
     * @return The bronze ticket price.
     */
    public Double getBronzePrice() {
        return this.bronzePrice;
    }

    /**
     * Set the bronze ticket price for the event.
     * 
     * @param bronzePrice The bronze ticket price to set.
     */
    public void setBronzePrice(Double bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

    /**
     * Get the general admission ticket price for the event.
     * 
     * @return The general admission ticket price.
     */
    public Double getGeneralAdmissionPrice() {
        return this.generalAdmissionPrice;
    }

    /**
     * Set the general admission ticket price for the event.
     * 
     * @param generalAdmissionPrice The general admission ticket price to set.
     */
    public void setGeneralAdmissionPrice(Double generalAdmissionPrice) {
        this.generalAdmissionPrice = generalAdmissionPrice;
    }

    /**
     * Get the venue where the event takes place.
     * 
     * @return The venue.
     */
    public Venue getVenue() {
        return this.venue;
    }

    /**
     * Set the venue where the event takes place.
     * 
     * @param venue The venue to set.
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * Calculate the ticket price for the given event and ticket type.
     * 
     * @param event The event for which to calculate the ticket price.
     * @param ticketType The type of the ticket (1 for General Admission, 2 for Bronze, 3 for Silver, 4 for Gold, 5 for VIP).
     * @return The calculated ticket price.
     */
    public static double calculateTicketPrice(Event event, int ticketType) {
        double ticketPrice = 0.0;

        switch (ticketType) {
            case 1:
                ticketPrice = event.getGeneralAdmissionPrice();
                break;
            case 2:
                ticketPrice = event.getBronzePrice();
                break;
            case 3:
                ticketPrice = event.getSilverPrice();
                break;
            case 4:
                ticketPrice = event.getGoldPrice();
                break;
            case 5:
                ticketPrice = event.getVipPrice();
                break;
            default:
                System.out.println("Invalid ticket type.");
        }

        return ticketPrice;
    }

    /**
     * Show events of a specific type from a list of events.
     * 
     * @param events The list of events to filter.
     * @param eventType The type of event to filter.
     */
    public static void showEventsByType(List<Event> events, String eventType) {
        // Filter events based on the given eventType
        List<Event> filteredEvents = events.stream()
                .filter(event -> event.getEventType().equalsIgnoreCase(eventType))
                .collect(Collectors.toList());

        // Sort the filtered events based on their IDs before displaying
        filteredEvents.sort(Comparator.comparingInt(Event::getEventID));

        // Display the sorted events
        for (Event event : filteredEvents) {
            event.displayEventDetails();
        }
    }


    /**
     * Retrieve a list of purchased tickets for this event and print their information.
     * 
     * @return The list of purchased tickets.
     */
    public List<Ticket> getPurchasedTickets() {
        for (Ticket ticket : purchasedTickets) {
            ticket.printInfo();
        }
        return purchasedTickets;
    }


    public void purchaseTickets(int ticketType, int ticketQuantity, Customer customer) {
        switch (ticketType) {
            case 1:
                venue.incrementGeneralAdmSeatsSold(ticketQuantity);
                venue.updateRevenueGeneralAdm(generalAdmissionPrice, ticketQuantity, customer);

                break;
            case 2:
                venue.incrementBronzeSeatsSold(ticketQuantity);
                venue.updateRevenueBronze(bronzePrice, ticketQuantity, customer);
                break;
            case 3:
                venue.incrementSilverSeatsSold(ticketQuantity);
                venue.updateRevenueSilver(silverPrice, ticketQuantity, customer);
                break;
            case 4:
                venue.incrementGoldSeatsSold(ticketQuantity);
                venue.updateRevenueGold(goldPrice, ticketQuantity, customer);
                break;
            case 5:
                venue.incrementVIPSeatsSold(ticketQuantity);
                venue.updateRevenueVIP(vipPrice, ticketQuantity, customer);
                break;
            default:
                break;
        }
    }

    /**
     * Calculate the expected profit for the event, assuming a sellout of all available seats.
     * 
     * @return The expected profit formatted as a string.
     */
    public String calculateExpectedProfit() {
        int vipSeatsAvailable = venue.calculateAvailableVIPSeats();
        int goldSeatsAvailable = venue.calculateGoldSeatsAvailable();
        int silverSeatsAvailable = venue.calculateSilverSeatsAvailable();
        int bronzeSeatsAvailable = venue.calculateBronzeSeatsAvailable();
        int generalAdmissionSeatsAvailable = venue.calculateGeneralAdmissionSeatsAvailable();
        double vipPrice = getVipPrice();
        double goldPrice = getGoldPrice();
        double silverPrice = getSilverPrice();
        double bronzePrice = getBronzePrice();
        double generalAdmissionPrice = getGeneralAdmissionPrice();
        double totalProfit = (vipSeatsAvailable * vipPrice) + (goldSeatsAvailable * goldPrice) +
                            (silverSeatsAvailable * silverPrice) + (bronzeSeatsAvailable * bronzePrice) +
                            (generalAdmissionSeatsAvailable * generalAdmissionPrice);
        // Format the totalProfit as a string with two decimal places
        String formattedTotalProfit = String.format("%.2f", totalProfit);
        return formattedTotalProfit;
    }
    /**
     * Create a log string with useful information about the event for logging purposes.
     * 
     * @return The log information as a string.
     */
    public String logInfo() {
        String loggerInfo = "'" + eventID + " " + eventType + " " + name + " " + date + " " + time + " "  + venue.getVenueName() + "'";
        return loggerInfo;
    }

    /**
     * Print detailed information about the event.
     */
    public void printInfo() {
        System.out.println("Event Information:");
        System.out.println("=================");
        System.out.printf("ID: %-22s   Type: %s\n", eventID, eventType);
        System.out.printf("Name: %-20s   Date: %s\n", name, date);
        System.out.printf("Time: %-20s   Venue: %s\n", time, venue.getVenueName());
        System.out.printf("VIP Price: $%-14s   Gold Price: $%-9s\n", vipPrice, goldPrice);
        System.out.printf("Silver Price: $%-11s   Bronze Price: $%-9s\n", silverPrice, bronzePrice);
        System.out.printf("General Admission Price: $%-9s\n", generalAdmissionPrice);
        System.out.println();
    }

    /**
     * Display event details, ticket prices, and available money for a customer.
     * 
     * @param customer The customer making the purchase.
     */
    public void eventPrices(Customer customer) {
        int seatsUnavailable = (int)(((double)this.getVenue().getPctSeatsUnavailable() / 100.0) * this.getVenue().getCapacity());
        int venueCapacity = this.getVenue().getCapacity();
        int seatsAvailable = (venueCapacity - seatsUnavailable);

        if (seatsAvailable <= 0) {
            System.out.println("Sorry, no tickets available for this event.");
            return;
        }
        displayEventDetails();
        System.out.println("\n*-*-*-* T_I_C_K_E_T_S *-*-*-*");
        System.out.println("-----------------------------");
        System.out.println("[1] General Admission: $" + this.generalAdmissionPrice);
        System.out.println("[2] Bronze Admission: $" + this.bronzePrice);
        System.out.println("[3] Silver Admission: $" + this.silverPrice);
        System.out.println("[4] Gold price: $" + this.goldPrice);
        System.out.println("[5] VIP price: $" + this.vipPrice);
        System.out.println("=================================");
        System.out.println("Your money available = $ " + Invoice.roundToTwoDecimals(customer.getMoneyAvailable()));
    }

    /**
     * Displays event details.
     * 
     * @param None
     * @return None
     */
    public void displayEventDetails() {
        System.out.println("\n=================================");
        System.out.println("Event ID: " + this.eventID);
        System.out.println("Event Name: " + this.getName());
        System.out.println("Event Type: " + this.getEventType());
        System.out.println("Event Date: " + this.getDate());
        System.out.println("Event Time: " + this.getTime());
        System.out.println("Venue: " + this.venue.getVenueName());
    }

    
    /**
    * This method prints all event data
    * @param None
    * @return None
    */
    public void printEventData() {
        displayEventDetails();
        System.out.println("Event Capacity: " + venue.getCapacity());
        System.out.println("Total Seats sold: " + venue.totalSeatsSold());
        System.out.println("Total VIP Seats Sold: " + venue.getTotalVIPSeatsSold());
        System.out.println("Total Gold Seats Sold: " + venue.getTotalGoldSeatsSold());
        System.out.println("Total Silver Seats Sold: " + venue.getTotalSilverSeatsSold());
        System.out.println("Total Bronze Seats Sold: " + venue.getTotalBronzeSeatsSold());
        System.out.println("Total General Adm Seats Sold: " + venue.getTotalGeneralAdmSeatsSold());
        System.out.println("Total revenue for VIP tickets: $" + venue.getTotalRevenueVIP());
        System.out.println("Total revenue for Gold tickets: $" + venue.getTotalRevenueGold());
        System.out.println("Total revenue for Silver tickets: $" + venue.getTotalRevenueSilver());
        System.out.println("Total revenue for Bronze tickets: $" + venue.getTotalRevenueBronze());
        System.out.println("Total revenue for General Admission tickets: $" + venue.getTotalRevenueGeneralAdm());
        System.out.println("Total revenue for all tickets: $" + venue.getTotalRevenue());
        System.out.println("Expected profit (Sell Out): $" + calculateExpectedProfit());
        System.out.println("Actual profit: $" + venue.calculateActualProfit());
        System.out.println("Discounts made for TicketMiner Members: $" + Invoice.roundToTwoDecimals(venue.getDiscounts()));
        System.out.println("");
    }


}// end of file-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------