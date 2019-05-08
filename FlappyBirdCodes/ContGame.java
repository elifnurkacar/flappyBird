package FlappyBird;

public class ContGame implements Runnable {

	private final int PAUSE=5;  //ekranýn kayma hýzýný ayarlýyoruz
	@Override
	public void run() {
		
		while(Main.screen.gameOver==false)
		{
	
            Main.screen.x--;  // groundun kaymasýný saðlar
			Main.screen.repaint();   //paint componentte gider.
		
			
			if(Main.screen.flappyBird.y==476) //476+34=500 kuþ grounda çarptýysa break
            {
				Main.screen.gameOver=true;
				break;
            }
		try{///////////////////////////////////////bunu koymazsak ekranýn formu,hýzý,görüntüsü bozuluyor
			Thread.sleep(this.PAUSE);	
			}
			catch(InterruptedException e)
			{
				break;	
			}
	
		}
		
		while(Main.screen.gameOver==true)
		{
			
			if(Main.screen.flappyBird.y>=476)
                {
				    Main.screen.repaint();
					break;
                }
	                Main.screen.repaint();
			   		Main.screen.flappyBird.y++;  // oyun bittiðinde kuþ yere düþmesini saðlar // y=476 olduðunda yukarýda while break yapar kuþ ilerlemez
			try{ 	
				Thread.sleep(1); //kuþun düþme hýzý=1
		       }
			catch(InterruptedException e)
			   {
			
			    break;	
			   }
			
		}	
		}
		

}

