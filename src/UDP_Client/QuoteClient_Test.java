package UDP_Client;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;

public class QuoteClient_Test{
	public static void main(String[] args) throws IOException {
		//Define important variables
		int port;
		InetAddress address;
		DatagramSocket socket = null;
		DatagramPacket packet;
		byte[] sendBuf = new byte[256];
		
		//Get a Datagram Socket
		socket = new DatagramSocket();
		
		//Send Request
		byte[] buf = new byte[256];
		address = InetAddress.getByName("10.30.2.13");
		packet = new DatagramPacket(buf, buf.length, address, 8697);
		socket.send(packet);
		
		//Get Response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

	    //Display Response
        float receivedFloat = ByteBuffer.wrap(packet.getData()).getFloat();
//      String received = new String(packet.getData(), 0, packet.getLength());
//      System.out.println("Quote of the Moment: " + received);
        System.out.println(receivedFloat);
    
        socket.close();
	}
}
