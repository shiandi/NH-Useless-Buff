package com.shiandi.NHUselessBuff.mixins.MultiMachines;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import machines.Chaos;

@Mixin(value = Chaos.class, remap = false)
public abstract class ChaosMixin extends MTEExtendedPowerMultiBlockBase<Chaos> implements ISurvivalConstructable {

    protected ChaosMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Shadow
    private static final String STRUCTURE_PIECE_MAIN = "main";

    @Inject(method = "checkMachine", at = @At("RETURN"), cancellable = true)
    private void checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack,
        CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(checkPiece(STRUCTURE_PIECE_MAIN, 1, 1, 0));
    }
}
