package monstersanddungeons.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.client.ClientProxy;
import monstersanddungeons.util.dungeon.DungeonNBTTag;
import monstersanddungeons.util.dungeon.EnumDirection;
import monstersanddungeons.util.dungeon.ExitData;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Structure {

	public static void StructureReaderFromWorld(World world, BlockPos point1, BlockPos point2){

		int howManyExits = 0;
		StructureData data = new StructureData();
		List<ExitData> totalExits = new ArrayList<ExitData>();

		if(point1 != null && point2 != null){
			int minX = point1.getX() > point2.getX() ? point2.getX() : point1.getX();
			int maxX = point1.getX() > point2.getX() ? point1.getX() : point2.getX();
			int minY = point1.getY() > point2.getY() ? point2.getY() : point1.getY();
			int maxY = point1.getY() > point2.getY() ? point1.getY() : point2.getY();
			int minZ = point1.getZ() > point2.getZ() ? point2.getZ() : point1.getZ();
			int maxZ = point1.getZ() > point2.getZ() ? point1.getZ() : point2.getZ();

			int placePosX = 0, placePosY = 0, placePosZ = 0;

			int xSize = maxX - minX + 1;
			int ySize = maxY - minY + 1;
			int zSize = maxZ - minZ + 1;

			data.blocks = new IBlockState[xSize][ySize][zSize];

			for(int x = 0; x < data.blocks.length; x++) {
				for(int y = 0; y <data.blocks[0].length; y++) {
					for(int z = 0; z <data.blocks[0][0].length; z++) {


						if(point1.getX() > point2.getX()){
							placePosX = point1.getX() - x;
						}else if(point1.getX() < point2.getX()){
							placePosX = point1.getX() + x;
						}
						placePosY = point1.getY() + y;

						if(point1.getZ() > point2.getZ()){
							placePosZ = point1.getZ() - z;
						}else if(point1.getZ() < point2.getZ()){
							placePosZ = point1.getZ() + z;
						}


						data.blocks[x][y][z] =  world.getBlockState(new BlockPos(placePosX, placePosY, placePosZ));


						if(world.getBlockState(new BlockPos(placePosX, placePosY, placePosZ)).getBlock().equals(MaDBlocksHandler.BlockExit))
						{
							totalExits.add(new ExitData(new BlockPos(x,y,z), EnumDirection.getDirectionFromBlockState(data.blocks[x][y][z]), false));
							howManyExits++;
						}
					}
				}
			}

			NBTTagCompound cmp = new NBTTagCompound();


			for(int x = 0; x < data.blocks.length; x++) {
				for(int y = 0; y < data.blocks[0].length; y++) {
					for(int z = 0; z < data.blocks[0][0].length; z++) {


						if(!data.blocks[x][y][z].equals(Blocks.AIR.getDefaultState())){

							cmp.setInteger(x + "," + y + "," + z, Block.getStateId(data.blocks[x][y][z]));		
						}
					}
				}
			}
			for(int i = 0; i < totalExits.size(); i ++)
			{
				cmp.setInteger("ExitNumberX" + i, totalExits.get(i).getPos().getX());
				cmp.setInteger("ExitNumberY" + i, totalExits.get(i).getPos().getY());
				cmp.setInteger("ExitNumberZ" + i, totalExits.get(i).getPos().getZ());
				cmp.setString("ExitDirection" + i, totalExits.get(i).getDirection().getDirection());
			}

			cmp.setInteger("TotalExits", howManyExits);
			cmp.setInteger("MaxXValue", data.blocks.length);
			cmp.setInteger("MaxYValue", data.blocks[0].length);
			cmp.setInteger("MaxZValue", data.blocks[0][0].length);

			File file = DungeonNBTTag.createOrGetNBTFile(new File(ClientProxy.dataDirectory, "RecentlyCreated"));
			DungeonNBTTag.injectNBTToFile(cmp, file);

		}
	}
}



