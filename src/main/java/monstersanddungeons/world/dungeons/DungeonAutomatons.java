package monstersanddungeons.world.dungeons;

import java.util.Random;

import monstersanddungeons.entity.automatons.EntityAutomatonsRook;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.util.dungeon.Dungeon;
import monstersanddungeons.util.dungeon.DungeonRoom;
import monstersanddungeons.util.dungeon.EnumDirection;
import monstersanddungeons.util.dungeon.ExitData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DungeonAutomatons  extends Dungeon{


	private DungeonRoom bossRoom = new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsBossRoom");


	public DungeonAutomatons(int dungeonSize)
	{
		super(dungeonSize);	
	}

	@Override
	public DungeonRoom selectRandomRoom()
	{
		Random rand = new Random();

		switch(rand.nextInt(25))
		{
		case 1:
		case 6:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom1");//corner
		case 7:
		case 11:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom2"); //straight
		case 12:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom3");
		case 13:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom5"); // straight
		case 14:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom6"); // corner
		case 15:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom7");
		case 16:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom10");
		case 17:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom11");
		case 18:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom12");
		case 19:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom15");
		case 20:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom16");
		case 21:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom17");
		case 22:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom18");
		case 23:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom19");
		case 24:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom20");
		default:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom7");
		}	
	}

	@Override
	public DungeonRoom selectRandomExit()
	{
		Random rand = new Random();

		switch(rand.nextInt(15))
		{
		case 1:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom4"); // ore
		case 2:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom14");
		case 3:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom9");
		case 4:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom21");
		case 5:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom13"); // ore
		default:
			return new DungeonRoom("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom8");
		}
	}


	@Override
	public void ConstructDungeon(World world, BlockPos startingLocation, int DungeonSize) {
		System.out.println("building");
		createBranch(world, startingLocation, DungeonSize, null, EnumDirection.EAST);

		boolean hasBuiltBossRoom = true;
		for(int i = 0; i < getTotalExits().size(); i ++)
		{
			Random rand = new Random();
			ExitData exit = getTotalExits().get(i);

			if(!exit.getAlreadyBuilt())
			{
				if(rand.nextInt(5) == 0)
				{
					createBranch(exit, world, 5); // use to be 4
				}else
				{
					closeBranch(exit, world, false);
				}
			}
		}
		for(int i = 0; i < getTotalExits().size(); i ++)
		{
			ExitData exit = getTotalExits().get(i);
			
			if(hasBuiltBossRoom)
			{
				if(hasEnoughRoom(exit, 100, 100))
				{
					closeBranch(exit, world, true);
					
					EntityAutomatonsRookBoss rook = new EntityAutomatonsRookBoss(world);
					BlockPos pos = exit.getPos();
					
					switch (exit.getDirection()) {
					case WEST:
						rook.setPosition(pos.getX() - 43, pos.getY(), pos.getZ());
						break;
					case EAST:
						rook.setPosition(pos.getX() + 43, pos.getY(), pos.getZ());
						break;
					case SOUTH:
						rook.setPosition(pos.getX(), pos.getY(), pos.getZ() + 43);
						break;
					case NORTH:
						rook.setPosition(pos.getX(), pos.getY(), pos.getZ() - 43);
						break;
					default:
						break;
					}
					world.spawnEntityInWorld(rook);
					
					hasBuiltBossRoom = false;
					DungeonRoom.clearDungeonRoom();
					getTotalExits().clear();
					return;
				}
			}
		}
	}

	@Override
	public DungeonRoom getBossRoom() {
		return bossRoom;
	}
}