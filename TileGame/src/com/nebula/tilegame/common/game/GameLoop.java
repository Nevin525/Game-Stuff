package com.nebula.tilegame.common.game;

import com.nebula.tilegame.gui.GamePanel;

public class GameLoop implements Runnable {

	private GamePanel gamePanel;
	private Thread gameLoop;

	private final int TICKS_BEFORE_WAIT = 10;
	private int delays = 0;
	private long beforeTime, afterTime, deltaTime, sleepTime,
			overSleepTime = 0, period = 6 * 1000000;

	public GameLoop(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void start() {
		if (gameLoop == null) {
			gameLoop = new Thread(this);
			gameLoop.start();
		}
	}

	public void stop() {
		if (gameLoop != null) {
			gameLoop.interrupt();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (gameLoop.isAlive()) {
			beforeTime = System.nanoTime();

			gamePanel.update();
			gamePanel.paint();
			gamePanel.render();

			afterTime = System.nanoTime();
			deltaTime = afterTime - beforeTime;
			sleepTime = (period - deltaTime) - overSleepTime;

			if (sleepTime < period && sleepTime > 0) {
				try {
					gameLoop.sleep(sleepTime / 1000000L);
					overSleepTime = 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (deltaTime > period) {
				overSleepTime = deltaTime - period;
			} else if (++delays >= TICKS_BEFORE_WAIT) {
				gameLoop.yield();
				delays = 0;
				overSleepTime = 0;
			} else {
				overSleepTime = 0;
			}

			/*System.out.println("Before Time:	" + beforeTime + "\nAfterTime:	"
					+ afterTime + "\nDeltaTime:	" + deltaTime + "\nSleepTime:	"
					+ sleepTime / 1000000L + "\nOverSleepTime:	" + overSleepTime / 1000000L
					+ "\nDelays:	" + delays + "\n");*/
		}
	}
}
