/**
 * The `Venue` class holds the data for a venue where events are held.
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
public abstract class Venue {

    private String venueName;
    private int pctSeatsUnavailable;
    private String venueType;
    private int capacity;
    private int cost;
    private double vipPct;
    private double goldPct;
    private double silverPct;
    private double bronzePct;
    private double generalAdmissionPct;
    private double reservedExtraPct;
    private boolean fireworksPlanned;
    private int fireworksCost;

    // variables for ticket stats
    private int totalSeatsSold;
    private int totalVIPSeatsSold;
    private int totalGoldSeatsSold;
    private int totalSilverSeatsSold;
    private int totalBronzeSeatsSold;
    private int totalGeneralAdmSeatsSold;
    private double totalRevenueVIP;
    private double totalRevenueGold;
    private double totalRevenueSilver;
    private double totalRevenueBronze;
    private double totalRevenueGeneralAdm;
    private double totalRevenueAllTickets;
    private double expectedProfit;
    private double actualProfit;
    private Double discounts = 0.0;
    private Double charityFee = 0.0;
    private Double serviceFee = 0.0;
    private Double convenienceFee = 0.0;

    // Constructors
    /**
     * Constructs a `Venue` object with the specified characteristics.
     *
     * @param venueName            The name of the venue.
     * @param pctSeatsUnavailable  The percentage of seats that are unavailable.
     * @param venueType            The type or category of the venue.
     * @param capacity             The maximum seating capacity of the venue.
     * @param cost                 The cost associated with the venue.
     * @param vipPct               The percentage of VIP seats.
     * @param goldPct              The percentage of Gold seats.
     * @param silverPct            The percentage of Silver seats.
     * @param bronzePct            The percentage of Bronze seats.
     * @param generalAdmissionPct  The percentage of General Admission seats.
     * @param reservedExtraPct     The percentage of extra reserved seats.
     * @param fireworksPlanned     A flag indicating if fireworks are planned.
     * @param fireworksCost        The cost of fireworks if planned.
     */
    public Venue(String venueName, int pctSeatsUnavailable, String venueType, int capacity, int cost, double vipPct,
                 double goldPct, double silverPct, double bronzePct, double generalAdmissionPct, double reservedExtraPct,
                 boolean fireworksPlanned, int fireworksCost) {

        this.venueName = venueName;
        this.pctSeatsUnavailable = pctSeatsUnavailable;
        this.venueType = venueType;
        this.capacity = capacity;
        this.cost = cost;
        this.vipPct = vipPct;
        this.goldPct = goldPct;
        this.silverPct = silverPct;
        this.bronzePct = bronzePct;
        this.generalAdmissionPct = generalAdmissionPct;
        this.reservedExtraPct = reservedExtraPct;
        this.fireworksPlanned = fireworksPlanned;
        this.fireworksCost = fireworksCost;
    }

    /**
     * Sets the name of the venue.
     *
     * @param venueName The name of the venue to be set.
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    /**
     * Sets the percentage of unavailable seats in the venue.
     *
     * @param pctSeatsUnavailable The percentage of unavailable seats to be set.
     */
    public void setPctSeatsUnavailable(int pctSeatsUnavailable) {
        this.pctSeatsUnavailable = pctSeatsUnavailable;
    }

    /**
     * Sets the type of the venue.
     *
     * @param venueType The type of the venue to be set.
     */
    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    /**
     * Sets the capacity of the venue.
     *
     * @param capacity The capacity of the venue to be set.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the cost of the venue.
     *
     * @param cost The cost of the venue to be set.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Sets the percentage of VIP seats in the venue.
     *
     * @param vipPct The percentage of VIP seats to be set.
     */
    public void setVipPct(int vipPct) {
        this.vipPct = vipPct;
    }

    /**
     * Sets the percentage of Gold seats in the venue.
     *
     * @param goldPct The percentage of Gold seats to be set.
     */
    public void setGoldPct(int goldPct) {
        this.goldPct = goldPct;
    }

    /**
     * Sets the percentage of Silver seats in the venue.
     *
     * @param silverPct The percentage of Silver seats to be set.
     */
    public void setSilverPct(int silverPct) {
        this.silverPct = silverPct;
    }

    /**
     * Sets the percentage of Bronze seats in the venue.
     *
     * @param bronzePct The percentage of Bronze seats to be set.
     */
    public void setBronzePct(int bronzePct) {
        this.bronzePct = bronzePct;
    }

    /**
     * Sets the percentage of General Admission seats in the venue.
     *
     * @param generalAdmissionPct The percentage of General Admission seats to be set.
     */
    public void setGeneralAdmissionPct(int generalAdmissionPct) {
        this.generalAdmissionPct = generalAdmissionPct;
    }

    /**
     * Sets the percentage of reserved extra seats in the venue.
     *
     * @param reservedExtraPct The percentage of reserved extra seats to be set.
     */
    public void setReservedExtraPct(int reservedExtraPct) {
        this.reservedExtraPct = reservedExtraPct;
    }

    /**
     * Sets whether fireworks are planned for the venue.
     *
     * @param fireworksPlanned A boolean indicating whether fireworks are planned.
     */
    public void setFireworksPlanned(boolean fireworksPlanned) {
        this.fireworksPlanned = fireworksPlanned;
    }

    /**
     * Sets the cost of fireworks for the venue.
     *
     * @param fireworksCost The cost of fireworks for the venue to be set.
     */
    public void setFireworksCost(int fireworksCost) {
        this.fireworksCost = fireworksCost;
    }

    /**
     * Gets the name of the venue.
     *
     * @return The name of the venue.
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * Gets the percentage of unavailable seats in the venue.
     *
     * @return The percentage of unavailable seats.
     */
    public int getPctSeatsUnavailable() {
        return pctSeatsUnavailable;
    }

    /**
     * Gets the type of the venue.
     *
     * @return The type of the venue.
     */
    public String getVenueType() {
        return venueType;
    }

    /**
     * Gets the capacity of the venue.
     *
     * @return The capacity of the venue.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the cost of the venue.
     *
     * @return The cost of the venue.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the percentage of VIP seats in the venue.
     *
     * @return The percentage of VIP seats.
     */
    public double getVipPct() {
        return vipPct;
    }

    /**
     * Gets the percentage of Gold seats in the venue.
     *
     * @return The percentage of Gold seats.
     */
    public double getGoldPct() {
        return goldPct;
    }

    /**
     * Gets the percentage of Silver seats in the venue.
     *
     * @return The percentage of Silver seats.
     */
    public double getSilverPct() {
        return silverPct;
    }

    /**
     * Gets the percentage of Bronze seats in the venue.
     *
     * @return The percentage of Bronze seats.
     */
    public double getBronzePct() {
        return bronzePct;
    }

    /**
     * Gets the percentage of General Admission seats in the venue.
     *
     * @return The percentage of General Admission seats.
     */
    public double getGeneralAdmissionPct() {
        return generalAdmissionPct;
    }

    /**
     * Gets the percentage of reserved extra seats in the venue.
     *
     * @return The percentage of reserved extra seats.
     */
    public double getReservedExtraPct() {
        return reservedExtraPct;
    }

    /**
     * Checks whether fireworks are planned for the venue.
     *
     * @return A boolean indicating whether fireworks are planned.
     */
    public boolean isFireworksPlanned() {
        return fireworksPlanned;
    }

    /**
     * Gets the cost of fireworks for the venue.
     *
     * @return The cost of fireworks for the venue.
     */
    public int getFireworksCost() {
        return fireworksCost;
    }


    /**
     * @param price
     * @param numberOfTickets
     * @return none
     */
    // Calculate and update revenue for VIP tickets
    public void updateRevenueVIP(double price, int numberOfTickets, Customer customer) {
        if(customer.getIsMember()){
            totalRevenueVIP += ((price * numberOfTickets) * 0.9);
        } else {
            totalRevenueVIP += price * numberOfTickets;
        }
    }

    /**
     * @param price
     * @param numberOfTickets
     * @return none
     */
    // Calculate and update revenue for Gold tickets
    public void updateRevenueGold(double price, int numberOfTickets, Customer customer) {
        if(customer.getIsMember()){
            totalRevenueGold += ((price * numberOfTickets) * 0.9);
            
        } else {
            totalRevenueGold += price * numberOfTickets;
        }
    }

    /**
     * @param price
     * @param numberOfTickets
     * @return none
     */
    // Calculate and update revenue for Silver tickets
    public void updateRevenueSilver(double price, int numberOfTickets, Customer customer) {
        if(customer.getIsMember()){
            totalRevenueSilver += ((price * numberOfTickets) * 0.9);
            
        } else {
            totalRevenueSilver += price * numberOfTickets;
        }
    }

    /**
     * @param price
     * @param numberOfTickets
     * @return none
     */
    // Calculate and update revenue for Bronze tickets
    public void updateRevenueBronze(double price, int numberOfTickets, Customer customer) {
        if(customer.getIsMember()){
            totalRevenueBronze += ((price * numberOfTickets) * 0.9);
            
        } else {
            totalRevenueBronze += price * numberOfTickets;
        }
    }

    /**
     * @param price
     * @param numberOfTickets
     * @return none
     */
    // Calculate and update revenue for General Admission tickets
    public void updateRevenueGeneralAdm(double price, int numberOfTickets, Customer customer) {
        if(customer.getIsMember()){
            totalRevenueGeneralAdm += ((price * numberOfTickets) * 0.9);

        } else {
            totalRevenueGeneralAdm += price * numberOfTickets;
        }
    }

    /**
     * @param none
     * @return double
     */
    // Calculate and return the total revenue for all ticket types
    public double getTotalRevenue() {
        totalRevenueAllTickets = totalRevenueVIP + totalRevenueGold + totalRevenueSilver + totalRevenueBronze + totalRevenueGeneralAdm;
        return totalRevenueAllTickets;
    }

    /**
     * @param none
     * @return int
     */
    // Increment the total seats sold
    public int totalSeatsSold() {
        totalSeatsSold = totalVIPSeatsSold + totalGoldSeatsSold + totalSilverSeatsSold + totalBronzeSeatsSold + totalGeneralAdmSeatsSold;
        return totalSeatsSold;
    }

    /**
     * @param numberOfTickets
     * @return none
     */
    // Increment the VIP seats sold
    public void incrementVIPSeatsSold(int numberOfTickets) {
        totalVIPSeatsSold += numberOfTickets;
    }

    /**
     * @param numberOfTickets
     * @return none
     */
    // Increment the Gold seats sold
    public void incrementGoldSeatsSold(int numberOfTickets) {
        totalGoldSeatsSold += numberOfTickets;
    }

    /**
     * @param numberOfTickets
     * @return none
     */
    // Increment the Silver seats sold
    public void incrementSilverSeatsSold(int numberOfTickets) {
        totalSilverSeatsSold += numberOfTickets;
    }

    /**
     * @param numberOfTickets
     * @return none
     */
    // Increment the Bronze seats sold
    public void incrementBronzeSeatsSold(int numberOfTickets) {
        totalBronzeSeatsSold += numberOfTickets;
    }

    /**
     * @param numberOfTickets
     * @return none
     */
    // Increment the General Admission seats sold
    public void incrementGeneralAdmSeatsSold(int numberOfTickets) {
        totalGeneralAdmSeatsSold += numberOfTickets;
    }

    /**
     * @param none
     * @return Double
     */
    public Double calculateActualProfit() {

        int costOfEvent = getCost();

        actualProfit = getTotalRevenue() - costOfEvent;

        return actualProfit;
    }

    /**
     * @param none
     * @return double
     */
    public double getActualProfit() {
        return actualProfit;
    }

    /**
     * @param none
     * @return int
     */
    // setters and getters for ticket stats
    public int getTotalSeatsSold() {
        return this.totalSeatsSold;
    }

    /**
     * @param totalSeatsSold
     * @return none
     */
    public void setTotalSeatsSold(int totalSeatsSold) {
        this.totalSeatsSold = totalSeatsSold;
    }

    /**
     * @param none
     * @return int
     */
    public int getTotalVIPSeatsSold() {
        return this.totalVIPSeatsSold;
    }

    /**
     * @param totalVIPSeatsSold
     * @return none
     */
    public void setTotalVIPSeatsSold(int totalVIPSeatsSold) {
        this.totalVIPSeatsSold = totalVIPSeatsSold;
    }

    /**
     * @param none
     * @return int
     */
    public int getTotalGoldSeatsSold() {
        return this.totalGoldSeatsSold;
    }

    /**
     * @param totalGoldSeatsSold
     * @return none
     */
    public void setTotalGoldSeatsSold(int totalGoldSeatsSold) {
        this.totalGoldSeatsSold = totalGoldSeatsSold;
    }

    /**
     * @param none
     * @return int
     */
    public int getTotalSilverSeatsSold() {
        return this.totalSilverSeatsSold;
    }

    /**
     * @param totalSilverSeatsSold
     * @return none
     */
    public void setTotalSilverSeatsSold(int totalSilverSeatsSold) {
        this.totalSilverSeatsSold = totalSilverSeatsSold;
    }

    /**
     * @param
     * @return int
     */
    public int getTotalBronzeSeatsSold() {
        return this.totalBronzeSeatsSold;
    }

    /**
     * @param totalBronzeSeatsSold
     * @return
     */
    public void setTotalBronzeSeatsSold(int totalBronzeSeatsSold) {
        this.totalBronzeSeatsSold = totalBronzeSeatsSold;
    }

    /**
     * @param none
     * @return int
     */
    public int getTotalGeneralAdmSeatsSold() {
        return this.totalGeneralAdmSeatsSold;
    }

    /**
     * @return none
     * @param totalGeneralAdmSeatsSold
     */
    public void setTotalGeneralAdmSeatsSold(int totalGeneralAdmSeatsSold) {
        this.totalGeneralAdmSeatsSold = totalGeneralAdmSeatsSold;
    }

    /**
     * @param none
     * @return double
     */
    public double getTotalRevenueVIP() {
        return this.totalRevenueVIP;
    }

    /**
     * @param totalRevenueVIP
     * @return none
     */
    public void setTotalRevenueVIP(double totalRevenueVIP) {
        this.totalRevenueVIP = totalRevenueVIP;
    }

    /**
     * @param none
     * @return double
     */
    public double getTotalRevenueGold() {
        return this.totalRevenueGold;
    }

    /**
     * @param totalRevenueGold
     * @return none
     */
    public void setTotalRevenueGold(double totalRevenueGold) {
        this.totalRevenueGold = totalRevenueGold;
    }

    /**
     * @param none
     * @return double
     */
    public double getTotalRevenueSilver() {
        return this.totalRevenueSilver;
    }

    /**
     * @param totalRevenueSilver
     * @return none
     */
    public void setTotalRevenueSilver(double totalRevenueSilver) {
        this.totalRevenueSilver = totalRevenueSilver;
    }

    /**
     * @param none
     * @return double
     */
    public double getTotalRevenueBronze() {
        return this.totalRevenueBronze;
    }

    /**
     * @param totalRevenueBronze
     * @return none
     */
    public void setTotalRevenueBronze(double totalRevenueBronze) {
        this.totalRevenueBronze = totalRevenueBronze;
    }

    /**
     * @param none
     * @return double
     */
    public double getTotalRevenueGeneralAdm() {
        return this.totalRevenueGeneralAdm;
    }

    /**
     * @return none
     * @param totalRevenueGeneralAdm
     */
    public void setTotalRevenueGeneralAdm(double totalRevenueGeneralAdm) {
        this.totalRevenueGeneralAdm = totalRevenueGeneralAdm;
    }

    /**
     * @param none
     * @return double
     */
    public double getTotalRevenueAllTickets() {
        return this.totalRevenueAllTickets;
    }

    /**
     * @param totalRevenueAllTickets
     * @return none
     */
    public void setTotalRevenueAllTickets(double totalRevenueAllTickets) {
        this.totalRevenueAllTickets = totalRevenueAllTickets;
    }

    /**
     * @param none
     * @return double
     */
    public double getExpectedProfit() {
        return this.expectedProfit;
    }

    /**
     * @param expectedProfit
     * @return none
     */
    public void setExpectedProfit(double expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    /**
     * @param actualProfit
     * @return none
     */
    public void setActualProfit(double actualProfit) {
        this.actualProfit = actualProfit;
    }

    /**
     * Gets the total discounts applied to the venue.
     *
     * @return The total discounts applied.
     */
    public Double getDiscounts() {
        return this.discounts;
    }

    /**
     * Sets the total discounts applied to the venue.
     *
     * @param discounts The additional discounts to be applied.
     */
    public void setDiscounts(Double discounts) {
        this.discounts += discounts;
    }

    /**
     * Gets the charity fee for the venue.
     *
     * @return The charity fee for the venue.
     */
    public Double getCharityFee() {
        return this.charityFee;
    }

    /**
     * Sets the charity fee for the venue.
     *
     * @param charityFee The additional charity fee to be applied.
     */
    public void setCharityFee(Double charityFee) {
        this.charityFee += charityFee;
    }

    /**
     * Gets the convenience fee for the venue.
     *
     * @return The convenience fee for the venue.
     */
    public Double getConvenienceFee() {
        return this.convenienceFee;
    }

    /**
     * Sets the convenience fee for the venue.
     *
     * @param convenienceFee The additional convenience fee to be applied.
     */
    public void setConvenienceFee(Double convenienceFee) {
        this.convenienceFee += convenienceFee;
    }

    /**
     * Gets the service fee for the venue.
     *
     * @return The service fee for the venue.
     */
    public Double getServiceFee() {
        return this.serviceFee;
    }

    /**
     * Sets the service fee for the venue.
     *
     * @param serviceFee The additional service fee to be applied.
     */
    public void setServiceFee(Double serviceFee) {
        this.serviceFee += serviceFee;
    }

    /**
     * Decrements the total revenue for general admission tickets.
     *
     * @param num The amount to decrement from the total revenue for general admission tickets.
     */
    public void decrementTotalRevenueGeneralAdm(double num) {
        this.totalRevenueGeneralAdm += num;
    }

    /**
     * Decrements the total revenue for bronze tickets.
     *
     * @param num The amount to decrement from the total revenue for bronze tickets.
     */
    public void decrementTotalRevenueBronze(double num) {
        this.totalRevenueBronze += num;
    }

    /**
     * Decrements the total revenue for silver tickets.
     *
     * @param num The amount to decrement from the total revenue for silver tickets.
     */
    public void decrementTotalRevenueSilver(double num) {
        this.totalRevenueSilver += num;
    }

    /**
     * Decrements the total revenue for gold tickets.
     *
     * @param num The amount to decrement from the total revenue for gold tickets.
     */
    public void decrementTotalRevenueGold(double num) {
        this.totalRevenueGold += num;
    }

    /**
     * Decrements the total revenue for VIP tickets.
     *
     * @param num The amount to decrement from the total revenue for VIP tickets.
     */
    public void decrementTotalRevenueVIP(double num) {
        this.totalRevenueVIP += num;
    }

    /**
     * Decrements the total revenue for all ticket types.
     *
     * @param num The amount to decrement from the total revenue for all ticket types.
     */
    public void decrementTotalRevenue(double num) {
        this.totalRevenueAllTickets -= num;
    }

    /**
     * Decrements the total discounts applied to the venue.
     *
     * @param num The amount to decrement from the total discounts applied.
     */
    public void decrementDiscounts(double num) {
        this.discounts += num;
    }

    
    /**
     * Calculates and returns the amount of VIP seats available based on capacity and availability.
     *
     * @return The number of available VIP seats.
     */

    public int calculateAvailableVIPSeats() {
        int capacityInt = capacity;
        double pctSeatsUnavailableDouble = pctSeatsUnavailable;
        double vipPctDouble = vipPct;

        int seatsUnavailable = (int) ((pctSeatsUnavailableDouble / 100) * capacityInt);
        int vipSeatsAvailable = (int) ((vipPctDouble / 100) * (capacityInt - seatsUnavailable));

        return vipSeatsAvailable;
    }

    /**
     * Calculates and returns the amount of Gold seats available based on capacity and availability.
     *
     * @return The number of available Gold seats.
     */
    public int calculateGoldSeatsAvailable() {
        int capacityInt = capacity;
        double pctSeatsUnavailableDouble = pctSeatsUnavailable;
        double goldPctDouble = goldPct;

        int seatsUnavailable = (int) ((pctSeatsUnavailableDouble / 100) * capacityInt);
        int goldSeatsAvailable = (int) ((goldPctDouble / 100) * (capacityInt - seatsUnavailable));

        return goldSeatsAvailable;
    }

    /**
     * Calculates and returns the amount of Silver seats available based on capacity and availability.
     *
     * @return The number of available Silver seats.
     */
    public int calculateSilverSeatsAvailable() {
        int capacityInt = capacity;
        double pctSeatsUnavailableDouble = pctSeatsUnavailable;
        double silverPctDouble = silverPct;

        int seatsUnavailable = (int) ((pctSeatsUnavailableDouble / 100) * capacityInt);
        int silverSeatsAvailable = (int) ((silverPctDouble / 100) * (capacityInt - seatsUnavailable));

        return silverSeatsAvailable;
    }

    /**
     * Calculates and returns the amount of Bronze seats available based on capacity and availability.
     *
     * @return The number of available Bronze seats.
     */
    public int calculateBronzeSeatsAvailable() {
        int capacityInt = capacity;
        double pctSeatsUnavailableDouble = pctSeatsUnavailable;
        double bronzePctDouble = bronzePct;

        int seatsUnavailable = (int) ((pctSeatsUnavailableDouble / 100) * capacityInt);
        int bronzeSeatsAvailable = (int) ((bronzePctDouble / 100) * (capacityInt - seatsUnavailable));

        return bronzeSeatsAvailable;
    }

    /**
     * Calculates and returns the amount of General Admission seats available based on capacity and availability.
     *
     * @return The number of available General Admission seats.
     */
    public int calculateGeneralAdmissionSeatsAvailable() {
        int capacityInt = capacity;
        double pctSeatsUnavailableDouble = pctSeatsUnavailable;
        double generalAdmissionPctDouble = generalAdmissionPct;

        int seatsUnavailable = (int) ((pctSeatsUnavailableDouble / 100) * capacityInt);
        int generalAdmissionSeatsAvailable = (int) ((generalAdmissionPctDouble / 100) * (capacityInt - seatsUnavailable));

        return generalAdmissionSeatsAvailable;
    }




    public void printMoneyRaised(Event event){
        System.out.println("______________________________________________________");
        System.out.println("The amount of money gained by TICKETMINER on event: " + event.getName() + " -- [ID #" + event.getEventID() + "]");
    
        // Round each fee to two decimal places
        double serviceFee = getServiceFee();
        double convenienceFee = getConvenienceFee();
        double charityFee = getCharityFee();
    
        System.out.println("Service fees collected: $" + String.format("%.2f", serviceFee));
        System.out.println("Convenience fees collected: $" + String.format("%.2f", convenienceFee));
        System.out.println("Charity fees collected: $" + String.format("%.2f", charityFee));
    
        // Calculate and round the total fees collected
        double totalFeesCollected = serviceFee + convenienceFee + charityFee;
        System.out.println("Total fees collected: $" + String.format("%.2f", totalFeesCollected));
    }
    
} // end of file
