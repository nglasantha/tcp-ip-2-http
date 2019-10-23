package JavaServer;

import java.net.*;
import java.io.*;
import java.util.Date;

//ref https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = 8080;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));


                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.length() == 0){
                        break;
                    }
                    System.out.println(line);
                    writer.println(line);
                }
                writer.println("Date :" +  new Date());
                writer.println();
                reader.close();
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}