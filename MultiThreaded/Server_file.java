import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server_file {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try (BufferedReader fileReader = new BufferedReader(new FileReader("file.txt"));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server_file server = new Server_file();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> server.getConsumer().accept(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
