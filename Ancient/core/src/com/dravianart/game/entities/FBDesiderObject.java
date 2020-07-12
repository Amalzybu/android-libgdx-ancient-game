package com.dravianart.game.entities;

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
	float staticTime=0;
	int id;
	Crect rect;
	public float x;
	public float y;
	float width;
	float height;
	Texture crecttext=null;
	Animation anm;
	public FBDesiderObject(Texture t,int id,int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.tm=t;
		this.id=id;
		this.width=width;
		this.height=height;
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tm, width, height);
		this.anm=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[0]));
		rect=new Crect(this.x, this.y, this.width/3, this.height/3);
		 crecttext=new Texture("levelThreeText/crect.png");
		
	}
	
	public boolean render(Batch batch,Crect tr) {
//		batch.draw(crecttext, rect.x,  rect.y,  rect.width, rect.height);
		if(tr.isCollided(rect)) {
		staticTime+=Gdx.app.getGraphics().getDeltaTime();
		batch.draw((TextureRegion) anm.getKeyFrame(staticTime), x, y, width/3, height/3);
		
		}
		else {
			staticTime=0;
			batch.draw((TextureRegion) anm.getKeyFrame(0), x, y, width/3, height/3);
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
