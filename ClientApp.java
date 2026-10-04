import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class ClientApp {
    public static void main(String[] args) {
        try {
	    Scanner scan=new Scanner(System.in);
	    System.out.println("Enter USD Amount");
            double inputRupees = scan.nextDouble();
            String url = "http://localhost:8080/api/convert?rupees=" + inputRupees;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("HTTP Status Code: " + response.statusCode());
            System.out.println("Response Payload: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}