package com.robii.turnbased.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.world.GameWorld;

public class Tile extends Actor {

	private GameWorld world;

	private int playerZone;
	private int tileX, tileY;
	private int yOffset = 0;

	private GameObject childObject;
	private TileType type;

	private Polygon hitbox;

	private float[] vertices;

	public Tile(int tileX, int tileY, TileType type, GameWorld world) {
		this.world = world;
		this.tileX = tileX;
		this.tileY = tileY;
		this.type = type;

		setX(tileX * Constants.TILE_BOTTOM_RIGHT_X);

		if (tileX % 2 == 0 || tileX == 0) {
			setY(tileY * Constants.TILE_HEIGHT);
		} else {
			setY(tileY * Constants.TILE_HEIGHT + Constants.TILE_HEIGHT / 2);
		}

		// this is just the hitbox for the tiles. It doesn't have to make sense
		vertices = new float[] { getX(), getY() + 11, getX() + 8, getY() + 19,
				getX() + 23, getY() + 19, getX() + 31, getY() + 11,
				getX() + 23, getY() + 3, getX() + 8, getY() + 3, };

		hitbox = new Polygon();
		hitbox.setOrigin(getX(), getY());
		hitbox.setVertices(vertices);

		playerZone = 0;
	}

	public void drawTile(SpriteBatch batch, float parentAlpha) {

		if (childObject != null && childObject instanceof Visible)
			((Visible) childObject).drawThis(batch, yOffset);
		switch (type) {
		case FOREST:
			batch.draw(TextureHandler.objForest, getX()+3, getY() + 3);
			break;

		case GOLDMINE:
			batch.draw(TextureHandler.objGoldmine, getX()+5, getY()+ 2);
			break;

		case MOUNTAIN:
			batch.draw(TextureHandler.objMountain, getX()+3, getY()+ 3);
			break;
		}
	}

	public void drawDebug(ShapeRenderer debugRenderer) {
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.setColor(Color.RED);
		debugRenderer.polygon(vertices);
		debugRenderer.end();
	}

	public ArrayList<Tile> getAdjecentTiles() {
		ArrayList<Tile> adjecent = new ArrayList<Tile>();

		if (getTileX() == 0 || getTileX() % 2 == 0) {

			adjecent.add(getTileIfValid(getTileX(), getTileY() + 1));
			adjecent.add(getTileIfValid(getTileX() - 1, getTileY()));
			adjecent.add(getTileIfValid(getTileX() + 1, getTileY()));
			adjecent.add(getTileIfValid(getTileX() - 1, getTileY() - 1));
			adjecent.add(getTileIfValid(getTileX(), getTileY() - 1));
			adjecent.add(getTileIfValid(getTileX() + 1, getTileY() - 1));

		} else {
			adjecent.add(getTileIfValid(getTileX(), getTileY() - 1));
			adjecent.add(getTileIfValid(getTileX() - 1, getTileY()));
			adjecent.add(getTileIfValid(getTileX() + 1, getTileY()));
			adjecent.add(getTileIfValid(getTileX() - 1, getTileY() + 1));
			adjecent.add(getTileIfValid(getTileX(), getTileY() + 1));
			adjecent.add(getTileIfValid(getTileX() + 1, getTileY() + 1));
		}

		return adjecent;
	}

	private Tile getTileIfValid(int tileX, int tileY) {
		if (tileX < world.getMap().length && tileX >= 0
				&& tileY < world.getMap()[0].length && tileY >= 0)
			return world.getMap()[tileX][tileY];

		return null;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Polygon getHitbox() {
		return hitbox;
	}

	public GameObject getChildObject() {
		return childObject;
	}

	public void setChildObject(GameObject childObject) {
		this.childObject = childObject;
	}

	public enum TileType {
		GRASS, FOREST, MOUNTAIN, GOLDMINE;
	}

	public int getPlayerZone() {
		return playerZone;
	}

	public void setPlayerZone(int playerZone) {
		this.playerZone = playerZone;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

}
