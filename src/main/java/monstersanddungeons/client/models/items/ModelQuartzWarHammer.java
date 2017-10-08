package monstersanddungeons.client.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQuartzWarHammer extends ModelBase{

	//Have to shift everything  186 Y Pixels
	public ModelRenderer Shaft;
	public ModelRenderer Middle_Piece;
	public ModelRenderer Bigger_Shaft;
	ModelRenderer Bottom_Shaft;
	ModelRenderer Right_Hammer;
	ModelRenderer LeftHammer;
	ModelRenderer LeftSpike11;
	ModelRenderer LeftSpike;
	ModelRenderer LeftSpike1;
	ModelRenderer LeftSpike2;
	ModelRenderer LeftSpike3;
	ModelRenderer LeftSpike4;
	ModelRenderer RightSpike1;
	ModelRenderer RightSpike2;
	ModelRenderer RightSpike3;
	ModelRenderer Leftspike5;
	ModelRenderer LeftSpike6;
	ModelRenderer LeftSpike7;
	ModelRenderer RightForeHammer;
	ModelRenderer LeftForeHammer;
	ModelRenderer RightSpike4;
	ModelRenderer RightSpike5;
	ModelRenderer LeftSpike8;
	ModelRenderer LeftSpike9;
	ModelRenderer LeftSpike10;
	ModelRenderer RightSpike;
	ModelRenderer RightSpike6;
	ModelRenderer Rightspike7;
	ModelRenderer RightSpike8;
	ModelRenderer RightSpike9;
	ModelRenderer RightSpike10;
	ModelRenderer RightSpike11;

	

	public ModelQuartzWarHammer()
	{
		this(0);
	}
	
	public ModelQuartzWarHammer(int offsetX) //- moves towards left hand, + towards right hand
	{
		textureWidth = 256;
		textureHeight = 256;

		Shaft = new ModelRenderer(this, 0, 186);
		Shaft.addBox(-1F + offsetX, -20.5F, -1F, 2, 25, 2);
		Shaft.setRotationPoint(0F, 11F, 0F);
		Shaft.setTextureSize(256, 256);
		Shaft.mirror = true;
		setRotation(Shaft, 0F, 0F, 0F);
		
		Middle_Piece = new ModelRenderer(this, 8, 10 + 186);
		Middle_Piece.addBox(-2F + offsetX, -1F, -2F, 4, 5, 4);
		Middle_Piece.setRotationPoint(0F, -15F, 0F);
		Middle_Piece.setTextureSize(256, 256);
		Middle_Piece.mirror = true;
		setRotation(Middle_Piece, 0F, 0F, 0F);
		
		Bigger_Shaft = new ModelRenderer(this, 8, 0 + 186);
		Bigger_Shaft.addBox(-1.5F + offsetX, -21.5F, -1.5F, 3, 7, 3);
		Bigger_Shaft.setRotationPoint(0F, 5F, 0F);
		Bigger_Shaft.setTextureSize(256, 256);
		Bigger_Shaft.mirror = true;
		setRotation(Bigger_Shaft, 0F, 0F, 0F);
		
		Bottom_Shaft = new ModelRenderer(this, 20, 0 + 186);
		Bottom_Shaft.addBox(-1.5F+ offsetX, 7.5F, -1.5F, 3, 2, 3);
		Bottom_Shaft.setRotationPoint(0F, 5F, 0F);
		Bottom_Shaft.setTextureSize(256, 256);
		Bottom_Shaft.mirror = true;
		setRotation(Bottom_Shaft, 0F, 0F, 0F);
		
		Right_Hammer = new ModelRenderer(this, 36, 19 + 186);
		Right_Hammer.addBox(-4F+ offsetX, -22F, -9F, 8, 8, 6);
		Right_Hammer.setRotationPoint(0F, 5F, 0F);
		Right_Hammer.setTextureSize(256, 256);
		Right_Hammer.mirror = true;
		setRotation(Right_Hammer, 0F, 0F, 0F);
		
		LeftHammer = new ModelRenderer(this, 8, 19 + 186);
		LeftHammer.addBox(-4F+ offsetX, -22F, 3F, 8, 8, 6);
		LeftHammer.setRotationPoint(0F, 5F, 0F);
		LeftHammer.setTextureSize(256, 256);
		LeftHammer.mirror = true;
		setRotation(LeftHammer, 0F, 0F, 0F);
		
		LeftSpike11 = new ModelRenderer(this, 0, 27 + 186);
		LeftSpike11.addBox(-4F+ offsetX, -16F, 9F, 1, 2, 1);
		LeftSpike11.setRotationPoint(0F, 5F, 0F);
		LeftSpike11.setTextureSize(256, 256);
		LeftSpike11.mirror = true;
		setRotation(LeftSpike11, 0F, 0F, 0F);
		
		LeftSpike = new ModelRenderer(this, 0, 27 + 186);
		LeftSpike.addBox(-4F+ offsetX, -22F, 9F, 1, 2, 1);
		LeftSpike.setRotationPoint(0F, 5F, 0F);
		LeftSpike.setTextureSize(256, 256);
		LeftSpike.mirror = true;
		setRotation(LeftSpike, 0F, 0F, 0F);
		
		LeftSpike1 = new ModelRenderer(this, 0, 27+ 186);
		LeftSpike1.addBox(-4F+ offsetX, -19F, 9F, 1, 2, 1);
		LeftSpike1.setRotationPoint(0F, 5F, 0F);
		LeftSpike1.setTextureSize(256, 256);
		LeftSpike1.mirror = true;
		setRotation(LeftSpike1, 0F, 0F, 0F);
		
		LeftSpike2 = new ModelRenderer(this, 0, 27+ 186);
		LeftSpike2.addBox(3F+ offsetX, -22F, 9F, 1, 2, 1);
		LeftSpike2.setRotationPoint(0F, 5F, 0F);
		LeftSpike2.setTextureSize(256, 256);
		LeftSpike2.mirror = true;
		setRotation(LeftSpike2, 0F, 0F, 0F);
		
		LeftSpike3 = new ModelRenderer(this, 0, 27+ 186);
		LeftSpike3.addBox(3F+ offsetX, -19F, 9F, 1, 2, 1);
		LeftSpike3.setRotationPoint(0F, 5F, 0F);
		LeftSpike3.setTextureSize(256, 256);
		LeftSpike3.mirror = true;
		setRotation(LeftSpike3, 0F, 0F, 0F);
		
		LeftSpike4 = new ModelRenderer(this, 0, 27+ 186);
		LeftSpike4.addBox(3F+ offsetX, -16F, 9F, 1, 2, 1);
		LeftSpike4.setRotationPoint(0F, 5F, 0F);
		LeftSpike4.setTextureSize(256, 256);
		LeftSpike4.mirror = true;
		setRotation(LeftSpike4, 0F, 0F, 0F);
		
		RightSpike1 = new ModelRenderer(this, 4, 27+ 186);
		RightSpike1.addBox(-3F+ offsetX, -22F, -10F, 1, 1, 1);
		RightSpike1.setRotationPoint(0F, 5F, 0F);
		RightSpike1.setTextureSize(256, 256);
		RightSpike1.mirror = true;
		setRotation(RightSpike1, 0F, 0F, 0F);
		
		RightSpike2 = new ModelRenderer(this, 4, 27 + 186);
		RightSpike2.addBox(2F+ offsetX, -22F, -10F, 1, 1, 1);
		RightSpike2.setRotationPoint(0F, 5F, 0F);
		RightSpike2.setTextureSize(256, 256);
		RightSpike2.mirror = true;
		setRotation(RightSpike2, 0F, 0F, 0F);
		
		RightSpike3 = new ModelRenderer(this, 4, 27 + 186);
		RightSpike3.addBox(2F+ offsetX, -15F, -10F, 1, 1, 1);
		RightSpike3.setRotationPoint(0F, 5F, 0F);
		RightSpike3.setTextureSize(256, 256);
		RightSpike3.mirror = true;
		setRotation(RightSpike3, 0F, 0F, 0F);
		
		Leftspike5 = new ModelRenderer(this, 4, 27+ 186);
		Leftspike5.addBox(-3F+ offsetX, -15F, 9F, 1, 1, 1);
		Leftspike5.setRotationPoint(0F, 5F, 0F);
		Leftspike5.setTextureSize(256, 256);
		Leftspike5.mirror = true;
		setRotation(Leftspike5, 0F, 0F, 0F);
		
		LeftSpike6 = new ModelRenderer(this, 0, 30 + 186);
		LeftSpike6.addBox(-1F+ offsetX, -22F, 9F, 2, 1, 1);
		LeftSpike6.setRotationPoint(0F, 5F, 0F);
		LeftSpike6.setTextureSize(256, 256);
		LeftSpike6.mirror = true;
		setRotation(LeftSpike6, 0F, 0F, 0F);
		
		LeftSpike7 = new ModelRenderer(this, 0, 30 + 186);
		LeftSpike7.addBox(-1F+ offsetX, -15F, 9F, 2, 1, 1);
		LeftSpike7.setRotationPoint(0F, 5F, 0F);
		LeftSpike7.setTextureSize(256, 256);
		LeftSpike7.mirror = true;
		setRotation(LeftSpike7, 0F, 0F, 0F);
		
		RightForeHammer = new ModelRenderer(this, 0, 33 + 186);
		RightForeHammer.addBox(-3.5F+ offsetX, -21.5F, -3F, 7, 7, 1);
		RightForeHammer.setRotationPoint(0F, 5F, 0F);
		RightForeHammer.setTextureSize(256, 256);
		RightForeHammer.mirror = true;
		setRotation(RightForeHammer, 0F, 0F, 0F);
		
		LeftForeHammer = new ModelRenderer(this, 0, 33 + 186);
		LeftForeHammer.addBox(-3.5F+ offsetX, -21.5F, 2F, 7, 7, 1);
		LeftForeHammer.setRotationPoint(0F, 5F, 0F);
		LeftForeHammer.setTextureSize(256, 256);
		LeftForeHammer.mirror = true;
		setRotation(LeftForeHammer, 0F, 0F, 0F);
		
		RightSpike4 = new ModelRenderer(this, 0, 30 + 186);
		RightSpike4.addBox(-1F+ offsetX, -22F, -10F, 2, 1, 1);
		RightSpike4.setRotationPoint(0F, 5F, 0F);
		RightSpike4.setTextureSize(256, 256);
		RightSpike4.mirror = true;
		setRotation(RightSpike4, 0F, 0F, 0F);
		
		RightSpike5 = new ModelRenderer(this, 0, 30+ 186);
		RightSpike5.addBox(-1F+ offsetX, -15F, -10F, 2, 1, 1);
		RightSpike5.setRotationPoint(0F, 5F, 0F);
		RightSpike5.setTextureSize(256, 256);
		RightSpike5.mirror = true;
		setRotation(RightSpike5, 0F, 0F, 0F);
		
		LeftSpike8 = new ModelRenderer(this, 4, 27 + 186);
		LeftSpike8.addBox(2F+ offsetX, -22F, 9F, 1, 1, 1);
		LeftSpike8.setRotationPoint(0F, 5F, 0F);
		LeftSpike8.setTextureSize(256, 256);
		LeftSpike8.mirror = true;
		setRotation(LeftSpike8, 0F, 0F, 0F);
		
		LeftSpike9 = new ModelRenderer(this, 4, 27+ 186);
		LeftSpike9.addBox(-3F+ offsetX, -22F, 9F, 1, 1, 1);
		LeftSpike9.setRotationPoint(0F, 5F, 0F);
		LeftSpike9.setTextureSize(256, 256);
		LeftSpike9.mirror = true;
		setRotation(LeftSpike9, 0F, 0F, 0F);
		
		LeftSpike10 = new ModelRenderer(this, 4, 27+ 186);
		LeftSpike10.addBox(2F+ offsetX, -15F, 9F, 1, 1, 1);
		LeftSpike10.setRotationPoint(0F, 5F, 0F);
		LeftSpike10.setTextureSize(256, 256);
		LeftSpike10.mirror = true;
		setRotation(LeftSpike10, 0F, 0F, 0F);
		
		RightSpike = new ModelRenderer(this, 4, 27+ 186);
		RightSpike.addBox(-3F+ offsetX, -15F, -10F, 1, 1, 1);
		RightSpike.setRotationPoint(0F, 5F, 0F);
		RightSpike.setTextureSize(256, 256);
		RightSpike.mirror = true;
		setRotation(RightSpike, 0F, 0F, 0F);
		
		RightSpike6 = new ModelRenderer(this, 0, 27+ 186);
		RightSpike6.addBox(-4F+ offsetX, -22F, -10F, 1, 2, 1);
		RightSpike6.setRotationPoint(0F, 5F, 0F);
		RightSpike6.setTextureSize(256, 256);
		RightSpike6.mirror = true;
		setRotation(RightSpike6, 0F, 0F, 0F);
		
		Rightspike7 = new ModelRenderer(this, 0, 27+ 186);
		Rightspike7.addBox(-4F+ offsetX, -19F, -10F, 1, 2, 1);
		Rightspike7.setRotationPoint(0F, 5F, 0F);
		Rightspike7.setTextureSize(256, 256);
		Rightspike7.mirror = true;
		setRotation(Rightspike7, 0F, 0F, 0F);
		
		RightSpike8 = new ModelRenderer(this, 0, 27+ 186);
		RightSpike8.addBox(-4F+ offsetX, -16F, -10F, 1, 2, 1);
		RightSpike8.setRotationPoint(0F, 5F, 0F);
		RightSpike8.setTextureSize(256, 256);
		RightSpike8.mirror = true;
		setRotation(RightSpike8, 0F, 0F, 0F);
		
		RightSpike9 = new ModelRenderer(this, 0, 27+ 186);
		RightSpike9.addBox(3F+ offsetX, -22F, -10F, 1, 2, 1);
		RightSpike9.setRotationPoint(0F, 5F, 0F);
		RightSpike9.setTextureSize(256, 256);
		RightSpike9.mirror = true;
		setRotation(RightSpike9, 0F, 0F, 0F);
		
		RightSpike10 = new ModelRenderer(this, 0, 27+ 186);
		RightSpike10.addBox(3F+ offsetX, -19F, -10F, 1, 2, 1);
		RightSpike10.setRotationPoint(0F, 5F, 0F);
		RightSpike10.setTextureSize(256, 256);
		RightSpike10.mirror = true;
		setRotation(RightSpike10, 0F, 0F, 0F);
		
		RightSpike11 = new ModelRenderer(this, 0, 27+ 186);
		RightSpike11.addBox(3F+ offsetX, -16F, -10F, 1, 2, 1);
		RightSpike11.setRotationPoint(0F, 5F, 0F);
		RightSpike11.setTextureSize(256, 256);
		RightSpike11.mirror = true;
		setRotation(RightSpike11, 0F, 0F, 0F);
		
		
		convertToChild(Right_Hammer, RightSpike);
		convertToChild(Right_Hammer, RightSpike1);
		convertToChild(Right_Hammer, RightSpike2);
		convertToChild(Right_Hammer, RightSpike3);
		convertToChild(Right_Hammer, RightSpike4);
		convertToChild(Right_Hammer, RightSpike5);
		convertToChild(Right_Hammer, RightSpike6);
		convertToChild(Right_Hammer, Rightspike7);
		convertToChild(Right_Hammer, RightSpike8);
		convertToChild(Right_Hammer, RightSpike9);
		convertToChild(Right_Hammer, RightSpike10);
		convertToChild(Right_Hammer, RightSpike11);
		
		convertToChild(LeftHammer, LeftSpike);
		convertToChild(LeftHammer, LeftSpike1);
		convertToChild(LeftHammer, LeftSpike2);
		convertToChild(LeftHammer, LeftSpike3);
		convertToChild(LeftHammer, LeftSpike4);
		convertToChild(LeftHammer, Leftspike5);
		convertToChild(LeftHammer, LeftSpike6);
		convertToChild(LeftHammer, LeftSpike7);
		convertToChild(LeftHammer, LeftSpike8);
		convertToChild(LeftHammer, LeftSpike9);
		convertToChild(LeftHammer, LeftSpike10);
		convertToChild(LeftHammer, LeftSpike11);
		
		convertToChild(LeftForeHammer, LeftHammer);
		convertToChild(RightForeHammer, Right_Hammer);
		
		convertToChild(Middle_Piece, LeftForeHammer);
		convertToChild(Middle_Piece, RightForeHammer);
		convertToChild(Bigger_Shaft, Middle_Piece);
		
		convertToChild(Shaft, Bottom_Shaft);
		convertToChild(Shaft, Bigger_Shaft);
	
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
	
		Shaft.render(f5);
	}
	
	
	
	public void attachShaftTo(ModelRenderer parent)
	{
		convertToChild(parent, this.Shaft);
	}
	
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}
	
	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
	{
		// move child rotation point to be relative to parent
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		// make rotations relative to parent
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		// create relationship
		parParent.addChild(parChild);
	}

	protected float degToRad(float degrees)
	{
		return degrees * (float)Math.PI / 180 ;
	}
}
