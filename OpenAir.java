 /**
 * This is the OpenAir class and it extends Venue class
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
class OpenAir extends Venue {

    /**
     * @param venueName            The name of the open-air venue.
     * @param venueType            The type of the open-air venue.
     * @param pctSeatsUnavailable   The percentage of seats that are unavailable in the open-air venue.
     * @param capacity              The maximum seating capacity of the open-air venue.
     * @param vipPct                The percentage of VIP seats in the open-air venue.
     * @param goldPct               The percentage of gold seats in the open-air venue.
     * @param silverPct             The percentage of silver seats in the open-air venue.
     * @param broncePct             The percentage of bronze seats in the open-air venue.
     * @param generalAdmissionPct   The percentage of general admission seats in the open-air venue.
     * @param reservedExtraPct      The percentage of extra reserved seats in the open-air venue.
     */
    public OpenAir(String venueName,int pctSeatsUnavailable, String venueType, int capacity, int cost, double vipPct,
                    double goldPct, double silverPct, double bronzePct, double generalAdmissionPct,double reservedExtraPct, boolean fireworksPlanned, int fireworksCost) {
            super(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct, silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksPlanned, fireworksCost);
}


}
