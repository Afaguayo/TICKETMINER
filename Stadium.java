/**
 * This is the Stadium class and it extends Venue class
 * 
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
class Stadium extends Venue {

    /**
     * Constructs a Stadium object with the specified venue details.
     *
     * @param venueName            The name of the stadium venue.
     * @param venueType            The type of the stadium venue.
     * @param pctSeatsUnavailable   The percentage of seats that are unavailable in the stadium.
     * @param capacity              The maximum seating capacity of the stadium.
     * @param vipPct                The percentage of VIP seats in the stadium.
     * @param goldPct               The percentage of gold seats in the stadium.
     * @param silverPct             The percentage of silver seats in the stadium.
     * @param broncePct             The percentage of bronze seats in the stadium.
     * @param generalAdmissionPct   The percentage of general admission seats in the stadium.
     * @param reservedExtraPct      The percentage of extra reserved seats in the stadium.
     */
    public Stadium(String venueName,int pctSeatsUnavailable, String venueType, int capacity, int cost, double vipPct,
    double goldPct, double silverPct, double bronzePct, double generalAdmissionPct,double reservedExtraPct, boolean fireworksPlanned, int fireworksCost) {

            super(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct, silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksPlanned, fireworksCost);
}



}
