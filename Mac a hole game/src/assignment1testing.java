import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class assignment1 extends JFrame implements ActionListener {
	
	private int score;
	private JLabel headerLabel, profilePic, profileName;
	private Panel controlPanel;
	private Panel outerPanel;
	private JPanel panel1,panel2,panelHalf;

	private JButton[] result;
	private int size;
	private JButton start;
	private JButton exit;
	private String[] pic;
	private String[] command;
	String nameString = "Player";
	private JTabbedPane tabbedPane;
	JTextField nameVal;
	Button Submit;
	JButton icon1,icon2,icon3,grid3,grid4,grid5;
	String chosenIcon;
	
public assignment1(String title, int size) {
	

		super(title);		
		this.setLayout(new FlowLayout());
		this.size = size;
		
		this.setSize(850,550);
		
		//this.setResizable(false);

		
		 tabbedPane = new JTabbedPane();
		 
		//Pane 1 ->  Settings
	      this.panel1 = new JPanel(false);
	      panel1.setLayout(new GridLayout(1, 1));
	      tabbedPane.addTab("Play", new ImageIcon ("src/images/play.png"), panel1,"Play the game...");
	      tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);    
	      
	      
	      
	      //Pane 2 ->  Settings
	      this.panel2 = new JPanel(false);
	      //panel2.setLayout(new GridLayout(1, 1));
	      tabbedPane.addTab("Settings", new ImageIcon ("src/images/settings.png"), panel2,"Adjust your settings...");
	      tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

	      //Panel Half
	      panelHalf = new JPanel(false);
	      panelHalf.setPreferredSize(new Dimension(600, 400));
	      
	      JLabel iconPick = new JLabel("Pick your player icon!   ");
	      JLabel pickGrid = new JLabel("Pick your grid size!   ");
	      pickGrid.setHorizontalAlignment(JLabel.LEFT);
	      
	      String[] iconChoice = {"src/images/icon0.png", "src/images/icon1.png", "src/images/icon2.png"};
	      
	      
	      
		  //Icon Buttons	
	      icon1 = new JButton(new ImageIcon(iconChoice[0]));  
	      icon2 = new JButton(new ImageIcon(iconChoice[1]));  
	      icon3 = new JButton(new ImageIcon(iconChoice[2]));  
	      
	      //Grid Buttons
	      grid3 = new JButton("3x3");  
	      grid4 = new JButton("4x4");  
	      grid5 = new JButton("5x5");  

	      
	      
	      chosenIcon = iconChoice[0];
	      profilePic = new JLabel (new ImageIcon(chosenIcon));
	      profilePic.setHorizontalAlignment(JLabel.LEFT);
	      panelHalf.add(profilePic);
	      
	      JLabel PlayerNameIs = new JLabel("Welcome ");
	      panelHalf.add(PlayerNameIs);
	      profileName = new JLabel("Player");
	      panelHalf.add(profileName);
	      JLabel welcome = new JLabel(" to a game created by Apeiron Cheung and Nahee Jamie Choi!");
	      panelHalf.add(welcome);
	      
	      //Icon Buttons Action/Commands
	      this.icon1.setActionCommand("icon1");
	      this.icon1.addActionListener(this);
	      this.icon2.setActionCommand("icon2");
	      this.icon2.addActionListener(this);
	      this.icon3.setActionCommand("icon3");
	      this.icon3.addActionListener(this);
	      
	    //Grid Buttons Action/Commands
	      this.grid3.setActionCommand("grid3");
	      this.grid3.addActionListener(this);
	      this.grid4.setActionCommand("grid4");
	      this.grid4.addActionListener(this);
	      this.grid5.setActionCommand("grid5");
	      this.grid5.addActionListener(this);
	      
	      
	     //Panel 2 -> Adding    
	      JLabel Name = new JLabel("Name: ");
	      
	      Name.setHorizontalAlignment(JLabel.LEFT);

	      panelHalf.add(Name);
	      
	      
		    //Button for Submit
	      this.Submit = new Button("Submit");  
	      this.Submit.setBounds(50,200,50,50);  
	      this.Submit.setActionCommand("Submit");
	      this.Submit.addActionListener(this);

	      
	              
	      //Name Value + Add
	      this.nameVal = new JTextField();
	      this.nameVal.setHorizontalAlignment(JTextField.LEFT);
	      this.nameVal.setPreferredSize(new Dimension(400,20));
	      panelHalf.add(this.nameVal);
	      panelHalf.add(Submit);

	      
	    
	      panelHalf.add(iconPick);  
	      panelHalf.add(icon1);
	      panelHalf.add(icon2);
	      panelHalf.add(icon3);
	      panelHalf.add(pickGrid);
	      panelHalf.add(grid3);
	      panelHalf.add(grid4);
	      panelHalf.add(grid5);

	      
	      JLabel credits = new JLabel("Credits to Apeiron Cheung and Jamie Choi. The adorable icons are by Google Stadia!");
	      panelHalf.add(credits);

	      panel2.add(panelHalf);
	     
	 
	      
	    this.reset();

	}


	
	public void reset() {
		
		this.panel1.removeAll();
		
		this.score = 0;
		
		this.headerLabel = new JLabel();
		this.headerLabel.setText("Score = " + this.score); 
		this.headerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		this.controlPanel = new Panel();
		this.controlPanel.setLayout(new GridLayout(size,size));	
		
		this.pic = new String[3];
		pic[0] = "src/images/Pic0.png";
		pic[1] = "src/images/Pic1.png";
		pic[2] = "src/images/Pic2.png";

		this.command = new String[3];
		command[0] = "Meow0";
		command[1] = "Meow1";
		command[2] = "Meow2";
		
		this.result = new JButton[size * size];
		
		int random;
		for(int i = 0; i < this.result.length; i++)
		{
			
			random = (int)(Math.random() *3);
			this.result[i] = new JButton(new ImageIcon(pic[random]));
			
			this.result[i].setActionCommand(command[random]);
			this.result[i].addActionListener(this);
			this.controlPanel.add(this.result[i]);
		}
		
		
		
		
		this.start = new JButton("start!");
		this.start.setActionCommand("start");
		this.start.addActionListener(this);
		
		this.exit = new JButton("Exit");
		this.exit.setActionCommand("exit");
		this.exit.addActionListener(this);
		
		
		this.outerPanel = new Panel();
		this.outerPanel.setLayout(new FlowLayout());
		this.outerPanel.add(this.headerLabel);
		this.outerPanel.add(this.controlPanel);
		this.outerPanel.add(this.start);
		this.outerPanel.add(this.exit);	
		
		panel1.add(this.outerPanel);
		
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		 	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);  
	}		
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "exit") {
			System.exit(0);		
		}
		
		if(e.getActionCommand() == "Submit") {
			this.profileName.setText(this.nameVal.getText());
		
		}
		
		if(e.getActionCommand() == "icon1") {
			this.profilePic.setIcon(new ImageIcon("src/images/icon0.png"));
		}
		else if(e.getActionCommand() == "icon2") {
			this.profilePic.setIcon(new ImageIcon("src/images/icon1.png"));
		}
		else if(e.getActionCommand() == "icon3") {
			this.profilePic.setIcon(new ImageIcon("src/images/icon2.png"));
		}
	  	  
		if(e.getActionCommand() == "grid3") {
			this.size = 3;
			this.reset();
		}
		else if(e.getActionCommand() == "grid4") {
			this.size = 4;
			this.reset();
		}
		else if (e.getActionCommand() == "grid5") {
			this.size = 5;
			this.reset();
		}

		/////////////
		
		if (e.getActionCommand() == "Meow2"){
			this.score += 2;
		} else if (e.getActionCommand() == "Meow1"){
			this.score += 1;
		} else if (e.getActionCommand() == "Meow0"){
			this.score += 0;
		}
		if (e.getActionCommand() == "start"){
			this.score = 0;
			this.reset();
		}		
		this.headerLabel.setText("Score = " + this.score); 

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		assignment1 frame = new assignment1("Meow!Meow!Meow!", 3);
	}

}

