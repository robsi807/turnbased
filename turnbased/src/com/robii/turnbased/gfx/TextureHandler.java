package com.robii.turnbased.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureHandler {

	// texture files
	private static Texture tiles;

	// tiles
	public static TextureRegion tileGrass;

	public static void init() {
		initTiles();
	}

	private static void initTiles() {
		tiles = new Texture("images/tiles.png");
		tileGrass = new TextureRegion(tiles, 0, 0, 32, 20);
	}

	public static void dispose() {
		tiles.dispose();
	}

}
