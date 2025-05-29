import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() {
        int port = 8010;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000); // 10 seconds timeout
            System.out.println("Server is listening on port " + port);

            while (true) {
                try {
                    Socket acceptedConnection = serverSocket.accept();
                    System.out.println("Connection accepted from " + acceptedConnection.getRemoteSocketAddress());

                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true); // auto-flush
                    BufferedReader fromClient = new BufferedReader(
                            new InputStreamReader(acceptedConnection.getInputStream()));


                    toClient.println("Hello from the server");

                    String line = fromClient.readLine();
                    
                    System.out.println("Received from client: " + line);
                    fromClient.close();
                    toClient.close();
                    acceptedConnection.close();
                } catch (IOException e) {
                    System.out.println("No client connected within timeout.");
                    // If you want to exit on timeout, uncomment:
                    // break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
