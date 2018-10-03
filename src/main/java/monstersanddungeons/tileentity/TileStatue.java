package monstersanddungeons.tileentity;

import net.minecraft.block.BlockSkull;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileStatue extends TileEntity
{
    private int rotation;
    private ModelBiped model = new ModelBiped();
    
    public NBTTagCompound writeToNBT( NBTTagCompound compound )
    {
        super.writeToNBT( compound );
        compound.setByte( "Rot", (byte) (this.rotation & 255) );
        
        return compound;
    }
    
    public void readFromNBT( NBTTagCompound compound )
    {
        super.readFromNBT( compound );
        this.rotation = compound.getByte( "Rot" );
    }
    
    @Override
    public void onDataPacket( NetworkManager net, SPacketUpdateTileEntity pkt )
    {
        readFromNBT( pkt.getNbtCompound() );
    }
    
    @SideOnly( Side.CLIENT )
    public int getHelmetRotation()
    {
        return this.rotation;
    }
    
    public void setHelmetRotation( int rotation )
    {
        this.rotation = rotation;
    }
    
    public void mirror( Mirror mirrorIn )
    {
        if ( this.world != null && this.world.getBlockState( this.getPos() ).getValue( BlockSkull.FACING ) == EnumFacing.UP )
        {
            this.rotation = mirrorIn.mirrorRotation( this.rotation, 16 );
        }
    }
    
    public void rotate( Rotation rotationIn )
    {
        if ( this.world != null && this.world.getBlockState( this.getPos() ).getValue( BlockSkull.FACING ) == EnumFacing.UP )
        {
            this.rotation = rotationIn.rotate( this.rotation, 16 );
        }
    }
    
    public ModelBase getModel()
    {
        return this.model;
    }
}