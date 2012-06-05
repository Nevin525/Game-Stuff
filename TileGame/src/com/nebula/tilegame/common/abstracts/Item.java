package com.nebula.tilegame.common.abstracts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.game.objects.ui.ToolBar;
import com.nebula.tilegame.handlers.InputHandler;
import com.nebula.tilegame.util.Timer;

public abstract class Item extends GameObject {

	private Timer intervalTimer;

	private BufferedImage image;
	private int interval;

	public Item(BufferedImage image) {
		this.image = image;
		this.interval = 0;

		intervalTimer = new Timer(interval);
	}

	public Item(BufferedImage image, int interval) {
		this.image = image;
		this.interval = interval;

		intervalTimer = new Timer(interval);
	}

	public abstract void use();

	@Override
	public void update() {
		if (InputHandler.instance.mouseIsPressed()
				&& (intervalTimer.getRemaining() == 0)) {
			System.out.println("using");
			use();
			intervalTimer.reset();
		}
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, ToolBar.rectangles.get(0).x,
				ToolBar.rectangles.get(0).y,
				Configuration.TOOLBAR_BOX_DIMENSION.width,
				Configuration.TOOLBAR_BOX_DIMENSION.height, null);
	}
}
