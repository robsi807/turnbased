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
	public void drawThis(SpriteBatch batch, int yOffset) {
		position = GameWorld.getPositionForTile(getTileX(), getTileY());
		batch.draw(TextureHandler.unitTown, position.x + getOffsetFromTile().x,
				position.y + getOffsetFromTile().y + yOffset);
	}

	@Override
	public void onClick() {
		System.out.println("SHOULD OPEN A MENU");
	}

	@Override
	public Vector2 getOffsetFromTile() {
		return new Vector2(0, 3);
	}

	@Override
	public void onUnselect() {
		
	}

}
