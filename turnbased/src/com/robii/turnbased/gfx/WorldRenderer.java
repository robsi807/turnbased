package com.robii.turnbased.gfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.robii.turnbased.Constants;
import com.robii.turnbased.world.GameWorld;

public class WorldRenderer {

	private GameWorld world;
	private ShapeRenderer debugRenderer;
	private SpriteBatch batch;
	// for rendering of the player zones
	private Color color;

	private OrthographicCamera camera;

	public WorldRenderer(GameWorld world) {
		this.world = world;
		this.camera = new OrthographicCamera(Constants.WIDTH
				* Constants.VIEWPORT_SCALE, Constants.HEIGHT
				* Constants.VIEWPORT_SCALE);
		camera.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2, 0f);
		batch = new SpriteBatch();
	}

	public void drawWorld() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawTiles(batch);
		//drawObjects(batch);

		batch.end();
	}

	private void drawTiles(SpriteBatch batch) {
		for (int y = world.getMap().getMapTileHeight() - 1; y >= 0; y--) {
			for (int x = 1; x < world.getMap().getMapTileWidth(); x += 2) {
				world.getMap().getTile(x, y).drawTile(batch, 1f);
			}
			for (int x = 0; x < world.getMap().getMapTileWidth(); x += 2) {
				world.getMap().getTile(x, y).drawTile(batch, 1f);
			}

		}

	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
