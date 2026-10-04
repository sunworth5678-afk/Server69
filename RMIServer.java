import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            Calculator calculator = new CalculatorImpl();

            // Create the RMI registry on port 1099.
            Registry registry = LocateRegistry.createRegistry(1099);

            // Register the remote object.
            registry.rebind("CalculatorService", calculator);

            System.out.println("RMI server is running...");
        } catch (Exception exception) {
            System.err.println("Server error: "
                    + exception.getMessage());
            exception.printStackTrace();
        }
    }
}