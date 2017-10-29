package monstersanddungeons.entity;

import java.util.Random;
import java.util.Set;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.client.models.ModelAutomatonsRook;
import monstersanddungeons.client.models.ModelAutomatonsRookBoss;
import monstersanddungeons.client.models.ModelCommanderPawn;
import monstersanddungeons.client.models.ModelEnt;
import monstersanddungeons.client.models.ModelMarshDweller;
import monstersanddungeons.client.models.ModelMarshDwellerFisherman;
import monstersanddungeons.client.models.ModelMarshDwellerShaman;
import monstersanddungeons.client.models.ModelWitePawns;
import monstersanddungeons.client.models.items.ModelQuartzBigSword;
import monstersanddungeons.client.renderer.RenderAutomatonsRook;
import monstersanddungeons.client.renderer.RenderAutomatonsRookBoss;
import monstersanddungeons.client.renderer.RenderCommanderPawn;
import monstersanddungeons.client.renderer.RenderEnt;
import monstersanddungeons.client.renderer.RenderFlyingSword;
import monstersanddungeons.client.renderer.RenderMarshDweller;
import monstersanddungeons.client.renderer.RenderMarshDwellerFisherman;
import monstersanddungeons.client.renderer.RenderMarshDwellerShaman;
import monstersanddungeons.client.renderer.RenderSafeZone;
import monstersanddungeons.client.renderer.RenderSunBeam;
import monstersanddungeons.client.renderer.RenderWhitePawns;
import monstersanddungeons.entity.automatons.EntityAutomatonsRook;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import monstersanddungeons.entity.marshdwellers.EntityMarshDweller;
import monstersanddungeons.entity.marshdwellers.EntityMarshDwellerFisherman;
import monstersanddungeons.entity.marshdwellers.EntityMarshDwellerShaman;
import monstersanddungeons.entity.miscellaneous.EntityFlyingSword;
import monstersanddungeons.entity.miscellaneous.EntitySafeZone;
import monstersanddungeons.entity.miscellaneous.EntitySunBeam;
import monstersanddungeons.entity.world.EntityEnt;
import monstersanddungeons.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MaDEntityHandler {

	private static int entityID = 0;

	public static void registerEntities() {
		registerEntity(EntityAutomatonsRook.class, "AutomatonsRook", 80, 3, false, 0xFFFFFF, 0x000000);
		registerEntity(EntityAutomatonsRookBoss.class, "AutomatonsRookBoss", 80, 3, false, 0xFFFFFF, 0xB200FF);


		registerEntity(EntitySafeZone.class, "EntitySafeZone", 80, 3, false, 0xFFFFFF, 0x000000);
		registerEntity(EntitySunBeam.class, "EntitySunBeam", 80, 3, false, 0xFFFFFF, 0x000000);
		registerEntity(EntityFlyingSword.class, "EntityFlyingSword", 80, 3, false, 0xFFFFFF, 0x000000);

		registerEntity(EntityWhitePawns.class, "WhitePawns", 80, 3, false, 0xFFFFFF, 0x808080);
		registerEntity(EntityPawnCommander.class, "PawnCommander", 80, 3, false, 0xFFFFFF, 0x000000);

		registerEntity(EntityMarshDweller.class, "MarshDweller", 80, 3, false, 0xFFFFFF, 0x000000);
		registerEntity(EntityMarshDwellerFisherman.class, "MarshDwellerFisherman", 80, 3, false, 0xFFFFFF, 0x000000);
		registerEntity(EntityMarshDwellerShaman.class, "MarshDwellerShaman", 80, 3, false, 0xFFFFFF, 0x000000);

		registerEntity(EntityEnt.class, "Ent", 80, 3, false, 0xFFFFFF, 0x000000);


	}

	public static void addSpawns() {
		Object[] biomeList = BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST).toArray();
	
		Biome[] biomeArray = new Biome[biomeList.length];
		for(int i=0;i<biomeList.length;i++){
			biomeArray[i] = (Biome)biomeList[i];
		}
		EntityRegistry.addSpawn(EntityEnt.class, 100, 1, 1, EnumCreatureType.AMBIENT, biomeArray);
	}


	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int mainColor, int offColpr) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, entityName), entityClass, entityName, entityID++, MonstersAndDungeons.instance, trackingRange, updateFrequency, sendsVelocityUpdates);

		long x = entityClass.hashCode();
		Random random = new Random(x);
		int MainColour = random.nextInt() * 16777215;
		int SubColour = random.nextInt() * 16777215;

		MaDEntityList.addMapping(entityClass, entityName, MainColour, SubColour);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityAutomatonsRook.class, new RenderAutomatonsRook(new ModelAutomatonsRook()));
		RenderingRegistry.registerEntityRenderingHandler(EntityAutomatonsRookBoss.class, new RenderAutomatonsRookBoss(new ModelAutomatonsRookBoss()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySafeZone.class, new RenderSafeZone());
		RenderingRegistry.registerEntityRenderingHandler(EntitySunBeam.class, new RenderSunBeam());

		RenderingRegistry.registerEntityRenderingHandler(EntityFlyingSword.class, new RenderFlyingSword(new ModelQuartzBigSword()));


		RenderingRegistry.registerEntityRenderingHandler(EntityPawnCommander.class, new RenderCommanderPawn(new ModelCommanderPawn()));
		RenderingRegistry.registerEntityRenderingHandler(EntityWhitePawns.class, new RenderWhitePawns(new ModelWitePawns()));

		RenderingRegistry.registerEntityRenderingHandler(EntityMarshDweller.class, new RenderMarshDweller(new ModelMarshDweller()));		
		RenderingRegistry.registerEntityRenderingHandler(EntityMarshDwellerFisherman.class, new RenderMarshDwellerFisherman(new ModelMarshDwellerFisherman()));		
		RenderingRegistry.registerEntityRenderingHandler(EntityMarshDwellerShaman.class, new RenderMarshDwellerShaman(new ModelMarshDwellerShaman()));		

		RenderingRegistry.registerEntityRenderingHandler(EntityEnt.class, new RenderEnt(new ModelEnt()));		
	}
}
