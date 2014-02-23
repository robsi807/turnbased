package com.robii.turnbased.units;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.gameobjects.Clickable;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Movable;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.gameobjects.Visible;
import com.robii.turnbased.world.GameWorld;

public abstract class Unit extends GameObject implements Visible, Clickable,
		Movable {

	public static final int MAX_MOVE_DISTANCE = 3;
	private GameWorld world;
	private ArrayList<DistanceNode> moveableTiles;

	private Color playerColorHighlight;
	private Color startColor;

	private int ownerId;

	private int moveDistanceLeft;

	public Unit(int tileX, int tileY, int ownerId, GameWorld world) {
		super(tileX, tileY);
		this.world = world;
		this.ownerId = ownerId;
		moveDistanceLeft = MAX_MOVE_DISTANCE;
		playerColorHighlight = getWorld().getPlayers()
				.getPlayerWithId(ownerId).getColor();
	}

	@Override
	public void onClick() {
		moveableTiles = getPossibleMovement();
		for (DistanceNode d : moveableTiles) {
			d.tile.setDistanceFromSelectedUnit(d.distance);
		}
	}

	/**
	 * Gets the tiles that the unit can move from its current position.
	 * 
	 * @return Null if no valid movement is found
	 */
	private ArrayList<DistanceNode> getPossibleMovement() {

		ArrayList<DistanceNode> possibleMovement = new ArrayList<DistanceNode>();
		ArrayList<Tile> visited = new ArrayList<Tile>();
		DistanceNode currentTile;

		Queue<DistanceNode> tileQueue = new LinkedList<DistanceNode>();

		tileQueue.add(new DistanceNode(0, world.getMap().getTile(getTileX(),
				getTileY())));
		while (!tileQueue.isEmpty()) {
			currentTile = tileQueue.poll();
			if (currentTile.distance <= getMoveDistance()
					&& !visited.contains(currentTile.tile)
					&& canMoveToTile(currentTile.tile)) {
				possibleMovement.add(currentTile);

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

	private boolean canMoveToTile(Tile tile) {
		return (tile.getChildObject() == null || tile == world.getMap()
				.getTile(getTileX(), getTileY()))
				&& tile.getType() != TileType.MOUNTAIN;
	}

	@Override
	public void onUnselect() {
		for (DistanceNode d : moveableTiles) {
			d.tile.setDistanceFromSelectedUnit(0);
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

	public int getMoveDistanceLeft() {
		return moveDistanceLeft;
	}

	public void setMoveDistanceLeft(int moveDistanceLeft) {
		this.moveDistanceLeft = moveDistanceLeft;
	}

	public ArrayList<DistanceNode> getMoveableTiles() {
		return moveableTiles;
	}

	public void setMoveableTiles(ArrayList<DistanceNode> moveableTiles) {
		this.moveableTiles = moveableTiles;
	}

	public Color getPlayerColorHighlight() {
		return playerColorHighlight;
	}

	public GameWorld getWorld() {
		return world;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public Color getStartColor() {
		return startColor;
	}

	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

}
