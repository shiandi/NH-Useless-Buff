package com.shiandi.NHUselessBuff.mixins.MultiMachines.Unused;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.GTPPMultiBlockBase;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.MTEAlloyBlastSmelter;

@Mixin(value = MTEAlloyBlastSmelter.class, remap = false)
public abstract class MTEAlloyBlastSmelterMixin extends GTPPMultiBlockBase<MTEAlloyBlastSmelter>
    implements ISurvivalConstructable {

    public MTEAlloyBlastSmelterMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Shadow
    private int mCasing = 0;

    /**
     * @author shiandi
     * @reason 取消能源仓个数限制
     */
    @Overwrite
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        mCasing = 0;
        return checkPiece(mName, 1, 3, 0) && mCasing >= 1 && !mEnergyHatches.isEmpty() && checkHatch();
    }
}
