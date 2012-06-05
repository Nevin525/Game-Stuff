package com.nebula.tilegame.concurrent.workers;

import java.util.logging.Logger;

import com.nebula.tilegame.concurrent.workers.impl.Loop;

public abstract class LoopWorker extends Worker implements Loop {

	private boolean paused;
	private boolean halting;

	public boolean valid() {
		return true;
	}

	public final void execute() {
		boolean start = false;
		try {
			start = valid();
		} catch (Exception e) {
			Logger.getLogger("CONCURRENT-VALIDATION").severe(e.getMessage());
			e.printStackTrace();
		} catch (ThreadDeath td) {
			Logger.getLogger("CONCURRENT-VALIDATION").severe(td.getMessage());
			td.printStackTrace();
		}

		try {
			while (start) {
				if (halting)
					break;
				int timeout = 100;

				if (!paused) {
					try {
						timeout = loop();
					} catch (Exception e) {
						Logger.getLogger("CONCURRENT-LOOP").severe(
								e.getMessage());
						e.printStackTrace();
						timeout = -1;
					}
				}

				if (timeout == -1)
					break;
				sleep(timeout);

			}
		} catch (Exception e) {
			Logger.getLogger("CONCURRENT").severe(e.getMessage());
			e.printStackTrace();
		} catch (ThreadDeath td) {
			Logger.getLogger("CONCURRENT").severe(td.getMessage());
			td.printStackTrace();
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isStopping() {
		return halting;
	}

	public void stop() {
		halting = true;
	}
}