package com.robii.turnbased.algorithm.criterias;

import java.util.ArrayList;

import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.algorithm.SelectTileCriteria;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.units.Unit;

public class MoveTileCriteria implements SelectTileCriteria {

	private Unit unit;
	private Tile tile;
	
	
	
	@Override
	public boolean isTileValid(Unit unit, DistanceNode currentTile) {
		return (currentTile.distance <= unit.getMoveDistanceLeft() && canMoveToTile(currentTile.tile));
	}

	private boolean canMoveToTile(Tile tile) {
		return (tile.getChildObject() == null || tile == world.getMap()
				.getTile(getTileX(), getTileY()))
				&& tile.getType() != TileType.MOUNTAIN;
	}

}
