import java.text.DecimalFormat;

/**
 * The `Invoice` class represents an invoice for ticket purchases and contains relevant details.
 * It provides methods to manage invoice information and display it on the console.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
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
                   Customer customer, double taxes) {
        this.event = event;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.totalPrice = totalPrice;
        this.confirmationNumber = confirmationNumber;
        this.customer = customer;
        this.taxes = taxes;
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
     * Retrieves the amount of taxes associated with this object.
     *
     * @return The amount of taxes for this object.
     */
    public double getTaxes() {
        return this.taxes;
    }


    /**
     * Sets the amount of taxes for this object.
     *
     * @param taxes The new amount of taxes to be set.
     */
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }



    /**
     * Displays the invoice details on the console.
     */
    public void displayInvoice() {
        System.out.println("\n**-*-*-*-*-*-*-*-*-*-*-*-*-**");
        System.out.println("*-*-*-* I_N_V_O_I_C_E *-*-*-*\n");
        System.out.println("Event: " + event.getName());
        System.out.println("Time: " + event.getTime());
        System.out.println("Event Date: " + event.getDate());
        System.out.println("Event ID: " + event.getEventID());
        System.out.println("Ticket Type: " + getTicketTypeName(ticketType));
        System.out.println("Cost for each ticket" + ": $" + roundToTwoDecimals((totalPrice)/ticketQuantity));
        System.out.println("Ticket Quantity: " + ticketQuantity);
        System.out.println("Subtotal: $" + roundToTwoDecimals(totalPrice));
        System.out.println("Tax 8.25%: $" + roundToTwoDecimals(taxes));
        System.out.println("---> Total Price: $" + roundToTwoDecimals(totalPrice+taxes) + " <---");
        System.out.println("Confirmation Number: " + confirmationNumber);
        double actualMoney = customer.getMoneyAvailable();
        System.out.println("Member ID: " + customer.getCustomerID());
        System.out.println("Client name: " + customer.getFirstName() + ' ' + customer.getLastName());
        System.out.println("Balance before: $" + Invoice.roundToTwoDecimals(actualMoney));
        System.out.println("Balance after purchase: $" + roundToTwoDecimals(updateCustomerMoney(totalPrice)));
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("  Thank you for your purchase");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
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
