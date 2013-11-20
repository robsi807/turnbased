package com.robii.turnbased.input;

import java.awt.Point;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robii.turnbased.Constants;
import com.robii.turnbased.world.GameWorld;

public class InputHandler implements GestureListener {

	private Stage stage;
	private float realCameraX, realCameraY;
	private GameWorld world;
	private Ray ray;
	private Point clickTile;

	public InputHandler(Stage stage, GameWorld world) {
		this.stage = stage;
		this.world = world;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		ray = world.getStage().getCamera().getPickRay(x, y);
		clickTile = getTilePosFromCoords(ray.origin.x, ray.origin.y);
		System.out.println("Zone: " + world.getMap()[clickTile.x][clickTile.y].getPlayerZone());

		if (clickTile != null)
			world.addTown(clickTile.x, clickTile.y, 1);

		return false;
	}

	private Point getTilePosFromCoords(float x, float y) {
		for (int j = 0; j < world.getMap()[0].length; j++) {
			for (int i = 0; i < world.getMap().length; i++) {
				if (world.getMap()[i][j].getHitbox().contains(ray.origin.x,
						ray.origin.y)) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	@Override
	public boolean longPress(float x, float y) {
		if (world.isDebugMode())
			world.setDebugMode(false);
		else
			world.setDebugMode(true);
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		realCameraX = realCameraX - deltaX * Constants.CAMERA_SENS;
		realCameraY = realCameraY + deltaY * Constants.CAMERA_SENS;

		stage.getCamera().position.set((int) realCameraX, (int) realCameraY, 0);
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
