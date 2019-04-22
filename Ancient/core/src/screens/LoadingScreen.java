package screens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;
import com.dravianart.game.Ancient;

import tools.AssetFinder;

public class LoadingScreen implements Screen{
	Ancient game;
	int f=0;
	Texture ld=null;
	float stateTime=0;
	Animation[] anm;
	FileHandleResolver resolver;
	AssetFinder finder;
	Map<String,String> mp;
	Screen s;
	public LoadingScreen(Ancient game)
	{
		this.game=game;
		resolver = new InternalFileHandleResolver();
		//mp=new HashMap<String,String>();
		//mp.put(game.prefs.getString("msc"), "Music");
		//mp.put(game.prefs.getString("tex"), "Texture");
		//mp.put(game.prefs.getString("msc"), "Music");
		
		finder=new AssetFinder(game.manager,resolver);
		System.out.println(game.prefs.getString("tex"));
		finder.loadText(game.prefs.getString("tex"));
		finder.loadMsc(game.prefs.getString("msc"));
		anm=new Animation[2];
		
		
		ld=game.manager.get("loading.png",Texture.class);
		anm[0]=new Animation(0.05f,new Array<TextureRegion>(TextureRegion.split(ld, 120, 180)[0]));
		anm[1]=new Animation(0.05f,new Array<TextureRegion>(TextureRegion.split(ld, 120, 180)[1]));
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(!game.manager.update())
		{
			//anm[0].setFrameDuration(1-game.manager.getProgress());
			//anm[1].setFrameDuration(1-game.manager.getProgress());
		stateTime+=delta;
		// TODO Auto-generated method stub
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		
		game.batch.begin();
		if(f==0)
		{
		game.batch.draw((TextureRegion) anm[0].getKeyFrame(stateTime), 0, 0, 120*4, 180*4);
		if(anm[0].isAnimationFinished(stateTime))
		{
			stateTime=0;
			f=1;
		}
		}
		else if(f==1)
		{
			game.batch.draw((TextureRegion) anm[1].getKeyFrame(stateTime), 0, 0, 120*4, 180*4);
			if(anm[1].isAnimationFinished(stateTime))
			{
				stateTime=0;
				f=0;
			}
		}
		game.batch.end();
		}
		else
		{
			System.out.println("end"+game.prefs.getString("lvl"));
			if(game.prefs.getString("lvl").equals("StageOne"))
			{
			game.setScreen(new StageOne(game));
			}
			else if(game.prefs.getString("lvl").equals("AbyssPath"))
			{
			game.setScreen(new AbyssPath(game));
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void setScreen(Screen s)
	{
		this.s=s;
	}

}
