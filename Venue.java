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
     * @param venueName
     * @return none
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    /**
     * @param pctSeatsUnavailable
     * @return none
     */
    public void setPctSeatsUnavailable(int pctSeatsUnavailable) {
        this.pctSeatsUnavailable = pctSeatsUnavailable;
    }

    /**
     * @param venueType
     * @return none
     */
    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    /**
     * @param capacity
     * @return none
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @param cost
     * @return none
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @param vipPct
     * @return none
     */
    public void setVipPct(int vipPct) {
        this.vipPct = vipPct;
    }

    /**
     * @param goldPct
     * @return none
     */
    public void setGoldPct(int goldPct) {
        this.goldPct = goldPct;
    }

    /**
     * @param silverPct
     * @return none
     */
    public void setSilverPct(int silverPct) {
        this.silverPct = silverPct;
    }

    /**
     * @param bronzePct
     * @return none
     */
    public void setBronzePct(int bronzePct) {
        this.bronzePct = bronzePct;
    }

    /**
     * @param generalAdmissionPct
     * @return none
     */
    public void setGeneralAdmissionPct(int generalAdmissionPct) {
        this.generalAdmissionPct = generalAdmissionPct;
    }

    /**
     * @param reservedExtraPct
     * @return none
     */
    public void setReservedExtraPct(int reservedExtraPct) {
        this.reservedExtraPct = reservedExtraPct;
    }

    /**
     * @param fireworksPlanned
     * @return none
     */
    public void setFireworksPlanned(boolean fireworksPlanned) {
        this.fireworksPlanned = fireworksPlanned;
    }

    /**
     * @param fireworksCost
     * @return none
     */
    public void setFireworksCost(int fireworksCost) {
        this.fireworksCost = fireworksCost;
    }

    // venue attributes getters

    /**
     * @param none
     * @return venueName
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * @param none
     * @return pctSeatsUnavailable
     */
    public int getPctSeatsUnavailable() {
        return pctSeatsUnavailable;
    }

    /**
     * @param none
     * @return venueType
     */
    public String getVenueType() {
        return venueType;
    }

    /**
     * @param none
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param none
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param none
     * @return vipPct
     */
    public double getVipPct() {
        return vipPct;
    }

    /**
     * @param none
     * @return goldPct
     */
    public double getGoldPct() {
        return goldPct;
    }

    /**
     * @param none
     * @return silverPct
     */
    public double getSilverPct() {
        return silverPct;
    }

    /**
     * @param none
     * @return bronzePct
     */
    public double getBronzePct() {
        return bronzePct;
    }

    /**
     * @param none
     * @return generalAdmissionPct
     */
    public double getGeneralAdmissionPct() {
        return generalAdmissionPct;
    }

    /**
     * @param none
     * @return reservedExtraPct
     */
    public double getReservedExtraPct() {
        return reservedExtraPct;
    }

    /**
     * @param none
     * @return fireworksPlanned
     */
    public boolean isFireworksPlanned() {
        return fireworksPlanned;
    }

    /**
     * @param none
     * @return fireworksCost
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

    public Double getDiscounts() {
        return this.discounts;
    }

    public void setDiscounts(Double discounts) {
        this.discounts += discounts;
    }


    public Double getCharityFee() {
        return this.charityFee;
    }

    public void setCharityFee(Double charityFee) {
        this.charityFee += charityFee;
    }
    
    public Double getConvenienceFee() {
        return this.convenienceFee;
    }
    
    public void setConvenienceFee(Double convenienceFee) {
        this.convenienceFee += convenienceFee;
    }
    
    public Double getServiceFee() {
        return this.serviceFee;
    }
    
    public void setServiceFee(Double serviceFee) {
        this.serviceFee += serviceFee;
    }

    public void decrementTotalRevenueGeneralAdm(double num) {
        this.totalRevenueGeneralAdm += num;
    }
    
    public void decrementTotalRevenueBronze(double num) {
        this.totalRevenueBronze += num;
    }
    
    public void decrementTotalRevenueSilver(double num) {
        this.totalRevenueSilver += num;
    }
    
    public void decrementTotalRevenueGold(double num) {
        this.totalRevenueGold += num;
    }
    
    public void decrementTotalRevenueVIP(double num) {
        this.totalRevenueVIP += num;
    }
    
    public void decrementTotalRevenue(double num) {
        this.totalRevenueAllTickets -= num;
    }
    
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
        System.out.println("The amount of money gained by TICKETMINER on event : " + event.getName() + " ID: " + event.getEventID());
    
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
