package com.teamaurora.abundance.common.entity.living;

import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatedEntity;
import com.teamaurora.abundance.core.Abundance;
import com.teamaurora.abundance.core.registry.AbundanceEffects;
import com.teamaurora.abundance.core.registry.AbundanceEntities;
import com.teamaurora.abundance.core.registry.AbundanceSoundEvents;
import net.minecraft.command.impl.EffectCommand;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

public class ScreecherEntity extends EndimatedEntity {

    public static final DataParameter<Boolean> IS_SCREECHING = EntityDataManager.createKey(ScreecherEntity.class, DataSerializers.BOOLEAN);
    private int timeNextScreech = 0;

    public ScreecherEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createScreecherAttributes() {
        return MonsterEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_SCREECHING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
        this.goalSelector.addGoal(2, new ScreechGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this, ScreecherEntity.class));
    }

    public boolean canScreech() {
        Abundance.LOGGER.info("Can screech: " + (this.timeNextScreech <= 0));
        return this.timeNextScreech <= 0;
    }

    public void setScreeching(boolean screeching) {
        this.dataManager.set(IS_SCREECHING, screeching);
    }

    public boolean isScreeching() {
        Abundance.LOGGER.info("Is screeching: " + this.dataManager.get(IS_SCREECHING));
        return this.dataManager.get(IS_SCREECHING);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return AbundanceSoundEvents.SCREECHER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AbundanceSoundEvents.SCREECHER_DEATH.get();
    }

    protected SoundEvent getScreechSound() {
        return AbundanceSoundEvents.SCREECHER_SCREECH.get();
    }

    @Override
    public float getSoundVolume() {
        return 0.8F;
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!this.isScreeching()) {
            if (this.timeNextScreech > 0) {
                --this.timeNextScreech;
            }
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        compoundNBT.putInt("ScreechTime", this.timeNextScreech);
    }

    @Override
    public void readAdditional(CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        this.timeNextScreech = compoundNBT.getInt("ScreechTime");
    }

    @Override
    public Endimation[] getEndimations() {
        return new Endimation[0];
    }

    private static class ScreechGoal extends Goal {

        private final ScreecherEntity screecher;
        private static final int maxScreechTime = 40;
        private int screechTime;

        protected ScreechGoal(ScreecherEntity screecher) {
            this.screecher = screecher;
        }

        @Override
        public boolean shouldExecute() {
            return this.screecher.getAttackTarget() != null && this.screecher.canScreech();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return this.screecher.getAttackTarget() != null && this.screecher.getAttackTarget().isAlive() && this.screechTime < maxScreechTime;
        }

        @Override
        public void startExecuting() {
            this.screecher.setScreeching(true);
            this.screecher.timeNextScreech = 600;
            this.screecher.world.playSound(null, this.screecher.getPosition(), this.screecher.getScreechSound(), SoundCategory.NEUTRAL, 2.0F, this.screecher.getSoundPitch());
        }

        @Override
        public void resetTask() {
            this.screecher.setScreeching(false);
            this.screechTime = 0;
        }

        @Override
        public void tick() {
            ++this.screechTime;

            if (this.screechTime >= maxScreechTime) {
                this.doScreechEffect();
            }
        }

        private void doScreechEffect() {
            if (!this.screecher.world.isRemote) {
                AxisAlignedBB box = this.screecher.getBoundingBox().expand(14.0D, 14.0D, 14.0D);
                List<PlayerEntity> nearbyPlayers = this.screecher.world.getEntitiesWithinAABB(PlayerEntity.class, box);

                for (PlayerEntity player : nearbyPlayers) {
                    player.addPotionEffect(new EffectInstance(AbundanceEffects.DEAFNESS.get(), 140));
                }
            }
        }
    }
}
