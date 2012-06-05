package com.nebula.tilegame.util;

public class Random extends java.util.Random {

	private static final long serialVersionUID = 1L;

	public int nextInt(int minimum, int maximum) {
		return (minimum + (nextInt((maximum + 1) - minimum)));
	}
	
}
