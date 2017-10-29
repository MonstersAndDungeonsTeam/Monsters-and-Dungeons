package monstersanddungeons.items.weapon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemQuartzGreatSword extends ItemSword {

	public ItemQuartzGreatSword(ToolMaterial material, String name) {
		super(material);

		this.setCreativeTab(CreativeTabs.COMBAT);
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	
	/*
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
		

		if (slot == EntityEquipmentSlot.MAINHAND) {
		///	replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, 1.0);
		//	replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, -0.1);
		}

		return modifiers;
	}
	*/
	
	/*

	private void replaceModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double multiplier) {
		// Get the modifiers for the specified attribute
		final Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getAttributeUnlocalizedName());

		// Find the modifier with the specified ID, if any
		//	final Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();
		final Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (modifierOptional.isPresent()) { // If it exists,
			final AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier); // Remove it
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), multiplier, modifier.getOperation())); // Add the new modifier
		}
	}
	*/

	/* public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
		
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {

			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			if (stack.getTagCompound().hasKey(TAG_NAME_BLADE)) {
				EnumBlade enumBlade = EnumBlade.getEnumFromId(stack.getTagCompound().getInteger(TAG_NAME_BLADE));
				replaceModifier(multimap, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, enumBlade.attackspeed);
			}
			if (stack.getTagCompound().hasKey(TAG_NAME_CROSSGUARD)) {
				EnumBlade enumCrossguard = EnumBlade.getEnumFromId(stack.getTagCompound().getInteger(TAG_NAME_CROSSGUARD));
				replaceModifier(multimap, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, enumCrossguard.attackspeed);
			}
			if (stack.getTagCompound().hasKey(TAG_NAME_HILT)) {
				EnumBlade enumHilt = EnumBlade.getEnumFromId(stack.getTagCompound().getInteger(TAG_NAME_HILT));
				replaceModifier(multimap, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, enumHilt.attackspeed);
			}
		}
		return multimap;
	}
	*/

}
