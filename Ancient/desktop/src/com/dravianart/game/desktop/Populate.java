package com.dravianart.game.desktop;

import java.util.ArrayList;

import com.dravianart.game.entities.FBDesiderObject;

public class Populate {
	static ArrayList<float[]> am=new ArrayList<float[]>();
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		for(int i=0;i<10;i++) {
//			for(int y=0;y<10;y++) {
//			System.out.print("new FBDesiderObject(windflower, 1,"+(705+(i*40))+","+(900+(y*50))+", 200, 300),");
//			}
//			System.out.println();
//		}
//
//	}
	
	public static void DrawMeACircle(float h, float k, float r) {
		float x,y;
		 for (int i = 0; i < 45; i++)
		    {
		    x = (float) (r * Math.cos(i) - h);
		    y = (float) (r * Math.sin(i) + k);
		    float[] a={x,y};
		   
		   am.add(a);
		    }
	}

	public static void main(String[] args){
		int i=2;
		while(i++<5)
	    DrawMeACircle(i+(i/1.5f),i+(i/1.5f),(i/1.5f));
		
		
		for(float[] y:am) {
			System.out.print("new FBDesiderObject(windflower, 1,"+(int)(1200+(y[0]*60))+","+(int)(900+(y[1]*50))+", 200, 300),");
			}
	}

}
