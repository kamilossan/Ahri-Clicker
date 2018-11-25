package ahri_clicker;

import java.util.TimerTask;

//task to update score/status of skills in regular intervals


class Refresh extends TimerTask {
	public void run() {
		update();
	}

	protected void update() {
		Main_frame.update();


	}
}
