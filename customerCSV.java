import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The `customerCSV` class handles reading and writing customer data to a CSV file.
 * It provides methods for writing customer data to a CSV file.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class customerCSV {

    /**
     * Default constructor for the `customerCSV` class.
     */
    customerCSV() {}

    // Variables for the customerCSV class
    private static customerCSV instance;
    private String filename;

    /**
     * Private constructor for creating an instance of the `customerCSV` class with a given filename.
     *
     * @param filename The filename of the CSV file.
     */
    private customerCSV(String filename) {
        this.filename = filename;
    }

    /**
     * Retrieves or creates an instance of the `customerCSV` class with a specified filename.
     *
     * @param filename The filename of the CSV file.
     * @return An instance of the `customerCSV` class.
     */
    public static customerCSV getInstance(String filename) {
        if (instance == null) {
            instance = new customerCSV(filename);
        }
        return instance;
    }

    /**
     * Writes customer data to the CSV file with the specified filename.
     * 
     * @param data The list of Customer objects to write to the CSV file.
     */
    public void writeData(List<Customer> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.append("CustomerID,FirstName,LastName,MoneyAvailable,ConcertsPurchased,TicketMinerMembership,Username,Password,MoneySaved\n");

            // Write customer data
            for (Customer customer : data) {
                writer.append(String.valueOf(customer.getCustomerID()))
                    .append(",")
                    .append(customer.getFirstName())
                    .append(",")
                    .append(customer.getLastName())
                    .append(",")
                    .append(String.valueOf(Invoice.roundToTwoDecimals(customer.getMoneyAvailable())))
                    .append(",")
                    .append(String.valueOf(customer.getTransactionCount()))
                    .append(",")
                    .append(String.valueOf(customer.getIsMember()))
                    .append(",")
                    .append(customer.getUserName())
                    .append(",")
                    .append(customer.getPassword())
                    .append(",")
                    .append(String.valueOf(Invoice.roundToTwoDecimals(customer.getMoneySaved())))
                    .append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to " + filename + ": " + e.getMessage());
        }
    }
}
