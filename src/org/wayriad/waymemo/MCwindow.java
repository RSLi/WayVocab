package org.wayriad.waymemo;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MCwindow extends JFrame{
	
	private int trueAnswer;
	private int wrongAnswer1;
	private int wrongAnswer2;
	private int wrongAnswer3;
	
	private int scoreWrong=0;
	
	/*
	 * Array declaration
	 */
	public String dtr[]=new String[2000];
	public String odtr[]=new String[2000];
	public String d1[]=new String[2000];
	public String d2[]=new String[2000];
	public String d3[]=new String[2000];
	public String d4[]=new String[2000];
	private int num = 0;
	private int currentMaxIndex;
	
	/*
	 * UI component declaration
	 */
	JPanel governPane = new JPanel(new BorderLayout());
	JPanel displayPane = new JPanel(new GridLayout(6,1));
	JPanel selectionPane = new JPanel(new GridLayout(5,1));
	JPanel leftToolPane = new JPanel(new GridLayout(1,1));
	JPanel consolePane = new JPanel();
	JPanel rightToolPane = new JPanel(new GridLayout(1,1));
	JPanel topInfoPane = new JPanel();
	public static JLabel lblTopInfo = new JLabel();
	JButton bA = new JButton();
	JButton bB = new JButton();
	JButton bC = new JButton();
	JButton bD = new JButton();
	JButton bIDK = new JButton("I don't know(r)");
	JTextField console = new JTextField();
	
	File bgFile = new File("images\\bg\\userBg.jpg");
	TestTextArea l1 = new TestTextArea(bgFile,0,0,800,100);
	TestTextArea l2 = new TestTextArea(bgFile,0,100,800,200);
	TestTextArea l3 = new TestTextArea(bgFile,0,200,800,300);
	TestTextArea l4 = new TestTextArea(bgFile,0,300,800,400);
	TestTextArea l5 = new TestTextArea(bgFile,0,400,800,500);
	
	public MCwindow(){
		/*
		 * read the file
		 */
		try{
			this.fileScan(new OpenFileDTR().getPathDTR());
			}catch(IOException e){
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		
		
		
		
		initMCwindow();
		resetText();
	}
	private void initMCwindow(){
		/*
		 * Basic window configurations
		 */
		this.setTitle("WayMemo - Multiple Choice Mode");
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(governPane);
		
		/*
		 * declare basic components
		 */
		
		Utility.initTextAreaView(l1);
		Utility.initTextAreaView(l2);
		Utility.initTextAreaView(l3);
		Utility.initTextAreaView(l4);
		Utility.initTextAreaView(l5);
		
		l1.setFont(new Font("UTF-8",Font.BOLD,46));
		l2.setFont(new Font("UTF-8",Font.PLAIN,30));
		l3.setFont(new Font("UTF-8",Font.PLAIN,30));
		l4.setFont(new Font("UTF-8",Font.PLAIN,30));
		l5.setFont(new Font("UTF-8",Font.PLAIN,30));
		bA.setFont(new Font("UTF-8",Font.BOLD,23));
		bB.setFont(new Font("UTF-8",Font.BOLD,23));
		bC.setFont(new Font("UTF-8",Font.BOLD,23));
		bD.setFont(new Font("UTF-8",Font.BOLD,23));
		/*
		 * add panels
		 */
		governPane.add(topInfoPane,"North");
		governPane.add(displayPane,"Center");
		governPane.add(leftToolPane,"West");
		governPane.add(rightToolPane,"East");
		governPane.add(selectionPane,"South");
		
		/*
		 * add basic components
		 */
		topInfoPane.add(lblTopInfo);
		displayPane.add(l1);
		displayPane.add(l2);
		displayPane.add(l3);
		displayPane.add(l4);
		displayPane.add(l5);
		displayPane.add(consolePane);
		consolePane.add(console);
		selectionPane.add(bA);
		selectionPane.add(bB);
		selectionPane.add(bC);
		selectionPane.add(bD);
		selectionPane.add(bIDK);
		console.setText("Please select the right answer.Hot key for A,B,C,D are respectively \"a\",\"w\",\"c\",\"d\"");
		
		/*
		 * Button init
		 */
		
		Action actionA = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	checkAnswer(1);
		        
		    }
		};
		bA.getInputMap().put(KeyStroke.getKeyStroke("A"),
		                            "pressed");
		bA.getActionMap().put("released",
		                             actionA);
		//
		Action actionB = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	checkAnswer(2);
		    }
		};
		bB.getInputMap().put(KeyStroke.getKeyStroke("W"),
		                            "pressed");
		bB.getActionMap().put("released",
		                             actionB);
		//
		Action actionC = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	checkAnswer(3);
		    }
		};
		bC.getInputMap().put(KeyStroke.getKeyStroke("S"),
		                            "pressed");
		bC.getActionMap().put("released",
		                             actionC);
		//
		//
		Action actionD = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	checkAnswer(4);
		    }
		};
		bD.getInputMap().put(KeyStroke.getKeyStroke("D"),
		                            "pressed");
		bD.getActionMap().put("released",
		                             actionD);
		//
		Action actionIDK = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				checkAnswer(5);
			}
		};
		bIDK.getInputMap().put(KeyStroke.getKeyStroke("r"),
				"pressed");
		bIDK.getActionMap().put("released", actionIDK);
	
		
		//
		//
		bA.setMnemonic(KeyEvent.VK_A);
		bB.setMnemonic(KeyEvent.VK_W);
		bC.setMnemonic(KeyEvent.VK_S);
		bD.setMnemonic(KeyEvent.VK_D);
		bIDK.setMnemonic(KeyEvent.VK_R);
		
		
		bA.addActionListener(actionA);
		bB.addActionListener(actionB);
		bC.addActionListener(actionC);
		bD.addActionListener(actionD);
	
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
		currentMaxIndex = maxIndex;
		
	}
	
	
	private void resetText(){
		l1.setText(dtr[num]);
		l2.setText("");
		l3.setText("");
		l4.setText("Choose the answer that matchs the item above");
		l5.setText("");
		
		randomString();
		switch(trueAnswer)
		{
		
		case 1:
			bA.setText("A:"+d1[num]);
			bB.setText("B:"+d1[wrongAnswer1]);
			bC.setText("C:"+d1[wrongAnswer2]);
			bD.setText("D:"+d1[wrongAnswer3]);
			break;
		case 2:
			bB.setText("B:"+d1[num]);
			bA.setText("A:"+d1[wrongAnswer1]);
			bC.setText("C:"+d1[wrongAnswer2]);
			bD.setText("D:"+d1[wrongAnswer3]);
			break;
		case 3:
			bC.setText("C:"+d1[num]);
			bA.setText("A:"+d1[wrongAnswer1]);
			bB.setText("B:"+d1[wrongAnswer2]);
			bD.setText("D:"+d1[wrongAnswer3]);
			break;
		case 4:
			bD.setText("D:"+d1[num]);
			bA.setText("A:"+d1[wrongAnswer1]);
			bB.setText("B:"+d1[wrongAnswer2]);
			bC.setText("C:"+d1[wrongAnswer3]);
			break;
		}
		
	}
	private void checkAnswer(int answer){
		if(answer == trueAnswer){
			showAnswer(true);
		}
		else{
			scoreWrong++;
			repeatWrong(num);
			showAnswer(false);
		}
	}
	private void showAnswer(boolean check)
	{
		if(check){
			num++;
			if(dtr[num]==null){
				console.setText("Congratulation !!! You have finished all the list.  Total mistake(s) : "+ scoreWrong + "  " +lblTopInfo.getText());
			}
			else{
			resetText();
			console.setText("You are Right!!    You have missed : " + scoreWrong + " tries");
			}
		}
		else{
			l1.setFont(new Font("UTF-8",Font.BOLD,46));
			l1.setText("Wrong___ >> "+ dtr[num]);
			l2.setFont(new Font("UTF-8",Font.PLAIN,30));
			l2.setText(d1[num]);
			l3.setFont(new Font("UTF-8",Font.PLAIN,30));
			l3.setText(d2[num]);
			l4.setFont(new Font("UTF-8",Font.PLAIN,30));
			l4.setText(d3[num]);
			l5.setFont(new Font("UTF-8",Font.PLAIN,30));
			l5.setText(d4[num]);
			console.setText("Wrong Answer!   Please try again!!     You have missed: " + scoreWrong + " tries");
		}
	}
	private void randomString(){
		if(currentMaxIndex < 4){
			System.exit(2);
		}
		Random rnd = new Random();
		trueAnswer = 1+rnd.nextInt(4);
		do{
			wrongAnswer1=rnd.nextInt(currentMaxIndex);
			wrongAnswer2=rnd.nextInt(currentMaxIndex);
			wrongAnswer3=rnd.nextInt(currentMaxIndex);
			
		}while(wrongAnswer1 == wrongAnswer2 || wrongAnswer1 == wrongAnswer3
				|| wrongAnswer2 == wrongAnswer3 || wrongAnswer1 == num
				|| wrongAnswer2 == num || wrongAnswer3 == num);
	}
	private void repeatWrong(int num)
	{
		currentMaxIndex++;
		dtr[currentMaxIndex]=dtr[num];
		d1[currentMaxIndex]=d1[num];
		d2[currentMaxIndex]=d2[num];
		d3[currentMaxIndex]=d3[num];
		d4[currentMaxIndex]=d4[num];
	}
}
