package com.shiandi.NHUselessBuff.mixins;

import java.util.EnumSet;

import net.minecraftforge.common.util.ForgeDirection;

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

    @Inject(method = "updateValidGridProxySides", at = @At("HEAD"), cancellable = true)
    private void updateValidGridProxySides(CallbackInfo ci) {
        if (!additionalConnection) {
            getProxy().setValidSides(EnumSet.complementOf(EnumSet.of(ForgeDirection.UNKNOWN)));
        } else {
            getProxy().setValidSides(EnumSet.of(getBaseMetaTileEntity().getFrontFacing()));
        }
        ci.cancel();
    }
}
