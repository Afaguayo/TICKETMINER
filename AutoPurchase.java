import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * The `AutoPurchase` class is responsible for processing automated purchases of tickets based on data from a CSV file.
 *
 * @param events        A list of available events.
 * @param customers     A list of customers.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class AutoPurchase {

    private List<Event> events;
    private List<Customer> customers;


    /**
     * Constructs an `AutoPurchase` object with the specified event and customer lists.
     *
     * @param events        A list of available events.
     * @param customers     A list of customers.
     */
    public AutoPurchase(List<Event> events, List<Customer> customers) {
        this.events = events;
        this.customers = customers;
    }


    /**
     * Process an automated purchase of tickets based on data from a CSV file.
     *
     * @param events        A list of available events.
     * @param customers     A list of customers.
     * @param csvFilePath   The path to the CSV file containing purchase data.
     */
    public void processAutoPurchase(List<Event> events, List<Customer> customers,String csvFilePath) {
        try {
            File csvFile = new File(csvFilePath);
            Scanner fileReader = new Scanner(csvFile);

            // get colums and read them
            if (fileReader.hasNextLine()){
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
                    String firstName = getValue(dataStorage, headerMapping, "First");
                    String lastName = getValue(dataStorage, headerMapping, "Last");
                    String action = getValue(dataStorage, headerMapping, "Action");
                    int eventID = getIntValue(dataStorage, headerMapping, "Event ID");
                    String eventName =  getValue(dataStorage, headerMapping, "Event Name");
                    int ticketQuantity = getIntValue(dataStorage, headerMapping, "Ticket Quantity");
                    String ticketType = getValue(dataStorage, headerMapping, "Ticket Type");


                    // test print to check if values are correctly being parsed
                    //System.out.println(firstName + " " +  lastName + " " +  action + " " + eventID + " " + eventName  + " " + ticketQuantity + " " + ticketType);
                    
                    //purchasing logic here
                    if (action.equalsIgnoreCase("Buy")) {
                        customerActions.autoBuyTickets(events,customers,firstName,lastName,eventID,eventName,ticketQuantity,ticketType);
                    
                    }
                }// end of nested nested  if
            }// end of nested if
        }// end of while
    }// end of if
            System.out.println("\nAuto purchase complete.\nNew folder with invoices has been created.\n");
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.out.println();
        }
    }// end of method 


 /**
     * Taken from Angel
     * gets int values and checks if theres is not a valid value if not place a 0 in that data spot
     * @param data
     * @param headerMapping
     * @param columnName
     * @return int
     */
    private static int getIntValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {

            int columnIndex = headerMapping.get(columnName);
            String value = data[columnIndex].trim();

            if (value.isEmpty()){
                return 0; // Return null for empty values
            }
            else{
                return Integer.parseInt(value);
            }
        } 
        else{
            return 0; // Return null for missing columns
        }
    }// end of method
    
    
    /**
     * Taken from Angel
     * gets String values and checks if theres is not a valid value if not place a null in that data spot 
     * @param data
     * @param headerMapping
     * @param columnName
     * @return String
     */
    private static String getValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)){
            int columnIndex = headerMapping.get(columnName);
            return data[columnIndex].trim();
        } 
        else{
            return null; // Return null for missing columns
        }
    }
    


}// end of file
