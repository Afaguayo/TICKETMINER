import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The `eventDataReader` class reads and processes event data from a CSV file.
 * It provides methods to store event data in a structured list.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
public class eventDataReader {
   
    /**
     * Reads event data from a CSV file and stores it in a list of events.
     *
     * @param filename The name of the CSV file containing event data.
     * @return A list of `Event` objects read from the CSV file.
     */

    public static List<Event> eventDataStoring(String filename) {

        // list of type event
        List<Event> eventList = new ArrayList<>();

        try {
            File myObj = new File(filename);
            Scanner fileReader2 = new Scanner(myObj);

        // get colums and read them
        if (fileReader2.hasNextLine()) {
             Map<String, Integer> headerMapping = new HashMap<>();
             String[] headers = fileReader2.nextLine().split(",");
             for (int i = 0; i < headers.length; i++) {
                headerMapping.put(headers[i].trim(), i);
                
            }

            while (fileReader2.hasNextLine()) {

                String data = fileReader2.nextLine(); // goes through csv file

                if (!data.isEmpty()) {
                    String[] dataStorage = data.split(",");
                    
                    if (dataStorage.length >= 1) {

                        int id = getIntValue(dataStorage, headerMapping, "Event ID");
                        String type = getValue(dataStorage, headerMapping, "Event Type");
                        String name = getValue(dataStorage, headerMapping, "Name");
                        String date = getValue(dataStorage, headerMapping, "Date");
                        String time = getValue(dataStorage, headerMapping, "Time");
                        Double vipPrice = getDoubleValue(dataStorage, headerMapping, "VIP Price");
                        Double goldPrice = getDoubleValue(dataStorage, headerMapping, "Gold Price");
                        Double silverPrice = getDoubleValue(dataStorage, headerMapping, "Silver Price");
                        Double bronzePrice = getDoubleValue(dataStorage, headerMapping, "Bronze Price");
                        Double generalAdmissionPrice = getDoubleValue(dataStorage, headerMapping, "General Admission Price");
                        String venueName = getValue(dataStorage, headerMapping, "Venue Name");
                        int pctSeatsUnavailable = getIntValue(dataStorage, headerMapping, "Pct Seats Unavailable");
                        String venueType = getValue(dataStorage, headerMapping, "Venue Type");
                        int capacity = getIntValue(dataStorage, headerMapping, "Capacity");
                        int cost = getIntValue(dataStorage, headerMapping, "Cost");
                        double vipPct = getDoubleValue(dataStorage, headerMapping, "VIP Pct");
                        double goldPct = getDoubleValue(dataStorage, headerMapping, "Gold Pct");
                        double silverPct = getDoubleValue(dataStorage, headerMapping, "Silver Pct");
                        double bronzePct = getDoubleValue(dataStorage, headerMapping, "Bronze Pct");
                        double generalAdmissionPct = getDoubleValue(dataStorage, headerMapping, "General Admission Pct");
                        double reservedExtraPct = getDoubleValue(dataStorage, headerMapping, "Reserved Extra Pct");
                        boolean fireworksplanned = getBooleanValue(dataStorage, headerMapping, "Fireworks Planned");
                        int fireworksCost = getIntValue(dataStorage, headerMapping, "Fireworks Cost");
                      
                        // Sport and stadium allocator
                        if ("Sport".equalsIgnoreCase(type) && "Stadium".equalsIgnoreCase(venueType)) {
                            Stadium stadium = new Stadium(venueName,pctSeatsUnavailable,venueType,capacity,cost,vipPct,goldPct,
                            silverPct,bronzePct,generalAdmissionPct,reservedExtraPct,fireworksplanned,fireworksCost);
                        
                            Sport sport = new Sport(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice,stadium);
                                
                            eventList.add(sport);
                        }

                        // Sport and arena allocator
                        if ("Sport".equalsIgnoreCase(type) && "Arena".equalsIgnoreCase(venueType)) {
                            Arena arena = new Arena(venueName,pctSeatsUnavailable,venueType,capacity,cost,vipPct,goldPct,
                            silverPct,bronzePct,generalAdmissionPct,reservedExtraPct,fireworksplanned,fireworksCost);
                        
                            Sport sport = new Sport(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice,arena );
                                
                            eventList.add(sport);
                        }

                        // Concert and auditorium allocator
                        if ("Concert".equalsIgnoreCase(type) && "Auditorium".equalsIgnoreCase(venueType)){
                            Auditorium auditorium = new Auditorium(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct,
                            silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksplanned, fireworksCost);
                        
                            Concert concert = new Concert(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice, auditorium);

                            eventList.add(concert);
                        }

                        // concert and arena stadium
                        if ("Concert".equalsIgnoreCase(type) && "Stadium".equalsIgnoreCase(venueType)){
                            Stadium stadium = new Stadium(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct,
                            silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksplanned, fireworksCost);
                        
                            Concert concert = new Concert(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice, stadium);

                            eventList.add(concert);
                        }

                        // concert and Arena allocator
                        if ("Concert".equalsIgnoreCase(type) && "Arena".equalsIgnoreCase(venueType)){
                            Arena arena = new Arena(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct,
                            silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksplanned, fireworksCost);
                        
                            Concert concert = new Concert(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice, arena);

                            eventList.add(concert);
                        }

                        // Festival and Open Air allocator
                        if("Festival".equalsIgnoreCase(type) && "Open Air".equalsIgnoreCase(venueType)){
                            OpenAir openAir = new OpenAir(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct,
                            silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksplanned, fireworksCost);
                        
                            Festival festival = new Festival(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice, openAir);

                            eventList.add(festival);
                        }

                        // Festival and Arena allocator
                        if("Festival".equalsIgnoreCase(type) && "Arena".equalsIgnoreCase(venueType)){
                            Arena arena = new Arena(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct,
                            silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksplanned, fireworksCost);
                        
                            Festival festival = new Festival(id, type, name, date, time, vipPrice, goldPrice, silverPrice,
                                bronzePrice, generalAdmissionPrice, arena);

                            eventList.add(festival);
                        }               
                    
                    }// end of nested nested if
                }// end  of nested if
            }// end of while
        }// end of if

        fileReader2.close(); // close reader

        }// end of try

        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }// end of catch

        return eventList; // return the filled list


    }// methods start-----------------------------------------------------------------------------------------------


    public List<Event> getEvents() {
        List<Event> eventList = new ArrayList<>();
        // Read events from the CSV file and add them to eventList
        // Implement this method to read the events from your CSV file.

        return eventList;
    }

    /**
     * Taken from Angel
     * gets int values and checks if theres is not a valid value if not place a 0 in that data spot
     * @param data
     * @param headerMapping
     * @param columnName
     * @return none
     */
    private static int getIntValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {
            int columnIndex = headerMapping.get(columnName);
            String value = data[columnIndex].trim();
            if (value.isEmpty()) {
                return 0; // Return null for empty values
            } else {
                return Integer.parseInt(value);
            }
        } else {
            return 0; // Return null for missing columns
        }
    }
    
    
    /**
     * Taken from Angel
     * gets String values and checks if theres is not a valid value if not place a null in that data spot 
     * @param data
     * @param headerMapping
     * @param columnName
     * @return none
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
     * Taken from Angel
     * gets Double values and checks if theres is not a valid value if not place a null in that data spot 
     * @param data
     * @param headerMapping
     * @param columnName
     * @return none
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
     * Taken from Angel
     * gets Booelan values and checks if theres is not a valid value if not place a null in that data spot 
     * @param data
     * @param headerMapping
     * @param columnName
     * @return none
     */
    private static Boolean getBooleanValue(String[] data, Map<String, Integer> headerMapping, String columnName) {
        if (headerMapping.containsKey(columnName)) {
            int columnIndex = headerMapping.get(columnName);
            if (columnIndex >= data.length) {
                return false; // Return false for out-of-bounds index
            }
            String value = data[columnIndex].trim();
            if (value.isEmpty()) {
                return false; // Return false for empty values
            } else {
                return "true".equalsIgnoreCase(value) || "1".equals(value);
            }
        } else {
            return false; // Return false for missing columns
        }
    }
    
    
}// end of file------------------------------------------------------------------------------------------------------