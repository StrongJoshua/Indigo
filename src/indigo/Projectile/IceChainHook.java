package indigo.Projectile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import indigo.Entity.Entity;
import indigo.Landscape.Wall;
import indigo.Manager.Content;
import indigo.Stage.Stage;

public class IceChainHook extends Projectile
{
	private boolean reverse;
	private int timer;

	private double angle;

	private Entity attached;

	// Animation
	private final int DEFAULT = 0;

	public static final int DAMAGE = 0;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final double SPEED = 70;
	public static final int EXTEND_DURATION = 20; // Time before hook is recalled
	public static final int RETURN_DURATION = 20; // Time for hook to return

	public IceChainHook(Entity entity, double x, double y, double velX, double velY, int dmg)
	{
		super(entity, x, y, velX, velY, dmg);
		width = WIDTH;
		height = HEIGHT;
		solid = true;
		flying = true;

		if(getVelX() >= 0)
		{
			angle = Math.atan(getVelY() / getVelX());
		}
		else
		{
			angle = Math.PI + Math.atan(getVelY() / getVelX());
		}

		reverse = false;
		timer = 0;

		setAnimation(DEFAULT, Content.WATER_PROJECTILE, -1);
	}

	public void update()
	{
		super.update();
		timer++;

		if(!reverse)
		{
			if(getVelX() >= 0)
			{
				angle = Math.atan(getVelY() / getVelX());
			}
			else
			{
				angle = Math.PI + Math.atan(getVelY() / getVelX());
			}

			if(timer == EXTEND_DURATION)
			{
				reverse();
			}
		}
		else
		{
			double dx = 0;
			double dy = stage.getPlayer().getY() - getY();

			if(getX() > stage.getPlayer().getX())
			{
				stage.getPlayer().setDirection(true);
				dx = stage.getPlayer().getX() + 30 - getX();
			}
			else
			{
				stage.getPlayer().setDirection(false);
				dx = stage.getPlayer().getX() - 30 - getX();
			}
			double scale = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

			setVelX(dx / scale * SPEED);
			setVelY(dy / scale * SPEED);

			if(getVelX() >= 0)
			{
				angle = Math.PI + Math.atan(getVelY() / getVelX());
			}
			else
			{
				angle = Math.atan(getVelY() / getVelX());
			}

			if(scale < SPEED || timer == RETURN_DURATION)
			{
				dead = true;
			}
		}

		if(attached != null)
		{
			attached.setX(getX());
			attached.setY(getY());

			attached.setVelX(0);
			attached.setVelY(0);

			// Checks if the attached Entity is colliding with any walls
			for(Wall wall : stage.getWalls())
			{
				if(wall.killsEntities())
				{
					if(attached.intersects(wall.getLine()))
					{
						attached.die();
						stage.trackDeath(wall.getName(), attached);
					}
				}
				if(wall.blocksEntities())
				{
					if(!wall.isHorizontal())
					{
						// Leftward collision into wall
						if(attached.isRightOfLine(wall.getLine()))
						{
							while(attached.intersects(wall.getLine()))
							{
								attached.setX(attached.getX() + Stage.PUSH_AMOUNT);
								attached.setVelX(Math.max(attached.getVelX(), 0));
							}
						}
						// Rightward collision into wall
						else
						{
							while(attached.intersects(wall.getLine()))
							{
								attached.setX(attached.getX() - Stage.PUSH_AMOUNT);
								attached.setVelX(Math.min(attached.getVelX(), 0));
							}
						}
					}
					else
					{
						// Downward collision into wall
						if(attached.isAboveLine(wall.getLine()))
						{
							while(attached.intersects(wall.getLine()))
							{
								attached.setY(attached.getY() - Stage.PUSH_AMOUNT);
								attached.setVelY(Math.min(attached.getVelY(), 0));
							}
						}
						// Upward collision into wall
						else
						{
							while(attached.intersects(wall.getLine()))
							{
								attached.setY(attached.getY() + Stage.PUSH_AMOUNT);
								attached.setVelY(Math.max(attached.getVelY(), 0));
							}
						}
					}
				}
			}

			setX(attached.getX());
			setY(attached.getY());
			
			if(dead)
			{
				if(getX() > stage.getPlayer().getX())
				{
					attached.setX(stage.getPlayer().getX() + 30);
				}
				else
				{
					attached.setX(stage.getPlayer().getX() - 30);
				}
			}
		}
	}

	public void render(Graphics2D g)
	{
		// Draw ranges from 0 to 1 in intervals of 0.05
		g.setColor(new Color(0, 255, 255));
		g.drawLine((int)stage.getPlayer().getX(), (int)stage.getPlayer().getY(), (int)getX(), (int)getY());
		if(getX() > 0 && getX() < stage.getMapX())
		{
			// Rotation breaks if x is negative
			g.rotate(angle, getX(), getY());
			g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2),
					(int)getWidth(), (int)getHeight(), null);
			g.rotate(-angle, getX(), getY());
		}
	}

	public Shape getHitbox()
	{
		return new Rectangle2D.Double(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
	}

	public void collide(Entity ent)
	{
		if(!reverse)
		{
			reverse();
			attached = ent;
		}
	}

	public void collide(Wall wall)
	{
		reverse();
	}
	
	public void reverse()
	{
		reverse = true;
		timer = 0;
	}

	public boolean isActive()
	{
		return true;
	}
}