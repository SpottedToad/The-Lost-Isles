package net.spottedtoad.lostisles;

import net.minecraft.resources.Identifier;
import net.spottedtoad.lostisles.world.biome.region.ModRegion;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class TheLostIslesTerraBlenderEntry implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        //Disabled
        // Regions.register(new ModRegion(Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "overworld_region"),
        // RegionType.OVERWORLD, 2));
    }
}
