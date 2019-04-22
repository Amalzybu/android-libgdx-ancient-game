package com.dravianart.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class Pond {
	public Crect r;
	int nwcnt=0,flwcnt=0,fiscnt=0,lcnt=0;
	public static final float width=240;
	public static final float height=50;
	public float stateTime=0,stateTime1=0,stTime=0,stme=0;
	public float x,y,x1,y1;
	float oldAlpha=0,oldg=0;

	boolean mark=false;
	public Texture pln,pmd,wat,fis,wtm;
	Animation[] flr,waterfl,fish;
	public Animation[] stamp;
	Color color ;
	public Pond(float x,float y,AssetManager m)
	{
		stamp=new Animation[2];
		fish=new Animation[4];
		waterfl=new Animation[2];
		flr=new Animation[2];
		wat=(Texture)m.get("leveloneText/water.png");
		pmd=(Texture)m.get("leveloneText/pondmod.png");
		fis=(Texture)m.get("leveloneText/fish.png");
		pln=(Texture)m.get("leveloneText/pond.png");
		wtm=(Texture)m.get("leveloneText/watermod.png");
		r=new Crect(x,y,240*4,10*4);
		flr[0]=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(wat, 240, 50)[0]));
		flr[1]=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(wat, 240, 50)[1]));
		fish[0]=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(fis, 240, 50)[0]));
		fish[1]=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(fis, 240, 50)[1]));
		fish[2]=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(fis, 240, 50)[2]));
		fish[3]=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(fis, 240, 50)[3]));
		stamp[0]=new Animation(0.3f,new Array<TextureRegion>(TextureRegion.split(pmd, 270, 100)[0]));
		stamp[1]=new Animation(0.3f,new Array<TextureRegion>(TextureRegion.split(pmd, 270, 100)[1]));
		waterfl[0]=new Animation(0.3f,new Array<TextureRegion>(TextureRegion.split(wtm, 260, 100)[0]));
		waterfl[1]=new Animation(0.3f,new Array<TextureRegion>(TextureRegion.split(wtm, 260, 100)[1]));
		this.x=x;
		this.y=y;
		x1=x+width*4;
		y1=y+height*4;
	}
	public void update()
	{
		r.move(x, y);
	}
	public void render(SpriteBatch batch,boolean b)
	{
		stateTime+=Gdx.graphics.getDeltaTime();
		if(b)
		{
			mark=true;
		}
		if(!mark)
		{
		batch.draw(pln, x, y-200,270*4,100*4);
		
		}
		else if(mark)
		{
			stateTime1+=Gdx.graphics.getDeltaTime();
			if(lcnt==0)
			{
				batch.draw((TextureRegion) stamp[0].getKeyFrame(stateTime1), x, y-200, 270*4, 100*4);
				if(stamp[0].isAnimationFinished(stateTime1))
				{
					lcnt=1;
					stateTime1=0;
				}
			}
			else if(lcnt==1)
			{
				batch.draw((TextureRegion) stamp[1].getKeyFrame(stateTime1), x, y-200, 270*4, 100*4);
				
			}
			
		}
	
		if(fiscnt==0)
		{
		batch.draw((TextureRegion) fish[0].getKeyFrame(stateTime), x+50, y, 240*1.9f, 50*1.9f);
		if(fish[0].isAnimationFinished(stateTime))
		{
			fiscnt=1;
			stateTime=0;
		}
		}
		else if(fiscnt==1)
		{
		batch.draw((TextureRegion) fish[1].getKeyFrame(stateTime), x+50, y, 240*1.9f, 50*1.9f);
		if(fish[1].isAnimationFinished(stateTime))
		{
			fiscnt=2;
			stateTime=0;
		}
		}
		else if(fiscnt==2)
		{
		batch.draw((TextureRegion) fish[2].getKeyFrame(stateTime), x+50, y, 240*1.9f, 50*1.9f);
		if(fish[2].isAnimationFinished(stateTime))
		{
			fiscnt=3;
			stateTime=0;
		}
		}
		else if(fiscnt==3)
		{
		batch.draw((TextureRegion) fish[3].getKeyFrame(stateTime), x+50, y, 240*1.9f, 50*1.9f);
		if(fish[3].isAnimationFinished(stateTime))
		{
			fiscnt=0;
			stateTime=0;
		}
		}
	}
	public void water(SpriteBatch batch,Tree t)
	{
		batch.enableBlending();
		
		color =batch.getColor();//get current Color, you can't modify directly
		oldAlpha = color.a; //save its alpha
		oldg=color.g;
		//From here you can modify alpha however you want
		
		color.a = oldAlpha*0.8f; //ex. scale = 0.5 will make alpha halved
		color.g =oldg*0.5f;
		batch.setColor(color);
		if(lcnt==1)
		{

			stTime+=Gdx.graphics.getDeltaTime();
			if(!waterfl[1].isAnimationFinished(stTime))
			{
			t.x+=300*Gdx.graphics.getDeltaTime();
			}
			if(flwcnt==0)
			{
			batch.draw((TextureRegion) waterfl[0].getKeyFrame(stTime), x, y-200, 260*4, 100*4);
			if(waterfl[0].isAnimationFinished(stTime))
			{
				flwcnt=1;
				stTime=0;
			}
			}
			else if(flwcnt==1)
			{
			batch.draw((TextureRegion) waterfl[1].getKeyFrame(stTime), x, y-200, 260*4, 100*4);
			
			
			}
		
		}
		else
		{
			stme+=Gdx.graphics.getDeltaTime();
			if(nwcnt==0)
			{
			batch.draw((TextureRegion) flr[0].getKeyFrame(stme), x, y, 240*4, 50*4);
			if(flr[0].isAnimationFinished(stme))
			{
				nwcnt=1;
				stme=0;
			}
			}
			else if(nwcnt==1)
			{
				batch.draw((TextureRegion) flr[1].getKeyFrame(stme), x, y, 240*4, 50*4);
				if(flr[1].isAnimationFinished(stme))
				{
					nwcnt=0;
					stme=0;
				}
			}
		}
		color.a = oldAlpha;
		color.g =oldg;
		batch.setColor(color);
	}
public void dispose()
{
	pln.dispose();
	pmd.dispose();
	wat.dispose();
	fis.dispose();
	wtm.dispose();
}

}
