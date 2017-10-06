package monstersanddungeons.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class MaDEntityList 
{
	/** Used to assign IDs automatically as entities are added*/
	private static int entitiesAdded = 0;

	/** Provides a mapping between entity classes and a string */
	public static Map<String, Class<? extends Entity>> stringToClassMapping = new HashMap<String, Class<? extends Entity>>();

	/** Provides a mapping between a string and an entity classes */
	public static Map<Class<? extends Entity>, String> classToStringMapping = new HashMap<Class<? extends Entity>, String>();

	/** provides a mapping between an entityID and an Entity Class */
	public static Map<Integer, Class<? extends Entity>> IDtoClassMapping = new HashMap<Integer, Class<? extends Entity>>();

	/** provides a mapping between an Entity Class and an entity ID */
	private static Map<Class<? extends Entity>, Integer> classToIDMapping = new HashMap<Class<? extends Entity>, Integer>();

	/** Maps entity names to their numeric identifiers */
	private static Map<String, Integer> stringToIDMapping = new HashMap<String, Integer>();

	/** This is a HashMap of the Creative Entity Eggs/Spawners. */
	public static HashMap<Class<? extends Entity>, List<Integer>> entityEggs = new LinkedHashMap<Class<? extends Entity>, List<Integer>>();

	/**
	 * Adds a mapping between Entity classes and both a string representation and an ID
	 */
	public static void addMapping(Class<? extends Entity> oclass, String entityName) {
		int entityID = getEntityId(oclass);
		stringToClassMapping.put(entityName, oclass);
		classToStringMapping.put(oclass, entityName);
		IDtoClassMapping.put(entityID, oclass);
		classToIDMapping.put(oclass, entityID);
		stringToIDMapping.put(entityName, entityID);
	}

	/**
	 * Adds an entity mapping with list of colors.
	 */
	public static void addMapping(Class<? extends Entity> oclass, String entityName, Integer... colors) {
		addMapping(oclass, entityName);
		entityEggs.put(oclass, Arrays.asList(colors));
	}

	/**
	 * Returns the next available ID or the current ID assigned to this class
	 */
	public static int getEntityId(Class<? extends Entity> oclass) {
		if (classToIDMapping.containsKey(oclass)) {
			return classToIDMapping.get(oclass);
		} else if (!IDtoClassMapping.containsKey(entitiesAdded)) {
			return entitiesAdded++;
		} else {
			while (IDtoClassMapping.containsKey(entitiesAdded)) {
				++entitiesAdded;
			}
			return entitiesAdded;
		}
	}

	/**
	 * Create a new instance of an entity in the world by using the entity name.
	 */
	public static Entity createEntityByName(String entityName, World world) {
		return createEntity(stringToClassMapping.get(entityName), world);
	}

	/**
	 * create a new instance of an entity from NBT store
	 */
	public static Entity createEntityFromNBT(NBTTagCompound compound, World world) {
		Entity entity = createEntityByName(compound.getString("id"), world);
		if (entity != null) {
			try {
				entity.readFromNBT(compound);
			} catch (Exception e) {

				e.printStackTrace();
				entity = null;
			}
		} else {

		}

		return entity;
	}

	/**
	 * Create a new instance of an entity in the world by using an entity ID.
	 */
	public static Entity createEntityByID(int entityID, World world) {
		return createEntity(getClassFromID(entityID), world);
	}

	/**
	 * Constructs an entity based on the class provided
	 */
	public static Entity createEntity(Class<? extends Entity> oclass, World world) {
		Entity entity = null;
		try {
			if (oclass != null) {
				entity = oclass.getConstructor(World.class).newInstance(world);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return entity;
	}

	/**
	 * gets the entityID of a specific entity
	 */
	public static int getEntityID(Entity entity) {
		Class<? extends Entity> oclass = entity.getClass();
		return classToIDMapping.containsKey(oclass) ? classToIDMapping.get(oclass) : 0;
	}

	/**
	 * Return the class assigned to this entity ID.
	 */
	public static Class<? extends Entity> getClassFromID(int entityID) {
		return IDtoClassMapping.get(entityID);
	}

	/**
	 * Gets the string representation of a specific entity.
	 */
	public static String getEntityString(Entity entity) {
		return classToStringMapping.get(entity.getClass());
	}

	/**
	 * Finds the class using IDtoClassMapping and classToStringMapping
	 */
	public static String getStringFromID(int entityID) {
		Class<? extends Entity> oclass = getClassFromID(entityID);
		return oclass != null ? classToStringMapping.get(oclass) : null;
	}
}
