package com.nebula.tilegame;

import javax.swing.SwingUtilities;

import com.nebula.tilegame.gui.GameFrame;

public class Boot {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameFrame();
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO cleanup, cleanup, everybody do your share
			}
		}));
	}

}
