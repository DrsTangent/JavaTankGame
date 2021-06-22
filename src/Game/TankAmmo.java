package Game;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */

public class TankAmmo extends GameObject{
	//public
	private Tank attachwith;
	private Tank target;
	private BufferedImage missileIMG;
	public enum missileSTATUS{
		FIRED,
		ATTACHED
	}
	private missileSTATUS state = missileSTATUS.ATTACHED;
	public TankAmmo(Tank t1, Tank t2, String missilePic){
		super(0, 0, 20, 20);
		attachwith = t1;
		target = t2;
		this.rotation = 0;
		//it's Image//
		try {
			missileIMG = ImageIO.read(getClass().getResource(missilePic));
		}
		catch(IOException e)
		{
			System.out.print("Please Reset, There is an Error in Loading Image");
		}
		
		state = missileSTATUS.ATTACHED;
	}
	public void fire()
	{
		if(state == missileSTATUS.ATTACHED)
		{
			this.rotation = attachwith.rotation;
			state = missileSTATUS.FIRED;
		}
	}
	public void update()
	{
		if(state == missileSTATUS.ATTACHED)
		{
			//Center of Tank
			int tankX = attachwith.getX() + attachwith.object.width/2;
			int tankY = attachwith.getY() + attachwith.object.height/2;
			//
			double pointOfRotation = attachwith.rotation; //- 18.42132292021208;
			
			object.x = tankX+(int) (30*Math.cos(Math.PI*pointOfRotation/180));
			object.y = tankY+(int) (30*Math.sin(Math.PI*pointOfRotation/180));
			object.x-=10;
			object.y-=10;
			object.x+=(int) (10*Math.cos(Math.PI*pointOfRotation/180));
			object.y+=(int) (10*Math.sin(Math.PI*pointOfRotation/180));
			this.rotation = attachwith.rotation;
		}
		else if (state == missileSTATUS.FIRED)
		{
			object.x+=(10*Math.cos(Math.PI*rotation/180));
			object.y+=(10*Math.sin(Math.PI*rotation/180));
			if(object.x < 0 || object.y < 0 || object.x > Game.WIDTH || object.y > Game.HEIGHT)
			{
				state = missileSTATUS.ATTACHED; 
			}
			else if(this.collision(this.target.object))
			{
				target.setHealth(target.getHealth() - attachwith.getDamage());
				state = missileSTATUS.ATTACHED;
			}
		}
	}
	public void render(Graphics g)
	{
		
		double rotationRequired = Math.toRadians (rotation);
		double locationX = missileIMG.getWidth()/2;
		double locationY = missileIMG.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);

		//g.drawImage(op.filter(tankIMG, null), this.x, this.y, this.objectBound.height, this.getObjectBound().width, null);
		g.drawImage(op.filter(missileIMG, null), object.x, object.y, null);
	}
	
	
}
