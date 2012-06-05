package com.nebula.tilegame.handlers;

import com.nebula.tilegame.common.game.State;

public class StateHandler {
	
	public static StateHandler instance = new StateHandler(State.MENU);

	private State currentState;
	
	public StateHandler(State currentState) {
		this.currentState = currentState;
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
}