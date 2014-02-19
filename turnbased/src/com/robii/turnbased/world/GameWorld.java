package com.robii.turnbased.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.Clickable;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Player;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.gameobjects.Town;
import com.robii.turnbased.gfx.WorldRenderer;
import com.robii.turnbased.units.BasicMeleeUnit;
import com.robii.turnbased.units.Unit;

public class GameWorld {

	private boolean debugMode = false;

	private GameObject selectedObject;

	private GameMap map;

	private ArrayList<Player> players;
	private WorldRenderer worldRenderer;

	private int currentPlayerId = 0;

	public GameWorld(int nrOfPlayers, int currentPlayerId) {
		this.players = new ArrayList<Player>();
		this.currentPlayerId = currentPlayerId;

		// FIX THIS, THIS IS TEMP FOR TESTING
		players.add(new Player(Color.BLUE, 0, 0));
		// FIX ABOVE!!

		map = new GameMap(this);
		selectedObject = null;
		worldRenderer = new WorldRenderer(this);
		playerIncome();
	}

	private void playerIncome() {
		int income = 0;
		for (GameObject obj : getPlayers().get(currentPlayerId).getObjects()) {
			if (obj instanceof Town) {
				for (Tile t : map.getAdjecentTiles(obj.getTileX(),
						obj.getTileY())) {
					if (t.getType() == TileType.GOLDMINE)
						income += 5;
				}
			}
		}
	}

	private void endTurn() {
		currentPlayerId++;
		if (currentPlayerId >= players.size())
			currentPlayerId = 0;
	}

	public void draw() {
		worldRenderer.drawWorld();
	}

	public GameMap getMap() {
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
			map.getTile(selectedObject.getTileX(), selectedObject.getTileY())
					.setyOffset(0);

			if (selectedObject instanceof Clickable) {
				((Clickable) selectedObject).onUnselect();
			}
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
		map.getTile(tileX, tileY).setyOffset(3);
		selectedObject = map.getTile(tileX, tileY).getChildObject();
		if (selectedObject instanceof Clickable) {
			((Clickable) selectedObject).onClick();
		}

	}

	public GameObject getSelectedObject() {
		return selectedObject;
	}

	public WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}

}
