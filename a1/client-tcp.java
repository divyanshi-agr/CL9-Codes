import java.util.*;
import java.net.*;
import java.math.*;
import java.io.*;

public class Client {
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try{
            int a = 0, b = 0;
            while(a != -1) {
                socket = new Socket(address, port);
                System.out.println("Connected");

                //takes i/p from terminal
                in = new DataInputStream(socket.getInputStream());

                //sends o/p to socket
                out = new DataOutputStream(socket.getOutputStream());

                Scanner sc = new Scanner(System.in);

                System.out.println("Enter base");
                a = sc.nextInt();
                System.out.println("Enter power");
                b = sc.nextInt();

                System.out.println("Sending base and power to server...");

                out.writeInt(a);
                out.writeInt(b);

                System.out.println("Answer received from server : ", in.readInt());

                System.out.println("Closing connection!");

                out.close();
                socket.close();
                sc.close();
            }
        }
        catch(Exception i) {
            System.out.println(i)
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000)
    }
}