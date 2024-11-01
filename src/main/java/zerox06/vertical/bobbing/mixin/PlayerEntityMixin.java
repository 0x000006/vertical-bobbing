package zerox06.vertical.bobbing.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.imixin.LivingEntityExtension;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements LivingEntityExtension {

    protected PlayerEntityMixin() {
        super(null, null);
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setMovementSpeed(F)V", shift = At.Shift.AFTER))
    void afterSetMovementSpeed(CallbackInfo ci) {
        float f;
        if (!this.isOnGround() && this.getHealth() > 0.0f) {
            f = (float) (Math.atan(-this.getVelocity().y * (double) 0.2f) * 15.0);
        } else {
            f = 0.0f;
        }
        vB$setRot(vB$getRot() + (f - vB$getRot()) * 0.8f);
    }

}
