package job;

import java.util.ArrayList;


// make any necessary modification to make this class a thread that creates a job and pushes it to the arraylist.  
public class Producer extends Thread
{
	ArrayList<Job> jobs;
	
	public Producer(ArrayList<Job> jobs)
	{
		this.jobs = jobs;		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			
			Job j = new Job();
			
			synchronized(jobs) {
				
				jobs.add(j);
			}		
		}
	}	
}