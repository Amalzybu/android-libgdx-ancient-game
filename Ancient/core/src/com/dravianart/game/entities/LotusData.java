package com.dravianart.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class LotusData {

	public static FBDesiderObject[] arr;
	public ArrayList<float[]> temxy=new ArrayList<float[]>();
	int i=0;
	public LotusData(Texture lotus,TiledMapTileLayer collisionLayer) {
	//	arr=new FBDesiderObject[]{new FBDesiderObject(windflower, 1,1219,1095, 200, 300),new FBDesiderObject(windflower, 1,2520,2500, 200, 300),new FBDesiderObject(windflower, 1,2520,2550, 200, 300),new FBDesiderObject(windflower, 1,2520,2600, 200, 300),new FBDesiderObject(windflower, 1,2520,2650, 200, 300),new FBDesiderObject(windflower, 1,2520,2700, 200, 300),new FBDesiderObject(windflower, 1,2520,2750, 200, 300),new FBDesiderObject(windflower, 1,2520,2800, 200, 300),new FBDesiderObject(windflower, 1,2520,2850, 200, 300),new FBDesiderObject(windflower, 1,2580,2400, 200, 300),new FBDesiderObject(windflower, 1,2580,2450, 200, 300),new FBDesiderObject(windflower, 1,2580,2500, 200, 300),new FBDesiderObject(windflower, 1,2580,2550, 200, 300),new FBDesiderObject(windflower, 1,2580,2600, 200, 300),new FBDesiderObject(windflower, 1,2580,2650, 200, 300),new FBDesiderObject(windflower, 1,2580,2700, 200, 300),new FBDesiderObject(windflower, 1,2580,2750, 200, 300),new FBDesiderObject(windflower, 1,2580,2800, 200, 300),new FBDesiderObject(windflower, 1,2580,2850, 200, 300),new FBDesiderObject(windflower, 1,2640,2400, 200, 300),new FBDesiderObject(windflower, 1,2640,2450, 200, 300),new FBDesiderObject(windflower, 1,2640,2500, 200, 300),new FBDesiderObject(windflower, 1,2640,2550, 200, 300),new FBDesiderObject(windflower, 1,2640,2600, 200, 300),new FBDesiderObject(windflower, 1,2640,2650, 200, 300),new FBDesiderObject(windflower, 1,2640,2700, 200, 300),new FBDesiderObject(windflower, 1,2640,2750, 200, 300),new FBDesiderObject(windflower, 1,2640,2800, 200, 300),new FBDesiderObject(windflower, 1,2640,2850, 200, 300),new FBDesiderObject(windflower, 1,2640,2900, 200, 300),new FBDesiderObject(windflower, 1,2700,2400, 200, 300),new FBDesiderObject(windflower, 1,2700,2450, 200, 300),new FBDesiderObject(windflower, 1,2700,2500, 200, 300),new FBDesiderObject(windflower, 1,2700,2550, 200, 300),new FBDesiderObject(windflower, 1,2700,2600, 200, 300),new FBDesiderObject(windflower, 1,2700,2650, 200, 300),new FBDesiderObject(windflower, 1,2700,2700, 200, 300),new FBDesiderObject(windflower, 1,2700,2750, 200, 300),new FBDesiderObject(windflower, 1,2700,2800, 200, 300),new FBDesiderObject(windflower, 1,2700,2850, 200, 300),new FBDesiderObject(windflower, 1,2700,2900, 200, 300),new FBDesiderObject(windflower, 1,2760,2450, 200, 300),new FBDesiderObject(windflower, 1,2760,2500, 200, 300),new FBDesiderObject(windflower, 1,2760,2550, 200, 300),new FBDesiderObject(windflower, 1,2760,2600, 200, 300),new FBDesiderObject(windflower, 1,2760,2650, 200, 300),new FBDesiderObject(windflower, 1,2760,2700, 200, 300),new FBDesiderObject(windflower, 1,2760,2750, 200, 300),new FBDesiderObject(windflower, 1,2760,2800, 200, 300),new FBDesiderObject(windflower, 1,2760,2850, 200, 300),new FBDesiderObject(windflower, 1,2760,2900, 200, 300),new FBDesiderObject(windflower, 1,2820,2450, 200, 300),new FBDesiderObject(windflower, 1,2820,2500, 200, 300),new FBDesiderObject(windflower, 1,2820,2550, 200, 300),new FBDesiderObject(windflower, 1,2820,2600, 200, 300),new FBDesiderObject(windflower, 1,2820,2650, 200, 300),new FBDesiderObject(windflower, 1,2820,2700, 200, 300),new FBDesiderObject(windflower, 1,2820,2750, 200, 300),new FBDesiderObject(windflower, 1,2820,2800, 200, 300),new FBDesiderObject(windflower, 1,2820,2850, 200, 300),new FBDesiderObject(windflower, 1,2820,2900, 200, 300),new FBDesiderObject(windflower, 1,2880,2450, 200, 300),new FBDesiderObject(windflower, 1,2880,2500, 200, 300),new FBDesiderObject(windflower, 1,2880,2550, 200, 300),new FBDesiderObject(windflower, 1,2880,2600, 200, 300)};
		for(int x = 0; x < collisionLayer.getWidth(); x++) {
			for(int y = 0; y < collisionLayer.getHeight(); y++) {
				Cell cell = collisionLayer.getCell(x, y);
				if(cell!=null) {
				if(cell.getTile().getProperties().containsKey("lotus") )
				{
					i++;
				temxy.add(new float[]{x*32,y*32});
				System.out.println("new FBDesiderObject(Lotus, 1,"+x*30+","+y*30+", 200, 300),");
				}
				}
			}
		}
	System.out.println("number of flower "+i);
	arr=new FBDesiderObject[temxy.size()];
	System.out.println("number of flower "+arr.length);
		int j=0;
		for(float[] k:temxy) {
			if(j<temxy.size()) {
			arr[j]=new FBDesiderObject(lotus, 2,3,false, (int)k[0], (int)k[1], 75, 170);
			j++;
			}
		}
		System.out.println(" size of array is "+arr.length);
	}

}
