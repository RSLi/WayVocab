package org.wayriad.waymemo;

import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Paint;


class Utility {
	public Utility()
	{
		
	}
	
	public static void initTextAreaView(JTextArea j1)
	{
		j1.setEditable(false);
		j1.setLineWrap(true);
		j1.setWrapStyleWord(true);
		j1.setOpaque(false);
	}
	
	
	
	public static void initTextAreaEdit(JTextArea j1)
	{
		j1.setLineWrap(true);
		j1.setWrapStyleWord(true);
		
	}
}
