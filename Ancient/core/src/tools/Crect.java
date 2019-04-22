package tools;

public class Crect {
	public float x,y,width,height;
	public Crect(float x,float y,float x1,float y1)
	{
		
		this.x=x;
		this.y=y;
		width=x1;
		height=y1;
	}
	public void move(float x,float y)
	{
		this.x=x;
		this.y=y;
		
	}
	
	public boolean isCollided(Crect r)
	{
		//System.out.println("check "+(x<r.x+r.width && y<r.y+r.height && x+width>r.x && y+height >r.y));
		return (x<r.x+r.width && y<r.y+r.height && x+width>r.x && y+height >r.y);
	}
	public boolean vCollid(Crect r)
	{
		return (x<r.x+r.width && y+height>=r.y && x+width>r.x && y<r.y);
	}
	public boolean hCollid(Crect r)
	{
		return (x+width<r.x+r.width &&  x+width>r.x && y+height<r.y+r.height&&y+height>r.y);
	}
	public boolean lCollid(Crect r)
	{
		return (x<r.x+r.width &&  x>r.x && y+height<r.y+r.height&&y+height>r.y);
	}
	public boolean upHcollid(Crect r)
	{
		if(isCollided(r)&&y<r.y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
