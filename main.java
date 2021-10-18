import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;


public class main {

	public static void main(String[] args) {

		
       grafik bs = new grafik();
       Color mycolor = new Color(111, 90, 85);
       bs.setBackground(mycolor);  
       bs.image_oku();
       bs.sutun_ata();
       bs.player_ata();
       JFrame jf = new JFrame("java genetik algoritma");
       jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
       jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       jf.setUndecorated(true);       

       jf.setVisible(true);


       jf.add(bs); 

       
		
		

	}

	void dnm() {
		
	}


	
	
	
}




class Column {
 
 public int posx,posy,sizex,sizey;
 Random rand = new Random();


 void getfirstpos() {

     posy=rand.nextInt(500)+50;
     sizex = 20;
     sizey = 1000;
     

 }

 

 void getrandom_posy() {

     
	 posy = rand.nextInt(500)+50;
     

 }


}	 

class Player {
    
	 public int posx,posy,sizex,sizey,score;
	 Random rand = new Random();
	 Network net = new Network();
	 
	 boolean isdead;

	void dead()
	{
		isdead=true;
		posy=-99999999;
		posx=-99999999;
	}
	 void getfirstweights() {
		 net.setfirstweights();

	 }
	 void getfirstpos() {
		 isdead=false;
		 net.setfirstweights();
	     posx=  100;
		 posy = rand.nextInt(525)+50;
	     sizex = 25;
	     sizey = 25;
	     

	 }
	 
	 int kararver(int possx,int possy, int possy2)
	 {
		 
		 net.setinput(possx, possy, possy2  ,posy);
		 net.startnetwork();
		 //System.out.println(net.output[0]+" "+net.output[1]);
		 if(net.output[0]>net.output[1])
			 return 1;
		 net.resnetwork();
		 return 0;
	 }
	 
	 void update_gen(double[] newbrain){
		 
		 double brain_data[] = new double[net.inputsize*net.hiddensize +  net.hiddensize*net.outputsize];
			for(int i=0;i<net.inputsize;i++)
			{	
				for(int j=0;j<net.hiddensize;j++)
				{
				
					net.input_w[net.inputsize*j+i]=newbrain[net.inputsize*j+i];

					
				}	
			}

			for(int i=0;i<net.hiddensize;i++)
			{	
				for(int j=0;j<net.outputsize-1;j++)
				{
				
					net.hidden_w[net.hiddensize*j+i]=newbrain[net.inputsize*net.hiddensize+(net.hiddensize*j+i)];
					
				}	
			}		 
		 
		 
		 
	 }
		 
	 double[] brain()
	 {
		 double brain_data[] = new double[net.inputsize*net.hiddensize +  net.hiddensize*net.outputsize];
			for(int i=0;i<net.inputsize;i++)
			{	
				for(int j=0;j<net.hiddensize;j++)
				{
				
					brain_data[net.inputsize*j+i]=net.input_w[net.inputsize*j+i];

					
				}	
			}

			for(int i=0;i<net.hiddensize;i++)
			{	
				for(int j=0;j<net.outputsize-1;j++)
				{
				
					brain_data[net.inputsize*net.hiddensize+(net.hiddensize*j+i)]=net.hidden_w[net.hiddensize*j+i];
					
				}	
			}
		 return brain_data;
	 }
	 
	
}
	
