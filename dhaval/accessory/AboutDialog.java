/**
 * A chatting software implemented in java
 * @author Dhaval Kapil
 * MIT license http://www.opensource.org/licenses/mit-license.php
 */

package dhaval.accessory;

/**
 * This class is used to display the About Dialog
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AboutDialog extends JDialog
{
    //Declaring static variables
    private static final long serialVersionUID = 1L;
    private static final String CHAT_VERSION = "Version: 1.0";
    private static final String ABOUT_STRING = "Developed by Dhaval-Kapil";
    private static final Dimension screenSize;
    private static final Dimension dlgSize;
    private static int dlgPosX;
    private static int dlgPosY;
    
    // Initializing static variables
    static 
    {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dlgSize = new Dimension(320, 240);
        dlgPosX = screenSize.width / 2 - dlgSize.width / 2;
        dlgPosY = screenSize.height / 2 - dlgSize.height / 2;
    }
    
    public AboutDialog()
    {
        super((JFrame)null, "About Chat");
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(AboutDialog.class.getResource("Icon.png")));
        this.setSize(dlgSize);
        this.setLocation(dlgPosX, dlgPosY);
        this.setResizable(false);       
        
        JPanel panel = new JPanel(null);
        
        JLabel versionLabel = new JLabel("Chat " + CHAT_VERSION);
        versionLabel.setFont(new Font("Arial", 1, 24));
        versionLabel.setBounds(0, 10, 312, 36);
        versionLabel.setHorizontalAlignment(0);
        panel.add(versionLabel);
        
        JLabel image = new JLabel(new ImageIcon(getClass().getResource("Logo.jpg")));
        image.setBounds(66, 50, 180, 80);        
        image.setHorizontalAlignment(0);
        panel.add(image);
        
        JLabel aboutLabel = new JLabel(ABOUT_STRING);
        aboutLabel.setBounds(0, 130, 312, 20);
        aboutLabel.setHorizontalAlignment(0);
        panel.add(aboutLabel);
        
        JButton button = new JButton("OK");
        button.setBounds(120, 170, 80, 24);
        panel.add(button);
        setContentPane(panel);      
        
        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent actionevent)
            {
                close();
            }

        });
        
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent windowevent)
            {
                close();
            }

        });
    }
    
    private void close()
    {   
        this.setVisible(false);
    }
    
}
