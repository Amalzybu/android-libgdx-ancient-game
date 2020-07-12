package tools;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.dravianart.game.entities.FBDesiderObject;
import com.dravianart.game.entities.OrthoTree;

public class FrontBehindDesider {
	
	OrthoTree tree=null;
	FBDesiderObject[] obj=null;
	public FrontBehindDesider(OrthoTree tree,FBDesiderObject[] obj) {
		this.tree=tree;
		this.obj=obj;
		Arrays.sort(obj);
		
	}
	
	public void render(Batch batch) {
		
//		for(FBDesiderObject ob:obj) {
//			ob.render(batch, tree.position);
//			
//		}
		if(obj[0].y<tree.getY()) {
		tree.draw(batch, Gdx.graphics.getDeltaTime());
		for(int i=1;i<obj.length-1;i++) {
	
			obj[i].render(batch, tree.position);
		}
		}
		else if(obj[0].y>tree.getY()&&obj[obj.length-1].y<tree.getY()) {
			int c=0;
//			System.out.println("forloop"+obj[0].y+" end "+obj[obj.length-1].y+" tree "+tree.getY());
			
		for(int i=1;i<obj.length;i++) {
			
//		System.out.println("obj position "+obj[i].y);
		if(obj[i].y<tree.getY()&&c==0) {
			c=1;
			tree.draw(batch, Gdx.graphics.getDeltaTime());
		}
			obj[i].render(batch, tree.position);
		}
		
		}
	else {
		for(int i=1;i<obj.length-1;i++) {
			
			obj[i].render(batch, tree.position);
		}
		tree.draw(batch, Gdx.graphics.getDeltaTime());
	}
	}
	
	
	
	

}
