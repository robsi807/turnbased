package com.robii.turnbased.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.gameobjects.Town;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class PlayerHandler {
	private ArrayList<Player> players;
	private int currentPlayerId = 0;
	private GameWorld world;

	public PlayerHandler(GameWorld world) {
		this.world = world;
		this.players = new ArrayList<Player>();
		players.add(new Player(Color.BLUE, 0, 0));
		players.add(new Player(Color.RED, 1, 0));
	}

	public int getNumberOfPlayers() {
		return players.size();
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerId);
	}

	public void nextPlayer() {
		int income = 0;
		currentPlayerId++;
		if (currentPlayerId >= getNumberOfPlayers())
			currentPlayerId = 0;
		for (Unit unit : getPlayerWithId(currentPlayerId).getUnits()) {

			// reset movement
			unit.resetMoveDistance();

			// calculating income for the player
			if (unit instanceof Town) {
				for (Tile t : world.getMap().getAdjecentTiles(unit.getTileX(),
						unit.getTileY())) {
					if (t.getType() == TileType.GOLDMINE)
						income += Constants.GOLD_MINE_INCOME_RATE;
				}
			}

			// adding income for a player

		}
		getCurrentPlayer().addGold(income); // should not be able to build alot
											// of towns around a mine
		System.out.println("Income for player " + currentPlayerId + " = "
				+ income);
	}

	public Player getPlayerWithId(int id) {
		return players.get(id);
	}

}
