package tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

public class Bglv1 {
	public static final float width=240;
	public static final float height=50;
	
	public float x,y;
	public Animation a;
	public Bglv1(float x,float y,Texture t)
	{
		a=new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split(t, 100, 50)[0]),PlayMode.LOOP);
		this.x=x;
		this.y=y;
		
	}

}
