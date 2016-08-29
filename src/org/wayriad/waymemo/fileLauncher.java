package org.wayriad.waymemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class fileLauncher extends JFrame
{	
	//components initialization
	JPanel panelLauncher = null;
	JLabel logoLauncher = new JLabel(new ImageIcon("images\\bglogo.png"));
	JLabel signature1 = new JLabel();
	JLabel signature2 = new JLabel();
	JButton bTraditional = new JButton("Tradional Flashcards");
	JButton bReverse = new JButton("Reverse Flash Card Mode");
	JButton bMC = new JButton("Mutiple Choice");
	JButton bEdit = new JButton("Edit/Create lists");
	JButton bTransparentWindow =new JButton("Transparent Mode (When watching videos, switch the screen by alt-tab)");
	JButton bMemoLoop = new JButton("Under construction");
	JButton bRaceMode = new JButton("Under construction");
	JButton bExit = new JButton("Exit");
	
	
	public fileLauncher(){
	//components setting
	this.setTitle("WayMemo --- Academic Memory booster ---          Created by R.S.Li");
	this.setSize(800,600);
	panelLauncher = new JPanel(new GridLayout(11,1)){
		protected void paintComponent(Graphics g){
			ImageIcon icon = new ImageIcon("images\\title\\tree1.jpg");
			Image img = icon.getImage();
			g.drawImage(img,0,0,icon.getIconWidth(),
					icon.getIconHeight(),icon.getImageObserver());
			
		}
	};
	signature1.setFont(new java.awt.Font("Arial",1,20));
	signature1.setForeground(Color.white);
	signature1.setText("                                               		WayMemo");
	signature2.setFont(new java.awt.Font("Arial",1,20));
	signature2.setForeground(Color.white);
	signature2.setText("                                            Created by R.S.Li");
	add(panelLauncher);
	panelLauncher.add(logoLauncher);
	panelLauncher.add(signature1);
	panelLauncher.add(signature2);
	panelLauncher.add(bEdit);
	panelLauncher.add(bTraditional);
	panelLauncher.add(bReverse);
	panelLauncher.add(bMC);
	panelLauncher.add(bTransparentWindow);
	panelLauncher.add(bMemoLoop);
	panelLauncher.add(bRaceMode);
	panelLauncher.add(bExit);
	
	//listerner settings
	ActionListener abTraditional = new actiontraditional();
	ActionListener abExit = new actionexit();
	ActionListener abEdit = new actionedit();
	ActionListener abReverse = new actionReverse();
	ActionListener abMC = new actionMC();
	ActionListener abTransparentWindow = new actionTransparentWindow();
	
	//add action
	bExit.addActionListener(abExit);
	bTraditional.addActionListener(abTraditional);
	bEdit.addActionListener(abEdit);
	bReverse.addActionListener(abReverse);
	bMC.addActionListener(abMC);
	bTransparentWindow.addActionListener(abTransparentWindow);
	
	
	}
	
	
	
	
	
	class actiontraditional implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
		init me = new init("WayMemo - Traditional Flash Card Mode");
		me.setVisible(true);
		me.wordDisplay();
		fileReader.currentMode = 1;
		autoTimer at = new autoTimer();

			
		}
	}
	
	class actionReverse implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			ReverseFlashCard me = new ReverseFlashCard();
			me.setVisible(true);
			me.wordDisplay();
			fileReader.currentMode = 2;
			autoTimer at = new autoTimer();
		}
	}
	
	class actionMC implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			MCwindow mcWindow = new MCwindow();
			mcWindow.setVisible(true);
			fileReader.currentMode = 3;
			autoTimer at = new autoTimer();
		}
	}
	class actionTransparentWindow implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			TranscluentFlashCard frame = new TranscluentFlashCard();
	    	frame.setAlwaysOnTop(true);
	    	frame.setVisible(true);
		}
	}
	class actionexit implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
		System.exit(0);
		}
	}
	class actionedit implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			fileWriter fw = new fileWriter();
			fw.setVisible(true);
			
		}
	}
}