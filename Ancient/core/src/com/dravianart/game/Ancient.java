package com.dravianart.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import screens.AbyssPath;
import screens.LoadingScreen;
import screens.MainMenu;
import screens.StageOne;
import screens.TestScreen;
import tools.AssetFinder;
import tools.GameCamera;

public class Ancient extends Game {

	
	public static final int WIDTH=480;
	public static final int HEIGHT=720;
	public static boolean IS_Mobile=false;
	public SpriteBatch batch;
	public AssetManager manager;
	public AssetFinder finder;
	public LoadingScreen lod;
	public MainMenu m;
	String s;
	public Preferences prefs ;
	
	//Texture img;
	public GameCamera camera;
	@Override
	public void create () {
		prefs = Gdx.app.getPreferences("pref");
		
		//temp
//		prefs.putString("lvl", "StageLight");
//		prefs.putString("tex", "levellightText");
//		prefs.putString("msc", "levelThreeMsc");
//		prefs.flush();
//		prefs.putString("lvl", "StageThree");
//		prefs.putString("tex", "levelThreeText");
//		prefs.putString("msc", "levelThreeMsc");
//		prefs.putString("tmx", "mycrappymap.tmx");
//		prefs.flush();
		prefs.putString("lvl", "StageOne");
		prefs.putString("tex", "leveloneText");
		prefs.putString("msc", "leveloneMsc");
		prefs.putString("tmx", "");
//		prefs.flush();
//		prefs.putString("lvl", "AbyssPath");
//		prefs.putString("tex", "AbyssText");
//		prefs.putString("msc", "AbyssMsc");
//		prefs.flush();
		//temp
		System.out.println("pref"+(s=prefs.getString("tex")));
		if(s.equals(""))
		{
			
			prefs.putString("lvl", "StageOne");
			prefs.putString("tex", "leveloneText");
			prefs.putString("msc", "leveloneMsc");
			prefs.putString("tmx", "");
			prefs.flush();
			System.out.println("pref "+prefs.getString("msc"));
		}
		
		manager=new AssetManager();
		
		camera=new GameCamera(WIDTH,HEIGHT);
		batch = new SpriteBatch();
		
		manager.load("loading.png", Texture.class);
		manager.load("action.png", Texture.class);
		manager.load("Sprintfog.png", Texture.class);
		manager.load("action.png", Texture.class);
		manager.load("play_button_active.png", Texture.class);
		manager.load("play_button_inactive.png", Texture.class);
		manager.load("exit_button_active.png", Texture.class);
		manager.load("exit_button_inactive.png", Texture.class);
		manager.load("name.png", Texture.class);
		manager.load("bg.png", Texture.class);
		manager.load("cloud.png", Texture.class);
		manager.load("floor.png", Texture.class);
		manager.load("bg.png", Texture.class);
		manager.load("realpas.png", Texture.class);
		manager.load("lifes.png", Texture.class);
		manager.load("resume.png", Texture.class);
		manager.load("mainmenu.png", Texture.class);
		manager.load("activeresume.png", Texture.class);
		manager.load("activemainmenu.png", Texture.class);
		manager.finishLoading();
		m=new MainMenu(this);
		
		this.lod=new LoadingScreen(this);
		this.setScreen(lod);
		//this.setScreen(new MainMenu(this));
		//this.setScreen(new StageOne(this));
		//this.setScreen(new TestScreen(this));
		//img = new Texture("badlogic.jpg");
		if(Gdx.app.getType()==ApplicationType.Android||Gdx.app.getType()==ApplicationType.iOS)IS_Mobile=true;
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined());
		super.render();
		
		/*batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
	@Override
	public void resize(int width, int height) {
		camera.update(width, height);
		super.resize(width, height);
	}
}
