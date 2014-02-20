package com.robii.turnbased.world;

import java.util.ArrayList;

import com.robii.turnbased.Constants;
import com.robii.turnbased.gameobjects.Tile;
import com.robii.turnbased.gameobjects.Town;
import com.robii.turnbased.gameobjects.Tile.TileType;
import com.robii.turnbased.units.BasicMeleeUnit;
import com.robii.turnbased.units.Unit;

public class GameMap {
	private Tile[][] map;
	private GameWorld world;

	public GameMap(GameWorld world) {

		this.world = world;

		map = new Tile[Constants.MAP_TILE_WIDTH][Constants.MAP_TILE_HEIGHT];
		fillMap();
	}

	private void fillMap() {

		for (int y = 0; y < getMapTileHeight(); y++) {
			for (int x = 0; x < getMapTileWidth(); x++) {
				map[x][y] = new Tile(x, y, TileType.GRASS);
			}
		}

		map[2][2] = new Tile(2, 2, TileType.FOREST);
		map[4][3] = new Tile(4, 3, TileType.GOLDMINE);
		map[6][2] = new Tile(6, 2, TileType.MOUNTAIN);
		map[10][5] = new Tile(10, 5, TileType.GOLDMINE);
		map[5][8] = new Tile(5, 8, TileType.GOLDMINE);

		addTown(4, 4, 1);
		addTown(5, 7, 1);

		addUnit(3, 3, 1, new BasicMeleeUnit(3, 3, world));
	}

	public int getMapTileHeight() {
		return map[0].length;
	}

	public int getMapTileWidth() {
		return map.length;
	}

	public void addUnit(int tileX, int tileY, int player, Unit unit) {
		world.getPlayers().get(player - 1).addObject(unit);
		map[tileX][tileY].setChildObject(unit);
	}

	public void addTown(int tileX, int tileY, int player) {

		Town addTown = new Town(tileX, tileY);
		world.getPlayers().get(player - 1).addObject(addTown);
		map[tileX][tileY].setChildObject(addTown);

		for (Tile t : getAdjecentTiles(tileX, tileY)) {
			if (t != null && t.getPlayerZone() == 0) {
				t.setPlayerZone(player);
			}
		}
	}

	public boolean moveUnitTo(Unit unit, int tileX, int tileY) {
		Tile moveToTile = getTileIfValid(tileX, tileY);
		if (moveToTile == null)
			return false;
		
		return true;
	}

	public Tile getTile(int tileX, int tileY) {
		return map[tileX][tileY];
	}

	public ArrayList<Tile> getAdjecentTiles(int tileX, int tileY) {
		ArrayList<Tile> adjecent = new ArrayList<Tile>();

		if (tileX == 0 || tileX % 2 == 0) {

			addTile(adjecent, getTileIfValid(tileX, tileY + 1));
			addTile(adjecent, getTileIfValid(tileX - 1, tileY));
			addTile(adjecent, getTileIfValid(tileX + 1, tileY));
			addTile(adjecent, getTileIfValid(tileX - 1, tileY - 1));
			addTile(adjecent, getTileIfValid(tileX, tileY - 1));
			addTile(adjecent, getTileIfValid(tileX + 1, tileY - 1));

		} else {
			addTile(adjecent, getTileIfValid(tileX, tileY - 1));
			addTile(adjecent, getTileIfValid(tileX - 1, tileY));
			addTile(adjecent, getTileIfValid(tileX + 1, tileY));
			addTile(adjecent, getTileIfValid(tileX - 1, tileY + 1));
			addTile(adjecent, getTileIfValid(tileX, tileY + 1));
			addTile(adjecent, getTileIfValid(tileX + 1, tileY + 1));
		}

		return adjecent;
	}

	private void addTile(ArrayList<Tile> tiles, Tile addTile) {
		if (addTile != null)
			tiles.add(addTile);
	}

	private Tile getTileIfValid(int tileX, int tileY) {
		if (tileX < map.length && tileX >= 0 && tileY < map[0].length
				&& tileY >= 0)
			return map[tileX][tileY];

		return null;
	}

}
