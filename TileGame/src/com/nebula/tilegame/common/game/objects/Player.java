package com.nebula.tilegame.common.game.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.GameObject;
import com.nebula.tilegame.handlers.InputHandler;

public class Player extends GameObject {

	private int x, y;
	// private int id[] = new int[1]; // TODO fix this ish
	private int health = 10;

	public Player() {
		this.x = (Configuration.FRAME_DIMENSION.width / 2);
		this.y = (Configuration.FRAME_DIMENSION.height / 2);

		// id = new int[] { 1, 1 };

		gameObjects.add(this);
	}

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		
		gameObjects.add(this);
	}
	
	@Override
	public void update() {
		gravity();

		if (InputHandler.instance.keyIsDown(KeyEvent.VK_W)) {
			// Walking up
			// Tile.shift(0, Direction.UP.getDirection());
			move(Direction.UP);
		}
		if (InputHandler.instance.keyIsDown(KeyEvent.VK_A)) {
			// Walking Left
			// Tile.shift(Direction.LEFT.getDirection(), 0);
			move(Direction.LEFT);
		}
		if (InputHandler.instance.keyIsDown(KeyEvent.VK_S)) {
			// Walking Down
			// Tile.shift(0, Direction.DOWN.getDirection());
			move(Direction.DOWN);
		}
		if (InputHandler.instance.keyIsDown(KeyEvent.VK_D)) {
			// Walking Right
			// Tile.shift(Direction.RIGHT.getDirection(), 0);
			move(Direction.RIGHT);
		}
	}

	public void gravity() {
		// y += 5;
	}

	private void move(Direction direction) {
		switch (direction) {
		case UP:
			y += direction.getDirection();
			break;

		case DOWN:
			y += direction.getDirection();
			break;

		case LEFT:
			x += direction.getDirection();
			break;

		case RIGHT:
			x += direction.getDirection();
			break;
		}
	}

	@Override
	public boolean isValid() {
		return (health != 0);
	}
	
	@Override
	public boolean shouldDestroy() {
		return false;
	}

	@Override
	public void paint(Graphics2D g) {
		// g.drawImage(SpriteManager.CHARACTERS.get(id[0], id[1]), x, y, null);
		g.setColor(Color.GREEN);
		
		// head
		g.fillOval(x - 5, y - 5, 10, 10);
		// torso
		g.drawLine(x, y + 5, x, y + 20);
		// arms
		g.drawLine(x - 7, y + 10, x + 7, y + 10);
		// legs
		g.drawLine(x - 5, y + 27, x, y + 20);
		g.drawLine(x + 5, y + 27, x, y + 20);
		
		g.setColor(Color.BLACK);
		// Eyes
		g.drawLine(x - 1, y - 1, x - 1, y - 1);
		g.drawLine(x + 1, y - 1, x + 1, y - 1);
		
		// Mouth
		g.drawLine(x - 2, y + 2, x + 2, y + 2);
	}

	private enum Direction {

		UP(-5), DOWN(5), LEFT(-5), RIGHT(5);

		private int direction;

		Direction(int direction) {
			this.direction = direction;
		}

		public int getDirection() {
			return direction;
		}
	}

}
