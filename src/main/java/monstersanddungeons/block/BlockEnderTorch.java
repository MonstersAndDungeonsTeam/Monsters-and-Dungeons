package monstersanddungeons.block;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnderTorch extends BlockTorch
{
    public BlockEnderTorch() {
        setHardness( 0.0F );
        setLightLevel( 0.632F );
        setSoundType( SoundType.SLIME );
    }
    
    // TODO Hitbox
    // TODO particles
    @SideOnly( Side.CLIENT )
    @Override
    public void randomDisplayTick( IBlockState stateIn, World worldIn, BlockPos pos, Random rand )
    {
        
    }
}