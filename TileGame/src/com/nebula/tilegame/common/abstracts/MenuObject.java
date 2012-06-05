package com.nebula.tilegame.common.abstracts;

import java.util.ArrayList;

import com.nebula.tilegame.common.abstracts.Object;

public abstract class MenuObject extends Object {

	/**
	 * Will hold all menu objects.
	 */
	public static ArrayList<MenuObject> menuObjects = new ArrayList<MenuObject>();
	
	/*public void destroy(MenuObject object) {
		if(menuObjects.contains(object)) {
			menuObjects.remove(object);
		}
	}*/
}
