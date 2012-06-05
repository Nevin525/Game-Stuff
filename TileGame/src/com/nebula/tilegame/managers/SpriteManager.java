package com.nebula.tilegame.managers;

import java.awt.image.BufferedImage;

import com.nebula.tilegame.common.Configuration;

public class SpriteManager {

	public static SpriteManager UI;
	public static SpriteManager CHARACTERS;
	public static SpriteManager ITEMS;
	public static SpriteManager TILES;

	private BufferedImage spriteSheet;

	public SpriteManager() {
		if (Configuration.useHD) {
			/*UI = new SpriteManager(
					ResourceManager.getImage("Sprites/HD/UI.png"));
			CHARACTERS = new SpriteManager(
					ResourceManager.getImage("Sprites/HD/Characters.png"));
			ITEMS = new SpriteManager(
					ResourceManager.getImage("Sprites/HD/Items.png"));*/
			TILES = new SpriteManager(
					ResourceManager.getImage("Sprites/HD/Tiles.png"));
		} else {
			UI = new SpriteManager(
					ResourceManager.getImage("Sprites/SD/UI.png"));
			CHARACTERS = new SpriteManager(
					ResourceManager.getImage("Sprites/SD/Characters.png"));
			ITEMS = new SpriteManager(
					ResourceManager.getImage("Sprites/SD/Items.png"));
			TILES = new SpriteManager(
					ResourceManager.getImage("Sprites/SD/Tiles.png"));
		}
	}

	public SpriteManager(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public BufferedImage get(int row, int column) {
		return spriteSheet.getSubimage(
				((--column) * Configuration.SPRITE_DIMENSION.width),
				((--row) * Configuration.SPRITE_DIMENSION.height),
				Configuration.SPRITE_DIMENSION.width,
				Configuration.SPRITE_DIMENSION.height);
	}

	public BufferedImage get(int row, int column, int xOff, int yOff) {
		return spriteSheet
				.getSubimage(
						((--column) * Configuration.SPRITE_DIMENSION.width),
						((--row) * Configuration.SPRITE_DIMENSION.height),
						(Configuration.SPRITE_DIMENSION.width + ((--xOff) * Configuration.SPRITE_DIMENSION.width)),
						(Configuration.SPRITE_DIMENSION.height + ((--yOff) * Configuration.SPRITE_DIMENSION.height)));
	}
}
