import java.net.*;
import java.io.*;

public class MyServer {
	private int port;
	private Socket s;
	private ServerSocket ss;
	private DataInputStream in;
	private DataOutputStream out;
	private String text;
	public MyServer(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port);
			System.out.println("Waiting for client");
			s = ss.accept();
                        System.out.println("Connection established!");
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		}
		catch (IOException ioe)
		{
		}
		text = "";
	}
	
	public int getPort() {
		return port;
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
	
	public String getText() {
		return text;
	}
	
}
