package ruukas.monstersanddungeons.block;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnderTorch extends BlockTorch{
	public BlockEnderTorch() {
		setHardness(0.0F);
		setLightLevel(0.632F);
		setSoundType(SoundType.SLIME);
	}
	//TODO Hitbox
	//TODO particles
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	/*
        EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        //double d3 = 0.22D;
        //double d4 = 0.27D;

        if (enumfacing.getAxis().isHorizontal())
        {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
        else
        {
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        */
    }
}