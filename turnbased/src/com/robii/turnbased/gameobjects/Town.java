package com.robii.turnbased.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class Town extends Unit {

	public Town(int tileX, int tileY, int ownerId, GameWorld world) {
		super(tileX, tileY, ownerId, world);
	}

	@Override
	public void drawThis(SpriteBatch batch, int yOffset) {
		position = GameWorld.getPositionForTile(getTileX(), getTileY());
		batch.draw(TextureHandler.unitTown, position.x + getOffsetFromTile().x,
				position.y + getOffsetFromTile().y + yOffset);

		setStartColor(batch.getColor());
		batch.setColor(getPlayerColorHighlight());
		batch.draw(TextureHandler.unitTownColor, position.x
				+ getOffsetFromTile().x, position.y+ 3+ yOffset);
		batch.setColor(getStartColor());
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
