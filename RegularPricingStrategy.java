/**
 * The `RegularPricingStrategy` class represents a pricing strategy for regular (non-member) customers,
 * implementing the `TicketPricingStrategy` interface. Regular customers do not receive any discounts.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
class RegularPricingStrategy implements TicketPricingStrategy {

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
        double ticketPrice = Event.calculateTicketPrice(event, ticketType);

        // Calculate the total price without any discounts
        double totalPrice = ticketPrice * ticketQuantity;

        return totalPrice;
    }
}
