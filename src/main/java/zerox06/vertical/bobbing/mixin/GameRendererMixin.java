package zerox06.vertical.bobbing.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.imixin.LivingEntityExtension;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(method = "bobView", at = @At(value = "TAIL", shift = At.Shift.BEFORE))
    private void postBobView(MatrixStack matrices, float tickDelta, CallbackInfo ci, @Local PlayerEntity player) {
        LivingEntityExtension lee = (LivingEntityExtension) player;
        float rot = MathHelper.lerp(tickDelta, lee.vB$getPrevRot(), lee.vB$getRot());
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rot));
    }

}
