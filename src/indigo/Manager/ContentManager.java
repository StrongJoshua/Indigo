
package indigo.Manager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * This class is the content manager for Indigo. Call {@link ContentManager#getImage(ImageData)} or
 * {@link ContentManager#getAnimation(AnimationData)} with the respective static image or animation data to get and
 * cache the content, if it hasn't already. <br>
 * <br>
 * Call {@link #dispose()} whenever the stage is changed. To prevent RAM hogging.<br>
 * <br>
 * Conventions should dictate that {@link #dispose()} can be called at any given time, so images and animations should
 * be reattained from the cache every frame.
 * 
 * @author Jan Risse (a.k.a. <a href="http://strongjoshua.com">StrongJoshua</a>)
 *
 */
public class ContentManager
{
	// Cursor
	public static ImageData CURSOR = new ImageData("/cursor/crosshair.png", 32, 32);

	// HUD
	public static ImageData INDICATOR = new ImageData("/hud/indicator.png", 100, 100);
	public static ImageData PLAYER_HUD = new ImageData("/hud/player_hud.png", 350, 100);
	public static ImageData POINTER = new ImageData("/hud/pointer.png", 100, 7);

	// Items
	public static AnimationData HEALTH_PICKUP_DEATH = new AnimationData("/items/health_pickup/death.png", 110, 110, 5);
	public static AnimationData HEALTH_PICKUP_IDLE = new AnimationData("/items/health_pickup/idle.png", 110, 110, 4);
	public static AnimationData HEALTH_PICKUP_SPAWN = new AnimationData("/items/health_pickup/spawn.png", 110, 110, 5);
	public static AnimationData MANA_PICKUP = new AnimationData("/items/mana_pickup.png", 100, 100, 1);

	// Menus
	public static ImageData BACK_BUTTON = new ImageData("/menus/back_button.png", 200, 60);
	public static ImageData CONFIRM_BUTTON = new ImageData("/menus/confirm_button.png", 200, 60);
	public static ImageData CREDITS_BACKGROUND = new ImageData("/menus/credits_background.png", 1920, 1080);
	public static ImageData CREDITS_BUTTON = new ImageData("/menus/credits.png", 250, 100);
	public static ImageData CREDITS_BUTTON_HOVER = new ImageData("/menus/credits_hover.png", 250, 100);
	public static ImageData EXIT_BUTTON_HOVER = new ImageData("/menus/exit_hover.png", 130, 100);
	public static ImageData EXIT_BUTTON = new ImageData("/menus/exit.png", 130, 100);
	public static ImageData GLOW = new ImageData("/menus/glow.png", 500, 160);
	public static ImageData HELP_BUTTON = new ImageData("/menus/help.png", 160, 100);
	public static ImageData HELP_BUTTON_HOVER = new ImageData("/menus/help_hover.png", 160, 100);
	public static ImageData INSTRUCTIONS_BACKGROUND = new ImageData("/menus/instructions_background.png", 1920, 1080);
	public static ImageData MENU_BACKGROUND = new ImageData("/menus/menu_background.png", 1920, 1080);
	public static ImageData OPTIONS_BACKGROUND = new ImageData("/menus/options_background.png", 1920, 1080);
	public static ImageData OPTIONS_BUTTON = new ImageData("/menus/options.png", 280, 100);
	public static ImageData OPTIONS_BUTTON_HOVER = new ImageData("/menus/options_hover.png", 280, 100);
	public static ImageData PLAY_BUTTON = new ImageData("/menus/play.png", 150, 100);
	public static ImageData PLAY_BUTTON_HOVER = new ImageData("/menus/play_hover.png", 150, 100);
	public static ImageData SELECT_BAR = new ImageData("/menus/select_bar.png", 268, 46);
	public static ImageData STAGE_SELECT_BACKGROUND = new ImageData("/menus/stage_select_background.png", 1920, 1080);
	public static ImageData TALENTS_BACKGROUND = new ImageData("/menus/talents.png", 1920, 1080);
	public static ImageData TITLE = new ImageData("/menus/title.png", 800, 382);

	// Projectiles
	public static AnimationData ELECTRIC_BALL = new AnimationData("/projectiles/electric_ball.png", 100, 100, 1);
	public static AnimationData ELECTRIC_SPARK = new AnimationData("/projectiles/electric_spark.png", 100, 100, 1);
	public static AnimationData GEYSER_BASE = new AnimationData("/projectiles/geyser_base.png", 100, 100, 1);
	public static AnimationData GEYSER = new AnimationData("/projectiles/geyser_particle.png", 80, 50, 1);
	public static AnimationData ICICLE = new AnimationData("/projectiles/icicle/default.png", 160, 73, 1);
	public static AnimationData MORTAR_DEATH = new AnimationData("/projectiles/mortar_death.png", 100, 100, 9);
	public static AnimationData MORTAR = new AnimationData("/projectiles/mortar.png", 50, 50, 1);
	public static AnimationData PULSE_WAVE = new AnimationData("/projectiles/pulse_wave/default.png", 1000, 1000, 1);
	public static AnimationData WATER_PROJECTILE_DEATH = new AnimationData("/projectiles/water_projectile/death.png",
			80, 73, 2);
	public static AnimationData WATER_PROJECTILE_DEATH_WALL = new AnimationData(
			"/projectiles/water_projectile/death_wall.png", 80, 73, 2);
	public static AnimationData WATER_PROJECTILE = new AnimationData("/projectiles/water_projectile/default.png", 80,
			73, 2);

	// Sprites
	public static AnimationData PLAYER_CROUCH_LEFT = new AnimationData("/sprites/player/crouch_left.png", 68, 111, 1);
	public static AnimationData PLAYER_CROUCH_LEFT_ARMOR = new AnimationData("/sprites/player/crouch_left_armor.png",
			68, 111, 1);
	public static AnimationData PLAYER_CROUCH_RIGHT = new AnimationData("/sprites/player/crouch_right.png", 68, 111, 1);
	public static AnimationData PLAYER_CROUCH_RIGHT_ARMOR = new AnimationData("/sprites/player/crouch_right_armor.png",
			68, 111, 1);
	public static AnimationData PLAYER_DEATH_LEFT = new AnimationData("/sprites/player/death_left.png", 68, 111, 15);
	public static AnimationData PLAYER_DEATH_LEFT_ARMOR = new AnimationData("/sprites/player/death_left_armor.png", 68,
			111, 15);
	public static AnimationData PLAYER_DEATH_RIGHT = new AnimationData("/sprites/player/death_right.png", 68, 111, 15);
	public static AnimationData PLAYER_DEATH_RIGHT_ARMOR = new AnimationData("/sprites/player/death_right_armor.png",
			68, 111, 15);
	public static AnimationData PLAYER_IDLE_LEFT = new AnimationData("/sprites/player/idle_left.png", 68, 111, 6);
	public static AnimationData PLAYER_IDLE_LEFT_ARMOR = new AnimationData("/sprites/player/idle_left_armor.png", 68,
			111, 6);
	public static AnimationData PLAYER_IDLE_RIGHT = new AnimationData("/sprites/player/idle_right.png", 68, 111, 6);
	public static AnimationData PLAYER_IDLE_RIGHT_ARMOR = new AnimationData("/sprites/player/idle_right_armor.png", 68,
			111, 6);
	public static AnimationData PLAYER_JUMP_LEFT = new AnimationData("/sprites/player/jump_left.png", 68, 111, 1);
	public static AnimationData PLAYER_JUMP_LEFT_ARMOR = new AnimationData("/sprites/player/jump_left_armor.png", 68,
			111, 1);
	public static AnimationData PLAYER_JUMP_RIGHT = new AnimationData("/sprites/player/jump_right.png", 68, 111, 1);
	public static AnimationData PLAYER_JUMP_RIGHT_ARMOR = new AnimationData("/sprites/player/jump_right_armor.png", 68,
			111, 1);
	public static AnimationData PLAYER_MIST = new AnimationData("/sprites/player/mist.png", 68, 111, 5);
	public static AnimationData PLAYER_MOVE_LEFT = new AnimationData("/sprites/player/move_left.png", 68, 111, 8);
	public static AnimationData PLAYER_MOVE_LEFT_ARMOR = new AnimationData("/sprites/player/move_left_armor.png", 68,
			111, 8);
	public static AnimationData PLAYER_MOVE_RIGHT = new AnimationData("/sprites/player/move_right.png", 68, 111, 8);
	public static AnimationData PLAYER_MOVE_RIGHT_ARMOR = new AnimationData("/sprites/player/move_right_armor.png", 68,
			111, 8);
	public static ImageData BOT_CANNON = new ImageData("/sprites/bot/cannon.png", 18, 11);
	public static AnimationData BOT_DEATH = new AnimationData("/sprites/bot/death.png", 60, 60, 6);
	public static AnimationData BOT_IDLE = new AnimationData("/sprites/bot/idle.png", 60, 60, 4);
	public static AnimationData TURRET_DEATH = new AnimationData("/sprites/turret_death.png", 100, 130, 31);
	public static AnimationData TURRET_IDLE = new AnimationData("/sprites/turret_idle.png", 100, 130, 1);

	// Stages
	public static ImageData STAGE_BEACH = new ImageData("/stages/beach.png", 6400, 1200);

	// Weapons
	public static AnimationData STAFF_ATTACK_LEFT = new AnimationData("/weapon/staff/attack_left.png", 100, 90, 1);
	public static AnimationData STAFF_ATTACK_RIGHT = new AnimationData("/weapon/staff/attack_right.png", 100, 90, 1);
	public static AnimationData STAFF_CAST_LEFT = new AnimationData("/weapon/staff/cast_left.png", 100, 90, 6);
	public static AnimationData STAFF_CAST_RIGHT = new AnimationData("/weapon/staff/cast_right.png", 100, 90, 6);
	public static AnimationData STAFF_IDLE_LEFT = new AnimationData("/weapon/staff/idle_left.png", 100, 90, 1);
	public static AnimationData STAFF_IDLE_RIGHT = new AnimationData("/weapon/staff/idle_right.png", 100, 90, 1);

	private static HashMap<ImageData, BufferedImage> imageMap;
	private static HashMap<AnimationData, BufferedImage[]> animationMap;

	static
	{
		imageMap = new HashMap<>();
		animationMap = new HashMap<>();
	}

	/**
	 * @param ad The ImageData to retrieve the image from. Should be attained from {@link ContentManager}.
	 * @return A BufferedImage composing the requested image.
	 */
	public static BufferedImage getImage(ImageData id)
	{
		BufferedImage img = imageMap.get(id);
		if(img != null)
			return img;
		img = load(id);
		imageMap.put(id, img);
		return img;
	}

	/**
	 * @param ad The AnimationData to retrieve the animation from. Should be attained from {@link ContentManager}.
	 * @return A BufferedImage[] composing the requested animation.
	 */
	public static BufferedImage[] getAnimation(AnimationData ad)
	{
		BufferedImage[] ani = animationMap.get(ad);
		if(ani != null)
			return ani;
		ani = load(ad);
		animationMap.put(ad, ani);
		return ani;
	}

	private static class ImageData
	{
		private String path;
		private int width, height;

		private ImageData(String path, int width, int height)
		{
			this.path = path;
			this.width = width;
			this.height = height;
		}
	}

	private static class AnimationData
	{
		private String path;
		private int width, height, frames;

		private AnimationData(String path, int width, int height, int frames)
		{
			this.path = path;
			this.width = width;
			this.height = height;
			this.frames = frames;
		}
	}

	private static BufferedImage load(ImageData id)
	{
		return load(id.path, id.width, id.height);
	}

	private static BufferedImage load(String path, int width, int height)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(Content.class.getResourceAsStream(path));
			img = img.getSubimage(0, 0, width, height);
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

	private static BufferedImage[] load(AnimationData ad)
	{
		BufferedImage[] img = new BufferedImage[ad.frames];
		BufferedImage sheet = load(ad.path, ad.width * ad.frames, ad.height);
		try
		{
			for(int i = 0; i < ad.frames; i++)
			{
				img[i] = sheet.getSubimage(i * ad.width, 0, ad.width, ad.height);
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

	/**
	 * Clears the image and animation cache. To be called between stages, but should conventionally be able to be called
	 * at any time.
	 */
	public static void dispose()
	{
		imageMap.clear();
		animationMap.clear();
	}
}