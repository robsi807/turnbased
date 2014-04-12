package com.robii.turnbased.input;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gfx.TextureHandler;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class GuiHandler {

	private GameWorld world;
	private BitmapFont guiFont;
	private ArrayList<GuiButton> buttons;
	private ArrayList<GuiButton> selectedUnitButtons;
	private ArrayList<GuiButton> addButtons;

	private boolean removeSelectedUnitGui = false;

	private SpriteBatch guiBatch;
	private ShapeRenderer debugRenderer;
	private OrthographicCamera guiCam;

	public GuiHandler(GameWorld world) {
		this.world = world;
		creatingHud();
	}

	private void creatingHud() {
		buttons = new ArrayList<GuiButton>();
		buttons.add(new GuiButton(5, 5, "End turn", world) {

			@Override
			public void onClick() {
				world.endTurn();
			}
		});
		buttons.add(new GuiButton(50, 50, "End turn", world) {

			@Override
			public void onClick() {
				world.endTurn();
			}
		});
		buttons.add(new GuiButton(100, 100, "End turn", world) {

			@Override
			public void onClick() {
				world.endTurn();
			}
		});

		// setting up the camera for the GUI
		debugRenderer = new ShapeRenderer();
		guiBatch = new SpriteBatch();
		guiCam = new OrthographicCamera(Constants.WIDTH
				* Constants.VIEWPORT_SCALE * 2, Constants.HEIGHT
				* Constants.VIEWPORT_SCALE * 2);
		guiCam.position.set(guiCam.viewportWidth / 2,
				guiCam.viewportHeight / 2, 0f);
		guiCam.update();
		guiBatch.setProjectionMatrix(guiCam.combined);

		debugRenderer.setProjectionMatrix(guiCam.combined);
	}

	public void drawGUI() {
		if (addButtons != null) {
			if (selectedUnitButtons == null)
				selectedUnitButtons = new ArrayList<GuiButton>();
			selectedUnitButtons.addAll(addButtons);
			addButtons = null;
		}

		if (removeSelectedUnitGui) {
			selectedUnitButtons = null;
			removeSelectedUnitGui = false;
		}

		guiBatch.begin();

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
		
		// this code makes it lag, why?
		if (selectedUnitButtons != null) {
			for (GuiButton btn : selectedUnitButtons) {
				btn.draw(guiBatch, debugRenderer);
			}
		}

		if (world.getSelectedObject() != null
				&& world.getSelectedObject() instanceof Unit) {
			guiBatch.end();
			((Unit) world.getSelectedObject()).drawStats(world
					.getWorldRenderer().getBatch());
			guiBatch.begin();
		}
		guiBatch.end();
	}

	public ArrayList<GuiButton> getButtons() {
		return buttons;
	}

	public OrthographicCamera getGuiCam() {
		return guiCam;
	}

	public void addSelectedUnitButtons(final ArrayList<String> guiItems) {
		addButtons = new ArrayList<GuiButton>();

		for (int i = 0; i < guiItems.size(); i++) {
			System.out.println("adding selected unit gui item " + i);
			addButtons.add(new GuiButton(150, i * 40, guiItems.get(i), world) {
				@Override
				public void onClick() {
					// world.getSelectedObject().handleGuiClick(guiItems.get(i));
				}
			});
		}
	}

	public void removeSelectedUnitButtons() {
		removeSelectedUnitGui = true;
	}

}
