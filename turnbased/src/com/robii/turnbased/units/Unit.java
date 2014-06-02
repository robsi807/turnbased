package com.robii.turnbased.units;

import java.util.ArrayList;

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
import com.robii.turnbased.input.SelectedUnitGuiButton;
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
		world.getGameScreen().getGuiHandler()
				.addSelectedUnitButtons(generateGuiButtons(getGuiItems()));
	}

	private ArrayList<SelectedUnitGuiButton> generateGuiButtons(
			ArrayList<String> guiItems) {
		ArrayList<SelectedUnitGuiButton> buttons = new ArrayList<SelectedUnitGuiButton>();
		SelectedUnitGuiButton addbutton;
		for (int i = 0; i < guiItems.size(); i++) {
			addbutton = new SelectedUnitGuiButton(0, i * 40, guiItems.get(i),
					world, guiItems.get(i));
			addbutton.getHitbox().x = world.getGameScreen().getGuiHandler().getGuiCam().viewportWidth
					- addbutton.getHitbox().getWidth();
			buttons.add(addbutton);

		}
		
		System.out.println("adding " + buttons.size() + " buttons to gui");

		return buttons;
	}

	public abstract ArrayList<String> getGuiItems();

	public void startAttack() {

	}




	@Override
	public void onUnselect() {
		for (DistanceNode d : moveableTiles) {
			d.tile.setDistanceFromSelectedUnit(0);
		}
	}

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
