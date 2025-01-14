import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class includes all methods used to compute the customer actions like buy tickets, cancel tickets, and iterate over a list of events.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
public class customerActions {

    private TicketPricingStrategy pricingStrategy;

    /**
     * Sets the pricing strategy for ticket prices.
     *
     * @param pricingStrategy The pricing strategy to be set.
     */
    public void setPricingStrategy(TicketPricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }


    /**
     * Allows a user to perform various actions as a customer, such as
     * browsing events and buying tickets.
     *
     * @param scanner          A `Scanner` object for user input.
     * @param events           A list of available events.
     * @param selectedCustomer The customer performing the actions.
     */

    public static void userCustomer(Scanner scanner, List<Event> events, Customer selectedCustomer) {
        int eventTypeChoice = -99;
        int choice = 0;
        Boolean customerMenuRunning = true;

        while (customerMenuRunning) {
            System.out.println("\u001B[32m\n┌─────────────────────────────────┐");
            System.out.println("│         M A I N   M E N U       │");
            System.out.println("└─────────────────────────────────┘\u001B[0m");
            System.out.println("[\u001B[36m1\u001B[0m] \u001B[36mLook events by type\u001B[0m");  
            System.out.println("[\u001B[33m2\u001B[0m] \u001B[33mSearch with Event ID\u001B[0m");  
            System.out.println("[\u001B[35m3\u001B[0m] \u001B[35mCancel Order\u001B[0m");  
            System.out.println("[\u001B[31m4\u001B[0m] \u001B[31mExit\u001B[0m"); 
            
            System.out.print("\nPlease enter an option: ");
            

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option.");
                scanner.nextLine(); // Clears bad input
            }

            switch (choice) {
                case 1:
                    System.out.println("\n---------------------------------");
                    System.out.println("Enter an event type to see available events:");
                    System.out.println("\n[\u001B[33m1\u001B[0m] \u001B[33mSport\u001B[0m");
                    System.out.println("[\u001B[33m2\u001B[0m] \u001B[33mConcert\u001B[0m");
                    System.out.println("[\u001B[33m3\u001B[0m] \u001B[33mFestival\u001B[0m");
                    System.out.println("[\u001B[33m4\u001B[0m] \u001B[33mSee all events\u001B[0m");
                    System.out.print("\nPlease enter an option: ");

                    try {
                        eventTypeChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid option.");
                        scanner.nextLine(); // Clears bad input
                    }

                    switch (eventTypeChoice) {
                        case 1:
                            scanner.nextLine();
                            Event.showEventsByType(events, "Sport");
                            buyTickets(scanner, events, selectedCustomer);
                            break;
                        case 2:
                            Event.showEventsByType(events, "Concert");
                            buyTickets(scanner, events, selectedCustomer);
                            break;
                        case 3:
                            Event.showEventsByType(events, "Festival");
                            buyTickets(scanner, events, selectedCustomer);
                            break;
                        case 4:
                            printEventsSortedByID(events);
                            buyTickets(scanner, events, selectedCustomer);
                            break;
                        default:
                            System.out.println("\n\u001B[31mInvalid option. Please try again.\u001B[0m");
                            System.out.println("\n---------------------------------");
                            System.out.println("Enter an event type to see available events:");
                            System.out.println("\n[1] Sport");
                            System.out.println("[2] Concert");
                            System.out.println("[3] Festival");
                            System.out.println("[4] See all events");
                            System.out.print("\nPlease enter an option: ");
                    }
                    break;

                case 2:
                    scanner.nextLine();
                    buyTickets(scanner, events, selectedCustomer);
                    ActionLogger.logInfo("Customer " + selectedCustomer.getFirstName() + " bought tickets");
                    break;

                case 3:
                    cancelTicketPurchase(scanner, selectedCustomer, events);
                    ActionLogger.logInfo( "Customer " + selectedCustomer.getFirstName() + " canceled a purchase");
                    break;
                case 4: 
                    System.out.println("\nExiting... Thank you! " + selectedCustomer.getFirstName());
                    ActionLogger.logInfo( "Customer " + selectedCustomer.getFirstName() + " logged out");
                    customerMenuRunning = false;           
                    break;
                default:
                    System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
                    break;
            }
        }
    }


     /**
     * Allows a customer to cancel a previously made ticket purchase, providing a list of their purchases for selection.
     * Displays purchase details and prompts the user to choose a purchase to cancel. Refunds are processed, and relevant
     * records are updated, including customer balance, purchase history, and event revenues.
     *
     * @param scanner   Scanner object for user input.
     * @param customer  The customer initiating the cancellation.
     * @param events    The list of events containing details about available tickets and revenues.
     */
    private static void cancelTicketPurchase(Scanner scanner, Customer customer, List<Event> events) {

        int intTicketType = 0;
        Boolean endCancelMenu = false;

        while (!endCancelMenu){
        
            System.out.println("\u001B[34m\n┌─────────────────────────────────┐");
            System.out.println("│     CANCEL TICKET PURCHASE      │");
            System.out.println("└─────────────────────────────────┘\u001B[0m");
            
        
        // Display the list of the customer's purchases
        List<Map<String, Object>> customerPurchases = InvoiceGenerator.getCustomerPurchaseHistory(customer);
    
        if (customerPurchases.isEmpty()) {
            System.out.println("\u001B[33mYou have no ticket purchases to cancel.\u001B[0m");
            return;
        }
        
        System.out.println("Select a ticket purchase to cancel:");
    
        int purchaseIndex = 1;
        for (Map<String, Object> purchase : customerPurchases) {
            System.out.println("\u001B[33m[" + purchaseIndex + "]\u001B[0m");
            System.out.println("Event: " + purchase.get("Event Name"));
            System.out.println("Date: " + purchase.get("Event Date"));
            System.out.println("Ticket Type: " + purchase.get("Ticket Type"));
            System.out.println("Number of Tickets: " + purchase.get("Number Of Tickets"));
            System.out.println("Total Price: " + purchase.get("Total Price"));
            System.out.println("Confirmation Number: " + purchase.get("Confirmation Number"));
            System.out.println();
            System.out.println(); // Empty line between purchases
            purchaseIndex++;
        }
    
        System.out.println("\u001B[31m--> Disclaimer: Fees will not be refunded <--\u001B[0m");
        System.out.print("\u001B[33mEnter the number corresponding to the purchase you want to cancel (or 0 to go back): \u001B[0m");
        int cancelChoice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        
        if (cancelChoice == 0) {
            System.out.println("Exiting Cancel order menu...");
            endCancelMenu = true;
            break; // Use break to exit the loop without leaving the method
        }
        
        if (cancelChoice < 0 || cancelChoice > customerPurchases.size()) {
            System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
            // Use continue to go back to the start of the loop for a new selection
            continue;
        }
    
        // Retrieve the selected purchase details (adjusting for zero-based indexing)
        Map<String, Object> selectedPurchase = customerPurchases.get(cancelChoice - 1);
        String confirmationNumber = (String) selectedPurchase.get("Confirmation Number");


        // Number Of Tickets
        int numberOfTickets = (int) selectedPurchase.get("Number Of Tickets");

        // Event Name
        String eventName = (String) selectedPurchase.get("Event Name");

        // Subtotal
        double subtotal = 0.0;
        Object subtotalObject = selectedPurchase.get("Subtotal");
        if (subtotalObject instanceof String) {
            String subtotalStr = (String) subtotalObject;
            subtotal = Double.parseDouble(subtotalStr.replace("$", ""));
        } else if (subtotalObject instanceof Double) {
            subtotal = (Double) subtotalObject;
        }

        Object ticketType = selectedPurchase.get("Ticket Type"); // Assuming selectedPurchase is a Map or similar structure

        if (ticketType instanceof Integer) {
             intTicketType = (int) ticketType; // If it's an Integer, cast it to int
            // Further operations with intTicketType as an integer
        }
        else if (ticketType instanceof String) {
            String strTicketType = (String) ticketType;
            
            switch (strTicketType) {
            
            case "General Admission":
                intTicketType = 1;
                break;
            case "Bronze":
                intTicketType = 2;
                break;
            case "Silver":
                intTicketType = 3;
                break;
            case "Gold":
                intTicketType = 4;
                break;
            case "VIP":
                intTicketType = 5;
                break;
        }
    }
    
        // Update the customer's balance
        customer.setMoneyAvailable(customer.getMoneyAvailable() + subtotal);
    
        // Remove the canceled purchase from the customer's purchase history
        //InvoiceGenerator.removePurchaseFromHistory(customer, confirmationNumber);
        // Additional line to cancel the order and update the invoice
        InvoiceGenerator.cancelOrderAndUpdateInvoice(customer, confirmationNumber);
        Event event = findEventByName(events, eventName);

        //Subtract money from event revenues
        fixRevenues(customer, events, intTicketType, numberOfTickets, event);
    
        System.out.println("Ticket purchase cancelled successfully.");
        }
    } //  end of method


     /**
     * Adjusts the revenues and seat counts for a specific ticket type in an event based on a customer's purchase.
     *
     * @param customer  The customer making the purchase.
     * @param events    The list of events.
     * @param ticketType The type of the ticket (1 for General Admission, 2 for Bronze, 3 for Silver, 4 for Gold, 5 for VIP).
     * @param amount    The number of tickets purchased.
     * @param event     The event for which the tickets are purchased.
     */
    public static void fixRevenues(Customer customer, List<Event> events, int ticketType, int amount, Event event) {
        double sub = 0.0;
        double discounts = 0.0;
    
        if (ticketType == 1) {

                sub = event.getGeneralAdmissionPrice() * amount;

            event.getVenue().incrementGeneralAdmSeatsSold(-amount);
            event.getVenue().decrementTotalRevenueGeneralAdm(-sub);

        } else if (ticketType == 2) {
            if (customer.getIsMember()) {
                sub = event.getBronzePrice() * amount * 0.9;
                discounts = event.getBronzePrice() * amount * 0.1;
            } else {
                sub = event.getBronzePrice() * amount;
            }
            event.getVenue().incrementBronzeSeatsSold(-amount);
            event.getVenue().decrementTotalRevenueBronze(-sub);

        } else if (ticketType == 3) {
            if (customer.getIsMember()) {
                sub = event.getSilverPrice() * amount * 0.9;
                discounts = event.getSilverPrice() * amount * 0.1;
            } else {
                sub = event.getSilverPrice() * amount;
            }
            event.getVenue().incrementSilverSeatsSold(-amount);
            event.getVenue().decrementTotalRevenueSilver(-sub);

        } else if (ticketType == 4) {
            if (customer.getIsMember()) {
                sub = event.getGoldPrice() * amount * 0.9;
                discounts = event.getGoldPrice() * amount * 0.1;
            } else {
                sub = event.getGoldPrice() * amount;
            }
            event.getVenue().incrementGoldSeatsSold(-amount);
            event.getVenue().decrementTotalRevenueGold(-sub);

        } else if (ticketType == 5) {
            if (customer.getIsMember()) {
                sub = event.getVipPrice() * amount * 0.9;
                discounts = event.getVipPrice() * amount * 0.1;
            } else {
                sub = event.getVipPrice() * amount;
            }
            event.getVenue().incrementVIPSeatsSold(-amount);
            event.getVenue().decrementTotalRevenueVIP(-sub);
        }
    
        // Common updates for all ticket types
        event.getVenue().decrementTotalRevenue(sub);
        event.getVenue().decrementDiscounts(discounts);
    }
    

    public static Event findEventByName(List<Event> events, String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return null; // Event not found
    }
    

    /**
     * Facilitates the process of buying tickets for events.
     *
     * @param scanner  A `Scanner` object for user input.
     * @param events   A list of available events.
     * @param customer The customer making the ticket purchase.
     */
    public static void buyTickets(Scanner scanner, List<Event> events, Customer customer) {
        boolean wantToPurchase = true;
        List<Map<String, Object>> allPurchases = new ArrayList<>(); // Initialize the list to store all purchases
    
        while (wantToPurchase) {
            System.out.print("\nSelect an Event ID to choose the event (type 0 to go back): ");
            int eventID;
    
            try {
                eventID = scanner.nextInt();
                scanner.nextLine();
    
                // Check if the user wants to go back
                if (eventID == 0) {
                    System.out.println("\nGoing back to the main menu...");
                    break;
                }
    
                Event event = events.stream()
                        .filter(e -> e.getEventID() == eventID)
                        .findFirst()
                        .orElse(null);
    
                if (event == null) {
                    System.out.println("Event not found.");
                    continue;
                }
    
                int seatsUnavailable = (int) (((double) event.getVenue().getPctSeatsUnavailable() / 100.0) * event.getVenue().getCapacity());
                int venueCapacity = event.getVenue().getCapacity();
                int seatsAvailable = venueCapacity - seatsUnavailable;
    
                if (seatsAvailable <= 0) {
                    System.out.println("Sorry, no tickets available for this event.");
                    continue;
                }
    
                event.eventPrices(customer);
    
                System.out.print("\nSelect a Ticket Type you would like to buy: ");
                int ticketType = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("\nEnter the quantity of tickets (up to 6 tickets): ");
                int ticketQuantity = scanner.nextInt();
                scanner.nextLine();
    
                if (ticketQuantity <= 0 || ticketQuantity > 6) {
                    System.out.println("Invalid number of tickets. Please select between 1 and 6 tickets.");
                    continue;
                }
    
                if (customer.getIsMember()) {
                    customer.setPricingStrategy(new MemberPricingStrategy(customer));
                } else {
                    customer.setPricingStrategy(new RegularPricingStrategy());
                }
    
                double subtotal = customer.pricingStrategy.calculateTicketPrice(event, ticketType, ticketQuantity);
                double taxes = (subtotal * 0.0825);
                double total = subtotal + taxes;
    
                double convenienceFee = 2.5;
                double serviceFee = RegularPricingStrategy.getServiceFee(subtotal);
                double charityFee = RegularPricingStrategy.getCharityFee(subtotal);

                if (customer.getIsMember()) {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("\u001B[34mYou are a TicketMiner Member, you will get 10% off.\u001B[0m");
                    System.out.println("\u001B[33mSubtotal would be: $" + Invoice.roundToTwoDecimals(subtotal) + " [Fees included, Tax not included]\u001B[0m");
                    System.out.println("\u001B[33m--> Total would be: $" + Invoice.roundToTwoDecimals(total) + " [Tax & Fees included]\u001B[0m");
                    
                    System.out.println();
                } else {
                    System.out.println("\u001B[33mSubtotal would be: $" + Invoice.roundToTwoDecimals(subtotal) + " [Fees included, Tax not included]\u001B[0m");
                    System.out.println("\u001B[33m--> Total Price would be: $" + Invoice.roundToTwoDecimals(total) + " [Tax & Fees included]\u001B[0m");                    
                    System.out.println();
                }

    
                if (total > customer.getMoneyAvailable()) {
                    System.out.println("\n\u001B[31m*************************************\u001B[0m");
                    System.out.println("\n\u001B[31m*** Insufficient Funds, try again ***\u001B[0m");
                    System.out.println("\n\u001B[31m*************************************\u001B[0m");
                    
                    continue;
                }
    
                System.out.print("\nWould you like to proceed with this purchase? (Yes/No): ");
                String proceed = scanner.nextLine();
    
                if (!proceed.equalsIgnoreCase("yes")) {
                    System.out.println("\nPurchase cancelled. Returning to Main Menu...");
                    return;  // Return to the main menu without processing further
                }
    
                // Proceed with the purchase logic
                String confirmationNumber = ConfirmationNumberGenerator.generateConfirmationNumber(customer, event);
                Invoice invoice = new Invoice(event, ticketType, ticketQuantity, total, confirmationNumber, customer, subtotal, taxes, charityFee, serviceFee, convenienceFee);
                event.getVenue().setConvenienceFee(convenienceFee);
                event.getVenue().setCharityFee(charityFee);
                event.getVenue().setServiceFee(serviceFee);
    
                event.purchaseTickets(ticketType, ticketQuantity, customer);
                // Display invoice to customer
                invoice.displayInvoice();
                ActionLogger.logInfo( "Customer " + customer.getFirstName() + " bought tickets for event " + event.getName());
    
                Map<String, Object> purchase = new HashMap<>();
                purchase.put("Event Type", event.getEventType());
                purchase.put("Event Name", event.getName());
                purchase.put("Event Date", event.getDate());
                purchase.put("Ticket Type", Invoice.getTicketTypeName(ticketType));
                purchase.put("Subtotal", Invoice.roundToTwoDecimals(subtotal));
                purchase.put("Number Of Tickets", ticketQuantity);
                purchase.put("Service Fees", Invoice.roundToTwoDecimals(serviceFee));
                purchase.put("Convenience Fees", Invoice.roundToTwoDecimals(convenienceFee));
                purchase.put("Charity Fees", Invoice.roundToTwoDecimals(charityFee));
                purchase.put("Total Price", Invoice.roundToTwoDecimals(total));
                purchase.put("Confirmation Number", confirmationNumber);
                //allPurchases.add(purchase); // Add the purchase to the list
                customer.addTicketPurchase(purchase);
    
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mInvalid input. Please enter a valid number.\u001B[0m");
                scanner.nextLine();
            }
    
            // Check if the user wants to make another purchase
            System.out.print("\nWould you like to make another purchase? (Yes/No): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                wantToPurchase = false;
                System.out.println("\n*-- Thank you for your purchase " + customer.getFirstName() + " --*");
    
                InvoiceGenerator.generateInvoiceSummary(customer, customer.getTicketPurchases());
                break;
            }
        }
    }
    
    

    /**
     * Automates the ticket purchase process for a specific customer.
     *
     * @param events     A list of available events.
     * @param customers  A list of customer objects.
     * @param firstName  The first name of the customer.
     * @param lastName   The last name of the customer.
     * @param eventID    The ID of the event to purchase tickets for.
     * @param EventName  The name of the event to purchase tickets for.
     * @param ticketQuantity The number of tickets to purchase.
     * @param ticketType The type of ticket to purchase (e.g., "General Admission").
     */
    public static void autoBuyTickets(List<Event> events, List<Customer> customers, String firstName, String lastName, int eventID, String EventName, int ticketQuantity, String ticketType) {
        Event event = null;
        Customer customer = null;
        int intTicketType = 0;
    
        // Find the event
        for (Event e : events) {
            if (e.getEventID() == eventID && e.getName().equals(EventName)) {
                event = e;
                break;
            }
        }
    
        // Find the customer
        for (Customer e : customers) {
            if (e.getFirstName().equals(firstName) && e.getLastName().equals(lastName)) {
                customer = e;
                break;
            }
        }
    
        switch (ticketType) {
            case "General Admission":
                intTicketType = 1;
                break;
            case "Bronze":
                intTicketType = 2;
                break;
            case "Silver":
                intTicketType = 3;
                break;
            case "Gold":
                intTicketType = 4;
                break;
            case "VIP":
                intTicketType = 5;
                break;
        }
    
        // If all details are correct, proceed with the purchase
        if (event != null && customer != null) {
            int seatsUnavailable = (int) (((double) event.getVenue().getPctSeatsUnavailable() / 100.0) * event.getVenue().getCapacity());
            int venueCapacity = event.getVenue().getCapacity();
            int seatsAvailable = venueCapacity - seatsUnavailable;
    
            if (seatsAvailable <= 0 || ticketQuantity <= 0 || ticketQuantity > 6) {
                // Handle the case when seats are unavailable or ticket quantity is invalid
                List<Map<String, Object>> ticketPurchases = new ArrayList<>();
                Map<String, Object> purchase = new HashMap<>();
                purchase.put("Purchase Status", "PURCHASE FAILED (Invalid ticket amount)");
                purchase.put("Event Type", event.getEventType());
                purchase.put("Event Name", event.getName());
                purchase.put("Event Date", event.getDate());
                purchase.put("Ticket Type", ticketType);
                purchase.put("Number Of Tickets", ticketQuantity);
                purchase.put("Total Price", 0.0); // Adjust this accordingly
                purchase.put("Confirmation Number", "N/A");
                ticketPurchases.add(purchase);
                
                
                // Generate the invoice summary
                InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);
                return;
            }
    
            if (customer.getIsMember()) {
                customer.setPricingStrategy(new MemberPricingStrategy(customer));
            } else {
                customer.setPricingStrategy(new RegularPricingStrategy());
            }
    
            double subtotal = customer.pricingStrategy.calculateTicketPrice(event, intTicketType, ticketQuantity);
            double taxes = subtotal * 0.0825;
            double total = subtotal + taxes;
    
            
            double convenienceFee = RegularPricingStrategy.getConvenienceFee();
            double serviceFee = RegularPricingStrategy.getServiceFee(subtotal);
            double charityFee = RegularPricingStrategy.getCharityFee(subtotal);
    
            if ((total) > customer.getMoneyAvailable()) {
                // Handle the case when the customer doesn't have enough funds
                List<Map<String, Object>> ticketPurchases = new ArrayList<>();
                Map<String, Object> purchase = new HashMap<>();
                purchase.put("Purchase Status", "PURCHASE FAILED (Not enough funds available)");
                purchase.put("Event Type", event.getEventType());
                purchase.put("Event Name", event.getName());
                purchase.put("Event Date", event.getDate());
                purchase.put("Ticket Type", ticketType);
                purchase.put("Subtotal", Invoice.roundToTwoDecimals(subtotal));
                purchase.put("Convenience Fee", convenienceFee);
                purchase.put("Service Fee", serviceFee);
                purchase.put("Charity Fee", charityFee);
                purchase.put("Number Of Tickets", ticketQuantity);
                purchase.put("Total Price", total);
                purchase.put("Confirmation Number", "N/A");
                ticketPurchases.add(purchase);
                
    
                // Generate the invoice summary
                InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);
                return;
            }
    
            String confirmationNumber = ConfirmationNumberGenerator.generateConfirmationNumber(customer, event);
            

            event.getVenue().setConvenienceFee(convenienceFee);
            event.getVenue().setCharityFee(charityFee);
            event.getVenue().setServiceFee(serviceFee);
        
            // Modify the method call to use intTicketType instead of ticketType
            event.purchaseTickets(intTicketType, ticketQuantity, customer);
        
            List<Map<String, Object>> ticketPurchases = new ArrayList<>();
            Map<String, Object> purchase = new HashMap<>();
            purchase.put("Purchase Status", "PURCHASE SUCCESSFUL");
            purchase.put("Event Type", event.getEventType());
            purchase.put("Event Name", event.getName());
            purchase.put("Event Date", event.getDate());
            purchase.put("Ticket Type", ticketType);
            purchase.put("Subtotal", Invoice.roundToTwoDecimals(subtotal));
            purchase.put("Convenience Fee", Invoice.roundToTwoDecimals(convenienceFee));
            purchase.put("Service Fee", Invoice.roundToTwoDecimals(serviceFee));
            purchase.put("Charity Fee", Invoice.roundToTwoDecimals(charityFee));
            purchase.put("Number Of Tickets", ticketQuantity);
            purchase.put("Total Price", Invoice.roundToTwoDecimals(total));
            purchase.put("Confirmation Number", confirmationNumber);
            customer.addTicketPurchase(purchase);

    
            // Generate the invoice summary with the accumulated purchase details
            InvoiceGenerator.generateInvoiceSummaryAuto(customer, customer.getTicketPurchases());

        }
    }

    /**
     * Prints information about events, sorted by their IDs in ascending order.
     * Utilizes the natural order of event IDs to sort the events before displaying them.
     *
     * @param events The list of events to be printed.
     */
    public static void printEventsSortedByID(List<Event> events) {
        // Sort the events based on their IDs before displaying
        List<Event> sortedEvents = events.stream()
                .sorted(Comparator.comparingInt(Event::getEventID))
                .collect(Collectors.toList());

        // Display the sorted events
        for (Event event : sortedEvents) {
            event.printInfo();
        }
    }

}