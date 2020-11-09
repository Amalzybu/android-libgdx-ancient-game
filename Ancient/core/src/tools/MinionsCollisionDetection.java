package tools;

import java.util.Vector;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dravianart.game.entities.OrthoEnemyOneMinion;

public class MinionsCollisionDetection {
	public TiledMapTileLayer collisionLayer;
	private float increment;
	Vector<Object> minions=null;
	Crect tree;
	public MinionsCollisionDetection(Vector<Object> minions,TiledMapTileLayer collisionLayer,Crect tree) {
		this.collisionLayer=collisionLayer;
		this.tree=tree;
		this.minions=minions;
		increment = collisionLayer.getTileWidth();
		increment = 40 < increment ? 40 / 2 : increment / 2;
		
	}
	
	


	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) ((x+30)/ collisionLayer.getTileWidth()), (int) ((y+20) / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}
	
	
	public void collidesRight() {
		for(float step = 0; step <= 40; step += increment)
			for(Object oem:minions) {
				OrthoEnemyOneMinion oemin=(OrthoEnemyOneMinion) oem;
			if(isCellBlocked(oemin.getX() + 40, oemin.getY() + step)) {
				minions.remove(oem);
			}
				
			}
	}

	public void collidesLeft() {
		for(float step = 0; step <= 40; step += increment)
			for(Object oem:minions) {
				OrthoEnemyOneMinion oemin=(OrthoEnemyOneMinion) oem;
			if(isCellBlocked(oemin.getX(), oemin.getY() + step)) {
				minions.remove(oem);
			}
				
			}
	}

	public void collidesTop() {
		for(float step = 0; step <= 30; step += increment) {
			for(Object oem:minions) {
				OrthoEnemyOneMinion oemin=(OrthoEnemyOneMinion) oem;
			if(isCellBlocked(oemin.getX() + step, oemin.getY() +30)) {
				minions.remove(oem);
			}
			}
				
		}

	}

	public void collidesBottom() {
		for(float step = 0; step <= 20; step += increment)
			for(Object oem:minions) {
				OrthoEnemyOneMinion oemin=(OrthoEnemyOneMinion) oem;
			if(isCellBlocked(oemin.getX() + step, oemin.getY())) {
				minions.remove(oem);
			}
			}
			
	}
	
	public void collides() {
		Vector tmp=new Vector<Object>();
		for(float step = 0; step <= 20; step += increment)
			for(Object oem:minions) {
				OrthoEnemyOneMinion oemin=(OrthoEnemyOneMinion) oem;
			if(isCellBlocked(oemin.getX(), oemin.getY())) {
				tmp.add(oem);
			}
			}
		
		minions.removeAll(tmp);
			
	}
	

}
