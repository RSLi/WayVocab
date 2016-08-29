package org.wayriad.waymemo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
    private Image image;
    private Image offScreenImage;
    

    public MyPanel(Image img) {
     this.image = img;
    }
    public MyPanel(GridLayout g1)
    {
    	super(g1);
    }
    @Override
   public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        ImageIcon icon = new ImageIcon("images\\bg\\Academy1.jpg");
		Image img = icon.getImage();
		g.drawImage(img,0,0,icon.getIconWidth(),
				icon.getIconHeight(),icon.getImageObserver());
    }
    public void update(Graphics g) {
     if(offScreenImage == null)
      offScreenImage = this.createImage(800, 600);
        Graphics gImage = offScreenImage.getGraphics();
        paint(gImage);
        g.drawImage(offScreenImage, 0, 0, null);
    }

   public void animation() {
      try {                                                                      
        Thread.sleep(15);
      } catch (InterruptedException e) {
       e.printStackTrace();
   }
   update(getGraphics());
  }
}
