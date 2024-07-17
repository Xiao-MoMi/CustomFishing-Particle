package net.momirealms.customfishing.expansion.effect;

import net.momirealms.customfishing.api.mechanic.context.Context;
import net.momirealms.customfishing.api.mechanic.context.ContextKeys;
import net.momirealms.customfishing.api.mechanic.misc.value.MathValue;
import net.momirealms.customfishing.expansion.ParticleAction;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import top.zoyn.particlelib.pobject.ParticleObject;
import top.zoyn.particlelib.pobject.Sphere;

import static java.util.Objects.requireNonNull;

public class SphereEffect extends ParticleAction {

    private final double radius;
    private final int sample;
    private final MathValue<Player> yMathValue;
    private final MathValue<Player> zMathValue;
    private final MathValue<Player> xMathValue;

    public SphereEffect(boolean playerOrOther, double chance, String yExp, String xExp, String zExp, String yAxis, String xAxis, String zAxis, Particle particle, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable ItemStack data, @Nullable Color color, @Nullable Color toColor, float scale, double radius, int sample) {
        super(playerOrOther, chance, yExp, xExp, zExp, yAxis, xAxis, zAxis, particle, count, offsetX, offsetY, offsetZ, extra, data, color, toColor, scale);
        this.radius = radius;
        this.sample = sample;
        this.yMathValue = MathValue.auto(yExp);
        this.zMathValue = MathValue.auto(zExp);
        this.xMathValue = MathValue.auto(xExp);
    }

    @Override
    protected ParticleObject setProperties(Context<Player> context) {
        double y = yMathValue.evaluate(context);
        double z = zMathValue.evaluate(context);
        double x = xMathValue.evaluate(context);
        Location base = playerOrOther ? context.getHolder().getLocation() : requireNonNull(context.arg(ContextKeys.OTHER_LOCATION));
        Sphere circle = new Sphere(base.clone().add(x, y, z));
        super.initParticleObject(circle);
        circle.setRadius(radius);
        circle.setSample(sample);
        return circle;
    }
}
