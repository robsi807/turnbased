package com.robii.turnbased;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.input.GuiButton;
import com.robii.turnbased.input.InputHandler;
import com.robii.turnbased.world.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;

	private SpriteBatch guiBatch;
	private OrthographicCamera guiCam;

	private ArrayList<GuiButton> buttons;

	public GameScreen() {
		// world loader (game loader) loads the information about the current
		// state
		world = new GameWorld(Constants.NR_OF_PLAYERS, this);

		buttons = new ArrayList<GuiButton>();
		buttons.add(new GuiButton(30, 30, 64, 16, "test", world) {

			@Override
			public void onClick() {
				world.endTurn();
				System.out.println("end turn");
			}
		});

		guiBatch = new SpriteBatch();
		guiCam = new OrthographicCamera(Constants.WIDTH
				* Constants.VIEWPORT_SCALE, Constants.HEIGHT
				* Constants.VIEWPORT_SCALE);
		guiCam.position.set(guiCam.viewportWidth / 2,
				guiCam.viewportHeight / 2, 0f);
		guiCam.update();
		guiBatch.setProjectionMatrix(guiCam.combined);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.draw();
		drawGUI();
	}

	private void drawGUI() {
		guiBatch.begin();
		guiBatch.draw(TextureHandler.guiCoin, 5f,
				Constants.HEIGHT * Constants.VIEWPORT_SCALE
						- TextureHandler.guiCoin.getRegionHeight() - 5);
		for (GuiButton btn : buttons) {
			btn.draw(guiBatch);
		}
		guiBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		world.getWorldRenderer().getCamera().viewportWidth = Constants.WIDTH
				* Constants.VIEWPORT_SCALE;
		world.getWorldRenderer().getCamera().viewportHeight = Constants.HEIGHT
				* Constants.VIEWPORT_SCALE;
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

	public ArrayList<GuiButton> getButtons() {
		return buttons;
	}

}
