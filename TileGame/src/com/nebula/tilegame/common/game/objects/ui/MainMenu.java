package com.nebula.tilegame.common.game.objects.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.MenuObject;
import com.nebula.tilegame.common.game.State;
import com.nebula.tilegame.common.game.objects.ui.Button;
import com.nebula.tilegame.handlers.StateHandler;
import com.nebula.tilegame.util.ColorUtils;

public class MainMenu extends MenuObject {

	private final Color BACKGROUND_COLOR = new Color(255, 0, 153);

	public MainMenu() {
		new Button("Click me to play",
				((Configuration.FRAME_DIMENSION.width / 2) - Math
						.round(190 / 2)),
				((Configuration.FRAME_DIMENSION.height / 2) - Math
						.round(50 / 2))) {

			@Override
			protected void pressed() {
				StateHandler.instance.setCurrentState(State.PLAYING);
			}

			@Override
			protected void hovered() {

			}

		};

		menuObjects.add(this);
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
		g.setColor(ColorUtils.getAlpha(BACKGROUND_COLOR, 1));
		g.fillRect(0, 0, Configuration.FRAME_DIMENSION.width,
				Configuration.FRAME_DIMENSION.height);
	}

}
