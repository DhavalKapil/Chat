/**
 * A chatting software implemented in java
 * @author Dhaval Kapil
 * MIT license http://www.opensource.org/licenses/mit-license.php
 */

package dhaval.client;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

class ChatGUI extends JFrame implements ActionListener, WindowListener, Runnable
{
	// Instance variables
	private String name;
	private Connection con;
	private boolean open = true;
	
	// GUI instance variables
	private JPanel middle;
	private JPanel bottom;
	private JScrollPane sp;
	private JTextArea screen;
	private JTextField input;
	private BorderLayout layout;
	private JButton signOut;
	
	// Static variables
	private static final long serialVersionUID = 1L;
    private static final Dimension screenSize;
    private static final Dimension dlgSize;
    private static int dlgPosX;
    private static int dlgPosY;
    
    // Initializing static variables
    static 
    {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dlgSize = new Dimension(260, 350);
        dlgPosX = screenSize.width / 2 - dlgSize.width / 2;
        dlgPosY = screenSize.height / 2 - dlgSize.height / 2;
    }
    
	// Constructor
	public ChatGUI(String name, final Connection con)
	{	
		// Initializing values for instance variables
		this.name = name;
		this.con = con;
        
        // Setting layout
        layout = new BorderLayout();
		
		// JFrame properties
		this.setTitle("Welcome: " + this.name);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ChatGUI.class.getResource("Icon.png")));
		this.setLayout(layout);
		this.addWindowListener(this);
		this.setSize(dlgSize);
		this.setLocation(dlgPosX, dlgPosY);
		this.setResizable(false);
		
		// Initializing GUI instance variables		
		middle = new JPanel();
		bottom = new JPanel();
		screen = new JTextArea(15, 20);
		sp = new JScrollPane(screen);
		input = new JTextField(20);
		signOut = new JButton("SIGN OUT");
		
		// Setting functionality
		screen.setEditable(false);
		screen.setLineWrap(true);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        input.addActionListener(this);
        signOut.addActionListener( new ActionListener()	{
        	public void actionPerformed(ActionEvent ae)
        	{	
        		// Closing the connection
        		try
        		{	open = true;
        			
        			con.close();
        		
        			// Exiting application
        			System.exit(0);
        		}
        		catch(Exception e){}
        	}
        });
        
        // Adding components to panels
        middle.add(sp);
        bottom.add(input);
        bottom.add(signOut);
        
        // Adding panels to main frame
        this.add(middle, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.CENTER);
        
        // Running the screen thread
        Thread screenThread = new Thread(this);
        screenThread.start();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		// Sending message
		try
		{	
			if(input.getText().equals("") || input.getText()==null)
				return;
			this.con.send(input.getText());
		}
		catch(Exception e){}
		
		// Clearing input text field
		this.input.setText("");
	}
	
	// Receiving chat messages from the server
	public void run()
	{
		try
		{
			while(open)
			{
				// Getting the message
				screen.append(con.receive() + "\n");
				sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum() + 4);
			}
		}
		catch(Exception e){}
	}
	
	public void windowClosing(WindowEvent windowevent)
    {
		// Closing the connection
		try
		{	con.close();
			System.exit(0);
		}
		catch(Exception e){}
		
		// Exit
    }
	public void windowClosed(WindowEvent windowevent){}
    public void windowOpened(WindowEvent windowevent){}
    public void windowActivated(WindowEvent windowevent){}
    public void windowDeactivated(WindowEvent windowevent){}
    public void windowIconified(WindowEvent windowevent){}
    public void windowDeiconified(WindowEvent windowevent){}
	
}