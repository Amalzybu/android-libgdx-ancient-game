package tools;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.dravianart.game.Ancient;

public class Chat {
	int j=0,k=0;
	float stateTime=0,p=0,py=0;
	boolean b=false;
		BitmapFont scoreFont;
		float i;
	Animation anm=null;
	Texture t1=null;
	ArrayList<GlyphLayout> scoreLayout;
	SpriteBatch g=null;
	public float x,y,m;
	public Chat(String[] str,SpriteBatch batch,float x,float y,float m,float ts)
	{
		this.x=x;
		this.y=y;
		this.m=m;
		this.g=batch;
		t1=new Texture("chatbox.png");
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
		scoreLayout=new ArrayList<GlyphLayout>();
		scoreFont.getData().setScale(ts);
		for(String s:str)
		{
			
		scoreLayout.add( new GlyphLayout(scoreFont, s, Color.BLACK, 0, Align.left, false));
		
		}
		
	}
	public boolean render(float delta)
	{
		stateTime+=delta;
		if(stateTime>=1f)
		{
			stateTime=0;
			if(j<=scoreLayout.size())
			{
			j++;
			}
			
		}
		if(Gdx.input.isTouched()&&stateTime>=0.2f)
		{
			stateTime=0;
			if(j<=scoreLayout.size())
			{
			j++;
			}
		}
		g.draw(t1, x, y,50*m,28*m);
		for(GlyphLayout gm:scoreLayout)
		{
			
			if(k<=j)
			{
				b=false;
			scoreFont.draw(g, gm, x+50, y+200+i);
			i-=gm.height+5;
			k++;
			}
			else
			{
				
				break;
			}
			
			}
		if(k==scoreLayout.size())
		{
			b=true;
		}
			i=0;
			k=0;
			return b;
		}
	public void dispose()
	{
		t1.dispose();
		g.dispose();
		scoreFont.dispose();
	}
		


}
