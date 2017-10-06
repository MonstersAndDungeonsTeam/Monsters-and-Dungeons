package monstersanddungeons.client.models;

import monstersanddungeons.entity.marshdwellers.EntityMarshDweller;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;



public class ModelMarshDwellerFisherman extends MaDEntityModelBase
{
	ModelRenderer belly;
	ModelRenderer MiddleUpperChest;
	ModelRenderer leftBoob;
	ModelRenderer rightBoob;
	ModelRenderer uppperChest;
	ModelRenderer mouth;
	ModelRenderer head;
	ModelRenderer rightKnee;
	ModelRenderer leftKnee;
	ModelRenderer rightLeg;
	ModelRenderer leftLeg;
	ModelRenderer leftEar;
	ModelRenderer rightEar;
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
	ModelRenderer pole;
	ModelRenderer upperPole;
	ModelRenderer lowerPole;
	ModelRenderer middleFish;
	ModelRenderer lowerFish;
	ModelRenderer lowerFish1;
	ModelRenderer upperFish;
	ModelRenderer upperFish1;
	ModelRenderer upperFish2;
	ModelRenderer upperFish3;
	ModelRenderer tail;
	ModelRenderer fin;
	ModelRenderer fin1;
	ModelRenderer fin2;
	ModelRenderer fin3;
	ModelRenderer fish2;
	ModelRenderer bodyfish2;
	ModelRenderer bodyFish1;
	ModelRenderer fish2Eye;
	ModelRenderer fish2Eye1;
	ModelRenderer fish2Body;
	ModelRenderer fish2Body1;
	ModelRenderer fish2Body2;
	ModelRenderer basket;
	ModelRenderer basket1;
	ModelRenderer basket2;
	ModelRenderer basket3;
	ModelRenderer basket4;
	ModelRenderer basket5;
	ModelRenderer basket6;
	ModelRenderer legCover;
	ModelRenderer legCover1;
	ModelRenderer leftToe;
	ModelRenderer leftToe1;
	ModelRenderer leftToe2;
	ModelRenderer rightToe;
	ModelRenderer rightToe1;
	ModelRenderer rightToe2;
	ModelRenderer harpoon;
	ModelRenderer legCover2;
	ModelRenderer legCover3;
	ModelRenderer legCover4;
	ModelRenderer legCover5;
	ModelRenderer legCover6;
	ModelRenderer legCover7;
	ModelRenderer legCover8;
	ModelRenderer legCover9;
	ModelRenderer legCover10;
	ModelRenderer legCover11;

	public ModelMarshDwellerFisherman()
	{
		textureWidth = 128;
		textureHeight = 128;

		belly = new ModelRenderer(this, 0, 0);
		belly.addBox(-8F, -6F, -8F, 16, 12, 15);
		belly.setRotationPoint(0F, 6F, 0F);
		belly.setTextureSize(128, 128);
		belly.mirror = true;
		setRotation(belly, -0.1745329F, 0F, 0F);


		MiddleUpperChest = new ModelRenderer(this, 84, 59);
		MiddleUpperChest.addBox(-6.5F, -5F, -8.5F, 13, 3, 2);
		MiddleUpperChest.setRotationPoint(0F, 6F, 0F);
		MiddleUpperChest.setTextureSize(128, 128);
		MiddleUpperChest.mirror = true;
		setRotation(MiddleUpperChest, -0.6108652F, 0F, 0F);

		leftBoob = new ModelRenderer(this, 64, 22);
		leftBoob.addBox(-7.3F, -12F, -7F, 7, 5, 1);
		leftBoob.setRotationPoint(0F, 6F, 0F);
		leftBoob.setTextureSize(128, 128);
		leftBoob.mirror = true;
		setRotation(leftBoob, -0.1919862F, 0F, 0.0174533F);

		rightBoob = new ModelRenderer(this, 90, 22);
		rightBoob.addBox(0.3F, -12F, -7F, 7, 5, 1);
		rightBoob.setRotationPoint(0F, 6F, 0F);
		rightBoob.setTextureSize(128, 128);
		rightBoob.mirror = true;
		setRotation(rightBoob, -0.1919862F, 0F, -0.0174533F);

		uppperChest = new ModelRenderer(this, 64, 0);
		uppperChest.addBox(-7F, -14F, -4.5F, 14, 10, 11);
		uppperChest.setRotationPoint(0F, 6F, 0F);
		uppperChest.setTextureSize(128, 128);
		uppperChest.mirror = true;
		setRotation(uppperChest, 0F, 0F, 0F);

		mouth = new ModelRenderer(this, 38, 28);
		mouth.addBox(-5F, 0F, -8F, 10, 7, 8);
		mouth.setRotationPoint(0F, -12F, 2F);
		mouth.setTextureSize(128, 128);
		mouth.mirror = true;
		setRotation(mouth, -0.1745329F, 0F, 0F);



		head = new ModelRenderer(this, 0, 28);
		head.addBox(-4.5F, -6F, -7F, 9, 8, 10);
		head.setRotationPoint(0F, -9F, 2F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, -0.0523599F, 0F, 0F);


		rightKnee = new ModelRenderer(this, 0, 46);
		rightKnee.addBox(-3F, -1F, -5.5F, 6, 6, 9);
		rightKnee.setRotationPoint(4F, 12F, 0F);
		rightKnee.setTextureSize(128, 128);
		rightKnee.mirror = true;
		setRotation(rightKnee, -0.2617994F, 0F, -0.0872665F);

		leftKnee = new ModelRenderer(this, 29, 46);
		leftKnee.addBox(-3F, -1F, -5.5F, 6, 6, 9);
		leftKnee.setRotationPoint(-4F, 12F, 0F);
		leftKnee.setTextureSize(128, 128);
		leftKnee.mirror = true;
		setRotation(leftKnee, -0.2617994F, 0F, 0.0872665F);

		rightLeg = new ModelRenderer(this, 28, 62);
		rightLeg.addBox(-3.7F, -1F, -3.5F, 7, 9, 7);
		rightLeg.setRotationPoint(4.5F, 16F, 1F);
		rightLeg.setTextureSize(128, 128);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0F, -0.0872665F, 0F);

		leftLeg = new ModelRenderer(this, 0, 62);
		leftLeg.addBox(-3.3F, -1F, -3.5F, 7, 9, 7);
		leftLeg.setRotationPoint(-4.5F, 16F, 1F);
		leftLeg.setTextureSize(128, 128);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0F, 0.0872665F, 0F);

		leftEar = new ModelRenderer(this, 80, 22);
		leftEar.addBox(-6.5F, -5.5F, -4.5F, 2, 3, 3);
		leftEar.setRotationPoint(0F, -9F, 2F);
		leftEar.setTextureSize(128, 128);
		leftEar.mirror = true;
		setRotation(leftEar, 0F, -0.1745329F, 0F);

		rightEar = new ModelRenderer(this, 106, 22);
		rightEar.addBox(4.5F, -5.5F, -4.5F, 2, 3, 3);
		rightEar.setRotationPoint(0F, -9F, 2F);
		rightEar.setTextureSize(128, 128);
		rightEar.mirror = true;
		setRotation(rightEar, 0F, 0.1745329F, 0F);

		rightShoulder = new ModelRenderer(this, 96, 28);
		rightShoulder.addBox(-1F, -3F, -3F, 5, 10, 6);
		rightShoulder.setRotationPoint(8F, -4F, 1.5F);
		rightShoulder.setTextureSize(128, 128);
		rightShoulder.mirror = true;
		setRotation(rightShoulder, 0.0872665F, 0F, -0.1745329F);

		leftShoulder = new ModelRenderer(this, 74, 28);
		leftShoulder.addBox(-4F, -3F, -3F, 5, 10, 6);
		leftShoulder.setRotationPoint(-8F, -4F, 1.5F);
		leftShoulder.setTextureSize(128, 128);
		leftShoulder.mirror = true;
		setRotation(leftShoulder, 0F, 0F, 0.1745329F);

		rightHand = new ModelRenderer(this, 86, 44);
		rightHand.addBox(-3F, 0F, -3.5F, 6, 8, 7);
		rightHand.setRotationPoint(11F, 2F, 2F);
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
		rightFinger.addBox(-1F, -1F, -1F, 4, 2, 2);
		rightFinger.setRotationPoint(13F, 11F, 3.7F);
		rightFinger.setTextureSize(128, 128);
		rightFinger.mirror = true;
		setRotation(rightFinger, 0F, 3.141593F, 0.2617994F);

		rightFinger1 = new ModelRenderer(this, 72, 63);
		rightFinger1.addBox(-1F, -1F, -1F, 4, 2, 2);
		rightFinger1.setRotationPoint(13F, 11F, 1.5F);
		rightFinger1.setTextureSize(128, 128);
		rightFinger1.mirror = true;
		setRotation(rightFinger1, 0F, 3.141593F, 0.2617994F);

		rightFinger2 = new ModelRenderer(this, 72, 67);
		rightFinger2.addBox(-1F, -1F, -1F, 4, 2, 2);
		rightFinger2.setRotationPoint(13F, 11F, -0.7F);
		rightFinger2.setTextureSize(128, 128);
		rightFinger2.mirror = true;
		setRotation(rightFinger2, 0F, -3.141593F, 0.2617994F);

		rightFinger3 = new ModelRenderer(this, 72, 71);
		rightFinger3.addBox(-1F, -1F, -1F, 2, 2, 4);
		rightFinger3.setRotationPoint(9F, 11F, 0.7F);
		rightFinger3.setTextureSize(128, 128);
		rightFinger3.mirror = true;
		setRotation(rightFinger3, -0.2617994F, 0F, 0F);

		pole = new ModelRenderer(this, 116, 0);
		pole.addBox(-1F, -24F, -1F, 2, 24, 2);
		pole.setRotationPoint(11F, 10F, 0F);
		pole.setTextureSize(128, 128);
		pole.mirror = true;
		setRotation(pole, 1.570796F, 0F, 0F);
		
		upperPole = new ModelRenderer(this, 124, 0);
		upperPole.addBox(-0.5F, -37F, -0.5F, 1, 18, 1);
		upperPole.setRotationPoint(11F, 9.4F, -0.5F);
		upperPole.setTextureSize(128, 128);
		upperPole.mirror = true;
		setRotation(upperPole, 1.570796F, 0F, 0F);
		
		lowerPole = new ModelRenderer(this, 124, 19);
		lowerPole.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		lowerPole.setRotationPoint(11F, 10F, 0F);
		lowerPole.setTextureSize(128, 128);
		lowerPole.mirror = true;
		setRotation(lowerPole, 0F, 0F, 0F);

		middleFish = new ModelRenderer(this, 0, 96);
		middleFish.addBox(-2.5F, -1F, -3F, 5, 2, 6);
		middleFish.setRotationPoint(4F, -28F, 6F);
		middleFish.setTextureSize(128, 128);
		middleFish.mirror = true;
		setRotation(middleFish, -0.2617994F, 0F, -0.4363323F);
		lowerFish = new ModelRenderer(this, 0, 104);
		lowerFish.addBox(-2F, -1.7F, 2F, 4, 1, 5);
		lowerFish.setRotationPoint(4F, -28F, 6F);
		lowerFish.setTextureSize(128, 128);
		lowerFish.mirror = true;
		setRotation(lowerFish, -0.5759587F, 0F, -0.4363323F);
		lowerFish1 = new ModelRenderer(this, 0, 104);
		lowerFish1.addBox(-2F, -1F, 2F, 4, 1, 5);
		lowerFish1.setRotationPoint(4F, -28F, 6F);
		lowerFish1.setTextureSize(128, 128);
		lowerFish1.mirror = true;
		setRotation(lowerFish1, -0.5759587F, 0F, -0.4363323F);
		upperFish = new ModelRenderer(this, 0, 110);
		upperFish.addBox(1.5F, -1F, -4F, 3, 2, 4);
		upperFish.setRotationPoint(4F, -28F, 6F);
		upperFish.setTextureSize(128, 128);
		upperFish.mirror = true;
		setRotation(upperFish, 0.1919862F, 0.9424778F, -0.4363323F);
		upperFish1 = new ModelRenderer(this, 0, 110);
		upperFish1.addBox(1.5F, -1.3F, -4F, 3, 2, 4);
		upperFish1.setRotationPoint(4F, -28F, 6F);
		upperFish1.setTextureSize(128, 128);
		upperFish1.mirror = true;
		setRotation(upperFish1, 0.1919862F, 0.9424778F, -0.4363323F);
		upperFish2 = new ModelRenderer(this, 14, 110);
		upperFish2.addBox(1.5F, -0.3F, -4F, 3, 1, 2);
		upperFish2.setRotationPoint(4F, -28F, 6F);
		upperFish2.setTextureSize(128, 128);
		upperFish2.mirror = true;
		setRotation(upperFish2, 0.1919862F, 1.22173F, -0.4363323F);
		upperFish3 = new ModelRenderer(this, 14, 110);
		upperFish3.addBox(1.5F, 0.2F, -4F, 3, 1, 2);
		upperFish3.setRotationPoint(4F, -28F, 6F);
		upperFish3.setTextureSize(128, 128);
		upperFish3.mirror = true;
		setRotation(upperFish3, 0.1919862F, 1.22173F, -0.4363323F);
		tail = new ModelRenderer(this, 18, 96);
		tail.addBox(-2F, -2.2F, 6.5F, 4, 0, 4);
		tail.setRotationPoint(4F, -28F, 6F);
		tail.setTextureSize(128, 128);
		tail.mirror = true;
		setRotation(tail, -0.7679449F, 0F, -0.4363323F);
		fin = new ModelRenderer(this, 19, 100);
		fin.addBox(2F, 0F, -1F, 1, 0, 3);
		fin.setRotationPoint(4F, -28F, 6F);
		fin.setTextureSize(128, 128);
		fin.mirror = true;
		setRotation(fin, -0.2617994F, 0.2617994F, -0.4363323F);
		fin1 = new ModelRenderer(this, 19, 103);
		fin1.addBox(-3F, 0F, -1F, 1, 0, 4);
		fin1.setRotationPoint(4F, -28F, 6F);
		fin1.setTextureSize(128, 128);
		fin1.mirror = true;
		setRotation(fin1, -0.2617994F, -0.2268928F, -0.4363323F);
		fin2 = new ModelRenderer(this, 19, 107);
		fin2.addBox(-1F, 1F, 0F, 2, 0, 2);
		fin2.setRotationPoint(4F, -28F, 6F);
		fin2.setTextureSize(128, 128);
		fin2.mirror = true;
		setRotation(fin2, -0.7853982F, 0F, -0.4363323F);

		fin3 = new ModelRenderer(this, 19, 107);
		fin3.addBox(-1F, -1F, 0F, 2, 0, 2);
		fin3.setRotationPoint(4F, -28F, 6F);
		fin3.setTextureSize(128, 128);
		fin3.mirror = true;
		setRotation(fin3, 0.0872665F, 0F, -0.4363323F);

		fish2 = new ModelRenderer(this, 0, 116);
		fish2.addBox(-2F, -1F, -0.7F, 4, 2, 2);
		fish2.setRotationPoint(6F, -22F, 3F);
		fish2.setTextureSize(128, 128);
		fish2.mirror = true;
		setRotation(fish2, -0.3490659F, -0.3490659F, -0.2617994F);


		bodyfish2 = new ModelRenderer(this, 0, 120);
		bodyfish2.addBox(-4F, -1.5F, -0.3F, 3, 2, 1);
		bodyfish2.setRotationPoint(6F, -22F, 3F);
		bodyfish2.setTextureSize(128, 128);
		bodyfish2.mirror = true;
		setRotation(bodyfish2, -0.3490659F, -0.5235988F, -0.5585054F);


		bodyFish1 = new ModelRenderer(this, 0, 123);
		bodyFish1.addBox(-5.2F, -2F, 0.3F, 2, 1, 0);
		bodyFish1.setRotationPoint(6F, -22F, 3F);
		bodyFish1.setTextureSize(128, 128);
		bodyFish1.mirror = true;
		setRotation(bodyFish1, -0.2792527F, -0.5934119F, -0.8726646F);

		fish2Eye = new ModelRenderer(this, 0, 125);
		fish2Eye.addBox(0.5F, -0.5F, -0.8F, 1, 1, 1);
		fish2Eye.setRotationPoint(6F, -22F, 3F);
		fish2Eye.setTextureSize(128, 128);
		fish2Eye.mirror = true;
		setRotation(fish2Eye, -0.3490659F, -0.3490659F, -0.2617994F);

		fish2Eye1 = new ModelRenderer(this, 0, 125);
		fish2Eye1.addBox(0.5F, -0.5F, 0.4F, 1, 1, 1);
		fish2Eye1.setRotationPoint(6F, -22F, 3F);
		fish2Eye1.setTextureSize(128, 128);
		fish2Eye1.mirror = true;
		setRotation(fish2Eye1, -0.3490659F, -0.3490659F, -0.2617994F);

		fish2Body = new ModelRenderer(this, 14, 113);
		fish2Body.addBox(-2F, 0F, -0.7F, 2, 1, 0);
		fish2Body.setRotationPoint(6F, -22F, 3F);
		fish2Body.setTextureSize(128, 128);
		fish2Body.mirror = true;
		setRotation(fish2Body, -0.3490659F, -0.6108652F, -0.2617994F);

		fish2Body1 = new ModelRenderer(this, 14, 113);
		fish2Body1.addBox(-2F, 0F, 1.3F, 2, 1, 0);
		fish2Body1.setRotationPoint(6F, -22F, 3F);
		fish2Body1.setTextureSize(128, 128);
		fish2Body1.mirror = true;
		setRotation(fish2Body1, -0.3490659F, 0F, -0.5235988F);

		fish2Body2 = new ModelRenderer(this, 0, 123);
		fish2Body2.addBox(-5F, 1F, 1.3F, 2, 1, 0);
		fish2Body2.setRotationPoint(6F, -22F, 3F);
		fish2Body2.setTextureSize(128, 128);
		fish2Body2.mirror = true;
		setRotation(fish2Body2, -0.2443461F, -0.5934119F, 0.0872665F);

		basket = new ModelRenderer(this, 34, 97);
		basket.addBox(-4F, -11F, 1F, 8, 20, 1);
		basket.setRotationPoint(-5F, 0F, 6F);
		basket.setTextureSize(128, 128);
		basket.mirror = true;
		setRotation(basket, 0.122173F, 0F, -0.4363323F);

		basket1 = new ModelRenderer(this, 52, 97);
		basket1.addBox(-2F, -11F, 4.5F, 5, 20, 1);
		basket1.setRotationPoint(-5F, 0F, 6F);
		basket1.setTextureSize(128, 128);
		basket1.mirror = true;
		setRotation(basket1, 0.122173F, 0F, -0.4363323F);


		basket2 = new ModelRenderer(this, 64, 97);
		basket2.addBox(-1F, -11F, 4F, 4, 20, 1);
		basket2.setRotationPoint(-5F, 0F, 6F);
		basket2.setTextureSize(128, 128);
		basket2.mirror = true;
		setRotation(basket2, -0.3141593F, -1.047198F, -0.3490659F);


		basket3 = new ModelRenderer(this, 74, 97);
		basket3.addBox(-1F, -11.2F, -5F, 4, 20, 1);
		basket3.setRotationPoint(-5F, 0F, 6F);
		basket3.setTextureSize(128, 128);
		basket3.mirror = true;
		setRotation(basket3, -0.418879F, -2.024582F, 0.122173F);

		basket4 = new ModelRenderer(this, 34, 118);
		basket4.addBox(-4F, -9F, 1F, 8, 0, 4);
		basket4.setRotationPoint(-5F, 0F, 6F);
		basket4.setTextureSize(128, 128);
		basket4.mirror = true;
		setRotation(basket4, 0.122173F, 0F, -0.4363323F);

		basket5 = new ModelRenderer(this, 34, 122);
		basket5.addBox(-4F, 8.8F, 1F, 8, 0, 4);
		basket5.setRotationPoint(-5F, 0F, 6F);
		basket5.setTextureSize(128, 128);
		basket5.mirror = true;
		setRotation(basket5, 0.122173F, 0F, -0.4363323F);

		basket6 = new ModelRenderer(this, 54, 118);
		basket6.addBox(0F, -9F, -7F, 1, 2, 8);
		basket6.setRotationPoint(-5F, 0F, 6F);
		basket6.setTextureSize(128, 128);
		basket6.mirror = true;
		setRotation(basket6, 0.122173F, 0F, -0.3839724F);

		legCover = new ModelRenderer(this, 51, 78);
		legCover.addBox(-3F, 0F, -0.5F, 6, 6, 1);
		legCover.setRotationPoint(0F, 11F, -7F);
		legCover.setTextureSize(128, 128);
		legCover.mirror = true;
		setRotation(legCover, 0F, 0F, 0F);

		legCover1 = new ModelRenderer(this, 65, 78);
		legCover1.addBox(-2F, 6F, -0.5F, 4, 1, 1);
		legCover1.setRotationPoint(0F, 11F, -7F);
		legCover1.setTextureSize(128, 128);
		legCover1.mirror = true;
		setRotation(legCover1, 0F, 0F, 0F);

		leftToe = new ModelRenderer(this, 0, 78);
		leftToe.addBox(-3.3F, 6.5F, -6.5F, 2, 2, 5);
		leftToe.setRotationPoint(-4.5F, 16F, 1F);
		leftToe.setTextureSize(128, 128);
		leftToe.mirror = true;
		setRotation(leftToe, -0.0872665F, 0.1745329F, 0F);

		leftToe1 = new ModelRenderer(this, 14, 78);
		leftToe1.addBox(-0.7F, 6.5F, -6.5F, 2, 2, 5);
		leftToe1.setRotationPoint(-4.5F, 16F, 1F);
		leftToe1.setTextureSize(128, 128);
		leftToe1.mirror = true;
		setRotation(leftToe1, -0.0872665F, 0.0872665F, 0F);

		leftToe2 = new ModelRenderer(this, 28, 78);
		leftToe2.addBox(1.7F, 6.5F, -6.5F, 2, 2, 5);
		leftToe2.setRotationPoint(-4.5F, 16F, 1F);
		leftToe2.setTextureSize(128, 128);
		leftToe2.mirror = true;
		setRotation(leftToe2, -0.0872665F, 0F, 0F);

		rightToe = new ModelRenderer(this, 28, 85);
		rightToe.addBox(-3.7F, 6.5F, -6.5F, 2, 2, 5);
		rightToe.setRotationPoint(4.5F, 16F, 1F);
		rightToe.setTextureSize(128, 128);
		rightToe.mirror = true;
		setRotation(rightToe, -0.0872665F, 0F, 0F);

		rightToe1 = new ModelRenderer(this, 14, 85);
		rightToe1.addBox(-1.3F, 6.5F, -6.5F, 2, 2, 5);
		rightToe1.setRotationPoint(4.5F, 16F, 1F);
		rightToe1.setTextureSize(128, 128);
		rightToe1.mirror = true;
		setRotation(rightToe1, -0.0872665F, -0.0872665F, 0F);

		rightToe2 = new ModelRenderer(this, 0, 78);
		rightToe2.addBox(1.3F, 6.5F, -6.5F, 2, 2, 5);
		rightToe2.setRotationPoint(4.5F, 16F, 1F);
		rightToe2.setTextureSize(128, 128);
		rightToe2.mirror = true;
		setRotation(rightToe2, -0.0872665F, -0.1745329F, 0F);

		harpoon = new ModelRenderer(this, 124, 0);
		harpoon.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		harpoon.setRotationPoint(2.7F, -33F, 7.8F);
		harpoon.setTextureSize(128, 128);
		harpoon.mirror = true;
		setRotation(harpoon, -0.4363323F, 0.122173F, 0.0872665F);

		legCover2 = new ModelRenderer(this, 51, 95);
		legCover2.addBox(-7.5F, 5.5F, -7F, 15, 1, 1);
		legCover2.setRotationPoint(0F, 6F, 0F);
		legCover2.setTextureSize(128, 128);
		legCover2.mirror = true;
		setRotation(legCover2, -0.1745329F, 0F, 0F);
		legCover3 = new ModelRenderer(this, 51, 85);
		legCover3.addBox(-6F, 0F, 0F, 5, 5, 0);
		legCover3.setRotationPoint(0F, 11F, -7F);
		legCover3.setTextureSize(128, 128);
		legCover3.mirror = true;
		setRotation(legCover3, 0F, 0F, 0.0872665F);
		legCover4 = new ModelRenderer(this, 51, 90);
		legCover4.addBox(1F, 0F, 0F, 5, 5, 0);
		legCover4.setRotationPoint(0F, 11F, -7F);
		legCover4.setTextureSize(128, 128);
		legCover4.mirror = true;
		setRotation(legCover4, 0F, 0F, -0.0872665F);
		legCover5 = new ModelRenderer(this, 51, 95);
		legCover5.addBox(-7.5F, 5.5F, 5F, 15, 1, 1);
		legCover5.setRotationPoint(0F, 6F, 0F);
		legCover5.setTextureSize(128, 128);
		legCover5.mirror = true;
		setRotation(legCover5, -0.1745329F, 0F, 0F);
		legCover6 = new ModelRenderer(this, 61, 83);
		legCover6.addBox(6.5F, 5.5F, -6F, 1, 1, 11);
		legCover6.setRotationPoint(0F, 6F, 0F);
		legCover6.setTextureSize(128, 128);
		legCover6.mirror = true;
		setRotation(legCover6, -0.1745329F, 0F, 0F);
		legCover7 = new ModelRenderer(this, 61, 83);
		legCover7.addBox(-7.5F, 5.5F, -6F, 1, 1, 11);
		legCover7.setRotationPoint(0F, 6F, 0F);
		legCover7.setTextureSize(128, 128);
		legCover7.mirror = true;
		setRotation(legCover7, -0.1745329F, 0F, 0F);
		legCover8 = new ModelRenderer(this, 51, 78);
		legCover8.addBox(-3F, 0F, -0.5F, 6, 6, 1);
		legCover8.setRotationPoint(0F, 11F, 5F);
		legCover8.setTextureSize(128, 128);
		legCover8.mirror = true;
		setRotation(legCover8, 0F, 0F, 0F);
		legCover9 = new ModelRenderer(this, 65, 78);
		legCover9.addBox(-2F, 6F, -0.5F, 4, 1, 1);
		legCover9.setRotationPoint(0F, 11F, 5F);
		legCover9.setTextureSize(128, 128);
		legCover9.mirror = true;
		setRotation(legCover9, 0F, 0F, 0F);
		legCover10 = new ModelRenderer(this, 51, 85);
		legCover10.addBox(-6F, 0F, 0F, 5, 5, 0);
		legCover10.setRotationPoint(0F, 11F, 5F);
		legCover10.setTextureSize(128, 128);
		legCover10.mirror = true;
		setRotation(legCover10, 0F, 0F, 0.0872665F);
		legCover11 = new ModelRenderer(this, 51, 90);
		legCover11.addBox(1F, 0F, 0F, 5, 5, 0);
		legCover11.setRotationPoint(0F, 11F, 5F);
		legCover11.setTextureSize(128, 128);
		legCover11.mirror = true;
		setRotation(legCover11, 0F, 0F, -0.0872665F);

		convertToChild(head, leftEar);
		convertToChild(head, rightEar);
		convertToChild(head, mouth);

		
		convertToChild(pole, upperPole);
		convertToChild(pole, lowerPole);

		convertToChild(rightHand, pole);
		convertToChild(rightHand, rightFinger);
		convertToChild(rightHand, rightFinger1);
		convertToChild(rightHand, rightFinger2);
		convertToChild(rightHand, rightFinger3);
		convertToChild(rightShoulder, rightHand);

		convertToChild(leftHand, leftFinger);
		convertToChild(leftHand, leftFinger1);
		convertToChild(leftHand, leftFinger2);
		convertToChild(leftHand, leftFinger3);
		convertToChild(leftShoulder, leftHand);

		convertToChild(uppperChest, leftShoulder);
		convertToChild(uppperChest, rightShoulder);
		convertToChild(uppperChest, head);
		convertToChild(uppperChest, leftBoob);
		convertToChild(uppperChest, rightBoob);

		convertToChild(belly, legCover);
		convertToChild(belly, legCover1);
		convertToChild(belly, legCover2);
		convertToChild(belly, legCover3);
		convertToChild(belly, legCover4);
		convertToChild(belly, legCover5);
		convertToChild(belly, legCover6);
		convertToChild(belly, legCover7);
		convertToChild(belly, legCover8);
		convertToChild(belly, legCover9);
		convertToChild(belly, legCover10);
		convertToChild(belly, legCover11);
		convertToChild(belly, MiddleUpperChest);
		convertToChild(belly, uppperChest);

		convertToChild(rightLeg, rightToe);
		convertToChild(rightLeg, rightToe1);
		convertToChild(rightLeg, rightToe2);
		convertToChild(rightKnee, rightLeg);

		convertToChild(leftLeg, leftToe);
		convertToChild(leftLeg, leftToe1);
		convertToChild(leftLeg, leftToe2);
		convertToChild(leftKnee, leftLeg);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		leftKnee.render(f5);
		rightKnee.render(f5);
		belly.render(f5);

		movePiece(this.rightShoulder, 1f, 0f, 0f, 0f);
		movePiece(this.rightHand, 1f, -90f, 0f, 0f);
		movePiece(this.pole, 1f,  90f, 0f, 0f);
		
			/*
	    middleFish.render(f5);
	    lowerFish.render(f5);
	    lowerFish1.render(f5);
	    upperFish.render(f5);
	    upperFish1.render(f5);
	    upperFish2.render(f5);
	    upperFish3.render(f5);
	    tail.render(f5);
	    fin.render(f5);
	    fin1.render(f5);
	    fin2.render(f5);
	    fin3.render(f5);
	    fish2.render(f5);
	    bodyfish2.render(f5);
	    bodyFish1.render(f5);
	    fish2Eye.render(f5);
	    fish2Eye1.render(f5);
	    fish2Body.render(f5);
	    fish2Body1.render(f5);
	    fish2Body2.render(f5);
	    */
		 
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void sitDown()
	{
		movePiece(this.leftShoulder, 1f, -45f, 0f, 0f);

		movePiece(this.leftKnee, 1f, -90f, 10f, 0f);
		movePiece(this.rightKnee, 1f, -90f, -10f, 0f);
		movePiece(this.belly, 1f, 0f, 0f, 0f);

		moveoffSet(this.leftKnee, 0.01f, 0.4f);
		moveoffSet(this.rightKnee, 0.01f, 0.4f);
		moveoffSet(this.belly, 0.01f, 0.4f);
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

			this.rightKnee.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
			this.leftKnee.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;

			this.leftShoulder.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;

			moveoffSet(this.leftKnee, 0.01f, 0f);
			moveoffSet(this.rightKnee, 0.01f, 0f);
			moveoffSet(this.belly, 0.01f, 0f);
		}else
		{
			this.sitDown();
		}
	}

}
