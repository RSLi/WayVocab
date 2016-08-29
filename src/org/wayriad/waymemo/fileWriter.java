package org.wayriad.waymemo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;


public class fileWriter extends JFrame{
	//components declaration
	JPanel panelfw = new JPanel(new GridLayout(3,1));
	JLabel labelTypeEdit = new JLabel();
	JButton buttonCreateNewList = new JButton("Create a new .dtr list");
	JButton buttonTraditionalEdit = new JButton("Edit existing .dtr file");
	JButton buttonMultipleEdit =new JButton("[Under Construction]");
	
	//constructor
	public fileWriter(){
		setTitle("Edit/Create your DTR File");
		setSize(800,600);
		add(panelfw);
		panelfw.add(labelTypeEdit);
		panelfw.add(buttonTraditionalEdit);
		panelfw.add(buttonMultipleEdit);
		labelTypeEdit.setText("<html>If you want to create a new list,just create a new txt file anywhere with the name you prefer, and then rename it with the extention .dtr<br> eg.- list.txt - rename - list.dtr<br>Then edit it with the following tools.<br><br>If you want to copy an existing file to manufacture lists, please follow the sample files with the software.</html>");
		
		
		//
		
		ActionListener abuttonTraditionalEdit = new actionbuttonTraditionalEdit();
		
		//
		
		buttonTraditionalEdit.addActionListener(abuttonTraditionalEdit);
		
	}
	
	class actionbuttonTraditionalEdit implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			EditFileDTR editFileDTR;
			try {
				editFileDTR = new EditFileDTR();
				editFileDTR.setVisible(true);
				editFileDTR.wordDisplay();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	class actionbuttonCreateNewList implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			
		}
	}
}
