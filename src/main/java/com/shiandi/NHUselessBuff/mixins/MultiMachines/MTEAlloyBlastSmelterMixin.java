package com.shiandi.NHUselessBuff.mixins.MultiMachines;

import org.spongepowered.asm.mixin.Mixin;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;

import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.GTPPMultiBlockBase;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.MTEAlloyBlastSmelter;

@Mixin(value = MTEAlloyBlastSmelter.class, remap = false)
public abstract class MTEAlloyBlastSmelterMixin extends GTPPMultiBlockBase<MTEAlloyBlastSmelter>
    implements ISurvivalConstructable {

    public MTEAlloyBlastSmelterMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }
}
