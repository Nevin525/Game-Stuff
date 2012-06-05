package com.nebula.tilegame.common;

import java.awt.Dimension;

public class Configuration {

	public static final Dimension FRAME_DIMENSION = new Dimension(960, 720); 
	public static final Dimension TILE_DIMENSION = new Dimension(32, 32);
	public static final Dimension TOOLBAR_BOX_DIMENSION = new Dimension(40, 40);
	public static final Dimension BLOCK_COUNT = new Dimension((FRAME_DIMENSION.width / TILE_DIMENSION.width), (FRAME_DIMENSION.height / TILE_DIMENSION.height));
	
	public static Dimension SPRITE_DIMENSION;
	
	public static boolean useHD = false;
	
	public static void useHD(boolean use) {
		useHD = use;
		
		if(use) {
			SPRITE_DIMENSION = new Dimension(64, 64);
		} else {
			SPRITE_DIMENSION = new Dimension(32, 32);
		}
	}
	  
	
}
