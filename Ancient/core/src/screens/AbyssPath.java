package screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dravianart.game.Ancient;
import com.dravianart.game.entities.Block;
import com.dravianart.game.entities.DiengTree;
import com.dravianart.game.entities.EnemyOne;
import com.dravianart.game.entities.EnemyTwo;
import com.dravianart.game.entities.Examp;
import com.dravianart.game.entities.Floor;
import com.dravianart.game.entities.LightFlys;
import com.dravianart.game.entities.MovBlock;
import com.dravianart.game.entities.Npc1;
import com.dravianart.game.entities.Pond;
import com.dravianart.game.entities.River;
import com.dravianart.game.entities.Tree;

import tools.Bglv1;
import tools.Crect;
import tools.EnemyDel;
import tools.Gravity;
import tools.State;

public class AbyssPath implements Screen{
	private State state = State.RUN;
	int i=0,j=0,jtmp=0,u=0,intrc=0;
	EnemyDel del;
	DiengTree dt; 
	ArrayList<EnemyOne> enemiesOnecl ;
	ArrayList<EnemyTwo> enemiesTwocl;
	float k=0,neut=0,strtsp=0,spintTimer=0;
	Texture block;
	Texture bt;
	Texture floor;
	Texture life,lig;
	Texture pause;
	Texture rsmbt;
	Texture mainbt;
	Texture arsmbt;
	Texture amainbt;
	Music snd;
	Crect ent=null;
	static boolean jump=false,fjmp=false,focus=false,freefl=false,ljmp=false,sprint=false,fs=false,bs=false,s=false,ankey=true,cineFocus=false,sprintcontrl=true,tchflg=false,fc1=true;
	Ancient game;
	Tree t;
	
	//ArrayList<Gravity> grav=new ArrayList<Gravity>();
	Vector<Gravity>objects=new Vector<Gravity>();
	LightFlys[] lfs;
	Block[] b;
	Examp[] ex;
	Vector<Crect> gr;
	Examp end;
	Animation anm=null,bointr=null,bointr1=null;
	int w=0;
	float temp=0,val=0,valy=0,val1=0,valy1=0,oldAlpha1=0,cinex=1800;
	float[] cv= { 240,0,-50,450};
	float[] hg= {150,300,500,250};
	float stateTime=0,stateTime1=0,stateTimep=0,stateTimeB=0;
	Floor[] f;
	Color color1;
	//Floor f=new Floor(0,0);
	Bglv1 bg;
	Crect limit=null;
	Vector<Crect> blocker;
	 MovBlock mvb;
	 River rv;
	public AbyssPath(Ancient game)
	{
		this.game=game;
		
		//snd = game.manager.get("AbyssMsc/Dunkstein and Slamough - Quad City DJs vs Dark Souls.mp3");
		ent=new Crect(8000,0,480,720);
		 end=new Examp(8000,130,120,50,(Texture)game.manager.get("AbyssText/cave.png"),4,-50,0,false);
		 block=game.manager.get("AbyssText/block1.png");
		 bt=game.manager.get("AbyssText/wf1.png");
		 floor=game.manager.get("AbyssText/realfloor1.png");
		 life=game.manager.get("lifes.png");
		 pause=game.manager.get("realpas.png");
		 rsmbt=game.manager.get("resume.png");
		 mainbt=game.manager.get("mainmenu.png");
		 arsmbt=game.manager.get("activeresume.png");
		 amainbt=game.manager.get("activemainmenu.png");
		 lig=game.manager.get("AbyssText/lig.png");
		 //need change
		
		
		 lfs=new LightFlys[] {new LightFlys(lig,200,300,3),new LightFlys(lig,250,350,2),new LightFlys(lig,300,300,1),new LightFlys(lig,350,400,3),new LightFlys(lig,400,500,3),new LightFlys(lig,500,200,1.5f),new LightFlys(lig,510,600,3),new LightFlys(lig,550,450,2.5f),new LightFlys(lig,600,250,3.5f),new LightFlys(lig,700,500,2),new LightFlys(lig,800,600,1),new LightFlys(lig,900,300,4),new LightFlys(lig,1000,500,3),new LightFlys(lig,1100,400,2)};
		 ex= new Examp[]{new Examp(-380,60,60,100,(Texture)game.manager.get("AbyssText/wterfall.png"),10,-50,0,true),new Examp(2660,45,510,50,(Texture)game.manager.get("AbyssText/shght.png"),4,-50,-40,true)};
			f= new Floor[]{new Floor(0,0,floor),new Floor(800,0,floor),new Floor(1700,0,floor),new Floor(2550,0,floor),new Floor(2710,80,floor),new Floor(3600,80,floor)};
			b= new Block[]{new Block(700,350,block),new Block(900,450,block)};
			Gdx.input.setCatchBackKey(true);
			del=new EnemyDel();
			bointr=new Animation(0.15f,new Array<TextureRegion>(TextureRegion.split((Texture)game.manager.get("AbyssText/bosintr1.png"), 210, 100)[0]));
			bointr1=new Animation(0.15f,new Array<TextureRegion>(TextureRegion.split((Texture)game.manager.get("AbyssText/bosintr1.png"), 210, 100)[1]));
			anm=new Animation(0.05f,new Array<TextureRegion>(TextureRegion.split((Texture)game.manager.get("Sprintfog.png"), 350, 65)[0]));
			blocker=new Vector<Crect>();
			gr=new Vector<Crect>();
			bg=new Bglv1(0,0,bt);
			rv=new River(4660,120,game);
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
				//	System.out.println("!freefl = "+!freefl+" !sprint ="+!sprint+" ankey ="+ankey+" !focus ="+!focus+" !t.painjmp ="+!t.painjmp+" !jump ="+!jump+" temp ="+temp);
					if(!freefl&&!sprint&&ankey&&!t.painjmp&&sprintcontrl)
					{
					
					//System.out.println("x "+x+" y "+y+" deltax "+deltaX+" deltay "+deltaY);
						
					if(temp==0)
					{
					

					
					//System.out.println("trmp changed");
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
					
					else if(val+50<x&& valy+25>y&&valy-25<y&&!jump&&temp==1&&!sprint)
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
					else if(val-50>x && valy+25>y&&valy-25<y&&!jump&&temp==1&&!sprint)
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
			t=new Tree((Texture)game.manager.get("action.png"));
			
			EnemyOne[] array= {new EnemyOne(1000,500,(Texture)game.manager.get("AbyssText/enemy1.png"),game.batch,t,false,1.5f)};
			enemiesOnecl = new ArrayList<EnemyOne>(Arrays.asList(array));
			EnemyTwo[] eto= new EnemyTwo[]{new EnemyTwo(1200,700,(Texture)game.manager.get("AbyssText/secondEnm.png"),(Texture)game.manager.get("AbyssText/enmytwodeath.png"),game.batch,t,true,1)};
			 enemiesTwocl= new ArrayList<EnemyTwo>(Arrays.asList(eto));
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
			for(Floor a:rv.f)
			{
				gr.add(a.r);
			}
			objects.add(t);
			for(Gravity p:eto)
			{
				objects.add(p);
			}
			for(Gravity p:array)
			{
				objects.add(p);
			}
			mvb=new MovBlock(2700,100,block,objects,true,true,250f,0,0,500,0,-500);
			blocker.add(mvb.r);
			gr.add(mvb.r);
			 dt=new DiengTree(2700,60,game.batch,(Texture)game.manager.get("AbyssText/dieing tree.png"),(Texture)game.manager.get("AbyssText/cuttree.png"),t,mvb);
			//snd.setLooping(true);
			//snd.setVolume(1);
			//snd.play();
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
		    		if(spintTimer>=0.35f)
		    		{
		    			sprintcontrl=true;
		    			spintTimer=0;
		    		}
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.ENTER)||Gdx.input.isKeyPressed(Input.Keys.BACK))
				{
					state=State.PAUSE;
					//System.out.println("pause");
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
		
		for(EnemyTwo et:enemiesTwocl)
		{
		et.y-=et.pull(delta, gr);
		et.block(gr, et.pos);
		et.update();
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
		Gdx.gl.glClearColor(0f, 0f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		game.batch.enableBlending();
		game.batch.begin();
		
		game.batch.draw((TextureRegion) bg.a.getKeyFrame(stateTime), bg.x, bg.y, 100*8, 90*8);
		
		for(LightFlys l:lfs)
		{
			l.render(delta, game.batch);
		}
		
		
		//
		if(t.r.isCollided(f[2].r))
		{
			tchflg=true;
		}
		if(tchflg)
		{
		if(intrc==0)
		{
			
		game.batch.draw((TextureRegion)bointr.getKeyFrame(stateTimeB),cinex,100,210*1.5f,100*1.5f);
		stateTimeB+=delta;
		if(bointr.isAnimationFinished(stateTimeB))
		{
			System.out.println("first ends");
			stateTimeB=0;
			intrc=1;
		}
		}
		else if(intrc==1) {
			game.batch.draw((TextureRegion)bointr1.getKeyFrame(stateTimeB),cinex,100,210*1.5f,100*1.5f);
			stateTimeB+=delta;
			if(bointr.isAnimationFinished(stateTimeB))
			{
				System.out.println("second ends ends");
				//stateTimeB=0;
				intrc=3;
			}
			
		}
		}
		
		//
		for(Floor k:f)
		{
		game.batch.draw((TextureRegion) k.flr.getKeyFrame(stateTime), k.x, k.y, 240*4, 50*4);
		}
		rv.render();
		ankey=dt.render(delta);
		if(!ankey&&fc1)
		{
			fc1=changeFocus(dt.r, 0, delta, 100f);
			
			
		}
		

		for(Examp e:ex)
		{
			if(!e.am)
			{
				game.batch.draw(e.blk, e.x, e.y, e.width, e.height);
			}
			else
			{
				e.render(game.batch);
			}
		}
		for(Block s:b)
		{
		
		game.batch.draw((TextureRegion) s.blk.getKeyFrame(stateTime), s.x, s.y, 50*3, 30*3);
		}
		mvb.render(game.batch, delta);
		
		game.batch.draw(end.blk, end.x, end.y, end.width, end.height);
		//System.out.println(focus+" bs "+bs+" sprint "+sprint+"  left touch "+(Gdx.input.isTouched()&&game.camera.getInputGameWorld().x<(game.WIDTH/2)+100&&!freefl&&t.block( blocker, t.r,1)&&ankey));
		for(EnemyTwo et:enemiesTwocl)
		{
		et.render(delta,gr);
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
			
				t.sprint=0;
				sprintcontrl=false;
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
			
		if(Gdx.input.isTouched()&&game.camera.getInputGameWorld().x>(game.WIDTH/2)&&!freefl&&t.block( blocker,0)&&ankey&&!t.painjmp)
		{
		
			t.pos=0;
			
		game.batch.draw((TextureRegion) t.rolls[2].getKeyFrame(stateTime), t.x, t.y, 20*7, 20*7);
		
		move(-200,delta);
		}
		else if(Gdx.input.isTouched()&&game.camera.getInputGameWorld().x<(game.WIDTH/2)&&!freefl&&t.block( blocker,1)&&ankey&&!t.painjmp)
		{
		
			t.pos=1;
			
			game.batch.draw((TextureRegion) t.rolls[2].getKeyFrame(stateTime), t.x+140, t.y, -20*7, 20*7);
			
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
					temp=0;
					//t.painjmp=false;
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
					temp=0;
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
		e.render(delta,blocker);
		}
		del.deleteEnemy(enemiesOnecl,null);
		del.deleteEnemy(enemiesTwocl,null);
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
		
		for(int l=0;l<t.life;l++)
		{
			game.batch.draw(life, 0+(l*30), 690,20,20);
		}
		
		game.batch.end();
		if(ent.isCollided(t.r))
		{
			game.prefs.putString("lvl", "StageOne");
			game.prefs.putString("tex", "leveloneText");
			game.prefs.putString("msc", "leveloneMsc");
			game.prefs.flush();
			game.lod=new LoadingScreen(game);
			game.setScreen(game.lod);
		}
		if(Math.round(t.x)!=200&&focus)
		{
			
			if(t.x>200)
			{
				move(-700,delta);
				
				t.x-=delta*700;
				t.update();
				if(Math.round(t.x)<=200)
				{
					//System.out.println("reset");
					focus=false;
				}
				

				
			}
			else if(t.x<200)
			{
				move(700,delta);
			
				t.x+=delta*700;
				t.update();
				if(Math.round(t.x)>=200)
				{
					//System.out.println("reset");
					focus=false;
				}
			
			}
			
						
			
			
		}
		break;
		    case PAUSE:
				//do stuff here
		    	
				    	stateTimep+=delta;
				    	//Gdx.gl.glClearColor(0f,0f, 0f, 1);
						//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
						game.batch.enableBlending();
						game.batch.begin();
						
						game.batch.draw(pause, 0, 0, 480, 720);
						if((game.camera.getInputGameWorld().x>150&&game.camera.getInputGameWorld().x<330)&&(game.camera.getInputGameWorld().y<220&&game.camera.getInputGameWorld().y>175))
						{
							game.batch.draw(arsmbt, 150, 500, 60*3, 15*3);
							 if(Gdx.input.isTouched())
							 {
								 
							state=State.RUN;
							 }
						}
						else
						{
							game.batch.draw(rsmbt, 150, 500, 60*3, 15*3);
						}
						 if((game.camera.getInputGameWorld().x>120&&game.camera.getInputGameWorld().x<360)&&(game.camera.getInputGameWorld().y<320&&game.camera.getInputGameWorld().y>275))
						{
							 game.batch.draw(amainbt, 120, 400, 80*3, 15*3);
							 if(Gdx.input.isTouched())
							 {
								 this.dispose();
								 game.m.stateTime=0;
								 game.setScreen(game.m);
								 
								 
								
							 }
						}
						 else
						 {
							 game.batch.draw(mainbt, 120, 400, 80*3, 15*3);
						 }
						
						 game.batch.end();
						
				    	if(Gdx.input.isKeyJustPressed(Keys.ENTER)||Gdx.input.isKeyJustPressed(Input.Keys.BACK))
						{
							state=State.RUN;
							//System.out.println("Run");
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
	public void move(float sp,float delta )
	{
		//System.out.println("mov");
		mvb.x+=delta*sp;
		mvb.update();
		mvb.fx+=delta*sp;
		
		/*for(int i=0; i<mvb.points.length;i++)
		{
			if(mvb.b&&i%2==0)
			{
				//System.out.println("points "+mvb.points[i]);
			//mvb.points[i]+=delta*sp;
			}
			
		}*/
		for(LightFlys l:lfs)
		{
			l.updte(delta*sp);
			
		}
		cinex+=delta*sp;
		for(Floor k:f)
		{
		k.x+=delta*sp;
		k.update();
		}
		for(Block s:b)
		{

		s.x+=delta*sp;
		s.update();
		}
		for(Examp e:ex)
		{
		e.x+=delta*sp;
		e.update();
		}
		bg.x+=delta*sp/20;
		for(EnemyOne e:enemiesOnecl)
		{
		e.x+=delta*sp;
		e.px+=delta*sp;
		e.update();
		}
		end.x+=delta*sp;
		end.update();
		
		
		for(int j=0;j<=3;j++)
		{
			cv[j]+=delta*sp/10;
			
		}
		
		ent.x+=delta*sp;
		for(EnemyTwo et:enemiesTwocl)
		{
		et.x+=delta*sp;
		}
		dt.x+=delta*sp;
		dt.ch.x+=delta*sp;
		dt.update();
		for(Floor r:rv.f)
		{
			r.x+=delta*sp;
			r.update();
		}
	}
	public boolean changeFocus(Crect r,float x,float delta,float sp)
	{
		
		if(r.x+x>=0)
		{
			cineFocus=true;
			t.x-=delta*sp;
			t.update();
			move(-sp,delta);
		}
		else
		{
			cineFocus=false;
		}
		return cineFocus;
	}
	

}
