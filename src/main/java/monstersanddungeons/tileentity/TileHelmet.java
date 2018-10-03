package monstersanddungeons.tileentity;

import javax.annotation.Nullable;

import monstersanddungeons.item.AllItems;
import monstersanddungeons.item.ItemArmorWasp;
import net.minecraft.block.BlockSkull;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileHelmet extends TileEntity
{
    private int helmetRotation;
    private ItemStack helmet = new ItemStack( AllItems.WASP_HEAD );
    
    public NBTTagCompound writeToNBT( NBTTagCompound compound )
    {
        super.writeToNBT( compound );
        compound.setByte( "Rot", (byte) (this.helmetRotation & 255) );
        
        if ( this.helmet != null )
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            helmet.writeToNBT( nbttagcompound );
            compound.setTag( "Helmet", nbttagcompound );
        }
        
        return compound;
    }
    
    public void readFromNBT( NBTTagCompound compound )
    {
        super.readFromNBT( compound );
        this.helmetRotation = compound.getByte( "Rot" );
        
        if ( compound.hasKey( "Helmet", NBT.TAG_COMPOUND ) )
        {
            helmet = new ItemStack( compound.getCompoundTag( "Helmet" ) );
        }
    }
    
    @Override
    public void onDataPacket( NetworkManager net, SPacketUpdateTileEntity pkt )
    {
        readFromNBT( pkt.getNbtCompound() );
    }
    
    public boolean setItemStack( ItemStack itemstack )
    {
        Item item = itemstack.getItem();
        
        if ( item instanceof ItemArmorWasp && ((ItemArmorWasp) item).getEquipmentSlot() == EntityEquipmentSlot.HEAD )
        {
            this.helmet = itemstack.copy();
            return true;
        }
        return false;
    }
    
    public ItemStack getItemStack()
    {
        return this.helmet;
    }
    
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity( this.pos, 4, this.getUpdateTag() );
    }
    
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT( new NBTTagCompound() );
    }
    
    @SideOnly( Side.CLIENT )
    public int getHelmetRotation()
    {
        return this.helmetRotation;
    }
    
    public void setHelmetRotation( int rotation )
    {
        this.helmetRotation = rotation;
    }
    
    public void mirror( Mirror mirrorIn )
    {
        if ( this.world != null && this.world.getBlockState( this.getPos() ).getValue( BlockSkull.FACING ) == EnumFacing.UP )
        {
            this.helmetRotation = mirrorIn.mirrorRotation( this.helmetRotation, 16 );
        }
    }
    
    public void rotate( Rotation rotationIn )
    {
        if ( this.world != null && this.world.getBlockState( this.getPos() ).getValue( BlockSkull.FACING ) == EnumFacing.UP )
        {
            this.helmetRotation = rotationIn.rotate( this.helmetRotation, 16 );
        }
    }
}