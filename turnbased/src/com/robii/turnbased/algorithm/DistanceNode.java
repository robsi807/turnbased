package com.robii.turnbased.algorithm;

import com.robii.turnbased.gameobjects.Tile;

public class DistanceNode {
	public int distance;
	public Tile tile;

	public DistanceNode(int distance, Tile tile) {
		super();
		this.distance = distance;
		this.tile = tile;
	}

}
