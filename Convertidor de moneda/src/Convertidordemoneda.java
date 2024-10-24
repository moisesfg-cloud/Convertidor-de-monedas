import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONObject;

public class Convertidordemoneda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar las monedas y cantidad al usuario
        System.out.print("Ingrese la moneda base (por ejemplo, USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda destino (por ejemplo, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad que desea convertir: ");
        double amount = scanner.nextDouble();

        // Clave API (reemplazar por una clave válida)
        String apiKey = "a6ee2980428df772a0bb4c489355af1c";

        // URL de la API
        String apiUrl = "https://api.currencylayer.com/convert?access_key=" + apiKey
                + "&from=" + baseCurrency + "&to=" + targetCurrency + "&amount=" + amount;

        try {
            // Crea el  HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create el reqiuerimiento
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()  // Use POST() or other methods if needed
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convert the response body to JSONObject
            JSONObject jsonResponse = new JSONObject(response.body());

            // Display the values
            System.out.println("El valor " + "(" + baseCurrency + ")" + " corresponde al valor final de: " + jsonResponse.get("result") + "(" + targetCurrency + ")");

        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
        scanner.close();
    }
}