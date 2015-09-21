package indigo.Stage;

import indigo.Entity.Entity;
import indigo.Entity.Player;
import indigo.GameState.PlayState;
import indigo.Landscape.Land;
import indigo.Landscape.Platform;
import indigo.Landscape.Wall;
import indigo.Main.Game;
import indigo.Manager.Data;
import indigo.Projectile.Projectile;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

// Shows the map, including platforms, entities, and projectiles
// Subclasses - When two walls are connected, initialize the vertical one before the horizontal one
public abstract class Stage
{
	protected PlayState playState;
	protected Data data;

	protected Player player;

	private int camX;
	private int camY;

	private int maxOffsetX;
	private int maxOffsetY;
	private int minOffsetX;
	private int minOffsetY;

	private double startingX;
	private double startingY;

	protected int mapX;
	protected int mapY;

	protected ArrayList<Entity> entities;
	protected ArrayList<Projectile> projectiles;
	protected ArrayList<Platform> platforms;
	protected ArrayList<Wall> walls;

	// Distance that entities are pushed when they collide with things - Fairly arbitrary
	public static final double PUSH_AMOUNT = 0.5;

	public static final double GRAVITY = 3;
	public static final double FRICTION = 2;
	public static final double TERMINAL_VELOCITY = 100; // Maximum value that x or y velocity can reach
	public static final double SKY_LIMIT = -1000;

	public Stage(PlayState playState)
	{
		this.playState = playState;
		data = playState.getData();

		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
		platforms = new ArrayList<Platform>();
		walls = new ArrayList<Wall>();
	}

	public void update()
	{
		for(int count = 0; count < entities.size(); count++)
		{
			Entity ent = entities.get(count);
			ent.update();

			// Collision loops
			if(ent.isActive())
			{
				// Entity-entity: Makes sure entities don't overlap
				for(int entCount = entities.indexOf(ent) + 1; entCount < entities.size(); entCount++)
				{
					Entity otherEnt = entities.get(entCount);

					if((otherEnt).isActive())
					{
						if(ent.intersects(otherEnt))
						{
							// Entities are pushed horizontally when colliding with each other
							if(ent.getX() < otherEnt.getX())
							{
								ent.setVelX(ent.getVelX() - ent.getPushability());
								otherEnt.setVelX(otherEnt.getVelX() + otherEnt.getPushability());
							}
							else
							{
								ent.setVelX(ent.getVelX() + ent.getPushability());
								otherEnt.setVelX(otherEnt.getVelX() - otherEnt.getPushability());
							}
							// Flying entities are also pushed vertically
							if(ent.isFlying())
							{
								if(ent.getY() < otherEnt.getY())
								{
									ent.setVelY(ent.getVelY() - ent.getPushability());
								}
								else
								{
									ent.setVelY(ent.getVelY() + ent.getPushability());
								}
							}
							if(otherEnt.isFlying())
							{
								if(ent.getY() < otherEnt.getY())
								{
									otherEnt.setVelY(otherEnt.getVelY() + otherEnt.getPushability());
								}
								else
								{
									otherEnt.setVelY(otherEnt.getVelY() - otherEnt.getPushability());
								}
							}
						}

						// Entity-melee: Melee weapon interactions
						if(ent.hasWeapon())
						{
							if(otherEnt.intersects(ent.getWeapon().getHitbox()))
							{
								ent.getWeapon().collide(otherEnt);
							}
							if(!otherEnt.isActive())
							{
								trackDeath(ent.getName(), otherEnt);
							}
						}
					}
				}

				Land ground = null;

				// Entity-platform: Landing on platforms
				if(ent.getVelY() >= 0 && !ent.isFlying())
				{
					for(Platform plat : platforms)
					{
						Line2D.Double feetTravel = new Line2D.Double(ent.getPrevX(), ent.getPrevY() + ent.getHeight()
								/ 2, ent.getX(), ent.getY() + ent.getHeight() / 2);

						if(feetTravel.intersectsLine(plat.getLine()))
						{
							ent.setY(plat.getSurface(ent.getX()) - ent.getHeight() / 2);
						}
						if(Math.round(ent.getY() + ent.getHeight() / 2) == Math.round(plat.getSurface(ent.getX()))
								&& ent.getX() > plat.getMinX() && ent.getX() < plat.getMaxX())
						{
							ground = plat;
						}
					}
				}

				// Entity-wall: Colliding with and landing on walls
				for(Wall wall : walls)
				{
					if(wall.killsEntities())
					{
						if(ent.intersects(wall.getLine()))
						{
							ent.die();
							trackDeath(wall.getName(), ent);
						}
					}
					if(wall.blocksEntities())
					{
						if(!wall.isHorizontal())
						{
							// Leftward collision into wall
							if(ent.isRightOfLine(wall.getLine()))
							{
								while(ent.intersects(wall.getLine()))
								{
									ent.setX(ent.getX() + PUSH_AMOUNT);
									ent.setVelX(Math.max(ent.getVelX(), 0));
								}
							}
							// Rightward collision into wall
							else
							{
								while(ent.intersects(wall.getLine()))
								{
									ent.setX(ent.getX() - PUSH_AMOUNT);
									ent.setVelX(Math.min(ent.getVelX(), 0));
								}
							}
						}
						else
						{
							// Downward collision into wall
							if(ent.isAboveLine(wall.getLine()))
							{
								if(ent.isFlying())
								{
									while(ent.intersects(wall.getLine()))
									{
										ent.setY(ent.getY() - PUSH_AMOUNT);
										ent.setVelY(Math.min(ent.getVelY(), 0));
									}
								}
								else
								{
									Line2D.Double line = new Line2D.Double(ent.getX(),
											ent.getY() - ent.getHeight() / 2, ent.getX(), ent.getY() + ent.getHeight()
													/ 2);

									if(line.intersectsLine(wall.getLine()))
									{
										ent.setY(wall.getSurface(ent.getX()) - ent.getHeight() / 2);
									}
									if(Math.round(ent.getY() + ent.getHeight() / 2) == Math.round(wall.getSurface(ent
											.getX())) && ent.getX() > wall.getMinX() && ent.getX() < wall.getMaxX())
									{
										ground = wall;
									}
								}
							}
							// Upward collision into wall
							else
							{
								while(ent.intersects(wall.getLine()))
								{
									ent.setY(ent.getY() + PUSH_AMOUNT);
									ent.setVelY(Math.max(ent.getVelY(), 0));
								}
							}
						}
					}
				}

				if(ground != null)
				{
					ent.setGround(ground);
				}
				else
				{
					ent.removeGround();
				}

				// Entity-projectile: Taking damage and tracking kills
				for(int projCount = 0; projCount < projectiles.size(); projCount++)
				{
					Projectile proj = projectiles.get(projCount);
					// Consider setting projectile location to intersection
					if((proj.isFriendly() != ent.isFriendly()) && proj.isActive() && ent.intersects(proj))
					{
						proj.collide(ent);
						if(!ent.isActive())
						{
							trackDeath(proj.getName(), ent);
						}
					}
				}
			}

			if(ent.isDead() || ent.getX() < 0 || ent.getX() > getMapX() || ent.getY() < SKY_LIMIT
					|| ent.getY() > getMapY())
			{
				if(count == 0)
				{
					playState.endGame();
				}
				entities.remove(entities.get(count));
				count--;
			}
		}

		for(int count = 0; count < projectiles.size(); count++)
		{
			Projectile proj = projectiles.get(count);
			proj.update();

			// Projectile-wall: Walls may block the projectile, kill the projectile, or both
			for(Wall wall : walls)
			{
				if(proj.isActive())
				{
					// TODO Proximity check
					if(wall.killsNonsolidProjectiles() && !proj.isSolid() && proj.intersects(wall.getLine()))
					{
						proj.die();
					}
					else if(wall.killsSolidProjectiles() && proj.isSolid() && proj.intersects(wall.getLine()))
					{
						proj.die();
					}
					if(wall.blocksNonsolidProjectiles() && !proj.isSolid() && proj.intersects(wall.getLine()))
					{
						double xInt = 0;
						double yInt = 0;

						// Calculate intersection point
						if(proj.getPrevX() != proj.getX())
						{
							double slope = (proj.getY() - proj.getPrevY()) / (proj.getX() - proj.getPrevX());
							double wallYInt = wall.getSlope() * -wall.getLine().getX1() + wall.getLine().getY1();
							double projYInt = -proj.getX() * slope + proj.getY();
							xInt = -(wallYInt - projYInt) / (wall.getSlope() - slope);
							yInt = xInt * slope + projYInt;
						}
						else
						{
							xInt = proj.getX();
							yInt = wall.getSlope() * (proj.getX() - wall.getLine().getX1()) + wall.getLine().getY1();
						}

						proj.setX(xInt);
						proj.setY(yInt);
						if(proj.isActive())
						{
							proj.collide(wall);
						}
					}
					else if(wall.blocksSolidProjectiles() && proj.isSolid() && proj.intersects(wall.getLine()))
					{
						double xInt = 0;
						double yInt = 0;

						// Calculate intersection point
						if(proj.getPrevX() != proj.getX())
						{
							double slope = (proj.getY() - proj.getPrevY()) / (proj.getX() - proj.getPrevX());
							double wallYInt = wall.getSlope() * -wall.getLine().getX1() + wall.getLine().getY1();
							double projYInt = -proj.getX() * slope + proj.getY();
							xInt = -(wallYInt - projYInt) / (wall.getSlope() - slope);
							yInt = xInt * slope + projYInt;
						}
						else
						{
							xInt = proj.getX();
							yInt = wall.getSlope() * (proj.getX() - wall.getLine().getX1()) + wall.getLine().getY1();
						}

						proj.setX(xInt);
						proj.setY(yInt);
						if(proj.isActive())
						{
							proj.collide(wall);
						}
					}
				}
			}

			if(proj.isDead() || proj.getX() < 0 || proj.getX() > getMapX() || proj.getY() < SKY_LIMIT
					|| proj.getY() > getMapY())
			{
				projectiles.remove(proj);
				count--;
			}
		}
	}

	public void trackDeath(String killer, Entity killed)
	{
		if(killed.equals(player))
		{
			data.setKiller(killer);
			data.setVictory(false);
		}
		else if(killed.isMarked())
		{
			// TODO Gain experienced - Add experience variable to Entity class
		}
	}

	// Updates the camera and renders everything
	// Subclasses need to render background, render targeting reticles, render projectiles, and render entities (in that
	// order)
	public abstract void render(Graphics2D g);

	// Updates camera reference point based on player position
	public void updateCam(Graphics2D g)
	{
		camX = (int)Math.round(player.getX()) - Game.WIDTH / 2;
		camY = (int)Math.round(player.getY()) - Game.HEIGHT / 2;
		if(camX > maxOffsetX)
		{
			camX = maxOffsetX;
		}
		else if(camX < minOffsetX)
		{
			camX = minOffsetX;
		}
		if(camY > maxOffsetY)
		{
			camY = maxOffsetY;
		}
		else if(camY < minOffsetY)
		{
			camY = minOffsetY;
		}
		g.translate(-camX, -camY);
	}

	// Consider changing - Inefficient
	public void resetCam(Graphics2D g)
	{
		g.translate(camX, camY);
	}

	// Returns mouse x position in game coordinates
	public double getMouseX()
	{
		return playState.getInput().mouseX() + camX;
	}

	// Returns mouse y position in game coordinates
	public double getMouseY()
	{
		return playState.getInput().mouseY() + camY;
	}

	// Sets camera boundaries when initializing the class
	public void setOffsets()
	{
		maxOffsetX = mapX - Game.WIDTH;
		maxOffsetY = mapY - Game.HEIGHT;
		minOffsetX = 0;
		minOffsetY = 0;
	}

	public Entity getPlayer()
	{
		return entities.get(0);
	}

	public ArrayList<Entity> getEntities()
	{
		return entities;
	}

	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}

	public ArrayList<Platform> getPlatforms()
	{
		return platforms;
	}

	public ArrayList<Wall> getWalls()
	{
		return walls;
	}

	public int getTime()
	{
		return playState.getTime();
	}

	public double getStartingX()
	{
		return startingX;
	}

	public double getStartingY()
	{
		return startingY;
	}

	public double getMapX()
	{
		return mapX;
	}

	public double getMapY()
	{
		return mapY;
	}
}