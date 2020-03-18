package proxy.virtual;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

public class ImageProxy implements Icon {
	
	private ImageIcon imageIcon;
	
	private URL imageURL;
	
	private Thread retrievalThead;
	
	private boolean retrieving = false;
	
	public ImageProxy(URL url) {
		this.imageURL = url;
	}
	
	@Override
	public int getIconWidth() {
		if(imageIcon != null) {
			return imageIcon.getIconWidth();
		}
		return 800;
	}
	
	@Override
	public int getIconHeight() {
		if(imageIcon != null) {
			return imageIcon.getIconHeight();
		}
		return 600;
	}
	
	@Override
	public void paintIcon(final Component c, Graphics g, int x, int y) {
		if(imageIcon != null) {
			imageIcon.paintIcon(c, g, x, y);
		}else {
			g.drawString("Loading CD cover, please wait...", x + 300, y + 190);
			if(!retrieving) {
				retrieving = true;
				retrievalThead = new Thread(() -> {
					try {
						imageIcon = new ImageIcon(imageURL, "CD Cover");
						c.repaint();
					} catch(Exception e) {
						e.printStackTrace();
					}
				});
				retrievalThead.start();
			}
		}
	}
}
