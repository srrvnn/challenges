package bits;

public class MyBit
{
	// YOUR CODE GOES HERE 
	
	int var;
	
	MyBit(int value) {
		
		var = value;
	}
	
	public void setBit( int index )
	{
		if(index < 0 || index > 31) return; 
		
		var = var | (1 << index); 
	}
	
	public boolean isSet( int index )
	{
		
		if(index < 0 || index > 31) return false;
		
		int temp = var & (1 << index);
		
		if(temp == 0) return false;
		else return true;
	}
	
	public static void main(String args[])
	{
		
		MyBit b = new MyBit(2);
		
		for(int i = 0; i < 32; i++)
			System.out.println(i + " " + b.isSet(i));		
	}
}