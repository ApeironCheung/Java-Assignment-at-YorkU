import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class tabTest {


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		createWindow();
	   }

	   private static void createWindow() {    
	      JFrame frame = new JFrame("EECS 1720 Assignment 1");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      createUI(frame);
	      frame.setSize(560, 500);      
	      frame.setLocationRelativeTo(null);  
	      frame.setVisible(true);
	   }

	   private static void createUI(final JFrame frame){  
		   
		   
	      JTabbedPane tabbedPane = new JTabbedPane();
	      
	      //Pane 1 ->  Settings
	      JPanel panel1 = new JPanel(false);
	      JLabel filler = new JLabel("Settings");
	      filler.setHorizontalAlignment(JLabel.CENTER);
	      panel1.setLayout(new GridLayout(1, 1));
	      //panel1.add(filler);
	      tabbedPane.addTab("Settings", new ImageIcon ("src/images/settings.png"), panel1,"Adjust your settings...");
	      tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	      
	      //Pane 2 -> Play
	      JPanel panel2 = new JPanel(false);
	      JLabel filler2 = new JLabel("Tab 2");
	      filler2.setHorizontalAlignment(JLabel.CENTER);
	      panel2.setLayout(new GridLayout(1, 1));
	      panel2.add(filler2);
	      tabbedPane.addTab("Play", new ImageIcon ("src/images/play.png"), panel2,"Play the game...");
	      tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);     
	      frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);    
	            
	   }

	   

}
