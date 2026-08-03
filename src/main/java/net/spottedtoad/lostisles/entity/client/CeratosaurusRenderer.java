package net.spottedtoad.lostisles.entity.client;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.spottedtoad.lostisles.entity.custom.CeratosaurusEntity;

public class CeratosaurusRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<CeratosaurusEntity, R> {
    public CeratosaurusRenderer(EntityRendererProvider.Context context) {
        super(context, new CeratosaurusModel());
    }
}