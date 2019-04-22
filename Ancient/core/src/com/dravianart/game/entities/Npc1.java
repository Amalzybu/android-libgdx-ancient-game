package com.dravianart.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import tools.Chat;
import tools.Crect;
import tools.Gravity;

public class Npc1 {
	public float x,y;
	public boolean b=true,ct=false;
	public Crect r;
	public Chat ch=null;
	public Animation[] rolls;
	float stateTime=0;
	boolean flg=true;
	SpriteBatch batch=null;
	Texture npc;
	String[] str={"hey....." ,"how could you"," get here ?","this is my pond idiot","get out from here",".........."};
	public Npc1(float x,float y,SpriteBatch batch,Texture np)
	{
		npc=np;
		this.batch=batch;
		ch=new Chat(str,batch,50,500,8,0.4f);
		this.x=x;
		this.y=y;
		r=new Crect(x,y,150,150);
		rolls=new Animation[4];
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(npc, 150, 150);
		rolls[0] = new Animation(0.8f, rollSpriteSheet[0]);
		rolls[1] = new Animation(0.3f, new Array<TextureRegion>(rollSpriteSheet[1]),PlayMode.LOOP);
		rolls[2] = new Animation(0.3f, new Array<TextureRegion>(rollSpriteSheet[2]),PlayMode.LOOP);
		rolls[3] = new Animation(0.5f, new Array<TextureRegion>(rollSpriteSheet[3]),PlayMode.LOOP);

	}
	public boolean render (float delta)
	{
		stateTime+=delta;
		if(x>-180)
		{
		batch.draw((TextureRegion) rolls[1].getKeyFrame(stateTime), x, y, 150*4, 150*4);
		b=true;
		}
		
		else if(x<-180)
		{
			if(flg)
			{
				stateTime=0;
				flg=false;
				
			}
			b=false;
			 if(!rolls[0].isAnimationFinished(stateTime))
			 {
			batch.draw((TextureRegion) rolls[0].getKeyFrame(stateTime), x, y+50, 150*4, 150*4);
			 }
			 else if(rolls[0].isAnimationFinished(stateTime)&&!ct)
			 {
				 batch.draw((TextureRegion) rolls[2].getKeyFrame(stateTime), x, y, 150*4, 150*4);
				ct=ch.render(delta);
			 }
			 else if(ct)
			 {
				 batch.draw((TextureRegion) rolls[3].getKeyFrame(stateTime), x, y, 150*4, 150*4);
				 b=true;
				 
			 }
			
		}
		
		return b;
	}
	public void update()
	{
		r.move(x, y);
	}
	public void dispose()
	{
		npc.dispose();
		ch.dispose();
	}

}
