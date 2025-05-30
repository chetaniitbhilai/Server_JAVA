import java.io.*;
import java.net.Socket;

public class Client_file {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8010;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
