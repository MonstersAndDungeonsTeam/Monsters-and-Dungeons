package monstersanddungeons.block;

import java.util.Random;

import monstersanddungeons.item.AllItems;
import monstersanddungeons.tileentity.TileHelmet;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings( "deprecation" )
public class BlockHelmet extends BlockContainer
{
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final PropertyBool NODROP = PropertyBool.create( "nodrop" );
    
    protected static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB( 0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D );
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB( 0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D );
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB( 0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D );
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB( 0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D );
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB( 0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D );
    
    protected BlockHelmet() {
        super( Material.CIRCUITS );
        this.setDefaultState( this.blockState.getBaseState().withProperty( FACING, EnumFacing.NORTH ).withProperty( NODROP, Boolean.valueOf( false ) ) );
    }
    
    /**
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName()
    {
        return I18n.translateToLocal( "tile.helmet.name" );
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
    
    @SideOnly( Side.CLIENT )
    public boolean hasCustomBreakingProgress( IBlockState state )
    {
        return true;
    }
    
    public AxisAlignedBB getBoundingBox( IBlockState state, IBlockAccess source, BlockPos pos )
    {
        switch ( (EnumFacing) state.getValue( FACING ) )
        {
            case UP:
            default:
                return DEFAULT_AABB;
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case EAST:
                return EAST_AABB;
        }
    }
    
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the IBlockstate
     */
    public IBlockState getStateForPlacement( World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer )
    {
        return this.getDefaultState().withProperty( FACING, placer.getHorizontalFacing() ).withProperty( NODROP, Boolean.valueOf( false ) );
    }
    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity( World worldIn, int meta )
    {
        return new TileHelmet();
    }
    
    @Override
    public ItemStack getPickBlock( IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player )
    {
        TileEntity tileentity = world.getTileEntity( pos );
        
        if ( tileentity instanceof TileHelmet )
        {
            return ((TileHelmet) tileentity).getItemStack().copy();
        }
        
        return super.getPickBlock( state, target, world, pos, player );
    }
    
    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect this block
     */
    public void onBlockHarvested( World worldIn, BlockPos pos, IBlockState state, EntityPlayer player )
    {
        if ( player.capabilities.isCreativeMode )
        {
            state = state.withProperty( NODROP, Boolean.valueOf( true ) );
            worldIn.setBlockState( pos, state, 4 );
        }
        this.dropBlockAsItem( worldIn, pos, state, 0 );
        
        super.onBlockHarvested( worldIn, pos, state, player );
    }
    
    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock( World worldIn, BlockPos pos, IBlockState state )
    {
        super.breakBlock( worldIn, pos, state );
    }
    
    public void getDrops( net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune )
    {
        if ( !((Boolean) state.getValue( NODROP )).booleanValue() )
        {
            TileEntity tileentity = worldIn.getTileEntity( pos );
            
            if ( tileentity instanceof TileHelmet )
            {
                TileHelmet tilehelmet = (TileHelmet) tileentity;
                drops.add( tilehelmet.getItemStack() );
            }
        }
    }
    
    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped( IBlockState state, Random rand, int fortune )
    {
        return AllItems.WASP_HEAD;
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta( int meta )
    {
        return this.getDefaultState().withProperty( FACING, EnumFacing.getFront( meta & 7 ) ).withProperty( NODROP, Boolean.valueOf( (meta & 8) > 0 ) );
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState( IBlockState state )
    {
        int i = 0;
        i = i | ((EnumFacing) state.getValue( FACING )).getIndex();
        
        if ( ((Boolean) state.getValue( NODROP )).booleanValue() )
        {
            i |= 8;
        }
        
        return i;
    }
    
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed blockstate.
     */
    public IBlockState withRotation( IBlockState state, Rotation rot )
    {
        return state.withProperty( FACING, rot.rotate( (EnumFacing) state.getValue( FACING ) ) );
    }
    
    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed blockstate.
     */
    public IBlockState withMirror( IBlockState state, Mirror mirrorIn )
    {
        return state.withRotation( mirrorIn.toRotation( (EnumFacing) state.getValue( FACING ) ) );
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer( this, new IProperty[] { FACING, NODROP } );
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