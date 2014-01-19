package com.robii.turnbased.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Player;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.gameobjects.Town;
import com.robii.turnbased.gfx.WorldRenderer;

public class GameWorld {

	private boolean debugMode = false;

	private GameObject selectedObject;

	private Tile[][] map;
	private ArrayList<Player> players;
	private ArrayList<Tile> tilesWithZone;
	private WorldRenderer worldRenderer;

	private int currentPlayerId = 0;

	public GameWorld(int nrOfPlayers, int currentPlayerId) {
		this.players = new ArrayList<Player>();
		this.currentPlayerId = currentPlayerId;
		tilesWithZone = new ArrayList<Tile>();

		// FIX THIS, THIS IS TEMP FOR TESTING
		players.add(new Player(Color.BLUE, 0, 0));
		// FIX ABOVE!!

		map = new Tile[12][12];
		selectedObject = null;
		worldRenderer = new WorldRenderer(this);
		fillWorld();
		playerIncome(currentPlayerId);
	}

	// TEST FUNCTION, REMOVE!!

	private void playerIncome(int currentPlayerId2) {

	}

	private void fillWorld() {

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				map[x][y] = new Tile(x, y, TileType.GRASS, this);
			}
		}

		addTown(4, 4, 1);

		map[2][2] = new Tile(2, 2, TileType.FOREST, this);
		map[4][3] = new Tile(4, 3, TileType.GOLDMINE, this);
		map[6][2] = new Tile(6, 2, TileType.MOUNTAIN, this);
		map[10][5] = new Tile(10, 5, TileType.GOLDMINE, this);
		map[5][8] = new Tile(5, 8, TileType.GOLDMINE, this);
	}

	// TEST FUNCTION, REMOVE!!

	public void update(float delta) {
	}
	
	public void addTown(int tileX, int tileY, int player) {
		Town addTown = new Town(tileX, tileY);
		// player is the number not the index, that is why -1
		players.get(player - 1).addObject(addTown);
		map[tileX][tileY].setChildObject(addTown);

		for (Tile t : map[tileX][tileY].getAdjecentTiles()) {
			if (t != null && t.getPlayerZone() == 0) {
				tilesWithZone.add(t);
				t.setPlayerZone(player);
			}
		}
	}

	public void draw() {
		worldRenderer.drawWorld();
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

	public void unselectObject() {
		if (selectedObject != null) {
			map[selectedObject.getTileX()][selectedObject.getTileY()]
					.setyOffset(0);
			selectedObject = null;
		}
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void selectObjectAtTile(int tileX, int tileY) {
		unselectObject();
		getMap()[tileX][tileY].setyOffset(3);
		selectedObject = getMap()[tileX][tileY].getChildObject();

	}

	public GameObject getSelectedObject() {
		return selectedObject;
	}

	public ArrayList<Tile> getTilesWithZone() {
		return tilesWithZone;
	}

	public WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}

	

}
