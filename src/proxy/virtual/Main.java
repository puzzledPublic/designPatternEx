package proxy.virtual;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {
	static ImageComponent imageComponent;
	public static void main(String[] args) throws MalformedURLException {
		JFrame frame = new JFrame("CD Cover Viewer");
		JMenuBar menuBar;
		JMenu menu;
		Map<String, String> map = new HashMap<>();
		
		map.put("Ambient: Music for Airports", "http://images.amazon.com/images/P/B000003S2K.01LZZZZZZZ.jpg");
		map.put("Buddha Bar", "http://images.amazon.com/images/P/B00009XBYK.01.LZZZZZZZ.jpg");
		map.put("Ima", "http://images.amazon.com/images/P/B000005IRM.01.LZZZZZZZ.jpg");
		map.put("Karma", "http://images.amazon.com/images/P/B000005DCB.01.LZZZZZZZ.jpg");
		map.put("MCMXC A.D.", "http://images.amazon.com/images/P/B000002URV.01.LZZZZZZZ.jpg");
		map.put("Northern Exposure", "http://images.amazon.com/images/P/B000003SFN.01.LZZZZZZZ.jpg");
		map.put("Selected Ambient Works Vol. 2", "http://images.amazon.com/images/P/B000002MNZ.01.LZZZZZZZ.jpg");
		map.put("oliver", "http://www.cs.yale.edu/homes/freeman-elisabeth/2004/9/Oliver_sm.jpg");
		
		URL initialURL = new URL(map.get("Selected Ambient Works Vol. 2"));
		menuBar = new JMenuBar();
		menu = new JMenu("Favorite CDs");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		for(String key : map.keySet()) {
			JMenuItem menuItem = new JMenuItem(key);
			menu.add(menuItem);
			menuItem.addActionListener((event) -> {
				try {
					imageComponent.setIcon(new ImageProxy(new URL(map.get(key))));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				frame.repaint();
			}); 
		}
		
		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	
}
