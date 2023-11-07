import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class AdministratorActionsTest {
    private List<Event> events;
    private AdministratorActions adminActions;


    /**
     * Sets up the test environment before running each test method. It initializes the 'events' list
     * and the 'adminActions' object for testing.
     */
    @Before
    public void setUp() {
        events = new ArrayList<>();
        adminActions = new AdministratorActions();
    }


    /**
     * Tests the 'inquireId' method of the 'AdministratorActions' class. It creates a sample event,
     * captures the expected output, resets the output stream, and then captures the printed output
     * after calling the method. Finally, it asserts that the printed output matches the expected output.
     */
    @Test
    public void testInquireId() {
        List<Event> events = new ArrayList<>();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setOut(new PrintStream(outContent1));

        // Create a sample event
        Stadium stadium1 = new Stadium("Test Stadium", 5, "open air", 1000, 1000, 5, 5, 5, 5, 5, 5, true, 555);
        Sport sport1 = new Sport(1, "Concert", "Concert A", "10/31/23", "7:00 PM", 100.0, 50.0, 30.0, 20.0, 10.0, stadium1);
        events.add(sport1);
        
        // Capture the printed output
        events.get(0).printInfo();
        String expectedOutput = outContent.toString().trim();

        // Reset the output stream
        outContent.reset();

        // Capture the printed output
        adminActions.inquireId(events, 1);
        String printedOutput = outContent.toString().trim();

        assertEquals(expectedOutput, printedOutput);
    }


    /**
     * Tests the 'inquireByname' method of the 'AdministratorActions' class. It creates a sample event,
     * captures the expected output, resets the output stream, and then captures the printed output
     * after calling the method. Finally, it asserts that the printed output matches the expected output.
     */
    @Test
    public void testInquireByName() {
        List<Event> events = new ArrayList<>();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create a sample event
        Stadium stadium1 = new Stadium("Test Stadium", 5, "open air", 1000, 1000, 5, 5, 5, 5, 5, 5, true, 555);
        Sport sport1 = new Sport(1, "Concert", "Concert A", "10/31/23", "7:00 PM", 100.0, 50.0, 30.0, 20.0, 10.0, stadium1);
        events.add(sport1);

        // Capture the printed output
        AdministratorActions.inquireByname(events, "Concert A");
        String printedOutput = outContent.toString().trim();

        // Reset the output stream
        outContent.reset();

        // Capture the printed output
        events.get(0).printEventData();
        String expectedOutput = outContent.toString().trim();

        assertEquals(expectedOutput, printedOutput);
    }





    


}
