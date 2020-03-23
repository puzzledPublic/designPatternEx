package compound.mvc;

public class HeartAdapter implements BeatModelInterface {
	
	private HeartModelInterface heart;
	
	public HeartAdapter(HeartModelInterface heart) {
		this.heart = heart;
	}

	@Override
	public void initialize() {}

	@Override
	public void on() {}

	@Override
	public void off() {}

	@Override
	public void setBPM(int bpm) {}

	@Override
	public int getBPM() {
		return heart.getHeartRate();
	}

	@Override
	public void registerObserver(BeatObserver beatObserver) {
		heart.registerObserver(beatObserver);
	}

	@Override
	public void removeObserver(BeatObserver beatObserver) {
		heart.removeObserver(beatObserver);
	}

	@Override
	public void registerObserver(BPMObserver bpmObserver) {
		heart.registerObserver(bpmObserver);
	}

	@Override
	public void removeObserver(BPMObserver bpmObserver) {
		heart.removeObserver(bpmObserver);
	}
}
