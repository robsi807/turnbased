package com.robii.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.robii.turnbased.input.InputHandler;
import com.robii.turnbased.world.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;

	public GameScreen() {
		// world loader (game loader) loads the information about the current
		// state
		world = new GameWorld(Constants.NR_OF_PLAYERS, 0);



	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.update(delta);
		world.draw();
	}

	@Override
	public void resize(int width, int height) {
		world.getWorldRenderer().getCamera().viewportWidth = Constants.WIDTH * Constants.VIEWPORT_SCALE;
		world.getWorldRenderer().getCamera().viewportHeight = Constants.HEIGHT * Constants.VIEWPORT_SCALE;
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

}
