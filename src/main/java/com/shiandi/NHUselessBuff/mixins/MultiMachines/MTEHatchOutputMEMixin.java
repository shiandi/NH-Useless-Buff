package com.shiandi.NHUselessBuff.mixins.MultiMachines;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import appeng.api.implementations.IPowerChannelState;
import gregtech.api.metatileentity.implementations.MTEHatchOutput;
import gregtech.common.tileentities.machines.MTEHatchOutputME;

@Mixin(value = MTEHatchOutputME.class, remap = false)
public abstract class MTEHatchOutputMEMixin extends MTEHatchOutput implements IPowerChannelState {

    public MTEHatchOutputMEMixin(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier);
    }

    @Shadow
    boolean additionalConnection;

    @Inject(method = "updateValidGridProxySides", at = @At("HEAD"))
    private void updateValidGridProxySides(CallbackInfo ci) {
        this.additionalConnection = true;
    }
}
