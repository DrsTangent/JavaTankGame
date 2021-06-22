package Game;

import java.awt.Rectangle;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public abstract class GameObject {
	//Positions//
	protected Rectangle object;
	protected double rotation;
	public GameObject(int x, int y, int sizeH, int sizeW)
	{
		Rectangle r = new Rectangle();
		r.setBounds(x, y, sizeW, sizeH);
		object = r;
	}
	public int getX()
	{
		return object.x;
	}
	public int getY()
	{
		return object.y;
	}
	public boolean collision(Rectangle o)
	{
		return object.intersects(o);
	}
}
