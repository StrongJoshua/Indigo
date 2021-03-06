package indigo.Manager;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Handles data files (i.e. images): their paths, usage, and general info.
 */
public class Content
{
	// Cursor
	public static BufferedImage CURSOR = load("/cursor/crosshair.png", 32, 32);

	// HUD
	public static BufferedImage INDICATOR = load("/hud/indicator.png", 100, 100);
	public static BufferedImage PLAYER_HUD = load("/hud/player_hud.png", 350, 100);
	public static BufferedImage POINTER = load("/hud/pointer.png", 100, 7);
	
	// Items
	public static BufferedImage[] HEALTH_PICKUP_DEATH = loadArray("/items/health_pickup/death.png", 110, 110, 5);
	public static BufferedImage[] HEALTH_PICKUP_IDLE = loadArray("/items/health_pickup/idle.png", 110, 110, 4);
	public static BufferedImage[] HEALTH_PICKUP_SPAWN = loadArray("/items/health_pickup/spawn.png", 110, 110, 5);
	public static BufferedImage[] MANA_PICKUP = loadArray("/items/mana_pickup.png", 100, 100, 1);

	// Menus
	public static BufferedImage BACK_BUTTON = load("/menus/back_button.png", 200, 60);
	public static BufferedImage CONFIRM_BUTTON = load("/menus/confirm_button.png", 200, 60);
	public static BufferedImage CREDITS_BACKGROUND = load("/menus/credits_background.png", 1920, 1080);
	public static BufferedImage CREDITS_BUTTON = load("/menus/credits.png", 250, 100);
	public static BufferedImage CREDITS_BUTTON_HOVER = load("/menus/credits_hover.png", 250, 100);
	public static BufferedImage EXIT_BUTTON_HOVER = load("/menus/exit_hover.png", 130, 100);
	public static BufferedImage EXIT_BUTTON = load("/menus/exit.png", 130, 100);
	public static BufferedImage GLOW = load("/menus/glow.png", 500, 160);
	public static BufferedImage HELP_BUTTON = load("/menus/help.png", 160, 100);
	public static BufferedImage HELP_BUTTON_HOVER = load("/menus/help_hover.png", 160, 100);
	public static BufferedImage INSTRUCTIONS_BACKGROUND = load("/menus/instructions_background.png", 1920, 1080);
	public static BufferedImage MENU_BACKGROUND = load("/menus/menu_background.png", 1920, 1080);
	public static BufferedImage OPTIONS_BACKGROUND = load("/menus/options_background.png", 1920, 1080);
	public static BufferedImage OPTIONS_BUTTON = load("/menus/options.png", 280, 100);
	public static BufferedImage OPTIONS_BUTTON_HOVER = load("/menus/options_hover.png", 280, 100);
	public static BufferedImage PLAY_BUTTON = load("/menus/play.png", 150, 100);
	public static BufferedImage PLAY_BUTTON_HOVER = load("/menus/play_hover.png", 150, 100);
	public static BufferedImage SELECT_BAR = load("/menus/select_bar.png", 268, 46);
	public static BufferedImage STAGE_SELECT_BACKGROUND = load("/menus/stage_select_background.png", 1920, 1080);
	public static BufferedImage TALENTS_BACKGROUND = load("/menus/talents.png", 1920, 1080);
	public static BufferedImage TITLE = load("/menus/title.png", 800, 382);

	// Projectiles
	public static BufferedImage[] ELECTRIC_BALL = loadArray("/projectiles/electric_ball.png", 100, 100, 1);
	public static BufferedImage[] ELECTRIC_SPARK = loadArray("/projectiles/electric_spark.png", 100, 100, 1);
	public static BufferedImage[] GEYSER_BASE = loadArray("/projectiles/geyser_base.png", 100, 100, 1);
	public static BufferedImage[] GEYSER = loadArray("/projectiles/geyser_particle.png", 80, 50, 1);
	public static BufferedImage[] ICICLE = loadArray("/projectiles/icicle/default.png", 160, 73, 1);
	public static BufferedImage[] MORTAR_DEATH = loadArray("/projectiles/mortar_death.png", 100, 100, 9);
	public static BufferedImage[] MORTAR = loadArray("/projectiles/mortar.png", 50, 50, 1);
	public static BufferedImage[] PULSE_WAVE = loadArray("/projectiles/pulse_wave/default.png", 1000, 1000, 1);
	public static BufferedImage[] WATER_PROJECTILE_DEATH = loadArray("/projectiles/water_projectile/death.png", 80, 73, 2);
	public static BufferedImage[] WATER_PROJECTILE_DEATH_WALL = loadArray("/projectiles/water_projectile/death_wall.png", 80, 73, 2);
	public static BufferedImage[] WATER_PROJECTILE = loadArray("/projectiles/water_projectile/default.png", 80, 73, 2);

	// Sprites
	public static BufferedImage[] PLAYER_CROUCH_LEFT = loadArray("/sprites/player/crouch_left.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_CROUCH_LEFT_ARMOR = loadArray("/sprites/player/crouch_left_armor.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_CROUCH_RIGHT = loadArray("/sprites/player/crouch_right.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_CROUCH_RIGHT_ARMOR = loadArray("/sprites/player/crouch_right_armor.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_DEATH_LEFT = loadArray("/sprites/player/death_left.png", 68, 111, 15);
	public static BufferedImage[] PLAYER_DEATH_LEFT_ARMOR = loadArray("/sprites/player/death_left_armor.png", 68, 111, 15);
	public static BufferedImage[] PLAYER_DEATH_RIGHT = loadArray("/sprites/player/death_right.png", 68, 111, 15);
	public static BufferedImage[] PLAYER_DEATH_RIGHT_ARMOR = loadArray("/sprites/player/death_right_armor.png", 68, 111, 15);
	public static BufferedImage[] PLAYER_IDLE_LEFT = loadArray("/sprites/player/idle_left.png", 68, 111, 6);
	public static BufferedImage[] PLAYER_IDLE_LEFT_ARMOR = loadArray("/sprites/player/idle_left_armor.png", 68, 111, 6);
	public static BufferedImage[] PLAYER_IDLE_RIGHT = loadArray("/sprites/player/idle_right.png", 68, 111, 6);
	public static BufferedImage[] PLAYER_IDLE_RIGHT_ARMOR = loadArray("/sprites/player/idle_right_armor.png", 68, 111, 6);
	public static BufferedImage[] PLAYER_JUMP_LEFT = loadArray("/sprites/player/jump_left.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_JUMP_LEFT_ARMOR = loadArray("/sprites/player/jump_left_armor.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_JUMP_RIGHT = loadArray("/sprites/player/jump_right.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_JUMP_RIGHT_ARMOR = loadArray("/sprites/player/jump_right_armor.png", 68, 111, 1);
	public static BufferedImage[] PLAYER_MIST = loadArray("/sprites/player/mist.png", 68, 111, 5);
	public static BufferedImage[] PLAYER_MOVE_LEFT = loadArray("/sprites/player/move_left.png", 68, 111, 8);
	public static BufferedImage[] PLAYER_MOVE_LEFT_ARMOR = loadArray("/sprites/player/move_left_armor.png", 68, 111, 8);
	public static BufferedImage[] PLAYER_MOVE_RIGHT = loadArray("/sprites/player/move_right.png", 68, 111, 8);
	public static BufferedImage[] PLAYER_MOVE_RIGHT_ARMOR = loadArray("/sprites/player/move_right_armor.png", 68, 111, 8);
	public static BufferedImage BOT_CANNON = load("/sprites/bot/cannon.png", 18, 11);
	public static BufferedImage[] BOT_DEATH = loadArray("/sprites/bot/death.png", 60, 60, 6);
	public static BufferedImage[] BOT_IDLE = loadArray("/sprites/bot/idle.png", 60, 60, 4);
	public static BufferedImage[] TURRET_DEATH = loadArray("/sprites/turret_death.png", 100, 130, 31);
	public static BufferedImage[] TURRET_IDLE = loadArray("/sprites/turret_idle.png", 100, 130, 1);

	// Stages
	public static BufferedImage STAGE_BEACH = load("/stages/beach.png", 6400, 1200);
	
	// Weapons
	public static BufferedImage[] STAFF_ATTACK_LEFT = loadArray("/weapon/staff/attack_left.png", 100, 90, 1);
	public static BufferedImage[] STAFF_ATTACK_RIGHT = loadArray("/weapon/staff/attack_right.png", 100, 90, 1);
	public static BufferedImage[] STAFF_CAST_LEFT = loadArray("/weapon/staff/cast_left.png", 100, 90, 6);
	public static BufferedImage[] STAFF_CAST_RIGHT = loadArray("/weapon/staff/cast_right.png", 100, 90, 6);
	public static BufferedImage[] STAFF_IDLE_LEFT = loadArray("/weapon/staff/idle_left.png", 100, 90, 1);
	public static BufferedImage[] STAFF_IDLE_RIGHT = loadArray("/weapon/staff/idle_right.png", 100, 90, 1);
	

	/**
	 * Loads an image.
	 * 
	 * @param s The path to the file.
	 * @param w The width of the image.
	 * @param h The height of the image.
	 * @return The loaded image.
	 */
	private static BufferedImage load(String s, int w, int h)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(Content.class.getResourceAsStream(s));
			img = img.getSubimage(0, 0, w, h);
			return img;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}

	/**
	 * Loads an array of images used together.
	 * 
	 * @param s The path to the file.
	 * @param w The width of the images.
	 * @param h The height of the images.
	 * @param frames The number of frames in the array.
	 * @return The array of loaded images.
	 */
	private static BufferedImage[] loadArray(String s, int w, int h, int frames)
	{
		BufferedImage[] img = new BufferedImage[frames];
		BufferedImage sheet = load(s, w * frames, h);
		try
		{
			for(int i = 0; i < frames; i++)
			{
				img[i] = sheet.getSubimage(i * w, 0, w, h);
			}
			return img;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}
}