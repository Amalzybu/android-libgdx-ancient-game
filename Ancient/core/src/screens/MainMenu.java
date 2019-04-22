package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.dravianart.game.Ancient;

public class MainMenu implements Screen{
	
	Animation anim=null;
	Animation canim=null;
	Texture c1=null;
	Texture c2=null;
	Texture c3=null;
	Texture c4=null;
	Texture head=null;
	Texture play=null;
	Texture exit=null;
	Texture uplay=null;
	Texture uexit=null;
	
	float x1=240,x2=0,x3=-50,x4=450;
	public static final int hero_width=80;
	public static final int hero_height=20;
	public static final float hero_animation_speed=0.5f;
	Ancient game;
	public float stateTime;
	int roll=0;
	public MainMenu(Ancient game)
	{
		this.game=game;
		/*head=new Texture("name.png");
		c1=new Texture("cloud.png");
		c2=new Texture("cloud.png");
		c3=new Texture("cloud.png");
		c4=new Texture("cloud.png");
		uplay=new Texture("play_button_active.png");
		uexit=new Texture("exit_button_active.png");
		play=new Texture("play_button_inactive.png");
		exit=new Texture("exit_button_inactive.png");*/
		head=this.game.manager.get("name.png");
		c1=this.game.manager.get("cloud.png");
		c2=this.game.manager.get("cloud.png");
		c3=this.game.manager.get("cloud.png");
		c4=this.game.manager.get("cloud.png");
		uplay=this.game.manager.get("play_button_active.png");
		uexit=this.game.manager.get("exit_button_active.png");
		play=this.game.manager.get("play_button_inactive.png");
		exit=this.game.manager.get("exit_button_inactive.png");
		
		//anim = new Animation(0.2f, TextureRegion.split(new Texture("hero1.png"), 20, 20)[0]);
		anim = new Animation(0.2f, new Array<TextureRegion>(TextureRegion.split((Texture)this.game.manager.get("bg.png"), 20, 20)[0]), PlayMode.LOOP);
		canim = new Animation(0.5f, new Array<TextureRegion>(TextureRegion.split((Texture)this.game.manager.get("floor.png"), 120, 50)[0]), PlayMode.LOOP);
		
		
		
	
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//System.out.println("delta "+delta);
		stateTime += delta;
		x1-=delta*5;
		x2-=delta*5;
		x3-=delta*5;
		x4-=delta*5;
		if(x1+380<0)
		{
			x1=480;
		}
		if(x2+380<0)
		{
			x2=480;
		}
		if(x3+380<0)
		{
			x3=480;
		}
		if(x4+380<0)
		{
			x4=480;
		}
		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.draw(c1, x1, 150, 38*10, 30*10);
		game.batch.draw(c2, x2, 300, 38*10, 30*10);
		game.batch.draw(c3, x3, 500, 38*10, 30*10);
		game.batch.draw(c4, x4, 250, 38*10, 30*10);
		game.batch.draw(head, 0, 500, 120*5, 50*5);
		
		if((game.camera.getInputGameWorld().x>Ancient.WIDTH/2-180&&game.camera.getInputGameWorld().x<Ancient.WIDTH/2-180+360)&&(game.camera.getInputGameWorld().y<350&&game.camera.getInputGameWorld().y>225))
		{
			game.batch.draw(uplay, Ancient.WIDTH/2-180, 350, 360, 125);
			 if(Gdx.input.isTouched()&&stateTime>=0.5f)
			 {
				 game.lod=new LoadingScreen(game);
			game.setScreen(game.lod);
			 }
		}
		else
		{
			game.batch.draw(play, Ancient.WIDTH/2-180, 350, 360, 125);
		}
		if((game.camera.getInputGameWorld().x>Ancient.WIDTH/2-135&&game.camera.getInputGameWorld().x<Ancient.WIDTH/2-135+270)&&(game.camera.getInputGameWorld().y<455&&game.camera.getInputGameWorld().y>350))		
		{
			game.batch.draw(uexit, Ancient.WIDTH/2-135, 225, 270, 105);
			 if(Gdx.input.isTouched()&&stateTime>=0.5f)
			 {
				 Gdx.app.exit();
			 }
		}
		else
		{
		game.batch.draw(exit, Ancient.WIDTH/2-135, 225, 270, 105);
		}
		
		game.batch.draw((TextureRegion) canim.getKeyFrame(stateTime), 0, 0, 120*5, 50*5);
		game.batch.draw((TextureRegion) anim.getKeyFrame(stateTime), Ancient.WIDTH/2-100, 60, 20*10, 20*10);
	
		game.batch.end();
			
	
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		game.camera.update(width, height);
		
		
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
		 c1.dispose();
		 c2.dispose();
		 c3.dispose();
		 c4.dispose();
		 head.dispose();
		 play.dispose();
		 exit.dispose();
		 uplay.dispose();
		 uexit.dispose();
		 
		
	}

}
