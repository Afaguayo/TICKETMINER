import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The `customerDataReader` class is responsible for reading and storing customer data.
 * It parses a CSV file and creates a list of Customer objects with the data.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
public class customerDataReader {

    /**
     * Default constructor for the `customerDataReader` class.
     */
    public customerDataReader() {}

    /**
     * Reads customer data from a CSV file and stores it in a list of Customer objects.
     * 
     * @param filename The name of the CSV file to read data from.
     * @return A list of Customer objects containing the customer data from the CSV file.
     */
    public static List<Customer> customerDataStoring(String filename) {
        List<Customer> customerList = new ArrayList<>();

        try {
            File myObj = new File(filename);
            Scanner fileReader = new Scanner(myObj);

            if (fileReader.hasNextLine()) {
                Map<String, Integer> headerMapping = new HashMap<>();
                String[] headers = fileReader.nextLine().split(",");
                for (int i = 0; i < headers.length; i++) {
                    headerMapping.put(headers[i].trim(), i);
                }

                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    if (!data.isEmpty()) {
                        String[] dataStorage = data.split(",");

                        if (dataStorage.length >= 1) {
                            int customerID = getIntValue(dataStorage, headerMapping, "ID");
                            String firstName = getValue(dataStorage, headerMapping, "First Name");
                            String lastName = getValue(dataStorage, headerMapping, "Last Name");
                            double moneyAvailable = getDoubleValue(dataStorage, headerMapping, "Money Available");
                            int concertsPurchased = getIntValue(dataStorage, headerMapping, "Concerts Purchased");
                            boolean ticketMinerMembership = getBooleanValue(dataStorage, headerMapping, "TicketMiner Membership");
                            String username = getValue(dataStorage, headerMapping, "Username");
                            String password = getValue(dataStorage, headerMapping, "Password");

                            // Create customer object
                            Customer customer = new Customer(customerID, firstName, lastName, moneyAvailable, concertsPurchased, ticketMinerMembership, username, password);

                            // Add the customer to the list
                            customerList.add(customer);
                        }
                    }
                }
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return customerList;
    }

    /**
     * Parses integer values and returns 0 if the value is not valid.
     * 
     * @param data The data array to extract the value from.
     * @param headerMapping Mapping of column names to indices.
     * @param columnName The name of the column to extract.
     * @return The integer value parsed from the data or 0 if not valid.
     */
    private static int getIntValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {
            int columnIndex = headerMapping.get(columnName);
            String value = data[columnIndex].trim();

            if (value.isEmpty()) {
                return 0; // Return 0 for empty values
            } else {
                return Integer.parseInt(value);
            }
        } else {
            return 0; // Return 0 for missing columns
        }
    }

    /**
     * Extracts and returns string values, or null if the value is not valid.
     * 
     * @param data The data array to extract the value from.
     * @param headerMapping Mapping of column names to indices.
     * @param columnName The name of the column to extract.
     * @return The string value parsed from the data or null if not valid.
     */
    private static String getValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {
            int columnIndex = headerMapping.get(columnName);
            return data[columnIndex].trim();
        } else {
            return null; // Return null for missing columns
        }
    }

    /**
     * Parses double values and returns null if the value is not valid.
     * 
     * @param data The data array to extract the value from.
     * @param headerMapping Mapping of column names to indices.
     * @param columnName The name of the column to extract.
     * @return The double value parsed from the data or null if not valid.
     */
    private static Double getDoubleValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {
            int columnIndex = headerMapping.get(columnName);
            String value = data[columnIndex].trim();

            if (value.isEmpty()) {
                return null; // Return null for empty values
            } else {
                return Double.parseDouble(value);
            }
        } else {
            return null; // Return null for missing columns
        }
    }

    /**
     * Parses boolean values and returns null if the value is not valid.
     * 
     * @param data The data array to extract the value from.
     * @param headerMapping Mapping of column names to indices.
     * @param columnName The name of the column to extract.
     * @return The boolean value parsed from the data or null if not valid.
     */
    private static Boolean getBooleanValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {
            int columnIndex = headerMapping.get(columnName);
            String value = data[columnIndex].trim();

            if (value.isEmpty()) {
                return null; // Return null for empty values
            } else {
                return "true".equalsIgnoreCase(value) || "1".equals(value);
            }
        } else {
            return null; // Return null for missing columns
        }
    }
}
