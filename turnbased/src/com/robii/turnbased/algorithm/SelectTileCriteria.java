package com.robii.turnbased.algorithm;

import com.robii.turnbased.units.Unit;

public interface SelectTileCriteria {

	public boolean isTileValid(Unit unit, DistanceNode currentTile);
}
