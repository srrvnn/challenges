package decoding;

public class Frame
{
	private boolean[][] blocks;
	private int sizeX;
	private int sizeY;
	private int delay;
	
	public Frame( int x , int y , int delay )
	{
		blocks = new boolean[x][y];
		this.sizeX = x;
		this.sizeY = y;
		this.delay = delay;
	}
	
	public void decodeBlock( int x , int y )
	{
		int dependents[][] = { {-1,-1} , {0 , -1} , {1 , -1} , { -1 , 0}};
		
		for ( int i=0; i < dependents.length; i++)
		{
			int[] neighbor = dependents[i];
			int cx = x + neighbor[0];
			int cy = y + neighbor[1];
			
			if (cx >=0 && cx < sizeX && cy >= 0 && cy < sizeY )
			{
				if ( !blocks[cx][cy])
				{
					throw new RuntimeException("Cannot decode " + x+","+y+" before decoding " + cx + "," + cy );
				}
			}
		}
		
		try
		{
			Thread.sleep( (int)(Math.random()*delay) );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		blocks[x][y] = true;
	}
	
	public void print()
	{
		for (int y=0; y<sizeY; y++)
		{
			for (int x=0; x<sizeX; x++)
			{
				if (blocks[x][y])
					System.out.print('x');
				else
					System.out.print('_');
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void reset()
	{
		blocks = new boolean[sizeX][sizeY];
	}
}
