import com.zeroc.Ice.*;

public class Server {
    public static void main(String[] args) {
        // 1. Initialize the Ice communicator within a try-with-resources block
        try (Communicator communicator = Util.initialize(args)) {

            // 2. Create an Object Adapter listening on port 5678 (TCP)
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints(
                "SimplePrinterAdapter", "default -p 5678"
            );

            // 3. Create a servant (the implementation object) and add it to the adapter
            PrinterI servant = new PrinterI();
            adapter.add(servant, Util.stringToIdentity("SimplePrinter"));

            // 4. Activate the adapter so it starts accepting requests
            adapter.activate();

            System.out.println("Server is running on port 5678. Press Ctrl+C to stop.");

            // 5. Block the main thread until the communicator is destroyed
            communicator.waitForShutdown();
        }
    }
}
