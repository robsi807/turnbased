package com.robii.turnbased.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class Tile extends Actor {

	private int playerZone;
	private int tileX, tileY;
	private int yOffset = 0;

	private Unit childObject;
	private TileType type;
	private int distanceFromSelectedUnit;

	private Polygon hitbox;
	private Color color;

	private float[] vertices;

	public Tile(int tileX, int tileY, TileType type) {
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

		playerZone = -1; // no players zone
	}

	public void drawTile(SpriteBatch batch, GameWorld world) {

		batch.draw(TextureHandler.tileGrass, getX(), getY() + getyOffset());

		color = batch.getColor();
		drawPlayerZone(batch, world);

		if (distanceFromSelectedUnit > 0) {
			batch.setColor(1f, 0.9f, 0f, 0.5f);
			batch.draw(TextureHandler.tileMovementHighlight, getX(), getY()
					+ getyOffset());
			batch.setColor(color.r, color.g, color.b, 1f);
		}

		switch (type) {
		case FOREST:
			batch.draw(TextureHandler.objForest, getX() + 2, getY() + 3);
			break;
		case GOLDMINE:
			batch.draw(TextureHandler.objGoldmine, getX(), getY() + 2);
			break;
		case MOUNTAIN:
			batch.draw(TextureHandler.objMountain, getX(), getY() + 3);
			break;
		}

		if (childObject != null && childObject instanceof Visible)
			((Visible) childObject).drawThis(batch, yOffset);

	}

	private void drawPlayerZone(SpriteBatch batch, GameWorld world) {

		if (playerZone < 0)
			return;
		color = batch.getColor();
		batch.setColor(world.getPlayers().getPlayerWithId(playerZone)
				.getColor().r, world.getPlayers().getPlayerWithId(playerZone)
				.getColor().g, world.getPlayers().getPlayerWithId(playerZone)
				.getColor().b, 0.3f);
		batch.draw(TextureHandler.tilePlayerZone, getX(), getY() + getyOffset());
		batch.setColor(color.r, color.g, color.b, 1f);
	}

	public void drawDebug(ShapeRenderer debugRenderer) {
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.setColor(Color.RED);
		debugRenderer.polygon(vertices);
		debugRenderer.end();
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

	public Unit getChildObject() {
		return childObject;
	}

	public void setChildObject(Unit childObject) {
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

	public TileType getType() {

		return type;
	}

	public int getDistanceFromSelectedUnit() {
		return distanceFromSelectedUnit;
	}

	public void setDistanceFromSelectedUnit(int distanceFromSelectedUnit) {
		this.distanceFromSelectedUnit = distanceFromSelectedUnit;
	}

}
