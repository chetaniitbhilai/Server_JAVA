import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server_file {
    private final ExecutorService threadPool;

    public Server_file(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("file.txt"));
             PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String line;
            while ((line = fileReader.readLine()) != null) {
                toSocket.println(line);
            }
            toSocket.println("EOF"); // optional EOF indicator

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 8010;
        int poolSize = 10;
        Server_file server = new Server_file(poolSize);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(70000);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                server.threadPool.execute(() -> server.handleClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.threadPool.shutdown();
        }
    }
}
