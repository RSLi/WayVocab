package org.wayriad.waymemo;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


class ReverseFlashCard extends JFrame
{
	File bgFile = new File("images\\bg\\userBg.jpg");
	
	public String dtr[]=new String[2000];
	public String odtr[]=new String[2000];
	public String d1[]=new String[2000];
	public String od1[]= new String[2000];
	public String d2[]=new String[2000];
	public String d3[]=new String[2000];
	public String d4[]=new String[2000];
	int num = 0;
	TestTextArea l1 = new TestTextArea(bgFile,0,0,800,100);
	TestTextArea l2 = new TestTextArea(bgFile,0,100,800,200);
	TestTextArea l3 = new TestTextArea(bgFile,0,200,800,300);
	TestTextArea l4 = new TestTextArea(bgFile,0,300,800,400);
	TestTextArea l5 = new TestTextArea(bgFile,0,400,800,500);
	public static JLabel ln = new JLabel();
	JLabel lt = new JLabel();
	//
	JPanel paneCenter = null;
	JButton b1 = new JButton("next(c)");
	JButton b2 = new JButton("back(z)");
	JButton b3 = new JButton("Show Answer(x)");
	JButton bReset = new JButton("Reset(r)");
	JButton bMark = new JButton("Mark(s)");
	JButton bUnMark = new JButton("Unmark(d)");
	JPanel pMark = new JPanel(new FlowLayout());
	public ReverseFlashCard()
	{
		//basic init
		setTitle("WayMemo -Reverse Flash Card Mode");
		this.setSize(800,600);
		paneCenter = new JPanel(new GridLayout(7,1));
		
		add(ln,"North");
		add(paneCenter,"Center");
		add(b2,"West");
		add(bReset,"South");
		add(b1,"East");
		paneCenter.add(l1);
		paneCenter.add(l2);
		paneCenter.add(l3);
		paneCenter.add(l4);
		paneCenter.add(l5);
		paneCenter.add(b3);
		paneCenter.add(pMark);
		pMark.add(bMark);
		pMark.add(bUnMark);
		pMark.add(lt);
		
		//text area init
		
		Utility.initTextAreaView(l1);
		Utility.initTextAreaView(l2);
		Utility.initTextAreaView(l3);
		Utility.initTextAreaView(l4);
		Utility.initTextAreaView(l5);
		
		
		//action
		
		//
		Action actionNext = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	num++;
				wordDisplay();
		        
		    }
		};
		b1.getInputMap().put(KeyStroke.getKeyStroke("C"),
		                            "pressed");
		b1.getActionMap().put("released",
		                             actionNext);
		//
		Action actionBack = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	num--;
				wordDisplay();
		    }
		};
		b2.getInputMap().put(KeyStroke.getKeyStroke("Z"),
		                            "pressed");
		b2.getActionMap().put("released",
		                             actionBack);
		//
		Action actionShow = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	l1.setText(dtr[num]);
				l3.setText(d2[num]);
				l4.setText(d3[num]);
				l5.setText(d4[num]);
		    }
		};
		b3.getInputMap().put(KeyStroke.getKeyStroke("X"),
		                            "pressed");
		b3.getActionMap().put("released",
		                             actionShow);
		//
		//
		Action actionMark = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	d1[num]="[MARKED*]"+d1[num];
				l2.setText(d1[num]);
		    }
		};
		bMark.getInputMap().put(KeyStroke.getKeyStroke("S"),
		                            "pressed");
		bMark.getActionMap().put("released",
		                             actionMark);
		//
		//
		//
		Action actionUnmark = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	d1[num]=od1[num];
				l2.setText(d1[num]);
		    }
		};
		bUnMark.getInputMap().put(KeyStroke.getKeyStroke("F2"),
		                            "pressed");
		bUnMark.getActionMap().put("released",
		                             actionUnmark);
		//
		//
		Action actionReset = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	num = 0;
				wordDisplay();
		    }
		};
		bReset.getInputMap().put(KeyStroke.getKeyStroke("r"),
		                            "pressed");
		bReset.getActionMap().put("released",
		                             actionReset);
		//
		//
		b1.setMnemonic(KeyEvent.VK_C);
		b2.setMnemonic(KeyEvent.VK_Z);
		b3.setMnemonic(KeyEvent.VK_X);
		bMark.setMnemonic(KeyEvent.VK_S);
		bUnMark.setMnemonic(KeyEvent.VK_D);
		bReset.setMnemonic(KeyEvent.VK_R);
		
		
		b1.addActionListener(actionNext);
		b2.addActionListener(actionBack);
		b3.addActionListener(actionShow);
		bReset.addActionListener(actionReset);
		bMark.addActionListener(actionMark);
		bUnMark.addActionListener(actionUnmark);
		//
		//
		try{
		this.fileScan(new OpenFileDTR().getPathDTR());
		}catch(IOException e){
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
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
			s=sf.nextLine();
			d1[maxIndex]=s;
			od1[maxIndex]=s;
			d2[maxIndex]=sf.nextLine();
			d3[maxIndex]=sf.nextLine();
			d4[maxIndex]=sf.nextLine();
		}
		sf.close();
		
	}
	/*
	 * 
	 * The word display method is here
	 * 
	 */
	public void wordDisplay()
	{
		l1.setFont(new Font("UTF-8",Font.BOLD,46));
		l1.setText("###################################################################");
		l2.setFont(new Font("UTF-8",Font.PLAIN,35));
		l2.setText(d1[num]);
		l3.setFont(new Font("UTF-8",Font.PLAIN,30));
		l3.setText("###################################################################");
		l4.setFont(new Font("UTF-8",Font.PLAIN,30));
		l4.setText("###################################################################");
		l5.setFont(new Font("UTF-8",Font.PLAIN,30));
		l5.setText("###################################################################");
		
		
	}
	
	
	
	
	
}
