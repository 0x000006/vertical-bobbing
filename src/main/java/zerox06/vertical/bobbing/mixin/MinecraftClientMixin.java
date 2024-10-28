package zerox06.vertical.bobbing.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zerox06.vertical.bobbing.TickCallbacks;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void preTick(CallbackInfo ci) {
        TickCallbacks.preTick.run();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void postTick(CallbackInfo ci) {
        TickCallbacks.postTick.run();
    }

}
