import java.util.*;
import java.io.*;
import java.net.*;

public class serverUDP {
    public static void main(String args[]) {

        DatagramSocket datagramSocket = new DatagramSocket(1234);
        byte[] clientData = new byte[65535];
        DatagramPacket datagramPacketRecieve = null;

        try {
            while(true) {
                datagramPacketRecieve = new DatagramPacket(clientData, clientData.length);
                datagramSocket.receive(datagramPacketRecieve);

                String s = byteToString(clientData);

                if(s.equals('bye')) {
                    System.out.println("Client is exitting...byee");
                    break;
                }

                System.out.println(s);
                clientData = new byte[65535];
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // function to convert bytes array to string
    public static String byteToString(byte[] a) {
        // stringbuilder in java is a mutable sequence of characters

        if(a == null) {
            return null;
        }

        StringBuilder res = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            res.append((char) a[i]);
            i++;
        }
        return res.toString();
    }
}

