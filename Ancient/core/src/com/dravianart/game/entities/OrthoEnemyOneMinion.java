package com.dravianart.game.entities;

import java.util.Vector;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import tools.Crect;
import tools.MinionsCollisionDetection;

public class OrthoEnemyOneMinion extends Actor {
	
	public float oldx,oldy,size,statusTime;
	private Texture minion,crect;
	private TiledMapTileLayer collisionLayer;
	float speed=100;
	private float increment;
	private Animation[] anm=new Animation[1];

	public Crect tree,rect;
	public OrthoEnemyOneMinion(float x,float y,float size,Texture minion,Texture crect,TiledMapTileLayer collisionLayer,Crect tree) {
		super.setX(x);
		super.setY(y);
		this.collisionLayer=collisionLayer;
		this.size=size;
		this.tree=tree;
		rect=new Crect(x,y,40,40);
		this.crect=crect;
		Sprite tex=new Sprite(minion);
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tex.getTexture(), 25, 25);
//		rect=new Crect(x+20, y, 75, 50);
		anm[0] = new Animation(0.05f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		
		
	
	}
	
	
	public void render(Batch batch, float alpha) {
		statusTime+=alpha;
		float l=speed*alpha;
		if(tree.x>getX()) {
			l=-l;
		}
		batch.draw(crect,rect.x, rect.y, rect.width, rect.height);
		batch.draw((TextureRegion) anm[0].getKeyFrame(statusTime), getX(), getY(),40,40);
		float m=(tree.y-getY())/(tree.x-getX());
		float x=(float) (getX()-l*(1 / Math.sqrt((1/1 + (m * m)))));
		setX(x);

		float y=(float) (getY()-(m*l)*(1 / Math.sqrt((1/1 + (m * m)))));
		setY(y);
//		if(y>oldy&&collidesTop()) {
//			setY(oldy);
//		}
//		else if(y<oldy&&collidesBottom()) {
//			setY(oldy);
//		}
		
		oldx=getX();
		oldy=getY();
		rect.x=x;
		rect.y=y;
	}
	
	@Override
	   public void act(float delta){
		increment = collisionLayer.getTileWidth();
		increment = 40 < increment ? 40 / 2 : increment / 2;
	}
	
	
	
	
	

}
