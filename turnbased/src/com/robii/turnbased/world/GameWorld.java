package com.robii.turnbased.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.Constants;
import com.robii.turnbased.GameScreen;
import com.robii.turnbased.gameobjects.Clickable;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.gameobjects.Town;
import com.robii.turnbased.gfx.WorldRenderer;
import com.robii.turnbased.player.PlayerHandler;
import com.robii.turnbased.units.Unit;

public class GameWorld {

	private boolean debugMode = false;

	private Unit selectedObject;

	private GameMap map;

	private PlayerHandler players;

	private WorldRenderer worldRenderer;

	private GameScreen gameScreen;

	public GameWorld(int nrOfPlayers, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		players = new PlayerHandler(this);

		map = new GameMap(this);
		selectedObject = null;
		worldRenderer = new WorldRenderer(this);
	}

	public void endTurn() {
		players.nextPlayer();
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

	public void selectObjectAtTile(int tileX, int tileY) {
		unselectObject();
		map.getTile(tileX, tileY).setyOffset(3);
		if (map.getTile(tileX, tileY).getChildObject() instanceof Unit)
			selectedObject = (Unit) map.getTile(tileX, tileY).getChildObject();
		if (selectedObject instanceof Clickable) {
			((Clickable) selectedObject).onClick();
		}

	}

	public Unit getSelectedObject() {
		return selectedObject;
	}

	public WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}

	public PlayerHandler getPlayers() {
		return players;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public Camera getGuiCamera(){
		return gameScreen.getGuiCamera();
	}

}
