import java.util.*;
import java.math.*;
import java.io.*;
import java.net.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;

    public Server(int port) {
        try {
            
        }
        catch(Exception i) {
            System.out.println(i)
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }
}

class ServerThread extends Thread {
    int a = 0, b = 0, ans = 0;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    Socket socket = null;
    String clientnum = "";

    public ServerThread(Socket s, String clientnum) {
        socket = s;
        this.clientnum = clientnum;
    }
     public void run() {
         try {
             in = new DataInputStream(socket.getInputStream());
             out = new DataOutputStream(socket.getOutputStream());

             a = in.readInt();
             b = in.readInt();
             ans = (int) Math.pow(a,b);

             System.out.println("Answer calculated is " + ans);
             out.writeInt(ans);
         }
         catch(IOException ie) {
             System.out.println("socket close error");
         }
     }
}