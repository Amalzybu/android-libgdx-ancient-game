package com.dravianart.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dravianart.game.Ancient;

import tools.Crect;

public class River {
	float x,y;
	Floor k;
	int ci=0;
	Texture rf,rs;
	float stateTime=0;
	Ancient game;
	boolean tc=false;
	public ArrayList<Floor> f;
	public River(float x,float y,Ancient game) {
		this.x=x;
		this.y=y;
		this.game=game;
		f=new ArrayList<Floor>();
		rf=(Texture)game.manager.get("AbyssText/riverfloor.png");
		rs=(Texture)game.manager.get("AbyssText/riverside.png");
		f.add(new Floor(x,y,(Texture)game.manager.get("AbyssText/river.png")));
		f.add(new Floor(x+240*4,y,(Texture)game.manager.get("AbyssText/extendedriver.png")));
		f.add(new Floor(x+470*4,y,(Texture)game.manager.get("AbyssText/extendedriver.png")));
		f.add(new Floor(x+700*4,y,(Texture)game.manager.get("AbyssText/river.png")));
		for(Floor a:f)
		{
			a.r.height=10*4;
			a.update();
			a.resetSpd(0.1f);
		}
		
		
	}
	
	public void render()
	{
		stateTime+=Gdx.graphics.getDeltaTime();
		ci=0;
		for(Floor a:f)
		{
			ci++;
			if(f.size()!=ci)
			{
			game.batch.draw(rf, a.x, 0,a.width*4,a.height*3.2f);
			game.batch.draw((TextureRegion) a.flr.getKeyFrame(stateTime), a.x, a.y,a.width*4,a.height*4);
			}
			else
			{
				game.batch.draw(rf, a.x, 0,a.width*4,a.height*3.2f);
				game.batch.draw(rf, a.x+240, 0,a.width*4,a.height*3.2f);
				game.batch.draw(rf, a.x+480, 0,a.width*4,a.height*3.2f);
				game.batch.draw(rf, a.x+720, 0,a.width*4,a.height*3.2f);
				game.batch.draw((TextureRegion) a.flr.getKeyFrame(stateTime), a.x+a.width, a.y,-a.width*4,a.height*4);
			}
		}
		//game.batch.draw(rf, 940, 0,240*4,50*3.2f);
		
	}
	public void update() {
		for(Floor a:f)
		{
			a.update();
		}
	}
	
	public boolean inRiver(Tree t)
	{
		tc=false;
		for(Floor g:f)
		{
			if(t.r.isCollided(g.r)) 
			{
				tc=true;
			}
			
		}
		return tc;
	}
	
	

}
