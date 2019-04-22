package com.dravianart.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Crect;
import tools.Gravity;

public class Tree extends Gravity{
	public float esj=0;
	public int pos=0,sprint=0;
	public int jflg=0;
	float stateTime=0,point=0;
	public boolean painjmp=false,npain=false,start=true;
	public int spcount=1;
	public int life;
	public Animation[] rolls;
	Texture tre;
	public Tree(Texture tr)
	{
		stateTime+=Gdx.graphics.getDeltaTime();
		if(npain)
		{
			if(start)
			{
				point=stateTime;
				start=false;
			}
			if(stateTime>=point+1)
			{
				start=true;
				npain=false;
			}
			
		}
		x=200;
		tre=tr;
		life=5;
		y=500;
		r=new Crect(x+(6*7),y+(3*7),8*7,15*7);
		rolls=new Animation[7];
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tre, 20, 20);
		rolls[0] = new Animation(0.3f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		rolls[1] = new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[1]),PlayMode.LOOP);
		rolls[2] = new Animation(0.05f, new Array<TextureRegion>(rollSpriteSheet[2]),PlayMode.LOOP);
		//rolls[3] = new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[3]),PlayMode.LOOP);
		rolls[3] = new Animation(0.2f, rollSpriteSheet[3]);
		//rolls[4] = new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[4]),PlayMode.LOOP);
		rolls[4] = new Animation(0.2f, rollSpriteSheet[4]);
		rolls[5] = new Animation(0.05f, rollSpriteSheet[5]);
		rolls[6] =   new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[6]),PlayMode.LOOP);
		
	}
	public void update()
	{
		if(spcount==5)
		{
			spcount=0;
		}
		r.move(x, y);
	}
	public void dispose()
	{
		tre.dispose();
	}
	
	

}
