package zerox06.vertical.bobbing.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.imixin.LivingEntityExtension;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityExtension {

    @Unique
    float prevRot; // field_6286

    @Unique
    float rot; // field_6223

    @Inject(method = "baseTick", at = @At("TAIL"))
    void postBaseTick(CallbackInfo ci) {
        this.prevRot = this.rot;
    }

    @Override
    public float vB$getPrevRot() {
        return prevRot;
    }

    @Override
    public float vB$getRot() {
        return rot;
    }

    @Override
    public void vB$setRot(float value) {
        rot = value;
    }
}
