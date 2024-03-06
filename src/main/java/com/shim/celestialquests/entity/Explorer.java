package com.shim.celestialquests.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;

public class Explorer extends Villager {
    public Explorer(EntityType<? extends Villager> p_35381_, Level p_35382_) {
        super(p_35381_, p_35382_);
    }

    @Override
    public InteractionResult mobInteract(Player p_35472_, InteractionHand p_35473_) {
        ItemStack itemstack = p_35472_.getItemInHand(p_35473_);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.isTrading() && !this.isSleeping() && !p_35472_.isSecondaryUseActive()) {
            if (this.isBaby()) {
                this.setUnhappy();
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            } else {
                boolean flag = this.getOffers().isEmpty();
                if (p_35473_ == InteractionHand.MAIN_HAND) {
                    if (flag && !this.level.isClientSide) {
                        this.setUnhappy();
                    }
                    p_35472_.awardStat(Stats.TALKED_TO_VILLAGER);
                }

                if (flag) {
                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                } else {
                    if (!this.level.isClientSide && !this.offers.isEmpty()) {
                        this.startTrading(p_35472_);
                    }
                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                }
            }
        } else {
            return super.mobInteract(p_35472_, p_35473_);
        }
    }

    private void setUnhappy() {
        this.setUnhappyCounter(40);
        if (!this.level.isClientSide()) {
            this.playSound(SoundEvents.VILLAGER_NO, this.getSoundVolume(), this.getVoicePitch());
        }

    }

    private void startTrading(Player p_35537_) {
        this.updateSpecialPrices(p_35537_);
        this.setTradingPlayer(p_35537_);
        this.openTradingScreen(p_35537_, this.getDisplayName(), this.getVillagerData().getLevel());
    }

    @Override
    public void openTradingScreen(Player p_45302_, Component p_45303_, int p_45304_) {
        super.openTradingScreen(p_45302_, p_45303_, p_45304_);
    }

    private void updateSpecialPrices(Player p_35541_) {
        int i = this.getPlayerReputation(p_35541_);
        if (i != 0) {
            for(MerchantOffer merchantoffer : this.getOffers()) {
                merchantoffer.addToSpecialPriceDiff(-Mth.floor((float)i * merchantoffer.getPriceMultiplier()));
            }
        }

        if (p_35541_.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)) {
            MobEffectInstance mobeffectinstance = p_35541_.getEffect(MobEffects.HERO_OF_THE_VILLAGE);
            int k = mobeffectinstance.getAmplifier();

            for(MerchantOffer merchantoffer1 : this.getOffers()) {
                double d0 = 0.3D + 0.0625D * (double)k;
                int j = (int)Math.floor(d0 * (double)merchantoffer1.getBaseCostA().getCount());
                merchantoffer1.addToSpecialPriceDiff(-Math.max(j, 1));
            }
        }
    }
}