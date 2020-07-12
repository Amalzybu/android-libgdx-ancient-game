package com.dravianart.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class Block {
	public float x,y;
	public Animation blk;
	public Crect r;
	public Block(float x,float y,Texture bk)
	{
		r=new Crect(x,y+3,50*3,10*3);
		this.x=x;
		this.y=y;
		blk= new Animation(0.1f, new Array<TextureRegion>(TextureRegion.split(bk, 50, 30)[0]),PlayMode.LOOP);
	}
	public void update()
	{
		r.move(x, y);
	}
	

}
