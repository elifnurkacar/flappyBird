package FlappyBird;

import javax.swing.*;


public class Main {

	public static JFrame frame; 
	public static Screen screen;
	public static void main(String[] args) {
	
		frame=new JFrame("Flappy Bird");
		screen=new Screen();
		frame.setSize(500,620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setContentPane(screen);
		frame.setVisible(true);
	}

}

