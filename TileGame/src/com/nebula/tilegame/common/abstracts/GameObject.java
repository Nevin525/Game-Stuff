package com.nebula.tilegame.common.abstracts;

import java.util.ArrayList;

import com.nebula.tilegame.common.Configuration;
import com.nebula.tilegame.common.abstracts.Object;
import com.nebula.tilegame.common.game.objects.Block;
import com.nebula.tilegame.common.game.objects.Tile;

public abstract class GameObject extends Object {

	/**
	 *  Will hold all the game tiles.
	 */
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	
	/**
	 *  Will hold all the game tiles.
	 */
	public static Block[][] blocks = new Block[Configuration.BLOCK_COUNT.height][Configuration.BLOCK_COUNT.width];
	
	/**
	 *  Will hold all game objects.
	 */
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	/**
	 *  Will hold all in-game menu objects.
	 */
	public static ArrayList<GameObject> menuObjects = new ArrayList<GameObject>();
	
	/*public void destroyGameObject(GameObject object) {
		if(gameObjects.contains(object)) {
			gameObjects.remove(object);
		}
	}
	
	public void destroyMenuObject(GameObject object) {
		if(menuObjects.contains(object)) {
			menuObjects.remove(object);
		}
	}*/
}
