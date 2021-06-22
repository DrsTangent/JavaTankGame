package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import ScreenComponents.Button;
import ScreenComponents.HUDSdisplay;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public final class LeaderBoard {
	static Button Exit = new Button(0,0,70, 30, "Exit");
	
	
	public static void StartFunction(float mouseX, float mouseY)
	{
		if(Exit.isInside(mouseX, mouseY))
		{
			Game.state = Game.STATE.STARTMENU;
		}
	}
	public static void settingUpLeaderBoard()
	{
		Exit.setColor(Color.RED);
		Collections.sort(Game.playersData.list);
	}
	public static void showTable(Graphics g)
	{
		Exit.draw(g);
		
		g.setColor(Color.ORANGE);
		g.fillRect(100, 100, Game.WIDTH - 200, Game.HEIGHT -200);
		
		//Title//
		HUDSdisplay.drawText(g, "LEADERBOARD", Game.WIDTH/2 - 75,120, 20);
		
		int j = 150;
		String text = "Position";
		HUDSdisplay.drawText(g, text, 140,j, 20);
		text = "Name";
		HUDSdisplay.drawText(g, text, 140 + 100, j, 20);
		text = "Wins";
		HUDSdisplay.drawText(g, text, 140 + 275, j, 20);
		j+=25;
		for(int i = 0; i < Game.playersData.list.size() && i < 15; i++)
		{
			text = "" + (1+i);
			HUDSdisplay.drawText(g, text, 140, j+i*20, 20);
			text = Game.playersData.list.get(i).getName();
			HUDSdisplay.drawText(g, text, 140 + 100, j+i*20, 20);
			text = "" + Game.playersData.list.get(i).getWins();
			HUDSdisplay.drawText(g, text, 140 + 275, j+i*20, 20);
		}
	}
}
