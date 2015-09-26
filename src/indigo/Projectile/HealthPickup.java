package indigo.Projectile;

import indigo.Entity.Entity;
import indigo.Landscape.Wall;
import indigo.Manager.Content;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class HealthPickup extends Projectile
{
	private final int DEFAULT = 0;

	public final static int HEALTH = 100;
	public final static int WIDTH = 50;
	public final static int HEIGHT = 50;
	public final static double SPEED = 0;

	private int timer = 0;

	public HealthPickup(Entity entity, double x, double y, double velX, double velY, int dmg)
	{
		super(entity, x, y, velX, velY, dmg);
		width = WIDTH;
		height = HEIGHT;
		solid = false;
		flying = true;
		
		friendly = false;

		setAnimation(DEFAULT, Content.HEALTH_PICKUP, -1);
	}

	public void update()
	{
		super.update();
		
		timer++;
		if(timer > 40)
		{
			timer = 0;
		}
	}

	public void render(Graphics2D g)
	{
		if(timer < 5)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2), (int)getWidth(), (int)getHeight(), null);
		}
		else if(timer < 10)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2) + 1, (int)getWidth(), (int)getHeight(), null);
		}
		else if(timer < 15)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2) + 2, (int)getWidth(), (int)getHeight(), null);
		}
		else if(timer < 20)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2) + 1, (int)getWidth(), (int)getHeight(), null);
		}
		else if(timer < 25)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2), (int)getWidth(), (int)getHeight(), null);
		}
		else if(timer < 30)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2) - 1, (int)getWidth(), (int)getHeight(), null);
		}
		else if(timer < 35)
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2) - 2, (int)getWidth(), (int)getHeight(), null);
		}
		else
		{
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2) - 1, (int)getWidth(), (int)getHeight(), null);
		}
	}

	public Shape getHitbox()
	{
		return new Ellipse2D.Double(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
	}

	public void collide(Entity ent)
	{
		if(ent.getHealth() < ent.getMaxHealth())
		{
			ent.setHealth(ent.getHealth() + damage);
			die();
		}
	}

	// Not used
	public void collide(Wall wall)
	{
	}

	// Not used
	public boolean isActive()
	{
		return true;
	}

	// TODO Death animation
	public void die()
	{
		super.die();
	}
}