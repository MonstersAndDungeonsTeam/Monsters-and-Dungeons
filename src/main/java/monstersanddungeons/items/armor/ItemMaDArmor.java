package monstersanddungeons.items.armor;

import java.util.List;

import monstersanddungeons.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemMaDArmor extends ItemArmor {
	protected String unlocalizedName;
	public ItemMaDArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.unlocalizedName = name;
		
		setUnlocalizedName(name);
		setRegistryName(name);
		
		GameRegistry.register(this);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Reference.MODID + ":textures/armor/" + unlocalizedName + ".png";
	}
	
	public List<ArmorStat> getArmorStats(){
		return null;
	}
	
	public List<ArmorStat> getSetBonusStats(){
		return null;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		if(getArmorMaterial() != null && !getArmorStats().isEmpty()){
			for(ArmorStat stat : getArmorStats()){
				tooltip.add(TextFormatting.BLUE + "+" + stat.getPower() + " " + stat.getStat().getName());
			}
		}
	}
}
