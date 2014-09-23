package job;

import java.util.ArrayList;
import java.util.Scanner;


public class Main
{
	public static void main( String[] args )
	{
		ArrayList<Job> jobs = new ArrayList<Job>();
		jobs.add( new Job() );
		jobs.add( new Job() );
		jobs.add( new Job() );
		

		// modify the construct if needed.
		Consumer consumer = new Consumer( jobs );
		Producer producer = new Producer( jobs );
		
		producer.start();
		consumer.start();

		// start?
		Scanner sc = new Scanner( System.in );

		String s;
		do
		{
			s = sc.next();
			
			// code here to stop producer and exit.
			// code here to stop consumer once the queue is empty
		} while (!s.equals( "exit" ));

		// anything to finalize the program goes here.
		
		sc.close();
	}
}