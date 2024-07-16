package net.momirealms.customfishing.expansion;

import net.momirealms.customfishing.api.BukkitCustomFishingPlugin;
import net.momirealms.customfishing.api.mechanic.action.ActionExpansion;
import net.momirealms.customfishing.api.mechanic.action.ActionFactory;
import net.momirealms.customfishing.api.mechanic.config.GUIItemParser;
import net.momirealms.customfishing.api.mechanic.context.Context;
import net.momirealms.customfishing.expansion.effect.*;
import net.momirealms.customfishing.libraries.boostedyaml.block.implementation.Section;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class ParticleExpansion extends ActionExpansion<Player> {

    @Override
    public String getVersion() {
        return "1.1";
    }

    @Override
    public String getAuthor() {
        return "XiaoMoMi";
    }

    @Override
    public String getActionType() {
        return "particle";
    }

    @Override
    public ActionFactory<Player> getActionFactory() {
        return (args, chance) -> {
            if (!(args instanceof Section section)) {
                throw new RuntimeException("Invalid config format for particle action.");
            }
            String x1 = getString(section.get("x1", "0"));
            String y1 = getString(section.get("y1", "0"));
            String z1 = getString(section.get("z1", "0"));
            String xAxis = getString(section.get("xAxisRotation", "0"));
            String yAxis = getString(section.get("yAxisRotation", "0"));
            String zAxis = getString(section.get("zAxisRotation", "0"));
            Particle particle = Particle.valueOf(section.getString("particle", "").toUpperCase(Locale.ENGLISH));
            boolean playerOrOther = section.getString("pos", "other").equals("player");
            int count = section.getInt("count", 1);
            double offsetX = section.getDouble("offsetX", 0.0);
            double offsetY = section.getDouble("offsetY", 0.0);
            double offsetZ = section.getDouble("offsetZ", 0.0);
            double extra = section.getDouble("extra", 0.0);
            float scale = section.getFloat("scale", 1.0f);

            ItemStack itemStack;
            GUIItemParser parser = new GUIItemParser("particle", section.getSection("itemStake"), BukkitCustomFishingPlugin.getInstance().getConfigManager().getFormatFunctions());
            itemStack = parser.getItem().build(Context.player(null));

            Color color;
            if (section.contains("color")) {
                String[] rgb = section.getString("color","255,255,255").split(",");
                color = Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            } else {
                color = null;
            }

            Color toColor;
            if (section.contains("color")) {
                String[] rgb = section.getString("to-color","255,255,255").split(",");
                toColor = Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            } else {
                toColor = null;
            }

            ParticleAction particleAction = null;
            switch (section.getString("shape", "none")) {
                case "circle" -> {
                    double radius = section.getDouble("radius", 1.0);
                    double step = section.getDouble("step", 1.0);
                    particleAction = new CircleEffect(
                            playerOrOther,
                            chance,
                            y1,
                            x1,
                            z1,
                            yAxis,
                            xAxis,
                            zAxis,
                            particle,
                            count,
                            offsetX,
                            offsetY,
                            offsetZ,
                            extra,
                            itemStack,
                            color,
                            toColor,
                            scale,
                            radius,
                            step
                    );
                }
                case "line" -> {
                    String x2 = getString(section.get("x2", "0"));
                    String y2 = getString(section.get("y2", "0"));
                    String z2 = getString(section.get("z2", "0"));
                    double step = section.getDouble("step", 1.0);
                    particleAction = new LineEffect(
                            playerOrOther,
                            chance,
                            y1,
                            x1,
                            z1,
                            yAxis,
                            xAxis,
                            zAxis,
                            particle,
                            count,
                            offsetX,
                            offsetY,
                            offsetZ,
                            extra,
                            itemStack,
                            color,
                            toColor,
                            scale,
                            x2,
                            y2,
                            z2,
                            step
                    );
                }
                case "cube" -> {
                    String x2 = getString(section.get("x2", "0"));
                    String y2 = getString(section.get("y2", "0"));
                    String z2 = getString(section.get("z2", "0"));
                    double step = section.getDouble("step", 1.0);
                    particleAction = new CubeEffect(
                            playerOrOther,
                            chance,
                            y1,
                            x1,
                            z1,
                            yAxis,
                            xAxis,
                            zAxis,
                            particle,
                            count,
                            offsetX,
                            offsetY,
                            offsetZ,
                            extra,
                            itemStack,
                            color,
                            toColor,
                            scale,
                            x2,
                            y2,
                            z2,
                            step
                    );
                }
                case "arc" -> {
                    String angle = getString(section.get("angle", "180"));
                    String startAngle = getString(section.get("startAngle", "0"));
                    double radius = section.getDouble("radius", 1.0);
                    double step = section.getDouble("step", 1.0);
                    particleAction = new ArcEffect(
                            playerOrOther,
                            chance,
                            y1,
                            x1,
                            z1,
                            yAxis,
                            xAxis,
                            zAxis,
                            particle,
                            count,
                            offsetX,
                            offsetY,
                            offsetZ,
                            extra,
                            itemStack,
                            color,
                            toColor,
                            scale,
                            radius,
                            step,
                            angle,
                            startAngle
                    );
                }
                case "sphere" -> {
                    double radius = section.getDouble("radius", 3.0);
                    int sample = section.getInt("sample", 10);
                    particleAction = new SphereEffect(
                            playerOrOther,
                            chance,
                            y1,
                            x1,
                            z1,
                            yAxis,
                            xAxis,
                            zAxis,
                            particle,
                            count,
                            offsetX,
                            offsetY,
                            offsetZ,
                            extra,
                            itemStack,
                            color,
                            toColor,
                            scale,
                            radius,
                            sample
                    );
                }
                default -> BukkitCustomFishingPlugin.getInstance().getPluginLogger().warn("No valid shape is set for action: particle");
            }
            return particleAction;
        };
    }

    private String getString(Object o) {
        if (o instanceof String s) {
            return s;
        } else if (o instanceof Integer i) {
            return String.valueOf(i);
        } else if (o instanceof Double d) {
            return String.valueOf(d);
        }
        throw new IllegalArgumentException("Illegal expression format: " + o);
    }
}
