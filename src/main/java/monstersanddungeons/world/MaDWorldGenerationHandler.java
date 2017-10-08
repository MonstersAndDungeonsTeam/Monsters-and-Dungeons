package monstersanddungeons.world;

import java.util.Random;

import monstersanddungeons.world.dungeons_new.VillageFrog;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MaDWorldGenerationHandler implements IWorldGenerator {


	boolean isConstructing = false;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int posX = chunkX * 16 + random.nextInt(16);
		int posZ = chunkZ * 16 + random.nextInt(16);
		int posY = 3;

		Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));
		if(!this.isConstructing)
		{
			if(biome.equals(Biomes.SWAMPLAND) || biome.equals(Biomes.ROOFED_FOREST))
			{
				if(random.nextInt(700) == 40)
				{
					posY = world.getTopSolidOrLiquidBlock(new BlockPos(posX, posY, posZ)).down(5).getY();
					this.isConstructing = true;
					VillageFrog dungeon = new VillageFrog();
					dungeon.constructDungeon(world, new BlockPos(posX, posY, posZ), random.nextInt(10) + 15);	
					this.isConstructing = false;
				}
			}
			/*else if(!biome.equals(Biomes.DEEP_OCEAN) && !biome.equals(Biomes.OCEAN) && !biome.equals(Biomes.HELL))
			{
				if(random.nextInt(1300) == 31)
				{
					this.isConstructing = true;
					DungeonAutomatons dungeon = new DungeonAutomatons();
					dungeon.constructDungeon(world, new BlockPos(posX, posY, posZ), random.nextInt(30) + 20);	
					this.isConstructing = false;
				}
			}*/
		}
	}
}
