package FlappyBird;


import java.awt.*;

import javax.swing.*;
public class FlapBird implements Runnable {

	private int weight;
	private int height;
	private int x;
	public int y;
	public int dy;
	private  String strImage;
	public ImageIcon icon;
	public Image image;
	private final int PAUSE=8;// kuşun zıplarkenki tutukluluğunu önleyen hız
	
	public FlapBird(int x,int y,String strImage) {
		this.weight=34;
		this.height=24;
		this.x=x;
		this.y=y;
		this.strImage=strImage;//resim yolu
		this.icon=new ImageIcon(getClass().getResource(this.strImage));
		this.image=this.icon.getImage();
		Thread flaps=new Thread(this);
		flaps.start();
	}
	
	public void setY(int y) {  this.y = y; }
	
	public void setX(int x) {  this.x = x;  }
	
	public int getY() {  return y;  }
	
	public int getX() { return x; }
	
	public int getWeight() {  return weight;  }
	
	public int getHeight() {  return height; }
	
	public Image getImage() {  return image;  }

	public void monte()///kuşun yükselmesi
	{ 
		this.dy=40;
		this.y=this.y-this.dy;
		
		
	}
	public void birdFlaps(int dy)  // tek bir zıplayıştaki düzgün akışı sağlıyor 
	{
		
		if(dy>10)
		{
			this.y=this.y-3;//kuþ füzeye dönüþtü :D çok zýplýyor
			Main.screen.flappyBird.icon=new ImageIcon(getClass().getResource("/imgg/bird-03.png"));
			Main.screen.flappyBird.image=Main.screen.flappyBird.icon.getImage();
		}
		else if(dy>5){this.y=this.y-2;}
		else if(dy>1){this.y=this.y-1;}
		else if(dy==1){
			Main.screen.flappyBird.icon=new ImageIcon(getClass().getResource("/imgg/bird-01.png"));
			Main.screen.flappyBird.image=Main.screen.flappyBird.icon.getImage();
	    
        }
	}
	public boolean collision(Pipe pipe)
	{
		if(pipe.getY()<100) //yani üst boruysa
		{
			
			if(this.y<=pipe.getY()+pipe.getHeight() && this.x + this.weight>=pipe.getX()+10
					&& this.x<=pipe.getX()+pipe.getWeight())//üst boru için borunun sınırlarına girmiş mi
				                                                             //ilk koşul: kuş borudan yukarda mı (kuş.y<boru.y)
				                                                             //ikinci koşul: borunun sol duvarından ilerde mi
				                                                          // üçüncü koşul : borunun sağ duvarından geride mi
			{return true;}
			else 
			return false;
			
		}
		else 
		{
			if(this.y + this.height>=pipe.getY()&&this.x+this.weight>=pipe.getX()+10
					&&this.x<=pipe.getX()+pipe.getWeight())//alt boru için borunun sınırlarına girmiş mi
                                                                        //ilk koşul: kuş borudan aşağıda mı (kuş.y>boru.y)
                                                                         //ikinci koşul: borunun sol duvarından ilerde mi
                                                                       // üçüncü koşul : borunun sağ duvarından geride mi
				{return true;}
			else 
			return false;
		}
		
	}
	public void run()
	{
		while(true)
		{
			this.birdFlaps(dy); 
			this.dy--;   
			
			try
			{
				Thread.sleep(PAUSE);
			}
			catch(InterruptedException e)
			{
				//e.printStackTrace();
			}
		}
		
	}
	
}



