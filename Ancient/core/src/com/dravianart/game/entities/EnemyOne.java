package com.dravianart.game.entities;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import tools.Crect;
import tools.EnemyFunc;
import tools.Gravity;

public class EnemyOne extends Gravity{
	public float x,px;
	float ll;
	float rl,mtx;
	Tree tr;
	int direction=0;
	boolean mv=true,pushed=false,lm=true,st=true;
	Texture t1=null;
	Animation anm=null;
	Animation anm1=null;
	Animation anm2=null;
	
	public float stateTime=0,stateTime1=0,stateTime2=0,stmark=0;
	TextureRegion[][] rollSpriteSheet = null;
	SpriteBatch batch=null;
	public EnemyFunc ef;
	int i=0,j=0;
	
	public EnemyOne(float x,float y,Texture t1,SpriteBatch batch,Tree t,boolean lm,float mtx)
	{
		this.mtx=mtx;
		this.lm=lm;
		this.tr=t;
		pos=1;
		px=x;
		r=new Crect(x+10,y+30,20f,80f);
		this.batch=batch;
		rollSpriteSheet = TextureRegion.split(t1, 50, 100);
		this.x=x;
		this.y=y;
		this.t1=t1;
		//ll=px-200;
		//rl=px+200;
		anm=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		anm1=new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[1]));
		anm2=new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[2]));
		ef=new EnemyFunc(2,r,t);
	}
	public void render(float delta,Vector<Crect> blocker)
	{
		stateTime+=delta;
		noPainMang();
		if(ef.life>0)
		{
		if(mv)
		{
			pos=1;
		batch.draw((TextureRegion) anm.getKeyFrame(stateTime), x, y+20, 50*mtx, 100*mtx);
		if(!pushed)
		{
		x-=delta*100;
		update();
		}
		if((x<ll&&lm)||!this.block(blocker, this.pos))
		{
			mv=false;
		}
		}
		else
		{
			pos=0;
			batch.draw((TextureRegion) anm.getKeyFrame(stateTime), x+75, y+20, -50*mtx, 100*mtx);
			if(!pushed)
			{
			x+=delta*100;
			update();
			}
		    if((x>rl&&lm)||!this.block(blocker, this.pos))
			{
				mv=true;
			}
		}
		ef.checkDmg();
		ef.checkHeroDmg();
		}
		else if(ef.life<=0)
		{
			
			if(!anm1.isAnimationFinished(stateTime1))
			{
				stateTime1+=delta;
			batch.draw((TextureRegion) anm1.getKeyFrame(stateTime1), x, y+20, 50*mtx, 100*mtx);
			
			}
			else
			{
				stateTime2+=delta;
				batch.draw((TextureRegion) anm2.getKeyFrame(stateTime2), x, y+20, 50*mtx, 100*mtx);
				if(anm2.isAnimationFinished(stateTime2))
				{
					ef.alive=false;
				}
			}
			
		}
		if(tr.r.isCollided(r)&&tr.sprint==1)
		{
			pushed=true;
			direction=tr.pos;
			i=0;
			j=0;
		}
		if(pushed)
		{
			if(direction==0)
			{
				
				if(i<=50)
				{
				x+=200*Gdx.app.getGraphics().getDeltaTime();
				update();
				if(!this.block(blocker, this.pos))
				{
					i=50;
					//System.out.println("hello");
					x-=400*Gdx.app.getGraphics().getDeltaTime();
					update();
				}
				i++;
				}
				else
				{
					pushed=false;
				}
			}
			else if(direction==1)
			{
				
				if(i<=50)
				{
					
				x-=200*Gdx.app.getGraphics().getDeltaTime();
				update();
				if(!this.block(blocker, this.pos))
				{
					i=50;
					System.out.println("hello1");
					x+=400*Gdx.app.getGraphics().getDeltaTime();
					update();
				}
				
				i++;
				}
				else
				{
					pushed=false;
				}
			}
			else
			{
				//System.out.println("blocked");
			}
			
				if(i<=20)
				{
					y+=1000*Gdx.app.getGraphics().getDeltaTime();
					
				}
				
			
			
		}
	}
	public void update()
	{
		ll=px-200;
		rl=px+200;
		
		r.move(x+15, y+20);
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
			if(stateTime>=stmark+2)
			{
				st=true;
				stmark=0;
				tr.npain=false;
			}
		}
	}


}
