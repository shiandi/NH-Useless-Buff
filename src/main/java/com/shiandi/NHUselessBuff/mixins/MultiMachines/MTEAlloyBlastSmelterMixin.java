package com.shiandi.NHUselessBuff.mixins.MultiMachines;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.onElementPass;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.enums.TAE;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gtPlusPlus.core.block.ModBlocks;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.GTPPMultiBlockBase;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.MTEAlloyBlastSmelter;

@Mixin(value = MTEAlloyBlastSmelter.class, remap = false)
public abstract class MTEAlloyBlastSmelterMixin extends GTPPMultiBlockBase<MTEAlloyBlastSmelter>
    implements ISurvivalConstructable {

    public MTEAlloyBlastSmelterMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Shadow
    private int mCasing;
    @Shadow
    private static IStructureDefinition<MTEAlloyBlastSmelter> STRUCTURE_DEFINITION = null;

    /**
     * @author shiandi
     * @reason 修改检测
     */
    @Overwrite
    public IStructureDefinition<MTEAlloyBlastSmelter> getStructureDefinition() {
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<MTEAlloyBlastSmelter>builder()
                .addShape(
                    mName,
                    transpose(
                        new String[][] { { "CCC", "CCC", "CCC" }, { "HHH", "H-H", "HHH" }, { "HHH", "H-H", "HHH" },
                            { "C~C", "CCC", "CCC" }, }))
                .addElement(
                    'C',
                    buildHatchAdder(MTEAlloyBlastSmelter.class)
                        .atLeast(
                            InputBus,
                            InputHatch,
                            OutputBus,
                            OutputHatch,
                            Maintenance,
                            Energy.or(ExoticEnergy),
                            Muffler)
                        .casingIndex(TAE.GTPP_INDEX(15))
                        .dot(1)
                        .buildAndChain(onElementPass(x -> ++mCasing, ofBlock(ModBlocks.blockCasingsMisc, 15))))
                .addElement('H', ofBlock(ModBlocks.blockCasingsMisc, 14))
                .build();
        }
        return STRUCTURE_DEFINITION;
    }

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
