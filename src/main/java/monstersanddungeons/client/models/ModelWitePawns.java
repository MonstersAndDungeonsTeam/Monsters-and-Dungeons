package monstersanddungeons.client.models;

import java.util.List;

import monstersanddungeons.client.entity.ParticleEmpowerPawns;
import monstersanddungeons.client.models.items.ModelQuartzWarHammer;
import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class ModelWitePawns extends MaDEntityModelBase {

	ModelQuartzWarHammer hammer = new ModelQuartzWarHammer(-6);
	ModelRenderer head;
	ModelRenderer neck;
	ModelRenderer body;
	ModelRenderer neck2;
	ModelRenderer Nose;
	ModelRenderer EyePiece;
	ModelRenderer EyePieceTwo;
	ModelRenderer EyePieceThree;
	ModelRenderer EyePieceFour;
	ModelRenderer rightFoot;
	ModelRenderer leftFoot;
	ModelRenderer rightArm;
	ModelRenderer leftArm;
	ModelRenderer rightKnee;
	ModelRenderer leftKnee;

	//left arm XandY, right X, chect X, hammer, X
	float arms[][] = 
		{
			{-40, -180, -30, 180},
			{0, 0, 0, 90},
		};
	//left arm X and Y, middle Y
	float attackAnimation[][] =
		{
			{-180, 0, 30},
			{0, 0, 0},
		};

	int armSway = 0, checkCD = 0, animationNumber = 0, animationTick = 0, cantSwitchCD = 0;
	double xValue, yValue, cycleNumber;
	boolean SwapYValue;

	public ModelWitePawns()
	{
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 7, 8);
		head.setRotationPoint(0F, 10F, 0F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		neck = new ModelRenderer(this, 32, 0);
		neck.addBox(-6F, -5F, -6F, 12, 3, 12);
		neck.setRotationPoint(0F, 15F, 0F);
		neck.setTextureSize(128, 128);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);
		body = new ModelRenderer(this, 30, 15);
		body.addBox(-4.5F, -4F, -4.5F, 9, 8, 9);
		body.setRotationPoint(0F, 16F, 0F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		neck2 = new ModelRenderer(this, 0, 15);
		neck2.addBox(-4F, -1F, -3F, 8, 1, 7);
		neck2.setRotationPoint(0F, 10F, 0F);
		neck2.setTextureSize(128, 128);
		neck2.mirror = true;
		setRotation(neck2, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 23);
		Nose.addBox(-1F, -1F, -4F, 2, 1, 1);
		Nose.setRotationPoint(0F, 10F, 0F);
		Nose.setTextureSize(128, 128);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		EyePiece = new ModelRenderer(this, 6, 23);
		EyePiece.addBox(3F, -1F, -4F, 1, 1, 1);
		EyePiece.setRotationPoint(0F, 10F, 0F);
		EyePiece.setTextureSize(128, 128);
		EyePiece.mirror = true;
		setRotation(EyePiece, 0F, 0F, 0F);
		EyePieceTwo = new ModelRenderer(this, 6, 23);
		EyePieceTwo.addBox(-4F, -1F, -4F, 1, 1, 1);
		EyePieceTwo.setRotationPoint(0F, 10F, 0F);
		EyePieceTwo.setTextureSize(128, 128);
		EyePieceTwo.mirror = true;
		setRotation(EyePieceTwo, 0F, 0F, 0F);
		EyePieceThree = new ModelRenderer(this, 0, 25);
		EyePieceThree.addBox(-3F, -1F, -3.5F, 2, 1, 1);
		EyePieceThree.setRotationPoint(0F, 10F, 0F);
		EyePieceThree.setTextureSize(128, 128);
		EyePieceThree.mirror = true;
		setRotation(EyePieceThree, 0F, 0F, 0F);
		EyePieceFour = new ModelRenderer(this, 0, 25);
		EyePieceFour.addBox(1F, -1F, -3.5F, 2, 1, 1);
		EyePieceFour.setRotationPoint(0F, 10F, 0F);
		EyePieceFour.setTextureSize(128, 128);
		EyePieceFour.mirror = true;
		setRotation(EyePieceFour, 0F, 0F, 0F);
		rightFoot = new ModelRenderer(this, 80, 0);
		rightFoot.addBox(-1.7F, 1F, -1.5F, 3, 3, 3);
		rightFoot.setRotationPoint(-2F, 20F, 0F);
		rightFoot.setTextureSize(128, 128);
		rightFoot.mirror = true;
		setRotation(rightFoot, 0F, 0F, 0F);
		leftFoot = new ModelRenderer(this, 80, 0);
		leftFoot.addBox(-1.3F, 1F, -1.5F, 3, 3, 3);
		leftFoot.setRotationPoint(2F, 20F, 0F);
		leftFoot.setTextureSize(128, 128);
		leftFoot.mirror = true;
		setRotation(leftFoot, 0F, 0F, 0F);
		rightArm = new ModelRenderer(this, 80, 6);
		rightArm.addBox(-2.5F, -1F, -1.5F, 3, 8, 3);
		rightArm.setRotationPoint(-5F, 12.5F, 0F);
		rightArm.setTextureSize(128, 128);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0F);
		leftArm = new ModelRenderer(this, 92, 6);
		leftArm.addBox(-0.5F, -1F, -1.5F, 3, 8, 3);
		leftArm.setRotationPoint(5F, 12.5F, 0F);
		leftArm.setTextureSize(128, 128);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, 0F);
		rightKnee = new ModelRenderer(this, 80, 17);
		rightKnee.addBox(-2.2F, -0.5F, -2F, 4, 2, 4);
		rightKnee.setRotationPoint(-2F, 20F, 0F);
		rightKnee.setTextureSize(128, 128);
		rightKnee.mirror = true;
		setRotation(rightKnee, 0F, 0F, 0F);
		leftKnee = new ModelRenderer(this, 80, 17);
		leftKnee.addBox(-1.8F, -0.5F, -2F, 4, 2, 4);
		leftKnee.setRotationPoint(2F, 20F, 0F);
		leftKnee.setTextureSize(128, 128);
		leftKnee.mirror = true;
		setRotation(leftKnee, 0F, 0F, 0F);


		convertToChild(leftKnee, leftFoot);
		convertToChild(rightKnee, rightFoot);

		convertToChild(neck2, head);
		convertToChild(neck, neck2);
		convertToChild(neck, EyePiece);
		convertToChild(neck, EyePieceTwo);
		convertToChild(neck, EyePieceThree);
		convertToChild(neck, EyePieceFour);

		this.hammer.attachShaftTo(rightArm);

		convertToChild(body, neck);
		convertToChild(body, leftArm);
		convertToChild(body, rightArm);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		body.render(f5);
		leftKnee.render(f5);
		rightKnee.render(f5);

		EntityWhitePawns pawn = (EntityWhitePawns) entity;

		if(!pawn.getHammer_attack())
		{
			switch(animationNumber)
			{
			case 1:
				this.activatePanicAnimation();
				break;
			case 2:
				this.activateAttackAnimation();
				break;
			case -1:
				this.normalStance();
				break;
			}
		}else
		{
			this.cantSwitchCD = 40;
			this.activateHammerAttack();
		}

		if(this.cantSwitchCD == 0)
			this.triggerAnimation(pawn);
		else
			this.cantSwitchCD--;
	}

	public void activateHammerAttack()
	{
		this.hammer.Shaft.isHidden = false;
		this.hammer.Shaft.offsetY = 0.3f;

		boolean canIncrement[] = new boolean[4];

		canIncrement[0] = this.movePiece(this.leftArm, 3f, arms[animationTick][0], arms[animationTick][0], 0f);
		canIncrement[1] = this.movePiece(this.rightArm, 3f, arms[animationTick][1], 0f , 0f);
		canIncrement[2] = this.movePiece(this.body, 1.5f, arms[animationTick][2], 0f, 0f);
		canIncrement[3] = this.movePiece(this.hammer.Shaft, 3f, arms[animationTick][3], 0f, 0f);

		boolean flag = false;
		for(int i = 0; i < canIncrement.length; i ++)
		{
			if(!canIncrement[i])
			{
				flag = true;
			}	
		}
		if(!flag)
		{
			if(animationTick < 1)
			{
				this.animationTick ++;
			}else
			{
				this.animationTick = 0;
			}
		}
	}

	public double calcValueZ()
	{
		return  Math.sqrt(4 - xValue *xValue);
	}
	private void activateAttackAnimation()
	{
		this.hammer.Shaft.isHidden = true;
		boolean canIncrement[] = new boolean[4];

		canIncrement[0] = this.movePiece(this.rightArm, 3f, attackAnimation[animationTick][0], -arms[animationTick][1], 0f);
		canIncrement[2] = this.movePiece(this.body, 1.5f, 0f, attackAnimation[animationTick][2], 0f);

		boolean flag = false;
		for(int i = 0; i < canIncrement.length; i ++)
		{
			if(!canIncrement[i])
			{
				flag = true;
			}	
		}
		if(!flag)
		{
			if(animationTick < 1)
			{
				this.animationTick ++;
			}else
			{
				this.animationTick = 0;
			}
		}
	}

	private void triggerAnimation(EntityWhitePawns pawn)
	{

		List<EntityPawnCommander> nearby_commanders = pawn.world.getEntitiesWithinAABB(EntityPawnCommander.class, new AxisAlignedBB(pawn.getPosition().getX() - 15, pawn.getPosition().getY() - 15, pawn.getPosition().getZ() - 15, pawn.getPosition().getX() + 15, pawn.getPosition().getY() + 15, pawn.getPosition().getZ() + 15));
		if(nearby_commanders.isEmpty())
		{
			List<EntityWhitePawns> nearby_pawn = pawn.world.getEntitiesWithinAABB(EntityWhitePawns.class, new AxisAlignedBB(pawn.getPosition().getX() - 15, pawn.getPosition().getY() - 15, pawn.getPosition().getZ() - 15, pawn.getPosition().getX() + 15, pawn.getPosition().getY() + 15, pawn.getPosition().getZ() + 15));
			if(nearby_pawn.size() < 2)
			{
				this.animationNumber = 1;
				return;
			}else
			{
				EntityPlayer player = pawn.world.getNearestAttackablePlayer(pawn, 10, 10);
				if(player != null)
				{
					if(pawn.getDistanceSq(player) < 4)
					{
						this.animationNumber = 2;
						this.cantSwitchCD = 40;
						return;
					}
				}
			}
		}else
		{
			if(this.SwapYValue)
			{
				if(this.yValue < 3)
				{
					this.yValue += 0.01;
				}else
				{
					this.SwapYValue = false;
				}
			}else
			{
				if(this.yValue > -1)
				{
					this.yValue -= 0.01;
				}else
				{
					this.SwapYValue = true;
				}
			}

			if(this.cycleNumber % 2 == 0)
			{
				if(this.xValue > -2)
				{
					this.xValue -= 0.1;
					this.xValue = Math.round(xValue * 100.0)/100.0;
				}else
				{
					this.cycleNumber++;
				}
				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleEmpowerPawns(pawn.world, this.xValue + pawn.posX, pawn.posY + yValue, pawn.posZ + calcValueZ(), 0d, 0d, 0d));
			}

			if(this.cycleNumber % 2 == 1)
			{
				if(this.xValue < 2)
				{
					this.xValue += 0.1;
					this.xValue = Math.round(xValue * 100.0)/100.0;
				}else
				{
					this.cycleNumber++;
				}

				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleEmpowerPawns(pawn.world, this.xValue + pawn.posX, pawn.posY + yValue, pawn.posZ - calcValueZ(), 0d, 0d, 0d));
			}

			EntityPlayer player = pawn.world.getNearestAttackablePlayer(pawn, 10, 10);
			if(player != null)
			{
				if(pawn.getDistanceSq(player) < 4)
				{
					this.animationNumber = 2;
					this.cantSwitchCD = 40;
					return;
				}
			}
		}




		this.animationNumber = -1;
	}

	private void activatePanicAnimation()
	{
		this.hammer.Shaft.isHidden = true;
		boolean canIncrement[] = new boolean[3];

		canIncrement[0] = this.movePiece(this.leftArm, 3f, -90f  + armSway, -90f , 0f);
		canIncrement[1] = this.movePiece(this.rightArm, 3f, -90f + armSway, 90f , 0f);
		canIncrement[2] = this.movePiece(this.body, 1.5f, -15f, 0f, 0f);

		boolean flag = false;
		for(int i = 0; i < canIncrement.length; i ++)
		{
			if(!canIncrement[i])
			{
				flag = true;
			}	
		}
		if(!flag)
		{
			if(armSway == 30)
			{
				armSway = -30;
			}else
				armSway = 30;
		}
	}

	private void normalStance()
	{
		this.hammer.Shaft.isHidden = true;
		this.movePiece(this.leftArm, 3f, 0f, 0f, 0f);
		this.movePiece(this.rightArm, 3f, 0f, 0f, 0f);
		this.movePiece(this.body, 1.5f, 0f, 0f, 0f);
	}


	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entityIn) 
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scaleFactor, entityIn);

		float  f = 1f;
		this.rightFoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.leftFoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
	}


}
