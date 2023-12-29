package net.momirealms.customfishing.expansion.effect;

import net.momirealms.customfishing.api.CustomFishingPlugin;
import net.momirealms.customfishing.api.manager.PlaceholderManager;
import net.momirealms.customfishing.api.mechanic.condition.Condition;
import net.momirealms.customfishing.expansion.ParticleAction;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import top.zoyn.particlelib.pobject.ParticleObject;
import top.zoyn.particlelib.pobject.Polygon;
import top.zoyn.particlelib.pobject.Sphere;

public class SphereEffect extends ParticleAction {

    private final double radius;
    private final int sample;

    public SphereEffect(boolean playerOrOther, double chance, String yExp, String xExp, String zExp, String yAxis, String xAxis, String zAxis, Particle particle, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable ItemStack data, @Nullable Color color, @Nullable Color toColor, float scale, double radius, int sample) {
        super(playerOrOther, chance, yExp, xExp, zExp, yAxis, xAxis, zAxis, particle, count, offsetX, offsetY, offsetZ, extra, data, color, toColor, scale);
        this.radius = radius;
        this.sample = sample;
    }

    @Override
    protected ParticleObject setProperties(Condition  condition) {
        PlaceholderManager manager = CustomFishingPlugin.getInstance().getPlaceholderManager();
        double y = manager.getExpressionValue(condition.getPlayer(), yExp, condition.getArgs());
        double z = manager.getExpressionValue(condition.getPlayer(), zExp, condition.getArgs());
        double x = manager.getExpressionValue(condition.getPlayer(), xExp, condition.getArgs());
        Location base = playerOrOther ? condition.getPlayer().getLocation() : condition.getLocation();
        Sphere circle = new Sphere(base.clone().add(x, y, z));
        super.initParticleObject(circle);
        circle.setRadius(radius);
        circle.setSample(sample);
        return circle;
    }
}
