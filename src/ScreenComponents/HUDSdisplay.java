package ScreenComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Game;
import Game.Tank;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class HUDSdisplay {
	public static void renderHealths(Graphics g, Tank t1, Tank t2)
	{
		
		//Health Rectangles//
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, (int) (250*t1.getHealth()/t1.getMaxHealth()), 20);
		g.setColor(Color.RED);
		g.fillRect(Game.WIDTH - (int)( 250*t2.getHealth()/t2.getMaxHealth()), 0, (int) (250*t2.getHealth()/t2.getMaxHealth()), 20);
		
		drawText(g, "Tank1", 0 , 0, 12);
		drawText(g, "Health: " + t1.getHealth(), 50, 0, 12);
		drawText(g, "Tank 2", Game.WIDTH - 40, 0, 12);
		drawText(g, "Health: " + t2.getHealth(), Game.WIDTH - 150, 0, 12);
	}
	
	public static void drawText(Graphics g, String Text, int x, int y, int size)
	{
		g.setColor(Color.BLACK);
		
		Graphics2D g2d = (Graphics2D) g;

		Font font = new Font("Comics", Font.BOLD, size);
		g2d.setFont(font);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		 
		// Draw a string such that the top-left corner is at x, y
		g2d.drawString(Text, x, y+fontMetrics.getAscent());
		
	}
	
	public static void drawText(Graphics g, String Text, int x, int y, Font font)
	{
		g.setColor(Color.BLACK);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(font);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		 
		// Draw a string such that the top-left corner is at x, y
		g2d.drawString(Text, x, y+fontMetrics.getAscent());
		
	}
}
