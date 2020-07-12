package com.dravianart.game.entities;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Crect;
import tools.EnemyFunc;
import tools.Gravity;

public class EnemyTwo extends Gravity{
	
	float ll;
	float rl,mtx;
	Tree tr;
	int i=0,j=0;
	int direction=0;
	boolean mv=true,pushed=false,lm=true,st=true,jfgt=false,tup=false;
	SpriteBatch batch;
	Animation anms[];
	public float x,px;
	public float stateTime=0,stateTime1=0,stateTime2=0,statetTime3=0,stateTime4=0,stmark=0,lckx=0;
	public EnemyFunc ef;
	public EnemyTwo(float x, float y, Texture t1,Texture t2, SpriteBatch batch, Tree t, boolean lm,float mtx) {
		this.mtx=mtx;
		this.lm=lm;
		this.tr=t;
		pos=1;
		px=x;
		this.batch=batch;
		r=new Crect(x+20,y+20,20,80);
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(t1, 50, 100);
		TextureRegion[][] rollSpriteSheet1 = TextureRegion.split(t2, 140, 100);
		this.x=x;
		this.y=y;
		//ll=px-200;
		//rl=px+200;
		anms=new Animation[5];
		anms[0]=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		anms[1]=new Animation(0.2f, new Array<TextureRegion>(rollSpriteSheet[1]),PlayMode.LOOP);
		anms[2]=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[2]));
		anms[3]=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet1[0]));
		anms[4]=new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet1[1]));
		ef=new EnemyFunc(5,r,t);
		
	}
	
	
	
	/*public EnemyTwo(float x,float y,Texture t,SpriteBatch batch)
	{
		
		
	}*/
	
	public void render(float delta,Vector<Crect> blocker)
	{
		stateTime+=delta;
		noPainMang();
		if(ef.life>0)
		{
		if(mv&&!jfgt)
		{
			pos=1;
		if(tr.x>x-100&&tr.x<x) {
			batch.draw((TextureRegion) anms[1].getKeyFrame(stateTime), x, y+20, 50*mtx, 100*mtx);
			jfgt=true;
			tup=true;
			lckx=x-tr.x;
		}
		else 
		{
		batch.draw((TextureRegion) anms[0].getKeyFrame(stateTime), x, y+20, 50*mtx, 100*mtx);
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
		}
		else if(!jfgt)
		{
			pos=0;
			 if(tr.x<x+100&&tr.x>x){
				batch.draw((TextureRegion) anms[1].getKeyFrame(stateTime), x+75, y+20, -50*mtx, 100*mtx);
				jfgt=true;
				tup=true;
				lckx=x-tr.x;
			}
			else
			{
			batch.draw((TextureRegion) anms[0].getKeyFrame(stateTime), x+75, y+20, -50*mtx, 100*mtx);
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
		}
		else if(jfgt)
		{
			System.out.println("jump x "+lckx+" block "+!this.block(blocker, this.pos));
			if(lckx<0&&this.block(blocker, this.pos))
				{
					x+=100*delta;
				}
			else if(lckx>=0&&this.block(blocker, this.pos))
			{
				x-=100*delta;
			}
			//x+=(lckx<0&&!this.block(blocker, this.pos))? 100*delta:100*delta;
			
			if(tup)
			{
			stateTime2+=delta;
			if(pos==0)
			{
				batch.draw((TextureRegion) anms[2].getKeyFrame(stateTime2), x, y+20, 50*mtx, 100*mtx);
				
			}
			else if(pos==1)
			{
				batch.draw((TextureRegion) anms[2].getKeyFrame(stateTime), x+75, y+20, -50*mtx, 100*mtx);
				
			}
			
			if(y<up) {
				y+=1000*delta;
				update();
				if(y>=up)
				{
				tup=false;
				stateTime2=0;
				}
				
			}
			
			}
			else
			{
				stateTime2+=delta;
				if(pos==0)
				{
					batch.draw((TextureRegion) anms[1].getKeyFrame(stateTime2), x, y+20, 50*mtx, 100*mtx);
					
				}
				else if(pos==1)
				{
					batch.draw((TextureRegion) anms[1].getKeyFrame(stateTime), x+75, y+20, -50*mtx, 100*mtx);
					
				}
				if(y<=limit)
				{
					jfgt=false;
				}
				
			}
			update();
		}
		ef.checkDmg();
		ef.checkHeroDmg();
		}
		else if(ef.life<=0)
		{
			
			if(!anms[3].isAnimationFinished(statetTime3))
			{
				System.out.println("death ");
				statetTime3+=delta;
			batch.draw((TextureRegion) anms[3].getKeyFrame(stateTime1), x+45*mtx, y+20, 140*mtx, 100*mtx);
			
			}
			else
			{
				stateTime4+=delta;
				batch.draw((TextureRegion) anms[4].getKeyFrame(stateTime4), x+45*mtx, y+20, 140*mtx, 100*mtx);
				if(anms[4].isAnimationFinished(stateTime4))
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
				x+=200*delta;
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
					
				x-=200*delta;
				update();
				if(!this.block(blocker, this.pos))
				{
					i=50;
					//System.out.println("hello1");
					x+=400*delta;
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
			
				if(i<=20&&y<=up)
				{
					
					y+=800*delta;
					
				}
				
			
			
		}
	}
	public void update()
	{
		ll=px-200;
		rl=px+200;
		
		r.move(x+20, y+20);
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
