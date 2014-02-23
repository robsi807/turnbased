package com.robii.turnbased.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.robii.turnbased.gameobjects.GameObject;

public class Player {
	private Color color;
	private int gold, id;
	private ArrayList<GameObject> objects;

	public Player(Color color, int id, int gold) {
		this.color = color;
		this.gold = gold;
		this.id = id;
		objects = new ArrayList<GameObject>();
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

	public void addObject(GameObject obj){
		objects.add(obj);
	}
	
	public ArrayList<GameObject> getObjects() {
		return objects;
	}
	

}
