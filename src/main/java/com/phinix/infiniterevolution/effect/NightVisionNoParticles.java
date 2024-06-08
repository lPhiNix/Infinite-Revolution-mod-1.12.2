package com.phinix.infiniterevolution.effect;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class NightVisionNoParticles extends PotionEffect {

    public NightVisionNoParticles(int duration, int amplifier) {
        super(Potion.getPotionFromResourceLocation("night_vision"), duration, amplifier, false, false);
    }

    @Override
    public boolean doesShowParticles() {
        return false;
    }
}

