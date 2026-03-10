package com.interview.backend.util;
import java.util.Map;

public class ColorMapper {
    private static final Map<String, String> COLOR_MAP = Map.of(
            "1", "blau",
            "2", "grün",
            "3", "violett",
            "4", "rot",
            "5", "gelb",
            "6", "türkis",
            "7", "weiß"
    );

    public static String mapColor(String id) {
        return COLOR_MAP.getOrDefault(id.trim(), "unbekannt");
    }
}