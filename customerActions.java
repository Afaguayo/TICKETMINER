import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
        boolean terminateMenu = false;

        while (!terminateMenu) {

            if (choice <= 0 || choice > 4){
                System.out.println("Please enter a valid number choice");
                System.out.println();
            }

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

                            events.sort(Comparator.comparingInt(Event::getEventID));

                            for (Event event : events) {
                                event.printInfo();
                            }
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
                    cancelTicketPurchase(scanner, selectedCustomer);
                    break;

                case 4:
                    terminateMenu = true;
                    System.out.println("\nExiting. Thank you!\n");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    private static void cancelTicketPurchase(Scanner scanner, Customer customer) {
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
            System.out.println("Event: " + purchase.get("eventName"));
            System.out.println("Date: " + purchase.get("eventDate"));
            System.out.println("Ticket Type: " + purchase.get("ticketType"));
            System.out.println("Number of Tickets: " + purchase.get("numberOfTickets"));
            System.out.println("Total Price: " + purchase.get("totalPrice"));
            System.out.println("Confirmation Number: " + purchase.get("confirmationNumber"));
            System.out.println();
            purchaseIndex++;
        }
    
        System.out.print("Enter the number corresponding to the purchase you want to cancel (or 0 to go back): ");
        int cancelChoice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    
        if (cancelChoice < 0 || cancelChoice > customerPurchases.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }
    
        if (cancelChoice == 0) {
            System.out.println("Cancelling operation. Returning to the main menu.");
            return;
        }
    
        // Retrieve the selected purchase details
        Map<String, Object> selectedPurchase = customerPurchases.get(cancelChoice - 1);
        String confirmationNumber = (String) selectedPurchase.get("confirmationNumber");
    
        // Calculate the refund amount (excluding fees)
        String totalPriceStr = ((String) selectedPurchase.get("totalPrice")).replace("$", "");
        double refundAmount = Double.parseDouble(totalPriceStr);        
        // Update the customer's balance
        customer.setMoneyAvailable(customer.getMoneyAvailable() + refundAmount);
    
        // Remove the canceled purchase from the customer's purchase history
        InvoiceGenerator.removePurchaseFromHistory(customer, confirmationNumber);
        // Additional line to cancel the order and update the invoice
        InvoiceGenerator.cancelOrderAndUpdateInvoice(customer, confirmationNumber);

    
        System.out.println("Ticket purchase cancelled successfully.");
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
        List<Map<String, Object>> allPurchases = new ArrayList<>();
        
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
        
                    double ticketPrice = customer.pricingStrategy.calculateTicketPrice(event, ticketType, ticketQuantity);
        
                    // Calculate fees
                    double convenienceFee = 2.50;
                    double serviceFee = 0.005 * ticketQuantity * ticketPrice;
                    double charityFee = 0.0075 * ticketQuantity * ticketPrice;
        
                    // Format the fees as currency strings
                    String formattedConvenienceFee = String.format("$%.2f", convenienceFee);
                    String formattedServiceFee = String.format("$%.2f", serviceFee);
                    String formattedCharityFee = String.format("$%.2f", charityFee);
        
                    double subtotal = ticketPrice + convenienceFee + serviceFee + charityFee;
                    double taxes = subtotal * 0.0825;
                    double total = subtotal + taxes;

                    if (customer.getIsMember()) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("You are a TicketMiner Member, you will get 10% off.");
                        System.out.println("\nSubtotal would be: $" + Invoice.roundToTwoDecimals(subtotal) + " [Fees included, Tax not included]");
                        System.out.println("--> Total would be: $" + Invoice.roundToTwoDecimals(total + taxes) + " [Tax & Fees included]");
                        System.out.println();
                    } else {
                        System.out.println("\nSubtotal would be: $" + Invoice.roundToTwoDecimals(subtotal) + " [Tax not included]");
                        System.out.println("--> Total Price would be: $" + Invoice.roundToTwoDecimals(total + taxes) + " [Tax included]");
                        System.out.println();
                    }

        
                    if (total > customer.getMoneyAvailable()) {
                        System.out.println("\n*************************************");
                        System.out.println("\n*** Insufficient Funds, try again ***");
                        System.out.println("\n*************************************");
                        continue;
                    }
        
                    System.out.print("Would you like to proceed with this purchase? (Yes/No): ");
                    String proceed = scanner.nextLine();
        
                    if (proceed.equalsIgnoreCase("yes")) {
                        String confirmationNumber = ConfirmationNumberGenerator.generateConfirmationNumber(customer);
                        Invoice invoice = new Invoice(event, ticketType, ticketQuantity, total, confirmationNumber, customer, taxes);

                        switch (ticketType) {
                            case 1:
                                events.get(eventID - 1).getVenue().incrementGeneralAdmSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueGeneralAdm(events.get(eventID - 1).getGeneralAdmissionPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (total + taxes));
                                break;

                            case 2:
                                events.get(eventID - 1).getVenue().incrementBronzeSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueBronze(events.get(eventID - 1).getBronzePrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (total + taxes));
                                break;
                            case 3:
                                events.get(eventID - 1).getVenue().incrementSilverSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueSilver(events.get(eventID - 1).getSilverPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (total + taxes));
                                break;

                            case 4:
                                events.get(eventID - 1).getVenue().incrementGoldSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueGold(events.get(eventID - 1).getGoldPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (total + taxes));
                                break;

                            case 5:
                                events.get(eventID - 1).getVenue().incrementVIPSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueVIP(events.get(eventID - 1).getVipPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (total + taxes));
                                break;

                            default:
                                break;
                        }

                        invoice.displayInvoice();
        
                        // Deduct the total amount (including fees) from the customer's balance
                        customer.setMoneyAvailable(customer.getMoneyAvailable() - total);
        
                        // Track the fees collected for this event
                        event.getVenue().incrementConvenienceFeesCollected(convenienceFee);
                        event.getVenue().incrementServiceFeesCollected(serviceFee);
                        event.getVenue().incrementCharityFeesCollected(charityFee);
        
                        isValidEvent = true;
        
                        // Create a map to store purchase details
                        Map<String, Object> purchase = new HashMap<>();
                        purchase.put("purchase status", "PURCHASE SUCCESSFUL");
                        purchase.put("eventType", event.getEventType());
                        purchase.put("eventName", event.getName());
                        purchase.put("eventDate", event.getDate());
                        purchase.put("ticketType", ticketType);
                        purchase.put("numberOfTickets", ticketQuantity);
                        purchase.put("ticketPrice", String.format("$%.2f", ticketPrice));
                        purchase.put("convenienceFee", formattedConvenienceFee);
                        purchase.put("serviceFee", formattedServiceFee);
                        purchase.put("charityFee", formattedCharityFee);
                        purchase.put("totalServiceFees", String.format("$%.2f", convenienceFee + serviceFee + charityFee));
                        purchase.put("subtotal", String.format("$%.2f", subtotal));
                        purchase.put("taxes", String.format("$%.2f", taxes));
                        purchase.put("totalPrice", String.format("$%.2f", total));
                        purchase.put("confirmationNumber", confirmationNumber);
        
                        // Add the purchase details to the list of all purchases
                        allPurchases.add(purchase);
                    } else {
                        System.out.println("Purchase cancelled. Returning to the event selection.");
                        isValidEvent = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
            }
        
            System.out.print("Would you like to make another purchase? (Yes/No): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                wantToPurchase = false;
                System.out.println("\nExiting. Thank you!\n");
                break;
            }
        }
        
        // Generate an invoice summary that includes fee details and all purchase details
        InvoiceGenerator.generateInvoiceSummary(customer, allPurchases);
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
    public static void autoBuyTickets(List<Event> events, List<Customer> customers, String firstName, String lastName, int eventID, String eventName, int ticketQuantity, String ticketType) {
        Event event = null;
        Customer customer = null;
        int intTicketType = 0;
    
        // Find the event
        for (Event e : events) {
            if (e.getEventID() == eventID && e.getName().equals(eventName)) {
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
                purchase.put("convenienceFee", "$0.00");
                purchase.put("serviceFee", "$0.00");
                purchase.put("charityFee", "$0.00");
                purchase.put("totalServiceFees", "$0.00");
                purchase.put("subtotal", "$0.00");
                purchase.put("taxes", "$0.00");
    
                ticketPurchases.add(purchase);
                customer.addTicketPurchase(purchase);
    
                // Generate the invoice summary
                InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);
                return;
            }
    
            if (customer.getIsMember()) {
                customer.setPricingStrategy(new MemberPricingStrategy(customer));
            } else {
                customer.setPricingStrategy(new RegularPricingStrategy());
            }
    
            double totalPrice = customer.pricingStrategy.calculateTicketPrice(event, intTicketType, ticketQuantity);
            double convenienceFee = 2.50;
            double serviceFee = 0.005 * ticketQuantity * totalPrice;
            double charityFee = 0.0075 * ticketQuantity * totalPrice;
            double taxes = (totalPrice * 0.0825);
            double totalFees = convenienceFee + serviceFee + charityFee;
    
            if ((totalPrice + totalFees + taxes) > customer.getMoneyAvailable()) {
                // Handle the case when the customer doesn't have enough funds
                List<Map<String, Object>> ticketPurchases = new ArrayList<>();
                Map<String, Object> purchase = new HashMap<>();
                purchase.put("purchase status", "PURCHASE FAILED (Not enough funds available)");
                purchase.put("eventType", event.getEventType());
                purchase.put("eventName", event.getName());
                purchase.put("eventDate", event.getDate());
                purchase.put("ticketType", ticketType);
                purchase.put("numberOfTickets", ticketQuantity);
                purchase.put("ticketPrice", String.format("$%.2f", totalPrice));
                purchase.put("convenienceFee", String.format("$%.2f", convenienceFee));
                purchase.put("serviceFee", String.format("$%.2f", serviceFee));
                purchase.put("charityFee", String.format("$%.2f", charityFee));
                purchase.put("totalServiceFees", String.format("$%.2f", totalFees));
                purchase.put("subtotal", String.format("$%.2f", totalPrice + totalFees));
                purchase.put("taxes", String.format("$%.2f", taxes));
                purchase.put("totalPrice", String.format("$%.2f", totalPrice + totalFees + taxes));
                purchase.put("confirmationNumber", "N/A");
                ticketPurchases.add(purchase);
                customer.addTicketPurchase(purchase);
    
                // Generate the invoice summary
                InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);
                return;
            }
    
            String confirmationNumber = ConfirmationNumberGenerator.generateConfirmationNumber(customer);
            Invoice invoice = new Invoice(event, intTicketType, ticketQuantity, totalPrice, confirmationNumber, customer, taxes);
    
            switch (intTicketType) {
                case 1:
                    events.get(eventID - 1).getVenue().incrementGeneralAdmSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueGeneralAdm(events.get(eventID - 1).getGeneralAdmissionPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + totalFees + taxes));
                    break;
                case 2:
                    events.get(eventID - 1).getVenue().incrementBronzeSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueBronze(events.get(eventID - 1).getBronzePrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + totalFees + taxes));
                    break;
                case 3:
                    events.get(eventID - 1).getVenue().incrementSilverSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueSilver(events.get(eventID - 1).getSilverPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + totalFees + taxes));
                    break;
                case 4:
                    events.get(eventID - 1).getVenue().incrementGoldSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueGold(events.get(eventID - 1).getGoldPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + totalFees + taxes));
                    break;
                case 5:
                    events.get(eventID - 1).getVenue().incrementVIPSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueVIP(events.get(eventID - 1).getVipPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + totalFees + taxes));
                    break;
                default:
                    break;
            }
    
            List<Map<String, Object>> ticketPurchases = new ArrayList<>();
            Map<String, Object> purchase = new HashMap<>();
            purchase.put("purchase status", "PURCHASE SUCCESSFUL");
            purchase.put("eventType", event.getEventType());
            purchase.put("eventName", event.getName());
            purchase.put("eventDate", event.getDate());
            purchase.put("ticketType", ticketType);
            purchase.put("numberOfTickets", ticketQuantity);
            purchase.put("ticketPrice", String.format("$%.2f", totalPrice));
            purchase.put("convenienceFee", String.format("$%.2f", convenienceFee));
            purchase.put("serviceFee", String.format("$%.2f", serviceFee));
            purchase.put("charityFee", String.format("$%.2f", charityFee));
            purchase.put("totalServiceFees", String.format("$%.2f", totalFees));
            purchase.put("subtotal", String.format("$%.2f", totalPrice + totalFees));
            purchase.put("taxes", String.format("$%.2f", taxes));
            purchase.put("totalPrice", String.format("$%.2f", totalPrice + totalFees + taxes));
            purchase.put("confirmationNumber", confirmationNumber);
    
            ticketPurchases.add(purchase);
            customer.addTicketPurchase(purchase);
    
            // Generate the invoice summary with the accumulated purchase details
            InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);
        }
    }// end of method





    







}