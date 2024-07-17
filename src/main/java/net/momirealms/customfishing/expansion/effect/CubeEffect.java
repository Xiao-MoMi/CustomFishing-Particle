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
import top.zoyn.particlelib.pobject.Cube;
import top.zoyn.particlelib.pobject.ParticleObject;

import static java.util.Objects.requireNonNull;

public class CubeEffect extends ParticleAction {

    private final MathValue<Player> x2MathValue;
    private final MathValue<Player> y2MathValue;
    private final MathValue<Player> z2MathValue;
    private final MathValue<Player> yMathValue;
    private final MathValue<Player> zMathValue;
    private final MathValue<Player> xMathValue;
    private final double step;

    public CubeEffect(boolean playerOrOther, double chance, String yExp, String xExp, String zExp, String yAxis, String xAxis, String zAxis, Particle particle, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable ItemStack data, @Nullable Color color, @Nullable Color toColor, float scale, String x2, String y2, String z2, double step) {
        super(playerOrOther, chance, yExp, xExp, zExp, yAxis, xAxis, zAxis, particle, count, offsetX, offsetY, offsetZ, extra, data, color, toColor, scale);
        this.x2MathValue = MathValue.auto(x2);
        this.y2MathValue = MathValue.auto(y2);
        this.z2MathValue = MathValue.auto(z2);
        this.yMathValue = MathValue.auto(yExp);
        this.zMathValue = MathValue.auto(zExp);
        this.xMathValue = MathValue.auto(xExp);
        this.step = step;
    }

    @Override
    protected ParticleObject setProperties(Context<Player> context) {
        Location base = playerOrOther ? context.getHolder().getLocation() : requireNonNull(context.arg(ContextKeys.OTHER_LOCATION));
        double dy1 = yMathValue.evaluate(context);
        double dz1 = zMathValue.evaluate(context);
        double dx1 = xMathValue.evaluate(context);
        double dy2 = y2MathValue.evaluate(context);
        double dz2 = z2MathValue.evaluate(context);
        double dx2 = x2MathValue.evaluate(context);
        Cube cube = new Cube(base.clone().add(dx1, dy1, dz1), base.clone().add(dx2, dy2, dz2));
        super.initParticleObject(cube);
        cube.setStep(step);
        return cube;
    }
}
