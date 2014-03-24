package com.robii.turnbased.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.world.GameWorld;

public class BasicMeleeUnit extends Unit {

	private int MAX_HP = 2;
	
	public BasicMeleeUnit(int tileX, int tileY, int ownerId, GameWorld world) {
		super(tileX, tileY, ownerId, world);
		setHp(MAX_HP);
	}

	@Override
	public void drawThis(SpriteBatch batch, int yOffset) {
		position = GameWorld.getPositionForTile(getTileX(), getTileY());
		batch.draw(TextureHandler.unitBasicMelee, position.x
				+ getOffsetFromTile().x, position.y + 10
				+ getOffsetFromTile().y + yOffset);
		
		setStartColor(batch.getColor());
		batch.setColor(getPlayerColorHighlight());
		batch.draw(TextureHandler.unitBasicMeleeColor, position.x
				+ getOffsetFromTile().x, position.y + 10
				+ getOffsetFromTile().y + yOffset);
		batch.setColor(getStartColor());
		
	}

	@Override
	public Vector2 getOffsetFromTile() {
		return new Vector2(0, 0);
	}

}
