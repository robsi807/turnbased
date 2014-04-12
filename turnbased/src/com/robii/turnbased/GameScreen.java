package com.robii.turnbased;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.input.FontHandler;
import com.robii.turnbased.input.GuiButton;
import com.robii.turnbased.input.InputHandler;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;

	private SpriteBatch guiBatch;
	private ShapeRenderer debugRenderer;
	private OrthographicCamera guiCam;
	
	private InputHandler inputHandler;

	private BitmapFont guiFont;

	private ArrayList<GuiButton> buttons;

	public GameScreen() {
		// world loader (game loader) loads the information about the current
		// state
		world = new GameWorld(Constants.NR_OF_PLAYERS, this);
		inputHandler = new InputHandler(world);
		creatingHud();

	}

	private void creatingHud() {
		buttons = new ArrayList<GuiButton>();
		buttons.add(new GuiButton(5, 5, "End turn", world) {

			@Override
			public void onClick() {
				world.endTurn();
				System.out.println("end turn");
			}
		});

		// setting up the camera for the GUI
		guiBatch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();
		guiCam = new OrthographicCamera(Constants.WIDTH
				* Constants.VIEWPORT_SCALE * 2, Constants.HEIGHT
				* Constants.VIEWPORT_SCALE * 2);
		guiCam.position.set(guiCam.viewportWidth / 2,
				guiCam.viewportHeight / 2, 0f);
		guiCam.update();
		guiBatch.setProjectionMatrix(guiCam.combined);
		debugRenderer.setProjectionMatrix(guiCam.combined);
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

		// drawing the gold
		guiBatch.setColor(Color.WHITE);
		guiBatch.draw(TextureHandler.guiCoin, 5f,
				Constants.HEIGHT * Constants.VIEWPORT_SCALE * 2
						- TextureHandler.guiCoin.getRegionHeight() * 2 - 5,
				TextureHandler.guiCoin.getRegionWidth() * 2,
				TextureHandler.guiCoin.getRegionHeight() * 2);

		// drawing the gold text
		guiFont = FontHandler.font[Constants.GUI_FONT_SIZE];
		guiFont.setColor(Color.YELLOW);
		guiFont.draw(guiBatch, "x"
				+ world.getPlayers().getCurrentPlayer().getGold(),
				10 + TextureHandler.guiCoin.getRegionWidth() * 2,
				Constants.HEIGHT * Constants.VIEWPORT_SCALE * 2 - 10);

		// drawing all buttons
		for (GuiButton btn : buttons) {
			btn.draw(guiBatch, debugRenderer);
		}

		if (world.getSelectedObject() != null
				&& world.getSelectedObject() instanceof Unit) {
			guiBatch.end();
			((Unit) world.getSelectedObject()).drawStats(world.getWorldRenderer().getBatch());
			guiBatch.begin();
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
				.setInputProcessor(new GestureDetector(inputHandler));
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

	public Camera getGuiCamera() {
		return guiCam;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}
	
}
