package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {
    /**
     * Экземпляр класса драйвера
     * @author Алексей Фадеев
     */
    private WebDriver chromeDriver;

    /**
     * Переменная класса явного ожидания
     * @author Алексей Фадеев
     */
    private WebDriverWait wait;

    /**
     * Переменная класса явного ожидания
     * @author Алексей Фадеев
     */
    private Actions action;

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

    public AlertsPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 60);
        this.action = new Actions(chromeDriver);
    }

    @Step("20.Нажать на «Alerts»")
    public void pushAlerts(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Alerts']")));
        chromeDriver.findElement(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Alerts']")).click();
    }

    @Step("21.Нажать на кнопку «Click me» рядом с Click Button to see alert")
    public void pushClickMeAlert(){
        chromeDriver.findElement(By.cssSelector("button[id = 'alertButton']")).click();
    }

    @Step("22.Закрыть уведомление")
    public void closeAlert(){
        alert = chromeDriver.switchTo().alert();
        alert.accept();
    }

    @Step("23.Нажать на кнопку «Click me» рядом с On button click, alert will appear after 5 seconds")
    public void pushClickMeAlertClock(){
        chromeDriver.findElement(By.cssSelector("button[id = 'timerAlertButton']")).click();
        WebDriverWait waiting = new WebDriverWait(chromeDriver, 5);
        waiting.until(ExpectedConditions.alertIsPresent());
    }

    @Step("24.Закрыть уведомление")
    public void closeAlertClock(){
        alert = chromeDriver.switchTo().alert();
        alert.accept();
    }

    @Step("25.Нажать на кнопку «Click me» рядом с On button click, confirm box will appear")
    public void pushClickConfirm(){
        chromeDriver.findElement(By.cssSelector("button[id = 'confirmButton']")).click();
    }

    @Step("26.Нажать на кнопку «Да» в уведомление")
    public void pushOk(){
        alert = chromeDriver.switchTo().alert();
        alert.accept();
    }

    @Step("27.Проверить, что появился текст You selected Ok")
    public void checkSelectedOk(){
        boolean isOk = chromeDriver.getPageSource().contains("You selected Ok");
        Assertions.assertTrue(isOk, "Текст появился");
    }

    @Step("28.Нажать на кнопку «Click me» рядом с On button click, prompt box will appear")
    public void pushClickPrompt(){
        chromeDriver.findElement(By.cssSelector("button[id = 'promtButton']")).click();
    }

    @Step("29.Заполнить поле в уведомление данными: Test name")
    public void enterTextTestName(){
        alert = chromeDriver.switchTo().alert();
        alert.sendKeys("Test name");
        alert.accept();
    }

    @Step("30.Проверить, что появился текст You entered Test name")
    public void checkTextTestName(){
        boolean isOk = chromeDriver.getPageSource().contains("You entered Test name");
        Assertions.assertTrue(isOk, "Текст появился");
    }
}
