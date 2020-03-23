package compound.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeartModel implements HeartModelInterface, Runnable {

	private List<BeatObserver> beatObservers = new ArrayList<>();
	
	private List<BPMObserver> bpmObservers = new ArrayList<>();
	
	private int time = 1000;
	
	private int bpm = 90;
	
	private Random random = new Random(System.currentTimeMillis());
	
	private Thread thread;
	
	public HeartModel() {
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		int lastRate = -1;
		
		while(true) {
			int change = random.nextInt(10);
			if(random.nextInt(2) == 0) {
				change = 0 - change;
			}
			int rate = 60000 / (time + change);
			if(50 < rate && rate < 120) {
				time += change;
			}
			notifyBeatObservers();
			if(rate != lastRate) {
				lastRate = rate;
				notifyBPMObservers();
			}
			try {
				Thread.sleep(time);
			} catch(Exception e) {}
		}
	}
	
	@Override
	public int getHeartRate() {
		return 60000 / time;
	}

	@Override
	public void registerObserver(BeatObserver beatObserver) {
		beatObservers.add(beatObserver);
	}

	@Override
	public void removeObserver(BeatObserver beatObserver) {
		beatObservers.remove(beatObserver);
	}
	
	public void notifyBeatObservers() {
		for(BeatObserver beatObserver : beatObservers) {
			beatObserver.updateBeat();
		}
	}

	@Override
	public void registerObserver(BPMObserver bpmObserver) {
		bpmObservers.add(bpmObserver);
	}

	@Override
	public void removeObserver(BPMObserver bpmObserver) {
		bpmObservers.add(bpmObserver);
	}

	public void notifyBPMObservers() {
		for(BPMObserver bpmObserver : bpmObservers) {
			bpmObserver.updateBPM();
		}
	}
}
