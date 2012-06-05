package com.nebula.tilegame.concurrent.workers;

import java.util.logging.Logger;

public abstract class Worker implements Runnable {

	protected boolean alive;

	public final void run() {
		alive = true;

		try {
			execute();
		} catch (Exception e) {
			Logger.getLogger("CONCURRENT").severe(e.getMessage());
			e.printStackTrace();
		} catch (ThreadDeath td) {
			Logger.getLogger("CONCURRENT").severe(td.getMessage());
			td.printStackTrace();
		} finally {
			try {
				cleanup();
			} catch (Exception e) {
				Logger.getLogger("CONCURRENT-CLEANUP").severe(e.getMessage());
				e.printStackTrace();
			} catch (ThreadDeath td) {
				Logger.getLogger("CONCURRENT-CLEANUP").severe(td.getMessage());
				td.printStackTrace();
			}
		}

		alive = false;
	}

	public abstract void execute();

	public void cleanup() {
	}

	public boolean isAlive() {
		return alive;
	}

	public static void sleep(int toSleep) {
		try {
			long now;
			long start = System.currentTimeMillis();
			Thread.sleep(toSleep);
			while (start + toSleep > (now = System.currentTimeMillis()))
				Thread.sleep(start + toSleep - now);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
