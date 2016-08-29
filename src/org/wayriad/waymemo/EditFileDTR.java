package org.wayriad.waymemo;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.wayriad.waymemo.*;

import java.io.*;
import java.util.*;

public class EditFileDTR extends JFrame{
	private String filePath = "";
	public int maxLine = 0;
	public String dtr[]=new String[2000];
	public String odtr[]=new String[2000];
	public String d1[]=new String[2000];
	public String d2[]=new String[2000];
	public String d3[]=new String[2000];
	public String d4[]=new String[2000];
	int num = 0;
	JTextArea l1 = new JTextArea();
	JTextArea l2 = new JTextArea();
	JTextArea l3 = new JTextArea();
	JTextArea l4 = new JTextArea();
	JTextArea l5 = new JTextArea();
	public static JLabel ln = new JLabel();
	JLabel lt = new JLabel();
	JLabel pageNumber = new JLabel();
	//
	JPanel paneCenter = new JPanel(new GridLayout(7,1));
	JButton b1 = new JButton("***next***");
	JButton b2 = new JButton("***back***");
	JButton b3 = new JButton("***\n***\nShow Answer\n***\n***");
	JButton bReset = new JButton("Reset");
	JButton bSave = new JButton("Save");
	JPanel pMark = new JPanel(new FlowLayout());
	public EditFileDTR() throws ClassNotFoundException
	{
		//init text areas
		Utility.initTextAreaEdit(l1);
		Utility.initTextAreaEdit(l2);
		Utility.initTextAreaEdit(l3);
		Utility.initTextAreaEdit(l4);
		Utility.initTextAreaEdit(l5);
		//
		//basic init
		this.setSize(800,600);
		add(ln,"North");
		add(paneCenter,"Center");
		add(b2,"West");
		add(b1,"East");
		add(pageNumber,"South");
		paneCenter.setBackground(Color.lightGray);
		paneCenter.add(l1);
		paneCenter.add(l2);
		paneCenter.add(l3);
		paneCenter.add(l4);
		paneCenter.add(l5);
		paneCenter.add(pMark);
		pMark.add(bSave);
		pMark.add(lt);
		//
		
		//
		ActionListener ab1 = new actionnext();
		ActionListener ab2 = new actionback();
		ActionListener ab3 = new actionshow();
		ActionListener abr = new actionreset();
		ActionListener abm = new actionsave();
		b1.addActionListener(ab1);
		b2.addActionListener(ab2);
		b3.addActionListener(ab3);
		bReset.addActionListener(abr);
		bSave.addActionListener(abm);
		try {
			filePath = new OpenFileDTR().getPathDTR();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		this.fileScan(filePath);
		}catch(IOException e){
		}
		
	}
	/*
	 * scan
	 * 
	 */
	public void fileScan(String filename) throws IOException
	{
		Scanner sf = new Scanner(new File(filename));
		int maxIndex = -1;
		String s = "";
		while(sf.hasNext())
		{
			maxIndex++;
			s=sf.nextLine();
			dtr[maxIndex]= s;
			odtr[maxIndex]= s;
			d1[maxIndex]=sf.nextLine();
			d2[maxIndex]=sf.nextLine();
			d3[maxIndex]=sf.nextLine();
			d4[maxIndex]=sf.nextLine();
			maxLine = maxIndex;
		}
		sf.close();
		
	}
	/*
	 * save
	 * 
	 */
	public void fileSave(String filepath) throws IOException
	{
		PrintWriter outputStream = null;
		dtr[num] = l1.getText();
		d1[num] = l2.getText();
		d2[num] = l3.getText();
		d3[num] = l4.getText();
		d4[num] = l5.getText();
		try{
			outputStream = new PrintWriter(new FileWriter(filepath));
			int outIndex = 0;
			while(!dtr[outIndex].equals("")){
				outputStream.println(dtr[outIndex]);
				outputStream.println(d1[outIndex]);
				outputStream.println(d2[outIndex]);
				outputStream.println(d3[outIndex]);
				outputStream.println(d4[outIndex]);
				outIndex++;
			}
			
		}finally{
			if (outputStream != null) {
                outputStream.close();
            }
		}
		
	}
	public void wordDisplay()
	{
		l1.setFont(new Font("UTF-8",Font.BOLD,46));
		l1.setText(dtr[num]);
		l2.setText(d1[num]);
		l3.setText(d2[num]);
		l4.setText(d3[num]);
		l5.setText(d4[num]);
		pageNumber.setText("Page " + num);

	}
	
	class actionsave implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			try {
				fileSave(filePath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class actionreset implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	class actionnext implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dtr[num] = l1.getText();
			d1[num] = l2.getText();
			d2[num] = l3.getText();
			d3[num] = l4.getText();
			d4[num] = l5.getText();
			num++;
			wordDisplay();
		}
	}
	class actionback implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dtr[num] = l1.getText();
			d1[num] = l2.getText();
			d2[num] = l3.getText();
			d3[num] = l4.getText();
			d4[num] = l5.getText();
			num--;
			wordDisplay();
		}
	}
	class actionshow implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
	
	
}

