package zerox06.vertical.bobbing.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.imixin.IPlayerEntityExtension;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity implements IPlayerEntityExtension {

    @Unique
    private float verticalSpeed;

    @Unique
    private float lastVerticalSpeed;

    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        lastVerticalSpeed = getVerticalSpeed();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void postTick(CallbackInfo ci) {
        verticalSpeed = (float) getVelocity().y;
    }

    @Override
    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    @Override
    public float getLastVerticalSpeed() {
        return lastVerticalSpeed;
    }

}
