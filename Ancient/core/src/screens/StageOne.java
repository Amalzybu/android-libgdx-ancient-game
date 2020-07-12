package screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dravianart.game.Ancient;

import com.dravianart.game.entities.Block;
import com.dravianart.game.entities.EnemyOne;
import com.dravianart.game.entities.Examp;
import com.dravianart.game.entities.Floor;
import com.dravianart.game.entities.Npc1;
import com.dravianart.game.entities.Pond;
import com.dravianart.game.entities.Tree;

import tools.Bglv1;
import tools.Crect;
import tools.EnemyDel;
import tools.Gravity;
import tools.State;

public class StageOne implements Screen{
	private State state = State.RUN;
	int i=0,j=0,jtmp=0,u=0;
	EnemyDel del;
	ArrayList<EnemyOne> enemiesOnecl ;
	float k=0,neut=0,strtsp=0,spintTimer=0;
	Texture block;
	Texture bt;
	Texture floor;
	Texture life;
	Texture pause;
	Texture rsmbt;
	Texture mainbt;
	Texture arsmbt;
	Texture amainbt;
	Music snd;
	public static boolean jump=false,fjmp=false,focus=false,freefl=false,ljmp=false,sprint=false,fs=false,bs=false,s=false,ankey=true,cineFocus=false,sprintcontrl=true;
	Ancient game;
	public Tree t;
	
	//ArrayList<Gravity> grav=new ArrayList<Gravity>();
	Crect teb=null; 
	Crect ent=null;
	Block[] b;
	Examp[] ex;
	Vector<Crect> gr;
	Texture c1=null;
	Npc1 np=null;
	Examp end;
	Animation anm=null;
	Texture cre;
	int w=0;
	float temp=0,val=0,valy=0,val1=0,valy1=0,oldAlpha1=0 ;
	float[] cv= { 240,0,-50,450};
	float[] hg= {150,300,500,250};
	public float stateTime=0,stateTime1=0,stateTimep=0;
	Floor[] f;
	Pond p;
	Color color1;
	//Floor f=new Floor(0,0);
	Bglv1 bg;
	Crect limit=null;
	Vector<Crect> blocker;
	public StageOne(Ancient game)
	{
		
		this.game=game;
		snd = game.manager.get("leveloneMsc/Dunkstein and Slamough - Quad City DJs vs Dark Souls.mp3");
		
		 end=new Examp(6560,130,120,50,(Texture)game.manager.get("leveloneText/cave.png"),4,-50,0,false);
		 block=game.manager.get("leveloneText/block.png");
		 bt=game.manager.get("leveloneText/wf.png");
		 floor=game.manager.get("leveloneText/realfloor.png");
		 life=game.manager.get("lifes.png");
		 pause=game.manager.get("realpas.png");
		 rsmbt=game.manager.get("resume.png");
		 mainbt=game.manager.get("mainmenu.png");
		 arsmbt=game.manager.get("activeresume.png");
		 amainbt=game.manager.get("activemainmenu.png");
		 cre=game.manager.get("leveloneText/crect.png");
		ex= new Examp[]{new Examp(-280,60,50,50,(Texture)game.manager.get("leveloneText/stones12.png"),10,-50,0,false),new Examp(1100,65,250,110,(Texture)game.manager.get("leveloneText/hight.png"),4,-50,0,false),new Examp(5432,-212,512,91,(Texture)game.manager.get("leveloneText/flrbase.png"),4,-50,0,false)};
		f= new Floor[]{new Floor(0,0,floor),new Floor(900,0,floor),new Floor(1100,400,floor),new Floor(1860,0,floor),new Floor(2820,0,floor),new Floor(5500,50,floor),new Floor(6400,50,floor)};
		b= new Block[]{new Block(700,350,block),new Block(900,450,block),new Block(500,200,block),new Block(3000,250,block),new Block(3600,200,block),new Block(3800,300,block),new Block(4000,400,block),new Block(4100,500,block),new Block(4200,600,block),new Block(4400,715,block),new Block(4600,600,block),new Block(4800,400,block),new Block(5080,350,block),new Block(5360,300,block),new Block(5460,200,block)};
		Gdx.input.setCatchBackKey(true);
		del=new EnemyDel();
		ent=new Crect(6560,0,480,720);
		teb=new Crect(3640,0,200,1000);
		p=new Pond(3780,0,game.manager);
		//limit=new Crect()
		anm=new Animation(0.05f,new Array<TextureRegion>(TextureRegion.split((Texture)game.manager.get("Sprintfog.png"), 350, 65)[0]));
		
		
		
		blocker=new Vector<Crect>();
		gr=new Vector<Crect>();
	
		bg=new Bglv1(0,-50,bt);
		c1=game.manager.get("leveloneText/cloud.png");
		
		
		
		
		Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureListener() {

			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean tap(float x, float y, int count, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean longPress(float x, float y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				// TODO Auto-generated method stub
				if(!freefl&&!sprint&&ankey&&!t.painjmp&&sprintcontrl)
				{
				
				//System.out.println("x "+x+" y "+y+" deltax "+deltaX+" deltay "+deltaY);
					
				if(temp==0)
				{
				

				
				
				val=x;
				valy=y;
				temp=1;
				
				
				}
				
				
				else  if(val<x&&valy-50>y&&temp==1)
				{
					t.jmp=true;
					stateTime=0;
					jump=true;
					t.pos=0;
					
					k=(x<=val+10)? 0:x-val;
					k=(k>=100)? 200:k;
					fjmp=true;
					
					
				}
				else if(val>x&&valy-50>y&&temp==1)
				{
					t.jmp=true;
					stateTime=0;
					jump=true;
					t.pos=1;
					
					k=(x>=val-10)? 0:x+val;
					//System.out.println("before" +k);
					k=(k<=-100)? -200:k;
					//System.out.println(k);
					ljmp=true;
					
					//Systexm.out.println("set k "+k);
				}
				
				else if(val+50<x&& valy+25>y&&valy-25<y&&!jump&&temp==1&&!t.r.isCollided(p.r)&&!sprint)
				{
					t.spcount++;
					strtsp=t.x;
					//System.out.println("action 3.1");
					t.pos=0;
					sprint=true;
					fs=true;
					stateTime=0;
					jump=false;
					
					t.jflg=0;stateTime=0;
				}
				else if(val-50>x && valy+25>y&&valy-25<y&&!jump&&temp==1&&!t.r.isCollided(p.r)&&!sprint)
				{
					t.spcount++;
					strtsp=t.x;
				//	System.out.println("action 3.2");
					t.pos=1;
					sprint=true;
					bs=true;
					jump=false;
					
					t.jflg=0;stateTime=0;
				}
				}
				
				
				
				
				return true;
			}

			@Override
			public boolean panStop(float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean zoom(float initialDistance, float distance) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void pinchStop() {
				// TODO Auto-generated method stub
				
			}
			
		}));
		c1=new Texture("cloud.png");
		t=new Tree((Texture)game.manager.get("action.png"));
		EnemyOne[] array= {new EnemyOne(6100,500,(Texture)game.manager.get("leveloneText/enemy1.png"),game.batch,t,true,1.5f),new EnemyOne(400,500,(Texture)game.manager.get("leveloneText/enemy1.png"),game.batch,t,false,1.5f)};
		enemiesOnecl = new ArrayList<EnemyOne>(Arrays.asList(array));
		np=new Npc1(3200,0,game.batch,(Texture)game.manager.get("leveloneText/npc1sleep.png"),t);
		
		//blocker.add(stone.r);
		for(Examp e:ex)
		{
		blocker.add(e.r);
		}
		//gr.add(ex.r);
		for(Block s:b)
		{
		blocker.add(s.r);
		gr.add(s.r);
		}
		for(Floor s:f)
		{
			
		gr.add(s.r);
		}
		gr.add(p.r);
		blocker.add(teb);
		snd.setLooping(true);
		snd.setVolume(1);
		snd.play();
		//grav.add(t);
		//grav.add(e);

		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 switch (state)
		    {
		    case RUN:
		    	if(!sprintcontrl)
		    	{
		    		spintTimer+=delta;
		    		if(spintTimer>=0.25f)
		    		{
		    			sprintcontrl=true;
		    			spintTimer=0;
		    		}
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.ENTER)||Gdx.input.isKeyPressed(Input.Keys.BACK))
				{
					state=State.PAUSE;
					System.out.println("pause");
					//pause();
				}
		stateTime+=delta;
		stateTime1+=delta;
		for(int j=0;j<=3;j++)
		{
			cv[j]-=delta*5;
			if(cv[j]+380<0)
			{
				cv[j]=480;
			}
		}
		
		
		
	t.block(blocker, t.pos);
	for(EnemyOne e:enemiesOnecl)
	{
	e.block(blocker, e.pos);
	e.y-=e.pull(delta, gr);
	e.update();
	}
		t.y-=t.pull(delta,gr);
	
		t.update();
		if(t.y>=t.limit&&!jump)
		{
			freefl=true;
		}
		else
		{
			freefl=false;
		}
		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		game.batch.enableBlending();
		game.batch.begin();
		Color color = game.batch.getColor();//get current Color, you can't modify directly
		float oldAlpha = color.a; //save its alpha

		//From here you can modify alpha however you want
		
		color.a = oldAlpha*0.5f; //ex. scale = 0.5 will make alpha halved
		game.batch.setColor(color);
		game.batch.draw((TextureRegion) bg.a.getKeyFrame(stateTime), bg.x, bg.y, 100*8, 50*8);
		color.a = oldAlpha;
		game.batch.setColor(color);
		w=0;
		for(float s:cv)
		{
			
		game.batch.draw(c1, s, hg[w], 38*10, 30*10);
		w++;
		}
		
		for(Floor k:f)
		{
		game.batch.draw((TextureRegion) k.flr.getKeyFrame(stateTime), k.x, k.y, 240*4, 50*4);
		}
		for(Examp e:ex)
		{
		game.batch.draw(e.blk, e.x, e.y, e.width, e.height);
		}
		for(Block s:b)
		{
		
		game.batch.draw((TextureRegion) s.blk.getKeyFrame(stateTime), s.x, s.y, 50*3, 30*3);
		}
		ankey=np.render( delta);
		//game.batch.draw(cre, np.x, np.y,np.r.width,np.r.height);
		if(np.ct)
		{
			blocker.remove(teb);
		}
		game.batch.draw(end.blk, end.x, end.y, end.width, end.height);
		//System.out.println(focus+" bs "+bs+" sprint "+sprint+"  left touch "+(Gdx.input.isTouched()&&game.camera.getInputGameWorld().x<(game.WIDTH/2)+100&&!freefl&&t.block( blocker, t.r,1)&&ankey));
		if(t.r.isCollided(p.r))
		{
			if(!p.stamp[1].isAnimationFinished(p.stateTime1))
			{
			changeFocus(p.r, 840, delta, 700);
			}
			else
			{
				focus=true;
			}
			
		}
		p.render(game.batch,t.r.isCollided(p.r));
		if(t.r.isCollided(p.r))
		{
			ankey=false;
		}
		if(s)
		{
			if(t.pos==0)
			{
			game.batch.draw((TextureRegion) anm.getKeyFrame(stateTime1), t.x-250, t.y+20, 350, 65);
			}
			else
			{
				game.batch.draw((TextureRegion) anm.getKeyFrame(stateTime1), t.x+(350), t.y+20, -350, 65);
			}
			//System.out.println(anm.isAnimationFinished(stateTime1));
			if(anm.isAnimationFinished(stateTime1))
			{
				s=false;
				
			}
		}
		if(t.npain)
		{
			color1 = game.batch.getColor();//get current Color, you can't modify directly
			 oldAlpha1 = color1.a; //save its alpha

			//From here you can modify alpha however you want
			
			color1.a = oldAlpha1*0.5f; //ex. scale = 0.5 will make alpha halved
			game.batch.setColor(color1);
		}
		if(jump)
		{
			if(t.jflg==0)
			{
			t.y+=delta*1500;
			if(t.pos==0)
			{
			game.batch.draw((TextureRegion) t.rolls[3].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
			}
			else
			{
				game.batch.draw((TextureRegion) t.rolls[3].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
			}
			//System.out.println(" tup "+t.up+"  tcollid ");
			if(t.y>=t.up)
			{
				//System.out.println("wrong"+(t.y>=t.up)+" block "+!t.block(blocker, t.r, t.pos)+" tup "+t.up);
				t.jflg=1;stateTime=0;
			}
			t.update();
			}
			if(t.jflg==1)
			{
				
				if(t.pos==0)
				{
			game.batch.draw((TextureRegion) t.rolls[4].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
				}
				else
				{
					game.batch.draw((TextureRegion) t.rolls[4].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
				}
			if(t.y<=t.limit)
			{
			jump=false;t.jflg=0;stateTime=0;fjmp=false;k=0;temp=0;focus=true;t.jmp=false;ljmp=false;
			
			}
			
			
		//game.batch.draw((TextureRegion) t.rolls[3].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
			}
			
		}
		// new 
		else if(sprint)
		{
			
			t.sprint=1;
			//System.out.println("sprint true");
			
		
			
			if(fs&&t.block(blocker,  t.pos))
			{
			//	System.out.println("sprint going");
				game.batch.draw((TextureRegion) t.rolls[5].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
				
				t.x+=delta*1000;
				
				t.update();
			
			if(t.x>=strtsp+75)
			{
				stateTime1=0;
				s=true;
			}
			
			if(t.x>=strtsp+200||!t.block(blocker,  t.pos))
			{
				//System.out.println("sprint end \\\\\\\\\\\\\\\\\\");
				sprintcontrl=false;
				t.sprint=0;
				temp=0;
				fs=false;
				sprint=false;
				focus=true;
				val=0;
				valy=0;
				//stateTime=0;
				
			}
			}
			
		else if(bs&&t.block(blocker, t.pos))
			{
				
				game.batch.draw((TextureRegion) t.rolls[5].getKeyFrame(stateTime), t.x+20*7, t.y, -20*7, 20*7);
				
				t.x-=delta*1000;
				
				t.update();
			
			if(t.x<=strtsp-75)
			{
				stateTime1=0;
				s=true;
			}
			
			if(t.x<=strtsp-200||!t.block(blocker, t.pos))
			{
				sprintcontrl=false;
				t.sprint=0;
				temp=0;
				bs=false;
				sprint=false;
				focus=true;
				val=0;
				valy=0;
				fjmp=false;
				ljmp=false;
				
			}
			}
			
			else
			{
				//System.out.println("reset");
				sprintcontrl=false;
				temp=0;
				fs=false;
				bs=false;
				sprint=false;
				focus=true;
				val=0;
				valy=0;
				fjmp=false;
				ljmp=false;
				
				
			}
		
		}
		else
		{
			
		if(Gdx.input.isTouched()&&game.camera.getInputGameWorld().x-game.camera.getCam().position.x+200>(game.WIDTH/2)&&!freefl&&t.block( blocker,0)&&ankey&&!t.painjmp)
		{
			
//			System.out.println("camer "+game.camera.getCam().position.x);
			t.pos=0;
			if(t.r.isCollided(p.r))
			{
				game.batch.draw((TextureRegion) t.rolls[6].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
			}
			else
			{
		game.batch.draw((TextureRegion) t.rolls[2].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
			}
		move(-200,delta);
		}
		else if(Gdx.input.isTouched()&&game.camera.getInputGameWorld().x-game.camera.getCam().position.x+200<(game.WIDTH/2)&&!freefl&&t.block( blocker,1)&&ankey&&!t.painjmp)
		{
			t.pos=1;
			if(t.r.isCollided(p.r))
			{
				game.batch.draw((TextureRegion) t.rolls[6].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
			}
			else
			{
			game.batch.draw((TextureRegion) t.rolls[2].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
			}
			move(200,delta);
		}
		else
		{
			if(temp==1&&!sprint&&!jump)
			{
				//System.out.println("reset 1");
				temp=0;
				bs=false;
				fs=false;
				sprint=false;
				val=0;
				valy=0;
			}
			if(t.pos==0&&!t.painjmp)
			{
			
			game.batch.draw((TextureRegion) t.rolls[1].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
			}
			else if(t.pos==1&&!t.painjmp)
			{
				game.batch.draw((TextureRegion) t.rolls[1].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
			}
			
			// new section
			
			
			if(t.painjmp)
			{
				
				u++;
				if(t.pos==0)
				{
				game.batch.draw((TextureRegion) t.rolls[3].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
				if(t.esj<=t.x&&t.block(blocker, t.pos))
				{
					t.x-=200*delta;
					t.update();
					if(!t.block(blocker, t.pos))
					{
						t.x+=200*delta;
						t.update();
					}
					
				}
				else
				{
					t.painjmp=false;
					focus=true;
				}
				
				
				}
				
				//  gap 
				
				
				if(t.pos==1)
				{
				game.batch.draw((TextureRegion) t.rolls[3].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
				if(t.esj>=t.x&&t.block(blocker, t.pos))
				{
					t.x+=200*delta;
					t.update();
					if(!t.block(blocker, t.pos))
					{
						
						t.x-=200*delta;
						t.update();
					}
				}
				else
				{
					t.painjmp=false;
					focus=true;
				}
				
				}
				//t.pull(delta, gr);
				if(u<=15)
				{
					t.y+=1000*delta;
					t.update();
				}
				else
				{
					u=0;
					t.painjmp=false;
					focus=true;
				}
				
			}
		}
		}
		if(t.npain)
		{
			color1.a = oldAlpha1;
			game.batch.setColor(color1);
		}
		for(EnemyOne e:enemiesOnecl)
		{
			game.batch.draw(cre, e.r.x, e.r.y, e.r.width, e.r.height );
		e.render(delta,blocker);
		}
		del.deleteEnemy(enemiesOnecl,null);
		if(fjmp&&t.block(blocker, 0))
		{
			t.x+=delta*200;
			t.update();
			i++;
			if(i>=Math.round(k))
			{
				//System.out.println("k "+k);
				fjmp=false;
				i=0;
				k=0;
				temp=0;
			}
			
		}
		if(ljmp&&t.block(blocker, 1))
		{
			t.x-=delta*200;
			t.update();
			j++;
			
			if(i>=Math.round(k))
			{
				//System.out.println("k "+k);
				ljmp=false;
				j=0;
				k=0;
				temp=0;
			}
			
		}
		p.water(game.batch,t);
		for(int l=0;l<t.life;l++)
		{
			game.batch.draw(life, 0+(l*30), 690,20,20);
		}
		game.batch.end();
		if(t.y<-50)
		{
			game.prefs.putString("lvl", "AbyssPath");
			game.prefs.putString("tex", "AbyssText");
			game.prefs.putString("msc", "AbyssMsc");
			game.prefs.flush();
			game.lod=new LoadingScreen(game);
			snd.stop();
			snd.dispose();
			game.camera.getCam().position.x=240.0f;
			game.camera.getCam().update();
			game.setScreen(game.lod);
		}
		if(ent.isCollided(t.r))
		{
			game.setScreen(new TestScreen(game));
		}
		if(Math.round(t.x)!=game.camera.getCam().position.x-100&&focus)
		{
			
			if(t.x>game.camera.getCam().position.x-100)
			{
				move(-700,delta);
				
				t.x-=delta*700;
				t.update();
				if(Math.round(t.x)<=game.camera.getCam().position.x-100)
				{
					//System.out.println("reset");
					focus=false;
				}
				

				
			}
			else if(t.x<game.camera.getCam().position.x-100)
			{
				move(700,delta);
			
				t.x+=delta*700;
				t.update();
				if(Math.round(t.x)>=game.camera.getCam().position.x-100)
				{
					//System.out.println("reset");
					focus=false;
				}
			
			}
			
						
			
			
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
		//game.camera.update(width, height);
		
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
		System.out.println("came x="+game.camera.getCam().position.x);
		
		game.manager.unload("leveloneText/fish.png");
		game.manager.unload("leveloneText/flrbase.png");
		game.manager.unload("leveloneText/cave.png");
		game.manager.unload("leveloneText/hight.png");
		game.manager.unload("leveloneText/npc1sleep.png");
		game.manager.unload("leveloneText/pond.png");
		game.manager.unload("leveloneText/pondmod.png");
		game.manager.unload("leveloneText/realfloor.png");
		game.manager.unload("leveloneText/cloud.png");
		game.manager.unload("leveloneText/water.png");
		game.manager.unload("leveloneText/watermod.png");
		game.manager.unload("leveloneText/wf.png");
		snd.stop();
		snd.dispose();
		//game.manager.finishLoading();
		/*np.dispose();
		p.dispose();
		t.dispose();
		 block.dispose();
		 bt.dispose();
		 floor.dispose();
		 life.dispose();
		 pause.dispose();
		 rsmbt.dispose();
		 mainbt.dispose();
		 arsmbt.dispose();
		 amainbt.dispose();
		 for(Examp o:ex)
		 {
			 o.dispose();
		 }
		 end.dispose();*/
		
		
	}
	public void move(float sp,float delta )
	{
		t.x-=delta*sp;
	
		game.camera.getCam().translate(-delta*sp, 0);
		game.camera.getCam().update();
		for(int i=0;i<cv.length;i++) {
		cv[i]+=-(delta*sp);
		}
		bg.x+=-(delta*sp);

		
	}
	public boolean changeFocus(Crect r,float x,float delta,float sp)
	{
		if(game.camera.getCam().position.x<=r.x+x)
		{
			cineFocus=true;
			t.x-=delta*sp;
			move(-sp,delta);
		}
		else
		{
			cineFocus=false;
		}
		return cineFocus;
	}
	

}
