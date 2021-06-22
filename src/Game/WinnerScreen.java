package Game;

import java.awt.Color;
import java.awt.Graphics;

import ScreenComponents.Button;
import ScreenComponents.HUDSdisplay;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public final class WinnerScreen {
	static Button IncreaseHealth = new Button(Game.WIDTH/2 - 75, Game.HEIGHT/2 - 25, 
			150, 50, "Increase Health by 10");
	static Button IncreaseSpeed = new Button(Game.WIDTH/2 - 75, Game.HEIGHT/2 - 80, 
			150, 50, "Increase Speed by 1");
	static Button IncreaseDMG = new Button(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 30, 
			150, 50, "Increase Damage by 2");
	
	static PlayerData winner;
	public static void setColors()
	{
		IncreaseHealth.setColor(Color.GREEN);
		IncreaseSpeed.setColor(Color.BLUE);
		IncreaseDMG.setColor(Color.RED);
	}
	public static void render(Graphics g)
	{
		HUDSdisplay.drawText(g, "Winner : " + winner.getName(), Game.WIDTH/2 - 100, Game.HEIGHT/2 - 250, 20);
		IncreaseHealth.draw(g);
		IncreaseSpeed.draw(g);
		IncreaseDMG.draw(g);
	}
	public static void StartFunction(float mouseX, float mouseY)
	{
		if(IncreaseHealth.isInside(mouseX, mouseY))
		{
			winner.setHealth(winner.getHealth() + 10);
			Game.state = Game.STATE.STARTMENU;
			Game.playersData.saveData();
		}
		else if(IncreaseSpeed.isInside(mouseX, mouseY))
		{
			winner.setSpeed(winner.getSpeed() + 1);
			Game.state = Game.STATE.STARTMENU;
			Game.playersData.saveData();
		}
		else if(IncreaseDMG.isInside(mouseX, mouseY))
		{
			winner.setFireDmg(winner.getFireDmg() + 2);
			Game.state = Game.STATE.STARTMENU;
			Game.playersData.saveData();
		}
	}
}
