package com.shiandi.NHUselessBuff.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;

@com.gtnewhorizon.gtnhmixins.LateMixin
public class LateMixin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.NHUselessBuff.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        // 创建 Mixin 列表
        List<String> mixins = new ArrayList<>(
            Arrays.asList(
                "MultiMachines.ChaosMixin",
                "MTEHatchCraftingInputMEMixin",
                "MTEHatchInputBusMEMixin",
                "MTEHatchInputMEMixin",
                "MTEHatchOutputBusMEMixin",
                "MTEHatchOutputMEMixin"));

        // 定义每个 mod 对应的 mixin 列表
        Map<String, List<String>> modMixins = new HashMap<>();
        modMixins.put("gtnothard", Arrays.asList("MultiMachines.ChaosMixin"));
        // 如果有其他 mod 需要 mixin，可以在这里添加，比如：
        // modMixins.put("AnotherMod", Arrays.asList("AnotherMod.SomeMixin1", "AnotherMod.SomeMixin2"));

        loadModSpecificMixins(loadedMods, modMixins, mixins);

        return mixins;
    }

    private void loadModSpecificMixins(Set<String> loadedMods, Map<String, List<String>> modMixins,
        List<String> mixins) {
        for (Map.Entry<String, List<String>> entry : modMixins.entrySet()) {
            String modName = entry.getKey();
            List<String> modMixinList = entry.getValue();

            if (loadedMods.contains(modName)) {
                for (String mixin : modMixinList) {
                    mixins.add(mixin);
                    System.out.println(modName + " mod detected! Loading " + mixin + ".");
                }
            } else {
                for (String mixin : modMixinList) {
                    System.out.println(modName + " mod not detected. Skipping " + mixin + ".");
                }
            }
        }
    }

}
