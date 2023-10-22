package helpers;

import io.qameta.allure.Step;

public class Assertions {
    /**
     * Метод для переопределения Assert JUnit
     * @author Алексей Фадеев
     */
    @Step("Проверяем, что тест прошел без ошибок: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}
