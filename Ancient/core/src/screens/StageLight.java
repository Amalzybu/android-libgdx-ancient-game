package screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
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
import tools.State;

public class StageLight implements Screen{
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
	static boolean jump=false,fjmp=false,focus=false,freefl=false,ljmp=false,sprint=false,fs=false,bs=false,s=false,ankey=true,cineFocus=false,sprintcontrl=true;
	Tree t;
	
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
	float stateTime=0,stateTime1=0,stateTimep=0;
	Floor[] f;
	Color color1;
	Bglv1 bg;
	Crect limit=null;
	Vector<Crect> blocker;
	public Ancient game;
	
	
	public StageLight(Ancient game) {
		
		this.game=game;
		//snd = game.manager.get("leveloneMsc/Dunkstein and Slamough - Quad City DJs vs Dark Souls.mp3");
		
//		 end=new Examp(6560,130,120,50,(Texture)game.manager.get("leveloneText/cave.png"),4,-50,0,false);
//		 block=game.manager.get("leveloneText/block.png");
//		 bt=game.manager.get("leveloneText/wf.png");
//		 floor=game.manager.get("leveloneText/realfloor.png");
		 life=game.manager.get("lifes.png");
		 pause=game.manager.get("realpas.png");
		 rsmbt=game.manager.get("resume.png");
		 mainbt=game.manager.get("mainmenu.png");
		 arsmbt=game.manager.get("activeresume.png");
		 amainbt=game.manager.get("activemainmenu.png");
//		 cre=game.manager.get("leveloneText/crect.png");
//		ex= new Examp[]{new Examp(-280,60,50,50,(Texture)game.manager.get("leveloneText/stones12.png"),10,-50,0,false)};
		f= new Floor[]{};
//		b= new Block[]{new Block(700,350,block)};
		Gdx.input.setCatchBackKey(true);
		del=new EnemyDel();
		ent=new Crect(6560,0,480,720);
		teb=new Crect(3640,0,200,1000);
		
		//limit=new Crect()
		anm=new Animation(0.05f,new Array<TextureRegion>(TextureRegion.split((Texture)game.manager.get("Sprintfog.png"), 350, 65)[0]));
		
		
		blocker=new Vector<Crect>();
		gr=new Vector<Crect>();
	
//		bg=new Bglv1(0,-50,bt);
//		c1=game.manager.get("leveloneText/cloud.png");
		
		
		
		
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
		c1=new Texture("cloud.png");
		t=new Tree((Texture)game.manager.get("action.png"));
//		EnemyOne[] array= {new EnemyOne(6100,500,(Texture)game.manager.get("leveloneText/enemy1.png"),game.batch,t,true,1.5f),new EnemyOne(400,500,(Texture)game.manager.get("leveloneText/enemy1.png"),game.batch,t,false,1.5f)};
//		enemiesOnecl = new ArrayList<EnemyOne>(Arrays.asList(array));
		
		
		//blocker.add(stone.r);
//		for(Examp e:ex)
//		{
//		blocker.add(e.r);
//		}
//		//gr.add(ex.r);
//		for(Block s:b)
//		{
//		blocker.add(s.r);
//		gr.add(s.r);
//		}
//		for(Floor s:f)
//		{
//			
//		gr.add(s.r);
//		}
//		
//		blocker.add(teb);
		
		
		
//		snd.setLooping(true);
//		snd.setVolume(1);
//		snd.play();
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
		game.batch.begin();
		game.batch.end();
		
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
