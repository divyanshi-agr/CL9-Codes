import java.io.*;
import java.net.*;
import java.util.*;

public class clientUDP {

    public static void main(String []args) {

        try {

            Scanner sc = new Scanner(System.in);

            DatagramSocket datagramSocket = new DatagramSocket();

            InetAddress ip = InetAddress.getLocalHost();
            byte[] buff = null;

            while(true) {
                
                System.out.print("Enter your message for server : ");
                
                String msg = sc.nextLine();

                buff = msg.getBytes();

                DatagramPacket datagramPacketSend = new DatagramPacket(buff, buff.length, ip, 1234);
                datagramSocket.send(datagramPacketSend);
                if(msg.equals("bye")) 
                    break;

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}