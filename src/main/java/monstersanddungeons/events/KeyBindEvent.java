package monstersanddungeons.events;

import org.lwjgl.input.Keyboard;

import monstersanddungeons.client.ClientProxy;
import monstersanddungeons.util.Structure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyBindEvent {


	static KeyBinding key = new KeyBinding("KOTRT save to File", Keyboard.KEY_L, "key.categories.misc");
	static KeyBinding key1 = new KeyBinding("KOTRT Point 1", Keyboard.KEY_K, "key.categories.misc");
	static KeyBinding key2 = new KeyBinding("KOTRT Point 2", Keyboard.KEY_J, "key.categories.misc");
	static KeyBinding key3 = new KeyBinding("KOTRT Save Menu", Keyboard.KEY_H, "key.categories.misc");

	public KeyBindEvent() {
		ClientRegistry.registerKeyBinding(key);
		ClientRegistry.registerKeyBinding(key1);
		ClientRegistry.registerKeyBinding(key2);
		ClientRegistry.registerKeyBinding(key3);
	}

	@SubscribeEvent
	public void onKeyInput(final InputEvent event) {

		if(key.isPressed()){
			
			//will set blockPos 1, (L)
			ClientProxy.BlockPos1 = new BlockPos(Minecraft.getMinecraft().player.getPosition().getX(), Minecraft.getMinecraft().player.getPosition().getY(), Minecraft.getMinecraft().player.getPosition().getZ());
			
			System.out.println("L:" +ClientProxy.BlockPos1);
			System.out.println("Stair: " + Minecraft.getMinecraft().world.getBlockState(new BlockPos(-609, 57, -361)).getBlock().getMetaFromState(Minecraft.getMinecraft().world.getBlockState(new BlockPos(-609, 57, -361))));
		}

		if(key1.isPressed()){
			//will set blockPos 2 (K)
			ClientProxy.BlockPos2 = new BlockPos(Minecraft.getMinecraft().player.getPosition().getX(), Minecraft.getMinecraft().player.getPosition().getY(), Minecraft.getMinecraft().player.getPosition().getZ());
			System.out.println("K:" +ClientProxy.BlockPos2);
		}

		if(key2.isPressed()){
			//will build the file and stuff (J)
			Structure.StructureReaderFromWorld(Minecraft.getMinecraft().world, ClientProxy.BlockPos1, ClientProxy.BlockPos2);
		}
	}
}





