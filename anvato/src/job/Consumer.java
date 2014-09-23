package job;



import java.util.ArrayList;


// any necessary modifications and make this class a thread that takes a job from the arraylist and consumes it.  
public class Consumer extends Thread
{
	ArrayList<Job> jobs;
	
	public Consumer(ArrayList<Job> jobs)
	{
		this.jobs = jobs; 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		
		while(true) {
			
			if(jobs.size() < 1)
				continue;
			
			Job j = null; 
			
			synchronized(jobs) {
			
				j = jobs.get(0);
				jobs.remove(j);
			}
				
			j.consume();				
		}
	}
}
