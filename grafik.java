import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class grafik extends JPanel   {
	
		Column []c = new Column[5];
		int startpopulationsize=1000;

		Player []p = new Player[startpopulationsize];
		boolean selected=false;
		int populationsize=1000;
        BufferedImage img0 = null;
        BufferedImage img1 = null;
        BufferedImage img2 = null;
        int generation=0;
        int currentblock=0;
        int score=0;
        		

				void image_oku() {
					

                    try {
                        img0 = ImageIO.read(new File("java_genetik_algoritma_flappybird_ai\\pipe.png"));
                        img1 = ImageIO.read(new File("java_genetik_algoritma_flappybird_ai\\\\pipe_ters.png"));
                        img2 = ImageIO.read(new File("java_genetik_algoritma_flappybird_ai\\\\ghost0.png"));

                    } catch (IOException e) {
                    	System.out.print("x");
                    }
					
				}
		
	  			void sutun_ata() {

	  		    	for(int i=0;i<5;i++)
	  		    	{
	  		        	c[i]= new Column();
	  		        	c[i].posx=400*(i+1);
	  		        	c[i].getfirstpos();
	  		    	}	 
	  			}
	  			
	  			void player_ata()
	  			{
	  				for(int i=0;i<startpopulationsize;i++)
	  				{	
	  					p[i]= new Player();

	  					p[i].getfirstweights();
	  					p[i].getfirstpos();
	  					
	  				}
	  				
	  				
	  			}
	
                public void paintComponent(Graphics g){
                    super.paintComponent(g);
                    
                	carpisma_kontrol();
                	for(int i=0;i<startpopulationsize;i++)
                	{
                		if(p[i].kararver(c[currentblock].posx,c[currentblock].posy,c[currentblock].posy-c[currentblock].sizey-150)==1)
                		{
                			if(p[i].posy>0)
                				p[i].posy-=50;
                			
                		}
                	}	
                	
                	for(int i=0;i<5;i++)
                	{	
                		ciz_column(g,c[i].posx,c[i].posy,c[i].sizex,c[i].sizey);
                		if(c[i].posx==0-c[i].sizex)
                			{
                				c[i].posx=5*400;
                				c[i].getrandom_posy();
                			}
                		
                		if(c[i].posx==100)
                		{	currentblock++;
                			score++;
                			if(currentblock==5)
                				currentblock=0;
                		}		
                				
                		
                	    c[i].posx-=10;

                	    
                	}   
                	
                	for(int i=0;i<startpopulationsize;i++)
                	{	
                		
                		if(p[i].posy<550)
                		p[i].posy+=30;
                	}
                	
                	for(int i=0;i<startpopulationsize;i++)
                	{	
                		ciz_player(g,p[i].posx,p[i].posy,p[i].sizex,p[i].sizey);
                	}

                    g.setColor(Color.white);

                    g.setFont(new Font("TimesRoman", Font.PLAIN, 32)); 

                    String drawtext0="Jenerasyon:"+generation;
                    
                    String drawtext1="Hayatta kalan:"+populationsize;
                    
                    String drawtext2="Skor:"+score;    
                    
                    g.drawString(drawtext0, 1000, 50);
                    
                    g.drawString(drawtext1, 1000, 80);
                    
                    g.drawString(drawtext2, 1000, 110);
      	
                	sleep(50);
                    repaint();

                }
               
                void ciz_column(Graphics g , int posx , int posy , int sizex , int sizey){
                                                             

                    g.drawImage(img0, posx, posy,sizex,sizey, null);
    
                    g.drawImage(img1, posx, posy-sizey-150,sizex,sizey, null);
          	
                }
                
   
                void ciz_player(Graphics g , int posx , int posy , int sizex , int sizey){
                    

                    g.drawImage(img2, posx, posy,sizex,sizey, null);
          	
                }                
                void carpisma_kontrol() {
                	
                	for(int i=0;i<5;i++)
                	{	
                		for(int j=0;j<startpopulationsize;j++) 
                		{
	                		
                			
	                		if(p[j].posx>c[i].posx && p[j].posx<c[i].posx+c[i].sizex && p[j].posy>c[i].posy )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}	
	                		else if(p[j].posx+p[j].sizex>c[i].posx && p[j].posx+p[j].sizex<c[i].posx+c[i].sizex && p[j].posy>c[i].posy )
	                		{	
	                			populationsize-=1;
	                			p[j].dead();
	                		}
	                		else if(p[j].posx>c[i].posx && p[j].posx<c[i].posx+c[i].sizex && p[j].posy+p[j].sizey>c[i].posy )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}
	                		else if(p[j].posx+p[j].sizex>c[i].posx && p[j].posx+p[j].sizex<c[i].posx+c[i].sizex && p[j].posy+p[j].sizey>c[i].posy )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}
	                		if(p[j].posx>c[i].posx && p[j].posx<c[i].posx+c[i].sizex && p[j].posy<c[i].posy-150 )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}
	                		else if(p[j].posx+p[j].sizex>c[i].posx && p[j].posx+p[j].sizex<c[i].posx+c[i].sizex && p[j].posy<c[i].posy-150 )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}
	                		else if(p[j].posx>c[i].posx && p[j].posx<c[i].posx+c[i].sizex && p[j].posy+p[j].sizey<c[i].posy-150 )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}
	                		else if(p[j].posx+p[j].sizex>c[i].posx && p[j].posx+p[j].sizex<c[i].posx+c[i].sizex && p[j].posy+p[j].sizey<c[i].posy-150 )
	                		{
	                			populationsize-=1;
	                			p[j].dead();
	                		}
            				int [] parents = new int[2] ;

	                		if(selected==false&&populationsize==2)
	                		{
	                			selected=true;
                				int sira=0;
                				
	                			for(int k=0;k<startpopulationsize;k++)
	                			{
	                				if(p[k].isdead==false)
	                				{	
	                					parents[sira]=k;
	                					sira++;
	                				}
	                			}
	                			
	                		}
	                		if(populationsize==0)
	                			crossover(p[parents[0]].brain(),p[parents[1]].brain());

                		}	
                	}                       	
                	
                	
                }
                
                void crossover(double [] brain0,double [] brain1)
                {
                	Random rand = new Random();

                	p[0].update_gen(brain0);
                	p[1].update_gen(brain1);
                	
                	double [] crossbrain = new double[brain0.length];
                	
                	for(int i=2;i<startpopulationsize;i++)
                		
                	{
                		if(Math.random()>0.5f)
                		{	
	                		for(int j=0;j<brain0.length;j++)
		                	{
		                		if(Math.random()>0.1f)
		                		{
		                			crossbrain[j]=Math.random()*2-1;
		                		}
		                		else {
		                			crossbrain[j]=brain0[j];
		                		}
		                	}
                		}
                		else 
                		{
                			for(int j=0;j<brain1.length;j++)
		                	{
		                		if(Math.random()>0.1f)
		                		{
		                			crossbrain[j]=Math.random()*2-1;
		                		}
		                		else {
		                			crossbrain[j]=brain1[j];
		                		}
		                	}
                					
                		}
                		p[i].update_gen(crossbrain);

                	}  
                	
	  		    	for(int i=0;i<5;i++)
	  		    	{
	  		        	c[i].posx=400*(i+1);
	  		        	c[i].getfirstpos();
	  		    	}
	  		    	
	  				for(int i=0;i<startpopulationsize;i++)
	  				{	
	  					p[i].getfirstpos();
	  					
	  				}
	  				generation++;
	  				score=0;
	  		    	populationsize=startpopulationsize;
	  		    	selected=false;
	  		    	currentblock=0;
                }
                
                
                void sleep(int sn)
                {
                	try {
						Thread.sleep(sn);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
                }
                
            
                
                
                
                
}
























