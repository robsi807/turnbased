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

		tileQueue.add(new DistanceNode(0, world.getMap().getTile(getTileX(),
				getTileY())));
		if (tileQueue.peek() == null)
			System.out.println("peek == null");
		System.out.println("getting possible movement from " + getTileX()
				+ ", " + getTileY());

		while (!tileQueue.isEmpty()) {
			currentTile = tileQueue.poll();
			if (currentTile.distance <= getMoveDistance()
					&& !visited.contains(currentTile.tile)
					&& (currentTile.tile.getChildObject() == null || currentTile.tile == world
							.getMap().getTile(getTileX(), getTileY()))) {
				possibleMovement.add(currentTile.tile);

				for (Tile t : world.getMap().getAdjecentTiles(
						currentTile.tile.getTileX(),
						currentTile.tile.getTileY())) {
					tileQueue
							.add(new DistanceNode(currentTile.distance + 1, t));
				}
				visited.add(currentTile.tile);
			}
		}
		possibleMovement.remove(world.getMap().getTile(getTileX(), getTileY()));
		return possibleMovement;
	}

	@Override
	public void onUnselect() {
		for (Tile t : moveableTiles) {
			t.setMovementHighlight(false);
		}
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

	public ArrayList<Tile> getMoveableTiles() {
		return moveableTiles;
	}

}
