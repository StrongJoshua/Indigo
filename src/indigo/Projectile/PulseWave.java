package indigo.Projectile;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import indigo.Entity.Entity;
import indigo.Landscape.Wall;
import indigo.Manager.Content;

public class PulseWave extends Projectile
{
	private int timer;

	private final int DEFAULT = 0;

	public static final int DAMAGE = 20; // Will scale by distance
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 1000;
	public final static double PUSHBACK = 100; // TODO: Change getWidth(), getHeight(), and PUSHBACK to suit the Pulse
												// Shot, keeping hitbox size in mind
	public final static int DURATION = 5;

	public PulseWave(Entity entity, double x, double y, double velX, double velY, int dmg)
	{
		super(entity, x, y, velX, velY, dmg);
		width = WIDTH;
		height = HEIGHT;
		solid = false;
		flying = true;

		timer = DURATION;

		setAnimation(DEFAULT, Content.PULSE_WAVE, -1); // TODO: Change to be that of the pulse shot
	}

	public void update()
	{
		super.update();
		timer--;

		if(timer == 0)
		{
			die();
		}

		// Kill projectiles - projectile.die()
		for(int count = 0; count < stage.getProjectiles().size(); count++)
		{
			if(intersects(stage.getProjectiles().get(count))
					&& !(stage.getProjectiles().get(count).getX() == this.getX() && stage.getProjectiles().get(count)
							.getY() == this.getY()))
			{
				stage.getProjectiles().get(count).die();
			}
		}
	}

	public boolean intersects(Projectile proj)
	{
		Area entArea = new Area(getHitbox());
		entArea.intersect(new Area(proj.getHitbox()));
		return !entArea.isEmpty();
	}

	public void render(Graphics2D g)
	{
		// TODO: If necessary, change to be that of the pulse shot
		g.drawImage(animation.getImage(), (int)(getX() - getWidth() / 2), (int)(getY() - getHeight() / 2),
				(int)getWidth(), (int)getHeight(), null);
	}

	public Shape getHitbox()
	{
		return new Ellipse2D.Double(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
	}

	public void collide(Entity ent) // Pushes enemies
	{
		if(!stage.getPlayer().isGrounded() || (ent.getY() < getY() + stage.getPlayer().getHeight() / 2))
		{
			// Push enemy away, further when closer
			double scale = Math.sqrt(Math.pow(ent.getY() - getY(), 2) + Math.pow(ent.getX() - getX(), 2));
			double iDP = 1 - (scale / WIDTH); // Inverse distance percentage; TODO: Change WIDTH to Radius here when
												// animation is drawn
			double velX = PUSHBACK * iDP * (ent.getX() - getX()) / scale;
			double velY = PUSHBACK * iDP * (ent.getY() - getY()) / scale;

			if(scale < getWidth() * 0.02 || scale < getHeight() * 0.02) // TODO: Change for when get circular hitbox.
			{
				// Directly apply knockback to avoid divide by zero error
				velX = PUSHBACK * (ent.getX() - getX()) / scale;
				velY = PUSHBACK * (ent.getY() - getY()) / scale;
			}

			ent.setVelX(velX + ent.getVelX()); // velocity is added on rather than set
			ent.setVelY(velY + ent.getVelY());

			if(!ent.isDodging()) // damaged, won't hurt if is dodging, scale by distance
			{
				ent.setHealth((int)(ent.getHealth() - (DAMAGE * iDP)));
			}

			ent.mark();
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
}
