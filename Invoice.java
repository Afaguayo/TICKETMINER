import java.text.DecimalFormat;

/**
 * The `Invoice` class represents an invoice for ticket purchases and contains relevant details.
 * It provides methods to manage invoice information and display it on the console.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
public class Invoice {

    // Variables
    private Event event;
    private int ticketType;
    private int ticketQuantity;
    private double totalPrice;
    private String confirmationNumber;
    private Customer customer;
    private double taxes;
    private double subtotal;
    private double charityFee;
    private double serviceFee;
    private double convenienceFee;

    /**
     * Constructs an `Invoice` object with the specified details.
     *
     * @param event               The event associated with this invoice.
     * @param ticketType          The ticket type (represented by an int) for this invoice.
     * @param ticketQuantity      The quantity of tickets for this invoice.
     * @param totalPrice          The total price of the tickets in this invoice.
     * @param confirmationNumber  The confirmation number for this invoice.
     * @param customer            The customer associated with this invoice.
     * @param taxes               The amount of taxes associated with this invoice.
     */
    public Invoice(Event event, int ticketType, int ticketQuantity, double totalPrice, String confirmationNumber,
                   Customer customer, double subtotal, double taxes, double charityFee, double serviceFee, double convenienceFee) {
        this.event = event;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.totalPrice = totalPrice;
        this.confirmationNumber = confirmationNumber;
        this.customer = customer;
        this.subtotal = subtotal;
        this.taxes = taxes;
        this.charityFee = charityFee;
        this.serviceFee = serviceFee;
        this.convenienceFee = convenienceFee;

    }

    public Invoice(Event event2, int ticketType2, int ticketQuantity2, double ticketPrice, String confirmationNumber2,
            Customer customer2, double taxes2) {
    }

    /**
     * Gets the event associated with this invoice.
     *
     * @return The event associated with this invoice.
     */
    public Event getEvent() {
        return this.event;
    }

    /**
     * Sets the event associated with this invoice.
     *
     * @param event The event to set.
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets the ticket type for this invoice.
     *
     * @return The ticket type.
     */
    public int getTicketType() {
        return this.ticketType;
    }

    /**
     * Sets the ticket type for this invoice.
     *
     * @param ticketType The ticket type to set.
     */
    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Gets the quantity of tickets for this invoice.
     *
     * @return The ticket quantity.
     */
    public int getTicketQuantity() {
        return this.ticketQuantity;
    }

    /**
     * Sets the quantity of tickets for this invoice.
     *
     * @param ticketQuantity The ticket quantity to set.
     */
    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    /**
     * Gets the total price for this invoice.
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Sets the total price for this invoice.
     *
     * @param totalPrice The total price to set.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the confirmation number for this invoice.
     *
     * @return The confirmation number.
     */
    public String getConfirmationNumber() {
        return this.confirmationNumber;
    }

    /**
     * Sets the confirmation number for this invoice.
     *
     * @param confirmationNumber The confirmation number to set.
     */
    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    /**
     * Gets the customer associated with this invoice.
     *
     * @return The customer associated with this invoice.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Sets the customer associated with this invoice.
     *
     * @param customer The customer to set.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    /**
     * Displays the invoice details on the console.
     */
    public void displayInvoice() {
        System.out.println("\u001B[34m╔════════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[34m║\u001B[0m             \u001B[32mI N V O I C E              \u001B[34m║\u001B[0m");
        System.out.println("\u001B[34m╠════════════════════════════════════════╣\u001B[0m");
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mEvent:\u001B[0m             %-20s\u001B[34m║%n", event.getName());
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mTime:\u001B[0m              %-20s\u001B[34m║%n", event.getTime());
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mEvent Date:\u001B[0m        %-20s\u001B[34m║%n", event.getDate());
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mEvent ID:\u001B[0m          %-20d\u001B[34m║%n", event.getEventID());
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mTicket Type:\u001B[0m       %-20s\u001B[34m║%n", getTicketTypeName(ticketType));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mCost per ticket:\u001B[0m   $%-19.2f\u001B[34m║%n", roundToTwoDecimals((totalPrice) / ticketQuantity));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mTicket Quantity:\u001B[0m   %-20d\u001B[34m║%n", ticketQuantity);
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mSubtotal:\u001B[0m          $%-19.2f\u001B[34m║%n", roundToTwoDecimals(subtotal));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mTax 8.25%%:\u001B[0m         $%-19.2f\u001B[34m║%n", roundToTwoDecimals(taxes));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mConvenience Fees:\u001B[0m  $%-19.2f\u001B[34m║%n", roundToTwoDecimals(convenienceFee));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mService Fees:\u001B[0m      $%-19.2f\u001B[34m║%n", roundToTwoDecimals(serviceFee));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mCharity Fees:\u001B[0m      $%-19.2f\u001B[34m║%n", roundToTwoDecimals(charityFee));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[32mTotal Price:\u001B[0m       $%-19.2f\u001B[34m║%n", roundToTwoDecimals(totalPrice));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mConfirmation #:\u001B[0m    %-20s\u001B[34m║%n", confirmationNumber);
        double actualMoney = customer.getMoneyAvailable();
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mMember ID:\u001B[0m         %-20d\u001B[34m║%n", customer.getCustomerID());
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mClient Name:\u001B[0m       %-20s\u001B[34m║%n", customer.getFirstName() + ' ' + customer.getLastName());
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mBalance Before:\u001B[0m    $%-19.2f\u001B[34m║%n", Invoice.roundToTwoDecimals(actualMoney));
        System.out.printf("\u001B[34m║\u001B[0m \u001B[33mBalance After:\u001B[0m     $%-19.2f\u001B[34m║%n", roundToTwoDecimals(updateCustomerMoney(totalPrice)));
        System.out.println("\u001B[34m╚════════════════════════════════════════╝\u001B[0m");
        System.out.println("\u001B[34m║        THANK YOU FOR YOUR PURCHASE     ║");
        System.out.println("\u001B[34m╚════════════════════════════════════════╝\u001B[0m");
        
        

    }

    /**
     * Gets the ticket type name based on the given ticket type code.
     *
     * @param ticketType The ticket type code. Valid values: 1 for General Admission, 2 for Bronze, 3 for Silver,
     *                   4 for Gold, 5 for VIP.
     * @return The ticket type name corresponding to the given ticket type code. Returns "Unknown Ticket Type" for an
     *         unrecognized ticket type code.
     */
    private String getTicketTypeName(int ticketType) {
        switch (ticketType) {
            case 1:
                return "General Admission";
            case 2:
                return "Bronze";
            case 3:
                return "Silver";
            case 4:
                return "Gold";
            case 5:
                return "VIP";
            default:
                return "Unknown Ticket Type";
        }
    }

    /**
    * Updates the customer's available money after a purchase.
    *
    * @param purchaseAmount The amount spent in the purchase.
    * @return The remaining money available for the customer after the purchase.
    */
    private double updateCustomerMoney(double purchaseAmount) {
        double moneyAfterPurchase = customer.getMoneyAvailable() - purchaseAmount;
        customer.setMoneyAvailable(moneyAfterPurchase);
        return moneyAfterPurchase;
    }

    /**
     * Rounds a given value to two decimal places.
     *
     * @param value The value to be rounded.
     * @return The rounded value.
     */
    public static double roundToTwoDecimals(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }
}
