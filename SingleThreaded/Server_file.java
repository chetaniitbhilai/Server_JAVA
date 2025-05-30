import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_file {

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

                    // Send file.txt content
                    BufferedReader fileReader = new BufferedReader(new FileReader("file.txt"));
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        toClient.println(line);
                    }
                    fileReader.close();
                    toClient.println("EOF"); // Optional: indicate end of file

                    // Receive a line from client (after sending file)
                    String clientResponse = fromClient.readLine();
                    System.out.println("Received from client: " + clientResponse);

                    fromClient.close();
                    toClient.close();
                    acceptedConnection.close();
                } catch (IOException e) {
                    System.out.println("No client connected within timeout.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server_file server = new Server_file();
        server.run();
    }
}
