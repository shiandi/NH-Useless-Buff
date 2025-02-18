package com.shiandi.NHUselessBuff.mixins;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;
import static gregtech.api.util.GTStructureUtility.ofCoil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.GregTechAPI;
import gregtech.common.tileentities.machines.multi.MTEAbstractMultiFurnace;
import gregtech.common.tileentities.machines.multi.MTEElectricBlastFurnace;

@Mixin(value = MTEElectricBlastFurnace.class, remap = false)
public abstract class ElectricBlastFurnaceMixin extends MTEAbstractMultiFurnace<MTEElectricBlastFurnace>
    implements ISurvivalConstructable {

    protected ElectricBlastFurnaceMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Unique
    private static final IStructureDefinition<MTEElectricBlastFurnace> STRUCTURE_DEFINITION = StructureDefinition
        .<MTEElectricBlastFurnace>builder()
        .addShape(
            "main",
            transpose(
                new String[][] { { "ttt", "tmt", "ttt" }, { "CCC", "C-C", "CCC" }, { "CCC", "C-C", "CCC" },
                    { "b~b", "bbb", "bbb" } }))
        .addElement(
            't',
            buildHatchAdder(MTEElectricBlastFurnace.class)
                .atLeast(InputHatch, OutputHatch, InputBus, OutputBus, Maintenance, Energy.or(ExoticEnergy))
                .casingIndex(11)
                .dot(1)
                .buildAndChain(GregTechAPI.sBlockCasings1, 11))
        .addElement('m', Muffler.newAny(11, 2))
        .addElement('C', ofCoil(MTEElectricBlastFurnace::setCoilLevel, MTEElectricBlastFurnace::getCoilLevel))
        .addElement(
            'b',
            buildHatchAdder(MTEElectricBlastFurnace.class)
                .atLeast(InputHatch, OutputHatch, InputBus, OutputBus, Maintenance, Energy.or(ExoticEnergy))
                .casingIndex(11)
                .dot(1)
                .buildAndChain(GregTechAPI.sBlockCasings1, 11))
        .build();

    @Override
    public IStructureDefinition<MTEElectricBlastFurnace> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }
}
