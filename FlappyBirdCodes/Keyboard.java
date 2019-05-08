package FlappyBird;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Keyboard implements KeyListener {
public static int sayac=0;
	FlapBird bird;
	@Override
	public void keyPressed(KeyEvent e) {
		
	  if(e.getKeyCode()==KeyEvent.VK_SPACE&& Main.screen.gameOver==false)  
	  {   sayac=1; // oyun baþlayýnca screende boru gelsin diye kontrol var
		  Main.screen.flappyBird.monte(); // kuþun sýçrayýþý
		 
	  }
	  if(e.getKeyCode()==KeyEvent.VK_SPACE&&Main.screen.gameOver==true)  ///////restartýn saðlandýðý yer
	  {
	   sayac=0;
	    Main.screen.flappyBird.setX(100);
	    Main.screen.flappyBird.setY(250);
	    Main.screen.x=0;
	    Main.screen.rand=new Random();
	    Main.screen.xPipe=550;
	    Main.screen.gameOver=false ;
	    Main.screen.score=0;
	    Main.screen.TopPipe1=new Pipe(Main.screen.xPipe,-120,"/imgg/pipe2.png");
	    Main.screen.DownPipe1=new Pipe(Main.screen.xPipe,350,"/imgg/pipe.png");
    	
	    Main.screen.TopPipe2=new Pipe(Main.screen.xPipe+200,-180,"/imgg/pipe2.png");///200=distance_pipes
	    Main.screen.DownPipe2=new Pipe(Main.screen.xPipe+200,290,"/imgg/pipe.png");
    	
	    Main.screen.TopPipe3=new Pipe(Main.screen.xPipe+200+200,-70,"/imgg/pipe2.png");//ilk+ 200 ilk boru ile ikinci için ikinci +200 ikinci boru ile üçüncü için
	    Main.screen.DownPipe3=new Pipe(Main.screen.xPipe+200+200,400,"/imgg/pipe.png");
	   
	    Main.screen.flappyBird=new FlapBird(180,250,"/imgg/bird-02.png");
	    
		Thread ContGameEcran=new Thread(new ContGame()); ////contgame e gider
    	ContGameEcran.start();
		
		
	  }
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
