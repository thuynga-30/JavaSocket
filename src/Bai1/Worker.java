import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



public class Worker implements Runnable {
    private final Socket clientSocket;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    

	@Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            int count=1;
            while(count<=1000){
                out.println(count);
                Thread.sleep(1000);
                count++;// Gửi mỗi giây
            }

            clientSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
