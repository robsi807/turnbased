package com.robii.turnbased.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Town;
import com.robii.turnbased.gameobjects.Visible;
import com.robii.turnbased.gfx.TextureHandler;

public class GameWorld extends Actor {

	private Stage stage;
	private Tile[][] map;
	private ArrayList<Visible> visObjects;

	public GameWorld(Stage stage) {
		this.stage = stage;
		stage.addActor(this);
		map = new Tile[12][12];
		visObjects = new ArrayList<Visible>();
		fillWorld();
	}

	// TEST FUNCTION, REMOVE!!

	private void fillWorld() {

		int tempX = 0, tempY = 0;

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				tempX = x * Constants.TILE_BOTTOM_RIGHT_X;
				if (x % 2 == 0 || x == 0) {
					tempY = y * Constants.TILE_HEIGHT;
				} else {
					tempY = y * Constants.TILE_HEIGHT + Constants.TILE_HEIGHT
							/ 2;
				}

				map[x][y] = new Tile(tempX, tempY);
			}
			tempX = 0;
		}

		visObjects.add(new Town(4, 4));

	}

	// TEST FUNCTION, REMOVE!!

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawMap(batch);
		drawObjects(batch);

	}

	private void drawMap(SpriteBatch batch) {
		for (int y = map[0].length - 1; y >= 0; y--) {
			for (int x = 1; x < map.length; x += 2) {
				batch.draw(TextureHandler.tileGrass, map[x][y].getX(),
						map[x][y].getY());
			}
			for (int x = 0; x < map.length; x += 2) {
				batch.draw(TextureHandler.tileGrass, map[x][y].getX(),
						map[x][y].getY());
			}

		}
	}

	private void drawObjects(SpriteBatch batch) {
		for (Visible obj : visObjects) {
			obj.drawThis(batch);
		}
	}

	public static Vector2 getPositionForTile(int x, int y) {
		Vector2 position = new Vector2();

		position.x = x * Constants.TILE_BOTTOM_RIGHT_X;
		if (x % 2 == 0 || x == 0) {
			position.y = y * Constants.TILE_HEIGHT;
		} else {
			position.y = y * Constants.TILE_HEIGHT + Constants.TILE_HEIGHT / 2;
		}
		return position;
	}
}
