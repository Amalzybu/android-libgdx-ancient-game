package tools;

import java.util.Vector;

public class Gravity {
	public boolean jmp=false,sprint=false;
	public float x,y,limit=60,h=0,i=0,up=0,fx=0;
	public Crect r;
	public int pos;
	public float pull(float delta,Vector<Crect> gr)
	{ 
		
		h=0;
		i=0;
		limit=-500;
		
		for(Crect g:gr)
		{
			if(r.isCollided(g))
			{
				if(r.vCollid(g))
				{
					//System.out.println("collided  "+i+" up "+up);
					up=g.y-r.height;
					
				}
				else
				{
					
				//System.out.println("collided  "+i+" changed up "+up);
				if(!jmp)
				{
				up=Math.round(g.y+g.height+200);
				}
				limit=Math.round(g.y+g.height);
				h=g.y;
					
				}
				
			}
			
			i++;
			//System.out.println("h "+h);
			
			
			
		}
		float k=0;
		if(y>=limit)
		{
		k=delta*500;
		}
		return k;
	}
	public boolean block(Vector<Crect> gr,int a)
	{
	
		boolean b=true;
		if(a==0)
		{
		for(Crect g:gr)
		{
			if(r.hCollid(g))
			{
				//System.out.println("blocked");
				fx=g.x-r.width;
				b=false;
				break;
			}
		}
		}
		else
		{
			for(Crect g:gr)
			{
				if(r.lCollid(g))
				{
					//System.out.println("blocked");
					fx=g.x+g.width;
					b=false;
					break;
				}
			}
		}
	//	System.out.println("blocked "+b);
		return b;
	}

}
