package com.robii.turnbased.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Visible {
	public void drawThis(SpriteBatch batch);

	public Vector2 getOffsetFromTile();
}
