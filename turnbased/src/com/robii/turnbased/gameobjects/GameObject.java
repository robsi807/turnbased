package com.robii.turnbased.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class GameObject {

	private int tileX, tileY;
	protected Vector2 position;

	public GameObject(int tileX, int tileY) {
		this.tileX = tileX;
		this.tileY = tileY;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

}
