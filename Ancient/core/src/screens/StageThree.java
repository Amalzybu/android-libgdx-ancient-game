package screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.dravianart.game.Ancient;
import com.dravianart.game.entities.FBDesiderObject;
import com.dravianart.game.entities.FlowerData;
import com.dravianart.game.entities.OrthoEnemyOne;
import com.dravianart.game.entities.OrthoTree;

import tools.FrontBehindDesider;
import tools.OrthogonalTiledMapRendererWithSprites;
import tools.State;
import tools.TiledAnimationCreator;
import tools.TmxLoad;

public class StageThree implements Screen{
	TiledMap tiledMap;
	Texture block;
	Texture bt;
	Texture floor;
	Texture life;
	Texture pause;
	Texture rsmbt;
	Texture mainbt;
	Texture arsmbt;
	Texture amainbt;
	Texture tcret;
	 TextureRegion textureRegion;
	 TiledMapTileSet tileset;
	 OrthogonalTiledMapRenderer renderer;
	 ArrayList<TiledMapTileLayer.Cell> waterCellsInScene;
	 Map<String,TiledMapTile> waterTiles;
	Sprite sb;
	float stateTimep=0;
	Ancient game;
	boolean ankey=true;
	ShapeRenderer sr;
	Texture plyr;
	private State state = State.RUN;
	OrthoTree player;
	Vector<OrthoEnemyOne> enemy_array;
	private Object enemyOneText;
	private Object trainfire;	
	private Texture windflower;
	FrontBehindDesider Fbdes;
public StageThree(final Ancient game)
{
	
	this.game=game;
	
	pause=game.manager.get("realpas.png");
	 rsmbt=game.manager.get("resume.png");
	 mainbt=game.manager.get("mainmenu.png");
	 arsmbt=game.manager.get("activeresume.png");
	 amainbt=game.manager.get("activemainmenu.png");
	tiledMap=game.manager.get("mycrappymap.tmx");
	plyr=game.manager.get("levelThreeText/rotation.png");
	tcret=game.manager.get("levelThreeText/crect.png");
	enemyOneText=game.manager.get("levelThreeText/orthogonalvillain.png");
	trainfire=game.manager.get("levelThreeText/trainfire.png");
	windflower=game.manager.get("levelThreeText/windflower.png");

	enemy_array=new Vector<OrthoEnemyOne>();	
	enemy_array.add(new OrthoEnemyOne((Texture)enemyOneText,(Texture)trainfire, 600, 600, (TiledMapTileLayer) tiledMap.getLayers().get(1), tcret));
	renderer=new OrthogonalTiledMapRenderer(tiledMap);
	player=new OrthoTree(plyr, 700, 700, (TiledMapTileLayer) tiledMap.getLayers().get(1),tcret);
	sr=new ShapeRenderer();
	InputMultiplexer inputMultiplexer = new InputMultiplexer();
	inputMultiplexer.addProcessor(player);
	inputMultiplexer.addProcessor(new GestureDetector( player));
	Gdx.input.setInputProcessor(inputMultiplexer);
	
	
	// replace static with animated tile
	TiledAnimationCreator animator=new TiledAnimationCreator(tiledMap,new String[]{"flow","sflow","flower1"}, "forest_tiles");
	animator.create();
	FlowerData fdata=new FlowerData(windflower,(TiledMapTileLayer) tiledMap.getLayers().get("bg"));
	FBDesiderObject[] fdtemp=fdata.arr;
	System.out.println("size of the array is  "+fdtemp.length+" fdate "+fdata.arr.length);
	
	
	
	
	
	Fbdes=new FrontBehindDesider(player, fdtemp);

}
	@Override
	public void show() {
		System.out.println("..................................................");
		// TODO Auto-generated method stub
	
//		Cell cell=layer.getCell(3, 0);
//		cell.setTile(animatedTile);
		
	}

	@Override
	public void render(float delta) {
		switch (state)
	    {
	    case RUN:
		// TODO Auto-generated method stub
	    	game.camera.getCam().position.set(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2, 0);
	    	game.camera.getCam().update();
			Gdx.gl.glClearColor(1, 0, 0, 1);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        renderer.setView(game.camera.getCam());
	        renderer.getBatch().begin();
//	        renderer.getBatch().setColor(0.1f, 0.1f, 0.1f,1f);
//	        renderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        renderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("bg"));
	        renderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("stones"));
	       
//	        player.draw(renderer.getBatch(),Gdx.graphics.getDeltaTime());
	        for(OrthoEnemyOne enmy : enemy_array) {
	        	enmy.draw(renderer.getBatch(),Gdx.graphics.getDeltaTime());
	        }
//	        renderer.getBatch().setColor(1f, 1f, 1f,1f);
//	        renderer.getBatch().setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
	      Fbdes.render(renderer.getBatch());
	        AnimatedTiledMapTile.updateAnimationBaseTime();
			renderer.getBatch().end();
	        if(Gdx.input.isKeyJustPressed(Keys.ENTER)||Gdx.input.isKeyPressed(Input.Keys.BACK))
			{
				state=State.PAUSE;
				System.out.println("pause");
				//pause();
			}

			
	        break;
	    case PAUSE:
			//do stuff here
	    	ankey=false;
			    	stateTimep+=delta;
			    	//Gdx.gl.glClearColor(0f,0f, 0f, 1);
					//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
			    	float camex=game.camera.getCam().position.x-240,camey=game.camera.getCam().position.y-360;
					game.batch.enableBlending();
					game.batch.begin();
					
					game.batch.draw(pause, game.camera.getCam().position.x-240, game.camera.getCam().position.y-360, 480, 720);
					if((game.camera.getInputGameWorld().x>camex+150&&game.camera.getInputGameWorld().x<camex+330)&&(game.camera.getInputGameWorld().y<camey+220&&game.camera.getInputGameWorld().y>camey+175))
					{
						game.batch.draw(arsmbt, camex+150, camey+500, 60*3, 15*3);
						 if(Gdx.input.isTouched())
						 {
							 ankey=true;
						state=State.RUN;
						 }
					}
					else
					{
						game.batch.draw(rsmbt, camex+150, camey+500, 60*3, 15*3);
					}
					 if((game.camera.getInputGameWorld().x>camex+120&&game.camera.getInputGameWorld().x<camex+360)&&(game.camera.getInputGameWorld().y<camey+320&&game.camera.getInputGameWorld().y>camey+275))
					{
						 game.batch.draw(amainbt, camex+120, camey+400, 80*3, 15*3);
						 if(Gdx.input.isTouched())
						 {
							 this.dispose();
							 game.m.stateTime=0;
							 game.camera.getCam().position.set(game.camera.getCam().viewportWidth/2,game.camera.getCam().viewportHeight/2,0);
							 game.camera.getCam().update();
							 game.setScreen(game.m);
							 
							 
							
						 }
					}
					 else
					 {
						 game.batch.draw(mainbt, camex+120, camey+400, 80*3, 15*3);
					 }
					
					 game.batch.end();
					
			    	if(Gdx.input.isKeyJustPressed(Keys.ENTER)||Gdx.input.isKeyJustPressed(Input.Keys.BACK))
					{
						state=State.RUN;
						System.out.println("Run");
						//pause();
					}

			        break;
			    case RESUME:

			        break;

			    default:
			        break;
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

}
