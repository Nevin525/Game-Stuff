package com.nebula.tilegame.concurrent;

import java.util.LinkedList;

import com.nebula.tilegame.concurrent.workers.Worker;
import com.nebula.tilegame.concurrent.workers.impl.Loop;

public class Container {

	private final Object lock = new Object();
	private final LinkedList<Worker> workers = new LinkedList<Worker>();
	private final ThreadGroup group = Thread.currentThread().getThreadGroup();

	public <T extends Worker> void submit(final T... submitted) {
		synchronized (lock) {
			for (Worker worker : submitted) {
				workers.add(worker);
				new Thread(group, worker).start();
			}
		}
	}

	public void stopLoopWorkers() {
		synchronized (lock) {
			for (Worker worker : workers)
				if (worker instanceof Loop)
					((Loop) worker).stop();
		}
	}
}
