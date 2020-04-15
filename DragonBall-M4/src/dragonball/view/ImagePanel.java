package dragonball.view;



import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.prism.Image;

public class ImagePanel extends JPanel{
	  private java.awt.Image image;

	    public ImagePanel(String x) {
	    	 ImageIcon bi = new ImageIcon(x);
	          this.image = bi.getImage();
	    }

	    protected void paintComponent(Graphics g) {
	    	  super.paintComponent(g);
	          g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
	    }

	
}
