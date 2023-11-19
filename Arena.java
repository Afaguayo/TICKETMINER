/**
 * Constructs an Arena object with the specified venue details.
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
class Arena extends Venue {


    /**
     * @param venueName            The name of the arena venue.
     * @param venueType            The type of the arena venue.
     * @param pctSeatsUnavailable   The percentage of seats that are unavailable in the arena.
     * @param capacity              The maximum seating capacity of the arena.
     * @param vipPct                The percentage of VIP seats in the arena.
     * @param goldPct               The percentage of gold seats in the arena.
     * @param silverPct             The percentage of silver seats in the arena.
     * @param broncePct             The percentage of bronze seats in the arena.
     * @param generalAdmissionPct   The percentage of general admission seats in the arena.
     * @param reservedExtraPct      The percentage of extra reserved seats in the arena.
     * @return
     */
    public Arena(String venueName,int pctSeatsUnavailable, String venueType, int capacity, int cost, double vipPct,
    double goldPct, double silverPct, double bronzePct, double generalAdmissionPct,double reservedExtraPct, boolean fireworksPlanned, int fireworksCost) {
        super(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct, silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksPlanned, fireworksCost);
    }


}
