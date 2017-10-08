package monstersanddungeons.client.gui;

import java.io.IOException;
import java.util.List;

import monstersanddungeons.packet.GivePlayerItem;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.util.Reference;
import monstersanddungeons.util.entity.IMaDShopKeeper;
import monstersanddungeons.util.entity.ShopItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

public class GuiBasicShopKeeper extends GuiScreen 
{	

	int xSize, ySize;
	List<ShopItem> shopItems;
	IMaDShopKeeper shopKeeper;
	public static final ResourceLocation shop_gui = new ResourceLocation(Reference.MODID + ":textures/gui/GUIShopMaybe.png");

	public GuiBasicShopKeeper(IMaDShopKeeper shopKeeper) 
	{
		this.xSize = 256;
		this.ySize = 256;
		this.shopKeeper = shopKeeper;
		shopItems = shopKeeper.getSellList();
	}

	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initGui() 
	{
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		buttonList.clear();

		for(int i = 0; i < shopItems.size(); i ++)
		{
			if(i < 8)
			{
				ShopItem item = shopItems.get(i);
				if(item != null)
				{
					buttonList.add(new GuiButton(i, guiX + 60, guiY + 42 + 30* i, 35, 20, "Trade"));
				}
			}
		}
	}


	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(shop_gui);
		this.drawModalRectWithCustomSizedTexture(guiX , guiY, 0, 0, 256, 230, 256, 256);

		RenderHelper.enableGUIStandardItemLighting();
		this.fontRenderer.drawString("Trading Scroll", guiX + 100, guiY + 15, 0xffffff); 

		for(int i = 0; i < shopItems.size(); i ++)
		{
			if(i < 8)
			{
				ShopItem item = shopItems.get(i);
				if(item != null)
				{
					boolean triggerGray = item.getStock() == 0; 
					itemRender.renderItemAndEffectIntoGUI(item.getCost_item(), guiX + 100, guiY + 42 + 30* i);
					itemRender.renderItemAndEffectIntoGUI(item.getTrade_item(), guiX+ 160, guiY + 42 + 30 * i);	

					this.fontRenderer.drawString("x " + item.getCost_item().getCount() + " - >", guiX + 117, guiY + 50 + 30* i, 0xffffff); 
					this.fontRenderer.drawString("x " + item.getTrade_item().getCount(), guiX+ 177, guiY + 50 + 30 * i, 0xffffff); 
				}
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{

		switch(button.id)
		{
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			if(this.shopItems.get(button.id).canTradeItem(Minecraft.getMinecraft().player))
			{
				MaDPacketHandler.INSTANCE.sendToServer(new GivePlayerItem(shopKeeper.getShopID(), button.id));
			}else
			{
				Minecraft.getMinecraft().player.closeScreen();
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Dont waste my time..."));	
			}
			break;
		}

		super.actionPerformed(button);
	}
}
