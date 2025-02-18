package com.shiandi.NHUselessBuff.mixins.MultiMachines;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static gregtech.api.enums.HatchElement.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.GregTechAPI;
import gregtech.api.util.GTStructureUtility;
import gregtech.common.tileentities.machines.multi.MTEAbstractMultiFurnace;
import gregtech.common.tileentities.machines.multi.MTEMultiFurnace;

@Mixin(value = MTEMultiFurnace.class, remap = false)
public abstract class MTEMultiFurnaceMixin extends MTEAbstractMultiFurnace<MTEMultiFurnace>
    implements ISurvivalConstructable {

    protected MTEMultiFurnaceMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Shadow
    private static final int CASING_INDEX = 11;
    @Shadow
    private static final String STRUCTURE_PIECE_MAIN = "main";

    @Unique
    private static final IStructureDefinition<MTEMultiFurnace> STRUCTURE_DEFINITION = StructureDefinition
        .<MTEMultiFurnace>builder()
        .addShape(
            STRUCTURE_PIECE_MAIN,
            transpose(new String[][] { { "ccc", "cmc", "ccc" }, { "CCC", "C-C", "CCC" }, { "b~b", "bbb", "bbb" } }))
        .addElement('c', ofBlock(GregTechAPI.sBlockCasings1, CASING_INDEX))
        .addElement('m', Muffler.newAny(CASING_INDEX, 2))
        .addElement('C', GTStructureUtility.ofCoil(MTEMultiFurnace::setCoilLevel, MTEMultiFurnace::getCoilLevel))
        .addElement(
            'b',
            ofChain(
                GTStructureUtility.<MTEMultiFurnace>buildHatchAdder()
                    .atLeast(Maintenance, InputBus, OutputBus, Energy.or(ExoticEnergy))
                    .casingIndex(CASING_INDEX)
                    .dot(1)
                    .build(),
                ofBlock(GregTechAPI.sBlockCasings1, CASING_INDEX)))
        .build();

    /**
     * @author shiandi
     * @reason 替换结构检测
     */
    @Overwrite
    public IStructureDefinition<MTEMultiFurnace> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }
}
