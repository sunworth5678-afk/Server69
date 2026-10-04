import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class RestServer {

    public static void main(String[] args) throws IOException {
        // Start server on port 8080 with default backlog
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Define endpoints (routes)
        server.createContext("/api/hello", new HelloHandler());
        server.createContext("/api/status", new StatusHandler());
        
        // Use default executor for handling threads
        server.setExecutor(null); 
        System.out.println("REST Service started on port 8080...");
        server.start();
    }

    // Handles: GET http://localhost:8080/api/hello
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = "{\"message\": \"Hello,Kaushal\"}";
                sendResponse(exchange, response, 200, "application/json");
            } else {
                sendResponse(exchange, "Method Not Allowed", 450, "text/plain");
            }
        }
    }

    // Handles: GET http://localhost:8080/api/status
    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{\"status\": \"UP\", \"database\": \"Connected\"}";
            sendResponse(exchange, response, 200, "application/json");
        }
    }

    // Helper method to send JSON or Text HTTP responses
    private static void sendResponse(HttpExchange exchange, String response, int statusCode, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}