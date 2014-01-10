/**
 * A chatting software implemented in java
 * @author Dhaval Kapil
 * MIT license http://www.opensource.org/licenses/mit-license.php
 */

package dhaval.client;

import java.net.*;
import java.io.*;

import dhaval.accessory.*;

class Connection
{
	// Connecting port
	private static final int port = 9999;
	
	// Official Identification Key
	private static final String key = "56as4fg6+awe84gmm6a5sd4";
	
	// Name of Chatter
	private String name;
	
	// SystemTrayHandler
	private SystemTrayHandler handler;
	
	// Connecting host
	private String host;
	
	// I/O streams
	private BufferedReader br;
	private PrintWriter pw;
	
	// A socket connection
	private Socket s;
	
	// A constructor
	public Connection(String host, String name, SystemTrayHandler handler)
	{
		// Initializing variables
		this.host = host;
		this.name = name;
		this.handler = handler;
	}
	
	// Establishing a connection
	public void connect()
	throws Exception
	{	
		// Creating a socket connection
		this.s = new Socket(host, port);
		
		// Getting I/O streams
		this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.pw = new PrintWriter(s.getOutputStream());
		
		// Notifying that the person has signed up
		this.sendOfficial(name + " has entered the chat room");
	}	
	
	// Sending a chat message
	public void send(String message)
	throws Exception
	{
		// Sending chat message
		pw.println(name + ": " + message);
		pw.flush();
	}
	
	// Sending official message
	public void sendOfficial(String message)
	throws Exception
	{
		// Sending identifications
		pw.println(key);
		pw.flush();

		// Sending message
		pw.println(message);	
		pw.flush();
	}
	
	
	// Receiving message
	public String receive()
	throws Exception
	{	
		// Storing the message
		String message;
		while(true)
		{
			while((message = this.br.readLine())!=null)
			{
				// Checking if the message is a special message	
				if(message.equals(key))
				{	
					message = this.br.readLine();
					
					// Handling the special message
					this.specialMessage(message);
					return message;
				}
				else
				{
					// Returning the special message
					return message;
				}
			}
		}
	
	}
	
	// Special message has been received
	public void specialMessage(String message)
	{
		// Showing the special message on using tray icon
		handler.displayMessage(message);
	}
	
	// Closing connection
	public void close()
	throws Exception
	{	
		// Notifying that the person has signed out
		this.sendOfficial(name + " has exited the chat room");
		
		// Closing I/O streams
		this.br.close();
		this.pw.close();
		
		// Closing socket connection
		this.s.close();
	}
}