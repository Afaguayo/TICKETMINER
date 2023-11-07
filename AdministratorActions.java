import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * AdministratorActions is a class that provides administrative functionalities for managing events.
 * Administrators can create new events, inquire about events by ID or name, and view a list of all events.
 * This class logs administrative actions to a text file.
 *
 * @author Angel, Caleb, Christian, & Javier
 * @since November 5, 2023
 */
public class AdministratorActions {


    /**
     * createEvent allows administrators to create a new event by providing various details such as event name,
     * type, date, time, venue, ticket prices, and more. If the event is successfully created, it is added to
     * the list of events. Administrative actions are logged into a text file.
     *
     * @param events   A list of events where the new event will be added.
     * @param keyboard A Scanner object for reading user input.
     */
    public static void createEvent(List<Event> events, Scanner keyboard) {
        // Find the largest event ID
        int largestEventID = findLargestEventID(events) + 1;

        // Increment it to get the new event ID
        int newEventID = largestEventID++;

        System.out.println("\nEnter the Event Name:");
        keyboard.nextLine(); // Consume the newline character left in the buffer
        String eventName = keyboard.nextLine();

        String eventType = "";
        System.out.println("\nSelect type of event: ");
        System.out.println("[1] Sport");
        System.out.println("[2] Concert");
        System.out.println("[3] Festival");
        int type = keyboard.nextInt();
        switch (type) {
            case 1:
                eventType = "Sport";
                break;
            case 2:
                eventType = "Concert";
                break;
            case 3:
                eventType = "Festival";
                break;
        }

        System.out.println("\nEnter the Event Date (MM/DD/YY, example 12/30/23 ):");
        String eventDate = keyboard.next();

        if (!isValidDate(eventDate)) {
            System.out.println("Invalid date format or invalid date. Event creation aborted.");
            return; // Exit without creating an event
        }

        System.out.println("\nEnter the valid Event Time (hh:mm) (12hr format, example 7:30):");
        String eventTime = keyboard.next();
        System.out.println("Is it 'AM' or 'PM'? (Enter 'AM' or 'PM'):");
        String amOrPm = keyboard.next();

        if (!isValidTime(eventTime, amOrPm)) {
            System.out.println("Invalid time format. Event creation aborted.");
            return; // Exit without creating an event
        } else {
            eventTime = eventTime + ' ' + amOrPm.toUpperCase();
        }

        int costForFireworks = 0;
        boolean fireworksPlanned = askForFireworks(keyboard);
        if (fireworksPlanned) {
            System.out.println("Enter cost of fireworks");
            costForFireworks = keyboard.nextInt();
        }

        String venueType = "";
        System.out.println("\nSelect Venue from the list:");
        System.out.println("[1] Sun Bowl Stadium ");
        System.out.println("[2] Don Haskins Center");
        System.out.println("[3] Magoffin Auditorium");
        System.out.println("[4] San Jacinto Plaza");
        System.out.println("[5] Centennial Plaza");
        int venueChoice = keyboard.nextInt();
        String venueName = ""; // Initialize with an empty string
        switch (venueChoice) {
            case 1:
                venueName = "Sun Bowl Stadium";
                venueType = "Stadium";
                break;
            case 2:
                venueName = "Don Haskins Center";
                venueType = "Arena";
                break;
            case 3:
                venueName = "Magoffin Auditorium";
                venueType = "Auditorium";
                break;
            case 4:
                venueName = "San Jacinto Plaza";
                venueType = "Auditorium";
                break;
            case 5:
                venueName = "Centennial Plaza";
                venueType = "Open Air";
                break;
            default:
                System.out.println("Invalid venue choice. Event creation aborted.");
                return; // Exit without creating an event
        }

        // Compute the number of seats for each ticket level
        int venueCapacity = computeVenueCapacity(venueName); // Implement this method

        System.out.println("Enter General Admission Price (max $4000):");
        double generalAdmissionPrice = keyboard.nextDouble();
        if (generalAdmissionPrice > 4000) {
            System.out.println("General Admission Price exceeds the maximum allowed. Event creation aborted.");
            return; // Exit without creating an event
        }

        int cost = computeCostForEvent(venueName, fireworksPlanned, costForFireworks);
        // Calculate prices for other ticket levels
        double vipPrice = generalAdmissionPrice * 5;
        double goldPrice = generalAdmissionPrice * 3;
        double silverPrice = generalAdmissionPrice * 2.5;
        double bronzePrice = generalAdmissionPrice * 1.5;
        
        Event event = buildEvent(venueName,  venueType,  venueCapacity,  cost,  fireworksPlanned,  costForFireworks,  eventType,  eventName,  eventDate,  eventTime, 
                                     vipPrice,  goldPrice,  silverPrice,  bronzePrice,  generalAdmissionPrice,  newEventID);
        
        events.add(event);
        ActionLogger.logInfo( "Admin created new event " + event.getName()); // Log into text file
        System.out.println("\nEvent Succesfully created");
        event.printInfo();
        System.out.println("Fireworks: $" + event.getVenue().getFireworksCost());
        System.out.println("Total cost: $" + event.getVenue().getCost());
    }


    /**
     * Builds an Event object based on the provided event details. The type of event (e.g., Sport, Concert, Festival) is determined
     * by the "eventType" parameter. It creates the corresponding event type (e.g., Sport, Concert, Festival) and associates it with
     * the specified venue. If the event type is not recognized, it throws an IllegalArgumentException.
     *
     * @param venueName             The name of the venue where the event will take place.
     * @param venueType             The type of the venue (e.g., Stadium, Arena, Auditorium, Open Air).
     * @param venueCapacity         The seating capacity of the venue.
     * @param cost                  The base cost of organizing the event.
     * @param fireworksPlanned      A boolean indicating whether fireworks are planned for the event.
     * @param costForFireworks      The cost of fireworks if they are planned.
     * @param eventType             The type of the event (e.g., Sport, Concert, Festival).
     * @param eventName             The name of the event.
     * @param eventDate             The date of the event in the "MM/DD/YY" format.
     * @param eventTime             The time of the event in "hh:mm AM/PM" (12-hour format).
     * @param vipPrice              The price for VIP tickets.
     * @param goldPrice             The price for Gold tickets.
     * @param silverPrice           The price for Silver tickets.
     * @param bronzePrice           The price for Bronze tickets.
     * @param generalAdmissionPrice The price for General Admission tickets.
     * @param newEventID            The unique ID of the new event.
     * @return An Event object of the specified type associated with the venue.
     * @throws IllegalArgumentException If the event type is not recognized.
     */
    private static Event buildEvent(String venueName, String venueType, int venueCapacity, int cost, boolean fireworksPlanned, int costForFireworks, String eventType, String eventName, String eventDate, String eventTime, 
    double vipPrice, double goldPrice, double silverPrice, double bronzePrice, double generalAdmissionPrice, int newEventID) {

    Venue venue = createVenue(venueName, venueType, venueCapacity, cost, fireworksPlanned, costForFireworks);

    switch (eventType.toLowerCase()) {
        case "sport":
            return new Sport(newEventID, eventType, eventName, eventDate, eventTime, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice, venue);
        case "concert":
            return new Concert(newEventID, eventType, eventName, eventDate, eventTime, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice, venue);
        case "festival":
            return new Festival(newEventID, eventType, eventName, eventDate, eventTime, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice, venue);
        default:
            throw new IllegalArgumentException("Invalid event type: " + eventType);
    }
}


/**
 * Creates a Venue object based on the provided venue details, including the venue name, type, seating capacity,
 * base cost, and information about fireworks. The specific Venue subclass is determined based on the venue name.
 * If the venue name is not recognized, it throws an IllegalArgumentException.
 *
 * @param venueName        The name of the venue.
 * @param venueType        The type of the venue (e.g., Stadium, Arena, Auditorium, Open Air).
 * @param venueCapacity    The seating capacity of the venue.
 * @param cost             The base cost of organizing an event at the venue.
 * @param fireworksPlanned A boolean indicating whether fireworks are planned for events at this venue.
 * @param costForFireworks The cost of fireworks if they are planned.
 * @return A Venue object of the specified type associated with the venue name.
 * @throws IllegalArgumentException If the venue name is not recognized.
 */
private static Venue createVenue(String venueName, String venueType, int venueCapacity, int cost, boolean fireworksPlanned, int costForFireworks) {
    if ("Sun Bowl Stadium".equals(venueName)) {
        return new Stadium(venueName, 0, venueType, venueCapacity, cost, 0.05, 0.1, 0.15, 0.2, 0.45, 0.05, fireworksPlanned, costForFireworks);
    } else if ("Don Haskins Center".equals(venueName)) {
        return new Arena(venueName, 0, venueType, venueCapacity, cost, 0.05, 0.1, 0.15, 0.2, 0.45, 0.05, fireworksPlanned, costForFireworks);
    } else if ("Magoffin Auditorium".equals(venueName)) {
        return new Auditorium(venueName, 0, venueType, venueCapacity, cost, 0.05, 0.1, 0.15, 0.2, 0.45, 0.05, fireworksPlanned, costForFireworks);
    } else if ("San Jacinto Plaza".equals(venueName) || "Centennial Plaza".equals(venueName)) {
        return new OpenAir(venueName, 0, venueType, venueCapacity, cost, 0.05, 0.1, 0.15, 0.2, 0.45, 0.05, fireworksPlanned, costForFireworks);
    } else {
        throw new IllegalArgumentException("Invalid venue name: " + venueName);
    }
}

    /**
     * Computes the total cost of an event based on the base cost and additional costs for fireworks if they are planned.
     *
     * @param cost             The base cost of organizing the event.
     * @param fireworksCost    The cost of fireworks if they are planned.
     * @param fireworksPlanned A boolean indicating whether fireworks are planned for the event.
     * @return The total cost of the event, including fireworks cost if planned.
     */
    private static int computeCostForEventHelper(int cost, int fireworksCost, boolean fireworksPlanned) {
        if (fireworksPlanned) {
            return cost + fireworksCost;
        } else {
            return cost;
        }
    }


     /**
     * Computes the total cost for organizing an event at a specified venue, considering the base cost and
     * additional costs for fireworks if they are planned. The total cost is determined based on the venue's name,
     * and it uses the helper method `computeCostForEventHelper` for calculations.
     *
     * @param venueName        The name of the venue where the event will take place.
     * @param fireworksPlanned A boolean indicating whether fireworks are planned for the event.
     * @param costForFireworks The cost of fireworks if they are planned.
     * @return The total cost of organizing the event at the specified venue, including fireworks cost if planned.
     */
    private static int computeCostForEvent(String venueName, boolean fireworksPlanned, int costForFireworks) {
        switch (venueName) {
            case "Don Haskins Center":
                return computeCostForEventHelper(150400, costForFireworks, fireworksPlanned);
            case "Sun Bowl Stadium":
                return computeCostForEventHelper(681500, costForFireworks, fireworksPlanned);
            case "Magoffin Auditorium":
                return computeCostForEventHelper(58750, costForFireworks, fireworksPlanned);
            case "San Jacinto Plaza":
                return computeCostForEventHelper(176250, costForFireworks, fireworksPlanned);
            case "Centennial Plaza":
                return computeCostForEventHelper(58750, costForFireworks, fireworksPlanned);
            default:
                return 0;
        }
    }


     /**
     * Computes the seating capacity of a venue based on its name. The capacity is determined based on the venue's name.
     * If the venue name is not recognized, it returns 0. This method is used to calculate ticket prices relative to
     * the venue's capacity.
     *
     * @param venueName The name of the venue.
     * @return The seating capacity of the specified venue or 0 if the venue name is not recognized.
     */
    private static int computeVenueCapacity(String venueName) {
        switch (venueName) {
            case "Don Haskins Center":
                return 12800;
            case "Sun Bowl Stadium":
                return 58000;
            case "Magoffin Auditorium":
                return 1152;
            case "San Jacinto Plaza":
                return 15000;
            case "Centennial Plaza":
                return 5000;
            default:
                // Handle the case when the venue name is not recognized
                return 0; // Or you can throw an exception or handle it differently
        }
    }


     /**
     * Checks if the provided date is in the "MM/DD/YY" format and if it's a valid date. The method uses a SimpleDateFormat
     * to validate the date format and ensure it's not a lenient parsing that allows invalid dates.
     *
     * @param date The date to be checked in the "MM/DD/YY" format.
     * @return `true` if the date is in the correct format and is valid; otherwise, `false`.
     */
    private static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        dateFormat.setLenient(false); // Disallow invalid dates
        try {
            Date parsedDate = dateFormat.parse(date);
            return parsedDate != null;
        } catch (ParseException e) {
            return false;
        }
    }


    /**
     * Checks if the provided time and AM/PM indicator are in valid formats. The time format must be "hh:mm" in
     * 12-hour notation, and the AM/PM indicator must be "AM" or "PM" (case-insensitive).
     *
     * @param time   The time to be checked, in "hh:mm" format.
     * @param amOrPm The AM/PM indicator, either "AM" or "PM" (case-insensitive).
     * @return `true` if both time and AM/PM are in valid formats; otherwise, `false`.
     */
    private static boolean isValidTime(String time, String amOrPm) {
        String timeRegex = "^(0?[1-9]|1[0-2]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(timeRegex);
        if (pattern.matcher(time).matches() && (amOrPm.equalsIgnoreCase("AM") || amOrPm.equalsIgnoreCase("PM"))) {
            return true;
        }
        return false;
    }


    /**
     * Finds the largest event ID among a list of events. It iterates through the events and returns the largest
     * event ID found. If no events are present, it returns 0.
     *
     * @param events A list of events from which to find the largest event ID.
     * @return The largest event ID among the provided events, or 0 if there are no events.
     */
    private static int findLargestEventID(List<Event> events) {
        int largestEventID = 0;
        for (Event event : events) {
            if (event.getEventID() > largestEventID) {
                largestEventID = event.getEventID();
            }
        }
        return largestEventID;
    }


    /**
     * Asks the user whether fireworks will be planned for an event. The method displays a prompt and reads
     * the user's response from the provided `Scanner` object. It returns `true` if the response is "yes" (case-insensitive),
     * indicating that fireworks are planned, or `false` otherwise.
     *
     * @param keyboard A `Scanner` object for reading the user's response.
     * @return `true` if fireworks are planned; otherwise, `false`.
 */
    public static boolean askForFireworks(Scanner keyboard) {
        System.out.println("Will there be fireworks at the event? (yes or no)");
        String response = keyboard.next();

        return response.equalsIgnoreCase("yes");
    }
    
/**     
 * The `userAdmin` method provides an administrative menu for querying event information. It allows
 * administrators to inquire events by ID or name and view a list of all events. The method logs
 * administrative actions to a text file.
 *
 * @param events   A list of events to be queried.
 * @param keyboard A `Scanner` object for reading user input.
 */
public static void userAdmin(List<Event> events, Scanner keyboard) {
    // Log the administrator's login
    ActionLogger.logInfo( "Admin logged in"); 

    String adminChoice = "";
    String adminNameChoice = "";
    int adminIdChoice = -999;
    boolean adminLogged = false;

    adminLogged = false;

    while (!adminLogged) {
        System.out.println("\n---------------------------------");
        System.out.println("[1] Inquire event by ID");
        System.out.println("[2] Inquire event by name");
        System.out.println("[3] Inquire all events");
        System.out.println("[4] To exit admin menu");
        System.out.println("[5] Create a new event"); // New option to create an event
        System.out.println("\nPlease enter an option: ");

        adminChoice = keyboard.next();

        switch (adminChoice) {
            case "1":
                System.out.println("Please enter the ID of the event you'd like to see.");
                try {
                    adminIdChoice = keyboard.nextInt(); // User input
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid option.");
                    keyboard.next(); // Consume the invalid input
                }
                inquireId(events, adminIdChoice);
                ActionLogger.logInfo( "Admin inquired on event ID #" + adminIdChoice); // Log into text file
                break;

            case "2":
                System.out.println("Please enter the name of the event you'd like to see.");
                keyboard.nextLine(); // Consume the newline character left in the buffer
                adminNameChoice = keyboard.nextLine(); // User input
                inquireByname(events, adminNameChoice);
                ActionLogger.logInfo( "Admin inquired on event " + adminNameChoice); // Log into text file
                break;

            case "3":
                printAllEvents(events);
                ActionLogger.logInfo( "Admin inquired on all events"); // Log into text file
                break;

            case "4":
                ActionLogger.logInfo( "Admin logged out"); // Log into text file
                adminLogged = true;
                break;
                
            case "5": // Option to create a new event
                createEvent(events, keyboard);
                ActionLogger.logInfo("Admin created a new event"); // Log into text file
                break;

            default:
                System.out.println("Invalid choice. Please select a valid option (1, 2, 3, 4, or 5).");
        }
    }
}


/**
 * The `inquireId` method allows administrators to inquire an event by its unique ID. If a matching event
 * is found in the provided list of events, its details are printed. Otherwise, it indicates that the
 * ID is not in the database.
 *
 * @param data    A list of events to search for the event by ID.
 * @param eventId The ID of the event to be inquired.
 */
public static void inquireId(List<Event> data, int eventId) {
    boolean found = false;

    for (Event event : data) {
        if (eventId == event.getEventID()) {
            event.printEventData();
            found = true;
        }
    }

    if (!found) {
        System.out.println("Invalid ID in the database");
    }
}

/**
 * The `inquireByname` method allows administrators to inquire an event by its name. If a matching event
 * is found in the provided list of events, its details are printed. Otherwise, it indicates that the
 * name is not in the database.
 *
 * @param data      A list of events to search for the event by name.
 * @param eventName The name of the event to be inquired.
 */
public static void inquireByname(List<Event> data, String eventName) {
    boolean found = false;

    for (Event event : data) {
        if (eventName.equalsIgnoreCase(event.getName())) {
            event.printEventData();
            found = true;
        }
    }

    if (!found) {
        System.out.println("Invalid name in the database");
    }
}


/**
 * The `printAllEvents` method allows administrators to print the details of all events in the provided
 * list of events.
 *
 * @param data A list of events to be printed.
 */
public static void printAllEvents(List<Event> data) {
    for (Event event : data) {
        event.printEventData();
    }
}

}