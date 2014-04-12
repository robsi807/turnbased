package com.robii.turnbased.input;

import com.robii.turnbased.world.GameWorld;

public class SelectedUnitGuiButton extends GuiButton{
	
	private String action;
	private GameWorld world;
	
	public SelectedUnitGuiButton(int x, int y, String text, GameWorld world, String action) {
		super(x, y, text, world);
		this.action = action;
		this.world = world;
	}

	@Override
	public void onClick() {
		world.getSelectedObject().handleGuiClick(action);
	}

}
