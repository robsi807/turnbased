package com.robii.turnbased.units;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.gameobjects.Clickable;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Movable;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Visible;
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
		// set movementHightlight to true for the affected tiles
	}

	/**
	 * Gets the tiles that the unit can move from its current position.
	 * 
	 * @return Null if no valid movement is found
	 */
	private ArrayList<Tile> getPossibleMovement() {

		ArrayList<Tile> possibleMovement = new ArrayList<Tile>();
		DistanceNode currentTile;

		Queue<DistanceNode> tileQueue = new LinkedList<DistanceNode>();

		tileQueue.add(new DistanceNode(0,
				world.getMap()[getTileX()][getTileY()]));

		while (!tileQueue.isEmpty()) {
			currentTile = tileQueue.poll();
			if (currentTile.distance <= getMoveDistance()) {
				possibleMovement.add(currentTile.tile);
				for (Tile t : currentTile.tile.getAdjecentTiles()) {
					tileQueue.add(new DistanceNode(currentTile.distance + 1,
							currentTile.tile));
				}
			}
		}

		return possibleMovement;
	}

	@Override
	public void move(int tileX, int tileY) {
		setTilePosition(tileX, tileY);
	}

	public abstract int getMoveDistance();

}
