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

	private PlayerZone playerZone;
	private int tileX, tileY;

	private Polygon hitbox;

	private float[] vertices;

	public Tile(int tileX, int tileY, GameWorld world) {
		this.world = world;
		this.tileX = tileX;
		this.tileY = tileY;

		setX(tileX * Constants.TILE_BOTTOM_RIGHT_X);

		if (tileX % 2 == 0 || tileX == 0) {
			setY(tileY * Constants.TILE_HEIGHT);
		} else {
			setY(tileY * Constants.TILE_HEIGHT + Constants.TILE_HEIGHT / 2);
		}

		 vertices = new float[] { 
				 getX(), getY() + 11, 
				 getX() + 8,getY() + 19, 
				 getX() + 23, getY() + 19, 
				 getX() + 31, getY() + 11, 
				 getX() + 23, getY() + 3, 
				 getX() + 8, getY() + 3, };
//		vertices = new float[] { getX() - 5, getY() - 5, getX() - 5,
//				getY() + 5, getX() + 5, getY() + 5, getX() + 5, getY() - 5, };

		hitbox = new Polygon();
		hitbox.setOrigin(getX(), getY());
		hitbox.setVertices(vertices);

		playerZone = PlayerZone.NONE;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(TextureHandler.tileGrass, getX(), getY());

		switch (playerZone) {
		case PLAYER1:
			batch.draw(TextureHandler.tilePlayer1Zone, getX(), getY());
			break;
		case PLAYER2:
			batch.draw(TextureHandler.tilePlayer2Zone, getX(), getY());
			break;
		case PLAYER3:
			batch.draw(TextureHandler.tilePlayer3Zone, getX(), getY());
			break;
		case PLAYER4:
			batch.draw(TextureHandler.tilePlayer4Zone, getX(), getY());
			break;

		}

		// ZONE TYPE eg. MOUNTAIN, FOREST => render special!

	}

	public void drawDebug(ShapeRenderer debugRenderer) {
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.setColor(Color.RED);
		debugRenderer.polygon(vertices);
		debugRenderer.end();
	}

	public ArrayList<Tile> getAdjecentTiles() {
		ArrayList<Tile> adjecent = new ArrayList<Tile>();

		return adjecent;
	}

	public PlayerZone getPlayerZone() {
		return playerZone;
	}

	public void setPlayerZone(PlayerZone PlayerZone) {
		this.playerZone = PlayerZone;
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

	public enum TileType {
		GRASS, FOREST, MOUNTAIN;
	}

	public enum PlayerZone {
		NONE, PLAYER1, PLAYER2, PLAYER3, PLAYER4;
	}

}
