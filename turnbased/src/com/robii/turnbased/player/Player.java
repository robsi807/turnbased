package com.robii.turnbased.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.robii.turnbased.units.Unit;

public class Player {
	private Color color;
	private int gold, id;
	private ArrayList<Unit> units;

	public Player(Color color, int id, int gold) {
		this.color = color;
		this.gold = gold;
		this.id = id;
		units = new ArrayList<Unit>();
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Color getColor() {
		return color;
	}

	public int getId() {
		return id;
	}

	public void addUnit(Unit unit){
		units.add(unit);
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}
	
	public void addGold(int amount){
		gold += amount;
	}
	

}
