package monstersanddungeons.events;

import java.util.List;

import monstersanddungeons.client.ClientProxy;
import monstersanddungeons.entity.automatons.EntityAutomatonsRook;
import monstersanddungeons.entity.automatons.EntityAutomatonsRookBoss;
import monstersanddungeons.items.MaDItemsHandler;
import monstersanddungeons.items.armor.ArmorStat;
import monstersanddungeons.items.armor.ItemQuartzArmor;
import monstersanddungeons.stats.StatDamageSources;
import monstersanddungeons.stats.Stats;
import monstersanddungeons.util.entity.IMaDBoss;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonEventHandler {


	ResourceLocation old = new ResourceLocation("textures/gui/icons.png");

	@SubscribeEvent
	public void onEntityAttack(LivingAttackEvent event){
		if(event.getSource() != StatDamageSources.bonusDamage){
			if(event.getSource().getTrueSource() instanceof EntityPlayer){

				EntityPlayer attacker = (EntityPlayer)event.getSource().getTrueSource();
				float bonusDamage = 0;
				if(attacker.getHeldItemMainhand() != null && attacker.getHeldItemMainhand().getItem() == MaDItemsHandler.quartzWarhammer){
					double str = 0;

					for(ItemStack armor : attacker.getArmorInventoryList()){
						if(armor != null)
						{
							if(armor.getItem() instanceof ItemQuartzArmor){
								ItemQuartzArmor quartzArmor = (ItemQuartzArmor)armor.getItem();
								for(ArmorStat stat : quartzArmor.getArmorStats()){
									if(stat.getStat() == Stats.strength){
										str += stat.getPower();
									}
								}
							}
						}
					}

					bonusDamage += (str > 0)?(1+(str/4d)):0;
				}

				if(bonusDamage > 0){
					event.getEntityLiving().attackEntityFrom(StatDamageSources.bonusDamage, bonusDamage);
				}

				if(event.getEntityLiving() instanceof EntityAutomatonsRook)
				{
					if(event.getSource().getTrueSource().getLookVec().dotProduct(event.getEntity().getLookVec()) < 0.5)
					{
						if(event.isCancelable())
						{
							event.setCanceled(true);
						}
					}
				}else if(event.getEntityLiving() instanceof EntityAutomatonsRookBoss)	
				{
					if(event.getSource().getTrueSource().getLookVec().dotProduct(event.getEntity().getLookVec()) < 0.5 && ((EntityAutomatonsRookBoss) event.getEntityLiving()).getWeakenedCD() > 0)
					{

						if(event.isCancelable())
						{
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent e)
	{
		World world = e.player.world;
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(e.player, new AxisAlignedBB(e.player.getPosition().getX() - 50, e.player.getPosition().getY() - 50, e.player.getPosition().getZ() - 50, e.player.getPosition().getX() + 50, e.player.getPosition().getY() + 50, e.player.getPosition().getZ() + 50));

		for(Entity entity : entities)
		{
			if(entity instanceof IMaDBoss)
			{
				if(((IMaDBoss) entity).getIcon() != null)
					ClientProxy.shouldRenderHpBar = (IMaDBoss) entity;
				return;
			}
		}
		ClientProxy.shouldRenderHpBar = null;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderGameOverlayEvent(RenderGameOverlayEvent.Post e)
	{
		if(ClientProxy.shouldRenderHpBar != null)
		{
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.color(1f, 1f, 1f, 1f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(ClientProxy.shouldRenderHpBar.getBossName());
			GuiScreen.drawModalRectWithCustomSizedTexture(180, 10, 0,  0,  95, 16,  95, 16);

			Minecraft.getMinecraft().getTextureManager().bindTexture(ClientProxy.shouldRenderHpBar.getIcon());
			GuiScreen.drawModalRectWithCustomSizedTexture(140, 30, ClientProxy.shouldRenderHpBar.getStartingX(true),  ClientProxy.shouldRenderHpBar.getStartingY(true),  ClientProxy.shouldRenderHpBar.getWidth(true), ClientProxy.shouldRenderHpBar.getHeight(true),  180, 50);
			GuiScreen.drawModalRectWithCustomSizedTexture(140, 30, ClientProxy.shouldRenderHpBar.getStartingX(false),  ClientProxy.shouldRenderHpBar.getStartingY(false),  ClientProxy.shouldRenderHpBar.getWidth(false), ClientProxy.shouldRenderHpBar.getHeight(false),  180, 50);
			GlStateManager.popMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(old);
		}
	}
}
