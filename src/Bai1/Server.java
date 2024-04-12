import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                Worker worker = new Worker(clientSocket);
                Thread workerThread = new Thread(worker);
                workerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
