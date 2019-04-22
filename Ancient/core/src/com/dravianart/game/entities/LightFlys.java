package com.dravianart.game.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class LightFlys {
	Animation tex;
	public float x,y,stateTime,m,tx,ty,mx,my,px,py;
	boolean mov=false;
	Random r=new Random();
	public LightFlys(Texture t,float x,float y,float m)
	{
		this.px=x;
		
		this.tex=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(t, 50, 50)[0]),PlayMode.LOOP);
		this.x=x;
		this.y=y;
		this.m=m;
		
	}
	public void render(float delta,SpriteBatch batch)
	{
		if(mov)
		{
			//System.out.println(" mov "+tx+" x "+x+" mx "+mx);
			if(x>=tx)
			{
				//System.out.println(" mov x-- "+mx);
				//System.out.println(" mov tx "+tx+" ty "+ty+" x "+x+" y "+y);
				x-=mx*delta;
				
				y+=((ty<y)?-my:my)*delta;
				if(x<tx)
				{
					mov=false;
				}
			}
			else if(x<=tx)
			{
			x+=mx*delta;	
			y+=((ty<y)?-my:my)*delta;
			if(x>tx)
			{
				mov=false;
			}
			}
			else
			{
				//System.out.println(" mov tx "+tx+" ty "+ty+" x "+x+" y "+y);
				mov=false;
			}
		}
		else
		{
			tx=px+r.nextInt(300);
			ty=r.nextInt(500);
			
			if(ty-y!=0)
			{
				mx=(tx-x)/(ty-y)*100;
				my=1*100;
			}
			else
			{
				mx=1;
				my=0;
			}
			//System.out.println("mx "+mx);
			mx=Math.abs(mx);
			mov=true;
			
		}
		stateTime+=delta;
		batch.draw((TextureRegion) tex.getKeyFrame(stateTime), x, y, 50*m, 50*m);
	}
	public void updte(float xq)
	{
		System.out.println(xq);
		x+=xq;
		tx+=xq;
		px+=xq;
	}

}
