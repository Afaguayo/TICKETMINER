import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * AdministratorActions is a class that provides administrative functionalities for managing events.
 * Administrators can create new events, inquire about events by ID or name, and view a list of all events.
 * This class logs administrative actions to a text file.
 *
 * @author Angel, Caleb, Christian, & Javier
 * @since November 19, 2023
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
        int type = 0;
    
        // Increment it to get the new event ID
        int newEventID = largestEventID++;
    
        System.out.println("\nEnter the Event Name (type 0 to return):");
        keyboard.nextLine(); // Consume the newline character left in the buffer
        String eventName = keyboard.nextLine();
    
        if (eventName.equals("0")) {
            System.out.println("Returning to main menu...");
            ActionLogger.logInfo("Admin returned to admin menu"); // Log into text file
            return; // Exit without creating an event
        }    

        String eventType = "";
        System.out.println("\nSelect type of event: ");
        System.out.println("[1] Sport");
        System.out.println("[2] Concert");
        System.out.println("[3] Festival");


        
        
        try {
            type = keyboard.nextInt(); // User input
            keyboard.nextLine(); // Consume the newline character
        } catch (java.util.InputMismatchException e) {
            keyboard.nextLine(); // Clears bad input
            type = 0;
        }
        
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
            default:
                
                while(type < 1 || type > 3){
                System.out.println("invalid input please enter a valid choice.");
                        try {
                        type = keyboard.nextInt(); // User input
                        keyboard.nextLine(); // Consume the newline character
                        } catch (java.util.InputMismatchException e) {
                        keyboard.nextLine(); // Clears bad input
                        type = 0;
                        }
                    }
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
public static void userAdmin(List<Event> events,List<Customer> customers, Scanner keyboard) {
    // Log the administrator's login
    ActionLogger.logInfo( "Admin logged in"); 

    String adminChoice = "";
    String adminNameChoice = "";
    int adminIdChoice = -999;
    int eventIDtoCancel = -999;
    boolean adminLogged = false;

    adminLogged = false;

    while (!adminLogged) {
        System.out.println("\n---------------------------------");
        System.out.println("[\u001B[33m1\u001B[0m] \u001B[33mInquire event by ID\u001B[0m");
        System.out.println("[\u001B[33m2\u001B[0m] \u001B[33mInquire event by name\u001B[0m");
        System.out.println("[\u001B[33m3\u001B[0m] \u001B[33mInquire all events\u001B[0m");
        System.out.println("[\u001B[33m4\u001B[0m] \u001B[33mCancel event\u001B[0m");
        System.out.println("[\u001B[33m5\u001B[0m] \u001B[33mCreate a new event\u001B[0m"); 
        System.out.println("[\u001B[33m6\u001B[0m] \u001B[33mCompute/print the amount of money gained by The TicketMiner Company for an event\u001B[0m");
        System.out.println("[\u001B[33m7\u001B[0m] \u001B[33mCompute/print the amount of money gained by The TicketMiner Company for all events\u001B[0m");
        System.out.println("[\u001B[33m8\u001B[0m] \u001B[33mCreate invoice for customer\u001B[0m");
        System.out.println("[\u001B[33m9\u001B[0m] \u001B[33mRetrieve all customer purchases\u001B[0m");
        System.out.println("[\u001B[33m10\u001B[0m] \u001B[33mExit admin menu\u001B[0m");
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
 
                try {
                    adminNameChoice = keyboard.next(); // User input
                    keyboard.nextLine(); // Consume the newline character
                } catch (java.util.InputMismatchException e) {
                    keyboard.nextLine(); // Clears bad input
                    adminNameChoice = "";
                }

                inquireByname(events, adminNameChoice);
                ActionLogger.logInfo( "Admin inquired on event " + adminNameChoice); // Log into text file
                break;

            case "3":
                printAllEvents(events);
                ActionLogger.logInfo( "Admin inquired on all events"); // Log into text file
                break;

            case "4":
                System.out.println("Select an Event ID to cancel (type 0 to return): ");

                
                try {
                    eventIDtoCancel = keyboard.nextInt(); // User input
                    keyboard.nextLine(); // Consume the newline character
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid ID in the database");
                    keyboard.nextLine(); // Clears bad input
                }

                // Check if user wants to return to main menu
                if (eventIDtoCancel == 0) {
                    System.out.println("Returning to main menu...");
                    ActionLogger.logInfo("Admin returned to admin menu"); // Log into text file
                    break;
                }
                
                Event event = getEventById(events, eventIDtoCancel);
                for (Customer customer : customers) {
                    cancelEventTicketPurchases(keyboard, customer, event, events);
                }
                cancelEvent(events, eventIDtoCancel, customers);
                ActionLogger.logInfo("Admin canceled an event with ID: " + eventIDtoCancel); // Log into text file
                break;
            
                
            case "5": // Option to create a new event
                createEvent(events, keyboard);
                ActionLogger.logInfo("Admin created a new event"); // Log into text file
                break;

            case "6":
                System.out.println("Please enter the ID of the event you'd like to select.");
                try {
                    adminIdChoice = keyboard.nextInt(); // User input
                } 
                catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid option.");
                    keyboard.next(); // Consume the invalid input
                }
                profitIdEvent(events,adminIdChoice);

                ActionLogger.logInfo( "Compute/print the amount of money gained by The TicketMiner Company for an event"); // Log into text file
                break;
                
            case "7": 
                profitAllEvents(events);
                ActionLogger.logInfo("Compute/print the amount of money gained by The TicketMiner Company for all events"); // Log into text file
                break;

            case "8": 
                
                createInvoice(customers);
                ActionLogger.logInfo("Admin created an invoice"); // Log into text file

                break;

            case "9":
                InvoiceGenerator.printPurchaseHistoryForCustomers(customers);


            case "10":
                ActionLogger.logInfo( "Admin logged out"); // Log into text file
                adminLogged = true;
                break;

            default:
                System.out.println("Invalid choice. Please select a valid option (1, 2, 3, 4, 5, 6, 7, 8, 9, or 10).");
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
 * Prints the details of all events in the specified list in ascending order based on their event ID.
 *
 * @param data The list of events to be printed.
 */
public static void printAllEvents(List<Event> data) {
    // Sort the events based on their IDs before displaying
    List<Event> sortedEvents = data.stream()
            .sorted(Comparator.comparingInt(Event::getEventID))
            .collect(Collectors.toList());

    // Display the sorted events
    for (Event event : sortedEvents) {
        event.printEventData();
    }
}




/**
 * Calculates and prints the profit generated by each event in the given list.
 * The profit is calculated based on the difference between the total revenue and
 * the expected profit.
 *
 * @param data A list of events for which profit needs to be calculated.
 */
public static void profitAllEvents(List<Event> data) {
    List<Event> sortedEvents = data.stream()
        .sorted(Comparator.comparingInt(Event::getEventID))
        .collect(Collectors.toList());
        
    for (Event event : sortedEvents) {
        event.getVenue().printMoneyRaised(event);
    }
}


/**
 * Calculates and displays the profit generated by a specific event identified by its event ID.
 * If the event ID is not found in the database, an error message is printed.
 *
 * @param data    The list of events in the database.
 * @param eventId The ID of the event for which profit is calculated and displayed.
 */
public static void profitIdEvent(List<Event> data, int eventId) {
    // Filter events based on the given eventId
    List<Event> filteredEvents = data.stream()
            .filter(event -> eventId == event.getEventID())
            .collect(Collectors.toList());

    // Sort the filtered events by their IDs before calculating profit
    filteredEvents.sort(Comparator.comparingInt(Event::getEventID));

    // Display the profit for the sorted events
    if (!filteredEvents.isEmpty()) {
        for (Event event : filteredEvents) {
            event.getVenue().printMoneyRaised(event);
        }
    } else {
        System.out.println("Invalid ID in the database");
    }
}


    /**
     * Allows the generation of invoice summaries for customers based on their usernames.
     * The method prompts the user to enter the username of the customer for whom an invoice summary is to be generated.
     * If a matching username is found in the list of customers, an invoice summary is generated and displayed.
     * The process can be repeated for multiple customers until the user chooses to exit.
     *
     * @param customers A list of Customer objects representing the pool of customers.
     */
    public static void createInvoice(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);

        boolean generateAnother = true;

        while (generateAnother) {
            System.out.println("\nPlease enter the username of the user you want to generate an invoice for:");

            Boolean found = false;
            String username = "";

            try {
                username = scanner.next(); // User input
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Clears bad input
            }

            for (Customer user : customers) {
                if (username.equalsIgnoreCase(user.getUserName())) {
                    found = true;
                    System.out.println(user.getTicketPurchases());
                    InvoiceGenerator.generateInvoiceSummary(user, user.getTicketPurchases());
                    break; // Exit the loop once a match is found
                }
            }

            if (!found) {
                System.out.println("Username not found...");
            } else {
                System.out.println("\nInvoice successfully generated!");
            }

            // Ask if the user wants to generate another invoice summary
            System.out.println("Do you want to generate another invoice summary? (yes/no)");
            String anotherSummary = scanner.next();

            if (!anotherSummary.equalsIgnoreCase("yes")) {
                generateAnother = false;
            }
            
        }
    }


    /**
     * Retrieves an event based on its unique ID.
     *
     * @param events  A list of events to search for the event by ID.
     * @param eventID The ID of the event to be retrieved.
     * @return The event with the specified ID, or null if not found.
     */
    public static Event getEventById(List<Event> events, int eventID) {
        for (Event event : events) {
            if (eventID == event.getEventID()) {
                return event;
            }
        }
        return null; // Event with the specified ID not found
    }


    /**
     * Cancels an event with the specified ID. If the event is found in the list of events, it is removed.
     *
     * @param events  A list of events where the event will be canceled.
     * @param eventId The ID of the event to be canceled.
     */
    public static void cancelEvent(List<Event> events, int eventId, List<Customer> customers) {
        Event eventToRemove = null;

        for (Event event : events) {
            if (eventId == event.getEventID()) {
                eventToRemove = event;
                break;
            }
        }

        if (eventToRemove != null) {
            // Remove the canceled event from the list of events
            events.remove(eventToRemove);
            System.out.println("Event with ID " + eventId + " has been canceled.");
        }
    }


     /**
     * Cancels all ticket purchases related to the specified event for a given customer.
     * Refunds the appropriate amount to the customer and updates the event's revenue accordingly.
     * The cancellation is based on the event name and considers only purchases related to the specified event.
     *
     * @param scanner  The Scanner object for user input.
     * @param customer The Customer object for whom the ticket purchases are to be canceled.
     * @param event    The Event object representing the event for which ticket purchases need to be canceled.
     * @param events   The list of all events, used to find the event by name.
     */
    private static void cancelEventTicketPurchases(Scanner scanner, Customer customer, Event event, List<Event> events) {
        // Retrieve the customer's purchase history
        List<Map<String, Object>> customerPurchases = InvoiceGenerator.getCustomerPurchaseHistory(customer);
    
        // Filter purchases related to the specified event
        List<Map<String, Object>> eventPurchases = new ArrayList<>();
        for (Map<String, Object> purchase : customerPurchases) {
            Object purchaseEventNameObject = purchase.get("Event Name");
    
            // Check if the Event Name is not null and is of the correct type (String)
            if (purchaseEventNameObject instanceof String) {
                String purchaseEventName = (String) purchaseEventNameObject;
    
                if (event.getName().equalsIgnoreCase(purchaseEventName)) {
                    eventPurchases.add(purchase);

                    // Ticket Type
                    int ticketType = (int) purchase.get("Ticket Type");

                    // Number Of Tickets
                    int numberOfTickets = (int) purchase.get("Number Of Tickets");

                    // Event Name
                    String eventName = (String) purchase.get("Event Name");
                
                    // Calculate the refund amount (excluding fees)
                    double refundAmount = 0.0;
                    Object totalPriceObject = purchase.get("Subtotal");
                    if (totalPriceObject instanceof String) {
                        String totalPriceStr = (String) totalPriceObject;
                        refundAmount = Double.parseDouble(totalPriceStr.replace("$", ""));
                    } else if (totalPriceObject instanceof Double) {
                        refundAmount = (Double) totalPriceObject;
                    }
    
                    // Update the customer's balance
                    customer.setMoneyAvailable(customer.getMoneyAvailable() + refundAmount);
                    Event evento = customerActions.findEventByName(events, eventName);
            
                    //Subtract money from event revenues
                    customerActions.fixRevenues(customer, events, ticketType, numberOfTickets, evento);
                }
            }
        }
    
        // Loop through event purchases and cancel each one
        for (Map<String, Object> purchase : eventPurchases) {
            String confirmationNumber = (String) purchase.get("Confirmation Number");
    
            // Check if the confirmationNumber is not null before canceling
            if (confirmationNumber != null) {
                InvoiceGenerator.cancelOrderAndUpdateInvoice(customer, confirmationNumber);
            }
        }
    }
    
    

}