package monstersanddungeons.blocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import monstersanddungeons.blocks.dungeonbuilder.BlockExit;
import monstersanddungeons.blocks.dungeonbuilder.BlockRotten;
import monstersanddungeons.blocks.dungeonbuilder.genblocks.GenBlockDoubleSlab;
import monstersanddungeons.blocks.dungeonbuilder.genblocks.GenBlockSlab;
import monstersanddungeons.blocks.dungeonbuilder.genblocks.GenBlockStairs;
import monstersanddungeons.blocks.dungeonbuilder.genblocks.GenBlocks;
import monstersanddungeons.blocks.miscellaneous.BlockEntityStatue;
import monstersanddungeons.blocks.miscellaneous.BlockStonePagoda;
import monstersanddungeons.items.genBlocks.ItemGenBlockSlab;
import monstersanddungeons.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class MaDBlocksHandler {

	public static Block blockExit, blockRotten, blockStonePagoda, blockEntityStatue;
	public static List<Block> genBlocks = new ArrayList<Block>();
	public static List<ItemGenBlockSlab> genSlab = new ArrayList<ItemGenBlockSlab>();


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		blockExit = new BlockExit("BlockExit");
		blockRotten = new BlockRotten("BlockRotten");
		blockEntityStatue = new BlockEntityStatue("BlockEntityStatue");
		blockStonePagoda = new BlockStonePagoda("Stone Pagoda");
		
		event.getRegistry().register(blockExit);
		event.getRegistry().register(blockRotten);
		event.getRegistry().register(blockEntityStatue);
		event.getRegistry().register(blockStonePagoda);
	}

	public static void registerRenders() {
		registerRender(blockExit);
		registerRender(blockRotten);
		registerRender(blockStonePagoda);
		registerRender(blockEntityStatue);

		for (int i = 0; i < genBlocks.size(); i++) {
			registerRender(genBlocks.get(i));
		}
		
		//Exit, Rotten, Pagoda - GameRegistry.register(new ItemBlock(this), getRegistryName());

	}

	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(
				Reference.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));

	}

	public static void genBlocks(String Directory) throws IOException {
		File OldZipFile = new File(Directory + "/" + Reference.NAME + "/madresources.zip");

		if (!OldZipFile.exists()) {
			OldZipFile.mkdirs();
			return;
		}

		ZipFile zipDirectory;
		try {
			zipDirectory = new ZipFile(OldZipFile);
		} catch (FileNotFoundException e) {
			return;
		}

		List<ZipEntry> missingBlockState = new ArrayList<ZipEntry>();
		List<ZipEntry> missingModels = new ArrayList<ZipEntry>();

		Enumeration<? extends ZipEntry> enumeration = zipDirectory.entries();

		while (enumeration.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) enumeration.nextElement();

			if (entry != null) {
				if (entry.getName().contains("/textures")) {
					if (entry.getName().endsWith(".png")) {

						String name = entry.getName().substring(36, entry.getName().length() - 4);

						if (name.contains("_stairs")) {
							Block block = new Block(Material.ROCK);
							BlockStairs block_stairs = new GenBlockStairs(block.getDefaultState(), name);

							genBlocks.add(block_stairs);

						} else if (name.contains("_slab")) {
							Block block = new GenBlockSlab(name, Material.CLOTH);
							Block Doubleblock = new GenBlockDoubleSlab(Material.CLOTH, "double_" + name);

							genBlocks.add(block);
							genBlocks.add(Doubleblock);

							ItemGenBlockSlab slab = (ItemGenBlockSlab) (new ItemGenBlockSlab((GenBlockSlab) block,
									(GenBlockSlab) block, (GenBlockSlab) Doubleblock, "item_" + name));
							genSlab.add(slab);

						} else {
							Block block = new GenBlocks(name);
							genBlocks.add(block);
						}

						boolean[] hasResources = hasModel(name, zipDirectory);

						if (!hasResources[0]) {
							missingBlockState.add(entry);
						}

						if (!hasResources[1]) {
							missingModels.add(entry);
						}
					}
				}
			}
		}

		if (!missingBlockState.isEmpty() || !missingModels.isEmpty()) {
			generateResources(OldZipFile, missingBlockState, missingModels);
		}

		zipDirectory.close();
	}

	private static boolean[] hasModel(String name, ZipFile file) {
		boolean[] missingResources = new boolean[2];

		Enumeration<? extends ZipEntry> enumeration = file.entries();

		while (enumeration.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) enumeration.nextElement();

			if (entry != null) {
				if (entry.getName().contains(name)) {
					if (entry.getName().contains("/blockstates/")) {
						missingResources[0] = true;

					} else if (entry.getName().contains("/models/")) {
						missingResources[1] = true;
					}
				}
			}
		}

		return missingResources;
	}

	private static boolean generateResources(File oldZipfile, List<ZipEntry> blockstateList, List<ZipEntry> modelList)
			throws IOException {

		File tempFile = new File(oldZipfile.getParent() + "/madresources_old.zip");
		tempFile.createNewFile();
		ZipInputStream oldInput = new ZipInputStream(new FileInputStream(oldZipfile));
		ZipOutputStream tempOut = new ZipOutputStream(new FileOutputStream(tempFile));

		byte[] buf = new byte[4096 * 1024];

		ZipEntry Oldentry = oldInput.getNextEntry();
		while (Oldentry != null) {
			String name = Oldentry.getName();

			tempOut.putNextEntry(new ZipEntry(name));
			int len;
			while ((len = oldInput.read(buf)) > 0) {
				tempOut.write(buf, 0, len);
			}

			Oldentry = oldInput.getNextEntry();
		}

		oldInput.close();
		tempOut.close();

		ZipInputStream tempIn = new ZipInputStream(new FileInputStream(tempFile));
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(oldZipfile));

		for (ZipEntry entry : blockstateList) {
			StringBuilder sb = new StringBuilder();
			String name = entry.getName().substring(36, entry.getName().length() - 4);

			ZipEntry newEntry = new ZipEntry("assets/" + Reference.MOD_ID_GenBlocks + "/blockstates/" + name + ".json");
			out.putNextEntry(newEntry);

			if (name.contains("_stairs")) {
				String blockstatejsonFile = Reference.genBlock_Stairs_Blockstate;

				blockstatejsonFile = blockstatejsonFile.replace("oak_stairs", Reference.MOD_ID_GenBlocks + ":" + name);
				blockstatejsonFile = blockstatejsonFile.replace("oak_outer_stairs",
						Reference.MOD_ID_GenBlocks + ":" + name + "_outer");
				blockstatejsonFile = blockstatejsonFile.replace("oak_inner_stairs",
						Reference.MOD_ID_GenBlocks + ":" + name + "_inner");

				sb.append(blockstatejsonFile);

				byte[] data = sb.toString().getBytes();
				out.write(data, 0, data.length);

			} else if (name.contains("_slab")) {
				String blockstatejsonFile = Reference.genBlocks_Slab_blockstate;

				blockstatejsonFile = blockstatejsonFile.replace("half_slab_oak",
						Reference.MOD_ID_GenBlocks + ":" + name + "_half");
				blockstatejsonFile = blockstatejsonFile.replace("upper_slab_oak",
						Reference.MOD_ID_GenBlocks + ":" + name + "_upper");

				sb.append(blockstatejsonFile);

				byte[] data = sb.toString().getBytes();
				out.write(data, 0, data.length);

			} else {

				String blockstatejsonFile = Reference.genBlocks_Block_blockstate;

				blockstatejsonFile = blockstatejsonFile.replace("dirt", Reference.MOD_ID_GenBlocks + ":" + name);

				sb.append(blockstatejsonFile);

				byte[] data = sb.toString().getBytes();
				out.write(data, 0, data.length);

			}
		}

		for (ZipEntry entry : modelList) {
			String name = entry.getName().substring(36, entry.getName().length() - 4);

			if (name.contains("_stairs")) {
				String stairreg = Reference.genBlocks_Stairs_model_reg;
				String stairInner = Reference.genBlocks_Stairs_model_inner;
				String stairOuter = Reference.genBlocks_Stairs_model_outer;

				StringBuilder sb1 = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();
				StringBuilder sb3 = new StringBuilder();

				ZipEntry newEntry1 = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/block/" + name + ".json");
				ZipEntry newEntryInner = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/block/" + name + "_inner" + ".json");
				ZipEntry newEntryOuter = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/block/" + name + "_outer" + ".json");
				ZipEntry newItemEntry = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/item/" + name + ".json");

				stairreg = stairreg.replace("blocks/planks_oak", Reference.MOD_ID_GenBlocks + ":" + name);
				sb1.append(stairreg);

				stairInner = stairInner.replace("blocks/planks_oak", Reference.MOD_ID_GenBlocks + ":" + name);
				sb2.append(stairInner);

				stairOuter = stairOuter.replace("blocks/planks_oak", Reference.MOD_ID_GenBlocks + ":" + name);
				sb3.append(stairOuter);

				byte[] data1 = sb1.toString().getBytes();
				byte[] data2 = sb2.toString().getBytes();
				byte[] data3 = sb3.toString().getBytes();

				out.putNextEntry(newEntry1);
				out.write(data1, 0, data1.length);

				out.putNextEntry(newEntryInner);
				out.write(data2, 0, data2.length);

				out.putNextEntry(newEntryOuter);
				out.write(data3, 0, data3.length);

				out.putNextEntry(newItemEntry);
				out.write(data1, 0, data1.length);

			} else if (name.contains("_slab")) {
				String slabHalf = Reference.genBlocks_Slab_model_half;
				String slabUpper = Reference.genBlocks_Slab_model_upper;

				StringBuilder sb1 = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();

				ZipEntry newEntryHalf = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/block/" + name + "_half" + ".json");
				ZipEntry newEntryUpper = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/block/" + name + "_upper" + ".json");
				ZipEntry newItemEntry = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/item/" + "item_" + name + ".json");

				slabHalf = slabHalf.replace("blocks/planks_oak", Reference.MOD_ID_GenBlocks + ":" + name);
				sb1.append(slabHalf);

				slabUpper = slabUpper.replace("blocks/planks_oak", Reference.MOD_ID_GenBlocks + ":" + name);
				sb2.append(slabUpper);

				byte[] data1 = sb1.toString().getBytes();
				byte[] data2 = sb2.toString().getBytes();

				out.putNextEntry(newEntryHalf);
				out.write(data1, 0, data1.length);

				out.putNextEntry(newEntryUpper);
				out.write(data2, 0, data2.length);

				out.putNextEntry(newItemEntry);
				out.write(data1, 0, data1.length);

			} else {
				String blockModel = Reference.genBlocks_Block_model;

				StringBuilder sb1 = new StringBuilder();

				ZipEntry newEntryHalf = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/block/" + name + ".json");
				ZipEntry newItemEntry = new ZipEntry(
						"assets/" + Reference.MOD_ID_GenBlocks + "/models/item/" + name + ".json");

				blockModel = blockModel.replace("blocks/dirt", Reference.MOD_ID_GenBlocks + ":" + name);
				sb1.append(blockModel);

				byte[] data1 = sb1.toString().getBytes();

				out.putNextEntry(newEntryHalf);
				out.write(data1, 0, data1.length);

				out.putNextEntry(newItemEntry);
				out.write(data1, 0, data1.length);
			}
		}

		ZipEntry TempEntry = tempIn.getNextEntry();

		while (TempEntry != null) {
			String name = TempEntry.getName();

			out.putNextEntry(new ZipEntry(name));
			int len;
			while ((len = tempIn.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			TempEntry = tempIn.getNextEntry();
		}

		out.close();
		tempIn.close();
		tempFile.delete();

		return true;
	}

}
