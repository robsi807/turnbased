package com.robii.turnbased.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureHandler {

	// texture files
	private static Texture tiles;
	private static Texture objects;

	// tiles
	public static TextureRegion tileGrass;
	public static TextureRegion tilePlayer1Zone;
	public static TextureRegion tilePlayer2Zone;
	public static TextureRegion tilePlayer3Zone;
	public static TextureRegion tilePlayer4Zone;

	// objets
	public static TextureRegion objTown;
	public static TextureRegion objForest;
	public static TextureRegion objMountain;
	public static TextureRegion objGoldmine;

	public static void init() {
		initTiles();
		initObjects();
	}

	private static void initTiles() {
		tiles = new Texture("images/tiles.png");
		tileGrass = new TextureRegion(tiles, 0, 0, 32, 20);
		tilePlayer1Zone = new TextureRegion(tiles, 32, 0, 32, 20);
		tilePlayer2Zone = new TextureRegion(tiles, 2 * 32, 0, 32, 20);
		tilePlayer3Zone = new TextureRegion(tiles, 3 * 32, 0, 32, 20);
		tilePlayer4Zone = new TextureRegion(tiles, 4 * 32, 0, 32, 20);
	}

	private static void initObjects() {
		objects = new Texture("images/objects.png");
		objTown = new TextureRegion(objects, 0, 0, 32, 26);
		objForest = new TextureRegion(objects, 32 + 1, 0, 32, 26);
		objMountain = new TextureRegion(objects, 2 * 32 + 2, 0, 32, 26);
		objGoldmine = new TextureRegion(objects, 3 * 32 + 3, 0, 32, 26);
	}

	public static void dispose() {
		tiles.dispose();
	}

}
