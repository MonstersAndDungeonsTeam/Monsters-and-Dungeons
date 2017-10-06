package monstersanddungeons.entity.automatons;

import com.google.common.base.Predicate;

import monstersanddungeons.entity.MaDEntityMonsterBase;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTameablePawn extends EntityTameable
{
    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityTameablePawn.class, DataSerializers.FLOAT);
    //private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityTameablePawn.class, DataSerializers.BOOLEAN);
    //private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.<Integer>createKey(EntityTameablePawn.class, DataSerializers.VARINT);
    
    public EntityTameablePawn(World worldIn){
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
    }

    protected void initEntityAI(){
    	//TODO change priority numbers
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        //this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        //this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, MaDEntityMonsterBase.class, false, new Predicate<Entity>()
        {
            public boolean apply(@Nullable Entity entity)
            {
                return entity instanceof EntityWhitePawns;
            }
        }));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
        super.setAttackTarget(entitylivingbaseIn);

        /*if (entitylivingbaseIn == null)
        {
            this.setAngry(false);
        }
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }*/
    }

    protected void updateAITasks()
    {
        this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
        //this.dataManager.register(BEGGING, Boolean.valueOf(false));
        //this.dataManager.register(COLLAR_COLOR, Integer.valueOf(EnumDyeColor.RED.getDyeDamage()));
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    public static void func_189788_b(DataFixer p_189788_0_)
    {
        EntityLiving.func_189752_a(p_189788_0_, "Wolf");
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        //compound.setBoolean("Angry", this.isAngry());
        //compound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        //this.setAngry(compound.getBoolean("Angry"));

        /*if (compound.hasKey("CollarColor", 99))
        {
            this.setCollarColor(EnumDyeColor.byDyeDamage(compound.getByte("CollarColor")));
        }*/
    }

    /*protected SoundEvent getAmbientSound()
    {
        return this.isAngry() ? SoundEvents.ENTITY_WOLF_GROWL : (this.rand.nextInt(3) == 0 ? (this.isTamed() && ((Float)this.dataManager.get(DATA_HEALTH_ID)).floatValue() < 10.0F ? SoundEvents.ENTITY_WOLF_WHINE : SoundEvents.ENTITY_WOLF_PANT) : SoundEvents.ENTITY_WOLF_AMBIENT);
    }*/

    /*protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_WOLF_HURT;
    }*/

    /*protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }*/

    /**
     * Returns the volume for the sounds this mob makes.
     */
    /*protected float getSoundVolume()
    {
        return 0.4F;
    }*/

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_WOLF;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        /*if (!this.worldObj.isRemote && this.getAttackTarget() == null && this.isAngry())
        {
            this.setAngry(false);
        }*/
    }


    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getEntity();

            if (this.aiSit != null)
            {
                this.aiSit.setSitting(false);
            }

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public void setTamed(boolean tamed)
    {
        super.setTamed(tamed);

        if (tamed)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack)
    {
        if (this.isTamed())
        {
            if (stack != null)
            {
                /*if (stack.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood)stack.getItem();

                    if (itemfood.isWolfsFavoriteMeat() && ((Float)this.dataManager.get(DATA_HEALTH_ID)).floatValue() < 20.0F)
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            --stack.stackSize;
                        }

                        this.heal((float)itemfood.getHealAmount(stack));
                        return true;
                    }
                }
                else */
            	/*if (stack.getItem() == Items.DYE)
                {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());

                    if (enumdyecolor != this.getCollarColor())
                    {
                        this.setCollarColor(enumdyecolor);

                        if (!player.capabilities.isCreativeMode)
                        {
                            --stack.stackSize;
                        }

                        return true;
                    }
                }*/
            }

            if (this.isOwner(player) && !this.worldObj.isRemote/* && !this.isBreedingItem(stack)*/)
            {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (stack != null && stack.getItem() == Items.BONE)
        {
            if (!player.capabilities.isCreativeMode)
            {
                --stack.stackSize;
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.navigator.clearPathEntity();
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.processInteract(player, hand, stack);
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        super.handleStatusUpdate(id);
    }

    /*@SideOnly(Side.CLIENT)
    public float getTailRotation()
    {
        return this.isAngry() ? 1.5393804F : (this.isTamed() ? (0.55F - (this.getMaxHealth() - ((Float)this.dataManager.get(DATA_HEALTH_ID)).floatValue()) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F));
    }*/

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(@Nullable ItemStack stack)
    {
    	return false;
        //return stack == null ? false : (!(stack.getItem() instanceof ItemFood) ? false : ((ItemFood)stack.getItem()).isWolfsFavoriteMeat());
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    /*public EnumDyeColor getCollarColor()
    {
        return EnumDyeColor.byDyeDamage(((Integer)this.dataManager.get(COLLAR_COLOR)).intValue() & 15);
    }

    public void setCollarColor(EnumDyeColor collarcolor)
    {
        this.dataManager.set(COLLAR_COLOR, Integer.valueOf(collarcolor.getDyeDamage()));
    }*/

    public EntityTameablePawn createChild(EntityAgeable ageable)
    {
        EntityTameablePawn entitypawn = new EntityTameablePawn(this.worldObj);
        UUID uuid = this.getOwnerId();

        if (uuid != null)
        {
            entitypawn.setOwnerId(uuid);
            entitypawn.setTamed(true);
        }

        return entitypawn;
    }

    /*public void setBegging(boolean beg)
    {
        this.dataManager.set(BEGGING, Boolean.valueOf(beg));
    }*/

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    /*public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityTameablePawn))
        {
            return false;
        }
        else
        {
            EntityTameablePawn entitywolf = (EntityTameablePawn)otherAnimal;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }*/

    /*public boolean isBegging()
    {
        return ((Boolean)this.dataManager.get(BEGGING)).booleanValue();
    }*/

    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner)
    {
        if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast))
        {
            if (target instanceof EntityTameablePawn)
            {
                EntityTameablePawn entitywolf = (EntityTameablePawn)target;

                if (entitywolf.isTamed() && entitywolf.getOwner() == owner)
                {
                    return false;
                }
            }

            return target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer)owner).canAttackPlayer((EntityPlayer)target) ? false : !(target instanceof EntityHorse) || !((EntityHorse)target).isTame();
        }
        else
        {
            return false;
        }
    }

    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return /*!this.isAngry() && */super.canBeLeashedTo(player);
    }
}