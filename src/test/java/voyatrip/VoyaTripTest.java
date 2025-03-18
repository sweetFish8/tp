package voyatrip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voyatrip.ui.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class VoyaTripTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void handleExit_exitCommand_printGoodbyeMessage() {
        // Simulate user input
        String input = "exit\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent); // Set System.in to use the simulated input

        // Run the main method (or the method that reads input)
        VoyaTrip.main(new String[]{});

        // Verify the output
        String expectedOutput = Message.getWelcomeMessage() + "\n"
                + Message.getGoodbyeMessage();
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void handleInput_invalidCommand_printInvalidCommand() {
        // Simulate user input
        String input = "add trip --name\nexit\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent); // Set System.in to use the simulated input

        // Run the main method (or the method that reads input)
        VoyaTrip.main(new String[]{});

        // Verify the output
        String expectedOutput = Message.getWelcomeMessage() + "\n"
                + Message.getInvalidCommandMessage() + "\n"
                + Message.getGoodbyeMessage();
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void handleInput_addTrip_printAddTripMessage() {
        // Simulate user input
        String input = "add trip --name Japan --start Mon --end Wed --b 10\nexit\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent); // Set System.in to use the simulated input

        // Run the main method (or the method that reads input)
        VoyaTrip.main(new String[]{});

        // Verify the output
        String expectedOutput = Message.getWelcomeMessage() + "\n"
                + "Adding trip\n"
                + Message.getGoodbyeMessage();
        assertEquals(expectedOutput, outContent.toString().trim());
    }
}
