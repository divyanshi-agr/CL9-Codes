import java.io.*;
import java.util.*;
import java.net.*;

public class serverUDP {

    public static void main(String []args) throws IOException{

        DatagramSocket datagramSocket = new DatagramSocket(1234);
        
        byte[] clientData = new byte[65535];
        DatagramPacket datagramPacketRecieve = null;
        
        try {

            while(true) {

                datagramPacketRecieve = new DatagramPacket(clientData, clientData.length);
                datagramSocket.receive(datagramPacketRecieve);

                String s = data(clientData);
                
                if(s.equals("bye")){
                    System.out.println("Client says bye...EXITING");
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

    public static String data(byte[] a)
	{
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		
		return ret.toString();
	}

}