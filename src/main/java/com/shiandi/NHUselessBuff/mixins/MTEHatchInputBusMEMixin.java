package com.shiandi.NHUselessBuff.mixins;

import java.util.EnumSet;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import appeng.api.implementations.IPowerChannelState;
import gregtech.api.interfaces.IConfigurationCircuitSupport;
import gregtech.api.interfaces.IDataCopyable;
import gregtech.api.interfaces.modularui.IAddGregtechLogo;
import gregtech.api.interfaces.modularui.IAddUIWidgets;
import gregtech.api.metatileentity.implementations.MTEHatchInputBus;
import gregtech.common.tileentities.machines.IRecipeProcessingAwareHatch;
import gregtech.common.tileentities.machines.ISmartInputHatch;
import gregtech.common.tileentities.machines.MTEHatchInputBusME;

@Mixin(value = MTEHatchInputBusME.class, remap = false)
public abstract class MTEHatchInputBusMEMixin extends MTEHatchInputBus implements IConfigurationCircuitSupport,
    IRecipeProcessingAwareHatch, IAddGregtechLogo, IAddUIWidgets, IPowerChannelState, ISmartInputHatch, IDataCopyable {

    public MTEHatchInputBusMEMixin(int id, String name, String nameRegional, int tier) {
        super(id, name, nameRegional, tier);
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
