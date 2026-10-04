import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    int add(int firstNumber, int secondNumber) throws RemoteException;
}