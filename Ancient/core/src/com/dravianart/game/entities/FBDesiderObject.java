package com.dravianart.game.entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class FBDesiderObject implements Comparable<FBDesiderObject>{
	Texture tm=null;
	private float staticTime;
	Random rand=new Random();
	int id,multi;
	Crect rect;
	public float x;
	public float y;
	float width;
	float height;
	Texture crecttext=null;
	boolean intrAnimate=true;
	Animation anm;
	public FBDesiderObject(Texture t,int id,int multi,boolean intrAnimate,int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.tm=t;
		staticTime=rand.nextFloat() * (0.4f - 0) + 0;
				this.id=id;
		this.multi=multi;
		this.width=width;
		this.height=height;
		this.intrAnimate=intrAnimate;
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tm, width, height);
		if(id!=1) {
			System.out.println("static Time "+staticTime+" id = "+id+" id =1");
		this.anm=new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[0]), PlayMode.LOOP);
		}
		else {
			System.out.println("static Time "+staticTime+" id = "+id+" id =2");
			this.anm=new Animation(0.2f,  new Array<TextureRegion>(rollSpriteSheet[0]));
		}
		

		rect=new Crect(this.x, this.y, this.width/3, this.height/3);
		 crecttext=new Texture("levelThreeText/crect.png");
		
	}
	
	public boolean render(Batch batch,Crect tr) {
//		batch.draw(crecttext, rect.x,  rect.y,  rect.width, rect.height);
		if(intrAnimate) {
		if(tr.isCollided(rect)) {
		staticTime+=Gdx.app.getGraphics().getDeltaTime();
		
		batch.draw((TextureRegion) anm.getKeyFrame(staticTime), x, y, (width/3)*multi, (height/3)*multi);
		
		}
		else {
			staticTime=0;
			batch.draw((TextureRegion) anm.getKeyFrame(0), x, y, (width/3)*multi, (height/3)*multi);
		}
		}
		else {
			System.out.println("delta time "+Gdx.app.getGraphics().getDeltaTime()+" static time"+staticTime);
			staticTime+=Gdx.app.getGraphics().getDeltaTime();
			
			batch.draw((TextureRegion) anm.getKeyFrame(staticTime), x, y, (width/3)*multi, (height/3)*multi);
			
		}
		return true;
	}

	@Override
	public int compareTo(FBDesiderObject ob) {
		// TODO Auto-generated method stub
		int k=-1;
		if(ob.y>=this.y)
		{
			k=1;
		}
		else {
			k=-1;
		}
		return k;
	}
	
	
	

}
