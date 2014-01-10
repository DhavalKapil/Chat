/**
 * A chatting software implemented in java
 * @author Dhaval Kapil
 * MIT license http://www.opensource.org/licenses/mit-license.php
 */

package dhaval.client;

import javax.swing.*;
import dhaval.accessory.*;

public class Client
{
	public static void main(String[] args)
	{	
		try
		{	// Setting Look and Feel
			//UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			
			// Declaring variable
			String name, host;
			
			// Taking input for the above variables
			name = JOptionPane.showInputDialog("Enter your name:");
			host = JOptionPane.showInputDialog("Enter ip-address/domain-name for server");
			
			// Checking for input
			if(name==null || host==null)
				System.exit(0);
			if(name.equals("") || host.equals(""))
				throw new Exception("Invalid name or address!");
			
			// Creating SystemTrayHandler
			SystemTrayHandler handler = new SystemTrayHandler();
			
			// Creating connection
			Connection con = new Connection(host, name, handler);
			con.connect();
			
			// Initializing GUI
			JFrame frame = new ChatGUI(name, con);
			frame.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
}
