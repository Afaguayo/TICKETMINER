import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

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

        while (choice <= 0 || choice > 3) {
            System.out.println("\n_____M_A_I_N___M_E_N_U_____");
            System.out.println("[1] Look events by type");
            System.out.println("[2] Search with Event ID");
            System.out.println("[3] Exit");
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
                    System.out.println("\nExiting. Thank you!\n");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
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

                    double totalPrice = customer.pricingStrategy.calculateTicketPrice(event, ticketType, ticketQuantity);
                    double taxes = (totalPrice * 0.0825);

                    if (customer.getIsMember()) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("You are a TicketMiner Member, you will get 10% off.");
                        System.out.println("\nSubtotal would be: $" + Invoice.roundToTwoDecimals(totalPrice) + " [Tax not included]");
                        System.out.println("--> Total would be: $" + Invoice.roundToTwoDecimals(totalPrice + taxes) + " [Tax included]");
                        System.out.println();
                    } else {
                        System.out.println("\nSubtotal would be: $" + Invoice.roundToTwoDecimals(totalPrice) + " [Tax not included]");
                        System.out.println("--> Total Price would be: $" + Invoice.roundToTwoDecimals(totalPrice + taxes) + " [Tax included]");
                        System.out.println();
                    }

                    if ((totalPrice + taxes) > customer.getMoneyAvailable()) {
                        System.out.println("\n*************************************");
                        System.out.println("\n*** Insufficient Funds, try again ***");
                        System.out.println("\n*************************************");
                        continue;
                    }

                    System.out.print("Would you like to proceed with this purchase? (Yes/No): ");
                    String proceed = scanner.nextLine();

                    if (proceed.equalsIgnoreCase("yes")) {
                        String confirmationNumber = ConfirmationNumberGenerator.generateConfirmationNumber(customer);
                        Invoice invoice = new Invoice(event, ticketType, ticketQuantity, totalPrice, confirmationNumber, customer, taxes);

                        switch (ticketType) {
                            case 1:
                                events.get(eventID - 1).getVenue().incrementGeneralAdmSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueGeneralAdm(events.get(eventID - 1).getGeneralAdmissionPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                                break;

                            case 2:
                                events.get(eventID - 1).getVenue().incrementBronzeSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueBronze(events.get(eventID - 1).getBronzePrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                                break;
                            case 3:
                                events.get(eventID - 1).getVenue().incrementSilverSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueSilver(events.get(eventID - 1).getSilverPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                                break;

                            case 4:
                                events.get(eventID - 1).getVenue().incrementGoldSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueGold(events.get(eventID - 1).getGoldPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                                break;

                            case 5:
                                events.get(eventID - 1).getVenue().incrementVIPSeatsSold(ticketQuantity);
                                events.get(eventID - 1).getVenue().updateRevenueVIP(events.get(eventID - 1).getVipPrice(), ticketQuantity, customer);
                                customer.setTransactionCount(ticketQuantity);
                                customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                                break;

                            default:
                                break;
                        }

                        invoice.displayInvoice();

                        Map<String, Object> purchase = new HashMap<>();
                        purchase.put("eventType", event.getEventType());
                        purchase.put("eventName", event.getName());
                        purchase.put("eventDate", event.getDate());
                        purchase.put("ticketType", ticketType);
                        purchase.put("numberOfTickets", ticketQuantity);
                        purchase.put("totalPrice", Invoice.roundToTwoDecimals(totalPrice + taxes));
                        purchase.put("confirmationNumber", confirmationNumber);
                        allPurchases.add(purchase); // Add the purchase to the list

                        isValidEvent = true;
                    } else {
                        System.out.println("Purchase cancelled. Returning to the event selection.");
                        isValidEvent = true;
                    }

                    System.out.print("Would you like to make another purchase? (Yes/No): ");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("no")) {
                        wantToPurchase = false;
                        System.out.println("\nExiting. Thank you!\n");
                        break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
            }
        }//Updated by Javier to allow multiple ticket purchases
        // in the same invoice.
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

        }// end of switch


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

        double totalPrice = customer.pricingStrategy.calculateTicketPrice(event, intTicketType, ticketQuantity);
        double taxes = (totalPrice * 0.0825);

        if ((totalPrice + taxes) > customer.getMoneyAvailable()) {
            // Handle the case when the customer doesn't have enough funds
            List<Map<String, Object>> ticketPurchases = new ArrayList<>();
            Map<String, Object> purchase = new HashMap<>();
            purchase.put("purchase status", "PURCHASE FAILED (Not enough funds available)");
            purchase.put("eventType", event.getEventType());
            purchase.put("eventName", event.getName());
            purchase.put("eventDate", event.getDate());
            purchase.put("ticketType", ticketType);
            purchase.put("numberOfTickets", ticketQuantity);
            purchase.put("totalPrice", totalPrice);
            purchase.put("confirmationNumber", "N/A");
            ticketPurchases.add(purchase);

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
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                break;

                case 2:
                    events.get(eventID - 1).getVenue().incrementBronzeSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueBronze(events.get(eventID - 1).getBronzePrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                break;

                case 3:
                    events.get(eventID - 1).getVenue().incrementSilverSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueSilver(events.get(eventID - 1).getSilverPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                    break;

                case 4:
                    events.get(eventID - 1).getVenue().incrementGoldSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueGold(events.get(eventID - 1).getGoldPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                break;

                case 5:
                    events.get(eventID - 1).getVenue().incrementVIPSeatsSold(ticketQuantity);
                    events.get(eventID - 1).getVenue().updateRevenueVIP(events.get(eventID - 1).getVipPrice(), ticketQuantity, customer);
                    customer.setTransactionCount(ticketQuantity);
                    customer.setMoneyAvailable(customer.getMoneyAvailable() - (totalPrice + taxes));
                break;

                default:
                break;
                    
            }// end of switch


        List<Map<String, Object>> ticketPurchases = new ArrayList<>();
        Map<String, Object> purchase = new HashMap<>();
        purchase.put("purchase status", "PURCHASE SUCCESSFUL");
        purchase.put("eventType", event.getEventType());
        purchase.put("eventName", event.getName());
        purchase.put("eventDate", event.getDate());
        purchase.put("ticketType", ticketType);
        purchase.put("numberOfTickets", ticketQuantity);
        purchase.put("totalPrice", Invoice.roundToTwoDecimals(totalPrice + taxes));
        purchase.put("confirmationNumber", confirmationNumber);
        ticketPurchases.add(purchase);

        // Generate the invoice summary with the accumulated purchase details
        InvoiceGenerator.generateInvoiceSummaryAuto(customer, ticketPurchases);

        }// end of if statement
    }// end of method
}