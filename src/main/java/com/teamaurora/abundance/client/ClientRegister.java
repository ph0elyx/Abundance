package com.teamaurora.abundance.client;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.abundance.core.Abundance;
import com.teamaurora.abundance.core.registry.AbundanceBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = Abundance.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            registerBlockColors();
            setupRenderLayer();
        });
    }

    public static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.LAVENDER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.TALL_LAVENDER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.LAVENDER_BLOSSOM_CARPET.get(), RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.SUNNY_MARIGOLD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.SHADY_MARIGOLD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.TALL_MARIGOLD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_SUNNY_MARIGOLD.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_SHADY_MARIGOLD.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.JACARANDA_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_JACARANDA_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.JACARANDA_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.BUDDING_JACARANDA_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.FLOWERING_JACARANDA_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.JACARANDA_LADDER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.JACARANDA_TRAPDOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.JACARANDA_DOOR.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.JACARANDA_HEDGE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.BUDDING_JACARANDA_HEDGE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.FLOWERING_JACARANDA_HEDGE.get(), RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.REDBUD_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_REDBUD_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.FLOWERING_REDBUD_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_FLOWERING_REDBUD_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.REDBUD_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.BUDDING_REDBUD_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.FLOWERING_REDBUD_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.REDBUD_LADDER.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.REDBUD_HEDGE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.BUDDING_REDBUD_HEDGE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.FLOWERING_REDBUD_HEDGE.get(), RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.AMARANTHUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.CHICORY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.NEMOPHILA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.PURPLE_AFRICAN_DAISY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.YELLOW_AFRICAN_DAISY.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_AMARANTHUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_CHICORY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_NEMOPHILA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_PURPLE_AFRICAN_DAISY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_YELLOW_AFRICAN_DAISY.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(AbundanceBlocks.SAGUARO_SPROUT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.POTTED_SAGUARO_SPROUT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AbundanceBlocks.SAGUARO_FLOWER.get(), RenderType.getCutout());
    }

    private static int blendColors(int color1, int color2, float biasTowards1) {
        int r1 = (color1 & 0xFF0000) >>> 16;
        int g1 = (color1 & 0x00FF00) >>> 8;
        int b1 = color1 & 0x0000FF;
        int r2 = (color2 & 0xFF0000) >>> 16;
        int g2 = (color2 & 0x00FF00) >>> 8;
        int b2 = color2 & 0x0000FF;

        float biasTowards2 = 1 - biasTowards1;

        int r3 = Math.round(r1 * biasTowards1 + r2 * biasTowards2);
        int g3 = Math.round(g1 * biasTowards1 + g2 * biasTowards2);
        int b3 = Math.round(b1 * biasTowards1 + b2 * biasTowards2);

        return (r3 << 16) + (g3 << 8) + b3;
    }

    private static int getRedbudColor(IBlockDisplayReader worldIn, BlockPos blockPosIn) {
        return blendColors(0xACE352, BiomeColors.getFoliageColor(worldIn, blockPosIn), 0.5F);
    }

    public static void registerBlockColors() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? getRedbudColor(world, pos) : 0xACE352, Arrays.asList(
                AbundanceBlocks.REDBUD_LEAVES,
                AbundanceBlocks.REDBUD_LEAF_CARPET,
                AbundanceBlocks.REDBUD_HEDGE,
                AbundanceBlocks.BUDDING_REDBUD_LEAVES,
                AbundanceBlocks.BUDDING_REDBUD_LEAF_CARPET,
                AbundanceBlocks.BUDDING_REDBUD_HEDGE,
                AbundanceBlocks.FLOWERING_REDBUD_LEAVES,
                AbundanceBlocks.FLOWERING_REDBUD_LEAF_CARPET,
                AbundanceBlocks.FLOWERING_REDBUD_HEDGE
        ));

        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        DataUtil.registerBlockItemColor(itemColors, (color, items) -> 0xACE352, Arrays.asList(
                AbundanceBlocks.REDBUD_LEAVES,
                AbundanceBlocks.REDBUD_LEAF_CARPET,
                AbundanceBlocks.REDBUD_HEDGE,
                AbundanceBlocks.BUDDING_REDBUD_LEAVES,
                AbundanceBlocks.BUDDING_REDBUD_LEAF_CARPET,
                AbundanceBlocks.BUDDING_REDBUD_HEDGE,
                AbundanceBlocks.FLOWERING_REDBUD_LEAVES,
                AbundanceBlocks.FLOWERING_REDBUD_LEAF_CARPET,
                AbundanceBlocks.FLOWERING_REDBUD_HEDGE
        ));
    }
}
