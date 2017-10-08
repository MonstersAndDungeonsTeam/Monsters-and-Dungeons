package monstersanddungeons.world.dungeons_new;

import java.util.Random;

import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import monstersanddungeons.tileentity.miscellaneous.TileEntityMonsterStatue;
import monstersanddungeons.util.dungeon.EnumDirection;
import monstersanddungeons.util.dungeon_new.DungeonExit_new;
import monstersanddungeons.util.dungeon_new.DungeonRoom_new;
import monstersanddungeons.util.dungeon_new.MaDDungeonBase;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

public class DungeonAutomatons extends MaDDungeonBase{

	@Override
	public DungeonRoom_new getBossRoom() {
		// TODO Auto-generated method stub
		return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsBossRoomReal");
	}

	@Override
	public DungeonRoom_new getExit() {
		Random rand = new Random();

		switch(rand.nextInt(15))
		{
		case 1:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom4"); // ore
		case 2:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom14");
		case 3:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom9");
		case 4:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom21");
		case 5:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom13"); // ore
		default:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom8");
		}
	}

	@Override
	public DungeonRoom_new getRoom() {
		Random rand = new Random();

		switch(rand.nextInt(30))
		{
		case 1:
		case 6:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom1");//corner
		case 7:
		case 11:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom2"); //straight
		case 12:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom3");
		case 13:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom5"); // straight
		case 14:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom6"); // corner
		case 15:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom7");
		case 16:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom10");
		case 17:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom11");
		case 18:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom12");
		case 19:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom15");
		case 20:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom16");
		case 21:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom17");
		case 22:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom18");
		case 23:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom19");
		case 24:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom20");
		case 25:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom22");
		case 26:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom23");
		case 27:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom24");
		default:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/dungeonAutomatons/DungeonAutomatonsRoom7");
		}	
	}

	@Override
	public void constructDungeon(World world, BlockPos startingLocation, int DungeonSize) 
	{
		System.out.println("building: ");
		boolean placeBossRoom = false;
		createRandomNetwork(DungeonSize);

		for (int i = 0; i < getAll_exits().size(); i++) 
		{
			DungeonExit_new exit = getAll_exits().get(i);

			if(!getAll_exits().get(i).getLookedAt())
			{
				if(tryToCloseExit(getAll_exits().get(i), true))
				{
					EntityAutomatonsRookBoss rook = new EntityAutomatonsRookBoss(world);
					TileEntityMonsterStatue spawner = new TileEntityMonsterStatue(rook);
					BlockPos pos = new BlockPos(startingLocation.getX() + exit.getReal_position().getX(), startingLocation.getY() + exit.getReal_position().getY(), startingLocation.getZ() + exit.getReal_position().getZ());

					switch (exit.getFacing_direction()) {
					case WEST:
						rook.setPosition(pos.getX() - 43, pos.getY(), pos.getZ());
						pos = pos.west(43);
						break;
					case EAST:
						rook.setPosition(pos.getX() + 43, pos.getY(), pos.getZ());
						pos = pos.east(43);
						break;
					case SOUTH:
						rook.setPosition(pos.getX(), pos.getY(), pos.getZ() + 43);
						pos = pos.south(43);
						break;
					case NORTH:
						rook.setPosition(pos.getX(), pos.getY(), pos.getZ() - 43);
						pos = pos.north(43);
						break;
					}
					world.setBlockState(pos.up(5), MaDBlocksHandler.BlockEntityStatue.getDefaultState());
					world.setTileEntity(pos.up(5), spawner);
					break;
				}
			}
		}
		//	closeAllExits();
			
		DungeonExit_new forced_room = tryPlaceRoom(75, 20, 75, EnumDirection.EAST);
	//	DungeonExit_new exit = tryPlaceRoom(200, 20, 100, EnumDirection.WEST);
		
		if(forced_room != null)
		{
			for(DungeonExit_new exit: getAll_exits())
			{
				if(!exit.getLookedAt())
				{
					arena[forced_room.getReal_position().getX()][forced_room.getReal_position().getY()][forced_room.getReal_position().getZ()] = Blocks.DIAMOND_BLOCK.getDefaultState();
					arena[exit.getReal_position().getX()][exit.getReal_position().getY()][exit.getReal_position().getZ()] = Blocks.DIAMOND_BLOCK.getDefaultState();
					tryConnectExits(forced_room, exit);
					break;
				}
			}
		}
		
		buildDungeonInWorld(world, startingLocation);
		arena = null;
		this.getAll_exits().clear();
	}

	@Override
	public void applyBuildingEffect(IBlockState arena, BlockPos blockPos, World world, int currentY) 
	{
		Random rand = new Random();

		if(arena.getBlock() instanceof BlockChest)
		{
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos);
			LootTable table = world.getLootTableManager().getLootTableFromLocation(LootTableList.CHESTS_ABANDONED_MINESHAFT);
			LootContext ctx = new LootContext.Builder((WorldServer) world).withLuck(2).build();
			table.fillInventory(chest, world.rand, ctx);
		}
		if(arena.getBlock().equals(Blocks.AIR) && currentY == 22)
		{
			if(rand.nextInt(300) == 0)
			{
				TileEntityMonsterStatue statue = null;
				if(rand.nextInt(20) == 0)
				{
					EntityPawnCommander pawn = new EntityPawnCommander(world);
					pawn.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
					statue = new TileEntityMonsterStatue(pawn);

				}else
				{
					EntityWhitePawns pawn = new EntityWhitePawns(world);
					pawn.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
					statue = new TileEntityMonsterStatue(pawn);
				}
				world.setBlockState(blockPos, MaDBlocksHandler.BlockEntityStatue.getDefaultState());
				world.setTileEntity(blockPos, statue);
			}
		}
	}
}
