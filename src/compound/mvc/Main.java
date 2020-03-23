package compound.mvc;

public class Main {
	public static void main(String[] args) {
		BeatModelInterface beatModel = new BeatModel();
		ControllerInterface beatController = new BeatController(beatModel);
		
//		HeartModelInterface heartModel = new HeartModel();
//		ControllerInterface heartController = new HeartController(heartModel);
	}
}
