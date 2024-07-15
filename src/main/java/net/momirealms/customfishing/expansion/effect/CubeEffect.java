package net.momirealms.customfishing.expansion.effect;

import net.momirealms.customfishing.api.BukkitCustomFishingPlugin;
import net.momirealms.customfishing.api.mechanic.context.Context;
import net.momirealms.customfishing.api.mechanic.context.ContextKeys;
import net.momirealms.customfishing.api.mechanic.misc.placeholder.PlaceholderManager;
import net.momirealms.customfishing.api.mechanic.misc.value.MathValue;
import net.momirealms.customfishing.expansion.ParticleAction;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import top.zoyn.particlelib.pobject.Cube;
import top.zoyn.particlelib.pobject.ParticleObject;

import static java.util.Objects.requireNonNull;

public class CubeEffect extends ParticleAction {

    private final String x2;
    private final String y2;
    private final String z2;
    private final double step;

    public CubeEffect(boolean playerOrOther, double chance, String yExp, String xExp, String zExp, String yAxis, String xAxis, String zAxis, Particle particle, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable ItemStack data, @Nullable Color color, @Nullable Color toColor, float scale, String x2, String y2, String z2, double step) {
        super(playerOrOther, chance, yExp, xExp, zExp, yAxis, xAxis, zAxis, particle, count, offsetX, offsetY, offsetZ, extra, data, color, toColor, scale);
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.step = step;
    }

    @Override
    protected ParticleObject setProperties(Context context) {
        Location base = playerOrOther ? ((Player) context.getHolder()).getLocation() : (Location) requireNonNull(context.arg(ContextKeys.OTHER_LOCATION));
        PlaceholderManager manager = BukkitCustomFishingPlugin.getInstance().getPlaceholderManager();
        double dy1 = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), yExp, context.placeholderMap())).evaluate(context);
        double dz1 = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), zExp, context.placeholderMap())).evaluate(context);
        double dx1 = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), xExp, context.placeholderMap())).evaluate(context);
        double dy2 = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), y2, context.placeholderMap())).evaluate(context);
        double dz2 = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), z2, context.placeholderMap())).evaluate(context);
        double dx2 = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), x2, context.placeholderMap())).evaluate(context);
        Cube cube = new Cube(base.clone().add(dx1, dy1, dz1), base.clone().add(dx2, dy2, dz2));
        super.initParticleObject(cube);
        cube.setStep(step);
        return cube;
    }
}
