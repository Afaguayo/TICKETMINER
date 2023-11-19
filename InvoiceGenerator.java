/**
 * The `InvoiceGenerator` class is responsible for generating invoice summaries and saving them to files.
 * It offers two methods for generating invoice summaries, one for standard invoices and another for automatic invoices.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InvoiceGenerator {

    /**
     * Generates an invoice summary for a customer and saves it to a file in the "Invoices" folder.
     *
     * @param customer        The customer for whom the invoice is generated.
     * @param ticketPurchases A list of maps representing ticket purchases.
     */
    private static Map<String, List<Map<String, Object>>> purchaseHistory = new HashMap<>();

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

            // Check if the customer's purchase history already exists
            List<Map<String, Object>> existingHistory = purchaseHistory.getOrDefault(customer.getUserName(), new ArrayList<>());

            // Append new purchases to existing history
            existingHistory.addAll(ticketPurchases);

            // Update the purchase history for the customer
            purchaseHistory.put(customer.getUserName(), existingHistory);

        } catch (IOException e) {
            System.out.println("An error occurred while generating the invoice summary.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the purchase history for a given customer.
     *
     * @param customer The customer for whom the purchase history is retrieved.
     * @return A list of maps representing the purchase history for the customer.
     */
    public static List<Map<String, Object>> getCustomerPurchaseHistory(Customer customer) {
        return purchaseHistory.getOrDefault(customer.getUserName(), new ArrayList<>());
    }

    /**
     * Removes a purchase from the customer's purchase history.
     *
     * @param customer           The customer from whom the purchase is removed.
     * @param confirmationNumber The confirmation number of the purchase to be removed.
     */
    public static void removePurchaseFromHistory(Customer customer, String confirmationNumber) {
        List<Map<String, Object>> purchaseHistoryList = purchaseHistory.getOrDefault(customer.getUserName(), new ArrayList<>());

        boolean foundAndRemoved = false;
        Iterator<Map<String, Object>> iterator = purchaseHistoryList.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> purchase = iterator.next();
            Object confirmation = purchase.get("Confirmation Number");
            if (confirmation != null && confirmationNumber != null && confirmationNumber.equals(confirmation.toString())) {
                iterator.remove();
                foundAndRemoved = true;
                break; // Exit loop after removal
            }
        }
    }

    /**
     * Cancels an order and updates the corresponding invoice.
     *
     * @param customer           The customer whose order is canceled.
     * @param confirmationNumber The confirmation number of the canceled order.
     */
    public static void cancelOrderAndUpdateInvoice(Customer customer, String confirmationNumber) {
        // Step 1: Update in-memory purchase history
        removePurchaseFromHistory(customer, confirmationNumber);

        // Step 2: Update physical invoice file
        cancelInvoiceFile(customer, confirmationNumber);
    }

    /**
     * Cancels an invoice file by updating the content of the file to indicate order cancellation.
     *
     * @param customer           The customer whose order is canceled.
     * @param confirmationNumber The confirmation number of the canceled order.
     */
    private static void cancelInvoiceFile(Customer customer, String confirmationNumber) {
        String folderPath = "Invoices/";
        String fileName = folderPath + customer.getUserName() + "_InvoiceSummary.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            // Identify the part of the content that corresponds to the canceled order
            String purchaseIdentifier = "Confirmation Number: " + confirmationNumber;
            int startIndex = content.indexOf(purchaseIdentifier);

            if (startIndex != -1) {
                // Replace the purchase details with a message indicating cancellation
                int endIndex = content.indexOf(System.lineSeparator(), startIndex);
                content.replace(startIndex, endIndex, "Order Canceled");
            }

            // Write the updated content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content.toString());
            writer.close();

            System.out.println("Invoice canceled after order cancellation.");

        } catch (IOException e) {
            System.out.println("An error occurred while canceling the invoice.");
            e.printStackTrace();
        }
    }

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
                writer.write("Total Price: " + purchase.get("totalPrice")); // Update to include fees
                writer.newLine();
                writer.write("Confirmation Number: " + purchase.get("confirmationNumber"));
                writer.newLine();
                writer.newLine();
            }

            // Calculate and print service fees
            calculateServiceFees(ticketPurchases);
            //printServiceFees(writer, ticketPurchases);

            // Do not close the file to keep it open for appending
        } catch (IOException e) {
            System.out.println("An error occurred while generating/updating the invoice summary.");
            e.printStackTrace();
        }
    }

    /**
     * Calculates and adds service fees to each ticket purchase.
     *
     * @param ticketPurchases A list of maps representing ticket purchases.
     */
    public static void calculateServiceFees(List<Map<String, Object>> ticketPurchases) {
        // Loop through the ticket purchases and calculate service fees for each purchase
        for (Map<String, Object> purchase : ticketPurchases) {
            Double totalPrice = (Double) purchase.get("totalPrice");

            double convenienceFee = 2.50;
            double serviceFee = 0.005 * totalPrice;
            double charityFee = 0.0075 * totalPrice;

            double totalServiceFees = convenienceFee + serviceFee + charityFee;
            double totalPriceWithFees = totalPrice + totalServiceFees;

            purchase.put("convenienceFee", String.format("$%.2f", convenienceFee));
            purchase.put("serviceFee", String.format("$%.2f", serviceFee));
            purchase.put("charityFee", String.format("$%.2f", charityFee));
            purchase.put("totalServiceFees", String.format("$%.2f", totalServiceFees));
            purchase.put("totalPrice", String.format("$%.2f", totalPriceWithFees));
        }
    }

    /**
     * Prints the purchase history for multiple customers.
     *
     * @param customers A list of customers.
     */
    public static void printPurchaseHistoryForCustomers(List<Customer> customers) {
        System.out.println("Purchase History for Multiple Customers:");

        for (Customer customer : customers) {
            String customerUserName = customer.getUserName();
            List<Map<String, Object>> purchases = getCustomerPurchaseHistory(customer);

            System.out.println("Customer: " + customerUserName);
            System.out.println("Purchase History:");

            for (Map<String, Object> purchase : purchases) {
                System.out.println("  Purchase Details:");
                for (Map.Entry<String, Object> detail : purchase.entrySet()) {
                    System.out.println("    " + detail.getKey() + ": " + detail.getValue());
                }
                System.out.println();
            }

            System.out.println("-------------------------");
        }
    }
}
