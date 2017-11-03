package ruukas.monstersanddungeons.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.BlockSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileHelmet extends TileEntity implements ITickable
{
    private int skullRotation;
    private ItemStack helmet;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setByte("Rot", (byte)(this.skullRotation & 255));

        if (this.helmet != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            helmet.writeToNBT(nbttagcompound);
            compound.setTag("Owner", nbttagcompound);
        }

        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.skullRotation = compound.getByte("Rot");

        if (compound.hasKey("Helmet", NBT.TAG_COMPOUND))
        {
            helmet = new ItemStack(compound.getCompoundTag("Helmet"));
        }
    }
    
    public ItemStack getItemStack(){
    	return this.helmet;
    }

    public void update()
    {
        //Perhaps some of the helmets need animation?
    }

    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 4, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }


    @SideOnly(Side.CLIENT)
    public int getSkullRotation()
    {
        return this.skullRotation;
    }

    public void setSkullRotation(int rotation)
    {
        this.skullRotation = rotation;
    }

    public void mirror(Mirror mirrorIn)
    {
        if (this.world != null && this.world.getBlockState(this.getPos()).getValue(BlockSkull.FACING) == EnumFacing.UP)
        {
            this.skullRotation = mirrorIn.mirrorRotation(this.skullRotation, 16);
        }
    }

    public void rotate(Rotation rotationIn)
    {
        if (this.world != null && this.world.getBlockState(this.getPos()).getValue(BlockSkull.FACING) == EnumFacing.UP)
        {
            this.skullRotation = rotationIn.rotate(this.skullRotation, 16);
        }
    }
}