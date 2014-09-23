package job;

public class Job
{
	static int counter = 0;
	private int id;
	
	public Job()
	{
		id = counter;
		counter++;
		
		try
		{
			Thread.sleep( (int) (Math.random() * (1000)) );
		}
		catch (InterruptedException e){}
		
		System.out.println("Job created: "+id);
	}
	
	public void consume()
	{
		try
		{
			Thread.sleep( (int) (Math.random() * (800)) );
		}
		catch (InterruptedException e){}
		
		System.out.println("Job consumed: "+id);
	}
}