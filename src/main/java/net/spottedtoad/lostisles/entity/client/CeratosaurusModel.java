package net.spottedtoad.lostisles.entity.client;

import com.geckolib.model.DefaultedEntityGeoModel;
import net.minecraft.resources.Identifier;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.entity.custom.CeratosaurusEntity;

public class CeratosaurusModel extends DefaultedEntityGeoModel<CeratosaurusEntity> {
    public CeratosaurusModel() {
        super(Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "ceratosaurus_male"));
        this.withAltTexture(Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "ceratosaurus/male/adult/1"));
    }

    public Identifier getModelResource(CeratosaurusEntity animatable) {
        return Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "geckolib/models/ceratosaurus_male");
    }
}