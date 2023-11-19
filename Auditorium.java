/**
 /**
 * This is the Auditorium class and it extends Venue class
 *
 * @author Angel, Caleb, Chris & Javier
 * @since November 19, 2023
 */
class Auditorium extends Venue {

    /**
     * @param venueName            The name of the auditorium venue.
     * @param venueType            The type of the auditorium venue.
     * @param pctSeatsUnavailable   The percentage of seats that are unavailable in the auditorium.
     * @param capacity              The maximum seating capacity of the auditorium.
     * @param vipPct                The percentage of VIP seats in the auditorium.
     * @param goldPct               The percentage of gold seats in the auditorium.
     * @param silverPct             The percentage of silver seats in the auditorium.
     * @param broncePct             The percentage of bronze seats in the auditorium.
     * @param generalAdmissionPct   The percentage of general admission seats in the auditorium.
     * @param reservedExtraPct      The percentage of extra reserved seats in the auditorium.
     */
    public Auditorium(String venueName,int pctSeatsUnavailable, String venueType, int capacity, int cost, double vipPct,
    double goldPct, double silverPct, double bronzePct, double generalAdmissionPct,double reservedExtraPct, boolean fireworksPlanned, int fireworksCost) {
        super(venueName, pctSeatsUnavailable, venueType, capacity, cost, vipPct, goldPct, silverPct, bronzePct, generalAdmissionPct, reservedExtraPct, fireworksPlanned, fireworksCost);
    }

}
