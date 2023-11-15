import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The `Customer` class represents customer credentials, money balances, and membership status.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
class Customer {

    // Variables
    private double moneyAvailable;
    private int transactionCount;
    private int customerID;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean isMember;
    private double moneySaved = 0.0;
    TicketPricingStrategy pricingStrategy;
    private List<Map<String, Object>> ticketPurchases = new ArrayList<>();

    /**
     * Constructs a default Customer object.
     */
    public Customer() {
    }

    /**
     * Constructs a Customer object with specified attributes.
     *
     * @param customerID       Unique identifier for the customer.
     * @param firstName        First name of the customer.
     * @param lastName         Last name of the customer.
     * @param moneyAvailable   Money available for the customer.
     * @param transactionCount Number of concerts purchased by the customer.
     * @param isMember         Indicates whether the customer is a member.
     * @param userName         Username for the customer.
     * @param passWord         Password for the customer.
     */
    public Customer(int customerID, String firstName, String lastName, double moneyAvailable, int transactionCount,
                    boolean isMember, String userName, String passWord) {

        this.moneyAvailable = moneyAvailable;
        this.transactionCount = transactionCount;
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.isMember = isMember;
    }

    /**
     * Sets the pricing strategy for the customer.
     *
     * @param pricingStrategy The pricing strategy to be set.
     */
    public void setPricingStrategy(TicketPricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return The customer's unique identifier.
     */
    public int getCustomerID() {
        return this.customerID;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param customerID The new customer ID to be set.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Retrieves the first name of the customer.
     *
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName The new first name to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the customer.
     *
     * @return The last name of the customer.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName The new last name to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the money available for the customer.
     *
     * @return The money available for the customer.
     */
    public double getMoneyAvailable() {
        return this.moneyAvailable;
    }

    /**
     * Sets the money available for the customer.
     *
     * @param moneyAvailable The new amount of money available to be set.
     */
    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    /**
     * Retrieves the number of transactions made by the customer has purchased.
     *
     * @return The number of concerts purchased by the customer.
     */
    public int getTransactionCount() {
        return this.transactionCount;
    }

    /**
     * Sets the number of transactions made by the customer.
     *
     * @param transactionCount Number of transactions made by the customer.
     */
    public void setTransactionCount(int transactionCount) {
        this.transactionCount += transactionCount;
    }

    /**
     * Gets whether the customer is a member.
     *
     * @return True if the customer is a member, false otherwise.
     */
    public boolean getIsMember() {
        return this.isMember;
    }

    /**
     * Sets whether the customer is a member.
     *
     * @param isMember True if the customer is a member, false otherwise.
     */
    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    /**
     * Retrieves the username of the customer.
     *
     * @return The username of the customer.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the username of the customer.
     *
     * @param userName The new username to be set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the password of the customer.
     *
     * @return The password of the customer.
     */
    public String getPassword() {
        return this.passWord;
    }

    /**
     * Sets the password of the customer.
     *
     * @param passWord The new password to be set.
     */
    public void setPassword(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Updates the money available for the customer.
     *
     * @param amount The amount by which the money available will be updated.
     */
    public void updateMoney(double amount) {
        this.moneyAvailable -= amount;
    }

    /**
     * Retrieves the amount of money saved by the customer.
     *
     * @return The money saved by the customer.
     */
    public double getMoneySaved() {
        return this.moneySaved;
    }

    /**
     * Sets the amount of money saved by the customer.
     *
     * @param moneySaved The new money saved to be set.
     */
    public void setMoneySaved(double moneySaved) {
        this.moneySaved = moneySaved;
    }

    public void addTicketPurchase(Map<String, Object> purchase){
        this.ticketPurchases.add(purchase);
    }
    

    public List<Map<String, Object>> getTicketPurchases(){
        return this.ticketPurchases;
    }



    /**
     * Validates customer credentials based on a list of customers and user input.
     *
     * @param customerInfo A list of customer information.
     * @param scanner      A `Scanner` object for user input.
     * @return A `Customer` object if credentials are valid, null otherwise.
     */
    public static Customer validateCredentials(List<Customer> customerInfo, Scanner scanner) {
        while (true) {
            System.out.print("\n---------------------------------");
            System.out.println("\nWELCOME TO TICKETMINER EVENTS ");
            System.out.println("=================================");
            
            // Validate first and last name
            // String inputFirstname;
            // String inputLastname;
            // boolean nameValidated = false;
            
            // while (!nameValidated) {
            //     System.out.println("Please enter your first name: ");
            //     inputFirstname = scanner.nextLine();
            //     System.out.println("Please enter your last name: ");
            //     inputLastname = scanner.nextLine();
                
            //     for (Customer activeCustomer : customerInfo) {
            //         if (activeCustomer.getFirstName().equalsIgnoreCase(inputFirstname) && activeCustomer.getLastName().equalsIgnoreCase(inputLastname)) {
            //             nameValidated = true;
            //             break;
            //         }
            //     }
                
            //     if (!nameValidated) {
            //         System.out.println("Name not found in the database. Please try again.");
            //     }
            // }
            
            // Validate username and password
            System.out.println("Enter username: ");
            String inputUsername = scanner.nextLine();
            System.out.println("\nEnter password: ");
            String inputPassword = scanner.nextLine();
    
            for (Customer activeCustomer : customerInfo) {
                if (activeCustomer.getUserName().equalsIgnoreCase(inputUsername) && activeCustomer.getPassword().equals(inputPassword)) {
                    System.out.print("\n---------------------------------");
                    System.out.println("\n      Welcome " + activeCustomer.getFirstName() + ' ' + activeCustomer.getLastName());
                    System.out.print("=================================\n");
                    return activeCustomer;
                }
            }
            System.out.println("Incorrect username or password. Please try again.");
        }
    }

        /**
     * Helper method to get ticket purchases for a specific event.
     *
     * @param event The event for which to retrieve ticket purchases.
     * @return A list of ticket purchases for the specified event.
     */
    public List<Map<String, Object>> getTicketPurchasesForEvent(Event event) {
        List<Map<String, Object>> eventPurchases = new ArrayList<>();

        for (Map<String, Object> purchase : ticketPurchases) {
            // Check if the purchase is for the specified event
            if (purchase.containsKey("event") && purchase.get("event").equals(event)) {
                eventPurchases.add(purchase);
            }
        }

        return eventPurchases;
    }
    
}
