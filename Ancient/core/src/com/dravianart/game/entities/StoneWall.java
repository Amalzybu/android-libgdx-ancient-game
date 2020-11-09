package com.dravianart.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;

public class StoneWall {
	public ArrayList<float[]> temxy=new ArrayList<float[]>();
	int i=0;
	public static boolean closed=false,closedflag=true;
	float staticTime=0;
	Cell replacecell;
	TiledMapTileLayer bg;
	Animation anm;
	public StoneWall(Texture stone,TiledMapTileLayer bg) {
		this.bg=bg;
		//	arr=new FBDesiderObject[]{new FBDesiderObject(windflower, 1,1219,1095, 200, 300),new FBDesiderObject(windflower, 1,2520,2500, 200, 300),new FBDesiderObject(windflower, 1,2520,2550, 200, 300),new FBDesiderObject(windflower, 1,2520,2600, 200, 300),new FBDesiderObject(windflower, 1,2520,2650, 200, 300),new FBDesiderObject(windflower, 1,2520,2700, 200, 300),new FBDesiderObject(windflower, 1,2520,2750, 200, 300),new FBDesiderObject(windflower, 1,2520,2800, 200, 300),new FBDesiderObject(windflower, 1,2520,2850, 200, 300),new FBDesiderObject(windflower, 1,2580,2400, 200, 300),new FBDesiderObject(windflower, 1,2580,2450, 200, 300),new FBDesiderObject(windflower, 1,2580,2500, 200, 300),new FBDesiderObject(windflower, 1,2580,2550, 200, 300),new FBDesiderObject(windflower, 1,2580,2600, 200, 300),new FBDesiderObject(windflower, 1,2580,2650, 200, 300),new FBDesiderObject(windflower, 1,2580,2700, 200, 300),new FBDesiderObject(windflower, 1,2580,2750, 200, 300),new FBDesiderObject(windflower, 1,2580,2800, 200, 300),new FBDesiderObject(windflower, 1,2580,2850, 200, 300),new FBDesiderObject(windflower, 1,2640,2400, 200, 300),new FBDesiderObject(windflower, 1,2640,2450, 200, 300),new FBDesiderObject(windflower, 1,2640,2500, 200, 300),new FBDesiderObject(windflower, 1,2640,2550, 200, 300),new FBDesiderObject(windflower, 1,2640,2600, 200, 300),new FBDesiderObject(windflower, 1,2640,2650, 200, 300),new FBDesiderObject(windflower, 1,2640,2700, 200, 300),new FBDesiderObject(windflower, 1,2640,2750, 200, 300),new FBDesiderObject(windflower, 1,2640,2800, 200, 300),new FBDesiderObject(windflower, 1,2640,2850, 200, 300),new FBDesiderObject(windflower, 1,2640,2900, 200, 300),new FBDesiderObject(windflower, 1,2700,2400, 200, 300),new FBDesiderObject(windflower, 1,2700,2450, 200, 300),new FBDesiderObject(windflower, 1,2700,2500, 200, 300),new FBDesiderObject(windflower, 1,2700,2550, 200, 300),new FBDesiderObject(windflower, 1,2700,2600, 200, 300),new FBDesiderObject(windflower, 1,2700,2650, 200, 300),new FBDesiderObject(windflower, 1,2700,2700, 200, 300),new FBDesiderObject(windflower, 1,2700,2750, 200, 300),new FBDesiderObject(windflower, 1,2700,2800, 200, 300),new FBDesiderObject(windflower, 1,2700,2850, 200, 300),new FBDesiderObject(windflower, 1,2700,2900, 200, 300),new FBDesiderObject(windflower, 1,2760,2450, 200, 300),new FBDesiderObject(windflower, 1,2760,2500, 200, 300),new FBDesiderObject(windflower, 1,2760,2550, 200, 300),new FBDesiderObject(windflower, 1,2760,2600, 200, 300),new FBDesiderObject(windflower, 1,2760,2650, 200, 300),new FBDesiderObject(windflower, 1,2760,2700, 200, 300),new FBDesiderObject(windflower, 1,2760,2750, 200, 300),new FBDesiderObject(windflower, 1,2760,2800, 200, 300),new FBDesiderObject(windflower, 1,2760,2850, 200, 300),new FBDesiderObject(windflower, 1,2760,2900, 200, 300),new FBDesiderObject(windflower, 1,2820,2450, 200, 300),new FBDesiderObject(windflower, 1,2820,2500, 200, 300),new FBDesiderObject(windflower, 1,2820,2550, 200, 300),new FBDesiderObject(windflower, 1,2820,2600, 200, 300),new FBDesiderObject(windflower, 1,2820,2650, 200, 300),new FBDesiderObject(windflower, 1,2820,2700, 200, 300),new FBDesiderObject(windflower, 1,2820,2750, 200, 300),new FBDesiderObject(windflower, 1,2820,2800, 200, 300),new FBDesiderObject(windflower, 1,2820,2850, 200, 300),new FBDesiderObject(windflower, 1,2820,2900, 200, 300),new FBDesiderObject(windflower, 1,2880,2450, 200, 300),new FBDesiderObject(windflower, 1,2880,2500, 200, 300),new FBDesiderObject(windflower, 1,2880,2550, 200, 300),new FBDesiderObject(windflower, 1,2880,2600, 200, 300)};
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			TextureRegion[][] rollSpriteSheet = TextureRegion.split(stone, 33, 33);
			anm=new Animation(1f, new Array<TextureRegion>(rollSpriteSheet[0]), PlayMode.NORMAL);
			replacecell=bg.getCell(12, 96);
			
			for(int x = 0; x < bg.getWidth(); x++) {
				for(int y = 0; y < bg.getHeight(); y++) {
					Cell cell = bg.getCell(x, y);
					
					if(cell!=null) {
					if(cell.getTile().getProperties().containsKey("stonewall") )
					{
					
					temxy.add(new float[]{x*32,y*32});
//					System.out.println("new stone(windflower, 1,"+x*30+","+y*30+", 200, 300),");
					}
					}
				}
			}
//		System.out.println("number of flower "+i);
			int j=0;
			
		}
	
	public void render(Batch batch) {
		staticTime+=Gdx.graphics.getDeltaTime();
		
//		System.out.println("stonr");
		if(closed){
			if(closedflag) {
				for(float[] tmp:temxy) {
					bg.setCell((int)tmp[0]/32, (int)tmp[1]/32, replacecell);
				}
				closedflag=false;
			}
		for(float[] tmp:temxy) {
			batch.draw((TextureRegion) anm.getKeyFrame(staticTime), tmp[0],tmp[1],33,33);
		}
		}
		else {
			
			for(float[] tmp:temxy) {
				batch.draw((TextureRegion) anm.getKeyFrame(0), tmp[0],tmp[1],33,33);
			}
		}
//		batch.draw(replacecell.getTile().getTextureRegion(), 1000, 400, 1000, 1000);
		
		
		
	}

}
