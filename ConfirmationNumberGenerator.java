 /**
 * Generates a confirmation number for the purchase.
 * 
 * @param customer The customer making the purchase.
 * @return The generated confirmation number.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
import java.util.Objects;

public class ConfirmationNumberGenerator {

    public static String generateConfirmationNumber(Customer customer) {
        int hashCode = Objects.hash(customer.getFirstName(), customer.getCustomerID()); // You can include more attributes if needed
        return String.format("%X", hashCode); // Convert the hash code to a hexadecimal string
    }

}