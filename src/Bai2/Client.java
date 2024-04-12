package v;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	Scanner nhap = new Scanner(System.in);
    //private static final String User = "Huy" ;
	
	public static void main(String[] args) throws Exception {
        new Client();
    }
	
    public Client() throws Exception {
    	System.out.println("Nhap username: "); 
    	String User = nhap.nextLine();
        Socket socket = new Socket("localhost", 8088);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    String message = in.readLine();
                    if (message != null) {
                        System.out.println(User+ ": "+ message);
                    } else {
                        System.out.println("Server disconnected");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            String message = scanner.nextLine();
            out.writeBytes(message + "\n");
            out.flush();
        }
    }
}