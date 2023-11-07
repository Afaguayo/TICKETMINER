/**
 * The `InvoiceGenerator` class is responsible for generating invoice summaries and saving them to files.
 * It offers two methods for generating invoice summaries, one for standard invoices and another for automatic invoices.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class InvoiceGenerator {

    /**
     * Generates an invoice summary for a customer and saves it to a file in the "Invoices" folder.
     *
     * @param customer        The customer for whom the invoice is generated.
     * @param ticketPurchases A list of maps representing ticket purchases.
     */
    public static void generateInvoiceSummary(Customer customer, List<Map<String, Object>> ticketPurchases) {
        // Define the folder where you want to store the invoices
        String folderPath = "Invoices/";

        // Check if the folder exists; if not, create it
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Create the necessary directories.
        }

        // Create a file name for the invoice summary
        String fileName = folderPath + customer.getUserName() + "_InvoiceSummary.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write the header of the invoice
            writer.write("Electronic Invoice Summary");
            writer.newLine();
            writer.write("Customer Name: " + customer.getFirstName() + " " + customer.getLastName());
            writer.newLine();
            writer.newLine();

            // Loop through the customer's ticket purchases
            for (Map<String, Object> purchase : ticketPurchases) {
                for (Map.Entry<String, Object> entry : purchase.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.newLine();
                }
                writer.newLine();
            }

            // Close the file
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while generating the invoice summary.");
            e.printStackTrace();
        }
    }// method end 

    /**
     * Generates an automatic invoice summary for a customer and appends it to a file in the "AutoInvoices" folder.
     *
     * @param customer        The customer for whom the invoice is generated.
     * @param ticketPurchases A list of maps representing ticket purchases.
     */
    public static void generateInvoiceSummaryAuto(Customer customer, List<Map<String, Object>> ticketPurchases) {
        // Define the folder where you want to store the automatic invoices
        String folderPath = "AutoInvoices/";

        // Check if the folder exists; if not, create it
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Create the necessary directories.
        }

        // Create a file name for the invoice summary
        String fileName = folderPath + customer.getUserName() + "_InvoiceSummary.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Write the details of each purchase
            for (Map<String, Object> purchase : ticketPurchases) {
                writer.write("Purchase status: " + purchase.get("purchase status"));
                writer.newLine();
                writer.write("Event Type: " + purchase.get("eventType"));
                writer.newLine();
                writer.write("Event Name: " + purchase.get("eventName"));
                writer.newLine();
                writer.write("Event Date: " + purchase.get("eventDate"));
                writer.newLine();
                writer.write("Ticket Type: " + purchase.get("ticketType"));
                writer.newLine();
                writer.write("Number of Tickets: " + purchase.get("numberOfTickets"));
                writer.newLine();
                writer.write("Total Price: $" + purchase.get("totalPrice"));
                writer.newLine();
                writer.write("Confirmation Number: " + purchase.get("confirmationNumber"));
                writer.newLine();
                writer.newLine();
            }

            // Do not close the file to keep it open for appending

            // Test Print a message indicating the invoice has been updated
            // System.out.println("Invoice summary for " + customer.getFirstName() + " " + customer.getLastName()
            //       + " has been updated: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while generating/updating the invoice summary.");
            e.printStackTrace();
        }
    }
}
