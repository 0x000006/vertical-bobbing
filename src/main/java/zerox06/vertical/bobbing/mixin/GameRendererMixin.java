package zerox06.vertical.bobbing.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import zerox06.vertical.bobbing.imixin.IPlayerEntityExtension;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "bobView", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;rotatef(FFFF)V", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    private void bobView(float tickDelta, CallbackInfo ci, PlayerEntity playerEntity, float f, float g, float h) {
        IPlayerEntityExtension playerExtension = (IPlayerEntityExtension) playerEntity;
        float i = MathHelper.lerp(tickDelta, playerExtension.getLastVerticalSpeed(), playerExtension.getVerticalSpeed());
        i = (i * 0.5f) - i;
        GlStateManager.rotatef(i * 5f, 1.0f, 0.0f, 0.0f);
    }

}
