package net.pttheta.loveandwar.blocks.stamping;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.oriented.OrientedData;
import com.mojang.math.Axis;
import com.simibubi.create.content.kinetics.base.ShaftInstance;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.pttheta.loveandwar.blocks.LAWPartialModels;
import org.joml.Quaternionf;

public class StampingPressInstance extends ShaftInstance<StampingPressBlockEntity> implements DynamicInstance {
    private final OrientedData pressHead;

    public StampingPressInstance(MaterialManager materialManager, StampingPressBlockEntity blockEntity) {
        super(materialManager, blockEntity);

        pressHead = materialManager.defaultSolid()
                .material(Materials.ORIENTED)
                .getModel(LAWPartialModels.STAMPING_PRESS_HEAD, blockState)
                .createInstance();

        Quaternionf q = Axis.YP
                .rotationDegrees(AngleHelper.horizontalAngle(blockState.getValue(MechanicalPressBlock.HORIZONTAL_FACING)));

        pressHead.setRotation(q);

        transformModels();
    }

    @Override
    public void beginFrame() {
        transformModels();
    }

    private float getRenderedHeadOffset(StampingPressBlockEntity press) {
        StampingBehaviour drawingBehaviour = press.getStampingBehaviour();
        return drawingBehaviour.getRenderedHeadOffset(AnimationTickHolder.getPartialTicks())
                * drawingBehaviour.mode.headOffset;
    }

    private void transformModels() {
        float renderedHeadOffset = getRenderedHeadOffset(blockEntity);

        pressHead.setPosition(getInstancePosition())
                .nudge(0, -renderedHeadOffset, 0);
    }

    @Override
    public void updateLight() {
        super.updateLight();

        relight(pos, pressHead);
    }

    @Override
    public void remove() {
        super.remove();
        pressHead.delete();
    }
}
