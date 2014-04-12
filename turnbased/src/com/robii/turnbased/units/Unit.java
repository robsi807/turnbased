package com.robii.turnbased.units;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robii.turnbased.Constants;
import com.robii.turnbased.algorithm.DistanceNode;
import com.robii.turnbased.gameobjects.Clickable;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Movable;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.gameobjects.Visible;
import com.robii.turnbased.input.FontHandler;
import com.robii.turnbased.world.GameWorld;

public abstract class Unit extends GameObject implements Visible, Clickable,
		Movable {

	private GameWorld world;
	private ArrayList<DistanceNode> moveableTiles;

	private Color playerColorHighlight;
	private Color startColor;

	// for drawing the stats on the screen
	private BitmapFont font;

	private int ownerId;

	private int moveDistanceLeft;
	private int maxMoveDistance;
	private int damage;
	private int hp;

	public Unit(int tileX, int tileY, int ownerId, GameWorld world) {
		super(tileX, tileY);
		this.world = world;
		this.ownerId = ownerId;
		playerColorHighlight = getWorld().getPlayers().getPlayerWithId(ownerId)
				.getColor();
	}

	@Override
	public void onClick() {
		moveableTiles = getPossibleMovement();
		for (DistanceNode d : moveableTiles) {
			d.tile.setDistanceFromSelectedUnit(d.distance);
		}
		world.getGameScreen().getGuiHandler().addSelectedUnitButtons(getGuiItems());
	}

	public void startAttack() {

	}

	/**
	 * Gets the tiles that the unit can move from its current position.
	 * 
	 * @return Null if no valid movement is found
	 */
	private ArrayList<DistanceNode> getPossibleMovement() {

		ArrayList<DistanceNode> possibleMovement = new ArrayList<DistanceNode>();
		ArrayList<Tile> visited = new ArrayList<Tile>();
		DistanceNode currentTile;

		Queue<DistanceNode> tileQueue = new LinkedList<DistanceNode>();

		tileQueue.add(new DistanceNode(0, world.getMap().getTile(getTileX(),
				getTileY())));
		while (!tileQueue.isEmpty()) {
			currentTile = tileQueue.poll();
			if (currentTile.distance <= getMoveDistanceLeft()
					&& !visited.contains(currentTile.tile)
					&& canMoveToTile(currentTile.tile)) {
				possibleMovement.add(currentTile);

				for (Tile t : world.getMap().getAdjecentTiles(
						currentTile.tile.getTileX(),
						currentTile.tile.getTileY())) {
					tileQueue
							.add(new DistanceNode(currentTile.distance + 1, t));
				}
				visited.add(currentTile.tile);
			}
		}
		possibleMovement.remove(world.getMap().getTile(getTileX(), getTileY()));
		return possibleMovement;
	}

	private boolean canMoveToTile(Tile tile) {
		return (tile.getChildObject() == null || tile == world.getMap()
				.getTile(getTileX(), getTileY()))
				&& tile.getType() != TileType.MOUNTAIN;
	}

	@Override
	public void onUnselect() {
		for (DistanceNode d : moveableTiles) {
			d.tile.setDistanceFromSelectedUnit(0);
		}
	}
	
	public abstract ArrayList<String> getGuiItems();
	
	public abstract void handleGuiClick(String action);

	@Override
	public void move(int tileX, int tileY) {
		setTilePosition(tileX, tileY);
	}

	public void resetMoveDistance() {
		moveDistanceLeft = getMoveMaxDistance();
	}

	public int getMoveMaxDistance() {
		return maxMoveDistance;
	}

	public int getMoveDistanceLeft() {
		return moveDistanceLeft;
	}

	public void setMoveDistanceLeft(int moveDistanceLeft) {
		this.moveDistanceLeft = moveDistanceLeft;
	}

	public ArrayList<DistanceNode> getMoveableTiles() {
		return moveableTiles;
	}

	public void setMoveableTiles(ArrayList<DistanceNode> moveableTiles) {
		this.moveableTiles = moveableTiles;
	}

	public Color getPlayerColorHighlight() {
		return playerColorHighlight;
	}

	public GameWorld getWorld() {
		return world;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public Color getStartColor() {
		return startColor;
	}

	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	public void drawStats(SpriteBatch batch) {
		batch.begin();
		font = FontHandler.font[1];
		font.setColor(Color.RED);
		font.draw(batch, Integer.toString(hp), position.x + 3, position.y + 15);
		font.setColor(Color.WHITE);
		font.draw(batch, Integer.toString(damage), position.x
				+ Constants.TILE_WIDTH - 13, position.y + 15);
		batch.end();
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getMaxMoveDistance() {
		return maxMoveDistance;
	}

	public void setMaxMoveDistance(int maxMoveDistance) {
		this.maxMoveDistance = maxMoveDistance;
		moveDistanceLeft = maxMoveDistance;
	}

}
