package tools;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.dravianart.game.Ancient;

public class TmxLoad {
	
	public static AssetDescriptor<TiledMap> uiSkin;
	public Ancient game;
	String m;
	public TmxLoad(Ancient game,String m)
	{
		this.m=m;
		this.game=game;
	        uiSkin=new AssetDescriptor<TiledMap>(m, TiledMap.class);
	        
	}

	public void load()
	{
		game.manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	  //  manager.load(someTexture);
	  //  manager.load(uiAtlas);
	    game.manager.load(uiSkin);
	    System.out.println(m);
	    System.out.println("loaded");
	}


}
