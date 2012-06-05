package com.nebula.tilegame.managers;

import java.applet.AudioClip;

public class SoundManager {

	public static final SoundManager EXPLOSION = new SoundManager("Clips/.wav");

	private AudioClip clip;
	
	public SoundManager(String fileName) {
		this.clip = ResourceManager.getAudioClip(fileName);
	}

	public void play() {
		if (clip != null) {
			new Thread() {
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.play();
					}
				}
			}.start();
		}
	}

	public void loop() {
		if (clip != null) {
			new Thread() {
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.loop();
					}
				}
			}.start();
		}
	}
}
