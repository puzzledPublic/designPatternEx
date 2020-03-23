package compound.mvc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;

public class BeatBar extends JProgressBar {
	
	private static final long serialVersionUID = 680754405951669025L;
	
	private ScheduledExecutorService executorService;
	
	public BeatBar() {
		setMaximum(200);
		executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(() -> {
			int value = getValue();
			value = (int)(value * 0.75);
			setValue(value);
			repaint();
		}, 0, 50, TimeUnit.MILLISECONDS);
		
	}
}
