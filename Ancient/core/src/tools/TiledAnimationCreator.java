package tools;

import java.util.Iterator;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class TiledAnimationCreator {
	
	private String[] keys=null;
	private TiledMap map=null;
	private String tileset=null;
	
	public TiledAnimationCreator(TiledMap map,String[] keys,String tileset) {
		
		this.map=map;
		this.keys=keys;
		this.tileset=tileset;
		
	}
	
	public void create() {
		
		for(String a:keys) {
		Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(2);
		Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet(tileset).iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			if(tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals(a))
			{
				System.out.println("added");
				frameTiles.add((StaticTiledMapTile) tile);
		
			}
		}
		AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(1 / 3f, frameTiles);

		// background layer
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("stones");
		for(int x = 0; x < layer.getWidth(); x++)
			for(int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if(cell!=null) {
				if(cell.getTile().getProperties().containsKey("animation") && cell.getTile().getProperties().get("animation", String.class).equals(a))
					cell.setTile(animatedTile);
				}
			}
	}
	}

}
