import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        
        System.out.println("Connecting to server at " + hostname + ":" + port + "...");
        try (Socket socket = new Socket(hostname, port)) {
            
            // Setup output stream to send data to server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            // Send a message
            String message = "Hello Server! This is a TCP message.";
            writer.println(message);
            System.out.println("Sent to server: " + message);
            
            // Setup input stream to read server response
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            // Read response
            String serverResponse = reader.readLine();
            System.out.println("Received from server: " + serverResponse);
            
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}