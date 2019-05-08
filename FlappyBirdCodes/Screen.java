package FlappyBird;
import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Screen extends JPanel {
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	private Image image;           //background
	
	private ImageIcon icon2;
	private Image image2;          //ground
	
    private ImageIcon icon3;
    private Image skor;            //score
    
    private ImageIcon icon14;
	private Image restart;        //restart
	
	private ImageIcon icon4;
	private ImageIcon icon5;
	private ImageIcon icon6;
	private ImageIcon icon7;
	private ImageIcon icon8;
	private ImageIcon icon9;
	private ImageIcon icon10;
	private ImageIcon icon11;
	private ImageIcon icon12;
	private ImageIcon icon13;
	private Image num0;
	private Image num1;
	private Image num2;
	private Image num3;
	private Image num4;
	private Image num5;
	private Image num6;
	private Image num7;
	private Image num8;
	private Image num9;       // numaralar
	
    public  int score; //ekranda artan skor
	private  int k;    //tempi tutuyoruz
	private  int temp; //best scoru tutuyor
	
	public Pipe TopPipe1;
	public Pipe DownPipe1;
	public Pipe TopPipe2;
	public Pipe DownPipe2;
	public Pipe TopPipe3;
	public Pipe DownPipe3;    //ilk üç boru
	
	public Pipe pipe;     // kuþ çarptý mý çarmadý mý kontrolü yaptýðýmýz boru
	
	public int x;         //groundun x ekseninde kaymasýný yapcak
	
	public int xPipe;     // kuþun baþta borulara olan uzaklýðý
	
	public FlapBird flappyBird;    // kuþ
    
	private final int weight=142;   //  groundun devamlýlýðý için
	private final int Distance_pipes=200;  // sað-sol iki boru arasýndaki mesafe
	private final int Distance2_pipes=170;  // top ve down boru arasýndaki mesafe
	
	public boolean gameOver;   // oyun bitti mi(true) bitmedi mi(false)
	
	public Random rand;	// borularýn uzunluðunun random olmasý için

    public Screen()   //constructer
    {
    	
    	super();    //üst sýnýfýn constructerýna ulaþýr (jPanel)
    	this.icon=new ImageIcon(getClass().getResource("/imgg/background.png"));
    	this.image=this.icon.getImage();
    	
    	this.icon2=new ImageIcon(getClass().getResource("/imgg/Groundd.png"));
    	this.image2=this.icon2.getImage();
    	
    	this.icon3=new ImageIcon(getClass().getResource("/imgg/score.png"));
    	this.skor=this.icon3.getImage();
    	
    	this.icon14=new ImageIcon(getClass().getResource("/imgg/restart.png"));
    	this.restart=this.icon14.getImage();    
    	
    	this.icon4=new ImageIcon(getClass().getResource("/imgg/num_0.png"));
    	this.num0=this.icon4.getImage();
    	this.icon5=new ImageIcon(getClass().getResource("/imgg/num_1.png"));
    	this.num1=this.icon5.getImage();
    	this.icon6=new ImageIcon(getClass().getResource("/imgg/num_2.png"));
    	this.num2=this.icon6.getImage();
    	this.icon7=new ImageIcon(getClass().getResource("/imgg/num_3.png"));
    	this.num3=this.icon7.getImage();
    	this.icon8=new ImageIcon(getClass().getResource("/imgg/num_4.png"));
    	this.num4=this.icon8.getImage();
    	this.icon9=new ImageIcon(getClass().getResource("/imgg/num_5.png"));
    	this.num5=this.icon9.getImage();
    	this.icon10=new ImageIcon(getClass().getResource("/imgg/num_6.png"));
    	this.num6=this.icon10.getImage();
    	this.icon11=new ImageIcon(getClass().getResource("/imgg/num_7.png"));
    	this.num7=this.icon11.getImage();
    	this.icon12=new ImageIcon(getClass().getResource("/imgg/num_8.png"));
    	this.num8=this.icon12.getImage();
    	this.icon13=new ImageIcon(getClass().getResource("/imgg/num_9.png"));    	
    	this.num9=this.icon13.getImage();
    	
	    this.score=0;  //sýfýrdan baþla
    	this.x=0;  //ground
    	this.xPipe=550;   //baþtaki mesafe
    	this.gameOver=false;
    
    	this.TopPipe1=new Pipe(this.xPipe,-120,"/imgg/pipe2.png");
    	this.DownPipe1=new Pipe(this.xPipe,350,"/imgg/pipe.png");
    	
    	this.TopPipe2=new Pipe(this.xPipe+this.Distance_pipes,-180,"/imgg/pipe2.png");
    	this.DownPipe2=new Pipe(this.xPipe+this.Distance_pipes,290,"/imgg/pipe.png");
    	
    	this.TopPipe3=new Pipe(this.xPipe+this.Distance_pipes+200,-70,"/imgg/pipe2.png");
    	this.DownPipe3=new Pipe(this.xPipe+this.Distance_pipes+200,400,"/imgg/pipe.png");
    
    	this.flappyBird=new FlapBird(180,250,"/imgg/bird-02.png"); ///////////////////////kuþun koordinatý 180.250
    	
    	rand=new Random(); //boru boyu için
    	
    	this.setFocusable(true);
    	this.requestFocusInWindow(); //panele odaklanýyor
    	
    	this.addKeyListener(new Keyboard());   
    	
    	Thread ContGameEcran=new Thread(new ContGame());  //thread akýþý saðlýyor   //contgame e gitti
    	ContGameEcran.start();    // oyun baþlar
    	
    }
    
    ///////////////////// background oluþturup zemin kaydýrma
    private void deplacementFond(Graphics g)
    {
    	if(this.x==-this.weight)
    	{
    		this.x=0;
    	}
    	g.drawImage(this.image, 0, 0, null);//background
    	
    	g.drawImage(this.image2,x,500, null);//ground
    	g.drawImage(this.image2,x+142*2,500, null);
    	g.drawImage(this.image2,x+142*3,500, null);
    	
    }
    //////////////////////// kuþun düþtüðü görüntülenir
    public void deplacementBird(Graphics g)
    {
    
    	this.flappyBird=new FlapBird(flappyBird.getX(),flappyBird.getY(),"/imgg/bird-down.png");
	    g.drawImage(this.flappyBird.getImage(),this.flappyBird.getX() ,this.flappyBird.getY(),null);
	 }
  //////////////////////////borularýn devamlýlýðý saðlanýyor
    private void deplacementPipe(Graphics g)
    {
    	if(gameOver==false)// bu kontrolleri koymadýðýmýzda kuþ yere düþerken boru gelmesini durduramýyoruz
    	{	this.TopPipe1.setX(this.TopPipe1.getX()-1);       //-1 borunun x ekseninde kaymasýný saðlar 
    	    this.DownPipe1.setX(this.TopPipe1.getX());}
    	
    	if(this.TopPipe1.getX()==-100)  //ilk boru ekrandan çýkýnca(-100) saðdan yeni borunun gelmesini saðlar
    	{
    		//giden  1.borunun x ine = 3. borunun koordinatýna iki boru arasýndaki mesafeyi ekleyip koyar
    		this.TopPipe1.setX(this.TopPipe3.getX()+this.Distance_pipes); 
    		this.TopPipe1.setY(-100-10*this.rand.nextInt(18)); //-100 borunun boyunu üstten kýsaltýr ekranda kalan random olur
    		this.DownPipe1.setY(this.TopPipe1.getY()+this.TopPipe1.getHeight()+this.Distance2_pipes);
    		//// eksi deðerli gety yi +olan heigth(sabit:300) ve mesafe ile toplayýp alt borunun ysini hesaplýyor
    	}
    	//hesaplara göre boruyu çizdik
    	g.drawImage(this.TopPipe1.getImage(), this.TopPipe1.getX(),this.TopPipe1.getY(),null);
    	g.drawImage(this.DownPipe1.getImage(), this.DownPipe1.getX(),this.DownPipe1.getY(),null);
    	
    	//pipe 2
    	
    	if(gameOver==false)   // bu kontrolleri koymadýðýmýzda kuþ yere düþerken boru gelmesini durduramýyoruz
    	{	this.TopPipe2.setX(this.TopPipe2.getX()-1);
    	this.DownPipe2.setX(this.TopPipe2.getX());}
    
    	if(this.TopPipe2.getX()==-100)
    	{
    		this.TopPipe2.setX(this.TopPipe1.getX()+this.Distance_pipes);
    		this.TopPipe2.setY(-100-10*this.rand.nextInt(18));
    		this.DownPipe2.setY(this.TopPipe2.getY()+this.TopPipe2.getHeight()+this.Distance2_pipes);
    	}
    	g.drawImage(this.TopPipe2.getImage(), this.TopPipe2.getX(),this.TopPipe2.getY(),null);
    	g.drawImage(this.DownPipe2.getImage(), this.DownPipe2.getX(),this.DownPipe2.getY(),null);
   	    //pipe 3
    	if(gameOver==false)// bu kontrolleri koymadýðýmýzda kuþ yere düþerken boru gelmesini durduramýyoruz
    	{	this.TopPipe3.setX(this.TopPipe3.getX()-1);
    	this.DownPipe3.setX(this.TopPipe3.getX());}
    
    	if(this.TopPipe3.getX()==-100)
    	{
    		this.TopPipe3.setX(this.TopPipe2.getX()+this.Distance_pipes);
    		this.TopPipe3.setY(-100-10*this.rand.nextInt(18));
    		this.DownPipe3.setY(this.TopPipe3.getY()+this.TopPipe3.getHeight()+this.Distance2_pipes);
    	}
    	g.drawImage(this.TopPipe3.getImage(), this.TopPipe3.getX(),this.TopPipe3.getY(),null);
    	g.drawImage(this.DownPipe3.getImage(), this.DownPipe3.getX(),this.DownPipe3.getY(),null);
     
    	//borular groundun altna alma!!!!!!
    	g.drawImage(this.image2,x,500, null);
    	g.drawImage(this.image2,x+142*2,500, null);
    	g.drawImage(this.image2,x+142*3,500, null);
   
    }
    
    /// kuþun çarpmasý
    public boolean collisionFlappy()
    {
    	boolean gameOver=false;
    	if(this.flappyBird.getX()+this.flappyBird.getWeight()>this.DownPipe1.getX()
    			&&this.flappyBird.getX()<this.DownPipe1.getX()+this.DownPipe1.getWeight())//kuþ ilk alt borunun yakýnýnda mý kontrolü
    	{
    		gameOver=this.flappyBird.collision(this.DownPipe1); //collisiondan alt boruya çarptý mý çarpmadý mý bilgisi geldi
    		if(gameOver==false)
    		{
    			gameOver=this.flappyBird.collision(this.TopPipe1);//collisiondan üst boruya çarptý mý çarpmadý mý bilgisi geldi
    		}
    	}
    	else
    	{
    		if(this.flappyBird.getX()+this.flappyBird.getWeight()>this.DownPipe2.getX()//kuþ 2. alt borunun yakýnýnda mý kontrolü
        			&&this.flappyBird.getX()<this.DownPipe2.getX()+this.DownPipe2.getWeight())
        	{
        		gameOver=this.flappyBird.collision(this.DownPipe2);//collisiondan alt boruya çarptý mý çarpmadý mý bilgisi geldi
        		
        		if(gameOver==false)
        		{
        			gameOver=this.flappyBird.collision(this.TopPipe2);//collisiondan üst boruya çarptý mý çarpmadý mý bilgisi geldi
        		}
        	}
    		
    	
    	   else
    	   {
    		   if(this.flappyBird.getX()+this.flappyBird.getWeight()>this.DownPipe3.getX()//kuþ 3. alt borunun yakýnýnda mý kontrolü
           			&&this.flappyBird.getX()<this.DownPipe3.getX()+this.DownPipe3.getWeight())
               {
           		gameOver=this.flappyBird.collision(this.DownPipe3);//collisiondan alt boruya çarptý mý çarpmadý mý bilgisi geldi
           		
           		     if(gameOver==false)
           		    {
           			gameOver=this.flappyBird.collision(this.TopPipe3);//collisiondan üst boruya çarptý mý çarpmadý mý bilgisi geldi
           		    }
    	       }
    		   else
    		   {
    			   if(this.flappyBird.getY()+this.flappyBird.getHeight()>500)   //yere çarptýysa oyun bitir
    			   {
    				   gameOver=true;
    			   }
    			   else
    			   {
    				   gameOver=false;
    			   }
    			   
    		   }
    	}
    }
    	return gameOver;
    }
//////////////////
    private void score()
    {
    	if(this.DownPipe1.getX()+this.DownPipe1.getWeight()==175 || this.DownPipe2.getX()  /// boru kuþun kordinatýndan sola kayýnca skoru artýr
    			+this.DownPipe2.getWeight()==175 || this.DownPipe3.getX()
    			+this.DownPipe3.getWeight()==175 )
    	{
    		this.score++;
    		
    	}
    		
    }
  /////////////////////repaint
    public void paintComponent(Graphics g)
    {

    this.deplacementFond(g); //groundu kaydýran fonksiyon
    
    if(Keyboard.sayac==1) // boþluða basýp oyunu baþlattýysam borular gelsin
    this.deplacementPipe(g);
    
    if(gameOver==false)//bunu koymazsak kuþ çarpýp donuyor
    {
    			
    	
        this.score();
    		this.gameOver=this.collisionFlappy();
      
       if(Keyboard.sayac==1) 
    	   {  

       	   this.flappyBird.setY(this.flappyBird.getY()+1);// bunu yazmazsak kuþ uçtuðu yerde kalýyor alçalmýyor
       	  
  	       }
          g.drawImage(this.flappyBird.getImage(),this.flappyBird.getX() ,this.flappyBird.getY(),null);
    }
       // a scorun 10a bölümden kalaný tutup bastýrcak
    //skor 10a bölündükçe sýfýrlanýyor ,c elimizdeki skoru kaybetmememizi saðlayacak 
    	int a = 0,c=score;
    	int b=250;  // sayýlarý basacaðýmýz kordinatlar, birini basýnca diðerini soluna bassýn diye aþaðýda eksiltiyoruz
    	int d=260;
    	int d1=260;
    	 if(gameOver==false){ ///////////////////// oynanýrken ekranda sayýlan skoru basýyor
    	    	while(true){
    	    		
    	    		a=score%10;
    	        	score=score/10;	
    	    
    	    	if(a==0)
    			{
    	    		
    	         	 g.drawImage(num0,b,50,20,33, null);////////////20,33 numaranýn boyutunu belirler ,elle yapýnca arkasý bozuldu
    	    	
    			}
    			else if(a==1)
    			{
    				
    				g.drawImage(num1, b,50,17,33, null);
    			   
    			}
    			else if(a==2)
    			{
    				g.drawImage(num2,b,50,20,33, null);
    			}
    			else if(a==3)
    			{
    				g.drawImage(num3, b,50,20,33, null);
    			}
    			else if(a==4)
    			{
    				g.drawImage(num4, b,50,20,33, null);
    			}
    			else if(a==5)
    			{
    				g.drawImage(num5, b,50,20,33, null);
    			}
    			else if(a==6)
    			{
    				g.drawImage(num6, b,50,20,33, null);
    			}
    			else if(a==7)
    			{
    				g.drawImage(num7, b,50,20,33, null);
    			}
    			else if(a==8)
    			{
    				g.drawImage(num8, b,50,20,33, null);
    			}
    			else if(a==9)
    			{
    				g.drawImage(num9, b,50,20,33, null);
    			}
    	    	
    	    	b=b-20;
    	    	
    	    	if(score==0)
    	    	{
    	    		score=c; // skor sýfýrlanýnca c den eski deðerini alýyor
    	    	    break;
    	   	    }
    	   	  }

    	    	
    	 }
    	 else if(gameOver==true) 
    	{
    
    		deplacementBird(g); //çarpýnca aþaðý düþen kuþun resmini koyuyor
    	    g.drawImage(skor, 190, 160,115,160, null);  
    	while(true)
    	{                        /////////////////ekranda oyun bitince skor tablosuna skoru basar
    		
        	
           a=score%10;
        	score=score/10;	
        		
        	
			if(a==0)
    		{
    			g.drawImage(num0,d,210,18,27, null);
    			
    		}
    		else if(a==1)
    		{
    			
    			g.drawImage(num1, d,210,15,27, null);
    		   
    		}
    		else if(a==2)
    		{
    			g.drawImage(num2,d,210,18,27, null);
    		}
    		else if(a==3)
    		{
    			g.drawImage(num3, d,210,18,27, null);
    		}
    		else if(a==4)
    		{
    			g.drawImage(num4, d,210,18,27, null);
    		}
    		else if(a==5)
    		{
    			g.drawImage(num5, d,210,18,27, null);
    		}
    		else if(a==6)
    		{
    			g.drawImage(num6, d,210,18,27, null);
    		}
    		else if(a==7)
    		{
    			g.drawImage(num7, d,210,18,27,null);
    		}
    		else if(a==8)
    		{
    			g.drawImage(num8, d,210,18,27, null);
    		}
    		else if(a==9)
    		{
    			g.drawImage(num9, d,210,18,27, null);
    		}
        	
        	d=d-20;
        	
        	if(score==0)
        	{
        		score=c;
        	    break;
        	}
        	}
    
    	    if(temp<score)           
    	    {
    	    	temp=score;
    	        
    	    	while(true) //////// oyun bitince ekrana best skoru basar
    	    	{
    	    		a=temp%10;
    	        	temp=temp/10;	
    	        	if(a==0)
    	    		{
    	    			g.drawImage(num0,d1,270,18,27, null);
    	    		}
    	    		else if(a==1)
    	    		{
    	    			g.drawImage(num1, d1,270,15 ,27, null);
    	    		 }
    	    		else if(a==2)
    	    		{
    	    			g.drawImage(num2,d1,270,18,27, null);
    	    		}
    	    		else if(a==3)
    	    		{
    	    			g.drawImage(num3, d1,270,18,27, null);
    	    		}
    	    		else if(a==4)
    	    		{
    	    			g.drawImage(num4, d1,270,18,27, null);
    	    		}
    	    		else if(a==5)
    	    		{
    	    			g.drawImage(num5, d1,270,18,27, null);
    	    		}
    	    		else if(a==6)
    	    		{
    	    			g.drawImage(num6, d1,270,18,27, null);
    	    		}
    	    		else if(a==7)
    	    		{
    	    			g.drawImage(num7, d1,270,18,27, null);
    	    		}
    	    		else if(a==8)
    	    		{
    	    			g.drawImage(num8, d1,270,18,27, null);
    	    		}
    	    		else if(a==9)
    	    		{
    	    			g.drawImage(num9, d1,270,18,27, null);
    	    		}
    	        	
    	        	d1=d1-20;
    	        	
    	        	if(temp==0)
    	        	{
    	        		break;
    	        	}
    	        	}
    	    	  temp=c;  //skoru temp e attýk temp<score olduðundan elimisdeki best bu
    	    	    	    		
    	    }
    	    else
    	    {////////////temp>=score
    	    
    	    	k=temp;     // c nin skoru tutmasý gibi k da tempi tutar
    	    	
    	    	while(true)
    	    	{
    	    		a=temp%10;
    	        	temp=temp/10;	
    	        	if(a==0)
    	    		{
    	    			g.drawImage(num0,d1,270,18,27, null);
    	    			
    	    		}
    	    		else if(a==1)
    	    		{
    	    			
    	    			g.drawImage(num1, d1,270,15,27, null);
    	    		   
    	    		}
    	    		else if(a==2)
    	    		{
    	    			g.drawImage(num2,d1,270,18,27, null);
    	    		}
    	    		else if(a==3)
    	    		{
    	    			g.drawImage(num3, d1,270,18,27, null);
    	    		}
    	    		else if(a==4)
    	    		{
    	    			g.drawImage(num4, d1,270,18,27, null);
    	    		}
    	    		else if(a==5)
    	    		{
    	    			g.drawImage(num5, d1,270,18,27, null);
    	    		}
    	    		else if(a==6)
    	    		{
    	    			g.drawImage(num6, d1,270,18,27, null);
    	    		}
    	    		else if(a==7)
    	    		{
    	    			g.drawImage(num7, d1,270,18,27, null);
    	    		}
    	    		else if(a==8)
    	    		{
    	    			g.drawImage(num8, d1,270,18,27, null);
    	    		}
    	    		else if(a==9)
    	    		{
    	    			g.drawImage(num9, d1,270,18,27, null);
    	    		}
    	        	
    	        	d1=d1-20;
    	        	
    	        	if(temp==0)
    	        	{
    	        		
    	        	    break;
    	        	}
    	       }
                     temp=k;
    	    }
    	
    		
    	     g.drawImage(restart, 150, 390,null); //restart bas
    		
    	   
    	}
    }
}

