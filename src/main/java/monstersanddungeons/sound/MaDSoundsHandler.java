package monstersanddungeons.sound;

import monstersanddungeons.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class MaDSoundsHandler {
	public static SoundEvent automatonHurtStone;
	public static SoundEvent automatonHurtMetal;
	
	public static void init() {
		automatonHurtStone = new SoundEvent(new ResourceLocation(Reference.MODID, "automaton_stone_hurt"));
		automatonHurtMetal = new SoundEvent(new ResourceLocation(Reference.MODID, "automaton_metal_hurt"));
	}

}
