package monstersanddungeons.items.armor;

import java.util.ArrayList;
import java.util.List;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.stats.Stats;
import monstersanddungeons.util.Reference;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemQuartzArmor extends ItemMaDArmor {
	private ArmorStat[] stats;
	
	public ItemQuartzArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name, ArmorStat[] stats) {
		super(materialIn, renderIndexIn, equipmentSlotIn, name);
		this.stats = stats;
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped model;

	@Override 	
	@SideOnly(Side.CLIENT)
		public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel) {
			if (stack != null) {
				if (stack.getItem() instanceof ItemArmor) {
					//EntityEquipmentSlot type = ((ItemArmor)stack.getItem()).armorType;
					ModelBiped armorModel = MonstersAndDungeons.proxy.getArmorModel(armorType.getIndex());
					
					armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
					armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
					armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST)
							|| (armorSlot == EntityEquipmentSlot.CHEST);
					armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
					armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
					armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
							|| (armorSlot == EntityEquipmentSlot.FEET);
					armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
							|| (armorSlot == EntityEquipmentSlot.FEET);

					armorModel.isSneak = defaultModel.isSneak;
					armorModel.isRiding = defaultModel.isRiding;
					armorModel.isChild = defaultModel.isChild;
					armorModel.rightArmPose = defaultModel.rightArmPose;
					armorModel.leftArmPose = defaultModel.leftArmPose;

					return armorModel;
				}
			}
			return null;
	}


	@Override
	public String getArmorTexture(ItemStack stack, Entity entity,
			EntityEquipmentSlot slot, String type) {
		// TODO Auto-generated method stub
		return Reference.MODID + ":textures/armor/Quartz Armour.png";
	}

	@Override
	public List<ArmorStat> getArmorStats() {
		List<ArmorStat> statList = new ArrayList<ArmorStat>();
		for(ArmorStat stat : stats){
			statList.add(stat);
		}
		return statList;
	}
	
	@Override
	public List<ArmorStat> getSetBonusStats() {
		List<ArmorStat> statList = new ArrayList<ArmorStat>();
		statList.add(new ArmorStat(Stats.strength));
		return statList;
	}
}
