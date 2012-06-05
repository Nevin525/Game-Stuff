package com.nebula.tilegame.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.MenuObject;
import com.nebula.tilegame.common.abstracts.Object;
import com.nebula.tilegame.common.abstracts.GameObject;
import com.nebula.tilegame.common.game.GameLoop;
import com.nebula.tilegame.common.game.State;
import com.nebula.tilegame.common.game.items.Hax;
import com.nebula.tilegame.common.game.objects.Player;
import com.nebula.tilegame.common.game.objects.Tile;
import com.nebula.tilegame.common.game.objects.ui.InGameMenu;
import com.nebula.tilegame.common.game.objects.ui.MainMenu;
import com.nebula.tilegame.common.game.objects.ui.ToolBar;
import com.nebula.tilegame.handlers.InputHandler;
import com.nebula.tilegame.handlers.StateHandler;
import com.nebula.tilegame.managers.SpriteManager;

public class GamePanel extends Canvas {

	private static final long serialVersionUID = 1L;

	private BufferedImage screen = new BufferedImage(
			Configuration.FRAME_DIMENSION.width,
			Configuration.FRAME_DIMENSION.height, BufferedImage.TYPE_INT_RGB);
	private BufferStrategy bufferStrategy;
	private Graphics2D g;

	private GameFrame frame;
	private GameLoop gameLoop;

	public GamePanel(final GameFrame frame) {
		this.frame = frame;
		this.g = screen.createGraphics();

		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));
		g.setRenderingHints(new RenderingHints(
				RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY));
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));

		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setBackground(Color.BLACK);

		setFocusable(true);
		requestFocus();

		init();
	}

	public void init() {
		if (frame != null) {
			initGameSettings();

			addKeyListener(InputHandler.instance);
			addMouseListener(InputHandler.instance);
			addMouseMotionListener(InputHandler.instance);
			addMouseWheelListener(InputHandler.instance);
			addFocusListener(InputHandler.instance);

			gameLoop = new GameLoop(this);
			gameLoop.start();
		}
	}

	private void initGameSettings() {
		Configuration.useHD(true);

		new SpriteManager();

		// Block.initBlocks();
		Tile.initTiles();

		new MainMenu();
		new Player();
		
		new ToolBar();
		new InGameMenu();
		// new Hax(SpriteManager.ITEMS.get(1, 1), 100);// getting a null
		// image... Zach make the sprites.
	}

	@Override
	public void addNotify() {
		super.addNotify();

		createBufferStrategy(3);
		bufferStrategy = this.getBufferStrategy();
	}

	public void update() {
		switch (StateHandler.instance.getCurrentState()) {

		case MENU:
			synchronized (MenuObject.menuObjects) {
				for (Iterator<MenuObject> it = MenuObject.menuObjects
						.iterator(); it.hasNext();) {
					MenuObject object = (MenuObject) it.next();
					object.update();

					/*
					 * if (object.shouldDestroy()) {
					 * MenuObject.menuObjects.remove(object); }
					 */
				}
			}
			break;

		case IN_GAME_MENU:
			synchronized (GameObject.menuObjects) {
				for (Iterator<GameObject> it = GameObject.menuObjects
						.iterator(); it.hasNext();) {
					GameObject object = (GameObject) it.next();
					object.update();

					/*
					 * if (object.shouldDestroy()) {
					 * GameObject.menuObjects.remove(object); }
					 */
				}
			}
			break;

		case PLAYING:
			synchronized (GameObject.gameObjects) {
				for (Iterator<GameObject> it = GameObject.gameObjects
						.iterator(); it.hasNext();) {
					GameObject object = (GameObject) it.next();
					object.update();

					/*
					 * if (object.shouldDestroy()) {
					 * GameObject.gameObjects.remove(object); }
					 */
				}
			}
			break;

		case PAUSED:
			// Don't update game objects while paused.
			break;
		}
	}

	public void paint() {
		if (g != null) {
			switch (StateHandler.instance.getCurrentState()) {

			case MENU:
				synchronized (MenuObject.menuObjects) {
					for (Iterator<MenuObject> it = MenuObject.menuObjects
							.iterator(); it.hasNext();) {
						MenuObject object = (MenuObject) it.next();

						if (object.isValid()) {
							object.paint(g);
						}
					}
				}
				break;

			case IN_GAME_MENU:
				synchronized (GameObject.menuObjects) {
					for (Iterator<GameObject> it = GameObject.menuObjects
							.iterator(); it.hasNext();) {
						GameObject object = (GameObject) it.next();

						if (object.isValid()) {
							object.paint(g);
						}
					}
				}
				
				synchronized (GameObject.gameObjects) {
					for (Iterator<GameObject> it = GameObject.gameObjects
							.iterator(); it.hasNext();) {
						GameObject object = (GameObject) it.next();

						if (object.isValid()) {
							object.paint(g);
						}
					}
				}
				break;

			case PLAYING:
				synchronized (GameObject.gameObjects) {
					for (Iterator<GameObject> it = GameObject.gameObjects
							.iterator(); it.hasNext();) {
						GameObject object = (GameObject) it.next();

						if (object.isValid()) {
							object.paint(g);
						}
					}
				}
				break;

			case PAUSED:
				synchronized (GameObject.gameObjects) {
					for (Iterator<GameObject> it = GameObject.gameObjects
							.iterator(); it.hasNext();) {
						GameObject object = (GameObject) it.next();

						if (object.isValid()) {
							object.paint(g);
						}
					}
				}
				break;
			}
		}
	}

	public void render() {
		if (bufferStrategy != null) {
			Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.drawImage(screen, 0, 0, Configuration.FRAME_DIMENSION.width,
					Configuration.FRAME_DIMENSION.height, this);
			g.dispose();

			if (!bufferStrategy.contentsLost()) {
				bufferStrategy.show();
			}
		}
	}
}
