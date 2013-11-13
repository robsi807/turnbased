package com.robii.turnbased.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.Tile;

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
		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				map[x][y] = new Tile(x * Constants.TILE_WIDTH, y
						* Constants.TILE_HEIGHT);
			}
		}
	}

	// TEST FUNCTION, REMOVE!!

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		
		
	}
}
