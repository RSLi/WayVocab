package org.wayriad.waymemo;

import javax.swing.JTextArea;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

public class MyTextArea extends JTextArea{
	BufferedImage bgImage;
	int bgX;
	int bgY;
	public MyTextArea()
	{
		super();
		  
	}
	
	public void setBgImage(BufferedImage bufferedImage,int x,int y){
		
		BufferedImage bgImage = bufferedImage;
		bgX = x;
		bgY = y;
	      
	      Graphics graphics=bufferedImage.getGraphics();
	     graphics.drawImage(bufferedImage,bgX,bgY,null);
	      
	      
	}
	public void paint(Graphics g) {
        g.drawImage(bgImage, bgX, bgY, null);
        super.paint(g);
	}
}
