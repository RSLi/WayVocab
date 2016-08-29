package org.wayriad.waymemo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;
import java.io.*;

public class autoTimer extends JFrame
{

	JPanel panelAutoTimer = new JPanel(new GridLayout(1,0));
	JButton labelAutoTimer = new JButton();
	
	public autoTimer()
	{
	
	//window declare
	setTitle("Timer");
	setSize(800,100);
	this.setResizable(false);
	//
	labelAutoTimer.setFocusPainted(false);
   labelAutoTimer.setMargin(new Insets(0, 0, 0, 0));
	labelAutoTimer.setContentAreaFilled(false);
   labelAutoTimer.setBorderPainted(false);
   labelAutoTimer.setOpaque(false);
	//add container
	add(panelAutoTimer);
	panelAutoTimer.add(labelAutoTimer);
	
	//action
	ActionListener aAutoTimer = new actionautotimer();
	labelAutoTimer.addActionListener(aAutoTimer);
	
	this.setVisible(true);
	Timer theTimer = new Timer(1000,aAutoTimer);
	theTimer.start();
	}
	
	class actionautotimer implements ActionListener
	{

		

		
		int secondsUsed = 0;
		int hoursDisplayed = 0;
		int minutesDisplayed = 0;
		int secondsDisplayed = 0;
		String timeUsed = "You have started for "+hoursDisplayed+" hours  "+minutesDisplayed+" minutes "+secondsDisplayed+" seconds";
		public void actionPerformed(ActionEvent e)
		{
			secondsUsed++;
			hoursDisplayed = secondsUsed/3600;
			minutesDisplayed = (secondsUsed%3600)/60;
			secondsDisplayed = (secondsUsed%3600)%60;
			timeUsed = "You have started for "+hoursDisplayed+" hours  "+minutesDisplayed+" minutes "+secondsDisplayed+" seconds";

			labelAutoTimer.setText(timeUsed);
			if(fileReader.currentMode == 1){
				init.ln.setText(timeUsed + "    " + init.num + " / " +init.currentMaxIndex);
			}
			if(fileReader.currentMode==2){
				ReverseFlashCard.ln.setText(timeUsed);
			}
			if(fileReader.currentMode == 3){
				MCwindow.lblTopInfo.setText(timeUsed);
			}
		}
	}
}