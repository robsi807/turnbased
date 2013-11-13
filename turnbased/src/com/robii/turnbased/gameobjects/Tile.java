package com.robii.turnbased.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robii.turnbased.gfx.TextureHandler;

public class Tile extends Actor {

	public Tile(float x, float y) {
		setX(x);
		setY(y);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(TextureHandler.tileGrass, getX(), getY());
	}

}
