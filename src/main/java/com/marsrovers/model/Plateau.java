package com.marsrovers.model;

import lombok.Data;

@Data
public class Plateau {

	private Integer sizeX;
	private Integer sizeY;

	// Singleton
	private static final Plateau instance = new Plateau();

	private Plateau() {

	}

	public static Plateau getInstance() {
		return instance;
	}
}
