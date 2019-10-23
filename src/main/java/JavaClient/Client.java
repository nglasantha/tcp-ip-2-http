package JavaClient;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 8080;
        try (Socket socket = new Socket(hostName, portNumber)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println("TCP/IP Client");
            writer.println();

            InputStream input = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }


        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
