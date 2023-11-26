import java.util.List;
import java.util.Scanner;

/**
 * The `RunTicket` class represents the main entry point of the TicketMiner application.
 * It allows customers and administrators to interact with the system and provides options for autopurchase testing.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 * @version 1.5
 * 
 * Class: CS 3331 - Object-Oriented Programming
 * Instructor: Daniel Mejia
 * Programming Assignment 5
 * Honesty Statement: We, Angel, Caleb, Chris, and Javier, completed this work entirely on our own without any outside sources, including peers, experts, online sources, or the like.
 * Lab description - Utilize Object-Oriented programming design and principles to create a system.
 * 
 */

public class RunTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load events and customers from CSV files
        eventDataReader eventDataReader = new eventDataReader();
        List<Event> events = eventDataReader.eventDataStoring("EventListPA5.csv");

        customerDataReader customerDataReader = new customerDataReader();
        List<Customer> customers = customerDataReader.customerDataStoring("CustomerListPA5.csv");

        AutoPurchase autoPurchase = new AutoPurchase(events, customers);

        // Singleton instances of the CSV writers
        customerCSV csvCustomer = customerCSV.getInstance("UpdatedCustomerList.csv");
        eventCSV csvEvent = eventCSV.getInstance("UpdatedEventList.csv");

        int userType;
        boolean terminateProgram = false;
        int userChoice;
        boolean terminateAutoBuy;

        while (!terminateProgram) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println(
            "**   *   **" + "  *****" +  "  **" +   "       ***** "+     "   *****   "+   " **       **  "+"*****\n"+
            "**  ***  **" + "  **    " +  " **" +   "     **     "+     "  **     **  "+   "** *   * **  "+"**\n"+
            "** ** ** **" + "  *****" +  "  **" +   "     **     "+     "  **     **  "+   "** ** ** **  "+"*****\n"+
            "** *   * **" + "  **    " +  " **" +   "     **     "+     "  **     **  "+   "**  ***  **  "+"**\n"+
            "**       **" + "  *****" +  "  ******"+    "   ***** "+     "   *****   "+   " **   *   **  "+"*****");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Are you an admin or Customer? (Select a choice): ");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("[\u001B[36m1\u001B[0m] \u001B[36mCustomer\u001B[0m");  
            System.out.println("[\u001B[33m2\u001B[0m] \u001B[33mAdministrator\u001B[0m");  
            System.out.println("[\u001B[35m3\u001B[0m] \u001B[35mAutopurchase test\u001B[0m");  
            System.out.println("[\u001B[31m4\u001B[0m] \u001B[31mExit\u001B[0m");  
            System.out.print("\nPlease enter an option: ");

            try {
                userType = scanner.nextInt(); // User input
                scanner.nextLine(); // Consume the newline character
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Clears bad input
                userType = 0;
            }

            if (userType > 4 || userType < 1) {
                System.out.println("\n\u001B[31mPlease enter a valid number choice\u001B[0m");
                System.out.println();
            }

            switch (userType) {
                case 1:
                    // Log in customer, create a new customer object for use
                    Customer selectedCustomer = Customer.validateCredentials(customers, scanner);
                    customerActions.userCustomer(scanner, events, selectedCustomer);
                    break;

                case 2: // Admin
                    AdministratorActions.userAdmin(events, customers, scanner);
                    break;

                case 3:
                    terminateAutoBuy = false;

                    while (!terminateAutoBuy) {
                        System.out.println("\n-----------------------------------------------------------------");
                        System.out.println("\u001B[34m[1] Auto purchase with 100 customers.\u001B[0m");
                        System.out.println("\u001B[34m[2] Auto purchase with 1000 customers.\u001B[0m");
                        System.out.println("\u001B[34m[3] Auto purchase with 100,000 customers.\u001B[0m");
                        System.out.println("\u001B[34m[4] Auto purchase with 1,000,000 customers\u001B[0m");
                        System.out.println("[\u001B[31m5\u001B[0m] \u001B[31mExit\u001B[0m");  
                        System.out.print("\nPlease select one of the following choices:  ");
                        try {
                            userChoice = scanner.nextInt(); // User input
                            scanner.nextLine(); // Consume the newline character
                        } catch (java.util.InputMismatchException e) {
                            System.out.println();
                            scanner.nextLine(); // Clears bad input
                            userChoice = 0;
                        }

                        if (userChoice > 5 || userChoice < 1) {
                            System.out.println("\n\u001B[31mPlease enter a valid number choice\u001B[0m");
                            System.out.println();
                        }

                        switch (userChoice) {
                            case 1:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase100.csv");
                                terminateAutoBuy = true;
                                System.out.println("Exiting menu.....\n");
                                break;
                            case 2:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase1k.csv");
                                terminateAutoBuy = true;
                                System.out.println("Exiting menu.....\n");
                                break;
                            case 3:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase100K.csv");
                                terminateAutoBuy = true;
                                System.out.println("Exiting menu.....\n");
                                break;
                            case 4:
                                autoPurchase.processAutoPurchase(events, customers, "AutoPurchase1M.csv");
                                terminateAutoBuy = true;
                                System.out.println("Exiting menu.....\n");
                                break;
                            case 5:
                                terminateAutoBuy = true;
                                System.out.println("Exiting menu.....\n");
                                break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("\u001B[33mType 'Exit' to terminate the program, or any other key to return to the main menu:\u001B[0m");
                    String exitInput = scanner.nextLine(); // Use nextLine() to read the whole line
                    if (exitInput.equalsIgnoreCase("Exit")) {
                        System.out.println("\n\u001B[38;5;208m---------------------------------\u001B[0m");
                        System.out.println("\u001B[38;5;208mThank you for using TICKETMINER\u001B[0m");
                        System.out.println("\u001B[38;5;208mHave a wonderful day!\u001B[0m");
                        System.out.println("\u001B[38;5;208m---------------------------------\u001B[0m");
                        

                        csvCustomer.writeData(customers);
                        csvEvent.writeData(events);

                        terminateProgram = true;
                    } else {
                        System.out.println("Returning to the main menu...");
                    }
                    break;
            }
        }
    }
}
