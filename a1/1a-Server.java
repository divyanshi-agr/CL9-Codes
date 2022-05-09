import java.util.*;
import java.net.*;
import java.io.*;
import java.math.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    

    public Server(int port)
    {
        try{
            //create new socket for that port
            server = new ServerSocket(port);
            System.out.println("Server Started");

            int i=1; //represents no.of clients in connection
            while(true)
            {
                System.out.println("Waiting for a client");
                socket = server.accept();

                System.out.println("Client " + i + " accepted");

                ServerThread st = new ServerThread(socket, "Client "+String.valueOf(i));
                i++;
                st.start();
            }
        }
        catch(Exception i)
		{
			System.out.println(i);
		}
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}

class ServerThread extends Thread{
    int a =0,b=0,ans=0;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    Socket socket = null;
    String clientnum="";

    public ServerThread(Socket s, String clientnum)
    {
        socket = s;
        this.clientnum = clientnum;
    }

    public void run(){
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            a = in.readInt();
            b = in.readInt();
            ans = (int) Math.pow(a, b);

            System.out.println("Answer calculated is "+ans);
            out.writeInt(ans);
        }
        catch (IOException ie) {
            System.out.println("socket close error");
        }
    }

}
