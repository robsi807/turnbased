package com.robii.turnbased.gfx;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Visible;
import com.robii.turnbased.world.GameWorld;

public class WorldRenderer {

	private GameWorld world;
	private ShapeRenderer debugRenderer;
	// for rendering of the player zones
	private Color color;

	private ArrayList<Vector2> playerZonePoints;

	public WorldRenderer(GameWorld world) {
		this.world = world;
	}

	public void drawWorld(SpriteBatch batch) {
		drawTiles(batch);
		drawPlayerZones(batch);
		drawObjects(batch);
	}

	private void drawTiles(SpriteBatch batch) {
		for (int y = world.getMap()[0].length - 1; y >= 0; y--) {
			for (int x = 1; x < world.getMap().length; x += 2) {
				batch.draw(
						TextureHandler.tileGrass,
						world.getMap()[x][y].getX(),
						world.getMap()[x][y].getY()
								+ world.getMap()[x][y].getyOffset());
			}
			for (int x = 0; x < world.getMap().length; x += 2) {
				batch.draw(
						TextureHandler.tileGrass,
						world.getMap()[x][y].getX(),
						world.getMap()[x][y].getY()
								+ world.getMap()[x][y].getyOffset());
			}

		}

	}

	private void drawPlayerZones(SpriteBatch batch) {

		for (Tile t : world.getTilesWithZone()) {

			color = batch.getColor();
			batch.setColor(color.r, color.g, color.b, 0.3f);

			switch (t.getPlayerZone()) {
			case 1:
				batch.draw(TextureHandler.tilePlayer1Zone, t.getX(), t.getY()
						+ t.getyOffset());
				break;
			case 2:
				batch.draw(TextureHandler.tilePlayer2Zone, t.getX(), t.getY()
						+ t.getyOffset());
				break;
			case 3:
				batch.draw(TextureHandler.tilePlayer3Zone, t.getX(), t.getY()
						+ t.getyOffset());
				break;
			case 4:
				batch.draw(TextureHandler.tilePlayer4Zone, t.getX(), t.getY()
						+ t.getyOffset());
				break;

			}
			batch.setColor(color.r, color.g, color.b, 1f);

		}
	}

	private void drawObjects(SpriteBatch batch) {
		for (int y = world.getMap()[0].length - 1; y >= 0; y--) {
			for (int x = 1; x < world.getMap().length; x += 2) {
				world.getMap()[x][y].drawTile(batch, 1f);
			}
			for (int x = 0; x < world.getMap().length; x += 2) {
				world.getMap()[x][y].drawTile(batch, 1f);
			}

		}

	}
}
