import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
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
            System.out.println("\n_____M_A_I_N___M_E_N_U_____");
            System.out.println("[1] Look events by type");
            System.out.println("[2] Search with Event ID");
            System.out.println("[3] Cancel Order");
            System.out.println("[4] Exit");
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
                    System.out.println("\n[1] Sport");
                    System.out.println("[2] Concert");
                    System.out.println("[3] Festival");
                    System.out.println("[4] See all events");
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
                            System.out.println("Invalid option. Please try again.");
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
                    break;

                case 3:
                cancelTicketPurchase(scanner, selectedCustomer, events);
                            break;
                case 4: 
                    System.out.println("\nExiting. Thank you!\n");
                    customerMenuRunning = false;           
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }


    private static void cancelTicketPurchase(Scanner scanner, Customer customer, List<Event> events) {
        boolean endCancelMenu = false;
        
        while (!endCancelMenu) {
            System.out.println("\n----- CANCEL TICKET PURCHASE -----");
    
            // Display the list of the customer's purchases
            List<Map<String, Object>> customerPurchases = InvoiceGenerator.getCustomerPurchaseHistory(customer);
    
            if (customerPurchases.isEmpty()) {
                System.out.println("You have no ticket purchases to cancel.");
                return;
            }
    
            System.out.println("Select a ticket purchase to cancel:");
    
            int purchaseIndex = 1;
            for (Map<String, Object> purchase : customerPurchases) {
                System.out.println("[" + purchaseIndex + "]");
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
    
            System.out.println("Disclaimer: Fees will not be refunded");
            System.out.print("Enter the number corresponding to the purchase you want to cancel (or 0 to go back): ");
            int cancelChoice;
            
            try {
                cancelChoice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }
    
            if (cancelChoice == 0) {
                System.out.println("Exiting Cancel order menu...");
                endCancelMenu = true;
                break; // Use break to exit the loop without leaving the method
            }
    
            if (cancelChoice < 0 || cancelChoice > customerPurchases.size()) {
                System.out.println("Invalid selection. Please try again.");
                // Use continue to go back to the start of the loop for a new selection
                continue;
            }
    
            // Retrieve the selected purchase details (adjusting for zero-based indexing)
            Map<String, Object> selectedPurchase = customerPurchases.get(cancelChoice - 1);
            String confirmationNumber = (String) selectedPurchase.get("Confirmation Number");
            String eventName = (String) selectedPurchase.get("Event Name");
            int ticketType = (int) selectedPurchase.get("Ticket Type");
            int amount = (int) selectedPurchase.get("Number Of Tickets");
            double costForEachTicket = (double) selectedPurchase.get("Subtotal");
            double charity = (double) selectedPurchase.get("Charity Fees");
            double service = (double) selectedPurchase.get("Service Fees");
            double convenience = (double) selectedPurchase.get("Convenience Fees");

            double subtotal = costForEachTicket - charity - service - convenience;
    
            // Find the corresponding event using the event name
            Event canceledEvent = findEventByName(events, eventName);
    
            if (canceledEvent != null) {
    
                // Update the customer's balance
                customer.setMoneyAvailable(customer.getMoneyAvailable() + subtotal);
                //fixRevenues(customer, events, ticketType, amount, canceledEvent);
    
                // Remove the canceled purchase from the customer's purchase history
                InvoiceGenerator.removePurchaseFromHistory(customer, confirmationNumber);
    
                System.out.println("\nYou canceled your ticket for " + canceledEvent.getName());
            } else {
                System.out.println("Event not found for the canceled purchase.");
            }
            
        }
    }

    // private static void fixRevenues(Customer customer, List<Event> events, int ticketType, int amount, Event event){
    //     double sub = 0.0;
    //     double discounts = 0.0;
    //     if (ticketType == 1) {
    //         if (customer.getIsMember()) {
    //             sub = event.getGeneralAdmissionPrice() * amount * 0.9; // Apply 10% discount for members
    //             discounts = event.getGeneralAdmissionPrice() * amount * 0.1; // Calculate the discount
    //         } else {
    //             sub = event.getGeneralAdmissionPrice() * amount;
    //         }
    //         event.getVenue().decrementGeneralAdmSeatsSold(amount);
    //         event.getVenue().decrementTotalRevenueGeneralAdm(sub);
    //         event.getVenue().decrementGeneralAdmSeatsSold(amount);
    //         event.getVenue().decrementTotalRevenueGeneralAdm(sub);
    //         event.getVenue().decrementTotalRevenue(sub);
    //         event.getVenue().decrementDiscounts(discounts);
    //     }
    //     if( ticketType == 2){
    //                     if(customer.getIsMember()){
    //             sub = (event.getBronzePrice() * 0.9) * amount;
    //             discounts = (event.getBronzePrice() * 0.1);
    //         }else{sub = (event.getBronzePrice()) * amount;}
    //         event.getVenue().decrementBronzeAdmSeatsSold(amount);
    //         event.getVenue().decrementTotalRevenueBronze(sub);
    //         event.getVenue().decrementTotalRevenue(sub);
    //         event.getVenue().decrementDiscounts(discounts);
    //     }
    //     if( ticketType == 3){
    //                     if(customer.getIsMember()){
    //             sub = (event.getSilverPrice() * 0.9) * amount;
    //             discounts = (event.getSilverPrice() * 0.1);
    //         }else{sub = (event.getSilverPrice()) * amount;}
    //         event.getVenue().decrementSilverAdmSeatsSold(amount);
    //         event.getVenue().decrementTotalRevenueSilver(sub);
    //         event.getVenue().decrementTotalRevenue(sub);
    //         event.getVenue().decrementDiscounts(discounts);
    //     }
    //     if( ticketType == 4){
    //                     if(customer.getIsMember()){
    //             sub = (event.getGoldPrice() * 0.9) * amount;
    //             discounts = (event.getGoldPrice() * 0.1);
    //         }else{sub = (event.getGoldPrice()) * amount;}
    //         event.getVenue().decrementGoldAdmSeatsSold(amount);
    //         event.getVenue().decrementTotalRevenueGold(sub);
    //         event.getVenue().decrementTotalRevenue(sub);
    //         event.getVenue().decrementDiscounts(discounts);
    //     }
    //     if( ticketType == 5){
    //                     if(customer.getIsMember()){
    //             sub = (event.getVipPrice() * 0.9) * amount;
    //             discounts = (event.getVipPrice() * 0.1);
    //         }else{sub = (event.getVipPrice()) * amount;}
    //         event.getVenue().decrementVIPAdmSeatsSold(amount);
    //         event.getVenue().decrementTotalRevenueVIP(sub);
    //         event.getVenue().decrementTotalRevenue(sub);
    //         event.getVenue().decrementDiscounts(discounts);
    //     }
    
    // }

    private static Event findEventByName(List<Event> events, String eventName) {
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
            boolean isValidEvent = false;
    
            while (!isValidEvent) {
                try {
                    System.out.print("\nSelect an Event ID to choose the event: ");
                    int eventID = scanner.nextInt();
                    scanner.nextLine();

                    Event event = null;

                    for (Event e : events) {
                        if (e.getEventID() == eventID) {
                            event = e;
                            break;
                        }
                    }

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
                        return;
                    }

                    if (customer.getIsMember()) {
                        customer.setPricingStrategy(new MemberPricingStrategy(customer));
                    } else {
                        customer.setPricingStrategy(new RegularPricingStrategy());
                    }

                    double subtotal = customer.pricingStrategy.calculateTicketPrice(event, ticketType, ticketQuantity);
                    double taxes = (subtotal * 0.0825);
                    double total = subtotal + taxes;

                    // Example usage in the buyTickets method
                    double convenienceFee = 2.5;
                    double serviceFee = RegularPricingStrategy.getServiceFee(subtotal);
                    double charityFee = RegularPricingStrategy.getCharityFee(subtotal);


                    if (customer.getIsMember()) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("You are a TicketMiner Member, you will get 10% off.");
                        System.out.println("\nSubtotal would be: $" + Invoice.roundToTwoDecimals(subtotal) + " [Fees included, Tax not included]");
                        System.out.println("--> Total would be: $" + Invoice.roundToTwoDecimals(total) + " [Tax & Fees included]");
                        System.out.println();
                    } else {
                        System.out.println("\nSubtotal would be: $" + Invoice.roundToTwoDecimals(subtotal) + " [Fees included, Tax not included]");
                        System.out.println("--> Total Price would be: $" + Invoice.roundToTwoDecimals(total) + " [Tax & Fees included]");
                        System.out.println();
                    }

                    if ((total) > customer.getMoneyAvailable()) {
                        System.out.println("\n*************************************");
                        System.out.println("\n*** Insufficient Funds, try again ***");
                        System.out.println("\n*************************************");
                        continue;
                    }

                    System.out.print("\nWould you like to proceed with this purchase? (Yes/No): ");
                    String proceed = scanner.nextLine();

                    if (proceed.equalsIgnoreCase("yes")) {
                        String confirmationNumber = ConfirmationNumberGenerator.generateConfirmationNumber(customer,event);
                        Invoice invoice = new Invoice(event, ticketType, ticketQuantity, total, confirmationNumber, customer, subtotal, taxes, charityFee, serviceFee, convenienceFee);
                        event.getVenue().setConvenienceFee(convenienceFee);
                        event.getVenue().setCharityFee(charityFee);
                        event.getVenue().setServiceFee(serviceFee);

                        event.purchaseTickets(ticketType, ticketQuantity, customer);

                        invoice.displayInvoice();

                        Map<String, Object> purchase = new HashMap<>();
                    purchase.put("Event Type", event.getEventType());
                    purchase.put("Event Name", event.getName());
                    purchase.put("Event Date", event.getDate());
                    purchase.put("Ticket Type", ticketType);
                    purchase.put("Subtotal", Invoice.roundToTwoDecimals(subtotal));
                    purchase.put("Number Of Tickets", ticketQuantity);
                    purchase.put("Service Fees", Invoice.roundToTwoDecimals(serviceFee));
                    purchase.put("Convenience Fees", Invoice.roundToTwoDecimals(convenienceFee));
                    purchase.put("Charity Fees", Invoice.roundToTwoDecimals(charityFee));
                    purchase.put("Total Price", Invoice.roundToTwoDecimals(total));
                    purchase.put("Confirmation Number", confirmationNumber);
                    allPurchases.add(purchase); // Add the purchase to the list

                    isValidEvent = true;
                } else {
                    System.out.println("\nPurchase cancelled. Returning to the event selection.");
                    isValidEvent = true;
                }

                System.out.print("\nWould you like to make another purchase? (Yes/No): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    wantToPurchase = false;
                    System.out.println("\nExiting... Thank you!\n");

                    InvoiceGenerator.generateInvoiceSummary(customer, allPurchases);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
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
                purchase.put("purchase status", "PURCHASE FAILED (Invalid ticket amount)");
                purchase.put("eventType", event.getEventType());
                purchase.put("eventName", event.getName());
                purchase.put("eventDate", event.getDate());
                purchase.put("ticketType", ticketType);
                purchase.put("numberOfTickets", ticketQuantity);
                purchase.put("totalPrice", 0.0); // Adjust this accordingly
                purchase.put("confirmationNumber", "N/A");
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
    
            // Example usage in the buyTickets method
            double convenienceFee = RegularPricingStrategy.getConvenienceFee();
            double serviceFee = RegularPricingStrategy.getServiceFee(subtotal);
            double charityFee = RegularPricingStrategy.getCharityFee(subtotal);
    
            if ((total) > customer.getMoneyAvailable()) {
                // Handle the case when the customer doesn't have enough funds
                List<Map<String, Object>> ticketPurchases = new ArrayList<>();
                Map<String, Object> purchase = new HashMap<>();
                purchase.put("purchase status", "PURCHASE FAILED (Not enough funds available)");
                purchase.put("eventType", event.getEventType());
                purchase.put("eventName", event.getName());
                purchase.put("eventDate", event.getDate());
                purchase.put("ticketType", ticketType);
                purchase.put("subtotal", Invoice.roundToTwoDecimals(subtotal));
                purchase.put("convenienceFee", convenienceFee);
                purchase.put("serviceFee", serviceFee);
                purchase.put("charityFee", charityFee);
                purchase.put("numberOfTickets", ticketQuantity);
                purchase.put("totalPrice", total);
                purchase.put("confirmationNumber", "N/A");
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
            purchase.put("purchase status", "PURCHASE SUCCESSFUL");
            purchase.put("eventType", event.getEventType());
            purchase.put("eventName", event.getName());
            purchase.put("eventDate", event.getDate());
            purchase.put("ticketType", ticketType);
            purchase.put("subtotal", Invoice.roundToTwoDecimals(subtotal));
            purchase.put("convenienceFee", Invoice.roundToTwoDecimals(convenienceFee));
            purchase.put("serviceFee", Invoice.roundToTwoDecimals(serviceFee));
            purchase.put("charityFee", Invoice.roundToTwoDecimals(charityFee));
            purchase.put("numberOfTickets", ticketQuantity);
            purchase.put("totalPrice", Invoice.roundToTwoDecimals(total));
            purchase.put("confirmationNumber", confirmationNumber);
            ticketPurchases.add(purchase);
    
            // Generate the invoice summary with the accumulated purchase details
            InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);

        }
    }

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