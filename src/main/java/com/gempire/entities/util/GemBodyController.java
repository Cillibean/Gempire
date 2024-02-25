package com.gempire.entities.util;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityFlyingRideableGem;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class GemBodyController extends BodyRotationControl {
    private final EntityFlyingRideableGem gem;

    public GemBodyController(EntityFlyingRideableGem gem) {
        super(gem);
        this.gem = gem;
    }

    @Override
    public void clientTick()
    {
        // sync the body to the yRot; no reason to have any other random rotations.
        gem.yBodyRot = gem.getYRot();

        // clamp head rotations so necks don't fucking turn inside out
        gem.yHeadRot = Mth.rotateIfNecessary(gem.yHeadRot, gem.yBodyRot, gem.getMaxHeadYRot());
    }
}
