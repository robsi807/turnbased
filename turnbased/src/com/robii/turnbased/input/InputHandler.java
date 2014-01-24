package com.robii.turnbased.input;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.world.GameWorld;

public class InputHandler implements GestureListener {

	private float realCameraX, realCameraY;
	private boolean camaraInitialized = false;
	private GameWorld world;
	private Ray ray;
	private Vector2 clickTile;

	public InputHandler(GameWorld world) {
		this.world = world;

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		ray = world.getWorldRenderer().getCamera().getPickRay(x, y);
		clickTile = getTilePosFromCoords(ray.origin.x, ray.origin.y);

		if (clickTile != null
				&& world.getMap()[(int) clickTile.x][(int) clickTile.y]
						.getChildObject() != null) {
			world.selectObjectAtTile((int) clickTile.x, (int) clickTile.y);
			return true;
		}
		if (world.getSelectedObject() != null) {
			world.unselectObject();
			return true;
		}

		return false;
	}

	private Vector2 getTilePosFromCoords(float x, float y) {
		for (int j = 0; j < world.getMap()[0].length; j++) {
			for (int i = 0; i < world.getMap().length; i++) {
				if (world.getMap()[i][j].getHitbox().contains(ray.origin.x,
						ray.origin.y)) {
					return new Vector2(i, j);
				}
			}
		}
		return null;
	}

	@Override
	public boolean longPress(float x, float y) {
		ray = world.getWorldRenderer().getCamera().getPickRay(x, y);
		clickTile = getTilePosFromCoords(ray.origin.x, ray.origin.y);

		if (clickTile != null) {
			world.addTown((int) clickTile.x, (int) clickTile.y, 1);
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

}
