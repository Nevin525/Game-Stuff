package com.nebula.tilegame.common.game.objects.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.GameObject;
import com.nebula.tilegame.common.game.State;
import com.nebula.tilegame.handlers.InputHandler;
import com.nebula.tilegame.handlers.StateHandler;
import com.nebula.tilegame.util.ColorUtils;
import com.nebula.tilegame.util.Timer;

public class InGameMenu extends GameObject {

	private Timer waitTimer = new Timer(350L);
	
	private boolean showing = false;
	private final Color BACKGROUND_COLOR = new Color(60, 60, 60);
	private boolean destroy = false;
	
	public InGameMenu() {
		gameObjects.add(this);
		menuObjects.add(this);
	}
	
	@Override
	public void update() {
		if (InputHandler.instance.keyIsDown(KeyEvent.VK_ESCAPE) && (StateHandler.instance.getCurrentState() == State.PLAYING) && (waitTimer.getRemaining() == 0)) {
			StateHandler.instance.setCurrentState(State.IN_GAME_MENU);
			showing = true;
			waitTimer.reset();
		} else if (InputHandler.instance.keyIsDown(KeyEvent.VK_ESCAPE) && (StateHandler.instance.getCurrentState() == State.IN_GAME_MENU) && (waitTimer.getRemaining() == 0)) {
			StateHandler.instance.setCurrentState(State.PLAYING);
			showing = false;
			waitTimer.reset();
			destroy = true;
		}
	}

	@Override
	public boolean isValid() {
		return showing;
	}
	
	@Override
	public boolean shouldDestroy() {
		return destroy;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(ColorUtils.getAlpha(BACKGROUND_COLOR, 180));
		g.fillRect(0, 0, Configuration.FRAME_DIMENSION.width, Configuration.FRAME_DIMENSION.height);
		
		g.setColor(Color.WHITE);
		g.drawString("I'M THE MENU!!!", (Configuration.FRAME_DIMENSION.width / 2) - (g.getFontMetrics().stringWidth("I'M THE MENU!!!") / 2), (Configuration.FRAME_DIMENSION.height / 2));
	}
}
