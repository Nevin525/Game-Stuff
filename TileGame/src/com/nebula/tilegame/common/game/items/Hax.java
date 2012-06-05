package com.nebula.tilegame.common.game.items;

import java.awt.image.BufferedImage;

import com.nebula.tilegame.common.abstracts.Item;

public class Hax extends Item {

	public Hax(BufferedImage image, int interval) {
		super(image, interval);
		
		gameObjects.add(this);
	}

	@Override
	public void use() {
		System.out.println("used hax");
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public boolean shouldDestroy() {
		return false;
	}
}
