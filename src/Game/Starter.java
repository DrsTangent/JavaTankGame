package Game;

import java.awt.Color;
import java.awt.Graphics;


import Game.Game.STATE;
import ScreenComponents.Button;
import ScreenComponents.PasswordField;
import ScreenComponents.TextBox;
import ScreenComponents.TextField;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class Starter {
	PlayerData p1 = null;
	PlayerData p2 = null;
	//Text Boxes
	private TextBox txt1 = new TextBox(Game.WIDTH/2 - 300, Game.HEIGHT/2 - 75, 
			250, 50, "Player 1 Enter Name", Color.BLUE);
	private TextBox txt2 = new TextBox(Game.WIDTH/2 + 50, Game.HEIGHT/2 - 75, 
			250, 50, "Player 2 Enter Name", Color.RED);
	
	//TextFields//
	TextField t1 = new TextField(Game.WIDTH/2 - 300, Game.HEIGHT/2 - 25, 
			250, 50, "");
	TextField t2 = new TextField(Game.WIDTH/2 + 50, Game.HEIGHT/2 - 25, 
			250, 50, "");
	//Text Boxes
	private TextBox txt3 = new TextBox(Game.WIDTH/2 - 300, Game.HEIGHT/2 + 75, 
			250, 50, "Enter Player 1 Password", Color.BLUE);
	private TextBox txt4 = new TextBox(Game.WIDTH/2 + 50, Game.HEIGHT/2 + 75, 
			250, 50, "Enter Player 2 Password", Color.RED);
	//PasswordFields//
	PasswordField pass1 = new PasswordField(Game.WIDTH/2 - 300, Game.HEIGHT/2 + 125, 
			250, 50);
	PasswordField pass2 = new PasswordField(Game.WIDTH/2 + 50, Game.HEIGHT/2 + 125, 
			250, 50);
	//Buttons//
	Button startButton = new Button(Game.WIDTH/2 - 50, Game.HEIGHT/2 - 25, 
			100, 50, "STARTGAME");
	Button LeaderBoardButton = new Button(Game.WIDTH/2 - 50, Game.HEIGHT/2 - 125, 
			100, 50, "LEADERBOARD");
	public void StartFunction(float mouseX, float mouseY)
	{
		if(startButton.isInside(mouseX, mouseY))
		{
			if(txt1.getText().equals("") || txt2.getText().equals("") 
					|| txt3.getText().equals("") || txt4.getText().equals(""))
			{
				System.out.println("Some Text Box is left Empty");
				return;
			}
			//Declaring New Tanks
			Game.t1  = new Tank( 60 , Game.HEIGHT/2, 180, 60, 60, "/player1Tank.png");
			Game.t2  = new Tank( Game.WIDTH - 120 , Game.HEIGHT/2, 0, 60, 60, "/player2Tank.png");
			//Declaring Ammo for it
			Game.t1Ammo = new TankAmmo(Game.t1, Game.t2, "/missile2.png") ;
		    Game.t2Ammo = new TankAmmo(Game.t2, Game.t1, "/missile.png") ;
		    //Declaring Enemies//
		    Game.t1.setEnemyTank(Game.t2);
		    Game.t2.setEnemyTank(Game.t1);
		    
			p1 = Game.playersData.search(t1.getText(), pass1.getText());
			p2 = Game.playersData.search(t2.getText(), pass2.getText());
			//Retrieving Player One Data//
			if(p1 != null)
			{
				Game.t1.setDamage(p1.getFireDmg());
				Game.t1.setSpeed(p1.getSpeed());
				Game.t1.setMaxHealth(p1.getHealth());
			}
			else
			{
				return;
			}
			//Retrieving Player Two Data//
			if(p2 != null)
			{
				Game.t2.setDamage(p2.getFireDmg());
				Game.t2.setSpeed(p2.getSpeed());
				Game.t2.setMaxHealth(p2.getHealth());
			}
			else
			{
				return;
			}
			Game.state = Game.STATE.GAME;
		}
		else if(LeaderBoardButton.isInside(mouseX, mouseY))
		{
			LeaderBoard.settingUpLeaderBoard();
			Game.state = Game.STATE.LEADERBOARD;
		}
		t1.select(mouseX, mouseY);
		t2.select(mouseX, mouseY);
		pass1.select(mouseX, mouseY);
		pass2.select(mouseX, mouseY);
	}
	
	public void render(Graphics g)
	{
		startButton.draw(g);
		LeaderBoardButton.draw(g);
		txt1.draw(g);
		txt2.draw(g);
		t1.draw(g);
		t2.draw(g);
		txt3.draw(g);
		txt4.draw(g);
		pass1.draw(g);
		pass2.draw(g);
	}
}
