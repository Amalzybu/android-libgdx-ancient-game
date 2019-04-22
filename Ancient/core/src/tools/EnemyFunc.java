package tools;

import com.dravianart.game.entities.Tree;

public class EnemyFunc {
	public int life=0,prev=0;
	Crect en;
	public boolean alive=true;
	Tree t;
	

	public EnemyFunc(int life,Crect en,Tree t)
	{
		this.en=en;
		this.life=life;
		this.t=t;
		
	}
	public void decLife()
	{
		life--;
		//if(life==0)
		//{
		//	alive=false;
		//}
	}
	public void checkDmg()
	{
		//System.out.println("count "+t.spcount);
		if(en.isCollided(t.r)&&t.sprint==1&&prev!=t.spcount)
		{
			prev=t.spcount;
			decLife();
			//System.out.println("damaged");
		}
	}
	public void checkHeroDmg()
	{
		if(en.isCollided(t.r)&&t.sprint==0&&!t.npain)
		{
			t.painjmp=true;
			t.npain=true;
			t.life--;
			if(t.pos==0)
			{
			t.esj=t.x-100;
			}
			else
			{
				t.esj=t.x+100;
			}
		}
	}
}
