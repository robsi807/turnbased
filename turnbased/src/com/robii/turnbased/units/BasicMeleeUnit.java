package com.robii.turnbased.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.world.GameWorld;

public class BasicMeleeUnit extends Unit {

	public BasicMeleeUnit(int tileX, int tileY, GameWorld world) {
		super(tileX, tileY, world);
	}

	@Override
	public void drawThis(SpriteBatch batch, int yOffset) {
		position = GameWorld.getPositionForTile(getTileX(), getTileY());
		batch.draw(TextureHandler.unitBasicMelee, position.x
				+ getOffsetFromTile().x, position.y + 10 + getOffsetFromTile().y
				+ yOffset);
	}

	@Override
	public Vector2 getOffsetFromTile() {
		return new Vector2(0, 0);
	}

	@Override
	public void onUnselect() {

	}

	@Override
	public int getMoveDistance() {
		return 3;
	}

}
