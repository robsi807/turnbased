package com.robii.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.input.InputHandler;
import com.robii.turnbased.world.GameWorld;

public class GameScreen implements Screen {

	private Stage stage;
	private GameWorld world;

	public GameScreen() {
		stage = new Stage();
		world = new GameWorld(stage);
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
		stage.setViewport(Constants.WIDTH, Constants.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(),
				-stage.getGutterHeight(), 0);
	}

	@Override
	public void show() {
		Gdx.input
				.setInputProcessor(new GestureDetector(new InputHandler(stage, world)));
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
