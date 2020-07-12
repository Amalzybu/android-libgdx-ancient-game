package com.dravianart.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import tools.Crect;

public class OrthoTree extends Actor implements InputProcessor,GestureDetector.GestureListener {
	float staticTime=0;
	public float speed=200*2;
	private BitmapFont font;
	private TiledMapTileLayer collisionLayer;
	public Crect position;
	Sprite tex;
	int anmstatus=0;
	float val;
	float valy;
	float temp;
	private Vector2 velocity=new Vector2();
	Animation anm[] =new Animation[4];
	private float increment;
	Texture tcret;
	private boolean idle;
	private boolean taped=true;
	public OrthoTree(Texture tre,float x,float y,TiledMapTileLayer collisionLayer,Texture tcret) {
		 font = new BitmapFont(Gdx.files.internal("fonts/score.fnt"),false);
		this.collisionLayer=collisionLayer;
		tex=new Sprite(tre);
		this.tcret=tcret;
		this.setX(x);
		this.setY(y);
		tex.setBounds(x, y, 500, 500);
		position=new Crect(x+30, y+20, 40,60);
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(tex.getTexture(), 20, 20);
		anm[0] = new Animation(0.05f, new Array<TextureRegion>(rollSpriteSheet[0]),PlayMode.LOOP);
		anm[1] = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[1]),PlayMode.LOOP);
		anm[2] = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[2]),PlayMode.LOOP);
		anm[3] = new Animation(0.1f, new Array<TextureRegion>(rollSpriteSheet[3]),PlayMode.LOOP);
		
		
	}
	
	@Override
    public void draw(Batch batch, float alpha){
		
//        batch.draw(tex,0,0);
//		System.out.println("is idle"+idle);
		
		 float deviceAngle = Gdx.input.getAccelerometerX();
		 float deviceAngleY = Gdx.input.getAccelerometerY();
//		 System.out.println("device angle ="+deviceAngle);
		staticTime+=alpha;
//		batch.setColor(3f,3f,3f, 3f);
		batch.draw(tcret, position.x, position.y, position.width, position.height);
//		batch.setColor(1f,1f,1f, 1f);
		if(!idle) {
		if(anmstatus==0)
		{
			batch.draw((TextureRegion) anm[3].getKeyFrame(staticTime), getX(), getY(), 100, 100);
				
				
				velocity.y = -speed;
			
				
			
		}
		else if(anmstatus==1)
			{
			
			batch.draw((TextureRegion) anm[1].getKeyFrame(staticTime), getX()+100, getY(), -100, 100);
			
			velocity.x = -speed;
				
			
			
			}
		else if(anmstatus==3)
			{
		
			batch.draw((TextureRegion) anm[1].getKeyFrame(staticTime), getX(), getY(), 100, 100);
			
			velocity.x = speed;
			
			
			}
		else if(anmstatus==2)
			{
			
			batch.draw((TextureRegion) anm[2].getKeyFrame(staticTime), getX(), getY(), 100, 100);
			velocity.y = speed;
			
			
			}
		}
		else {
			System.out.println("idle x="+getX()+" y= "+getY());
			if(anmstatus==0) {
				batch.draw((TextureRegion) anm[0].getKeyFrame(0.10f), getX(), getY(), 100, 100);
			}
			else if(anmstatus==1) {
				batch.draw((TextureRegion) anm[0].getKeyFrame(0), getX()+100, getY(), -100, 100);
			}
			else if(anmstatus==3) {
				batch.draw((TextureRegion) anm[0].getKeyFrame(0), getX(), getY(), 100, 100);
			}
			else if(anmstatus==2) {
				batch.draw((TextureRegion) anm[2].getKeyFrame(3), getX(), getY(), 100, 100);
			}
		}
		
		act(alpha);
		font.draw(batch, "x "+Math.round(deviceAngle), 0, 0);
		font.draw(batch, "y "+Math.round(deviceAngleY), 0, 50);
		
		position.x=getX()+30;
		position.y=getY()+20;
    }

	
	@Override
    public void act(float delta){
		
		
		if(!taped) {
			velocity.x = 0;
			velocity.y = 0;
		}
		
		float oldX = getX(), oldY = getY();
		boolean collisionX = false, collisionY = false;
		setX(getX() + velocity.x * delta);

		// calculate the increment for step in #collidesLeft() and #collidesRight()
		increment = collisionLayer.getTileWidth();
		increment = 40 < increment ? 40 / 2 : increment / 2;
//		System.out.println("is blocked"+isCellBlocked(getX(), getY()));
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
		}
		if(velocity.x==0&&velocity.y==0) {
			idle=true;
		}
		
    }

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		
	
		if(keycode==Keys.W)
		{
			
			anmstatus=0;
			velocity.y = speed;
			
		}
		else if(keycode==Keys.S)
		{
		
			anmstatus=2;
			System.out.println("S");
			velocity.y = -speed;
		}
		if(keycode==Keys.A)
		{
			
			anmstatus=1;
			System.out.println("A");
			velocity.x = -speed;
		}
		else if(keycode==Keys.D)
		{
			
			anmstatus=3;
			System.out.println("D");
			velocity.x = speed;
		}
		
		idle=false;
		
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		
		
		if(keycode==Keys.A) {
			velocity.x=0;
		}
		else if(keycode==Keys.D) {
			velocity.x=0;
		
		}
		if(keycode==Keys.W) {
			velocity.y=0;
		}
		else if(keycode==Keys.S) {
			velocity.y=0;
			
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		if(count==1)
		{
			taped=(taped)? false:true;
		System.out.println("tapped "+taped);
		
		}
		return true;
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
//		System.out.println("panned");
		idle=false;
		if(temp==0)
		{
		
			val=x;
			valy=y;
			temp=1;
		
		
		}
		if(Math.abs(val-x)>Math.abs(valy-y))
		{
		if(val<x&&temp==1)
		{
			anmstatus=3;
			System.out.println("D");
			velocity.y = 0;
			taped=true;
			temp=0;
		}
		else if(val>x&&temp==1)
		{
			anmstatus=1;
			System.out.println("A");
			velocity.y = 0;
			taped=true;
			temp=0;
			//Systexm.out.println("set k "+k);
		}
		}
		else if(Math.abs(valy-y)>Math.abs(val-x))
		{
			if(valy<y&&temp==1)
			{
				anmstatus=0;
				System.out.println("s");
				taped=true;
				velocity.x = 0;
//				velocity.x = -speed;
				
				temp=0;
			}
			else if(valy>y&&temp==1)
			{
				anmstatus=2;
				System.out.println("w");
				velocity.x = 0;
				taped=true;
				temp=0;
				//Systexm.out.println("set k "+k);
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
	   

	 

	  

}
