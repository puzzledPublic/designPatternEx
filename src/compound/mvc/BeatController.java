package compound.mvc;

public class BeatController implements ControllerInterface {

	private BeatModelInterface beatModel;
	
	private DJView djView;
	
	public BeatController(BeatModelInterface beatModel) {
		this.beatModel = beatModel;
		djView = new DJView(this, beatModel);
		djView.createView();
		djView.createControls();
		djView.disableStopMenuItem();
		djView.enableStartMenuItem();
		beatModel.initialize();
	}
	
	@Override
	public void start() {
		beatModel.on();
		djView.enableStopMenuItem();
		djView.disableStartMenuItem();
	}

	@Override
	public void stop() {
		beatModel.off();
		djView.enableStartMenuItem();
		djView.disableStopMenuItem();
	}

	@Override
	public void increaseBPM() {
		beatModel.setBPM(beatModel.getBPM() + 1);
	}

	@Override
	public void decreaseBPM() {
		beatModel.setBPM(beatModel.getBPM() - 1);
	}

	@Override
	public void setBPM(int bpm) {
		beatModel.setBPM(bpm);
	}
	
}
