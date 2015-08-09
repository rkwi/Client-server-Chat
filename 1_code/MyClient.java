import java.net.*;
import java.io.*;

public class MyClient {
	private Socket s;
	private DataInputStream in;
	private DataOutputStream out;
	private String text;
	public MyClient(int port) {
		try {
			s = new Socket("localhost",port);
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		}
		catch (UnknownHostException e)
		{
			System.out.println("Cannot find server!");
		}
		catch (IOException ioe)
		{
		}
	}
	
	public boolean write(String text) {
		boolean b = true;
		try {
			out.writeUTF(text);
			out.flush();
		}
		catch (IOException ioe)
		{
			b = false;
		}
		return b;
	}
	
	public boolean read() {
		boolean b = true;
		try {
			text = in.readUTF();
		}
		catch(IOException ioe) {
			b = false;
		}
		return b;
	}
	
	public String getText() {
		return text;
	}
}
