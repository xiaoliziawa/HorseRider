package mc.craig.software.horse;

import mc.craig.software.horse.mixin.AbstractHorseAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class HorseRider {
    public static final String MOD_ID = "riding_partners";

    public static float getOffset() {
        return -0.6F;
    }

    public static void positionRider(@NotNull AbstractHorse abstractHorse, Entity rider, Entity.MoveFunction moveFunction, CallbackInfo ci) {
        int riderIndex = abstractHorse.getPassengers().indexOf(rider);
        if (riderIndex == 1) {
            float kickUpCompensation = 0.20F * ((AbstractHorseAccessor)abstractHorse).getStandAnimO();
            float offset = HorseRider.getOffset();

            float yOffset = (float) (abstractHorse.getY() + abstractHorse.getPassengersRidingOffset() + rider.getMyRidingOffset() - (1.5 * kickUpCompensation));
            Vec3 vec3 = (new Vec3(0.0, 0.0, offset)).yRot(-abstractHorse.yBodyRot * 0.017453292F);

            if(kickUpCompensation > 0){
                vec3 = vec3.relative(abstractHorse.getDirection().getOpposite(), 0.5F);
            }

            moveFunction.accept(rider, abstractHorse.getX() + vec3.x, yOffset, abstractHorse.getZ() + vec3.z);

            if (rider instanceof LivingEntity livingEntity) {
                livingEntity.yBodyRot = abstractHorse.yBodyRot;
            }
            ci.cancel();
        }
    }


}
