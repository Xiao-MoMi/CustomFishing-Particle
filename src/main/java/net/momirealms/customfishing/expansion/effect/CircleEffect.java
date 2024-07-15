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
import top.zoyn.particlelib.pobject.Circle;
import top.zoyn.particlelib.pobject.ParticleObject;

import static java.util.Objects.requireNonNull;

public class CircleEffect extends ParticleAction {

    private final double radius;
    private final double step;

    public CircleEffect(boolean playerOrOther, double chance, String yExp, String xExp, String zExp, String yAxis, String xAxis, String zAxis, Particle particle, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable ItemStack data, @Nullable Color color, @Nullable Color toColor, float scale, double radius, double step) {
        super(playerOrOther, chance, yExp, xExp, zExp, yAxis, xAxis, zAxis, particle, count, offsetX, offsetY, offsetZ, extra, data, color, toColor, scale);
        this.radius = radius;
        this.step = step;
    }

    @Override
    protected ParticleObject setProperties(Context context) {
        PlaceholderManager manager = BukkitCustomFishingPlugin.getInstance().getPlaceholderManager();
        double y = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), yExp, context.placeholderMap())).evaluate(context);
        double z = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), zExp, context.placeholderMap())).evaluate(context);
        double x = MathValue.expression(manager.parse((OfflinePlayer) context.getHolder(), xExp, context.placeholderMap())).evaluate(context);
        Location base = playerOrOther ? ((Player) context.getHolder()).getLocation() : (Location) requireNonNull(context.arg(ContextKeys.OTHER_LOCATION));
        Circle circle = new Circle(base.clone().add(x, y, z));
        super.initParticleObject(circle);
        circle.setRadius(radius);
        circle.setStep(step);
        return circle;
    }
}
