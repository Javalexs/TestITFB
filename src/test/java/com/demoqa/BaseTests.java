package com.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    protected WebDriver chromeDriver;

    /**
     * Метод открывает браузер на полный экран и инициализирует неявное ожидание
     * @author Алексей Фадеев
     */
    @BeforeEach
    public void before(){
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Метод закрывает браузер после теста
     * @author Алексей Фадеев
     */
    @AfterEach
    public void closeMarketTest(){
        chromeDriver.quit();
    }
}
