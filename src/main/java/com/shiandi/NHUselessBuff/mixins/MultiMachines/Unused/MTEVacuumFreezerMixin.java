package com.shiandi.NHUselessBuff.mixins.MultiMachines.Unused;

import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.HatchElement.Energy;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.google.common.collect.ImmutableList;

import gregtech.api.interfaces.IHatchElement;
import gregtech.api.metatileentity.implementations.MTECubicMultiBlockBase;
import gregtech.common.tileentities.machines.multi.MTEVacuumFreezer;

@Mixin(value = MTEVacuumFreezer.class, remap = false)
public abstract class MTEVacuumFreezerMixin extends MTECubicMultiBlockBase<MTEVacuumFreezer> {

    protected MTEVacuumFreezerMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    /**
     * @author shiandi
     * @reason 替换原来的结构检测
     */
    @Overwrite
    protected List<IHatchElement<? super MTECubicMultiBlockBase<?>>> getAllowedHatches() {
        return ImmutableList.of(InputHatch, OutputHatch, InputBus, OutputBus, Maintenance, Energy.or(ExoticEnergy));
    }
}
