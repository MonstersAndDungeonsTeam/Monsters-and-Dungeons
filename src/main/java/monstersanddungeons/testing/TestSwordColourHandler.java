package monstersanddungeons.testing;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class TestSwordColourHandler implements IItemColor{

	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) 
	{
		int colour = 0;

		if(stack.hasTagCompound())
		{
			switch(tintIndex)
			{
			case 0:
				colour = EnumColours.swordColour.getEnumFrom(stack.getTagCompound().getInteger("blade_value")).getRBGValue();
				break;
			case 1:
				colour = EnumColours.hiltColour.getEnumFrom(stack.getTagCompound().getInteger("hilt_value")).getRBGValue();
				break;
			case 2:
				colour = EnumColours.handleColour.getEnumFrom(stack.getTagCompound().getInteger("handle_value")).getRBGValue();
				break;
			}
		}
		
		return colour;
	}

}
