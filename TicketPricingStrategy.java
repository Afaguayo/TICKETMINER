/**
 * This class is an interface for implementing the strategy design pattern
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
interface TicketPricingStrategy {
    double calculateTicketPrice(Event event, int ticketType, int ticketQuantity);
}
