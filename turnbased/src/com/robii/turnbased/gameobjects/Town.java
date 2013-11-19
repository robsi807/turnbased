package com.robii.turnbased.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.world.GameWorld;

public class Town extends GameObject implements Visible, Clickable {

	public Town(int tileX, int tileY) {
		super(tileX, tileY);
	}

	@Override
	public void drawThis(SpriteBatch batch) {
		position = GameWorld.getPositionForTile(getTileX(), getTileY());
		batch.draw(TextureHandler.objTown, position.x + getOffsetFromTile().x,
				position.y + getOffsetFromTile().y);
	}

	@Override
	public void onClick() {
		// WHEN CLICKED!!
	}

	@Override
	public Vector2 getOffsetFromTile() {
		return new Vector2(0, 3);
	}

}
