package com.dravianart.game.entities;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import com.badlogic.gdx.utils.Array;

import tools.Crect;
import tools.EnemyFunc;
import tools.Gravity;

public class AbyssBoss extends Gravity{
	public EnemyFunc ef;
	boolean lm,mv=false,rturn=false,rmv=true,lturn=false,st=true;
	int pos=1;
	public boolean activate=false;
	public float x;
	float mtx;
	public float stateTime=0,stateTime1=0,stmark;
	Texture t1,t2;
	public Animation anm[]=new Animation[3];
	TextureRegion[][] rollSpriteSheet;
	SpriteBatch batch;
	Tree tr;
	public AbyssBoss(float x,float y,Texture t1,Texture t2,SpriteBatch batch,Tree t,float mtx)
	{
		this.mtx=mtx;
		this.lm=lm;
		this.tr=t;
		pos=1;
		this.r=new Crect(x,y+20,210f*mtx,(100f*mtx)-50);
		this.batch=batch;
		 rollSpriteSheet = TextureRegion.split(t1, 210, 100);
		this.x=x;
		this.y=y;
		this.t1=t1;
		this.t2=t2;
		//ll=px-200;
		//rl=px+200;
		anm[0]=new Animation(0.02f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		anm[1]=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[1]));
		anm[2]=new Animation(0.35f, new Array<TextureRegion>(rollSpriteSheet[2]));
		ef=new EnemyFunc(15,r,t);
	}
	public void render(float delta,Vector<Crect> blocker)
	{
		
		noPainMang();
		if(activate)
		{
			stateTime+=delta;
		if(!anm[2].isAnimationFinished(stateTime))
		{
			batch.draw((TextureRegion)anm[2].getKeyFrame(stateTime), x,y,210*mtx,100*mtx);
		}
		else
		{
		if(rmv)
		{
			x-=500*delta;
			update();
			batch.draw((TextureRegion)anm[0].getKeyFrame(stateTime), x,y,210*mtx,100*mtx);
			//
		if(!this.block(blocker, pos))
		{
		rmv=false;
		rturn=true;
		//stateTime1=0;
		}
		else if(x-tr.x<-200)
		{
			rmv=false;
			rturn=true;
		}
		}
		if(rturn)
		{
			stateTime1+=delta;
			
				x+=100*delta;
				update();
			batch.draw((TextureRegion)anm[1].getKeyFrame(stateTime1), x,y,210*mtx,100*mtx);
			System.out.println(anm[1].isAnimationFinished(stateTime1));
			
			if(anm[1].isAnimationFinished(stateTime1))
			{
				System.out.println("finished");
				rturn=false;
				stateTime1=0;
				if(pos==1)
				{
					pos=0;
					
				}
				mv=true;
			}
		}
		if(mv)
		{
		System.out.println("turnees");
			x+=500*delta;
			update();
			batch.draw((TextureRegion)anm[0].getKeyFrame(stateTime), x+210*mtx,y,-210*mtx,100*mtx);
			//
		if(!this.block(blocker, pos))
		{
		mv=false;
		lturn=true;
		stateTime1=0;
		}else if(x-tr.x>200)
		{
			mv=false;
			lturn=true;
		}
		}
		if(lturn)
		{
			stateTime1+=delta;
			x-=100*delta;
			batch.draw((TextureRegion)anm[1].getKeyFrame(stateTime1), x+210*mtx,y,-210*mtx,100*mtx);
			if(anm[1].isAnimationFinished(stateTime1))
			{
				
				lturn=false;
				stateTime1=0;
				if(pos==0)
				{
					pos=1;
					rmv=true;
				}
				
			}
		}
		}
		
		ef.checkDmg();
		ef.checkHeroDmg();
		}
		
	}
	public void update()
	{
		r.move(x,y);
	}
	
	public void noPainMang()
	{
		if(tr.npain)
		{
			if(st)
			{
				stmark=stateTime;
				st=false;
			}
			if(stateTime>=stmark+2f)
			{
				st=true;
				stmark=0;
				tr.npain=false;
			}
		}
	}
	

}
