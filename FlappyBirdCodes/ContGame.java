package FlappyBird;

public class ContGame implements Runnable {

	private final int PAUSE=5;  //ekran�n kayma h�z�n� ayarl�yoruz
	@Override
	public void run() {
		
		while(Main.screen.gameOver==false)
		{
	
            Main.screen.x--;  // groundun kaymas�n� sa�lar
			Main.screen.repaint();   //paint componentte gider.
		
			
			if(Main.screen.flappyBird.y==476) //476+34=500 ku� grounda �arpt�ysa break
            {
				Main.screen.gameOver=true;
				break;
            }
		try{///////////////////////////////////////bunu koymazsak ekran�n formu,h�z�,g�r�nt�s� bozuluyor
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
			   		Main.screen.flappyBird.y++;  // oyun bitti�inde ku� yere d��mesini sa�lar // y=476 oldu�unda yukar�da while break yapar ku� ilerlemez
			try{ 	
				Thread.sleep(1); //ku�un d��me h�z�=1
		       }
			catch(InterruptedException e)
			   {
			
			    break;	
			   }
			
		}	
		}
		

}

