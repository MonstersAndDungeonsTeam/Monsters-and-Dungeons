package monstersanddungeons.client.models;

import java.util.List;
import java.util.Random;

import monstersanddungeons.entity.automatons.EntityPawnCommander;
import monstersanddungeons.entity.automatons.EntityWhitePawns;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class ModelCommanderPawn extends MaDEntityModelBase
{
	ModelRenderer head;	
	ModelRenderer neck;
	ModelRenderer body;
	ModelRenderer neck_back;
	ModelRenderer eye_;
	ModelRenderer eye1;
	ModelRenderer eye2;
	ModelRenderer eye3;
	ModelRenderer eye4;
	ModelRenderer leftFoot;
	ModelRenderer rightFoot;
	ModelRenderer leftArm;
	ModelRenderer rightArm;
	ModelRenderer leftKnee;
	ModelRenderer rightKnee;
	ModelRenderer head1;
	ModelRenderer rightShoulder;
	ModelRenderer cape;
	ModelRenderer flagPole;
	ModelRenderer flagTop;
	ModelRenderer flagBanner;
	ModelRenderer leftShoulder;
	ModelRenderer leftForeArm;
	ModelRenderer rightForeArm;

	int cooldown, animationTick;
	boolean shouldActivate;

	//left shoulderX Y, left arm X, body X
	float arms[][] = 
		{
			{-90f, 45f, -45f, -30f},
			{-45f, 45f, -45f, 0f},

		};

	public ModelCommanderPawn()
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
		neck_back = new ModelRenderer(this, 0, 15);
		neck_back.addBox(-4F, -1F, -3F, 8, 1, 7);
		neck_back.setRotationPoint(0F, 10F, 0F);
		neck_back.setTextureSize(128, 128);
		neck_back.mirror = true;
		setRotation(neck_back, 0F, 0F, 0F);
		eye_ = new ModelRenderer(this, 0, 23);
		eye_.addBox(-1F, -1F, -4F, 2, 1, 1);
		eye_.setRotationPoint(0F, 10F, 0F);
		eye_.setTextureSize(128, 128);
		eye_.mirror = true;
		setRotation(eye_, 0F, 0F, 0F);
		eye1 = new ModelRenderer(this, 6, 23);
		eye1.addBox(3F, -1F, -4F, 1, 1, 1);
		eye1.setRotationPoint(0F, 10F, 0F);
		eye1.setTextureSize(128, 128);
		eye1.mirror = true;
		setRotation(eye1, 0F, 0F, 0F);
		eye2 = new ModelRenderer(this, 0, 25);
		eye2.addBox(-3F, -1F, -3.5F, 2, 1, 1);
		eye2.setRotationPoint(0F, 10F, 0F);
		eye2.setTextureSize(128, 128);
		eye2.mirror = true;
		setRotation(eye2, 0F, 0F, 0F);
		eye3 = new ModelRenderer(this, 6, 23);
		eye3.addBox(-4F, -1F, -4F, 1, 1, 1);
		eye3.setRotationPoint(0F, 10F, 0F);
		eye3.setTextureSize(128, 128);
		eye3.mirror = true;
		setRotation(eye3, 0F, 0F, 0F);
		eye4 = new ModelRenderer(this, 0, 25);
		eye4.addBox(1F, -1F, -3.5F, 2, 1, 1);
		eye4.setRotationPoint(0F, 10F, 0F);
		eye4.setTextureSize(128, 128);
		eye4.mirror = true;
		setRotation(eye4, 0F, 0F, 0F);
		leftFoot = new ModelRenderer(this, 80, 0);
		leftFoot.addBox(-1.7F, 1F, -1.5F, 3, 3, 3);
		leftFoot.setRotationPoint(-2F, 20F, 0F);
		leftFoot.setTextureSize(128, 128);
		leftFoot.mirror = true;
		setRotation(leftFoot, 0F, 0F, 0F);
		rightFoot = new ModelRenderer(this, 80, 0);
		rightFoot.addBox(-1.3F, 1F, -1.5F, 3, 3, 3);
		rightFoot.setRotationPoint(2F, 20F, 0F);
		rightFoot.setTextureSize(128, 128);
		rightFoot.mirror = true;
		setRotation(rightFoot, 0F, 0F, 0F);
		leftArm = new ModelRenderer(this, 80, 6);
		leftArm.addBox(-2.5F, -1F, -1.5F, 3, 8, 3);
		leftArm.setRotationPoint(-5F, 12.5F, 0F);
		leftArm.setTextureSize(128, 128);
		leftArm.mirror = true;
		setRotation(leftArm, -1.396263F, 0.5235988F, 0F);
		rightArm = new ModelRenderer(this, 92, 6);
		rightArm.addBox(-0.5F, -1F, -1.5F, 3, 8, 3);
		rightArm.setRotationPoint(5F, 12.5F, 0F);
		rightArm.setTextureSize(128, 128);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0F);
		leftKnee = new ModelRenderer(this, 80, 17);
		leftKnee.addBox(-2.2F, -0.5F, -2F, 4, 2, 4);
		leftKnee.setRotationPoint(-2F, 20F, 0F);
		leftKnee.setTextureSize(128, 128);
		leftKnee.mirror = true;
		setRotation(leftKnee, 0F, 0F, 0F);
		rightKnee = new ModelRenderer(this, 80, 17);
		rightKnee.addBox(-1.8F, -0.5F, -2F, 4, 2, 4);
		rightKnee.setRotationPoint(2F, 20F, 0F);
		rightKnee.setTextureSize(128, 128);
		rightKnee.mirror = true;
		setRotation(rightKnee, 0F, 0F, 0F);
		head1 = new ModelRenderer(this, 20, 27);
		head1.addBox(-1F, -9F, -4.5F, 2, 9, 1);
		head1.setRotationPoint(0F, 10F, 0F);
		head1.setTextureSize(128, 128);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		rightShoulder = new ModelRenderer(this, 0, 37);
		rightShoulder.addBox(-1.5F, -4F, -2.5F, 5, 5, 5);
		rightShoulder.setRotationPoint(5F, 12.5F, 0F);
		rightShoulder.setTextureSize(128, 128);
		rightShoulder.mirror = true;
		setRotation(rightShoulder, 0F, 0F, 0.0872665F);
		cape = new ModelRenderer(this, 0, 47);
		cape.addBox(-5F, -1F, -0.5F, 10, 11, 1);
		cape.setRotationPoint(0F, 13F, 5F);
		cape.setTextureSize(128, 128);
		cape.mirror = true;
		setRotation(cape, 0.0872665F, 0F, 0F);
		flagPole = new ModelRenderer(this, 0, 32);
		flagPole.addBox(-1.5F, 5F, -29.5F, 1, 1, 40);
		flagPole.setRotationPoint(-5F, 12.5F, 0F);
		flagPole.setTextureSize(128, 128);
		flagPole.mirror = true;
		setRotation(flagPole, -1.396263F, 0.5235988F, 0F);
		flagTop = new ModelRenderer(this, 0, 68);
		flagTop.addBox(-6.5F, 5F, -27.5F, 11, 1, 1);
		flagTop.setRotationPoint(-5F, 12.5F, 0F);
		flagTop.setTextureSize(128, 128);
		flagTop.mirror = true;
		setRotation(flagTop, -1.396263F, 0.5235988F, 0F);
		flagBanner = new ModelRenderer(this, 0, 73);
		flagBanner.addBox(-6F, 9.7F, -26.2F, 10, 1, 20);
		flagBanner.setRotationPoint(-5F, 12.5F, 0F);
		flagBanner.setTextureSize(128, 128);
		flagBanner.mirror = true;
		setRotation(flagBanner, -1.570796F, 0.5235988F, 0F);
		leftShoulder = new ModelRenderer(this, 0, 27);
		leftShoulder.addBox(-3.5F, -4F, -2.5F, 5, 5, 5);
		leftShoulder.setRotationPoint(-5F, 12.5F, 0F);
		leftShoulder.setTextureSize(128, 128);
		leftShoulder.mirror = true;
		setRotation(leftShoulder, -1.396263F, 0.5235988F, -0.0872665F);
		leftForeArm = new ModelRenderer(this, 80, 17);
		leftForeArm.addBox(-3F, 0.5F, -2F, 4, 2, 4);
		leftForeArm.setRotationPoint(-5F, 12.5F, 0F);
		leftForeArm.setTextureSize(128, 128);
		leftForeArm.mirror = true;
		setRotation(leftForeArm, -1.396263F, 0.5235988F, 0F);
		rightForeArm = new ModelRenderer(this, 80, 17);
		rightForeArm.addBox(-1F, 0.5F, -2F, 4, 2, 4);
		rightForeArm.setRotationPoint(5F, 12.5F, 0F);
		rightForeArm.setTextureSize(128, 128);
		rightForeArm.mirror = true;
		setRotation(rightForeArm, 0F, 0F, 0F);


		convertToChild(head, head1);


		convertToChild(neck, neck_back);
		convertToChild(neck, eye_);
		convertToChild(neck, eye1);
		convertToChild(neck, eye2);
		convertToChild(neck, eye3);
		convertToChild(neck, eye4);
		convertToChild(neck, head);

		convertToChild(rightForeArm, rightArm);
		convertToChild(rightShoulder, rightForeArm);

		convertToChild(flagPole, flagTop);
		convertToChild(flagPole, flagBanner);
		convertToChild(leftArm, flagPole);
		convertToChild(leftForeArm, leftArm);
		convertToChild(leftShoulder, leftForeArm);


		convertToChild(rightKnee, rightFoot);
		convertToChild(leftKnee, leftFoot);

		convertToChild(body, rightShoulder);
		convertToChild(body, leftShoulder);
		convertToChild(body, neck);


	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		rightKnee.render(f5);
		leftKnee.render(f5);
		body.render(f5);
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

		EntityPawnCommander pawn =  (EntityPawnCommander) entityIn;
		List<EntityWhitePawns> nearby_pawns = pawn.world.getEntitiesWithinAABB(EntityWhitePawns.class, new AxisAlignedBB(pawn.getPosition().getX() - 15, pawn.getPosition().getY() - 15, pawn.getPosition().getZ() - 15, pawn.getPosition().getX() + 15, pawn.getPosition().getY() + 15, pawn.getPosition().getZ() + 15));

		if(nearby_pawns.size() > 0)
		{
			if(this.cooldown == 0)
			{
				this.activateBuff();
			}else
				this.cooldown--;
		}
	}

	public void resetAnimation()
	{
		this.movePiece(this.leftShoulder, 0.5f, -80f, 30f, -50f);
		this.movePiece(this.leftArm, 0.5f, -80f, 30f, 0f);
		this.movePiece(this.body, 0.5f, 0f, 0f, 0f);
	}

	public void activateBuff()
	{
		boolean canIncrement[] = new boolean[3];

		canIncrement[0] = this.movePiece(this.leftShoulder, 1f, arms[animationTick][0], arms[animationTick][1], 0f);
		canIncrement[1] = this.movePiece(this.leftArm, 1f, arms[animationTick][2], 0f, 0f);
		canIncrement[2] = this.movePiece(this.body, 1f, arms[animationTick][3], 0f, 0f);

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
			if(this.animationTick < 1)
			{
				this.animationTick ++;
			}else
			{
				this.animationTick= 0;
				this.cooldown = 200;
				this.resetAnimation();
			}
		}
	}
}
