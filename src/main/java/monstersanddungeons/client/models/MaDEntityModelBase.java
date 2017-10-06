package monstersanddungeons.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class MaDEntityModelBase extends ModelBase 
{
	/** This method moves the offset of the piece by the speed amount every tick until the offsetY value is reached	
	 * @param MiddlePiece
	 * @param speed
	 * @param offsetY
	 * @return
	 */
	public boolean moveoffSet(ModelRenderer MiddlePiece, float speed, float offsetY)
	{

		if(MiddlePiece.offsetY < offsetY)
		{
			if(MiddlePiece.offsetY + speed < offsetY)
			{
				MiddlePiece.offsetY += speed;
			}else
				MiddlePiece.offsetY = offsetY;
		}else
		{
			if(MiddlePiece.offsetY - speed > offsetY)
			{
				MiddlePiece.offsetY -= speed;
			}else
				MiddlePiece.offsetY = offsetY;
		}

		return true;
	}

	/** This function will increment the specific piece by the speed
	 * 
	 * @return true if at designated location, otherwise false
	 */
	public boolean movePiece(ModelRenderer currentRender, float speed, float rotationX, float rotationY, float rotationZ)
	{
		if(currentRender.rotateAngleX < degToRad(rotationX))
		{
			if(currentRender.rotateAngleX + degToRad(speed) < degToRad(rotationX))
			{
				currentRender.rotateAngleX += degToRad(speed);
			}else
				currentRender.rotateAngleX = degToRad(rotationX);
		}else
		{
			if(currentRender.rotateAngleX - degToRad(speed) > degToRad(rotationX))
			{
				currentRender.rotateAngleX -= degToRad(speed);
			}else
			{	
				currentRender.rotateAngleX = degToRad(rotationX);
			}
		}

		//Y
		if(currentRender.rotateAngleY < degToRad(rotationY))
		{
			if(currentRender.rotateAngleY + degToRad(speed) < degToRad(rotationY))
			{
				currentRender.rotateAngleY += degToRad(speed);
			}else
				currentRender.rotateAngleY = degToRad(rotationY);
		}else
		{
			if(currentRender.rotateAngleY - degToRad(speed) > degToRad(rotationY))
			{
				currentRender.rotateAngleY -= degToRad(speed);
			}else
				currentRender.rotateAngleY = degToRad(rotationY);
		}
		//Z
		if(currentRender.rotateAngleZ < degToRad(rotationZ))
		{
			if(currentRender.rotateAngleZ + degToRad(speed) < degToRad(rotationZ))
			{
				currentRender.rotateAngleZ += degToRad(speed);
			}else
				currentRender.rotateAngleZ = degToRad(rotationZ);
		}else
		{
			if(currentRender.rotateAngleZ - degToRad(speed) > degToRad(rotationZ))
			{
				currentRender.rotateAngleZ -= degToRad(speed);
			}else
				currentRender.rotateAngleZ = degToRad(rotationZ);
		}

		if(currentRender.rotateAngleX != degToRad(rotationX) || currentRender.rotateAngleY != degToRad(rotationY) || currentRender.rotateAngleZ != degToRad(rotationZ))
		{
			return false;
		}

		return true;
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
