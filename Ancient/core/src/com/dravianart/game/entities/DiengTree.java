package com.dravianart.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import tools.Chat;
import tools.Crect;

public class DiengTree {
	public float x,y;
	
	public boolean b=true,ct=false,dead=false,chf=false;
	public Crect r;
	
	public Chat ch=null;
	public Animation[] rolls;
	float stateTime=0,stime1=0,stime2=0,stime3=0;
	boolean flg=true;
	SpriteBatch batch=null;
	Texture npc,det;
	MovBlock mvb;
	Tree t;
	String[] str={"haaa",".................","......................",".....................","..................." };
	public DiengTree(float x,float y,SpriteBatch batch,Texture np,Texture det,Tree t,MovBlock mvb)
	{
		this.det=det;
		this.mvb=mvb;
		this.t=t;
		npc=np;
		this.batch=batch;
		ch=new Chat(str,batch,x+250,500,8,0.4f);
		this.x=x;
		this.y=y;
		r=new Crect(x+50,y,150,600);
		rolls=new Animation[4];
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(npc, 200, 200);
		rolls[0] = new Animation(0.2f,  new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		rolls[1] = new Animation(0.3f, new Array<TextureRegion>(rollSpriteSheet[1]));
		rolls[2] = new Animation(0.3f, new Array<TextureRegion>(rollSpriteSheet[2]));
		rolls[3] = new Animation(0.25f, new Array<TextureRegion>(TextureRegion.split(det, 300, 220)[0]));

	}
	public boolean render (float delta)
	{
		if(!dead)
		{
		if(x<=200)
		{ 
			
			if(!rolls[1].isAnimationFinished(stime1))
			{
				;
			stime1+=delta;
			batch.draw((TextureRegion) rolls[1].getKeyFrame(stime1), x, y,200*3, 200*3);b=false;
			}
			else
			{
				mvb.pause=1;
				
				mvb.speed=250;
				stime2+=delta;
				batch.draw((TextureRegion) rolls[2].getKeyFrame(stime2), x, y,200*3, 200*3);
				b=false;
			}
			if(rolls[2].isAnimationFinished(stime2))
			{
				mvb.pause=0;b=true;
				
			}
			
		}

		else
		{
			
		stateTime+=delta;
		batch.draw((TextureRegion) rolls[0].getKeyFrame(stateTime), x, y,200*3, 200*3);
		b=true;
		}
		
		}
		else
		{
			stime3+=delta;
			batch.draw((TextureRegion) rolls[3].getKeyFrame(stime3), x, y,300*3, 220*3);
			if(!chf)
			{
			chf=ch.render(delta);
			}
			
		}
		
		if(r.isCollided(t.r)&&t.sprint==1)
		{
			dead=true;
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
