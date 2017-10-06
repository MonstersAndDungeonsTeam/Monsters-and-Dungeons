package monstersanddungeons.world.dungeons_new;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNote;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import monstersanddungeons.entity.marshdwellers.EntityMarshDweller;
import monstersanddungeons.entity.marshdwellers.EntityMarshDwellerFisherman;
import monstersanddungeons.entity.marshdwellers.EntityMarshDwellerShaman;
import monstersanddungeons.tileentity.miscellaneous.TileEntityMonsterStatue;
import monstersanddungeons.util.dungeon_new.DungeonRoom_new;
import monstersanddungeons.util.dungeon_new.MaDDungeonBase;

public class VillageFrog extends MaDDungeonBase{


	List<BlockPos> wood_to_check = new ArrayList<BlockPos>();

	@Override
	public DungeonRoom_new getBossRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DungeonRoom_new getExit() {
		Random rand = new Random();

		switch(rand.nextInt(6))
		{
		case 1:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage11"); 
		case 2:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage18"); 
		case 3:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage22"); 
		default:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage18");
		}
	}

	@Override
	public DungeonRoom_new getRoom() {
		Random rand = new Random();

		switch(rand.nextInt(25))
		{
		case 1:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage"); // ore
		case 2:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage1"); // ore
		case 3:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage2"); // ore
		case 16:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage3"); // ore
		case 17:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage19"); // ore
		case 18:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage3"); // ore
		case 20:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage20"); // ore // ore
		case 19:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage9"); // ore
		case 4:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage4"); // ore
		case 5:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage5"); // ore
		case 6:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage6"); // ore
		case 7:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage7"); // ore
		case 8:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage8"); // ore
		case 9:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage9"); // ore
		case 10:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage10"); // ore
		case 11:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage12"); // ore
		case 12:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage13"); // ore
		case 13:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage14"); // ore
		case 14:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage15"); // ore
		case 15:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage9"); // ore
		case 21:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage23"); // ore
		case 22:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage21"); // ore

		default:
			return new DungeonRoom_new("assets/monstersanddungeons/generation/frogVillage/FrogVillage17"); // ore
		}
	}

	@Override
	public void constructDungeon(World world, BlockPos startingLocation, int DungeonSize) {

		System.out.println("building Frog");

		createRandomNetwork(DungeonSize);
		closeAllExits();
		buildDungeonInWorld(world, startingLocation);
		checkForPillars(world);
	}

	@Override
	public void applyBuildingEffect(IBlockState arena, BlockPos blockPos, World world, int currentY) 
	{
		Random rand = new Random();
		if(arena.getBlock() instanceof BlockChest)
		{
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos);
			LootTable table = world.getLootTableManager().getLootTableFromLocation(LootTableList.CHESTS_SIMPLE_DUNGEON);
			LootContext ctx = new LootContext.Builder((WorldServer) world).withLuck(2).build();
			table.fillInventory(chest, world.rand, ctx);
		}else
			if(arena.getBlock() instanceof BlockLog)
			{
				this.wood_to_check.add(blockPos);
			}else 
				if(arena.getBlock() instanceof BlockCauldron)
				{
					TileEntityMonsterStatue statue = null;
					EntityMarshDwellerShaman pawn = new EntityMarshDwellerShaman(world);
					pawn.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
					statue = new TileEntityMonsterStatue(pawn);
					world.setBlockState(blockPos, MaDBlocksHandler.BlockEntityStatue.getDefaultState());
					world.setTileEntity(blockPos, statue);
				}else
					if(arena.getBlock() instanceof BlockNote)
					{
						if(rand.nextInt(3) == 0)
						{
							TileEntityMonsterStatue statue = null;
							if(rand.nextInt(2) == 0)
							{
								EntityMarshDwellerFisherman pawn = new EntityMarshDwellerFisherman(world);
								pawn.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
								statue = new TileEntityMonsterStatue(pawn);

							}else
							{
								EntityMarshDweller pawn = new EntityMarshDweller(world);
								pawn.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
								statue = new TileEntityMonsterStatue(pawn);
							}
							world.setBlockState(blockPos, MaDBlocksHandler.BlockEntityStatue.getDefaultState());
							world.setTileEntity(blockPos, statue);
						}
					}
	}

	public void checkForPillars(World world)
	{
		for(BlockPos blockPos : wood_to_check)
		{
			IBlockState arena = world.getBlockState(blockPos);
			int wanted = 5;
			for(int i = 1; i < 7; i ++)
			{
				if(world.getBlockState(blockPos.up(i)).equals(arena))
				{
					wanted--;
				}
			}
			if(wanted <= 0 )
			{
				for(int i = 0; i < 70; i ++)
				{
					if(world.getBlockState(blockPos.down(i)).getBlock().equals(Blocks.AIR) || 
							world.getBlockState(blockPos.down(i)).getBlock().equals(Blocks.WATER) ||
							world.getBlockState(blockPos.down(i)).getBlock().equals(Blocks.LEAVES))
					{
						world.setBlockState(blockPos.down(i), arena);
					}
				}
			}
		}
	}

}
