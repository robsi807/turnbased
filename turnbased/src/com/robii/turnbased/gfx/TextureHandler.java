package com.robii.turnbased.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureHandler {

	// texture files
	private static Texture tiles;
	private static Texture objects;

	// tiles
	public static TextureRegion tileGrass;

	// objets
	public static TextureRegion objTown;

	public static void init() {
		initTiles();
		initObjects();
	}

	private static void initTiles() {
		tiles = new Texture("images/tiles.png");
		tileGrass = new TextureRegion(tiles, 0, 0, 32, 20);
	}

	private static void initObjects() {
		objects = new Texture("images/objects.png");
		objTown = new TextureRegion(objects, 0, 0, 32, 26);
	}

	public static void dispose() {
		tiles.dispose();
	}

}
