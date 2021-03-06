package indigo.Entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import indigo.Projectile.SteamCloud;
import indigo.Stage.Stage;

public class SteamVent extends Entity {
	
	/*private boolean canAttack;
	private boolean canMove;
	private boolean canTurn;*/
	private int timer;
	
	private final int DEFAULT = 0;
	private final int DEATH = 1;
	
	public static final double STEAMVENT_WIDTH = 100;
	public static final double STEAMVENT_HEIGHT = 10;
	public static final int BASE_HEALTH = 200;
	public static final int DURATION = 45;
	
	public SteamVent(Stage stage, double x, double y, int health) {
		super(stage, x, y, health);
		name = "a steam vent";
		
		canMove(false);
		canTurn(false);
		canAttack(true);
		
		flying = false;
		frictionless = false;
		friendly = false;
		
		pushability = 0;
		timer = 0;
	}
	
	public void update() {
		if(currentAnimation == DEATH)
		{
			super.update();
			if(animation.hasPlayedOnce())
			{
				dead = true;
			}
			return;
		}

		super.update();
		
		if(canAttack() && timer == DURATION) {
			stage.getProjectiles().add(new SteamCloud(this, getX(), getY(), 0, 
					(int)(Math.random() * SteamCloud.SPEED + 1), 0));
			timer = 0;
		}
		timer++;
		
	}
	
	public void render(Graphics2D g) {
		
	}
	
	public Shape getHitbox() {
		return new Ellipse2D.Double(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
	}
	
	public boolean isActive() {
		return true;
	}
	
	public void die() {
		
	}

}
