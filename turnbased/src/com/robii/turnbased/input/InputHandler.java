package com.robii.turnbased.input;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.GameObject;
import com.robii.turnbased.units.Unit;
import com.robii.turnbased.world.GameWorld;

public class InputHandler implements GestureListener {

	private float realCameraX, realCameraY;
	private boolean camaraInitialized = false;
	private GameWorld world;
	private Ray gameRay;
	private Ray guiRay;
	private Vector2 clickTile;
	private InputState inputState;
	private GuiButton clickedButton;

	public InputHandler(GameWorld world) {
		this.world = world;
		inputState = InputState.SELECT_UNIT;

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {

		// checking if the gui is clicked
		guiRay = world.getGuiCamera().getPickRay(x, y);
		clickedButton = getGuiButtonClick(guiRay.origin.x, guiRay.origin.y);
		if (clickedButton != null) {
			clickedButton.onClick();
			return true;
		}

		// checking if a tile is clicked
		gameRay = world.getWorldRenderer().getCamera().getPickRay(x, y);
		clickTile = getTilePosFromCoords(gameRay.origin.x, gameRay.origin.y);
		if (clickTile == null)
			return false;
		switch (inputState) {

		case MOVE_UNIT:
			GameObject selectedObj = world.getSelectedObject();

			if (selectedObj != null) {

				if (world.getMap()
						.getTile((int) clickTile.x, (int) clickTile.y) != null
						&& world.getMap()
								.getTile((int) clickTile.x, (int) clickTile.y)
								.getDistanceFromSelectedUnit() > 0) {
					// reset the y offset of the tile
					if (selectedObj instanceof Unit)
						world.getMap().moveUnitTo((Unit) selectedObj,
								(int) clickTile.x, (int) clickTile.y);

				}
				world.unselectObject();
				inputState = InputState.SELECT_UNIT;
				return true;
			}
			break;

		case SELECT_UNIT:

			// child != null and the player clicking owns the unit
			if (world.getMap().getTile((int) clickTile.x, (int) clickTile.y)
					.getChildObject() != null
					&& world.getMap()
							.getTile((int) clickTile.x, (int) clickTile.y)
							.getChildObject().getOwnerId() == world
							.getPlayers().getCurrentPlayer().getId()) {

				world.selectObjectAtTile((int) clickTile.x, (int) clickTile.y);

				if (Unit.class.isAssignableFrom(world.getSelectedObject()
						.getClass())) {
					inputState = InputState.MOVE_UNIT;
				}

				return true;

			}
			break;

		}

		return false;
	}

	private GuiButton getGuiButtonClick(float x, float y) {
		for (GuiButton btn : world.getGameScreen().getButtons()) {
			if (btn.getHitbox().contains(new Vector2(x, y)))
				return btn;
		}
		return null;
	}

	private Vector2 getTilePosFromCoords(float x, float y) {
		for (int j = 0; j < world.getMap().getMapTileHeight(); j++) {
			for (int i = 0; i < world.getMap().getMapTileWidth(); i++) {
				if (world.getMap().getTile(i, j).getHitbox()
						.contains(gameRay.origin.x, gameRay.origin.y)) {
					return new Vector2(i, j);
				}
			}
		}
		return null;
	}

	@Override
	public boolean longPress(float x, float y) {
		gameRay = world.getWorldRenderer().getCamera().getPickRay(x, y);
		clickTile = getTilePosFromCoords(gameRay.origin.x, gameRay.origin.y);

		if (clickTile != null) {
			world.getMap().addTown((int) clickTile.x, (int) clickTile.y,
					world.getPlayers().getCurrentPlayer().getId());
			return true;
		}

		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if (!camaraInitialized) {
			realCameraX = world.getWorldRenderer().getCamera().position.x;
			realCameraY = world.getWorldRenderer().getCamera().position.y;
			camaraInitialized = true;

		}
		realCameraX = realCameraX - deltaX * Constants.CAMERA_SENS;
		realCameraY = realCameraY + deltaY * Constants.CAMERA_SENS;

		world.getWorldRenderer().getCamera().position.set(realCameraX,
				realCameraY, 0);
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	public enum InputState {
		SELECT_UNIT, MOVE_UNIT, MENU;
	}

}
