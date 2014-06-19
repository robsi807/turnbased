package com.robii.turnbased.actions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.algorithm.TileSelector;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public abstract class ActionBase {

	private TileSelector selector;
	private String actionName;
	
	public ActionBase(TileSelector selector, String actionName) {
		this.selector = selector;
		this.actionName = actionName;
	}

	public abstract boolean use();
	
	/**
	 * Gets the tiles that the unit can move from its current position.
	 * 
	 * @return Null if no valid movement is found
	 */
	protected ArrayList<DistanceNode> getPossibleTilesForAction(GameWorld world, Unit unit) {

		ArrayList<DistanceNode> possibleMovement = new ArrayList<DistanceNode>();
		ArrayList<Tile> visited = new ArrayList<Tile>();
		DistanceNode currentTile;

		Queue<DistanceNode> tileQueue = new LinkedList<DistanceNode>();

		tileQueue.add(new DistanceNode(0, world.getMap().getTile(unit.getTileX(),
				unit.getTileY())));
		while (!tileQueue.isEmpty()) {
			currentTile = tileQueue.poll();
			if (!visited.contains(currentTile.tile) && selector.isTileValid(unit, currentTile)) {
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
		possibleMovement.remove(world.getMap().getTile(unit.getTileX(), unit.getTileY()));
		return possibleMovement;
	}

	public TileSelector getSelector() {
		return selector;
	}

	public String getActionName() {
		return actionName;
	}
	
}
