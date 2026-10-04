import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 5000;
        
        System.out.println("Server is starting and listening on port " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            
            // Wait and accept incoming client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getRemoteSocketAddress());
            
            // Setup input stream to read data from client
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            // Setup output stream to send data to client
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            // Read client message
            String clientMessage = reader.readLine();
            System.out.println("Received from client: " + clientMessage);
            
            // Send response back
            String response = "Hello Client! Your message was received.";
            writer.println(response);
            System.out.println("Response sent to client.");
            
            // Close connection
            socket.close();
            System.out.println("Connection closed.");
            
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}