package com.robii.turnbased.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontHandler {

	public static BitmapFont[] font;

	public static void init() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/pixel.ttf"));
		font = new BitmapFont[20];
		for (int x = 0; x < 20; x++)
			font[x] = generator.generateFont((x + 1) * 8);
		generator.dispose();
	}

}
