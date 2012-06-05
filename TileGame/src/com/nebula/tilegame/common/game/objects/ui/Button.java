package com.nebula.tilegame.common.game.objects.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.nebula.tilegame.common.abstracts.MenuObject;
import com.nebula.tilegame.handlers.InputHandler;
import com.nebula.tilegame.managers.SpriteManager;

public abstract class Button extends MenuObject {

	private boolean hovered = false;
	private boolean pressed = false;

	private String text;
	private int x, y;

	private BufferedImage buttonImage;
	private Rectangle rectangle;

	public Button(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;

		// buttonImage = SpriteManager.UI.getTiles(1, 1, 0, 0);

		menuObjects.add(this);
	}

	@Override
	public void update() {
		if (rectangle != null) {
			if (rectangle.contains(InputHandler.instance.getMouseLocation())) {
				if (InputHandler.instance.mouseIsPressed()
						&& InputHandler.instance.getMouseButton() == MouseEvent.BUTTON1) {
					pressed = true;
					pressed();
				}
				hovered = true;
				hovered();
			} else {
				hovered = false;
				pressed = false;
			}
		}
	}

	protected abstract void pressed();

	protected abstract void hovered();

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
		// g.drawImage(buttonImage, x, y, width, height, null);
		if (rectangle == null) {
			rectangle = new Rectangle(x, y, (g.getFontMetrics().stringWidth(
					text) + 25), 35);
		}

		g.setColor(Color.RED);

		if (isHovered()) {
			// buttonImage = SpriteManager.UI.get(1, 2, 0, 0);
			g.setColor(Color.GREEN);
		}

		g.fill(rectangle);

		g.setColor(Color.BLACK);
		g.drawString(text, ((x + Math.round(rectangle.width / 2)) - Math
				.round(g.getFontMetrics().stringWidth(text) / 2)),
				(y + Math.round(rectangle.height / 2) + (Math.round(g
						.getFontMetrics().getHeight() / 3))));
	}

	public boolean isHovered() {
		return hovered;
	}

	public boolean isPressed() {
		return pressed;
	}
}
