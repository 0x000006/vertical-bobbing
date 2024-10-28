package zerox06.vertical.bobbing.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.TickCallbacks;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Unique
    private static float verticalSpeed;

    @Unique
    private static float lastVerticalSpeed;

    static {
        TickCallbacks.preTick = () -> lastVerticalSpeed = verticalSpeed;
        TickCallbacks.postTick = () -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            Entity camEntity = mc.cameraEntity == null ? mc.player : mc.cameraEntity;

            if (camEntity == null) {
                return;
            }

            verticalSpeed = (float) (camEntity.getY() - camEntity.prevY);
        };
    }

    @Inject(method = "bobView", at = @At("TAIL"))
    private void postBobView(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        float deg = MathHelper.lerp(tickDelta, lastVerticalSpeed, verticalSpeed);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((deg * 0.5f - deg) * 5f));
    }

}
