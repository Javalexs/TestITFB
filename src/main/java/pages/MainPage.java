package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    /**
     * Экземпляр класса драйвера
     * @author Алексей Фадеев
     */
    private WebDriver chromeDriver;

    /**
     * Переменная главного окна при переключении вкладок
     * @author Алексей Фадеев
     */
    private String mainWindow;

    /**
     * Переменная класса всплывающего алерта
     * @author Алексей Фадеев
     */
    private Alert alert;

    public MainPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
    }

    @Step("1.Перейти на сайт: {url}")
    public void openSite(String url) {
        chromeDriver.get(url);
    }
    @Step("2.Нажать на «Elements»")
    public void pushElements(){
        chromeDriver.findElement(By.xpath("//h5[text() = 'Elements']")).click();
    }
}
