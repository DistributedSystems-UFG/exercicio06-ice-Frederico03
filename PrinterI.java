import com.zeroc.Ice.Current;

/**
 * Server-side implementation of the Demo.Printer interface.
 * This class provides the actual logic for all Printer operations.
 */
public class PrinterI implements Demo.Printer {

    // Tracks how many print operations (printString + printLines) were performed
    private int printCount = 0;

    /**
     * Prints a single string and returns a confirmation message.
     */
    @Override
    public String printString(String s, Current current) {
        printCount++;
        System.out.println("[Server] printString called: " + s);
        return "Printed: \"" + s + "\"";
    }

    /**
     * Prints multiple lines and returns how many lines were printed.
     */
    @Override
    public int printLines(String[] lines, Current current) {
        printCount++;
        System.out.println("[Server] printLines called with " + lines.length + " line(s):");
        for (String line : lines) {
            System.out.println("  > " + line);
        }
        return lines.length;
    }

    /**
     * Returns the total number of print operations performed since last clear.
     */
    @Override
    public int countPrints(Current current) {
        System.out.println("[Server] countPrints called, current count: " + printCount);
        return printCount;
    }

    /**
     * Resets the print counter.
     */
    @Override
    public void clear(Current current) {
        System.out.println("[Server] clear called. Resetting count from " + printCount + " to 0.");
        printCount = 0;
    }
}
