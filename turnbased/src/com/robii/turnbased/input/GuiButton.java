package com.robii.turnbased.input;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.robii.turnbased.Constants;
import com.robii.turnbased.world.GameWorld;

public abstract class GuiButton {

	private Rectangle hitbox;
	private String text;
	private GameWorld world;
	private TextBounds textBounds;
	
	private BitmapFont font;

	public GuiButton(int x, int y, String text, GameWorld world) {
		this.text = text;
		this.world = world;
		textBounds = FontHandler.font[Constants.GUI_FONT_SIZE].getBounds(text);
		hitbox = new Rectangle(x, y, textBounds.width + 10,
				textBounds.height + 5);
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public GameWorld getWorld() {
		return world;
	}

	public abstract void onClick();

	public void draw(SpriteBatch guiBatch, ShapeRenderer debugRenderer) {
		guiBatch.end();
		if (debugRenderer == null)
			debugRenderer = new ShapeRenderer();

		debugRenderer.begin(ShapeType.Filled);
		debugRenderer.setColor(Color.BLACK);
		debugRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		debugRenderer.end();
		guiBatch.begin();
		font = world.getGameScreen().getGuiHandler().getGuiFont();
		font.setColor(Color.WHITE);
		font.draw(
				guiBatch,
				text,
				hitbox.x
						+ Math.abs((hitbox.width - FontHandler.font[Constants.GUI_FONT_SIZE]
								.getBounds(text).width)) / 2,
				hitbox.y
						+ hitbox.height
						- Math.abs((hitbox.height - FontHandler.font[Constants.GUI_FONT_SIZE]
								.getBounds(text).height)) / 2);
		

	}
}
