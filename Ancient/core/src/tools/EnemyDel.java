package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dravianart.game.entities.EnemyOne;
import com.dravianart.game.entities.EnemyTwo;
import com.dravianart.game.entities.Floor;
import com.dravianart.game.entities.Tree;

public class EnemyDel {
	
	ArrayList<EnemyOne> a;
	List<EnemyTwo> atwo;
	
	public EnemyDel()
	{
		
		a=new ArrayList<EnemyOne>();
		
		atwo=new  ArrayList<EnemyTwo>();
	}
	
	public void deleteEnemy(ArrayList<EnemyOne> e,EnemyOne o)
	{
		for(EnemyOne g:e)
		{
			if(!g.ef.alive)
			{
			
				a.add(g);
			}
		}
		e.removeAll(a);
		
	}
	
	public void deleteEnemy(ArrayList<EnemyTwo> e,EnemyTwo o)
	{
		for(EnemyTwo g:e)
		{
			if(!g.ef.alive)
			{
			
				atwo.add(g);
			}
		}
		e.removeAll(atwo);
		
	}
	

}
