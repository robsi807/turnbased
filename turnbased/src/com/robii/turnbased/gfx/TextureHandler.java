package com.robii.turnbased.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureHandler {

	// texture files
	private static Texture tiles;
	private static Texture objects;
	private static Texture gui;
	private static Texture units;

	// tiles
	public static TextureRegion tileGrass;
	public static TextureRegion tilePlayerZone;

	// objets
	
	public static TextureRegion objForest;
	public static TextureRegion objMountain;
	public static TextureRegion objGoldmine;

	// gui
	public static TextureRegion guiCoin;

	// units
	public static TextureRegion unitSoldier;
	public static TextureRegion unitSoldierColor;
	public static TextureRegion unitTown;
	
	public static void init() {
		initTiles();
		initObjects();
		initGui();
		initUnits();
	}

	private static void initUnits() {
		units = new Texture("images/units.png");
		unitSoldier = new TextureRegion(units, 0, 0, 14, 20);
		unitTown = new TextureRegion(units, 32, 0, 32, 26);
	}

	private static void initGui() {
		gui = new Texture("images/gui.png");
		guiCoin = new TextureRegion(new TextureRegion(gui, 0, 0, 12, 16));
	}

	private static void initTiles() {
		tiles = new Texture("images/tiles.png");
		tileGrass = new TextureRegion(tiles, 0, 0, 32, 20);
		tilePlayerZone = new TextureRegion(tiles, 32, 0, 32, 20);
	}

	private static void initObjects() {
		objects = new Texture("images/objects.png");
		objForest = new TextureRegion(objects, 0, 0, 32, 26);
		objMountain = new TextureRegion(objects, 32, 0, 32, 26);
		objGoldmine = new TextureRegion(objects, 64, 0, 32, 26);
	}

	public static void dispose() {
		tiles.dispose();
	}

}
