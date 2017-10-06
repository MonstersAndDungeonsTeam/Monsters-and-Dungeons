package monstersanddungeons.client.models;

import monstersanddungeons.entity.world.EntityEnt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnt extends MaDEntityModelBase
{
	ModelRenderer face;
	ModelRenderer face1;
	ModelRenderer face2;
	ModelRenderer face3;
	ModelRenderer face4;
	ModelRenderer face5;
	public 	ModelRenderer upperbody;
	public	ModelRenderer lowerbody;
	public	ModelRenderer leftupperleg;
	public	ModelRenderer rightupperleg;
	public	ModelRenderer leftlowerleg;
	public	ModelRenderer rightlowerleg;
	public	ModelRenderer leftboot;
	public	ModelRenderer rightboot;
	public	ModelRenderer leftupperarm;
	public	ModelRenderer copyLeftupperarm;
	public	ModelRenderer rightupperarm;
	public	ModelRenderer leftlowerarm;
	public	ModelRenderer rightlowerarm;
	ModelRenderer leftfinger1;
	ModelRenderer leftfinger;
	ModelRenderer leftfinger2;
	ModelRenderer leftfinger3;
	ModelRenderer rightfinger;
	ModelRenderer rightfinger1;
	ModelRenderer rightfinger2;
	ModelRenderer rightfinger3;
	ModelRenderer rightbootmushroom;
	ModelRenderer rightbootmushroom2;
	ModelRenderer rightbootmushroom3;
	ModelRenderer mushroom;
	ModelRenderer mushroom1;
	ModelRenderer treeBack;
	ModelRenderer treeBack1;
	ModelRenderer treeBack2;
	ModelRenderer treeBack3;
	ModelRenderer treeBack4;
	ModelRenderer treeBack5;
	ModelRenderer treeBack6;
	ModelRenderer treeBack7;
	ModelRenderer treeBack8;
	ModelRenderer treeBack9;
	ModelRenderer treeBack10;

	EntityEnt ent = null;
	int animation_number = 0, animationSwap = 0;
	boolean Cycle = false;

	//right_upper X, right_lower X, left upper X, left lower X
	float walk_animation[][]  = 
		{
			{-45, 45, 20, -20},
			{20, -20, -45, 45},
			{ 0,   0,   0,  0},
		};

	public ModelEnt()
	{
		textureWidth = 128;
		textureHeight = 128;

		face = new ModelRenderer(this, 0, 11);
		face.addBox(-2.5F, -1F, -8F, 5, 3, 2);
		face.setRotationPoint(0F, -50F, 0F);
		face.setTextureSize(128, 128);
		face.mirror = true;
		setRotation(face, 0F, 0F, 0F);
		face1 = new ModelRenderer(this, 0, 0);
		face1.addBox(-4F, 2F, -8F, 8, 3, 8);
		face1.setRotationPoint(0F, -50F, 0F);
		face1.setTextureSize(128, 128);
		face1.mirror = true;
		setRotation(face1, 0F, 0F, 0F);
		face2 = new ModelRenderer(this, 32, 0);
		face2.addBox(-4F, -1F, -6F, 8, 3, 6);
		face2.setRotationPoint(0F, -50F, 0F);
		face2.setTextureSize(128, 128);
		face2.mirror = true;
		setRotation(face2, 0F, 0F, 0F);
		face3 = new ModelRenderer(this, 0, 16);
		face3.addBox(-4F, -7F, -8F, 8, 6, 8);
		face3.setRotationPoint(0F, -50F, 0F);
		face3.setTextureSize(128, 128);
		face3.mirror = true;
		setRotation(face3, 0F, 0F, 0F);
		face4 = new ModelRenderer(this, 22, 11);
		face4.addBox(-3.8F, -1F, -7.8F, 2, 3, 2);
		face4.setRotationPoint(0F, -50F, 0F);
		face4.setTextureSize(128, 128);
		face4.mirror = true;
		setRotation(face4, 0F, 0F, 0F);
		face5 = new ModelRenderer(this, 14, 11);
		face5.addBox(1.8F, -1F, -7.8F, 2, 3, 2);
		face5.setRotationPoint(0F, -50F, 0F);
		face5.setTextureSize(128, 128);
		face5.mirror = true;
		setRotation(face5, 0F, 0F, 0F);
		upperbody = new ModelRenderer(this, 0, 30);
		upperbody.addBox(-10F, -14F, -7F, 20, 14, 14);
		upperbody.setRotationPoint(0F, -39F, 6F);
		upperbody.setTextureSize(128, 128);
		upperbody.mirror = true;
		setRotation(upperbody, 0.1396263F, 0F, 0F);
		lowerbody = new ModelRenderer(this, 0, 58);
		lowerbody.addBox(-6F, -1F, -5F, 12, 10, 10);
		lowerbody.setRotationPoint(0F, -39F, 6F);
		lowerbody.setTextureSize(128, 128);
		lowerbody.mirror = true;
		setRotation(lowerbody, 0F, 0F, 0F);
		leftupperleg = new ModelRenderer(this, 0, 78);
		leftupperleg.addBox(-1F, 0F, -1F, 2, 23, 2);
		leftupperleg.setRotationPoint(-4F, -30F, 6F);
		leftupperleg.setTextureSize(128, 128);
		leftupperleg.mirror = true;
		setRotation(leftupperleg, 0F, 0F, 0F);
		rightupperleg = new ModelRenderer(this, 8, 78);
		rightupperleg.addBox(-1F, 0F, -1F, 2, 23, 2);
		rightupperleg.setRotationPoint(4F, -30F, 6F);
		rightupperleg.setTextureSize(128, 128);
		rightupperleg.mirror = true;
		setRotation(rightupperleg, 0F, 0F, 0F);
		leftlowerleg = new ModelRenderer(this, 0, 103);
		leftlowerleg.addBox(-2F, 0F, -2F, 4, 14, 4);
		leftlowerleg.setRotationPoint(-4F, -7F, 6F);
		leftlowerleg.setTextureSize(128, 128);
		leftlowerleg.mirror = true;
		setRotation(leftlowerleg, 0F, 0F, 0F);
		rightlowerleg = new ModelRenderer(this, 16, 103);
		rightlowerleg.addBox(-2F, 0F, -2F, 4, 14, 4);
		rightlowerleg.setRotationPoint(4F, -7F, 6F);
		rightlowerleg.setTextureSize(128, 128);
		rightlowerleg.mirror = true;
		setRotation(rightlowerleg, 0F, 0F, 0F);
		leftboot = new ModelRenderer(this, 16, 78);
		leftboot.addBox(-3F, 0F, -3F, 6, 18, 6);
		leftboot.setRotationPoint(-4F, 7F, 6F);
		leftboot.setTextureSize(128, 128);
		leftboot.mirror = true;
		setRotation(leftboot, 0F, 0F, 0F);
		rightboot = new ModelRenderer(this, 40, 78);
		rightboot.addBox(-3F, 0F, -3F, 6, 18, 6);
		rightboot.setRotationPoint(4F, 7F, 6F);
		rightboot.setTextureSize(128, 128);
		rightboot.mirror = true;
		setRotation(rightboot, 0F, 0F, 0F);


		leftupperarm = new ModelRenderer(this, 68, 44);
		leftupperarm.addBox(-3F, -1F, -1F, 3, 33, 3);
		leftupperarm.setRotationPoint(-9F, -49F, 5F);
		leftupperarm.setTextureSize(128, 128);
		leftupperarm.mirror = true;
		setRotation(leftupperarm, 0F, 0F, 0.0872665F);

		copyLeftupperarm = new ModelRenderer(this, 68, 44);
		copyLeftupperarm.addBox(-3F, -1F, -1F, 3, 33, 3);
		copyLeftupperarm.setRotationPoint(-9F, -49F, 5F);
		copyLeftupperarm.setTextureSize(128, 128);
		copyLeftupperarm.mirror = true;
		setRotation(copyLeftupperarm, 0F, 0F, 0.0872665F);

		rightupperarm = new ModelRenderer(this, 80, 44);
		rightupperarm.addBox(0F, -1F, -1F, 3, 33, 3);
		rightupperarm.setRotationPoint(9F, -49F, 5F);
		rightupperarm.setTextureSize(128, 128);
		rightupperarm.mirror = true;
		setRotation(rightupperarm, 0F, 0F, -0.0872665F);
		leftlowerarm = new ModelRenderer(this, 92, 44);
		leftlowerarm.addBox(-3.5F, 0F, -3.5F, 7, 15, 7);
		leftlowerarm.setRotationPoint(-13.2F, -18F, 5.5F);
		leftlowerarm.setTextureSize(128, 128);
		leftlowerarm.mirror = true;
		setRotation(leftlowerarm, 0F, 0F, 0.0872665F);
		rightlowerarm = new ModelRenderer(this, 92, 66);
		rightlowerarm.addBox(-3.5F, 0F, -3.5F, 7, 15, 7);
		rightlowerarm.setRotationPoint(13.2F, -18F, 5.5F);
		rightlowerarm.setTextureSize(128, 128);
		rightlowerarm.mirror = true;
		setRotation(rightlowerarm, 0F, 0F, -0.0872665F);
		leftfinger1 = new ModelRenderer(this, 92, 88);
		leftfinger1.addBox(-4.5F, 13F, 0F, 2, 8, 2);
		leftfinger1.setRotationPoint(-13.2F, -18F, 5.5F);
		leftfinger1.setTextureSize(128, 128);
		leftfinger1.mirror = true;
		setRotation(leftfinger1, -0.3141593F, 0F, 0.0349066F);
		leftfinger = new ModelRenderer(this, 100, 88);
		leftfinger.addBox(-4.5F, 13F, 0.5F, 2, 10, 2);
		leftfinger.setRotationPoint(-13.2F, -18F, 5.5F);
		leftfinger.setTextureSize(128, 128);
		leftfinger.mirror = true;
		setRotation(leftfinger, -0.1745329F, 0.1396263F, 0.0349066F);
		leftfinger2 = new ModelRenderer(this, 92, 98);
		leftfinger2.addBox(-5.5F, 13F, 1.5F, 2, 8, 2);
		leftfinger2.setRotationPoint(-13.2F, -18F, 5.5F);
		leftfinger2.setTextureSize(128, 128);
		leftfinger2.mirror = true;
		setRotation(leftfinger2, 0.0174533F, 0F, -0.0349066F);
		leftfinger3 = new ModelRenderer(this, 92, 98);
		leftfinger3.addBox(3.5F, 12F, -1F, 2, 8, 2);
		leftfinger3.setRotationPoint(-13.2F, -18F, 5.5F);
		leftfinger3.setTextureSize(128, 128);
		leftfinger3.mirror = true;
		setRotation(leftfinger3, -0.2617994F, 0F, 0.1745329F);
		rightfinger = new ModelRenderer(this, 92, 98);
		rightfinger.addBox(-5.5F, 12F, -3F, 2, 8, 2);
		rightfinger.setRotationPoint(13.2F, -18F, 5.5F);
		rightfinger.setTextureSize(128, 128);
		rightfinger.mirror = true;
		setRotation(rightfinger, -0.0872665F, 0F, -0.1745329F);
		rightfinger1 = new ModelRenderer(this, 92, 88);
		rightfinger1.addBox(2.5F, 13F, -2F, 2, 8, 2);
		rightfinger1.setRotationPoint(13.2F, -18F, 5.5F);
		rightfinger1.setTextureSize(128, 128);
		rightfinger1.mirror = true;
		setRotation(rightfinger1, -0.1396263F, 0F, -0.0349066F);
		rightfinger2 = new ModelRenderer(this, 100, 88);
		rightfinger2.addBox(3.5F, 13F, 0.5F, 2, 10, 2);
		rightfinger2.setRotationPoint(13.2F, -18F, 5.5F);
		rightfinger2.setTextureSize(128, 128);
		rightfinger2.mirror = true;
		setRotation(rightfinger2, -0.1047198F, 0.0349066F, -0.0174533F);
		rightfinger3 = new ModelRenderer(this, 100, 88);
		rightfinger3.addBox(3.5F, 12F, 1.5F, 2, 10, 2);
		rightfinger3.setRotationPoint(13.2F, -18F, 5.5F);
		rightfinger3.setTextureSize(128, 128);
		rightfinger3.mirror = true;
		setRotation(rightfinger3, 0.0349066F, 0.0349066F, -0.0174533F);
		rightbootmushroom = new ModelRenderer(this, 32, 102);
		rightbootmushroom.addBox(0F, 4F, -4F, 5, 1, 5);
		rightbootmushroom.setRotationPoint(4F, 7F, 6F);
		rightbootmushroom.setTextureSize(128, 128);
		rightbootmushroom.mirror = true;
		setRotation(rightbootmushroom, 0F, 0F, 0F);
		rightbootmushroom2 = new ModelRenderer(this, 32, 108);
		rightbootmushroom2.addBox(1F, 6F, -3.5F, 3, 1, 3);
		rightbootmushroom2.setRotationPoint(4F, 7F, 6F);
		rightbootmushroom2.setTextureSize(128, 128);
		rightbootmushroom2.mirror = true;
		setRotation(rightbootmushroom2, 0F, 0F, 0F);
		rightbootmushroom3 = new ModelRenderer(this, 44, 108);
		rightbootmushroom3.addBox(-1.5F, 5.2F, -3.5F, 2, 1, 2);
		rightbootmushroom3.setRotationPoint(4F, 7F, 6F);
		rightbootmushroom3.setTextureSize(128, 128);
		rightbootmushroom3.mirror = true;
		setRotation(rightbootmushroom3, 0F, 0F, 0F);
		mushroom = new ModelRenderer(this, 32, 115);
		mushroom.addBox(-3F, 6F, 0F, 3, 1, 3);
		mushroom.setRotationPoint(-4F, -8F, 6F);
		mushroom.setTextureSize(128, 128);
		mushroom.mirror = true;
		setRotation(mushroom, 0F, 0F, 0F);
		mushroom1 = new ModelRenderer(this, 32, 112);
		mushroom1.addBox(-2.5F, 8F, 0.5F, 2, 1, 2);
		mushroom1.setRotationPoint(-4F, -8F, 6F);
		mushroom1.setTextureSize(128, 128);
		mushroom1.mirror = true;
		setRotation(mushroom1, 0F, 0F, 0F);
		treeBack = new ModelRenderer(this, 72, 0);
		treeBack.addBox(-10F, -19F, -5F, 14, 14, 14);
		treeBack.setRotationPoint(0F, -39F, 6F);
		treeBack.setTextureSize(128, 128);
		treeBack.mirror = true;
		setRotation(treeBack, -0.1047198F, -0.1919862F, -0.2094395F);
		treeBack1 = new ModelRenderer(this, 72, 0);
		treeBack1.addBox(-7F, -20F, -2F, 14, 14, 14);
		treeBack1.setRotationPoint(0F, -39F, 6F);
		treeBack1.setTextureSize(128, 128);
		treeBack1.mirror = true;
		setRotation(treeBack1, 0F, 0.0523599F, 0.0174533F);
		treeBack2 = new ModelRenderer(this, 72, 0);
		treeBack2.addBox(-5F, -18F, -2F, 14, 14, 14);
		treeBack2.setRotationPoint(0F, -39F, 6F);
		treeBack2.setTextureSize(128, 128);
		treeBack2.mirror = true;
		setRotation(treeBack2, 0.0872665F, 0.3490659F, 0.296706F);
		treeBack3 = new ModelRenderer(this, 72, 28);
		treeBack3.addBox(-8F, -8F, 2F, 8, 8, 8);
		treeBack3.setRotationPoint(0F, -39F, 6F);
		treeBack3.setTextureSize(128, 128);
		treeBack3.mirror = true;
		setRotation(treeBack3, -0.2617994F, 0F, 0.6806784F);
		treeBack4 = new ModelRenderer(this, 72, 28);
		treeBack4.addBox(-2F, -7F, 2F, 8, 8, 8);
		treeBack4.setRotationPoint(0F, -39F, 6F);
		treeBack4.setTextureSize(128, 128);
		treeBack4.mirror = true;
		setRotation(treeBack4, 0.1396263F, 0.1966404F, 0.2617994F);
		treeBack5 = new ModelRenderer(this, 104, 28);
		treeBack5.addBox(11F, -10F, -4F, 4, 4, 4);
		treeBack5.setRotationPoint(0F, -39F, 6F);
		treeBack5.setTextureSize(128, 128);
		treeBack5.mirror = true;
		setRotation(treeBack5, 0.0349066F, 0.0349066F, -0.1047198F);
		treeBack6 = new ModelRenderer(this, 104, 28);
		treeBack6.addBox(12F, -7F, 2F, 4, 4, 4);
		treeBack6.setRotationPoint(0F, -39F, 6F);
		treeBack6.setTextureSize(128, 128);
		treeBack6.mirror = true;
		setRotation(treeBack6, 0.5585054F, 0.0349066F, -0.1047198F);
		treeBack7 = new ModelRenderer(this, 104, 28);
		treeBack7.addBox(-13F, -8F, 3F, 4, 4, 4);
		treeBack7.setRotationPoint(0F, -39F, 6F);
		treeBack7.setTextureSize(128, 128);
		treeBack7.mirror = true;
		setRotation(treeBack7, 0.5585054F, 0.0349066F, -0.1047198F);
		treeBack8 = new ModelRenderer(this, 104, 28);
		treeBack8.addBox(-13F, -8F, 6F, 4, 4, 4);
		treeBack8.setRotationPoint(0F, -39F, 6F);
		treeBack8.setTextureSize(128, 128);
		treeBack8.mirror = true;
		setRotation(treeBack8, 1.134464F, 0.0349066F, -0.0698132F);
		treeBack9 = new ModelRenderer(this, 70, 0);
		treeBack9.addBox(-5F, -20F, 3F, 4, 2, 4);
		treeBack9.setRotationPoint(0F, -39F, 6F);
		treeBack9.setTextureSize(128, 128);
		treeBack9.mirror = true;
		setRotation(treeBack9, -0.0698132F, -0.4363323F, -0.2094395F);
		treeBack10 = new ModelRenderer(this, 70, 0);
		treeBack10.addBox(-3.2F, -20.8F, 3.8F, 1, 1, 1);
		treeBack10.setRotationPoint(0F, -39F, 6F);
		treeBack10.setTextureSize(128, 128);
		treeBack10.mirror = true;
		setRotation(treeBack10, -0.122173F, -0.4363323F, -0.2094395F);

		convertToChild(leftlowerarm, leftfinger);
		convertToChild(leftlowerarm, leftfinger1);
		convertToChild(leftlowerarm, leftfinger2);
		convertToChild(leftlowerarm, leftfinger3);

		convertToChild(leftupperarm, leftlowerarm);

		convertToChild(rightlowerarm, rightfinger);
		convertToChild(rightlowerarm, rightfinger1);
		convertToChild(rightlowerarm, rightfinger2);
		convertToChild(rightlowerarm, rightfinger3);

		convertToChild(rightupperarm, rightlowerarm);

		convertToChild(upperbody, leftupperarm);
		convertToChild(upperbody, rightupperarm);

		convertToChild(upperbody, treeBack);
		convertToChild(upperbody, treeBack1);
		convertToChild(upperbody, treeBack2);
		convertToChild(upperbody, treeBack3);
		convertToChild(upperbody, treeBack4);
		convertToChild(upperbody, treeBack5);
		convertToChild(upperbody, treeBack6);
		convertToChild(upperbody, treeBack7);
		convertToChild(upperbody, treeBack8);
		convertToChild(upperbody, treeBack9);
		convertToChild(upperbody, treeBack10);

		convertToChild(upperbody, face);
		convertToChild(upperbody, face1);
		convertToChild(upperbody, face2);
		convertToChild(upperbody, face3);
		convertToChild(upperbody, face4);
		convertToChild(upperbody, face5);

		convertToChild(leftlowerleg, leftboot);
		convertToChild(leftlowerleg, mushroom);
		convertToChild(leftlowerleg, mushroom1);

		convertToChild(leftupperleg, leftlowerleg);

		convertToChild(rightboot, rightbootmushroom);
		convertToChild(rightboot, rightbootmushroom3);
		convertToChild(rightboot, rightbootmushroom2);

		convertToChild(rightlowerleg, rightboot);
		convertToChild(rightupperleg, rightlowerleg);

		convertToChild(lowerbody, rightupperleg);
		convertToChild(lowerbody, leftupperleg);
		convertToChild(lowerbody, upperbody);
	}

	public EntityEnt getEnt() {
		return ent;
	}

	public void render(Entity entityIn, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entityIn, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entityIn);

		EntityEnt boss = (EntityEnt) entityIn;
		this.ent = boss;

		lowerbody.render(f5);
		if(boss.getCurrentAttack() != null)
		{
			if(!Minecraft.getMinecraft().isGamePaused())
				boss.getCurrentAttack().tickAnimation(this, f5, (float)(60.0/Minecraft.getDebugFPS()));
		}

		if(entityIn.prevPosX != entityIn.posX || entityIn.prevPosZ != entityIn.posZ)
		{
			this.walkingAnimation(1.2f);
		}else
		{
			if(boss.getCurrentAttack() == null)
				this.normal_position();
		}
	}

	public void normal_position()
	{
		movePiece(leftupperleg, 1f, 0f, 0f, 0f);
		movePiece(leftlowerleg, 1f, 0f, 0f, 0f);

		movePiece(rightupperleg, 1f, 0f, 0f, 0f);
		movePiece(rightlowerleg, 1f, 0f, 0f, 0f);

		if(this.leftupperarm.rotateAngleZ < degToRad(7) && this.leftupperarm.rotateAngleZ > degToRad(-7) && 
				(this.rightupperarm.rotateAngleZ < degToRad(7) && this.rightupperarm.rotateAngleZ > degToRad(-7)))
		{
			if(this.leftupperarm.rotateAngleX < degToRad(7) && this.leftupperarm.rotateAngleX > degToRad(-7) && 
					(this.rightupperarm.rotateAngleX < degToRad(7) && this.rightupperarm.rotateAngleX > degToRad(-7)))
			{
					if(!this.Cycle)
					{
						if(this.leftupperarm.rotateAngleZ < degToRad(5))
						{
							this.leftupperarm.rotateAngleZ += degToRad(0.1f);
							this.rightupperarm.rotateAngleZ -= degToRad(0.1f);
						}else
							this.Cycle = true;
					}else
					{
						if(this.leftupperarm.rotateAngleZ > degToRad(-5))
						{
							this.leftupperarm.rotateAngleZ -= degToRad(0.1f);
							this.rightupperarm.rotateAngleZ += degToRad(0.1f);
						}else
							this.Cycle = false;
					}
			}else
			{
				this.movePiece(leftupperarm, 1f, 0f, 0f, 0f);
				this.movePiece(rightupperarm, 1f, 0f, 0f, 0f);
			}
		}else
		{
			this.movePiece(leftupperarm, 1f, 0f, 0f, 0f);
			this.movePiece(rightupperarm, 1f, 0f, 0f, 0f);
		}

		movePiece(rightboot, 1f, 0f, 0f, 0f);
		movePiece(leftboot, 1f, 0f, 0f, 0f);

		movePiece(lowerbody, 1f, 0f, 0f, 0f);
		movePiece(upperbody, 1f, 0f, 0f, 0f);


		moveoffSet(lowerbody, 0.02f, 0f);

	}

	public void walkingAnimation(float speed)
	{
		int maxAnimation = 2;
		boolean canIncrement[] = new boolean[5];

		canIncrement[0] = movePiece(leftupperleg, speed, walk_animation[animation_number][2], 0f, 0f);
		canIncrement[1] = movePiece(leftlowerleg, speed, walk_animation[animation_number][3], 0f, 0f);

		canIncrement[2] = movePiece(rightupperleg, speed, walk_animation[animation_number][0], 0f, 0f);
		canIncrement[3] = movePiece(rightlowerleg, speed, walk_animation[animation_number][1], 0f, 0f);

		canIncrement[4] = movePiece(rightupperarm, speed, walk_animation[animation_number][2] * 0.4f, 0f, 0f);

		movePiece(rightboot, 1f, 0f, 0f, 0f);
		movePiece(leftboot, 1f, 0f, 0f, 0f);

		movePiece(lowerbody, 1f, 0f, 0f, 0f);
		movePiece(upperbody, 1f, 0f, 0f, 0f);

		moveoffSet(lowerbody, 0.02f, 0f);

		if(this.getEnt() != null)
		{
			if(this.getEnt().getCurrentAttack() == null)
			{
				canIncrement[4] = movePiece(leftupperarm, speed, walk_animation[animation_number][0] * 0.4f, 0f, 0f);
			}
		}
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
			if(this.animation_number < maxAnimation)
			{
				this.animation_number++;
			}else
				this.animation_number = 0;
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

	}
}
