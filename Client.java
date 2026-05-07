import com.zeroc.Ice.*;

import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // 1. Initialize the Ice communicator within a try-with-resources block
        try (Communicator communicator = Util.initialize(args)) {

            // 2. Create a proxy to the remote 'Printer' object
            // Replace '98.90.53.6' with the actual IP of your ICE server
            ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -h 98.90.53.6 -p 5678");

            // 3. Downcast the proxy to the Printer interface
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            // 4. Call printString (original method)
            String response = printer.printString("Hello from Goiania!");
            System.out.println("printString response: " + response);

            // 5. Call countPrints (new method) - before printing more
            int count = printer.countPrints();
            System.out.println("Print count after printString: " + count);

            // 6. Call printLines (new method) - print multiple lines
            List<String> lines = Arrays.asList(
                "Line 1: Distributed Systems - UFG",
                "Line 2: ZeroC ICE Framework",
                "Line 3: Exercise 06"
            );
            int linesCount = printer.printLines(lines.toArray(new String[0]));
            System.out.println("printLines printed " + linesCount + " line(s)");

            // 7. Call countPrints again to see accumulated total
            count = printer.countPrints();
            System.out.println("Total print count: " + count);

            // 8. Call clear (new method) - reset the counter
            printer.clear();
            System.out.println("Printer history cleared.");

            // 9. Verify counter was reset
            count = printer.countPrints();
            System.out.println("Print count after clear: " + count);

        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}
