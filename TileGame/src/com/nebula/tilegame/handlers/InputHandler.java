package com.nebula.tilegame.handlers;

import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

public class InputHandler implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener, FocusListener {

	public static InputHandler instance = new InputHandler();

	public HashMap<Integer, Key> keyMap = new HashMap<Integer, Key>();;
	private boolean mousePressed = false;

	private Point mouseLocation = new Point(-1, -1);
	private int mouseButton = -1;
	private int mouseRotation = 0;

	// Keyboard
	public boolean keyIsDown(int key) {
		return (keyMap.get(key) != null);
	}

	// Mouse
	public boolean mouseIsPressed() {
		return mousePressed;
	}

	public Point getMouseLocation() {
		return mouseLocation;
	}

	public int getMouseButton() {
		return mouseButton;
	}

	public int getMouseButtonRotation() {
		return mouseRotation;
	}

	// Key Listener
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Key last = keyMap.get(e.getKeyCode());
		if (last == null)
			keyMap.put(e.getKeyCode(),
					new Key(e.getKeyCode(), System.currentTimeMillis()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyMap.put(e.getKeyCode(), null);
	}

	// Mouse Listener
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!mousePressed) {
			mousePressed = true;
			mouseButton = e.getButton();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mousePressed) {
			mousePressed = false;
			mouseButton = -1;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	// MouseMotion Listener
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseLocation = e.getPoint();
	}

	// MouseWheel Listener
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (!e.isAltDown()) {
			if (e.getWheelRotation() == -1) {
				mouseRotation = 1;
				System.out.println(mouseRotation);
			} else if (e.getWheelRotation() == 1) {
				mouseRotation = -1;
				System.out.println(mouseRotation);
			}
		}
	}

	// Focus Listener
	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}

	private class Key {

		private int code;
		private long time;

		public Key(int code, long time) {
			this.code = code;
			this.time = time;
		}

		public int getCode() {
			return code;
		}

		public long getTime() {
			return time;
		}

	}
}
