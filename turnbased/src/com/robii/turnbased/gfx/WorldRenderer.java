package com.robii.turnbased.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.robii.turnbased.gameobjects.Visible;
import com.robii.turnbased.world.GameWorld;

public class WorldRenderer {

	private GameWorld world;
	private ShapeRenderer debugRenderer;

	public WorldRenderer(GameWorld world) {
		this.world = world;
	}

	public void drawWorld(SpriteBatch batch) {
		drawMap(batch);
		drawObjects(batch);
	}

	private void drawMap(SpriteBatch batch) {
		for (int y = world.getMap()[0].length - 1; y >= 0; y--) {
			for (int x = 1; x < world.getMap().length; x += 2) {
				world.getMap()[x][y].draw(batch, 1f);
			}
			for (int x = 0; x < world.getMap().length; x += 2) {
				world.getMap()[x][y].draw(batch, 1f);
			}

		}

		if (world.isDebugMode()) {
			batch.end();
			if (debugRenderer == null)
				debugRenderer = new ShapeRenderer();
			debugRenderer.setProjectionMatrix(world.getStage().getCamera().combined);

			for (int y = 0; y < world.getMap()[0].length; y++) {
				for (int x = 0; x < world.getMap().length; x++) {
					world.getMap()[x][y].drawDebug(debugRenderer);
				}
			}
			batch.begin();
		}
	}

	private void drawObjects(SpriteBatch batch) {
		for (Visible obj : world.getVisObjects()) {
			obj.drawThis(batch);
		}
	}
}
