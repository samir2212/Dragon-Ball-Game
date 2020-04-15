package dragonball.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;


import java.awt.Graphics;

import javax.swing.ImageIcon;

public class ImageButton extends JButton{
	  private java.awt.Image image;

	    public ImageButton(String x) {
	    	 ImageIcon bi = new ImageIcon(x);
	          this.image = bi.getImage();
	    }

	    protected void paintComponent(Graphics g) {
	    	  super.paintComponent(g);
	          g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
	    }

	
}