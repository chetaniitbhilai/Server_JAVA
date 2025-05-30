import java.io.*;
import java.net.Socket;

public class Client_file {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8010;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String line;
            System.out.println("Received from server:");
            while ((line = in.readLine()) != null) {
                if (line.equals("EOF")) break; // Optional check for end
                System.out.println(line);
            }

            out.println("Thanks for the file!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
