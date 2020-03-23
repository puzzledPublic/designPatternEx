package compound.mvc;

public class HeartController implements ControllerInterface {

	private HeartModelInterface heartModel;
	
	private DJView djView;
	
	public HeartController(HeartModelInterface heartModel) {
		this.heartModel = heartModel;
		djView = new DJView(this, new HeartAdapter(heartModel));
		djView.createView();
		djView.createControls();
		djView.disableStartMenuItem();
		djView.disableStopMenuItem();
	}
	
	@Override
	public void start() {}

	@Override
	public void stop() {}

	@Override
	public void increaseBPM() {}

	@Override
	public void decreaseBPM() {}

	@Override
	public void setBPM(int bpm) {}

}
