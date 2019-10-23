package JavaHttpServer;

//https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        int portNumber = 8081;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));


                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println("HTTP/1.1 200"); // Version & status code
               // writer.println("Content-Type: text/plain"); // The type of data
                writer.println("Content-Type: text/html"); // The type of data
                writer.println("Connection: close"); // Will close stream


                String line;
                String route = "/";
                int count = 1;

                while ((line = reader.readLine()) != null) {
                    if (line.length() == 0){
                        break;
                    }

                    if(count == 1){
                        route = line.split(" ") [1];
                        System.out.println(" *******" + route);
                    }
                    System.out.println(line);
                    writer.println(line);
                    count ++ ;
                }
                writer.println(); // End of headers

                if(route.equalsIgnoreCase("/test")){

                    writer.println("<html><body>" +
                            "Hello Angular after Dark" +
                            "<h1 style=\"color:blue;text-align:center;\">This is a heading</h1>\n" +
                            "<p style=\"color:red;\">This is a paragraph.</p>" +
                            "</body></html>");
                }else if(route.equalsIgnoreCase("/")){

                    writer.println("<html><body>" +
                            "<h1>Hello Angular after Dark</h1></body></html>");
                }else{
                    writer.println("<html><body>" +
                            "<h1>Page not found</h1></body></html>");
                }
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
