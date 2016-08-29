package org.wayriad.waymemo;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TranscluentFlashCard extends JFrame{
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
	//
	TranslucentPane paneCenter = null;
	JButton b1 = new JButton("next(c)");
	JButton b2 = new JButton("back(z)");
	JButton b3 = new JButton("Show Answer(x)");
	JButton bReset = new JButton("Reset(r)");
	JButton bMark = new JButton("Mark(s)");
	JButton bUnMark = new JButton("Unmark(d)");
	JPanel pMark = new JPanel(new FlowLayout());
	public TranscluentFlashCard()
	{
		this.setUndecorated(true);
		setOpacity(0.4f);
		
		
		TranslucentPane contentPane = new TranslucentPane();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		//
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		this.setSize((int)width,(int)height);
		//
		paneCenter = new TranslucentPane();
		paneCenter.setLayout(new GridLayout(7,1));
		
		l1.setOpaque(false);
		l2.setOpaque(false);
		l3.setOpaque(false);
		l4.setOpaque(false);
		l5.setOpaque(false);
		
		contentPane.add(ln,"North");
		contentPane.add(paneCenter,"Center");
		contentPane.add(b2,"West");
		contentPane.add(bReset,"South");
		contentPane.add(b1,"East");
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
		    	l2.setText(d1[num]);
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
		    	dtr[num]="[MARKED*]"+dtr[num];
				l1.setText(dtr[num]);
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
		    	dtr[num]=odtr[num];
				l1.setText(dtr[num]);
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
			// 
			e.printStackTrace();
		} catch (InstantiationException e) {
			// 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// 
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// 
			e.printStackTrace();
		}
		wordDisplay();
		
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
			d1[maxIndex]=sf.nextLine();
			d2[maxIndex]=sf.nextLine();
			d3[maxIndex]=sf.nextLine();
			d4[maxIndex]=sf.nextLine();
		}
		sf.close();
		
	}
	public void wordDisplay()
	{
		l1.setFont(new Font("UTF-8",Font.BOLD,46));
		l1.setText(dtr[num]);
		l2.setFont(new Font("UTF-8",Font.PLAIN,30));
		l2.setText("###################################################################");
		l3.setFont(new Font("UTF-8",Font.PLAIN,30));
		l3.setText("###################################################################");
		l4.setFont(new Font("UTF-8",Font.PLAIN,30));
		l4.setText("###################################################################");
		l5.setFont(new Font("UTF-8",Font.PLAIN,30));
		l5.setText("###################################################################");
		
		
	}
	public class TranslucentPane extends JPanel {

        public TranslucentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0f));
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());

        }

    }


}
