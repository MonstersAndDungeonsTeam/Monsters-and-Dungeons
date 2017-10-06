package monstersanddungeons.testing;

import java.awt.Color;

public class EnumColours 
{

	public enum hiltColour
	{
		OBSIDIAN(0, new Color(150, 20, 160)),
		STEEL(1, new Color(150, 170, 160)),
		CHEESE(2, new Color(0, 70, 160)),
		REDSTONE(3, new Color(150, 20, 0));

		int id;
		Color color;
		hiltColour(int id, Color color)
		{
			this.id = id;
			this.color = color;
		}

		public int getID()
		{
			return this.id;
		}

		public int getRBGValue()
		{
			return this.color.getRGB();
		}

		public static hiltColour getEnumFrom(int id)
		{
			for(hiltColour colour : values())
			{
				if(colour.getID() == id)
				{
					return colour;
				}
			}
			return null;
		}
	}

	public enum swordColour
	{
		GOLD(0, new Color(150, 216, 15)),
		IROM(1, new Color(150, 5, 60)),
		DIAMOND(2, new Color(100, 30, 255)),
		WOOD(3, new Color(10, 40, 70));

		int id;
		Color color;
		swordColour(int id, Color color)
		{
			this.id = id;
			this.color = color;
		}

		public int getID()
		{
			return this.id;
		}

		public int getRBGValue()
		{
			return this.color.getRGB();
		}

		public static swordColour getEnumFrom(int id)
		{
			for(swordColour colour : values())
			{
				if(colour.getID() == id)
				{
					return colour;
				}
			}
			return null;
		}
	}

	public enum handleColour
	{
		OBSIDIAN(0, new Color(150, 20, 160)),
		STEEL(1, new Color(150, 170, 160)),
		CHEESE(2, new Color(0, 70, 160)),
		REDSTONE(3, new Color(150, 170, 255));

		int id;
		Color color;
		handleColour(int id, Color color)
		{
			this.id = id;
			this.color = color;
		}

		public int getID()
		{
			return this.id;
		}

		public int getRBGValue()
		{
			return this.color.getRGB();
		}

		public static handleColour getEnumFrom(int id)
		{
			for(handleColour colour : values())
			{
				if(colour.getID() == id)
				{
					return colour;
				}
			}
			return null;
		}
	}

}
