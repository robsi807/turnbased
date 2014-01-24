package com.robii.turnbased.gameobjects;

import java.util.ArrayList;

import com.robii.turnbased.world.GameWorld;

public abstract class Unit extends GameObject implements Visible, Clickable,
		Movable {

	private GameWorld world;

	public Unit(int tileX, int tileY, GameWorld world) {
		super(tileX, tileY);
		this.world = world;
	}

	@Override
	public void onClick() {
		//set movementHightlight to true for the affected tiles
	}

	private ArrayList<Tile> getPossibleMovement() {
		// Algorithm
		return null;
	}

}
