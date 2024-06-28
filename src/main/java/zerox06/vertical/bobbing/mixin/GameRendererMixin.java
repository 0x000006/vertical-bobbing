package zerox06.vertical.bobbing.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import zerox06.vertical.bobbing.imixin.IPlayerEntityExtension;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "bobView", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void bobView(MatrixStack matrices, float tickDelta, CallbackInfo ci, PlayerEntity playerEntity, float f, float g, float h) {
        IPlayerEntityExtension playerExtension = (IPlayerEntityExtension) playerEntity;
        float i = MathHelper.lerp(tickDelta, playerExtension.getLastVerticalSpeed(), playerExtension.getVerticalSpeed());
        i = (i * 0.5f) - i;
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(i * 5f));
    }

}
