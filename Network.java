import java.util.Random;

public class Network {

	public double [] input,hidden,output,input_w,hidden_w;
	int inputsize,hiddensize,outputsize;	
	
	public void setfirstweights()
	{
		Random rand = new Random();
		inputsize = 4;
		hiddensize = 10;
		outputsize = 2;
		input = new double[inputsize];
		hidden = new double[hiddensize];
		output = new double[outputsize];
		input_w = new double[inputsize * hiddensize];
		hidden_w = new double[hiddensize * outputsize];
		
		for(int i=0;i<inputsize;i++)
		{	
			for(int j=0;j<hiddensize;j++)
			{
			
				input_w[inputsize*j+i]=rand.nextDouble()-0.5f;

				
			}	
		}

		for(int i=0;i<hiddensize;i++)
		{	
			for(int j=0;j<outputsize;j++)
			{
			
				hidden_w[hiddensize*j+i]=rand.nextDouble()-0.5f;

				
			}	
		}

	
	
	}
	
	
	public void setinput(int posx,int posy,int posy2, int pposy)
	{
		input[0]=posx;
		input[1]=posy;
		input[2]=posy2;
		input[3]=pposy;


	}
	
	public void startnetwork()
	{
		resnetwork();
		for(int i=0;i<inputsize;i++)
		{	
			for(int j=0;j<hiddensize;j++)
			{
			
				hidden[j]+=input[i]*input_w[j*inputsize+i];

				
			}	
		}

		
		for(int j=0;j<hiddensize;j++)
		{
		
			
			//hidden[j]=sigmoid(hidden[j]);

			
		}	
		
		for(int i=0;i<hiddensize;i++)
		{	
			for(int j=0;j<outputsize;j++)
			{
			
				output[j]+=hidden[i]*hidden_w[j*hiddensize+i];

				
			}	
		}
		
		for(int j=0;j<outputsize;j++)
		{

			//output[j]=relu(output[j]);			
			
		}			
		
		
	}
	

	public void resnetwork()
	{
		for(int i=0;i<hiddensize;i++)
		{
		
			hidden[i]=0;
			
		}	
		
		for(int i=0;i<outputsize;i++)
		{
		
			output[i]=0;
			
		}	
		
	}
	
	
	private static double sigmoid(double x)
	{
	    return 1 / (1 + Math.exp(-x));
	}
	
	private static double relu(double x)
	{
		if(x<0)
			x=0;
		
	    return x;
	}
}

