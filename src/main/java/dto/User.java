package dto;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;

public class User {

    private final Faker faker = new Faker();
    private final String name = faker.name().firstName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();
    private final LocalDate data = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    String[] options = {"Начальный", "Средний", "Продвинутый", "Носитель языка"};
    private final String englishLevel = faker.options().option(options);

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getData() {
        return data;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }
}
