package data;

public enum LanguageLevelData {

    BEGINNER("Начальный", "beginner"),
    INTERMEDIATE("Средний", "intermediate"),
    ADVANCED("Продвинутый", "advanced"),
    NATIVE("Носитель языка", "native");

    private final String russianName;
    private final String englishName;

    LanguageLevelData(String russianName, String englishName) {
        this.russianName = russianName;
        this.englishName = englishName;
    }

    public String getRussianName() {
        return russianName;
    }

    public String getEnglishName() {
        return englishName;
    }
}
