import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String serverAddress = "localhost";

            Registry registry =
                    LocateRegistry.getRegistry(serverAddress, 1099);

            Calculator calculator = (Calculator)
                    registry.lookup("CalculatorService");

            System.out.print("Enter the first number: ");
            int firstNumber = scanner.nextInt();

            System.out.print("Enter the second number: ");
            int secondNumber = scanner.nextInt();

            int result = calculator.add(firstNumber, secondNumber);

            System.out.println("Result received from server: " + result);
        } catch (Exception exception) {
            System.err.println("Client error: "
                    + exception.getMessage());
            exception.printStackTrace();
        }
    }
}