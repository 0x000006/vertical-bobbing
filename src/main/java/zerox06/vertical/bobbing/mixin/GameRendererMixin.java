package zerox06.vertical.bobbing.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.imixin.LivingEntityExtension;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(method = "bobView", at = @At(value = "TAIL"))
    private void postBobView(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        LivingEntityExtension lee = (LivingEntityExtension) MinecraftClient.getInstance().getCameraEntity();

        if (lee == null) {
            return;
        }

        float rot = MathHelper.lerp(tickDelta, lee.vB$getPrevRot(), lee.vB$getRot());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rot));
    }

}
