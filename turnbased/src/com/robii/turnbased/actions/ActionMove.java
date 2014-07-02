package com.robii.turnbased.actions;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.algorithm.TileSelector;
import com.robii.turnbased.algorithm.selectors.MoveTileSelector;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class ActionMove extends ActionBase {

	private GameWorld world;
	private Unit unit;
	private ArrayList<DistanceNode> movableTiles;

	public ActionMove(GameWorld world, Unit unit) {
		super(new MoveTileSelector(), "Move");
		this.world = world;
		this.unit = unit;
	}

	@Override
	public boolean use() {
		movableTiles = getPossibleTilesForAction(world, unit);

		for (DistanceNode d : movableTiles) {
			d.tile.setDistanceFromSelectedUnit(d.distance);
		}

		world.getGameScreen().getGuiHandler()
				.addSelectedUnitButtons(unit.generateGuiButtons());

		return false;
	}

}
