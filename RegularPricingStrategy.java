/**
 * The `RegularPricingStrategy` class represents a pricing strategy for regular (non-member) customers,
 * implementing the `TicketPricingStrategy` interface. Regular customers do not receive any discounts.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
class RegularPricingStrategy implements TicketPricingStrategy {

    private static final double CONVENIENCE_FEE = 2.50;
    private static final double SERVICE_FEE_RATE = 0.005;
    private static final double CHARITY_FEE_RATE = 0.0075;

    /**
     * Calculates the ticket price for a non-member without applying any discount.
     *
     * @param event         The event for which tickets are being purchased.
     * @param ticketType    The type of ticket (e.g., General Admission, Bronze, etc.).
     * @param ticketQuantity The quantity of tickets being purchased.
     * @return The total ticket price for the non-member.
     */
    @Override
    public double calculateTicketPrice(Event event, int ticketType, int ticketQuantity) {
        // Calculate the base ticket price
        double total = 0.0;
        double ticketPrice = Event.calculateTicketPrice(event, ticketType);

        // Calculate the total price without any discounts
        double totalPrice = ticketPrice * ticketQuantity;

        double convenienceFee = getConvenienceFee();
        double serviceFee = getServiceFee(totalPrice);
        double charityFee = getCharityFee(totalPrice);

        total = totalPrice + convenienceFee + serviceFee + charityFee;
        return total;
        
    }

    /**
     * Gets the convenience fee.
     *
     * @return The convenience fee.
     */
    public static double getConvenienceFee() {
        return CONVENIENCE_FEE;
    }

    /**
     * Gets the service fee based on the total price.
     *
     * @param totalPrice The total price of the tickets.
     * @return The service fee.
     */
    public static double getServiceFee(double totalPrice) {
        return SERVICE_FEE_RATE * totalPrice;
    }

    /**
     * Gets the charity fee based on the total price.
     *
     * @param totalPrice The total price of the tickets.
     * @return The charity fee.
     */
    public static double getCharityFee(double totalPrice) {
        return CHARITY_FEE_RATE * totalPrice;
    }
}
