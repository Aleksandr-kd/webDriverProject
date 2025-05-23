package dto;

import com.github.javafaker.Faker;
import data.LanguageLevelData;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class User {

    private final Faker faker = new Faker();
    private final String name = faker.name().firstName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();
    private final LocalDate dataFaker = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    private final String data = dataFaker.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private final LanguageLevelData languageLevel = faker.options().nextElement(LanguageLevelData.values());

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataFaker() {
        return dataFaker;
    }

    public String getPassword() {
        return password;
    }

    public String getData() {
        return data;
    }

    public LanguageLevelData getLanguageLevel() {
        return languageLevel;
    }
}
