package com.robii.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.input.InputHandler;
import com.robii.turnbased.world.GameWorld;

public class GameScreen implements Screen {

	private Stage stage;
	private GameWorld world;
	private Table layout;

	public GameScreen() {
		stage = new Stage();
		// world loader (game loader) loads the information about the current
		// state
		world = new GameWorld(stage, Constants.NR_OF_PLAYERS, 0);
		layout = new Table();
		layout.setFillParent(true);
		stage.addActor(layout);

		layout.addActor(world);

		TextButtonStyle style = new TextButtonStyle();
		style.up = TextureHandler.guiTurnOver;
		style.down = TextureHandler.guiTurnOver;
		style.font = new BitmapFont();

		final TextButton button1 = new TextButton("End Turn", style);
		
		button1.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: "
						+ button1.isChecked());
				button1.setText("Good job!");
			}
		});
		layout.add(button1);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(Constants.WIDTH * Constants.VIEWPORT_SCALE,
				Constants.HEIGHT * Constants.VIEWPORT_SCALE, true);
		stage.getCamera().translate(-stage.getGutterWidth(),
				-stage.getGutterHeight(), 0);
	}

	@Override
	public void show() {
		Gdx.input
				.setInputProcessor(new GestureDetector(new InputHandler(world)));
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	public Stage getStage() {
		return stage;
	}

}
