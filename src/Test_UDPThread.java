import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;

public class Test_UDPThread extends Thread {
	DatagramSocket socket;
	BufferedReader in;
	float info1 = 3.14159265359f;
	float info2 = 4.20f;
	
	public Test_UDPThread() throws IOException {
		this("Test_UDP");
	}
	
	public Test_UDPThread(String name) throws IOException {
		super(name);
		socket = new DatagramSocket(8697);
		
		try {
			in = new BufferedReader(new FileReader("loot_drive.txt"));
		}   
		catch (FileNotFoundException e){
			System.err.println("Well, that didn't work...");
		}
	}  
	
	public void run() {
		try {
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
		
//			String dString = null;
//			if (in == null)
//				dString = new Date().toString();
//			else
//				dString = getNextQuote();
//			buf = dString.getBytes();
			
			buf = ByteBuffer.allocate(10).putFloat(info1).array();
		// Lines 32-37 chooses the server response
		
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			packet = new DatagramPacket(buf, buf.length, address, port);
			socket.send(packet);
		// Lines 39-42 get the address and port number from the incoming packet so the server knows where to send the new datagram packet to.
		 } catch (IOException e) {
             e.printStackTrace();
		 }
		socket.close();
	}
	
	protected String getNextQuote() {
		String returnValue;
		try {
			returnValue = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			returnValue = "I guess you're out of quotes... that sucks";
		}
		return returnValue;
	}
}
