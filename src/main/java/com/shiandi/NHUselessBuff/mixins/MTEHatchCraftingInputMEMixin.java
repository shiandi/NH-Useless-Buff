package com.shiandi.NHUselessBuff.mixins;

import java.util.EnumSet;

import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import appeng.api.implementations.IPowerChannelState;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.util.IInterfaceViewable;
import appeng.helpers.ICustomNameObject;
import appeng.me.helpers.IGridProxyable;
import gregtech.api.interfaces.IConfigurationCircuitSupport;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.modularui.IAddGregtechLogo;
import gregtech.api.interfaces.modularui.IAddUIWidgets;
import gregtech.api.metatileentity.implementations.MTEHatchInputBus;
import gregtech.common.tileentities.machines.IDualInputHatch;
import gregtech.common.tileentities.machines.MTEHatchCraftingInputME;

@Mixin(value = MTEHatchCraftingInputME.class, remap = false)
public abstract class MTEHatchCraftingInputMEMixin extends MTEHatchInputBus
    implements IConfigurationCircuitSupport, IAddGregtechLogo, IAddUIWidgets, IPowerChannelState, ICraftingProvider,
    IGridProxyable, IDualInputHatch, ICustomNameObject, IInterfaceViewable {

    public MTEHatchCraftingInputMEMixin(String aName, int aTier, int aSlots, String[] aDescription,
        ITexture[][][] aTextures) {
        super(aName, aTier, aSlots, aDescription, aTextures);
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
