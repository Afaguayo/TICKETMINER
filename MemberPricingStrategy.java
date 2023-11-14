/**
 * The `MemberPricingStrategy` class represents a pricing strategy for members, which implements the `TicketPricingStrategy` interface.
 * Members receive a 10% discount on ticket prices.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class MemberPricingStrategy implements TicketPricingStrategy {
    private Customer customer;

    /**
     * Constructs a `MemberPricingStrategy` with the given customer.
     *
     * @param customer The customer for whom the pricing strategy is applied.
     */
    public MemberPricingStrategy(Customer customer) {
        this.customer = customer;
    }

    /**
     * Calculates the ticket price for a member, applying a 10% discount to the base ticket price.
     *
     * @param event         The event for which tickets are being purchased.
     * @param ticketType    The type of ticket (e.g., General Admission, Bronze, etc.).
     * @param ticketQuantity The quantity of tickets being purchased.
     * @return The final discounted ticket price for the member.
     */
    @Override
    public double calculateTicketPrice(Event event, int ticketType, int ticketQuantity) {
        // Calculate the base ticket price
        double ticketPrice = Event.calculateTicketPrice(event, ticketType);

        // double convenienceFee = 2.50;
        // double serviceFee = 0.005 * ticketQuantity * ticketPrice;
        // double charityFee = 0.0075 * ticketQuantity * ticketPrice;

        // Calculate the total price before applying the discount
        double totalPrice = ticketPrice * ticketQuantity;

        // Calculate the discount amount (10% of the total price)
        double discount = (totalPrice * 0.1);

        // Update the customer's money saved with the discount amount
        customer.setMoneySaved(customer.getMoneySaved() + discount);

        // Update the venue's discounts with the discount amount
        event.getVenue().setDiscounts(discount);

        // Calculate the final ticket price after applying the discount
        return (totalPrice - discount);
    }
}
