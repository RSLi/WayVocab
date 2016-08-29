package org.wayriad.waymemo;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TranscluentWindow {

    public static void main(String[] args) {
    	TranscluentFlashCard frame = new TranscluentFlashCard();
    	frame.setAlwaysOnTop(true);
    	frame.setVisible(true);
    	
    	
    }

    public TranscluentWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				TranscluentFlashCard frame = new TranscluentFlashCard();
				frame.setAlwaysOnTop(true);
				frame.setVisible(true);

            }
        });
    }

    
}