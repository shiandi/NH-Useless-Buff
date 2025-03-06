package com.shiandi.NHUselessBuff.mixins;

import java.util.EnumSet;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import appeng.api.implementations.IPowerChannelState;
import gregtech.api.interfaces.IDataCopyable;
import gregtech.api.interfaces.modularui.IAddGregtechLogo;
import gregtech.api.interfaces.modularui.IAddUIWidgets;
import gregtech.api.metatileentity.implementations.MTEHatchInput;
import gregtech.common.tileentities.machines.IRecipeProcessingAwareHatch;
import gregtech.common.tileentities.machines.ISmartInputHatch;
import gregtech.common.tileentities.machines.MTEHatchInputME;

@Mixin(value = MTEHatchInputME.class, remap = false)
public abstract class MTEHatchInputMEMixin extends MTEHatchInput implements IPowerChannelState, IAddGregtechLogo,
    IAddUIWidgets, IRecipeProcessingAwareHatch, ISmartInputHatch, IDataCopyable {

    public MTEHatchInputMEMixin(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier);
    }

    @Shadow
    private boolean additionalConnection;

    @Inject(method = "updateValidGridProxySides", at = @At("HEAD"), cancellable = true)
    public void updateValidGridProxySides(CallbackInfo ci) {
        if (!additionalConnection) {
            getProxy().setValidSides(EnumSet.complementOf(EnumSet.of(ForgeDirection.UNKNOWN)));
        } else {
            getProxy().setValidSides(EnumSet.of(getBaseMetaTileEntity().getFrontFacing()));
        }
        ci.cancel();
    }
}
