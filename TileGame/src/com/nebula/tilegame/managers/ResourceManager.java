package com.nebula.tilegame.managers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResourceManager {

	public static Object getResource(String fileName) {
		return ResourceManager.class.getResource("/res/".concat(fileName));
	}

	public static AudioClip getAudioClip(String fileName) {
		return Applet.newAudioClip(ResourceManager.class.getResource("/res/Audio/".concat(fileName)));
	}

	public static BufferedImage getImage(String fileName) {
		try {
			return ImageIO.read(ResourceManager.class.getResource("/res/Images/"
					.concat(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ImageIcon getImageIcon(String fileName) {
		try {
			return new ImageIcon(ImageIO.read(ResourceManager.class
					.getResource("/res/Images/Icons/".concat(fileName))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
