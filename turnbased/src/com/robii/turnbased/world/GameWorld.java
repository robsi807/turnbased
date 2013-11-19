package com.robii.turnbased.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gfx.TextureHandler;

public class GameWorld extends Actor {

	private Stage stage;
	private Tile[][] map;

	public GameWorld(Stage stage) {
		this.stage = stage;
		stage.addActor(this);
		map = new Tile[12][12];
		fillWorld();
	}

	// TEST FUNCTION, REMOVE!!

	private void fillWorld() {

		int tempX = 0, tempY = 0;

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				tempX += Constants.TILE_BOTTOM_RIGHT_X;
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
	}

	// TEST FUNCTION, REMOVE!!

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
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
}
