package monstersanddungeons.client.models;

import monstersanddungeons.entity.automatons.EntityAutomatonsRook;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAutomatonsRook extends MaDEntityModelBase {

	protected float[][] SlamCycle = new float[][]
			{//
			{   0F  ,  0F  ,    0F, 0F,  0F , 0f}, //lower shoulders, upper shoulders, middle piece, lower legs, translation point
			{ -15F   , 0F   ,   0F, 0F,  0F, 0f},
			{ -30F ,   0F   ,   0F,  0F, 0F,  0f },
			{ -45F ,   -15F  ,  0F,  0F,  0F, 0f},
			{ -45F  , -30F  ,   0F,  0F, 0F,0f },
			{ -60F ,  -60F  ,   0F,  0F,  0F,0f },
			{ -60F  , -90F  ,   -5F,  0F,  0F,0f },
			{ -45F  , -120F  ,  -5F,  0F,  0F,0f },
			{ -30F  , -150F  ,  -10F,  0F,  0F,0f },
			{ -15F  , -180F  ,  -10F,  0F,  0F,0f },
			{   0F  , -180F  ,  -15F,  0F,   0F, 0}, 
			//charging up cycle
			{   0F  , -180F  ,  -15F,  0F,  0F, 0 }, 
			{   0F  , -180F  ,   0F,  0F,   0F, 0f}, 
			{   0F  , -180F  ,   15F,  15F,  10F, 0}, 
			{   0F  , -180F  ,   30F,  30F, 20F, 0}, 
			{   0F  , -160F  ,   45F,  75F, 50F, 1f}, 
			{   0F  , -140F  ,   60F,  90F, 50F, 1.5f},
			{   0F  , -160F  ,   90F,  75F, 15F, 1.5f},  
			{   0F  , -160F  ,   90F,  75F, 15F, 1.5f},  
			{   0F  , -160F  ,   90F,  75F, 15F, 1.5f},  
			{   0F  , -160F  ,   90F,  75F, 15F, 1.5f},  
			{   0F  , -160F  ,   90F,  75F, 15F, 1.5f},  
			{   0F  , -160F  ,   90F,  75F, 15F, 1.5f},  
			{   0F  , -110F  ,   50F,  75F, 15F, 1.5f},  
			{   0F  , -80F  ,    30F,  50F, 15F, 1f},  
			{   0F  , -40F  ,    20F,  30F, 0F, 0.5f},  
			{   0F  ,  0F  ,    0F, 0F,  0F , 0f  }, 
			};

	protected float[][] LeftPunchCycle = new float[][]
			{//
			{   0F  ,   0F  ,    0F, 0F,  0F , -15f }, 
			//left shoulder, upper shoulders, middle pieceX, middle piece Y, belt pieces, right shoulder
			{   -90F,  15F,  15F, -15F,  -10F,  -15F}, 
			{   -90F,  15F,  15F, -45F,  -15F,  -15F}, 
			{   -90F,  15F,  15F, -65F,  -15F,  -15F}, 
			{   -115F,  15F,  24F, -65F,  -15F,  -15F},
			{   -135F,  15F,  30F, -55F,  -25F,  -15F}, 
			{   -115F,  15F,  30F, -30F,  -20F,  -15F},
			{   -100F,  0F,  30F, -10F,  -20F,  -15F}, 
			{   -80F,  -15F,  30F, 20F,  -10F,  -15F},
			{   -40F,  -40F,  30F, 40F,  10F,  -15F},
			{    0F,  -40F,  30F, 40F,  10F,  -0F}, 
			{    0F,  -40F,  15F, 20F,  10F,  -0F}, 
			{    0F,   0,  0F,  0F,  0F,  0F},
			{   -15F,  15F,  15F, 15F,  -10F,  -90F}, 
			{   -15F,  15F,  15F, 45F,  -15F,  -90F}, 
			{   -15F,  15F,  15F, 65F,  -15F,  -90F}, 
			{   -15F,  15F,  24F, 65F,  -15F,  -115F},
			{   -15F,  15F,  30F, 55F,  -25F,  -135F}, 
			{   -15F,  15F,  30F, 30F,  -20F,  -115F},
			{   -15F,  0F,  30F,   10F,  -20F,  -100F}, 
			{   -15F,  -15F,  30F,  -20F,  -10F,  -80F},
			{   -15F,  -40F,  30F,  -40F,  10F,  -40F},
			{    -15F,  -40F,  30F, -40F,  10F,  -0F}, 
			{    -0F,  -40F,  15F, -20F,  10F,  -0F}, 
			{    0F,  0F,  0F, 0F,  0F,  0F}, 
			};

	protected float[][] KickAnimation = new float[][]
			{//
			{   0F, 0F,  0F,  0F}, // middle piece, right belt, right shin,
			{   10F, 0F,  10F, 0F},
			{   20F, 20F,  20F, 0F},
			{   30F, 30F,  30F, 0F},
			{   30F, 30F,  45F, 0F},
			{   30F, 30F,  45F, 0F},
			{   15F, 20F,  30F, 0F},
			{   15F, 20F,  30F, 0F},
			{   10F, 10F,  10F, 0F},
			{   10F, 10F,  10F, 0F},
			{   -10F, 0F,  0F,  0F},
			{   -10F, -20F,  -10F,  0F},
			{   -10F, -20F,  -10F,  0F},//12
			{   -10F, -20F,  -40F,  0F},//13
			{   -10F, -30F,  -60F,  0F},//14
			{   -10F, -20F,  -40F,  0F},//15
			{   -10F, -20F,  -10F,  0F},//16
			};


	ModelRenderer rightBoot;
	ModelRenderer LeftBoot;
	ModelRenderer LeftShin;
	ModelRenderer RightShin;
	ModelRenderer LeftPieceThing;
	ModelRenderer RightPieceThing;
	ModelRenderer RightLegArmor;
	ModelRenderer LeftLegArmor;
	ModelRenderer LeftBelt;
	ModelRenderer MiddlePiece;
	ModelRenderer rightMiddlePiece;
	ModelRenderer ChestOne;
	ModelRenderer ChestTwo;
	ModelRenderer UpperChest;
	ModelRenderer UpperChest1;
	ModelRenderer UpperChest2;
	ModelRenderer LegMiddleThing;
	ModelRenderer LegMiddle1;
	ModelRenderer LegMiddle2;
	ModelRenderer LegMiddle3;
	ModelRenderer BackPieceLeg;
	ModelRenderer BackMiddleThing;
	ModelRenderer smallCube;
	ModelRenderer smallCube2;
	ModelRenderer LegMiddleThing2;
	ModelRenderer LegMiddleThing3;
	ModelRenderer BackMiddleThing2;
	ModelRenderer BackMiddleThing3;
	ModelRenderer Head1;
	ModelRenderer Neck;
	ModelRenderer Head2;
	ModelRenderer Head3;
	ModelRenderer Head4;
	ModelRenderer Head5;
	ModelRenderer Head6;
	ModelRenderer Head7;
	ModelRenderer Head8;
	ModelRenderer Head9;
	ModelRenderer WeakSpot1;
	ModelRenderer WeakSpotLeg2;
	ModelRenderer WeakSpotLeg3;
	ModelRenderer WeakSpot2;
	ModelRenderer WeakSpot3;
	ModelRenderer WeakSpot4;
	ModelRenderer Unknown;
	ModelRenderer WeakSpot5;
	ModelRenderer WeakSpot7;
	ModelRenderer WeakSpot6;
	ModelRenderer WeakSpot8;
	ModelRenderer WeakSpot9;
	ModelRenderer WeakSpot10;
	ModelRenderer WeakSpot11;
	ModelRenderer WeakSpot12;
	ModelRenderer WeakSpot13;
	ModelRenderer WeakSpot14;
	ModelRenderer WeakSpot15;
	ModelRenderer WeakSpot17;
	ModelRenderer WeakSpot18;
	ModelRenderer WeakSpot19;
	ModelRenderer WeakSpot20;
	ModelRenderer LeftShoulder1;
	ModelRenderer RightShoulder1;
	ModelRenderer LeftShoulder2;
	ModelRenderer RightShoulder2;
	ModelRenderer GoldLeftShoulder3;
	ModelRenderer Unknown2;
	ModelRenderer LeftShoulder3;
	ModelRenderer RightShoulder4;
	ModelRenderer rightHand1;
	ModelRenderer RightFinger1;
	ModelRenderer RightFinger2;
	ModelRenderer RightThumb;
	ModelRenderer RightWrist;
	ModelRenderer RightHand2;
	ModelRenderer LeftWrist;
	ModelRenderer LeftHand1;
	ModelRenderer LeftFinger1;
	ModelRenderer LeftFinger2;
	ModelRenderer LeftFinger3;
	ModelRenderer LeftThumb;
	ModelRenderer RightEye;
	ModelRenderer LeftEye;

	public ModelAutomatonsRook()
	{
		textureWidth = 256;
		textureHeight = 256;

		rightBoot = new ModelRenderer(this, 0, 0);
		rightBoot.addBox(-3.5F, 3F, -3.5F, 7, 12, 7);
		rightBoot.setRotationPoint(6.5F, 9F, 0F);
		rightBoot.setTextureSize(256, 256);
		rightBoot.mirror = true;
		setRotation(rightBoot, 0F, 0F, 0F);
		LeftBoot = new ModelRenderer(this, 28, 0);
		LeftBoot.addBox(-3.5F, 3F, -3.5F, 7, 12, 7);
		LeftBoot.setRotationPoint(-6.5F, 9F, 0F);
		LeftBoot.setTextureSize(256, 256);
		LeftBoot.mirror = true;
		setRotation(LeftBoot, 0F, 0F, 0F);
		LeftShin = new ModelRenderer(this, 56, 0);
		LeftShin.addBox(-2.5F, -1F, -4F, 5, 5, 5);
		LeftShin.setRotationPoint(-6.5F, 9F, 0F);
		LeftShin.setTextureSize(256, 256);
		LeftShin.mirror = true;
		setRotation(LeftShin, -0.0872665F, 0F, 0F);
		RightShin = new ModelRenderer(this, 56, 10);
		RightShin.addBox(-2.5F, -1F, -4F, 5, 5, 5);
		RightShin.setRotationPoint(6.5F, 9F, 0F);
		RightShin.setTextureSize(256, 256);
		RightShin.mirror = true;
		setRotation(RightShin, -0.0872665F, 0F, 0F);
		LeftPieceThing = new ModelRenderer(this, 0, 20);
		LeftPieceThing.addBox(-3.5F, 0F, -3F, 8, 13, 6);
		LeftPieceThing.setRotationPoint(-4F, -4F, 0F);
		LeftPieceThing.setTextureSize(256, 256);
		LeftPieceThing.mirror = true;
		setRotation(LeftPieceThing, 0F, 0F, 0.1919862F);
		RightPieceThing = new ModelRenderer(this, 28, 20);
		RightPieceThing.addBox(-4.5F, 0F, -3F, 8, 13, 6);
		RightPieceThing.setRotationPoint(4F, -4F, 0F);
		RightPieceThing.setTextureSize(256, 256);
		RightPieceThing.mirror = true;
		setRotation(RightPieceThing, 0F, 0F, -0.1919862F);
		RightLegArmor = new ModelRenderer(this, 32, 39);
		RightLegArmor.addBox(-3.5F, 0F, -4F, 8, 11, 8);
		RightLegArmor.setRotationPoint(4F, -4F, 0F);
		RightLegArmor.setTextureSize(256, 256);
		RightLegArmor.mirror = true;
		setRotation(RightLegArmor, 0F, 0F, -0.2792527F);
		LeftLegArmor = new ModelRenderer(this, 0, 39);
		LeftLegArmor.addBox(-4.5F, 0F, -4F, 8, 11, 8);
		LeftLegArmor.setRotationPoint(-4F, -4F, 0F);
		LeftLegArmor.setTextureSize(256, 256);
		LeftLegArmor.mirror = true;
		setRotation(LeftLegArmor, 0F, 0F, 0.2792527F);
		LeftBelt = new ModelRenderer(this, 0, 58);
		LeftBelt.addBox(-9F, 0F, -4.5F, 9, 4, 9);
		LeftBelt.setRotationPoint(0F, -6F, 0F);
		LeftBelt.setTextureSize(256, 256);
		LeftBelt.mirror = true;
		setRotation(LeftBelt, 0F, 0F, 0.122173F);
		MiddlePiece = new ModelRenderer(this, 0, 71);
		MiddlePiece.addBox(-7.5F, -9F, -4F, 15, 11, 8);
		MiddlePiece.setRotationPoint(0F, -8F, 0F);
		MiddlePiece.setTextureSize(256, 256);
		MiddlePiece.mirror = true;
		setRotation(MiddlePiece, 0F, 0F, 0F);
		rightMiddlePiece = new ModelRenderer(this, 36, 58);
		rightMiddlePiece.addBox(0F, 0F, -4.5F, 9, 4, 9);
		rightMiddlePiece.setRotationPoint(0F, -6F, 0F);
		rightMiddlePiece.setTextureSize(256, 256);
		rightMiddlePiece.mirror = true;
		setRotation(rightMiddlePiece, 0F, 0F, -0.122173F);
		ChestOne = new ModelRenderer(this, 62, 90);
		ChestOne.addBox(-9.3F, -4F, -5F, 9, 7, 10);
		ChestOne.setRotationPoint(0F, -15F, 0F);
		ChestOne.setTextureSize(256, 256);
		ChestOne.mirror = true;
		setRotation(ChestOne, 0.0523599F, 0F, -0.1745329F);
		ChestTwo = new ModelRenderer(this, 100, 90);
		ChestTwo.addBox(0.3F, -4F, -5F, 9, 7, 10);
		ChestTwo.setRotationPoint(0F, -15F, 0F);
		ChestTwo.setTextureSize(256, 256);
		ChestTwo.mirror = true;
		setRotation(ChestTwo, 0.0523599F, 0F, 0.1745329F);
		UpperChest = new ModelRenderer(this, 0, 90);
		UpperChest.addBox(-10F, -6F, -5.5F, 20, 9, 11);
		UpperChest.setRotationPoint(0F, -18F, 0F);
		UpperChest.setTextureSize(256, 256);
		UpperChest.mirror = true;
		setRotation(UpperChest, -0.1047198F, 0F, 0F);
		UpperChest1 = new ModelRenderer(this, 0, 111);
		UpperChest1.addBox(-8F, -8F, -6.5F, 3, 5, 13);
		UpperChest1.setRotationPoint(0F, -18F, 0F);
		UpperChest1.setTextureSize(256, 256);
		UpperChest1.mirror = true;
		setRotation(UpperChest1, -0.1047198F, 0F, 0F);
		UpperChest2 = new ModelRenderer(this, 0, 129);
		UpperChest2.addBox(5F, -8F, -6.5F, 3, 5, 13);
		UpperChest2.setRotationPoint(0F, -18F, 0F);
		UpperChest2.setTextureSize(256, 256);
		UpperChest2.mirror = true;
		setRotation(UpperChest2, -0.1047198F, 0F, 0F);
		LegMiddleThing = new ModelRenderer(this, 86, 58);
		LegMiddleThing.addBox(-1F, 14.5F, -5F, 2, 1, 1);
		LegMiddleThing.setRotationPoint(0F, -6F, 0F);
		LegMiddleThing.setTextureSize(256, 256);
		LegMiddleThing.mirror = true;
		setRotation(LegMiddleThing, -0.0523599F, 0F, 0F);
		LegMiddle1 = new ModelRenderer(this, 72, 58);
		LegMiddle1.addBox(-3F, -0.5F, -5F, 6, 15, 1);
		LegMiddle1.setRotationPoint(0F, -6F, 0F);
		LegMiddle1.setTextureSize(256, 256);
		LegMiddle1.mirror = true;
		setRotation(LegMiddle1, -0.0523599F, 0F, 0F);
		LegMiddle2 = new ModelRenderer(this, 86, 60);
		LegMiddle2.addBox(-3F, 14.5F, -5F, 1, 1, 1);
		LegMiddle2.setRotationPoint(0F, -6F, 0F);
		LegMiddle2.setTextureSize(256, 256);
		LegMiddle2.mirror = true;
		setRotation(LegMiddle2, -0.0523599F, 0F, 0F);
		LegMiddle3 = new ModelRenderer(this, 86, 63);
		LegMiddle3.addBox(2F, 14.5F, -5F, 1, 1, 1);
		LegMiddle3.setRotationPoint(0F, -6F, 0F);
		LegMiddle3.setTextureSize(256, 256);
		LegMiddle3.mirror = true;
		setRotation(LegMiddle3, -0.0523599F, 0F, 0F);
		BackPieceLeg = new ModelRenderer(this, 73, 45);
		BackPieceLeg.addBox(-3F, -0.5F, 4F, 6, 12, 1);
		BackPieceLeg.setRotationPoint(0F, -6F, 0F);
		BackPieceLeg.setTextureSize(256, 256);
		BackPieceLeg.mirror = true;
		setRotation(BackPieceLeg, 0.0523599F, 0F, 0F);
		BackMiddleThing = new ModelRenderer(this, 87, 45);
		BackMiddleThing.addBox(-1F, 11.5F, 4F, 2, 1, 1);
		BackMiddleThing.setRotationPoint(0F, -6F, 0F);
		BackMiddleThing.setTextureSize(256, 256);
		BackMiddleThing.mirror = true;
		setRotation(BackMiddleThing, 0.0523599F, 0F, 0F);
		smallCube = new ModelRenderer(this, 87, 47);
		smallCube.addBox(-3F, 11.5F, 4F, 1, 1, 1);
		smallCube.setRotationPoint(0F, -6F, 0F);
		smallCube.setTextureSize(256, 256);
		smallCube.mirror = true;
		setRotation(smallCube, 0.0523599F, 0F, 0F);
		smallCube2 = new ModelRenderer(this, 87, 49);
		smallCube2.addBox(2F, 11.5F, 4F, 1, 1, 1);
		smallCube2.setRotationPoint(0F, -6F, 0F);
		smallCube2.setTextureSize(256, 256);
		smallCube2.mirror = true;
		setRotation(smallCube2, 0.0523599F, 0F, 0F);
		LegMiddleThing2 = new ModelRenderer(this, 72, 35);
		LegMiddleThing2.addBox(-4F, 3.5F, -4F, 4, 9, 1);
		LegMiddleThing2.setRotationPoint(0F, -6F, 0F);
		LegMiddleThing2.setTextureSize(256, 256);
		LegMiddleThing2.mirror = true;
		setRotation(LegMiddleThing2, -0.0872665F, 0F, 0.0872665F);
		LegMiddleThing3 = new ModelRenderer(this, 82, 35);
		LegMiddleThing3.addBox(0F, 3.5F, -4F, 4, 9, 1);
		LegMiddleThing3.setRotationPoint(0F, -6F, 0F);
		LegMiddleThing3.setTextureSize(256, 256);
		LegMiddleThing3.mirror = true;
		setRotation(LegMiddleThing3, -0.0872665F, 0F, -0.0872665F);
		BackMiddleThing2 = new ModelRenderer(this, 72, 27);
		BackMiddleThing2.addBox(0F, 3.5F, 3F, 4, 7, 1);
		BackMiddleThing2.setRotationPoint(0F, -6F, 0F);
		BackMiddleThing2.setTextureSize(256, 256);
		BackMiddleThing2.mirror = true;
		setRotation(BackMiddleThing2, 0.0872665F, 0F, -0.0872665F);
		BackMiddleThing3 = new ModelRenderer(this, 82, 27);
		BackMiddleThing3.addBox(-4F, 3.5F, 3F, 4, 7, 1);
		BackMiddleThing3.setRotationPoint(0F, -6F, 0F);
		BackMiddleThing3.setTextureSize(256, 256);
		BackMiddleThing3.mirror = true;
		setRotation(BackMiddleThing3, 0.0872665F, 0F, 0.0872665F);
		Head1 = new ModelRenderer(this, 32, 111);
		Head1.addBox(-4F, -9.5F, -4F, 8, 8, 8);
		Head1.setRotationPoint(0F, -24F, 1F);
		Head1.setTextureSize(256, 256);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 32, 129);
		Neck.addBox(-4F, -1.5F, -3F, 8, 2, 7);
		Neck.setRotationPoint(0F, -24F, 1F);
		Neck.setTextureSize(256, 256);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 64, 124);
		Head2.addBox(-1F, -11.5F, -5F, 2, 12, 2);
		Head2.setRotationPoint(0F, -24F, 1F);
		Head2.setTextureSize(256, 256);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);
		Head3 = new ModelRenderer(this, 72, 111);
		Head3.addBox(2.5F, -10.5F, -4.5F, 2, 11, 2);
		Head3.setRotationPoint(0F, -24F, 1F);
		Head3.setTextureSize(256, 256);
		Head3.mirror = true;
		setRotation(Head3, 0F, 0F, 0F);
		Head4 = new ModelRenderer(this, 64, 111);
		Head4.addBox(-4.5F, -10.5F, -4.5F, 2, 11, 2);
		Head4.setRotationPoint(0F, -24F, 1F);
		Head4.setTextureSize(256, 256);
		Head4.mirror = true;
		setRotation(Head4, 0F, 0F, 0F);
		Head5 = new ModelRenderer(this, 80, 111);
		Head5.addBox(2.5F, -10.5F, 2.5F, 2, 10, 2);
		Head5.setRotationPoint(0F, -24F, 1F);
		Head5.setTextureSize(256, 256);
		Head5.mirror = true;
		setRotation(Head5, 0F, 0F, 0F);
		Head6 = new ModelRenderer(this, 88, 111);
		Head6.addBox(-4.5F, -10.5F, 2.5F, 2, 10, 2);
		Head6.setRotationPoint(0F, -24F, 1F);
		Head6.setTextureSize(256, 256);
		Head6.mirror = true;
		setRotation(Head6, 0F, 0F, 0F);
		Head7 = new ModelRenderer(this, 96, 111);
		Head7.addBox(-1F, -10.5F, 2.5F, 2, 10, 2);
		Head7.setRotationPoint(0F, -24F, 1F);
		Head7.setTextureSize(256, 256);
		Head7.mirror = true;
		setRotation(Head7, 0F, 0F, 0F);
		Head8 = new ModelRenderer(this, 105, 111);
		Head8.addBox(2.5F, -10.5F, -1F, 2, 11, 2);
		Head8.setRotationPoint(0F, -24F, 1F);
		Head8.setTextureSize(256, 256);
		Head8.mirror = true;
		setRotation(Head8, 0F, 0F, 0F);
		Head9 = new ModelRenderer(this, 105, 111);
		Head9.addBox(-4.5F, -10.5F, -1F, 2, 11, 2);
		Head9.setRotationPoint(0F, -24F, 1F);
		Head9.setTextureSize(256, 256);
		Head9.mirror = true;
		setRotation(Head9, 0F, 0F, 0F);
		WeakSpot1 = new ModelRenderer(this, 139, 90);
		WeakSpot1.addBox(-2F, -3.5F, 4.5F, 1, 8, 2);
		WeakSpot1.setRotationPoint(0F, -18F, 0F);
		WeakSpot1.setTextureSize(256, 256);
		WeakSpot1.mirror = true;
		setRotation(WeakSpot1, -0.1047198F, 0F, 0F);
		WeakSpotLeg2 = new ModelRenderer(this, 76, 0);
		WeakSpotLeg2.addBox(-1.5F, -1F, 0.5F, 3, 5, 2);
		WeakSpotLeg2.setRotationPoint(-6.5F, 9F, 0F);
		WeakSpotLeg2.setTextureSize(256, 256);
		WeakSpotLeg2.mirror = true;
		setRotation(WeakSpotLeg2, 0.0872665F, 0F, 0F);
		WeakSpotLeg3 = new ModelRenderer(this, 76, 8);
		WeakSpotLeg3.addBox(-1.5F, -1F, 0.5F, 3, 5, 2);
		WeakSpotLeg3.setRotationPoint(6.5F, 9F, 0F);
		WeakSpotLeg3.setTextureSize(256, 256);
		WeakSpotLeg3.mirror = true;
		setRotation(WeakSpotLeg3, 0.0872665F, 0F, 0F);
		WeakSpot2 = new ModelRenderer(this, 146, 90);
		WeakSpot2.addBox(1F, -3.5F, 4.5F, 1, 8, 2);
		WeakSpot2.setRotationPoint(0F, -18F, 0F);
		WeakSpot2.setTextureSize(256, 256);
		WeakSpot2.mirror = true;
		setRotation(WeakSpot2, -0.1047198F, 0F, 0F);
		WeakSpot3 = new ModelRenderer(this, 153, 90);
		WeakSpot3.addBox(-1F, -3.5F, 4.5F, 2, 1, 2);
		WeakSpot3.setRotationPoint(0F, -18F, 0F);
		WeakSpot3.setTextureSize(256, 256);
		WeakSpot3.mirror = true;
		setRotation(WeakSpot3, -0.1047198F, 0F, 0F);
		WeakSpot4 = new ModelRenderer(this, 153, 94);
		WeakSpot4.addBox(-1F, 3.5F, 4.5F, 2, 1, 2);
		WeakSpot4.setRotationPoint(0F, -18F, 0F);
		WeakSpot4.setTextureSize(256, 256);
		WeakSpot4.mirror = true;
		setRotation(WeakSpot4, -0.1047198F, 0F, 0F);
		Unknown = new ModelRenderer(this, 139, 101);
		Unknown.addBox(-1F, -2.5F, 4F, 2, 6, 2);
		Unknown.setRotationPoint(0F, -18F, 0F);
		Unknown.setTextureSize(256, 256);
		Unknown.mirror = true;
		setRotation(Unknown, -0.1047198F, 0F, 0F);
		WeakSpot5 = new ModelRenderer(this, 0, 148);
		WeakSpot5.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		WeakSpot5.setRotationPoint(0F, -17F, 6F);
		WeakSpot5.setTextureSize(256, 256);
		WeakSpot5.mirror = true;
		setRotation(WeakSpot5, 0F, 0F, 0F);
		WeakSpot7 = new ModelRenderer(this, 0, 154);
		WeakSpot7.addBox(-1F, -1.5F, 1F, 2, 3, 1);
		WeakSpot7.setRotationPoint(0F, -17F, 6F);
		WeakSpot7.setTextureSize(256, 256);
		WeakSpot7.mirror = true;
		setRotation(WeakSpot7, 0F, 0F, 0F);
		WeakSpot6 = new ModelRenderer(this, 19, 148);
		WeakSpot6.addBox(-1F, -1.5F, -2F, 2, 3, 1);
		WeakSpot6.setRotationPoint(0F, -17F, 6F);
		WeakSpot6.setTextureSize(256, 256);
		WeakSpot6.mirror = true;
		setRotation(WeakSpot6, 0F, 0F, 0F);
		WeakSpot8 = new ModelRenderer(this, 8, 148);
		WeakSpot8.addBox(-1F, -2F, -1.5F, 2, 1, 3);
		WeakSpot8.setRotationPoint(0F, -17F, 6F);
		WeakSpot8.setTextureSize(256, 256);
		WeakSpot8.mirror = true;
		setRotation(WeakSpot8, 0F, 0F, 0F);
		WeakSpot9 = new ModelRenderer(this, 27, 148);
		WeakSpot9.addBox(-1F, 1F, -1.5F, 2, 1, 3);
		WeakSpot9.setRotationPoint(0F, -17F, 6F);
		WeakSpot9.setTextureSize(256, 256);
		WeakSpot9.mirror = true;
		setRotation(WeakSpot9, 0F, 0F, 0F);
		WeakSpot10 = new ModelRenderer(this, 7, 154);
		WeakSpot10.addBox(-0.5F, -0.5F, 1.5F, 1, 1, 1);
		WeakSpot10.setRotationPoint(0F, -17F, 6F);
		WeakSpot10.setTextureSize(256, 256);
		WeakSpot10.mirror = true;
		setRotation(WeakSpot10, 0F, 0F, 0F);
		WeakSpot11 = new ModelRenderer(this, 7, 158);
		WeakSpot11.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 1);
		WeakSpot11.setRotationPoint(0F, -17F, 6F);
		WeakSpot11.setTextureSize(256, 256);
		WeakSpot11.mirror = true;
		setRotation(WeakSpot11, 0F, 0F, 0F);
		WeakSpot12 = new ModelRenderer(this, 7, 162);
		WeakSpot12.addBox(-0.5F, -2.5F, -0.5F, 1, 1, 1);
		WeakSpot12.setRotationPoint(0F, -17F, 6F);
		WeakSpot12.setTextureSize(256, 256);
		WeakSpot12.mirror = true;
		setRotation(WeakSpot12, 0F, 0F, 0F);
		WeakSpot13 = new ModelRenderer(this, 1, 162);
		WeakSpot13.addBox(-0.5F, 1.5F, -0.5F, 1, 1, 1);
		WeakSpot13.setRotationPoint(0F, -17F, 6F);
		WeakSpot13.setTextureSize(256, 256);
		WeakSpot13.mirror = true;
		setRotation(WeakSpot13, 0F, 0F, 0F);
		WeakSpot14 = new ModelRenderer(this, 7, 166);
		WeakSpot14.addBox(-0.5F, -0.5F, 1.8F, 1, 1, 1);
		WeakSpot14.setRotationPoint(0F, -17F, 6F);
		WeakSpot14.setTextureSize(256, 256);
		WeakSpot14.mirror = true;
		setRotation(WeakSpot14, 0.7853982F, 0F, 0F);
		WeakSpot15 = new ModelRenderer(this, 1, 166);
		WeakSpot15.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 1);
		WeakSpot15.setRotationPoint(0F, -17F, 6F);
		WeakSpot15.setTextureSize(256, 256);
		WeakSpot15.mirror = true;
		setRotation(WeakSpot15, 0.7853982F, 0F, 0F);
		WeakSpot17 = new ModelRenderer(this, 1, 170);
		WeakSpot17.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 1);
		WeakSpot17.setRotationPoint(0F, -17F, 6F);
		WeakSpot17.setTextureSize(256, 256);
		WeakSpot17.mirror = true;
		setRotation(WeakSpot17, -0.7853982F, 0F, 0F);
		WeakSpot18 = new ModelRenderer(this, 7, 170);
		WeakSpot18.addBox(-0.5F, -0.5F, 1.8F, 1, 1, 1);
		WeakSpot18.setRotationPoint(0F, -17F, 6F);
		WeakSpot18.setTextureSize(256, 256);
		WeakSpot18.mirror = true;
		setRotation(WeakSpot18, -0.7853982F, 0F, 0F);
		WeakSpot19 = new ModelRenderer(this, 47, 85);
		WeakSpot19.addBox(-2.8F, -1.5F, -3.8F, 2, 2, 1);
		WeakSpot19.setRotationPoint(0F, -24F, 1F);
		WeakSpot19.setTextureSize(256, 256);
		WeakSpot19.mirror = true;
		setRotation(WeakSpot19, 0F, 0F, 0F);
		WeakSpot20 = new ModelRenderer(this, 54, 85);
		WeakSpot20.addBox(0.8F, -1.5F, -3.8F, 2, 2, 1);
		WeakSpot20.setRotationPoint(0F, -24F, 1F);
		WeakSpot20.setTextureSize(256, 256);
		WeakSpot20.mirror = true;
		setRotation(WeakSpot20, 0F, 0F, 0F);
		LeftShoulder1 = new ModelRenderer(this, 87, 0);
		LeftShoulder1.addBox(-9F, -4F, -5F, 10, 10, 10);
		LeftShoulder1.setRotationPoint(-10F, -18F, 2F);
		LeftShoulder1.setTextureSize(256, 256);
		LeftShoulder1.mirror = true;
		setRotation(LeftShoulder1, 0F, 0F, 0.2617994F);

		RightShoulder1 = new ModelRenderer(this, 128, 0);
		RightShoulder1.addBox(-1F, -4F, -5F, 10, 10, 10);
		RightShoulder1.setRotationPoint(10F, -18F, 2F);
		RightShoulder1.setTextureSize(256, 256);
		RightShoulder1.mirror = true;
		setRotation(RightShoulder1, 0F, 0F, -0.2617994F);


		LeftShoulder2 = new ModelRenderer(this, 95, 22);
		LeftShoulder2.addBox(-4.5F, 0F, -4.5F, 9, 3, 9);
		LeftShoulder2.setRotationPoint(-15F, -13F, 2F);
		LeftShoulder2.setTextureSize(256, 256);
		LeftShoulder2.mirror = true;
		setRotation(LeftShoulder2, 0F, 0F, 0.2617994F);


		RightShoulder2 = new ModelRenderer(this, 131, 22);
		RightShoulder2.addBox(-0.5F, 6F, -4.5F, 9, 3, 9);
		RightShoulder2.setRotationPoint(10F, -18F, 2F);
		RightShoulder2.setTextureSize(256, 256);
		RightShoulder2.mirror = true;
		setRotation(RightShoulder2, 0F, 0F, -0.2617994F);


		GoldLeftShoulder3 = new ModelRenderer(this, 95, 35);
		GoldLeftShoulder3.addBox(-4.5F, 0, -3.5F, 7, 4, 7);
		GoldLeftShoulder3.setRotationPoint(-15F, -10F, 2F);
		GoldLeftShoulder3.setTextureSize(256, 256);
		GoldLeftShoulder3.mirror = true;
		setRotation(GoldLeftShoulder3, 0F, 0F, 0.2617994F);



		Unknown2 = new ModelRenderer(this, 95, 35);
		Unknown2.addBox(0.5F, 9F, -3.5F, 7, 5, 7);
		Unknown2.setRotationPoint(10F, -18F, 2F);
		Unknown2.setTextureSize(256, 256);
		Unknown2.mirror = true;
		setRotation(Unknown2, 0F, 0F, -0.2617994F);

		LeftShoulder3 = new ModelRenderer(this, 95, 48);
		LeftShoulder3.addBox(-4.5F, 0F, -4.5F, 9, 13, 9);
		LeftShoulder3.setRotationPoint(-17F, -8F, 2F);
		LeftShoulder3.setTextureSize(256, 256);
		LeftShoulder3.mirror = true;
		setRotation(LeftShoulder3, 0F, 0F, 0.0872665F);



		RightShoulder4 = new ModelRenderer(this, 95, 48);
		RightShoulder4.addBox(-5.5F, 0F, -4.5F, 9, 13, 9);
		RightShoulder4.setRotationPoint(17F, -7F, 2F);
		RightShoulder4.setTextureSize(256, 256);
		RightShoulder4.mirror = true;
		setRotation(RightShoulder4, 0F, 0F, -0.0872665F);



		rightHand1 = new ModelRenderer(this, 177, 49);
		rightHand1.addBox(-4F, -1F, -1F, 5, 2, 2);
		rightHand1.setRotationPoint(20.4F, 10.4F, -0.5F);
		rightHand1.setTextureSize(256, 256);
		rightHand1.mirror = true;
		setRotation(rightHand1, 0F, 0F, 0.2094395F);
		RightFinger1 = new ModelRenderer(this, 177, 53);
		RightFinger1.addBox(-4F, -1F, -1F, 5, 2, 2);
		RightFinger1.setRotationPoint(20.4F, 10.4F, 4.5F);
		RightFinger1.setTextureSize(256, 256);
		RightFinger1.mirror = true;
		setRotation(RightFinger1, 0F, 0F, 0.2094395F);
		RightFinger2 = new ModelRenderer(this, 177, 45);
		RightFinger2.addBox(-4F, -1F, -1F, 5, 2, 2);
		RightFinger2.setRotationPoint(20.4F, 10.4F, 2F);
		RightFinger2.setTextureSize(256, 256);
		RightFinger2.mirror = true;
		setRotation(RightFinger2, 0F, 0F, 0.2094395F);
		RightThumb = new ModelRenderer(this, 177, 58);
		RightThumb.addBox(-3F, -1F, -1F, 4, 2, 2);
		RightThumb.setRotationPoint(15.6F, 7.9F, -0.5F);
		RightThumb.setTextureSize(256, 256);
		RightThumb.mirror = true;
		setRotation(RightThumb, 0F, 0F, -1.570796F);
		RightWrist = new ModelRenderer(this, 131, 58);
		RightWrist.addBox(-3.5F, 0.2F, -3.5F, 7, 3, 7);
		RightWrist.setRotationPoint(18.5F, 4F, 2F);
		RightWrist.setTextureSize(256, 256);
		RightWrist.mirror = true;
		setRotation(RightWrist, 0F, 0F, 0.0872665F);
		RightHand2 = new ModelRenderer(this, 159, 58);
		RightHand2.addBox(1.5F, 3.2F, -3.5F, 2, 2, 7);
		RightHand2.setRotationPoint(18.5F, 4F, 2F);
		RightHand2.setTextureSize(256, 256);
		RightHand2.mirror = true;
		setRotation(RightHand2, 0F, 0F, 0.0872665F);
		LeftWrist = new ModelRenderer(this, 131, 47);
		LeftWrist.addBox(-3.5F, 0.2F, -3.5F, 7, 3, 7);
		LeftWrist.setRotationPoint(-18.5F, 4F, 2F);
		LeftWrist.setTextureSize(256, 256);
		LeftWrist.mirror = true;
		setRotation(LeftWrist, 0F, 0F, -0.0872665F);
		LeftHand1 = new ModelRenderer(this, 159, 48);
		LeftHand1.addBox(-3.5F, 3.2F, -3.5F, 2, 2, 7);
		LeftHand1.setRotationPoint(-18.5F, 4F, 2F);
		LeftHand1.setTextureSize(256, 256);
		LeftHand1.mirror = true;
		setRotation(LeftHand1, 0F, 0F, -0.0872665F);
		LeftFinger1 = new ModelRenderer(this, 191, 49);
		LeftFinger1.addBox(-4F, -1F, -1F, 5, 2, 2);
		LeftFinger1.setRotationPoint(-20.4F, 10.4F, -0.5F);
		LeftFinger1.setTextureSize(256, 256);
		LeftFinger1.mirror = true;
		setRotation(LeftFinger1, 0F, 3.141593F, 0.2094395F);
		LeftFinger2 = new ModelRenderer(this, 191, 45);
		LeftFinger2.addBox(-4F, -1F, -1F, 5, 2, 2);
		LeftFinger2.setRotationPoint(-20.4F, 10.4F, 4.5F);
		LeftFinger2.setTextureSize(256, 256);
		LeftFinger2.mirror = true;
		setRotation(LeftFinger2, 0F, 3.141593F, 0.2094395F);
		LeftFinger3 = new ModelRenderer(this, 191, 53);
		LeftFinger3.addBox(-4F, -1F, -1F, 5, 2, 2);
		LeftFinger3.setRotationPoint(-20.4F, 10.4F, 2F);
		LeftFinger3.setTextureSize(256, 256);
		LeftFinger3.mirror = true;
		setRotation(LeftFinger3, 0F, 3.141593F, 0.2094395F);
		LeftThumb = new ModelRenderer(this, 193, 58);
		LeftThumb.addBox(-3F, -1F, -1F, 4, 2, 2);
		LeftThumb.setRotationPoint(-15.6F, 7.9F, -0.5F);
		LeftThumb.setTextureSize(256, 256);
		LeftThumb.mirror = true;
		setRotation(LeftThumb, 0F, 0F, -1.570796F);
		RightEye = new ModelRenderer(this, 54, 82);
		RightEye.addBox(0.8F, -0.7F, -4F, 2, 1, 1);
		RightEye.setRotationPoint(0F, -24F, 1F);
		RightEye.setTextureSize(256, 256);
		RightEye.mirror = true;
		setRotation(RightEye, 0F, 0F, 0F);
		LeftEye = new ModelRenderer(this, 48, 82);
		LeftEye.addBox(-3F, -0.7F, -4F, 2, 1, 1);
		LeftEye.setRotationPoint(0F, -24F, 1F);
		LeftEye.setTextureSize(256, 256);
		LeftEye.mirror = true;
		setRotation(LeftEye, 0F, 0F, 0F);

		//left arm
	
		
		convertToChild(LeftHand1, LeftFinger1);
		convertToChild(LeftHand1, LeftFinger2);
		convertToChild(LeftHand1, LeftFinger3);
		convertToChild(LeftWrist, LeftThumb);
		convertToChild(LeftWrist, LeftHand1);
		convertToChild(LeftShoulder3, LeftWrist);
		convertToChild(GoldLeftShoulder3, LeftShoulder3);
		convertToChild(LeftShoulder2, GoldLeftShoulder3);
		convertToChild(LeftShoulder1, LeftShoulder2);
		convertToChild(UpperChest, LeftShoulder1);

		//right arm
		
		convertToChild(RightHand2, RightFinger1);
		convertToChild(RightHand2, RightFinger2);
		convertToChild(RightHand2, rightHand1);
		
		//this.hammer.attachShaftTo(RightWrist);
		convertToChild(RightWrist, RightThumb);
		convertToChild(RightWrist, RightHand2);
		convertToChild(RightShoulder4, RightWrist);
		convertToChild(Unknown2, RightShoulder4);
		convertToChild(RightShoulder2, Unknown2);
		convertToChild(RightShoulder1, RightShoulder2);
		convertToChild(UpperChest, RightShoulder1);

		//head
		convertToChild(Head1, Head2);
		convertToChild(Head1, Head3);
		convertToChild(Head1, Head4);
		convertToChild(Head1, Head5);
		convertToChild(Head1, Head6);
		convertToChild(Head1, Head7);
		convertToChild(Head1, Head8);
		convertToChild(Head1, Head9);

		convertToChild(Head1, WeakSpot19);
		convertToChild(Head1, WeakSpot20);
		convertToChild(Head1, LeftEye);
		convertToChild(Head1, RightEye);
		convertToChild(Head1, Neck);

		convertToChild(UpperChest, Head1);

		//legmiddlething	
		convertToChild(LegMiddle1, LegMiddle2);
		convertToChild(LegMiddle1, LegMiddle3);
		convertToChild(LegMiddle1, LegMiddleThing);

		//backPieceLeg
		convertToChild(BackPieceLeg, smallCube2);
		convertToChild(BackPieceLeg, smallCube);
		convertToChild(BackPieceLeg, BackMiddleThing);

		//left belt

		convertToChild(LeftShin, LeftBoot);
		convertToChild(LeftShin, WeakSpotLeg2);

		convertToChild(LeftPieceThing, LeftShin);
		convertToChild(LeftLegArmor, LeftPieceThing);

		convertToChild(LeftBelt, BackPieceLeg);
		convertToChild(LeftBelt, LeftLegArmor);

		//rightMiddlePiece (really right belt)

		convertToChild(RightShin, rightBoot);
		convertToChild(RightShin, WeakSpotLeg3);
		convertToChild(RightPieceThing, RightShin);
		convertToChild(RightLegArmor, RightPieceThing);
		convertToChild(rightMiddlePiece, LegMiddle1);
		convertToChild(rightMiddlePiece,RightLegArmor);

		//chest
		convertToChild(UpperChest, WeakSpot1);
		convertToChild(UpperChest, WeakSpot2);
		convertToChild(UpperChest, WeakSpot3);
		convertToChild(UpperChest, WeakSpot4);
		convertToChild(UpperChest, WeakSpot5);
		convertToChild(UpperChest, WeakSpot6);
		convertToChild(UpperChest, WeakSpot7);
		convertToChild(UpperChest, WeakSpot8);
		convertToChild(UpperChest, WeakSpot9);
		convertToChild(UpperChest, WeakSpot10);
		convertToChild(UpperChest, WeakSpot11);
		convertToChild(UpperChest, WeakSpot12);
		convertToChild(UpperChest, WeakSpot13);
		convertToChild(UpperChest, WeakSpot14);
		convertToChild(UpperChest, WeakSpot15);
		convertToChild(UpperChest, Unknown);
		convertToChild(UpperChest, WeakSpot17);
		convertToChild(UpperChest, WeakSpot18);


		convertToChild(UpperChest, UpperChest1);
		convertToChild(UpperChest, UpperChest2);
		convertToChild(UpperChest, ChestOne);
		convertToChild(UpperChest, ChestTwo);
		convertToChild(MiddlePiece, UpperChest);	


	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			
		/*
		 * 	kneeling down
		movePiece(LeftShoulder3, 0.5f, -40, 0, 0);  //-20
		movePiece(LeftShoulder1, 0.5f, -45, 0, 0);  //-40
		
		movePiece(RightShoulder4, 0.5f, -45, 0, 0);  //-20
		movePiece(RightShoulder1, 0.5f, -0, 0, 0);  //-40

		
		movePiece(hammer.Shaft, 2f, 270, 0, 0);	
		movePiece(MiddlePiece, 0.5f, 0, 0, 0);	
		
		
		movePiece(LeftShin, 0.5f, 50, 0, 0);
		movePiece(RightShin, 0.5f, 80, 0, 0);
		
		
		movePiece(LeftLegArmor, 0.5f, 20, 0, 0);	
		movePiece(RightLegArmor, 0.5f, -80, 0, 0);	
		
		this.MiddlePiece.offsetY = 0.6f;
		this.LeftBelt.offsetY = 0.6f;
		this.rightMiddlePiece.offsetY = 0.6f;
		 */
		
		
		MiddlePiece.render(f5);
		LeftBelt.render(f5);
		rightMiddlePiece.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		// TODO Auto-generated method stub
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

		float  f = 1f;
		this.RightShin.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.LeftShin.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;

		EntityAutomatonsRook rook = (EntityAutomatonsRook) entityIn;

		if(rook.SlamAttack){
			this.slamAnimation(rook);
		}
		else if(rook.PunchMode){
			this.punchAnimation(rook);
		}else if(rook.KickMode)
		{
			this.kickAnimation(rook);
		}else
		{
			this.idolAnimation(rook);
		}
	}
	private void kickAnimation(EntityAutomatonsRook rook)
	{

		this.LeftShoulder1.rotateAngleX = degToRad(0);
		this.RightShoulder1.rotateAngleX = degToRad(0);  
		this.MiddlePiece.rotateAngleX = degToRad(KickAnimation[rook.getAnimationCycle()][0]);

		this.LeftBelt.rotateAngleX = degToRad(0);
		this.rightMiddlePiece.rotateAngleX = degToRad(KickAnimation[rook.getAnimationCycle()][1]);

		this.LeftShoulder3.rotateAngleX = degToRad(0);
		this.RightShoulder4.rotateAngleX = degToRad(0);

		this.MiddlePiece.rotateAngleY = degToRad(0);
		this.rightMiddlePiece.rotateAngleY = degToRad(0);
		this.LeftBelt.rotateAngleY = degToRad(0);

		this.LeftShin.rotateAngleX = degToRad(0);
		this.RightShin.rotateAngleX = degToRad(KickAnimation[rook.getAnimationCycle()][2]);


	}
	private void slamAnimation(EntityAutomatonsRook rook)
	{
		this.LeftShoulder1.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][1]);
		this.RightShoulder1.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][1]);  
		this.MiddlePiece.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][2]);

		this.LeftBelt.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][3]);
		this.rightMiddlePiece.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][3]);

		this.LeftShoulder3.rotateAngleX = degToRad(0);
		this.RightShoulder4.rotateAngleX = degToRad(0);

		this.MiddlePiece.rotateAngleY = degToRad(0);
		this.rightMiddlePiece.rotateAngleY = degToRad(0);
		this.LeftBelt.rotateAngleY = degToRad(0);

		this.LeftShin.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][4]);
		this.RightShin.rotateAngleX = degToRad(SlamCycle[rook.getAnimationCycle()][4]);

		this.MiddlePiece.offsetY = (float) SlamCycle[rook.getAnimationCycle()][5];
		this.LeftBelt.offsetY= (float) SlamCycle[rook.getAnimationCycle()][5];
		this.rightMiddlePiece.offsetY = (float) SlamCycle[rook.getAnimationCycle()][5];
	}

	private void punchAnimation(EntityAutomatonsRook rook)
	{
		this.LeftShoulder3.rotateAngleX = degToRad(LeftPunchCycle[rook.getAnimationCycle()][5]);
		this.RightShoulder4.rotateAngleX = degToRad(LeftPunchCycle[rook.getAnimationCycle()][0]);

		this.LeftShoulder1.rotateAngleX = degToRad(LeftPunchCycle[rook.getAnimationCycle()][1]);  
		this.RightShoulder1.rotateAngleX = degToRad(LeftPunchCycle[rook.getAnimationCycle()][1]);  

		this.MiddlePiece.rotateAngleX = degToRad(LeftPunchCycle[rook.getAnimationCycle()][2]);
		this.MiddlePiece.rotateAngleY = degToRad(LeftPunchCycle[rook.getAnimationCycle()][3]);

		this.rightMiddlePiece.rotateAngleY = degToRad(LeftPunchCycle[rook.getAnimationCycle()][4]);
		this.LeftBelt.rotateAngleY = degToRad(LeftPunchCycle[rook.getAnimationCycle()][4]);

	}

	private void idolAnimation(EntityAutomatonsRook rook)
	{
		this.LeftShoulder1.rotateAngleX = degToRad(0);
		this.RightShoulder1.rotateAngleX = degToRad(0);  
		this.MiddlePiece.rotateAngleX = degToRad(0);

		this.LeftBelt.rotateAngleX = degToRad(0);
		this.rightMiddlePiece.rotateAngleX = degToRad(0);

		this.LeftShoulder3.rotateAngleX = degToRad(0);
		this.RightShoulder4.rotateAngleX = degToRad(0);

		this.MiddlePiece.rotateAngleY = degToRad(0);
		this.rightMiddlePiece.rotateAngleY = degToRad(0);
		this.LeftBelt.rotateAngleY = degToRad(0);

		this.MiddlePiece.offsetY = (float) 0;
		this.LeftBelt.offsetY= (float) 0;
		this.rightMiddlePiece.offsetY = 0;
	}
}
