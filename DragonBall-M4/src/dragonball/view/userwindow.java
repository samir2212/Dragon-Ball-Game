package dragonball.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class userwindow extends JPanel{
	 private java.awt.Image image;

	    public userwindow() {  
	          ImageIcon bi = new ImageIcon("photos.jpg");
	          this.image = bi.getImage();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	    	  super.paintComponent(g);
	          g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
	    }

	}


