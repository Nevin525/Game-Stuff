package com.nebula.tilegame.common.game.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.GameObject;
import com.nebula.tilegame.managers.SpriteManager;
import com.nebula.tilegame.util.Random;

public class Block extends GameObject {

	private int x, y;
	private Type type;

	public Block(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;

		gameObjects.add(this);
	}

	public static void initBlocks() {
		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				// System.out.println((x * Configuration.TILE_DIMENSION.width) +
				// " " + (y * Configuration.TILE_DIMENSION.height));
				if ((y * Configuration.TILE_DIMENSION.height) < (6 * Configuration.TILE_DIMENSION.height)) {
					blocks[y][x] = new Block(
							(x * Configuration.TILE_DIMENSION.width),
							(y * Configuration.TILE_DIMENSION.height),
							getRandomTypeDominant(Type.NIGHT_AIR,
									Type.STAR_AIR, Type.NIGHT_AIR));
				} else if (((y * Configuration.TILE_DIMENSION.height) > (5 * Configuration.TILE_DIMENSION.height))
						&& ((y * Configuration.TILE_DIMENSION.height) < (7 * Configuration.TILE_DIMENSION.height))) {
					blocks[y][x] = new Block(
							(x * Configuration.TILE_DIMENSION.width),
							(y * Configuration.TILE_DIMENSION.height),
							Type.GRASS);
				} else if (((y * Configuration.TILE_DIMENSION.height) > (6 * Configuration.TILE_DIMENSION.height))
						&& ((y * Configuration.TILE_DIMENSION.height) < (10 * Configuration.TILE_DIMENSION.height))) {
					blocks[y][x] = new Block(
							(x * Configuration.TILE_DIMENSION.width),
							(y * Configuration.TILE_DIMENSION.height),
							Type.DIRT);
				} else if (((y * Configuration.TILE_DIMENSION.height) > (9 * Configuration.TILE_DIMENSION.height))
						&& ((y * Configuration.TILE_DIMENSION.height) < (13 * Configuration.TILE_DIMENSION.height))) {
					blocks[y][x] = new Block(
							(x * Configuration.TILE_DIMENSION.width),
							(y * Configuration.TILE_DIMENSION.height),
							getRandomType(Type.DIRT, Type.STONE));
				} else {
					blocks[y][x] = new Block(
							(x * Configuration.TILE_DIMENSION.width),
							(y * Configuration.TILE_DIMENSION.height),
							Type.STONE);
				}
			}
		}
	}

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
		g.setColor(Color.RED);
		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				g.drawRect(blocks[y][x].getX(), blocks[y][x].getY(),
						Configuration.TILE_DIMENSION.width,
						Configuration.TILE_DIMENSION.height);
				g.drawImage(blocks[y][x].getType().getImage(),
						blocks[y][x].getX(), blocks[y][x].getY(),
						Configuration.TILE_DIMENSION.width,
						Configuration.TILE_DIMENSION.height, null);
			}
		}
	}

	private static Type getRandomType(Type... type) {
		Random random = new Random();
		return type[random.nextInt(type.length)];
	}

	private static Type getRandomTypeDominant(Type dominant, Type... type) {
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
