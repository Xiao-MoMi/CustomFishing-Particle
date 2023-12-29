package net.momirealms.customfishing.expansion;

import net.momirealms.customfishing.api.CustomFishingPlugin;
import net.momirealms.customfishing.api.manager.PlaceholderManager;
import net.momirealms.customfishing.api.mechanic.action.Action;
import net.momirealms.customfishing.api.mechanic.condition.Condition;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import top.zoyn.particlelib.pobject.ParticleObject;
import top.zoyn.particlelib.utils.matrix.Matrixs;

public abstract class ParticleAction implements Action {

    protected final boolean playerOrOther;
    protected final double chance;
    protected final String yExp;
    protected final String xExp;
    protected final String zExp;
    protected final String yAxis;
    protected final String xAxis;
    protected final String zAxis;
    protected Particle particle;
    protected int count;
    protected double offsetX;
    protected double offsetY;
    protected double offsetZ;
    protected double extra;
    protected ItemStack data;
    protected Color color;
    protected Color toColor;
    protected float scale;

    public ParticleAction(
            boolean playerOrOther,
            double chance,
            String yExp,
            String xExp,
            String zExp,
            String yAxis,
            String xAxis,
            String zAxis,
            Particle particle,
            int count,
            double offsetX,
            double offsetY,
            double offsetZ,
            double extra,
            @Nullable ItemStack data,
            @Nullable Color color,
            @Nullable Color toColor,
            float scale
    ) {
        this.playerOrOther = playerOrOther;
        this.chance = chance;
        this.particle = particle;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.extra = extra;
        this.data = data;
        this.color = color;
        this.yExp = yExp;
        this.xExp = xExp;
        this.zExp = zExp;
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        this.zAxis = zAxis;
        this.toColor = toColor;
        this.scale = scale;
    }

    protected abstract ParticleObject setProperties(Condition condition);

    @Override
    public void trigger(Condition condition) {
        if (Math.random() < chance) {
            PlaceholderManager manager = CustomFishingPlugin.getInstance().getPlaceholderManager();
            var particle = setProperties(condition);
            particle.addMatrix(Matrixs.rotateAroundYAxis(manager.getExpressionValue(condition.getPlayer(), yAxis, condition.getArgs())));
            particle.addMatrix(Matrixs.rotateAroundXAxis(manager.getExpressionValue(condition.getPlayer(), xAxis, condition.getArgs())));
            particle.addMatrix(Matrixs.rotateAroundZAxis(manager.getExpressionValue(condition.getPlayer(), zAxis, condition.getArgs())));
            particle.show();
        }
    }

    protected void initParticleObject(ParticleObject object) {
        object.setParticle(particle);
        object.setCount(count);
        object.setOffsetX(offsetX);
        object.setOffsetY(offsetY);
        object.setOffsetZ(offsetZ);
        object.setExtra(extra);
        if (data != null)
            object.setData(data);
        if (color != null && toColor == null)
            object.setData(new Particle.DustOptions(color, scale));
        if (color != null && toColor != null) {
            object.setData(new Particle.DustTransition(color, toColor, scale));
        }
    }
}
