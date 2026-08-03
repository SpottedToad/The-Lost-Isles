package net.spottedtoad.lostisles.entity.client;

import com.geckolib.constant.DataTickets;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.util.Mth;
import net.spottedtoad.lostisles.entity.custom.CeratosaurusEntity;

public class CeratosaurusRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<CeratosaurusEntity, R> {
    public CeratosaurusRenderer(EntityRendererProvider.Context context) {
        super(context, new CeratosaurusModel());
    }

    private static final String[] NECK_CHAIN = {"n1", "n2", "n3"};
    private float smoothYaw = 0;
    private float smoothPitch = 0;

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<R> renderPassInfo, BoneSnapshots snapshots) {
        super.adjustModelBonesForRender(renderPassInfo, snapshots);
        R renderState = renderPassInfo.renderState();
        if (renderState == null) return;

        Float targetYaw = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f);
        Float targetPitch = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f);
        Float partialTick = renderState.getOrDefaultGeckolibData(DataTickets.PARTIAL_TICK, 0f);

        this.smoothYaw = Mth.lerp(0.12f * partialTick, this.smoothYaw, targetYaw);
        this.smoothPitch = Mth.lerp(0.12f * partialTick, this.smoothPitch, targetPitch);

        int segments = NECK_CHAIN.length;
        float yawPerSegment = this.smoothYaw / segments;
        float pitchPerSegment = this.smoothPitch / segments;

        for (String boneName : NECK_CHAIN) {
            snapshots.ifPresent(boneName, boneSnapshot -> {
                boneSnapshot.setRotY(boneSnapshot.getRotY() - (yawPerSegment * Mth.DEG_TO_RAD));
                boneSnapshot.setRotX(boneSnapshot.getRotX() + (pitchPerSegment * Mth.DEG_TO_RAD));
            });
        }

        snapshots.ifPresent("head", headSnapshot -> {
            float headYawCorrection = this.smoothYaw - (yawPerSegment * segments);
            float headPitchCorrection = this.smoothPitch - (pitchPerSegment * segments);
            headSnapshot.setRotY(headSnapshot.getRotY() - (headYawCorrection * Mth.DEG_TO_RAD));
            headSnapshot.setRotX(headSnapshot.getRotX() + (headPitchCorrection * Mth.DEG_TO_RAD));
        });
    }
}