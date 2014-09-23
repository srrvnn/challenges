package decoding;

public class Main
{
	public static void main( String[] args )
	{
		Frame frame = new Frame( 20 , 10 , 50 );
		
		for ( int y = 0; y<10; y++ )
		{
			for( int x = 0; x<20; x++ )
			{
				frame.decodeBlock( x , y );
				frame.print();
			}
		}
		
		frame.reset();
		frame.decodeBlock( 0 , 0 );
		frame.print();
		waitFor(400);
		frame.decodeBlock( 1 , 0 );
		frame.print();
		waitFor(400);
		frame.decodeBlock( 0 , 1 );
		frame.print();
		waitFor(400);
	}
	
	private static void waitFor( int delay )
	{
		try
		{
			Thread.sleep( delay );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
