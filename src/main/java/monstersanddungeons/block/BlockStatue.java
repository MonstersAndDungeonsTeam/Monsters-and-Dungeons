package monstersanddungeons.block;

import monstersanddungeons.tileentity.TileStatue;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStatue extends BlockContainer
{
    
    protected BlockStatue() {
        super( Material.CIRCUITS );
    }
    
    @Override
    public TileEntity createNewTileEntity( World worldIn, int meta )
    {
        return new TileStatue();
    }
    
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube( IBlockState state )
    {
        return false;
    }
    
    public boolean isFullCube( IBlockState state )
    {
        return false;
    }
    
    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape( IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face )
    {
        return BlockFaceShape.UNDEFINED;
    }
}
