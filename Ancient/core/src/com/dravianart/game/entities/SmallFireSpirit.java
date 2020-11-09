package com.dravianart.game.entities;

import java.util.Vector;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class SmallFireSpirit extends Actor{

	private Animation anm,holeanm;
	private Texture spirit,tcret;
	private float stateTime=0,stateTime1=0,size=1;
	private boolean start=false;
	public Crect rect;
	Vector<OrthoEnemyOne> enamies;
	Vector<Object> arr;
	public SmallFireSpirit(Texture spirit,Texture nexthole,Texture tcret,float x,float y,float size,Vector<Object> arr,Vector<OrthoEnemyOne> enamies) {
		this.size=size;
		super.setX(x);
		super.setY(y);
		this.arr=arr;
		rect=new Crect(x, y, 100*size, 100*size);
		this.spirit=spirit;
		this.tcret=tcret;
		Sprite tex=new Sprite(this.spirit);
		this.enamies=enamies;
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tex.getTexture(), 100, 100);
		TextureRegion[][] holeSpriteSheet = TextureRegion.split(nexthole, 35, 25);
		holeanm= new Animation(0.1f, new Array<TextureRegion>(holeSpriteSheet[0]),PlayMode.NORMAL);
		anm = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.NORMAL);
	}
	@Override
	   public void draw(Batch batch, float alpha){
		if(start&&!anm.isAnimationFinished(stateTime)) {
			stateTime+=alpha;
//			if(anm.isAnimationFinished(stateTime))stateTime=6;
		}
		else {
		for(Object p:arr) {
			OrthoEnemyOneMinion k=(OrthoEnemyOneMinion) p;
			
			start=rect.isCollided(k.rect);
			if(start) {
				System.out.println("coollliiddedd");
				enamies.clear();
				break;
			}
			
		}
		
		}
		
		
//		batch.draw(tcret,rect.x, rect.y, rect.width, rect.height);
		
		batch.draw((TextureRegion) anm.getKeyFrame(stateTime), getX(), getY(), 100*size, 100*size);

		if(anm.isAnimationFinished(stateTime)) {
			System.out.println("animation finished");
			stateTime1+=(!holeanm.isAnimationFinished(stateTime1))?alpha:0;
			batch.draw((TextureRegion) holeanm.getKeyFrame(stateTime1), getX(), getY(), 35*3, 25*3);
		}
	}
	
}
