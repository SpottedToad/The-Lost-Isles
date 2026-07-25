package net.spottedtoad.lostisles;

import com.terraformersmc.terraform.boat.api.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;

public class TheLostIslesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TerraformBoatClientHelper.registerModelLayers(TheLostIsles.CONIFER_BOATS_ID);
    }
}
