package com.nebula.tilegame.common.game.objects.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.GameObject;
import com.nebula.tilegame.common.game.State;
import com.nebula.tilegame.handlers.InputHandler;
import com.nebula.tilegame.handlers.StateHandler;
import com.nebula.tilegame.util.ColorUtils;
import com.nebula.tilegame.util.Timer;

public class ToolBar extends GameObject {

	public static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	private int x, y;
	
	private Timer inactiveTimer = new Timer(1000L);
	private boolean drawAll = true;

	private final Color BOX_COLOR = new Color(61, 61, 61);

	public ToolBar() {
		x = Configuration.FRAME_DIMENSION.width - Configuration.TOOLBAR_BOX_DIMENSION.width - 12;
		y = 5;

		rectangles.add(new Rectangle(x, y, Configuration.TOOLBAR_BOX_DIMENSION.width,
				Configuration.TOOLBAR_BOX_DIMENSION.height));

		addRects(5);

		gameObjects.add(this);
	}

	private void addRects(int numberRects) {
		for (int i = 0; i < numberRects; i++) {
			rectangles.add(new Rectangle(x,
					rectangles.get(rectangles.size() - 1).y
							+ rectangles.get(rectangles.size() - 1).height + 5,
							Configuration.TOOLBAR_BOX_DIMENSION.width, Configuration.TOOLBAR_BOX_DIMENSION.height));
		}
	}

	@Override
	public void update() {
		if (rectangles.get(0)
				.contains(InputHandler.instance.getMouseLocation()) && !drawAll) {
			inactiveTimer.reset();
			drawAll = true;
		}

		if ((InputHandler.instance.getMouseButton() == MouseEvent.BUTTON2)
				&& (InputHandler.instance.getMouseButtonRotation() != 0)) {
			System.out.println(InputHandler.instance.getMouseButtonRotation());
			// Prints twice.
		}

		if (inactiveTimer.getRemaining() == 0 && drawAll) {
			drawAll = false;
		}
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
		if (drawAll && StateHandler.instance.getCurrentState() == State.PLAYING) {
			for (int i = 0; i < rectangles.size(); i++) {
				g.setColor(ColorUtils.getAlpha(BOX_COLOR, 150));
				g.fillRoundRect(rectangles.get(i).x, rectangles.get(i).y,
						rectangles.get(i).width, rectangles.get(i).height, 10,
						10);
				g.setColor(Color.WHITE);
				g.drawRoundRect(rectangles.get(i).x, rectangles.get(i).y,
						rectangles.get(i).width, rectangles.get(i).height, 10,
						10);

				if (rectangles.get(i).contains(
						InputHandler.instance.getMouseLocation())
						&& !InputHandler.instance.mouseIsPressed()) {
					g.setColor(ColorUtils.getAlpha(Color.WHITE, 35));
					g.fillRoundRect(rectangles.get(i).x, rectangles.get(i).y,
							rectangles.get(i).width, rectangles.get(i).height,
							10, 10);
					inactiveTimer.reset();
				} else if (rectangles.get(i).contains(
						InputHandler.instance.getMouseLocation())
						&& InputHandler.instance.mouseIsPressed()) {
					g.setColor(ColorUtils.getAlpha(Color.WHITE, 20));
					g.fillRoundRect(rectangles.get(i).x, rectangles.get(i).y,
							rectangles.get(i).width, rectangles.get(i).height,
							10, 10);
					inactiveTimer.reset();
				}
			}
		} else {
			g.setColor(ColorUtils.getAlpha(BOX_COLOR, 150));
			g.fillRoundRect(rectangles.get(0).x, rectangles.get(0).y,
					rectangles.get(0).width, rectangles.get(0).height, 10, 10);
			g.setColor(Color.WHITE);
			g.drawRoundRect(rectangles.get(0).x, rectangles.get(0).y,
					rectangles.get(0).width, rectangles.get(0).height, 10, 10);
		}
	}
}