package com.robii.turnbased.gfx;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Visible;
import com.robii.turnbased.world.GameWorld;

public class WorldRenderer {

	private GameWorld world;
	private ShapeRenderer debugRenderer;
	private SpriteBatch batch;
	// for rendering of the player zones
	private Color color;

	private OrthographicCamera camera;

	private ArrayList<Vector2> playerZonePoints;

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
		drawObjects(batch);

		batch.end();
	}

	private void drawTiles(SpriteBatch batch) {
		for (int y = world.getMap()[0].length - 1; y >= 0; y--) {
			for (int x = 1; x < world.getMap().length; x += 2) {
				batch.draw(
						TextureHandler.tileGrass,
						world.getMap()[x][y].getX(),
						world.getMap()[x][y].getY()
								+ world.getMap()[x][y].getyOffset());
			}
			for (int x = 0; x < world.getMap().length; x += 2) {
				batch.draw(
						TextureHandler.tileGrass,
						world.getMap()[x][y].getX(),
						world.getMap()[x][y].getY()
								+ world.getMap()[x][y].getyOffset());
			}

		}

	}

	private void drawObjects(SpriteBatch batch) {
		for (int y = world.getMap()[0].length - 1; y >= 0; y--) {
			for (int x = 1; x < world.getMap().length; x += 2) {
				world.getMap()[x][y].drawTile(batch, 1f);
			}
			for (int x = 0; x < world.getMap().length; x += 2) {
				world.getMap()[x][y].drawTile(batch, 1f);
			}

		}

	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
