package com.robii.turnbased;

import com.badlogic.gdx.Game;
import com.robii.turnbased.gfx.TextureHandler;

public class TurnBased extends Game {

	@Override
	public void create() {
		TextureHandler.init();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		TextureHandler.dispose();
	}

}
