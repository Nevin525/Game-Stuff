package com.nebula.tilegame.common.game;

public enum State {

	MENU(0), IN_GAME_MENU(1), PLAYING(2), PAUSED(3); 
	
	private int id;
	
	State(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
