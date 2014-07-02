package com.robii.turnbased.input;

import com.robii.turnbased.actions.ActionBase;
import com.robii.turnbased.world.GameWorld;

public class SelectedUnitGuiButton extends GuiButton {

	private ActionBase action;

	public SelectedUnitGuiButton(int x, int y, String text, GameWorld world,
			ActionBase action) {
		super(x, y, text, world);
		this.action = action;
	}

	@Override
	public void onClick() {
		action.use();
	}

}
