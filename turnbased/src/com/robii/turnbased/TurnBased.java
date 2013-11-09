package com.robii.turnbased;

import com.badlogic.gdx.Game;
import com.robii.turnbased.gfx.GameScreen;

public class TurnBased extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}

}
