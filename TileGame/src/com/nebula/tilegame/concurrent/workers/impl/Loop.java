package com.nebula.tilegame.concurrent.workers.impl;

public interface Loop {
	public boolean valid();

	public int loop();

	public void stop();
}
