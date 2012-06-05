package com.nebula.tilegame.common.abstracts;

import java.awt.Graphics2D;

public abstract class Object {

	/**
	 *  Update calculations every tick.
	 */
	public abstract void update();
	
	/**
	 *  Should this object still be rendered?
	 *  
	 *  @return should the object be rendered?
	 */
	
	public abstract boolean isValid();
	
	/**
	 *  Should we remove the object from the list?
	 *  
	 *  @return should we remove the object?
	 */
	
	public abstract boolean shouldDestroy();
	
	/**
	 *  Render the game object.
	 */
	public abstract void paint(Graphics2D g);
	
}
