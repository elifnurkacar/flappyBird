package FlappyBird;


import java.awt.*;
import javax.swing.*;
public class Pipe {

	private int weight;
	private int height;
	private int x;
	private int y;
	private  String strImage;
	
	private ImageIcon icon;
	private Image image;
	public Pipe(int x,int y,String strImage) {
		this.weight=70;
		this.height=300;
		this.x=x;
		this.y=y;
		this.strImage=strImage; //resim yolu
		this.icon=new ImageIcon(getClass().getResource(this.strImage));
		this.image=this.icon.getImage();
	}
	
	public void setY(int y) {  this.y = y; }
	
	public void setX(int x) {  this.x = x;  }
	
	public int getY() {  return y;  }
	
	public int getX() { return x; }
	
	public int getWeight() {  return weight;  }
	
	public int getHeight() {  return height; }
	
	public Image getImage() {  return image;  }

}
