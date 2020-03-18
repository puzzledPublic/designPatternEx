package proxy.virtual;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

public class ImageIcon implements Icon {

	private URL imageURL;
	
	private String name;
	
	private javax.swing.ImageIcon imageIcon;
	
	public ImageIcon(URL imageURL, String name) {
		this.imageURL = imageURL;
		this.name = name;
		this.imageIcon = new javax.swing.ImageIcon(this.imageURL);
	}

	@Override
	public int getIconWidth() {
		return this.imageIcon.getIconWidth();
	}

	@Override
	public int getIconHeight() {
		return this.imageIcon.getIconHeight();
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		this.imageIcon.paintIcon(c, g, x, y);
	}

}
