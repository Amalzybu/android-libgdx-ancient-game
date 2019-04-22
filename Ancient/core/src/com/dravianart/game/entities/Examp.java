package com.dravianart.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class Examp {
	public float x,y,width,height,stateTime=0;
	public Texture blk;
	public Crect r;
	public boolean am;
	Animation anm=null;
	public Examp(float x,float y,float width,float height,Texture tx,float m,float nx,float ny,boolean am)
	{
		r=new Crect(x,y,(width*m)+nx,(height*m)+ny);
		this.x=x;
		this.y=y;
		this.width=width*m;
		this.height=height*m;
		blk=tx;
		this.am=am;
		if(this.am)
		{
			anm=new Animation(0.2f,new Array<TextureRegion>(TextureRegion.split(tx, (int)width, (int)height)[0]));
			anm.setPlayMode(PlayMode.LOOP);
		}
	}
	public void update()
	{
		r.move(x, y);
	}
	public void dispose()
	{
		blk.dispose();
	}
	public void render(SpriteBatch batch)
	{
		stateTime+=Gdx.graphics.getDeltaTime();
		batch.draw((TextureRegion) anm.getKeyFrame(stateTime), x, y, width,height);
	}

}
