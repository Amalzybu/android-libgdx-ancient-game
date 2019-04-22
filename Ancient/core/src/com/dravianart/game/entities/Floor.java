package com.dravianart.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class Floor {
	public Crect r;
	public static final float width=240;
	public static final float height=50;
	
	public float x,y,x1,y1;;
	public Animation flr;
	Texture t;
	public Floor(float x,float y,Texture t)
	{
		r=new Crect(x,y,(240*4)-50,15*4);
		flr=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(t, 240, 50)[0]),PlayMode.LOOP);
		this.x=x;
		this.y=y;
		x1=x+width*4;
		y1=y+height*4;
	}
	public void update()
	{
		r.move(x, y);
	}
	public void resetSpd(float s)
	{
		flr.setFrameDuration(s);
	}

}
