package com.robii.turnbased.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class PlayerHandler {
	private ArrayList<Player> players;
	private int currentPlayerId = 0;

	public PlayerHandler() {
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
		currentPlayerId++;
		if (currentPlayerId >= getNumberOfPlayers())
			currentPlayerId = 0;
	}
	
	public Player getPlayerWithId(int id){
		return players.get(id);
	}

}
