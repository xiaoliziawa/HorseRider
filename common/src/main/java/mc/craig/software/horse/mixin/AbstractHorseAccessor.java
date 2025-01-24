package mc.craig.software.horse.mixin;

import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractHorse.class)
public interface AbstractHorseAccessor {
    @Accessor("standAnimO")
    float getStandAnimO();
} 