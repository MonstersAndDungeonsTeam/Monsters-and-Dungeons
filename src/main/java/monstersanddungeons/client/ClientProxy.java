package monstersanddungeons.client;

import java.io.File;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.client.models.items.ModelQuartzArmor;
import monstersanddungeons.entity.MaDEntityHandler;
import monstersanddungeons.events.KeyBindEvent;
import monstersanddungeons.items.MaDItemsHandler;
import monstersanddungeons.proxy.CommonProxy;
import monstersanddungeons.util.Reference;
import monstersanddungeons.util.entity.IMaDBoss;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



public class ClientProxy extends CommonProxy {

	public static File dataDirectory;
	public static BlockPos BlockPos1;
	public static BlockPos BlockPos2;
	public static int sphereInside, sphereOutside;
	
	public static IMaDBoss shouldRenderHpBar;
	public static List<IResourcePack> rPacks;
	
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		
		
		dataDirectory = new File(event.getModConfigurationDirectory(), Reference.NAME);
		/*
		try
		{
			Field field = Minecraft.class.getDeclaredField("defaultResourcePacks");
			field.setAccessible(true);
			
			rPacks =  (List<IResourcePack>) field.get(Minecraft.getMinecraft());
			rPacks.add(new ResourceLoader(new File(dataDirectory.getAbsolutePath() + "/madresources.zip")));
			Minecraft.getMinecraft().refreshResources();
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		*/
		

	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
	
		MinecraftForge.EVENT_BUS.register(new KeyBindEvent());
		
		Sphere sphere = new Sphere();

		//Set up paramters that are common to both outside and inside.
		//GLU_FILL as a solid.
		sphere.setDrawStyle(GLU.GLU_FILL);
		//GLU_SMOOTH will try to smoothly apply lighting
		sphere.setNormals(GLU.GLU_SMOOTH);
		//First make the call list for the outside of the sphere
		sphere.setOrientation(GLU.GLU_OUTSIDE);
		sphereOutside= GL11.glGenLists(1);
		//Create a new list to hold our sphere data.
		GL11.glNewList(sphereOutside, GL11.GL_COMPILE);
		//binds the texture 
		ResourceLocation rL = new ResourceLocation(Reference.MODID+":textures/entities/magic_circle.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(rL);
		//The drawing the sphere is automatically doing is getting added to our list. Careful, the last 2 variables
		//control the detail, but have a massive impact on performance. 32x32 is a good balance on my machine.s
		sphere.draw(0.5F, 32, 32);
		GL11.glEndList();
		//Now make the call list for the inside of the sphere
		sphere.setOrientation(GLU.GLU_INSIDE);
		sphereInside = GL11.glGenLists(1);
		//Create a new list to hold our sphere data.
		GL11.glNewList(sphereInside, GL11.GL_COMPILE);
		Minecraft.getMinecraft().getTextureManager().bindTexture(rL);
		sphere.draw(0.5F, 32, 32);
		GL11.glEndList();

		this.RegisterRenders();
	}

	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		
		
	}
	
	@Override
	public void RegisterRenders() {
		
		MaDBlocksHandler.registerRenders();
		MaDItemsHandler.registerRenders();
		MaDEntityHandler.registerRenders();
	}
		
	@Override
	public ModelBiped getArmorModel(int id) {
		return new ModelQuartzArmor(id);
	}
}
