package monstersanddungeons.client.entity;

import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.world.World;

public class ParticleEmpowerPawns extends ParticleCloud {

	public ParticleEmpowerPawns(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i1221_8_, double p_i1221_10_, double p_i1221_12_) 
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i1221_8_, p_i1221_10_, p_i1221_12_);
		this.particleMaxAge = 20;
		this.particleGravity = 0;
		this.particleRed = 0;
		this.particleBlue = 1f;
	}

}
