package com.robii.turnbased.input;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.world.GameWorld;

public abstract class GuiButton {

	private Rectangle hitbox;
	private String text;
	private GameWorld world;

	public GuiButton(int x, int y, int width, int height, String text,
			GameWorld world) {
		this.text = text;
		this.world = world;
		hitbox = new Rectangle(x, y, width, height);
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

	public void draw(SpriteBatch guiBatch) {
		guiBatch.draw(TextureHandler.guiBtnEndTurn, hitbox.x, hitbox.y);
	}
}
