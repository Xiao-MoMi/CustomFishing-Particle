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
import top.zoyn.particlelib.pobject.Cube;
import top.zoyn.particlelib.pobject.Line;
import top.zoyn.particlelib.pobject.ParticleObject;

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
    protected ParticleObject setProperties(Condition condition) {
        Location base = playerOrOther ? condition.getPlayer().getLocation() : condition.getLocation();
        PlaceholderManager manager = CustomFishingPlugin.getInstance().getPlaceholderManager();
        double dy1 = manager.getExpressionValue(condition.getPlayer(), yExp, condition.getArgs());
        double dz1 = manager.getExpressionValue(condition.getPlayer(), zExp, condition.getArgs());
        double dx1 = manager.getExpressionValue(condition.getPlayer(), xExp, condition.getArgs());
        double dy2 = manager.getExpressionValue(condition.getPlayer(), y2, condition.getArgs());
        double dz2 = manager.getExpressionValue(condition.getPlayer(), z2, condition.getArgs());
        double dx2 = manager.getExpressionValue(condition.getPlayer(), x2, condition.getArgs());
        Cube cube = new Cube(base.clone().add(dx1, dy1, dz1), base.clone().add(dx2, dy2, dz2));
        super.initParticleObject(cube);
        cube.setStep(step);
        return cube;
    }
}
