package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class Tank extends GameObject implements Entity{
	BufferedImage tankIMG;
	double rotationChange;
	double displacementChange;
	private float maxHealth = 100;
	private float health = 100;
	private float speed = 2;
	private float damage = 5;
	private Tank enemyTank;
	public Tank(int x, int y, double rotation, int sizeH, int sizeW, String image) {
		super(x, y, sizeH, sizeW);
		rotationChange = 0;
		displacementChange = 0;
		this.rotation = rotation;
		try {
			tankIMG = ImageIO.read(getClass().getResource(image));
		}
		catch(IOException e)
		{
			System.out.print("Please Reset, There is an Error in Loading Image");
		}
		// TODO Auto-generated constructor stub
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public void setEnemyTank(Tank t)
	{
		enemyTank = t;
	}
	public boolean withinBorder(double displace)
	{
		// To Gets Ahead Movements of Current Tank so it may not overlap the rendering of Second Tank//
		Rectangle aheadMovements = new Rectangle();
		aheadMovements.setBounds(object.x+ (int ) (displace*Math.cos(Math.PI*rotation/180)), 
				object.y + (int ) (displace*Math.sin(Math.PI*rotation/180)), object.width, object.height);
		if(object.x+ (displace*Math.cos(Math.PI*rotation/180)) > 0 && object.y + (displace*Math.sin(Math.PI*rotation/180)) > 0
				&& object.x + (displace*Math.cos(Math.PI*rotation/180)) < Game.WIDTH-object.width &&
				object.y + (displace*Math.sin(Math.PI*rotation/180)) < Game.HEIGHT-object.height &&
				!aheadMovements.intersects(enemyTank.object))
			return true;
		return false;
	}
	public void render(Graphics g)
	{
		//As Size of Image and Object is Differnt so Formolas must be used.
		int widthDiff = Math.abs(tankIMG.getWidth() - object.width)/2;
		int heightDiff = Math.abs(tankIMG.getHeight() - object.height)/2;
		//Rotating the Image//
		double rotationRequired = Math.toRadians (rotation); // Rotation must be converted from Degrees to Radians// 
		AffineTransform transformingAgent = AffineTransform.getRotateInstance(rotationRequired, tankIMG.getWidth()/2, tankIMG.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transformingAgent, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(tankIMG, null), object.x-widthDiff, object.y-heightDiff, null);
	}
	public static BufferedImage rotate(BufferedImage img, double rotation)
    {
  
        // Getting Dimensions of image
        int width = img.getWidth()/2;
        int height = img.getHeight()/2;
  
        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
            img.getWidth(), img.getHeight(), img.getType());
  
        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();
  
        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(rotation), width / 2,
                  height / 2);
        g2.drawImage(img, null, 0, 0);
  
        // Return rotated buffer image
        return newImage;
    }
	public void rotateUP(int degrees)
	{
		rotationChange = -degrees;
	}
	public void rotateDown(int degrees)
	{
		rotationChange = degrees;
	}
	//Moving Functions
	public void move(int speed)
	{
		if(speed < 0)
			displacementChange = -this.speed;
		else if (speed > 0)
			displacementChange = this.speed;
		else
			displacementChange = 0;
	}
	public void playerFunctioning()
	{
		rotation+=rotationChange;
		if(withinBorder(displacementChange))
		{
			object.x+=(int) (displacementChange*Math.cos(Math.PI*rotation/180));
			object.y+= (int) (displacementChange*Math.sin(Math.PI*rotation/180));
		}
	}
	@Override
	public void position(int x, int y) {
		object.x =x;
		object.y =y;
	}
}
