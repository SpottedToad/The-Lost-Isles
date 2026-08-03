package net.spottedtoad.lostisles;

import com.terraformersmc.terraform.boat.api.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.entity.client.CeratosaurusRenderer;
import net.spottedtoad.lostisles.entity.custom.ModEntityTypes;

import java.util.List;

public class TheLostIslesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TerraformBoatClientHelper.registerModelLayers(TheLostIsles.CONIFER_BOATS_ID);
        registerConiferLeafColor();

        EntityRenderers.register(ModEntityTypes.CERATOSAURUS, CeratosaurusRenderer::new);
    }

    private static void registerConiferLeafColor(){
        BlockColorRegistry.register(
            List.of(BlockTintSources.constant(0xFFbace76)),
            ModBlocks.CONIFER_LEAVES
        );
    }
}
