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

	public static final int MAX_MOVE_DISTANCE = 3;
	private GameWorld world;
	private ArrayList<Tile> moveableTiles;

	private int moveDistanceLeft;

	public Unit(int tileX, int tileY, GameWorld world) {
		super(tileX, tileY);
		this.world = world;
		moveDistanceLeft = MAX_MOVE_DISTANCE;
	}

	@Override
	public void onClick() {
		moveableTiles = getPossibleMovement();
		for (Tile t : moveableTiles) {
			t.setMovementHighlight(true);
		}
	}

	/**
	 * Gets the tiles that the unit can move from its current position.
	 * 
	 * @return Null if no valid movement is found
	 */
	private ArrayList<Tile> getPossibleMovement() {

		ArrayList<Tile> possibleMovement = new ArrayList<Tile>();
		ArrayList<Tile> visited = new ArrayList<Tile>();
		DistanceNode currentTile;

		Queue<DistanceNode> tileQueue = new LinkedList<DistanceNode>();

		tileQueue.add(new DistanceNode(0,
				world.getMap()[getTileX()][getTileY()]));

		while (!tileQueue.isEmpty()) {
			currentTile = tileQueue.poll();
			if (currentTile.distance <= getMoveDistance()
					&& !visited.contains(currentTile.tile)) {
				possibleMovement.add(currentTile.tile);
				for (Tile t : currentTile.tile.getAdjecentTiles()) {
					tileQueue
							.add(new DistanceNode(currentTile.distance + 1, t));
				}
				visited.add(currentTile.tile);
			}
		}
		possibleMovement.remove(world.getMap()[getTileX()][getTileY()]);
		return possibleMovement;
	}

	@Override
	public void onUnselect() {
		int stuffs = 0;
		for (Tile t : moveableTiles) {
			stuffs++;
			t.setMovementHighlight(false);
		}
		System.out.println("unselected the stuffs, movement removed from "
				+ stuffs);
	}

	@Override
	public void move(int tileX, int tileY) {
		setTilePosition(tileX, tileY);
	}

	public void resetMoveDistance() {
		moveDistanceLeft = MAX_MOVE_DISTANCE;
	}

	public int getMoveDistance() {
		return moveDistanceLeft;
	}

}
