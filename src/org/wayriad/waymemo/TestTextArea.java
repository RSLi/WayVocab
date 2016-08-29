package org.wayriad.waymemo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class TestTextArea extends JTextArea {

    private BufferedImage bg;
    private int bgX1;
    private int bgY1;
    private int bgX2;
    private int bgY2;
    
    public TestTextArea(File file,int bgx1,int bgy1,int bgx2,int bgy2) {
        try {
            bg = ImageIO.read(file);
        } catch (IOException ex) {
            
        }
        bgX1= bgx1;
        bgY1 = bgy1;
        bgX2 = bgx2;
        bgY2 = bgy2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        // Fill the background, this is VERY important
        // fail to do this and you will have major problems
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        // Draw the background
        g2d.drawImage(bg,
        		0, 0,this.getWidth(),this.getHeight(),
        		bgX1,bgY1,bgX2,bgY2,
        		this);
        // Paint the component content, ie the text
        getUI().paint(g2d, this);
        g2d.dispose();
    }

}
