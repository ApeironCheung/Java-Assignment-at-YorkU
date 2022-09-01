import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import lectures.week10.mousemotiongfx.SimpleMoveALineKey.MoveKeyListener;
import lectures.week11.timersthreads.BouncingBallTimer.UpdateListener;

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
	public String nameString = "Player";
	private JTabbedPane tabbedPane;
	public JTextField nameVal;
	public Button Submit;
	public JButton icon1,icon2,icon3,grid3,grid4,grid5;
	public String chosenIcon;
	
	private Timer t;
	private static final int UPDATE_PERIOD = 1000; // milliseconds
	private int end = 60;
	private boolean paused = false;
	private Cursor hammer;
	
public assignment1(String title) {
	

		super(title);		
		this.setLayout(new FlowLayout());
		this.size = 3;
		
		this.setSize(850,550);
		
		//this.setResizable(false);

		
		 tabbedPane = new JTabbedPane();
		 
		//Pane 1 ->  Settings
	      this.panel1 = new JPanel(false);
	      panel1.setLayout(new GridLayout(1, 1));
	      tabbedPane.addTab("Play", new ImageIcon ("src/images/play.png"), panel1,"Play the game...");

	      
	      
	      
	      //Pane 2 ->  Settings
	      this.panel2 = new JPanel(false);
	      tabbedPane.addTab("Settings", new ImageIcon ("src/images/settings.png"), panel2,"Adjust your settings...");


	      //Panel Half
	      panelHalf = new JPanel(false);
	      panelHalf.setPreferredSize(new Dimension(600, 400));
	      
	      JLabel iconPick = new JLabel("Pick your player icon!   ");
	      JLabel pickGrid = new JLabel("Pick your grid size!   ");
	      

	      
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
	      Name.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	      Panel namePanel = new Panel(new FlowLayout());
	      
	
	      namePanel.add(Name);
	      
	      
		    //Button for Submit
	      this.Submit = new Button("Submit");  
	      this.Submit.setBounds(50,200,50,50);  
	      this.Submit.setActionCommand("Submit");
	      this.Submit.addActionListener(this);

	      
	              
	      //Name Value + Add
	      this.nameVal = new JTextField();
	      this.nameVal.setHorizontalAlignment(JTextField.LEFT);
	      this.nameVal.setPreferredSize(new Dimension(400,20));
	      namePanel.add(this.nameVal);
	      namePanel.add(Submit);
	      panelHalf.add(namePanel);

	      Panel iconPanel = new Panel(new GridLayout(1, 4));    
	      iconPanel.add(iconPick);  
	      iconPanel.add(icon1);
	      iconPanel.add(icon2);
	      iconPanel.add(icon3);
	      panelHalf.add(iconPanel);   
	      
	      Panel gridButtonPanel = new Panel(new GridLayout(1, 4));     
	      gridButtonPanel.add(pickGrid);
	      gridButtonPanel.add(grid3);
	      gridButtonPanel.add(grid4);
	      gridButtonPanel.add(grid5);
	      panelHalf.add(gridButtonPanel);   
	      
	      JLabel credits = new JLabel("<html>Credits:<br/>Code: Jamie Choi & Apeiron Cheung<br/>Image Sources: Google Stadia, ShutterStock, IndiaMart<html>");
	      panelHalf.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	      panelHalf.add(credits);

	      
	      
	      panel2.add(panelHalf);
	      this.addMouseListener(new miceListener());
	      this.addMouseMotionListener(new miceListener());
	      
	      this.t = new Timer(UPDATE_PERIOD, new UpdateListener());
	      this.refresh();
	      
	      this.addKeyListener(new KeyControlListener());
	      this.requestFocus();
	}

	//replaced reset(), reset call refresh() instead
	public void refresh() {
		
		this.panel1.removeAll();
		
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
		String buttonNum = "";
		int random;
		for(int i = 0; i < this.result.length; i++)
		{
			
			random = (int)(Math.random() *3);
			this.result[i] = new JButton(new ImageIcon(pic[random]));
			buttonNum = command[random];
			this.result[i].setActionCommand(buttonNum);
			this.result[i].addActionListener(this);
			this.controlPanel.add(this.result[i]);
		}
		
		
		
		
		this.start = new JButton("Reset");
		this.start.setActionCommand("start");
		this.start.addActionListener(this);
		
		this.outerPanel = new Panel();
		this.outerPanel.setLayout(new FlowLayout());
		this.outerPanel.add(this.headerLabel);
		this.outerPanel.add(this.controlPanel);
		this.outerPanel.add(this.start);
		
		
		this.exit = new JButton("Exit");
		this.exit.setActionCommand("exit");
		this.exit.addActionListener(this);
		
		this.outerPanel.add(this.exit);	
		
		panel1.add(this.outerPanel);
		
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		 	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);  
	}
	
	public void reset() {
		this.refresh();
		this.score = 0;
		this.end = 60;
		this.t.start();
	}
	
	public void update() {
			this.end --;
			this.refresh();
		if(this.end <= 0) {
				this.t.stop();
				this.gameOver();
		}		
	}
	
	public void gameOver() {
		this.controlPanel.removeAll();
		this.controlPanel.setLayout(null);
		String result = "Score: " + this.score;
		
		JLabel playerIcon = new JLabel(new ImageIcon((chosenIcon)));
		playerIcon.setBounds(100, 10, 100, 100);
		
		JLabel playerName = new JLabel(this.profileName.getText());
		playerName.setBounds(250, 10, 100, 50);
		
		JLabel finalScore = new JLabel(result);
		finalScore.setBounds(200, 120, 200, 50);
		
		this.controlPanel.add(playerIcon);
		this.controlPanel.add(playerName);
		this.controlPanel.add(finalScore);
		
	}
	public void pause() {
//			System.out.println("pause: " + paused);
			this.t.stop(); 
			
			this.controlPanel.removeAll();
			this.controlPanel.setLayout(null);
			JLabel pause = new JLabel("pause");
			pause.setBounds(250, 150, 100, 50);
			
			this.controlPanel.add(pause);
			repaint();
			requestFocus();
		}
	public void resume() {
		this.t.start();
		this.controlPanel.removeAll();
		this.controlPanel.setLayout(new GridLayout(size,size));	
		for(int i = 0; i < this.result.length ; i++) {
			this.controlPanel.add(this.result[i]);
		}
		repaint();
		requestFocus();
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
			this.refresh();
		}
		else if(e.getActionCommand() == "grid4") {
			this.size = 4;
			this.refresh();
		}
		else if (e.getActionCommand() == "grid5") {
			this.size = 5;
			this.refresh();
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
			this.reset();
		}		
		this.headerLabel.setText("Score = " + this.score); 
	}
	
	private class UpdateListener implements ActionListener {
	       public void actionPerformed(ActionEvent evt) {
				update();
							
		}
	}

	private class KeyControlListener implements KeyListener {
	       public void keyPressed(KeyEvent e) {	
	    	   if(e.getKeyCode() == KeyEvent.VK_Q) {
	    		   System.exit(0);
	    	   }
	    	   if ((e.getKeyCode()== KeyEvent.VK_SPACE) && !paused) {
	    		   pause();
	    		   paused = true;
	    	   } else if ((e.getKeyCode()== KeyEvent.VK_SPACE) && paused) {
	    		   resume();
	    		   paused = false;
	    	   }
		}
			public void keyTyped(KeyEvent e) {
				// do nothing
			}

			public void keyReleased(KeyEvent e) {
				// do nothing
			}
	}
	public class miceListener extends MouseInputAdapter{
		public void mouseEntered(MouseEvent e) {
			java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
			hammer = toolkit.createCustomCursor(toolkit.getImage("src/images/hammer0.png") , new Point(e.getX(),e.getY()), "");
			controlPanel.setCursor(hammer);
		}
		public void mouseExited(MouseEvent e) {
			
		}
		public void mouseMoved(MouseEvent e) {
		
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		assignment1 frame = new assignment1("Meow!Meow!Meow!");
	}

}
