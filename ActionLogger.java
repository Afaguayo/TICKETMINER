import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * ActionLogger is a utility class for logging information, warnings, and errors to a file.
 * It provides a convenient way to record actions and events in your application.
 *
 * The log messages are written to the "event_log.txt" file in the current directory.
 *
 * Usage:
 * - Use logInfo for general information messages.
 * - Use logWarning for warning messages.
 * - Use logError for error messages.
 *
 * @author Angel, Caleb, Christian, & Javier
 * @since November 5, 2023
 */
public class ActionLogger {
    private static final Logger logger = Logger.getLogger("ActionLogger");

    static {
        try {
            FileHandler fileHandler = new FileHandler("event_log.txt", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Remove default console handler
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void logError(String message) {
        logger.log(Level.SEVERE, message);
    }
}