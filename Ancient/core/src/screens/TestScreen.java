package screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.dravianart.game.Ancient;
import com.dravianart.game.entities.EnemyOne;
import com.dravianart.game.entities.Npc1;
import com.dravianart.game.entities.Pond;
import com.dravianart.game.entities.Tree;

import tools.Chat;
import tools.State;

public class TestScreen implements Screen{
	private State state = State.RUN;
	boolean b=false,s=false,f=false;
	Ancient g;
	EnemyOne e;
	Pond p;
	Tree t;
	Texture pause=new Texture("realpas.png");
	Texture rsmbt=new Texture("resume.png");
	Texture mainbt=new Texture("mainmenu.png");
	Texture arsmbt=new Texture("activeresume.png");
	Texture amainbt=new Texture("activemainmenu.png");
	//Animation pau;
	float stateTime=0,stateTimep=0;
	
	
	public TestScreen(Ancient g)
	{
		//new Animation(0.2f,, 240, 50)[0]));
		//pau=new Animation(0.5f, TextureRegion.split(pause, 480, 720)[0]);
		//pau.setPlayMode(PlayMode.LOOP);
		t=new Tree(new Texture("action.png"));
		Gdx.input.setCatchBackKey(true);
		//p=new Pond(-600,0);
		//e=new EnemyOne(240,60,new Texture("enemy1.png"),g.batch);
		//tm=new Texture("Sprintflas.png");
	
		this.g=g;
		
		

	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		 switch (state)
		    {
		    case RUN:
		//do suff here
		    	stateTime+=delta;
				// TODO Auto-generated method stub
				
				Gdx.gl.glClearColor(1f, 1f, 1f, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
				g.batch.enableBlending();
				g.batch.begin();
				g.batch.draw((TextureRegion) t.rolls[1].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
				p.render(g.batch,false);
				p.water(g.batch, new Tree(null));
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)||Gdx.input.isKeyPressed(Input.Keys.BACK))
				{
					state=State.PAUSE;
					System.out.println("pause");
					//pause();
				}
				g.batch.end();
		        break;
		    case PAUSE:
		//do stuff here
		    	stateTimep+=delta;
		    	//Gdx.gl.glClearColor(0f,0f, 0f, 1);
				//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
				g.batch.enableBlending();
				g.batch.begin();
				
				g.batch.draw(pause, 0, 0, 480, 720);
				if((g.camera.getInputGameWorld().x>150&&g.camera.getInputGameWorld().x<330)&&(g.camera.getInputGameWorld().y<220&&g.camera.getInputGameWorld().y>175))
				{
					g.batch.draw(arsmbt, 150, 500, 60*3, 15*3);
					 if(Gdx.input.isTouched())
					 {
					state=State.RUN;
					 }
				}
				else
				{
					g.batch.draw(rsmbt, 150, 500, 60*3, 15*3);
				}
				 if((g.camera.getInputGameWorld().x>120&&g.camera.getInputGameWorld().x<360)&&(g.camera.getInputGameWorld().y<320&&g.camera.getInputGameWorld().y>275))
				{
					g.batch.draw(amainbt, 120, 400, 80*3, 15*3);
					 if(Gdx.input.isTouched())
					 {
					g.setScreen(g.m);
					 }
				}
				 else
				 {
					 g.batch.draw(mainbt, 120, 400, 80*3, 15*3);
				 }
				
				g.batch.end();
				
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
		g.camera.update(width, height);
		
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
