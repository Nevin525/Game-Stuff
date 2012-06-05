package com.nebula.tilegame.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.managers.ResourceManager;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel panel;

	public GameFrame() {
		super("Tile Game - By Nebula V1.0");
		
		setSize(Configuration.FRAME_DIMENSION);
		setIconImage(ResourceManager.getImage("Icons/Icon.png"));
		getContentPane().setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		initialize();
	}

	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		add(getCanvas());
	}

	public GamePanel getCanvas() {
		if (panel == null) {
			panel = new GamePanel(this);
		}
		return panel;
	}
}
