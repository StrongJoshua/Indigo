package indigo.Skill;

import indigo.Phase.Phase;
import indigo.Projectile.GeyserParticle;
import indigo.Projectile.GeyserBase;
import indigo.Weapon.Staff;

public class Blizzard extends Skill
{
	private GeyserBase geyser;
	
	public Blizzard(Phase phase, int position)
	{
		super(phase, position);
		id = Skill.BLIZZARD;
	}
	
	public void update()
	{
		super.update();
		
		if(player.getMana() >= 2 && (input.mouseDown() || castTime < 10))
		{
			if(castTime == 0)
			{
				player.canAttack(false);
				geyser = new GeyserBase(player, playState.getMouseX(), playState.getMapY() - GeyserBase.HEIGHT / 2, 0, 0, 0);
				playState.getProjectiles().add(geyser);
				
				((Staff)player.getWeapon()).cast();
			}
			
			playState.getProjectiles().add(0, new GeyserParticle(player, geyser.getX(), playState.getMapY() - GeyserParticle.HEIGHT / 2 + GeyserParticle.SPEED, 0, -GeyserParticle.SPEED, GeyserParticle.DAMAGE));
			player.setMana(player.getMana() - 1); // TODO Revert
			
			((Staff)player.getWeapon()).holdCast();
		}
		else
		{
			endCast();
		}
	}
	
	public boolean canCast()
	{
		return player.getMana() >= 20 && player.canAttack();
	}
	
	public void endCast()
	{
		playState.getProjectiles().remove(geyser);
		geyser = null;
		player.canAttack(true);
		
		super.endCast();
	}
}