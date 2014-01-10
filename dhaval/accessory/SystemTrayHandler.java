/**
 * A chatting software implemented in java
 * @author Dhaval Kapil
 * MIT license http://www.opensource.org/licenses/mit-license.php
 */

package dhaval.accessory;

/**
 * This class is used to interact with the system tray and display 
 * notification.
 */

import java.awt.*;
import java.awt.event.*;

public class SystemTrayHandler
{
	// Instance Variables
	private final TrayIcon trayIcon;
	private Image image;
	
	// Constructor
	public SystemTrayHandler()
	throws Exception
	{
		if(SystemTray.isSupported())
		{
			// Getting a reference to the system tray
			SystemTray tray = SystemTray.getSystemTray();
			
			// Creating pop up menu
			PopupMenu m = new PopupMenu();
	        MenuItem i = new MenuItem("About");
	        i.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent ae)
	            {
	                //Opening Dialog Box
	            	AboutDialog about = new AboutDialog();
	            	about.setVisible(true);
	            }

	        });
	        m.add(i);
	        
	        // Initializing image
	        image = Toolkit.getDefaultToolkit().getImage(SystemTrayHandler.class.getResource("Icon.png"));

			// Creating the tray icon
			trayIcon = new TrayIcon(image, "Dhaval's Chat Application", m);
			
			// Tray icon propertied
			trayIcon.setToolTip("Dhaval's Chat Application");
			trayIcon.setImageAutoSize(true);
			
			tray.add(trayIcon);
		}
		else
		{
			// TrayIcon is not supported.....
			trayIcon = null;
			
			// Throwing exception
			throw new Exception("Tray Icon Not Supported!!");
		}
	}
	
	public void displayMessage(String message)
	{
		// Displaying message
		trayIcon.displayMessage("Notifications", message, TrayIcon.MessageType.INFO);
	}
}