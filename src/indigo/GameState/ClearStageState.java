package indigo.GameState;

import indigo.Manager.GameStateManager;
import indigo.Manager.InputManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * The state present when the player has ended a state. Shown after each level. Can display a variety of information
 * based on the stage; retrieves information from Data.
 */
public class ClearStageState extends GameState
{
	/**
	 * Sets up the clear stage state.
	 * 
	 * @param gsm The game state manager.
	 */
	public ClearStageState(GameStateManager gsm)
	{
		super(gsm);
	}

	@Override
	public void update()
	{
		handleInput();
	}

	@Override
	public void render(Graphics2D g)
	{
		String message = "";
		if(data.getVictory())
		{
			message = "STAGE CLEAR";
		}
		else
		{

			message = data.getDeathMessage();
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		g.drawString(message, 150, 300);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		g.drawString("Time elapsed: " + Math.round(data.getClearTime() / 30.0) + " s", 150, 350);
		// TODO Draw background and other things
	}

	@Override
	public void handleInput()
	{
		// Change to whatever
		if(input.keyPress(InputManager.ESCAPE))
		{
			gsm.setState(GameStateManager.MENU);
			// Play sound
		}
		// TODO If the Data.victory is true and stage number is equal to Data.unlockedStages, increase
		// Data.stagesToUnlock by one
	}
}