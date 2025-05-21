package utils;

import java.util.Map;


public class LanguageLevelUtils {
    private final Map<String, String> LEVEL_MAP = Map.of(
            "Начальный", "beginner",
            "Средний", "intermediate",
            "Продвинутый", "advanced",
            "Носитель языка", "native"
    );

    public String convert(String level) {
        return LEVEL_MAP.getOrDefault(level, "unknown");
    }
}
