package com.shiandi.NHUselessBuff.mixins;

import java.util.EnumSet;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import appeng.api.implementations.IPowerChannelState;
import gregtech.api.metatileentity.implementations.MTEHatchOutputBus;
import gregtech.common.tileentities.machines.MTEHatchOutputBusME;

@Mixin(value = MTEHatchOutputBusME.class, remap = false)
public abstract class MTEHatchOutputBusMEMixin extends MTEHatchOutputBus implements IPowerChannelState {

    public MTEHatchOutputBusMEMixin(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier);
    }

    @Shadow
    boolean additionalConnection;

    @Inject(method = "updateValidGridProxySides", at = @At("HEAD"), cancellable = true)
    public void updateEntity(CallbackInfo ci) {
        if (!additionalConnection) {
            getProxy().setValidSides(EnumSet.complementOf(EnumSet.of(ForgeDirection.UNKNOWN)));
        } else {
            getProxy().setValidSides(EnumSet.of(getBaseMetaTileEntity().getFrontFacing()));
        }
        ci.cancel();
    }
}
