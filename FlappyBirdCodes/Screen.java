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
	public Pipe DownPipe3;    //ilk �� boru
	
	public Pipe pipe;     // ku� �arpt� m� �armad� m� kontrol� yapt���m�z boru
	
	public int x;         //groundun x ekseninde kaymas�n� yapcak
	
	public int xPipe;     // ku�un ba�ta borulara olan uzakl���
	
	public FlapBird flappyBird;    // ku�
    
	private final int weight=142;   //  groundun devaml�l��� i�in
	private final int Distance_pipes=200;  // sa�-sol iki boru aras�ndaki mesafe
	private final int Distance2_pipes=170;  // top ve down boru aras�ndaki mesafe
	
	public boolean gameOver;   // oyun bitti mi(true) bitmedi mi(false)
	
	public Random rand;	// borular�n uzunlu�unun random olmas� i�in

    public Screen()   //constructer
    {
    	
    	super();    //�st s�n�f�n constructer�na ula��r (jPanel)
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
    	
	    this.score=0;  //s�f�rdan ba�la
    	this.x=0;  //ground
    	this.xPipe=550;   //ba�taki mesafe
    	this.gameOver=false;
    
    	this.TopPipe1=new Pipe(this.xPipe,-120,"/imgg/pipe2.png");
    	this.DownPipe1=new Pipe(this.xPipe,350,"/imgg/pipe.png");
    	
    	this.TopPipe2=new Pipe(this.xPipe+this.Distance_pipes,-180,"/imgg/pipe2.png");
    	this.DownPipe2=new Pipe(this.xPipe+this.Distance_pipes,290,"/imgg/pipe.png");
    	
    	this.TopPipe3=new Pipe(this.xPipe+this.Distance_pipes+200,-70,"/imgg/pipe2.png");
    	this.DownPipe3=new Pipe(this.xPipe+this.Distance_pipes+200,400,"/imgg/pipe.png");
    
    	this.flappyBird=new FlapBird(180,250,"/imgg/bird-02.png"); ///////////////////////ku�un koordinat� 180.250
    	
    	rand=new Random(); //boru boyu i�in
    	
    	this.setFocusable(true);
    	this.requestFocusInWindow(); //panele odaklan�yor
    	
    	this.addKeyListener(new Keyboard());   
    	
    	Thread ContGameEcran=new Thread(new ContGame());  //thread ak��� sa�l�yor   //contgame e gitti
    	ContGameEcran.start();    // oyun ba�lar
    	
    }
    
    ///////////////////// background olu�turup zemin kayd�rma
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
    //////////////////////// ku�un d��t��� g�r�nt�lenir
    public void deplacementBird(Graphics g)
    {
    
    	this.flappyBird=new FlapBird(flappyBird.getX(),flappyBird.getY(),"/imgg/bird-down.png");
	    g.drawImage(this.flappyBird.getImage(),this.flappyBird.getX() ,this.flappyBird.getY(),null);
	 }
  //////////////////////////borular�n devaml�l��� sa�lan�yor
    private void deplacementPipe(Graphics g)
    {
    	if(gameOver==false)// bu kontrolleri koymad���m�zda ku� yere d��erken boru gelmesini durduram�yoruz
    	{	this.TopPipe1.setX(this.TopPipe1.getX()-1);       //-1 borunun x ekseninde kaymas�n� sa�lar 
    	    this.DownPipe1.setX(this.TopPipe1.getX());}
    	
    	if(this.TopPipe1.getX()==-100)  //ilk boru ekrandan ��k�nca(-100) sa�dan yeni borunun gelmesini sa�lar
    	{
    		//giden  1.borunun x ine = 3. borunun koordinat�na iki boru aras�ndaki mesafeyi ekleyip koyar
    		this.TopPipe1.setX(this.TopPipe3.getX()+this.Distance_pipes); 
    		this.TopPipe1.setY(-100-10*this.rand.nextInt(18)); //-100 borunun boyunu �stten k�salt�r ekranda kalan random olur
    		this.DownPipe1.setY(this.TopPipe1.getY()+this.TopPipe1.getHeight()+this.Distance2_pipes);
    		//// eksi de�erli gety yi +olan heigth(sabit:300) ve mesafe ile toplay�p alt borunun ysini hesapl�yor
    	}
    	//hesaplara g�re boruyu �izdik
    	g.drawImage(this.TopPipe1.getImage(), this.TopPipe1.getX(),this.TopPipe1.getY(),null);
    	g.drawImage(this.DownPipe1.getImage(), this.DownPipe1.getX(),this.DownPipe1.getY(),null);
    	
    	//pipe 2
    	
    	if(gameOver==false)   // bu kontrolleri koymad���m�zda ku� yere d��erken boru gelmesini durduram�yoruz
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
    	if(gameOver==false)// bu kontrolleri koymad���m�zda ku� yere d��erken boru gelmesini durduram�yoruz
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
    
    /// ku�un �arpmas�
    public boolean collisionFlappy()
    {
    	boolean gameOver=false;
    	if(this.flappyBird.getX()+this.flappyBird.getWeight()>this.DownPipe1.getX()
    			&&this.flappyBird.getX()<this.DownPipe1.getX()+this.DownPipe1.getWeight())//ku� ilk alt borunun yak�n�nda m� kontrol�
    	{
    		gameOver=this.flappyBird.collision(this.DownPipe1); //collisiondan alt boruya �arpt� m� �arpmad� m� bilgisi geldi
    		if(gameOver==false)
    		{
    			gameOver=this.flappyBird.collision(this.TopPipe1);//collisiondan �st boruya �arpt� m� �arpmad� m� bilgisi geldi
    		}
    	}
    	else
    	{
    		if(this.flappyBird.getX()+this.flappyBird.getWeight()>this.DownPipe2.getX()//ku� 2. alt borunun yak�n�nda m� kontrol�
        			&&this.flappyBird.getX()<this.DownPipe2.getX()+this.DownPipe2.getWeight())
        	{
        		gameOver=this.flappyBird.collision(this.DownPipe2);//collisiondan alt boruya �arpt� m� �arpmad� m� bilgisi geldi
        		
        		if(gameOver==false)
        		{
        			gameOver=this.flappyBird.collision(this.TopPipe2);//collisiondan �st boruya �arpt� m� �arpmad� m� bilgisi geldi
        		}
        	}
    		
    	
    	   else
    	   {
    		   if(this.flappyBird.getX()+this.flappyBird.getWeight()>this.DownPipe3.getX()//ku� 3. alt borunun yak�n�nda m� kontrol�
           			&&this.flappyBird.getX()<this.DownPipe3.getX()+this.DownPipe3.getWeight())
               {
           		gameOver=this.flappyBird.collision(this.DownPipe3);//collisiondan alt boruya �arpt� m� �arpmad� m� bilgisi geldi
           		
           		     if(gameOver==false)
           		    {
           			gameOver=this.flappyBird.collision(this.TopPipe3);//collisiondan �st boruya �arpt� m� �arpmad� m� bilgisi geldi
           		    }
    	       }
    		   else
    		   {
    			   if(this.flappyBird.getY()+this.flappyBird.getHeight()>500)   //yere �arpt�ysa oyun bitir
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
    	if(this.DownPipe1.getX()+this.DownPipe1.getWeight()==175 || this.DownPipe2.getX()  /// boru ku�un kordinat�ndan sola kay�nca skoru art�r
    			+this.DownPipe2.getWeight()==175 || this.DownPipe3.getX()
    			+this.DownPipe3.getWeight()==175 )
    	{
    		this.score++;
    		
    	}
    		
    }
  /////////////////////repaint
    public void paintComponent(Graphics g)
    {

    this.deplacementFond(g); //groundu kayd�ran fonksiyon
    
    if(Keyboard.sayac==1) // bo�lu�a bas�p oyunu ba�latt�ysam borular gelsin
    this.deplacementPipe(g);
    
    if(gameOver==false)//bunu koymazsak ku� �arp�p donuyor
    {
    			
    	
        this.score();
    		this.gameOver=this.collisionFlappy();
      
       if(Keyboard.sayac==1) 
    	   {  

       	   this.flappyBird.setY(this.flappyBird.getY()+1);// bunu yazmazsak ku� u�tu�u yerde kal�yor al�alm�yor
       	  
  	       }
          g.drawImage(this.flappyBird.getImage(),this.flappyBird.getX() ,this.flappyBird.getY(),null);
    }
       // a scorun 10a b�l�mden kalan� tutup bast�rcak
    //skor 10a b�l�nd�k�e s�f�rlan�yor ,c elimizdeki skoru kaybetmememizi sa�layacak 
    	int a = 0,c=score;
    	int b=250;  // say�lar� basaca��m�z kordinatlar, birini bas�nca di�erini soluna bass�n diye a�a��da eksiltiyoruz
    	int d=260;
    	int d1=260;
    	 if(gameOver==false){ ///////////////////// oynan�rken ekranda say�lan skoru bas�yor
    	    	while(true){
    	    		
    	    		a=score%10;
    	        	score=score/10;	
    	    
    	    	if(a==0)
    			{
    	    		
    	         	 g.drawImage(num0,b,50,20,33, null);////////////20,33 numaran�n boyutunu belirler ,elle yap�nca arkas� bozuldu
    	    	
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
    	    		score=c; // skor s�f�rlan�nca c den eski de�erini al�yor
    	    	    break;
    	   	    }
    	   	  }

    	    	
    	 }
    	 else if(gameOver==true) 
    	{
    
    		deplacementBird(g); //�arp�nca a�a�� d��en ku�un resmini koyuyor
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
    	    	  temp=c;  //skoru temp e att�k temp<score oldu�undan elimisdeki best bu
    	    	    	    		
    	    }
    	    else
    	    {////////////temp>=score
    	    
    	    	k=temp;     // c nin skoru tutmas� gibi k da tempi tutar
    	    	
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

