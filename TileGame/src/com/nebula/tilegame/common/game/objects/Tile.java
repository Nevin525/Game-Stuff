package com.nebula.tilegame.common.game.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.GameObject;
import com.nebula.tilegame.managers.SpriteManager;
import com.nebula.tilegame.util.Random;

public class Tile extends GameObject {

	private int x, y;
	private Type type;

	public Tile(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;

		tiles.add(this);
		gameObjects.add(this);
	}

	public static void initTiles() {
		for (int x = 0; x + Configuration.TILE_DIMENSION.width <= Configuration.FRAME_DIMENSION.width; x += Configuration.TILE_DIMENSION.width) {
			for (int y = 0; y + Configuration.TILE_DIMENSION.height <= Configuration.FRAME_DIMENSION.height; y += Configuration.TILE_DIMENSION.height) {
				// Change tile type according to y location
				if (y < (6 * Configuration.TILE_DIMENSION.height)) {
					new Tile(x, y, getRandomTypeDominant(Type.NIGHT_AIR,
							Type.STAR_AIR, Type.NIGHT_AIR) /*Type.DAY_AIR*/);
				} else if ((y > (5 * Configuration.TILE_DIMENSION.height))
						&& (y < (7 * Configuration.TILE_DIMENSION.height))) {
					new Tile(x, y, Type.GRASS);
				} else if ((y > (6 * Configuration.TILE_DIMENSION.height))
						&& (y < (10 * Configuration.TILE_DIMENSION.height))) {
					new Tile(x, y, Type.DIRT);
				} else if ((y > (9 * Configuration.TILE_DIMENSION.height))
						&& (y < (13 * Configuration.TILE_DIMENSION.height))) {
					new Tile(x, y, getRandomType(Type.DIRT, Type.STONE));
				} else {
					new Tile(x, y, Type.STONE);
				}
			}
		}
	}

	public void shift(int x, int y) {
		for(Tile tile : tiles) {
			tile.x += x;
			tile.y += y;
		}
	}
	
	// public Tile get(int row, int column) {
	// row = tiles.get(row) % /*row size*/;
	// }

	@Override
	public void update() {

	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public boolean shouldDestroy() {
		return false;
	}
	
	@Override
	public void paint(Graphics2D g) {
		for (Tile tile : tiles) {
			if (tile != null)
				g.drawImage(tile.type.getImage(), tile.x, tile.y,
						Configuration.TILE_DIMENSION.width,
						Configuration.TILE_DIMENSION.height, null);
		}

		/*
		 * for (int i = 0; i < tiles.size(); i++) {
		 * g.drawString(String.valueOf(i), tiles.get(i).x, tiles.get(i).y); }
		 */

	}

	public static Type getRandomType(Type... type) {
		Random random = new Random();
		return type[random.nextInt(type.length)];
	}

	public static Type getRandomTypeDominant(Type dominant, Type... type) {
		Random random = new Random();

		switch (random.nextInt(0, 2)) {
		case 0:
			return type[random.nextInt(type.length)];

		default:
			return dominant;
		}
	}

	public enum Type {
		DAY_AIR(SpriteManager.TILES.get(8, 6)), NIGHT_AIR(SpriteManager.TILES
				.get(8, 7)), STAR_AIR(SpriteManager.TILES.get(8, 8)),

		GRASS(SpriteManager.TILES.get(1, 3)), DIRT(SpriteManager.TILES
				.get(1, 4)), STONE(SpriteManager.TILES.get(1, 2));

		private BufferedImage image;

		Type(BufferedImage image) {
			this.image = image;
		}

		public BufferedImage getImage() {
			return image;
		}
	}
}
