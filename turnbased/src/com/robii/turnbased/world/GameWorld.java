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
import com.robii.turnbased.gfx.WorldRenderer;

public class GameWorld extends Actor {

	private boolean debugMode = false;

	private Stage stage;
	private Tile[][] map;
	private ArrayList<ArrayList<GameObject>> playerObjects;
	private ArrayList<Visible> visObjects;
	private WorldRenderer worldRenderer;

	public GameWorld(Stage stage, int nrOfPlayers) {
		this.stage = stage;
		this.playerObjects = new ArrayList<ArrayList<GameObject>>();
		for (int i = 0; i < nrOfPlayers; i++) {
			this.playerObjects.add(new ArrayList<GameObject>());
		}
		stage.addActor(this);
		map = new Tile[12][12];
		visObjects = new ArrayList<Visible>();
		worldRenderer = new WorldRenderer(this);
		fillWorld();
	}

	// TEST FUNCTION, REMOVE!!

	private void fillWorld() {

		int tempX = 0, tempY = 0;

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {

				map[x][y] = new Tile(x, y, this);
			}
			tempX = 0;
		}

		addTown(4, 4, 1);

	}

	// TEST FUNCTION, REMOVE!!

	public void addTown(int tileX, int tileY, int player) {
		Town addTown = new Town(tileX, tileY);
		// player is the number not the index, that is why -1
		playerObjects.get(player - 1).add(addTown);
		map[tileX][tileY].setChildObject(addTown);

		for (Tile t : map[tileX][tileY].getAdjecentTiles()) {
			if (t.getPlayerZone() == 0) {
				t.setPlayerZone(player);
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		worldRenderer.drawWorld(batch);
	}

	public Tile[][] getMap() {
		return map;
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

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public ArrayList<ArrayList<GameObject>> getPlayerObjects() {
		return playerObjects;
	}

	public ArrayList<Visible> getVisObjects() {
		return visObjects;
	}

}
