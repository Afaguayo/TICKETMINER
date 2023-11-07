import java.util.List;
import java.util.Scanner;

/**
 * The `RunTicket` class represents the main entry point of the TicketMiner application.
 * It allows customers and administrators to interact with the system and provides options for autopurchase testing.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 * @version 1.4
 * 
 * Class: CS 3331 - Object-Oriented Programming
 * Instructor: Daniel Mejia
 * Programming Assignment 4
 * Honesty Statement: We, Angel, Caleb, Chris, and Javier, completed this work entirely on our own without any outside sources, including peers, experts, online sources, or the like.
 * Lab description - Utilize Object-Oriented programming design and principles to create a system.
 * 
 */
public class RunTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load events and customers from CSV files
        eventDataReader eventDataReader = new eventDataReader();
        List<Event> events = eventDataReader.eventDataStoring("EventListPA4.csv");

        customerDataReader customerDataReader = new customerDataReader();
        List<Customer> customers = customerDataReader.customerDataStoring("CustomerListPA4.csv");

        AutoPurchase autoPurchase = new AutoPurchase(events, customers);
        
        // Singleton instances of the CSV writers
        customerCSV csvCustomer = customerCSV.getInstance("UpdatedCustomerList.csv");
        eventCSV csvEvent = eventCSV.getInstance("UpdatedEventList.csv");

        int userType = 0;
        boolean terminateProgram = false;
        int userChoice = 0;
        boolean terminateAutoBuy = false;

        while (!terminateProgram) {
            System.out.println("\nAre you an admin or Customer? (Select a choice): ");
            System.out.println("---------------------------------");
            System.out.println("[1] Customer");
            System.out.println("[2] Administrator");
            System.out.println("[3] Autopurchase test");
            System.out.println("[4] Exit");
            System.out.print("\nPlease enter an option: ");

            try {
                userType = scanner.nextInt(); // User input
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Clears bad input
            }

            if (userType > 4 || userType < 1) {
                System.out.println("Please enter a valid number choice");
                System.out.println();
            }

            switch (userType) {
                case 1:
                    scanner.nextLine();
                    // Log in customer, create a new customer object for use
                    Customer selectedCustomer = Customer.validateCredentials(customers, scanner);
                    customerActions.userCustomer(scanner, events, selectedCustomer);
                    break;

                case 2: // Admin
                    AdministratorActions.userAdmin(events, scanner);
                    break;

                case 3:
                    while (!terminateAutoBuy) {
                        System.out.println("Please select one of the following choices");
                        System.out.println("[1] Auto purchase with 100 customers.");
                        System.out.println("[2] Auto purchase with 1000 customers.");
                        System.out.println("[3] Auto purchase with 500,000 customers.");
                        System.out.println("[4] Auto purchase with 1,000,000 customers");
                        System.out.println("[5] Exit Autopurchase test menu");

                        try {
                            userChoice = scanner.nextInt(); // User input
                        } catch (java.util.InputMismatchException e) {
                            System.out.println();
                            scanner.nextLine(); // Clears bad input
                        }

                        if (userChoice > 5 || userChoice < 1) {
                            System.out.println("Please enter a valid number choice");
                            System.out.println();
                        }

                        switch (userChoice) {
                            case 1:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase100.csv");
                                break;
                            case 2:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase1000.csv");
                                break;
                            case 3:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase500K.csv");
                                break;
                            case 4:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase1M.csv");
                                break;
                            case 5:
                                terminateAutoBuy = true;
                                System.out.println("Exiting menu.....");
                                break;
                        }
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("---------------------------------");
                    System.out.println("Thank you for using TICKETMINER");
                    System.out.println("Have a wonderful day!");
                    System.out.println("---------------------------------");

                    csvCustomer.writeData(customers);
                    csvEvent.writeData(events);

                    terminateProgram = true;
                    break;
            }
        }
    }
}
