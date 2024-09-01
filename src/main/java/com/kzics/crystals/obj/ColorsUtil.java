package com.kzics.crystals.obj;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.function.Function;

public class ColorsUtil {

    public static Function<String,String> translate = (str)-> ChatColor.translateAlternateColorCodes('&',str);

    public static Function<List<String>, List<String>> translateList = (list) -> {
        list.replaceAll(t -> translate.apply(t));
        return list;
    };

    public static String gradientText(String text, String startColor, String endColor) {
        StringBuilder gradientText = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            String hexColor = interpolateColor(startColor, endColor, (float) i / (length - 1));
            gradientText.append(net.md_5.bungee.api.ChatColor.of(hexColor)).append(text.charAt(i));
        }
        return gradientText.toString();
    }

    private static String interpolateColor(String startHex, String endHex, float fraction) {
        int r1 = Integer.parseInt(startHex.substring(1, 3), 16);
        int g1 = Integer.parseInt(startHex.substring(3, 5), 16);
        int b1 = Integer.parseInt(startHex.substring(5, 7), 16);

        int r2 = Integer.parseInt(endHex.substring(1, 3), 16);
        int g2 = Integer.parseInt(endHex.substring(3, 5), 16);
        int b2 = Integer.parseInt(endHex.substring(5, 7), 16);

        int r = (int) (r1 + (r2 - r1) * fraction);
        int g = (int) (g1 + (g2 - g1) * fraction);
        int b = (int) (b1 + (b2 - b1) * fraction);

        return String.format("#%02X%02X%02X", r, g, b);
    }


}
