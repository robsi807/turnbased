package com.robii.turnbased.algorithm.selectors;

import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.algorithm.TileSelector;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class MoveTileSelector implements TileSelector {
	
	
	@Override
	public boolean isTileValid(Unit unit, DistanceNode currentTile) {
		return (currentTile.distance <= unit.getMoveDistanceLeft() && canMoveToTile(currentTile.tile, unit.getWorld()));
	}

	private boolean canMoveToTile(Tile tile, GameWorld world) {
		return (tile.getChildObject() == null || tile == world.getMap()
				.getTile(tile.getTileX(), tile.getTileY()))
				&& tile.getType() != TileType.MOUNTAIN;
	}

}
