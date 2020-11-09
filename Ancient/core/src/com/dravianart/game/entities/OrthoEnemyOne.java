package com.dravianart.game.entities;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import tools.Crect;
import tools.MinionsCollisionDetection;

public class OrthoEnemyOne extends Actor {
	private TiledMapTileLayer collisionLayer;
	private Sprite tex,trainfire;
	private Texture tcret;
	private Texture enemyOneText;
	private Vector2 velocity=new Vector2();
	private Animation[] anm=new Animation[3];
	private float staticTime=0,staticGap=2,staticTemp=0;
	private int anmstatus=2;
	public float speed=180*2;
	private float increment;
	private Crect rect,tree;
	private boolean activate=false;
	private Random rand;
	private Stack<float[]> train=new Stack<float[]>();
	private Vector<Object> minions=new Vector<Object>();
	MinionsCollisionDetection collisionDetetct;
	
	
	public OrthoEnemyOne(Texture enemyOneText,Texture trainfire,float x,float y,TiledMapTileLayer collisionLayer,Texture tcret,Crect tree) {
		this.enemyOneText=enemyOneText;
		rand= new Random();
		 this.tree=tree;
		this.collisionLayer=collisionLayer;
		tex=new Sprite(enemyOneText);
		this.trainfire=new Sprite(trainfire);
		this.tcret=tcret;

		this.setX(x);
		this.setY(y);
		rect=new Crect(x+20, y, 75, 50);
		tex.setBounds(x, y, 500, 500);
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tex.getTexture(), 25, 25);
		TextureRegion[][] trainsprite=TextureRegion.split(this.trainfire.getTexture(), 20, 25);
		anm[0] = new Animation(0.05f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		anm[1] = new Animation(0.05f, new Array<TextureRegion>(trainsprite[0]),PlayMode.LOOP);
		anm[2] = new Animation(0.05f, new Array<TextureRegion>(trainsprite[1]),PlayMode.LOOP);
		increment = collisionLayer.getTileWidth();
		increment = 40 < increment ? 40 / 2 : increment / 2;
		//anm[1] = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[1]),PlayMode.LOOP);
		//anm[2] = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[2]),PlayMode.LOOP);
		//anm[3] = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[3]),PlayMode.LOOP);
		
		collisionDetetct=new MinionsCollisionDetection(minions,collisionLayer,tree);
	}
	
	@Override
   public void draw(Batch batch, float alpha){
       batch.draw(tcret,rect.x,rect.y,rect.width,rect.height);
//		System.out.println("is idle"+idle);
		 float deviceAngle = Gdx.input.getAccelerometerX();
		 float deviceAngleY = Gdx.input.getAccelerometerY();
//		 System.out.println("device angle ="+deviceAngle);
		staticTime+=alpha;
		if(staticTime>(staticTemp+staticGap)&&activate) {
			staticTemp+=staticGap;
			System.out.println("*****************************************\n*******************************\n****************"+staticTime);
			minions.add(new OrthoEnemyOneMinion(getX(), getY(), 20, enemyOneText,tcret,collisionLayer,tree));
		}
		
		for(Object omin:minions) {
			OrthoEnemyOneMinion iomin=(OrthoEnemyOneMinion) omin;
			iomin.render(batch, alpha);
		}
		batch.draw(tcret, getX()+30, getY()+20, 40, 30);
//		if(train.size()==0)
//		{
//			train.add(new float[] {getX()+20,getY()});
//		}
//		if((train.lastElement()[0]+40<getX()||train.lastElement()[0]-40>getX())||(train.lastElement()[1]+50<getY()||train.lastElement()[1]-50>getY()))
//		{
//		train.add(new float[] {getX()+20,getY()});
//		}
//		if(train.size()==20)
//		{
//			train.remove(0);
//		}
//		for(float[] a:train) {
//		batch.draw((TextureRegion) anm[1].getKeyFrame(staticTime), a[0], a[1], 80, 100);
//		}
		

		
		if(anmstatus==0)
		{
			batch.draw((TextureRegion) anm[0].getKeyFrame(staticTime), getX(), getY(), 100, 100);
				
				
				velocity.y = -speed;
			
				
			
		}
		else if(anmstatus==1)
			{
			
			batch.draw((TextureRegion) anm[0].getKeyFrame(staticTime), getX()+100, getY(), -100, 100);
			
			velocity.x = -speed;
				
			
			
			}
		else if(anmstatus==3)
			{
		
			batch.draw((TextureRegion) anm[0].getKeyFrame(staticTime), getX(), getY(), 100, 100);
			
			velocity.x = speed;
			
			
			}
		else
			{
			
			batch.draw((TextureRegion) anm[0].getKeyFrame(staticTime), getX(), getY(), 100, 100);
			velocity.y = speed;
			
			
			}
		
		act(alpha);
		if(activate) {
			
		}
	
		}
		
		
		
		@Override
		   public void act(float delta){
			if(activate) {
				float oldX = getX(), oldY = getY();
				boolean collisionX = false, collisionY = false;
				setX(getX() + velocity.x * delta);

				// calculate the increment for step in #collidesLeft() and #collidesRight()
				
//				System.out.println("is blocked"+isCellBlocked(getX(), getY()));
				if(velocity.x < 0) // going left
				{
					collisionX = collidesLeft();
				}
				else if(velocity.x > 0) // going right
				{
					collisionX = collidesRight();
				}

				// react to x collision
				if(collisionX) {
					
					setX(oldX);
					velocity.x = 0;
					int dir=rand.nextInt(4);
//					System.out.println("random number x "+dir);
					anmstatus=dir;
				}
				setY(getY() + velocity.y * delta);
				if(velocity.y < 0) // going down
					collisionY = collidesBottom();
				else if(velocity.y > 0) // going right
					collisionY = collidesTop();

				// react to x collision
				if(collisionY) {
				
					setY(oldY);
					velocity.y = 0;
					int dir=rand.nextInt(4);
//					System.out.println("random number y "+a);
					anmstatus=dir;
				}
				rect.move(getX()+20, getY());
			}
			if(rect.isCollided(tree)) {
				activate=true;
				StoneWall.closed=true;
			}
			collisionDetetct.collides();
			
			
		   }
			
			
			private boolean isCellBlocked(float x, float y) {
				Cell cell = collisionLayer.getCell((int) ((x+30)/ collisionLayer.getTileWidth()), (int) ((y+20) / collisionLayer.getTileHeight()));
				return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
			}
			
			
			public boolean collidesRight() {
				for(float step = 0; step <= 40; step += increment)
					if(isCellBlocked(getX() + 40, getY() + step))
						return true;
				return false;
			}

			public boolean collidesLeft() {
				for(float step = 0; step <= 40; step += increment)
					if(isCellBlocked(getX(), getY() + step))
						return true;
				return false;
			}

			public boolean collidesTop() {
				for(float step = 0; step <= 30; step += increment)
					if(isCellBlocked(getX() + step, getY() +30))
						return true;
				return false;

			}

			public boolean collidesBottom() {
				for(float step = 0; step <= 20; step += increment)
					if(isCellBlocked(getX() + step, getY()))
						return true;
				return false;
			}
			
			public Vector<Object> getMinions(){
				return minions;
			}
		
   }

	
	
	
	

