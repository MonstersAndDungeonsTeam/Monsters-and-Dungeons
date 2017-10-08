package monstersanddungeons.client.models;

import monstersanddungeons.entity.marshdwellers.EntityMarshDweller;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMarshDweller extends MaDEntityModelBase 
{
	ModelRenderer belly;
	ModelRenderer upperbody;
	ModelRenderer leftboob;
	ModelRenderer rightboob;
	ModelRenderer mouth;
	ModelRenderer head;
	ModelRenderer rightknee;
	ModelRenderer leftknee;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer leftear;
	ModelRenderer rightear;
	ModelRenderer rightShoulder;
	ModelRenderer leftShoulder;
	ModelRenderer rightHand;
	ModelRenderer leftHand;
	ModelRenderer leftFinger;
	ModelRenderer leftFinger1;
	ModelRenderer leftFinger2;
	ModelRenderer leftFinger3;
	ModelRenderer rightFinger;
	ModelRenderer rightFinger1;
	ModelRenderer rightFinger2;
	ModelRenderer rightFinger3;
	ModelRenderer leftToe;
	ModelRenderer leftToe1;
	ModelRenderer leftToe2;
	ModelRenderer rightToe;
	ModelRenderer rightToe1;
	ModelRenderer rightToe2;
	ModelRenderer upperChest;

	public ModelMarshDweller()
	{
		textureWidth = 128;
		textureHeight = 128;

		belly = new ModelRenderer(this, 0, 0);
		belly.addBox(-8F, -6F, -8F, 16, 12, 15);
		belly.setRotationPoint(0F, 6F, 0F);
		belly.setTextureSize(128, 128);
		belly.mirror = true;
		setRotation(belly, -0.1745329F, 0F, 0F);
		
		upperbody = new ModelRenderer(this, 64, 0);
		upperbody.addBox(-7F, -14F, -4.5F, 14, 10, 11);
		upperbody.setRotationPoint(0F, 6F, 0F);
		upperbody.setTextureSize(128, 128);
		upperbody.mirror = true;
		setRotation(upperbody, 0F, 0F, 0F);
		leftboob = new ModelRenderer(this, 64, 22);
		leftboob.addBox(-7.3F, -12F, -7F, 7, 5, 1);
		leftboob.setRotationPoint(0F, 6F, 0F);
		leftboob.setTextureSize(128, 128);
		leftboob.mirror = true;
		setRotation(leftboob, -0.1919862F, 0F, 0.0174533F);
		rightboob = new ModelRenderer(this, 90, 22);
		rightboob.addBox(0.3F, -12F, -7F, 7, 5, 1);
		rightboob.setRotationPoint(0F, 6F, 0F);
		rightboob.setTextureSize(128, 128);
		rightboob.mirror = true;
		setRotation(rightboob, -0.1919862F, 0F, -0.0174533F);
		mouth = new ModelRenderer(this, 38, 28);
		mouth.addBox(-5F, 0F, -8F, 10, 7, 8);
		mouth.setRotationPoint(0F, -12F, 2F);
		mouth.setTextureSize(128, 128);
		mouth.mirror = true;
		setRotation(mouth, -0.0523599F, 0F, 0F);
		head = new ModelRenderer(this, 0, 28);
		head.addBox(-4.5F, -6F, -7F, 9, 8, 10);
		head.setRotationPoint(0F, -9F, 2F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, -0.0523599F, 0F, 0F);
		rightknee = new ModelRenderer(this, 0, 46);
		rightknee.addBox(-3F, -1F, -5.5F, 6, 7, 9);
		rightknee.setRotationPoint(4F, 12F, 0F);
		rightknee.setTextureSize(128, 128);
		rightknee.mirror = true;
		setRotation(rightknee, -0.2617994F, 0F, -0.0872665F);
		leftknee = new ModelRenderer(this, 29, 46);
		leftknee.addBox(-3F, -1F, -5.5F, 6, 7, 9);
		leftknee.setRotationPoint(-4F, 12F, 0F);
		leftknee.setTextureSize(128, 128);
		leftknee.mirror = true;
		setRotation(leftknee, -0.2617994F, 0F, 0.0872665F);
		rightleg = new ModelRenderer(this, 28, 62);
		rightleg.addBox(-3.7F, -1F, -2.5F, 7, 9, 7);
		rightleg.setRotationPoint(4.5F, 16F, 1F);
		rightleg.setTextureSize(128, 128);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, -0.0872665F, 0F);
		leftleg = new ModelRenderer(this, 0, 62);
		leftleg.addBox(-3.3F, -1F, -2.5F, 7, 9, 7);
		leftleg.setRotationPoint(-4.5F, 16F, 1F);
		leftleg.setTextureSize(128, 128);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0.0872665F, 0F);
		leftear = new ModelRenderer(this, 80, 22);
		leftear.addBox(-6.5F, -5.5F, -4.5F, 2, 3, 3);
		leftear.setRotationPoint(0F, -9F, 2F);
		leftear.setTextureSize(128, 128);
		leftear.mirror = true;
		setRotation(leftear, 0F, -0.1745329F, 0F);
		rightear = new ModelRenderer(this, 106, 22);
		rightear.addBox(4.5F, -5.5F, -4.5F, 2, 3, 3);
		rightear.setRotationPoint(0F, -9F, 2F);
		rightear.setTextureSize(128, 128);
		rightear.mirror = true;
		setRotation(rightear, 0F, 0.1745329F, 0F);
		rightShoulder = new ModelRenderer(this, 96, 28);
		rightShoulder.addBox(-1F, -3F, -3F, 5, 10, 6);
		rightShoulder.setRotationPoint(8F, -4F, 1.5F);
		rightShoulder.setTextureSize(128, 128);
		rightShoulder.mirror = true;
		setRotation(rightShoulder, 0F, 0F, -0.1745329F);
		leftShoulder = new ModelRenderer(this, 74, 28);
		leftShoulder.addBox(-4F, -3F, -3F, 5, 10, 6);
		leftShoulder.setRotationPoint(-8F, -4F, 1.5F);
		leftShoulder.setTextureSize(128, 128);
		leftShoulder.mirror = true;
		setRotation(leftShoulder, 0F, 0F, 0.1745329F);
		rightHand = new ModelRenderer(this, 86, 44);
		rightHand.addBox(-3F, 0F, -3.5F, 6, 8, 7);
		rightHand.setRotationPoint(11F, 2F, 1.5F);
		rightHand.setTextureSize(128, 128);
		rightHand.mirror = true;
		setRotation(rightHand, 0F, 0F, 0F);
		leftHand = new ModelRenderer(this, 60, 44);
		leftHand.addBox(-3F, 0F, -3.5F, 6, 8, 7);
		leftHand.setRotationPoint(-11F, 2F, 1.5F);
		leftHand.setTextureSize(128, 128);
		leftHand.mirror = true;
		setRotation(leftHand, 0F, 0F, 0F);
		leftFinger = new ModelRenderer(this, 60, 59);
		leftFinger.addBox(-1F, -1F, -1F, 4, 2, 2);
		leftFinger.setRotationPoint(-13F, 11F, 1.5F);
		leftFinger.setTextureSize(128, 128);
		leftFinger.mirror = true;
		setRotation(leftFinger, 0F, 0F, 0.1745329F);
		leftFinger1 = new ModelRenderer(this, 60, 63);
		leftFinger1.addBox(-1F, -1F, -1F, 4, 2, 2);
		leftFinger1.setRotationPoint(-13F, 11F, 3.7F);
		leftFinger1.setTextureSize(128, 128);
		leftFinger1.mirror = true;
		setRotation(leftFinger1, 0F, 0F, 0.0872665F);
		leftFinger2 = new ModelRenderer(this, 60, 67);
		leftFinger2.addBox(-1F, -1F, -1F, 4, 2, 2);
		leftFinger2.setRotationPoint(-13F, 11F, -0.7F);
		leftFinger2.setTextureSize(128, 128);
		leftFinger2.mirror = true;
		setRotation(leftFinger2, 0F, 0F, 0.2617994F);
		leftFinger3 = new ModelRenderer(this, 60, 71);
		leftFinger3.addBox(-1F, -1F, -1F, 2, 2, 4);
		leftFinger3.setRotationPoint(-9F, 11F, -0.7F);
		leftFinger3.setTextureSize(128, 128);
		leftFinger3.mirror = true;
		setRotation(leftFinger3, -0.2617994F, 0F, 0F);
		rightFinger = new ModelRenderer(this, 72, 59);
		rightFinger.addBox(-3F, -1F, -1F, 4, 2, 2);
		rightFinger.setRotationPoint(13F, 11F, 1.5F);
		rightFinger.setTextureSize(128, 128);
		rightFinger.mirror = true;
		setRotation(rightFinger, 0F, 0F, -0.1745329F);
		rightFinger1 = new ModelRenderer(this, 72, 67);
		rightFinger1.addBox(-3F, -1F, -1F, 4, 2, 2);
		rightFinger1.setRotationPoint(13F, 11F, -0.7F);
		rightFinger1.setTextureSize(128, 128);
		rightFinger1.mirror = true;
		setRotation(rightFinger1, 0F, 0F, -0.2617994F);
		rightFinger2 = new ModelRenderer(this, 72, 63);
		rightFinger2.addBox(-3F, -1F, -1F, 4, 2, 2);
		rightFinger2.setRotationPoint(13F, 11F, 3.7F);
		rightFinger2.setTextureSize(128, 128);
		rightFinger2.mirror = true;
		setRotation(rightFinger2, 0F, 0F, -0.0872665F);
		rightFinger3 = new ModelRenderer(this, 72, 71);
		rightFinger3.addBox(-1F, -1F, -1F, 2, 2, 4);
		rightFinger3.setRotationPoint(9F, 11F, -0.7F);
		rightFinger3.setTextureSize(128, 128);
		rightFinger3.mirror = true;
		setRotation(rightFinger3, -0.2617994F, 0F, 0F);
		leftToe = new ModelRenderer(this, 0, 78);
		leftToe.addBox(-3.3F, 6.5F, -6.5F, 2, 2, 5);
		leftToe.setRotationPoint(-4.5F, 16F, 1F);
		leftToe.setTextureSize(128, 128);
		leftToe.mirror = true;
		setRotation(leftToe, -0.0872665F, 0.1745329F, 0F);
		leftToe1 = new ModelRenderer(this, 28, 78);
		leftToe1.addBox(1.7F, 6.5F, -6.5F, 2, 2, 5);
		leftToe1.setRotationPoint(-4.5F, 16F, 1F);
		leftToe1.setTextureSize(128, 128);
		leftToe1.mirror = true;
		setRotation(leftToe1, -0.0872665F, 0F, 0F);
		leftToe2 = new ModelRenderer(this, 14, 78);
		leftToe2.addBox(-0.7F, 6.5F, -6.5F, 2, 2, 5);
		leftToe2.setRotationPoint(-4.5F, 16F, 1F);
		leftToe2.setTextureSize(128, 128);
		leftToe2.mirror = true;
		setRotation(leftToe2, -0.0872665F, 0.0872665F, 0F);
		rightToe = new ModelRenderer(this, 0, 85);
		rightToe.addBox(1.3F, 6.5F, -6.5F, 2, 2, 5);
		rightToe.setRotationPoint(4.5F, 16F, 1F);
		rightToe.setTextureSize(128, 128);
		rightToe.mirror = true;
		setRotation(rightToe, -0.0872665F, -0.1745329F, 0F);
		rightToe1 = new ModelRenderer(this, 28, 85);
		rightToe1.addBox(-3.7F, 6.5F, -6.5F, 2, 2, 5);
		rightToe1.setRotationPoint(4.5F, 16F, 1F);
		rightToe1.setTextureSize(128, 128);
		rightToe1.mirror = true;
		setRotation(rightToe1, -0.0872665F, 0F, 0F);
		rightToe2 = new ModelRenderer(this, 14, 85);
		rightToe2.addBox(-1.3F, 6.5F, -6.5F, 2, 2, 5);
		rightToe2.setRotationPoint(4.5F, 16F, 1F);
		rightToe2.setTextureSize(128, 128);
		rightToe2.mirror = true;
		setRotation(rightToe2, -0.0872665F, -0.0872665F, 0F);
		upperChest = new ModelRenderer(this, 84, 59);
		upperChest.addBox(-6.5F, -5F, -9F, 13, 3, 2);
		upperChest.setRotationPoint(0F, 6F, 0F);
		upperChest.setTextureSize(128, 128);
		upperChest.mirror = true;
		setRotation(upperChest, -0.6108652F, 0F, 0F);


		convertToChild(head, mouth);
		convertToChild(head, leftear);
		convertToChild(head, rightear);
		
		convertToChild(leftHand, leftFinger);
		convertToChild(leftHand, leftFinger1);
		convertToChild(leftHand, leftFinger2);
		convertToChild(leftHand, leftFinger3);
		
		convertToChild(leftShoulder, leftHand);
		
		
		
		convertToChild(rightHand, rightFinger);
		convertToChild(rightHand, rightFinger1);
		convertToChild(rightHand, rightFinger2);
		convertToChild(rightHand, rightFinger3);
		
		convertToChild(rightShoulder, rightHand);
		
	
		convertToChild(leftleg, leftToe);
		convertToChild(leftleg, leftToe1);
		convertToChild(leftleg, leftToe2);
		
		convertToChild(leftknee, leftleg);
		
		convertToChild(rightleg, rightToe);
		convertToChild(rightleg, rightToe1);
		convertToChild(rightleg, rightToe2);
		
		convertToChild(rightknee, rightleg);
		
		convertToChild(upperbody, head);
		convertToChild(upperbody, leftboob);
		convertToChild(upperbody, rightboob);
		convertToChild(upperbody, leftShoulder);
		convertToChild(upperbody, rightShoulder);
		
		convertToChild(belly, upperbody);
		
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		
		rightknee.render(f5);
		leftknee.render(f5);
		belly.render(f5);
			
	}
	
	public void sitDown()
	{
		movePiece(this.leftShoulder, 1f, -45f, 0f, 0f);
		movePiece(this.rightShoulder, 1f, -45f, 0f, 0f);
		
		movePiece(this.leftknee, 1f, -90f, 10f, 0f);
		movePiece(this.rightknee, 1f, -90f, -10f, 0f);
		movePiece(this.belly, 1f, 0f, 0f, 0f);
		
		moveoffSet(this.leftknee, 0.01f, 0.4f);
		moveoffSet(this.rightknee, 0.01f, 0.4f);
		moveoffSet(this.belly, 0.01f, 0.4f);
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
		
		EntityMarshDweller dweller = (EntityMarshDweller) entityIn;
	
		if(!dweller.isSittingDown())
		{
			float  f = 1f;
			this.head.rotateAngleY = netHeadYaw * 0.017453292F;
			this.head.rotateAngleX = headPitch * 0.017453292F;
			
			this.rightknee.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
			this.leftknee.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
			
			this.rightShoulder.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
			this.leftShoulder.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
		
			moveoffSet(this.leftknee, 0.01f, 0f);
			moveoffSet(this.rightknee, 0.01f, 0f);
			moveoffSet(this.belly, 0.01f, 0f);
		}else
		{
			this.sitDown();
		}
	}
}
