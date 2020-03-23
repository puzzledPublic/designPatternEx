package compound.mvc;

public interface HeartModelInterface {

	int getHeartRate();

	void registerObserver(BeatObserver beatObserver);

	void removeObserver(BeatObserver beatObserver);

	void registerObserver(BPMObserver bpmObserver);

	void removeObserver(BPMObserver bpmObserver);
	
}
