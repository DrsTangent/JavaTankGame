package Game;

import java.awt.Dimension;

import javax.swing.JFrame;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class Runner {
	public static JFrame frame;
	private static Game game;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game = new Game();
		
		game.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));                    
        game.setMaximumSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
        game.setMinimumSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		
		frame = new JFrame("The Tank Game");                                                
		frame.add(game);                                                                        
		frame.pack();                                                                           
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                   
        frame.setResizable(false);                                                             
        frame.setLocationRelativeTo(null);                                                     
        frame.setVisible(true);                                                                 
        game.start();
	}

}
