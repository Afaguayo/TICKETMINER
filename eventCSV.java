import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The `eventCSV` class is responsible for reading and storing event data in a CSV file.
 * It provides methods for writing event information to a CSV file.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 5, 2023
 */
public class eventCSV {

    // Singleton instance
    private static eventCSV instance;
    private String filename;

    /**
     * Private constructor to create an `eventCSV` instance.
     *
     * @param filename The name of the CSV file.
     */
    private eventCSV(String filename) {
        this.filename = filename;
    }

    /**
     * Get the singleton instance of `eventCSV`.
     *
     * @param filename The name of the CSV file.
     * @return The singleton `eventCSV` instance.
     */
    public static eventCSV getInstance(String filename) {
        if (instance == null) {
            instance = new eventCSV(filename);
        }
        return instance;
    }

    /**
     * Write event data to the CSV file.
     *
     * @param events A list of `Event` objects to be written to the CSV.
     */
    public void writeData(List<Event> events) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.append("EventID,EventType,Name,Date,Time,VIPPrice,GoldPrice,SilverPrice,BronzePrice,GeneralAdmissionPrice,VenueName,PctSeatsUnavailable,VenueType,Capacity,Cost,VIPPct,GoldPct,SilverPct,BronzePct,GeneralAdmissionPct,ReservedExtraPct,FireworksPlanned,FireworksCost,TotalRevenue,VIPSeatsSold,GoldSeatsSold,SilverSeatsSold,BronzeSeatsSold,GeneralAdmissionSeatsSold,TotalVIPRevenue,TotalGoldRevenue,TotalSilverRevenue,TotalBronzeRevenue,TotalGeneralAdmissionRevenue,AmountDiscounted,Charity Fees, Service Fees, Convenience Fees\n");

            // Write event data
            for (Event event : events) {
                writer.append(String.valueOf(event.getEventID()))
                        .append(",")
                        .append(event.getEventType())
                        .append(",")
                        .append(event.getName())
                        .append(",")
                        .append(event.getDate())
                        .append(",")
                        .append(event.getTime())
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVipPrice())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getGoldPrice())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getSilverPrice())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getBronzePrice())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getGeneralAdmissionPrice())))
                        .append(",")
                        .append(event.getVenue().getVenueName())
                        .append(",")
                        .append(String.valueOf(event.getVenue().getPctSeatsUnavailable()))
                        .append(",")
                        .append(event.getVenue().getVenueType())
                        .append(",")
                        .append(String.valueOf(event.getVenue().getCapacity()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getCost()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getVipPct()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getGoldPct()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getSilverPct()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getBronzePct()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getGeneralAdmissionPct()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getReservedExtraPct()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().isFireworksPlanned()))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getFireworksCost())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getTotalRevenue())))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getTotalVIPSeatsSold()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getTotalGoldSeatsSold()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getTotalSilverSeatsSold()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getTotalBronzeSeatsSold()))
                        .append(",")
                        .append(String.valueOf(event.getVenue().getTotalGeneralAdmSeatsSold()))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getTotalRevenueVIP())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getTotalRevenueGold())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getTotalRevenueSilver())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getTotalRevenueBronze())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getTotalRevenueGeneralAdm())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getDiscounts())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getCharityFee())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getServiceFee())))
                        .append(",")
                        .append(String.valueOf(Invoice.roundToTwoDecimals(event.getVenue().getConvenienceFee())))
                        .append("\n");

                // Flush the writer after writing each event
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Error writing event data to " + filename + ": " + e.getMessage());
        }
    }
}