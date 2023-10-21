package pages;

import helpers.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static io.qameta.allure.Allure.step;

public class MainPage {
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

    public MainPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 60);
        this.action = new Actions(chromeDriver);
    }

    /**
     * Тестовый метод в рамках которого выполняется весь сценарий
     * @author Алексей Фадеев
     */
    public void testMainMethod(String url, WebDriver currentDriver, String name, String email, String address1, String address2){
        step("1.Переходим на сайт: " + url, () -> {
            chromeDriver = currentDriver;
            chromeDriver.get(url);
        });

        step("2.Нажать на «Elements»", () -> {
            chromeDriver.findElement(By.xpath("//h5[text() = 'Elements']")).click();
        });

        step("3.Нажать на «Text box»", () -> {
            chromeDriver.findElement(By.cssSelector("li[id='item-0']")).click();
        });

        step("4.Заполнить поля: Full Name, Email, Current Address, Permanent Address", () -> {
            chromeDriver.findElement(By.cssSelector("input[id='userName']")).sendKeys(name);
            chromeDriver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys(email);
            chromeDriver.findElement(By.cssSelector("textarea[id='currentAddress']")).sendKeys(address1);
            chromeDriver.findElement(By.cssSelector("textarea[id='permanentAddress']")).sendKeys(address2);
        });

        step("5.Нажать на кнопку «Submit»", () -> {
            chromeDriver.findElement(By.cssSelector("button[id='submit']")).click();
        });

        step("6.Проверить, что данные в блоке сохранены корректно", () -> {
            List<String> array = new ArrayList<>();
            List<String> values = Arrays.asList(name, email, address1, address2);
            List<WebElement> allElements = chromeDriver.findElements(By.cssSelector("p[class = 'mb-1']"));
            for(int i = 0; i < values.size(); i++){
                if(!allElements.get(i).getText().contains(values.get(i))) {
                    array.add(values.get(i));
                }
            }
            Assertions.assertTrue(array.isEmpty(), "Данные в блоке сохранены корректно");
        });

        step("7.Нажать на «Buttons»", () -> {
            chromeDriver.findElement(By.xpath("//span[text() = 'Buttons']")).click();
        });

        step("8.Нажать на кнопку «Click me»", () -> {
            chromeDriver.findElement(By.xpath("//button[text() = 'Click Me']")).click();
        });

        step("9.Проверить, что появился текст «You have done a dynamic click»", () -> {
            WebElement elementClickMe = chromeDriver.findElement(By.cssSelector("p[id = 'dynamicClickMessage']"));
            Assertions.assertTrue(elementClickMe.getText().equals("You have done a dynamic click"), "Текст появился");
        });

        step("10.Нажать на кнопку «Right Click me»", () -> {
            WebElement element = chromeDriver.findElement(By.xpath("//button[text() = 'Right Click Me']"));
            action.contextClick(element).perform();
        });

        step("11.Проверить, что появился текст «You have done a right click»", () -> {
            WebElement elementRightClick = chromeDriver.findElement(By.cssSelector("p[id = 'rightClickMessage']"));
            Assertions.assertTrue(elementRightClick.getText().equals("You have done a right click"), "Текст появился");
        });

        step("12.Нажать на кнопку «Double Click me»", () -> {
            WebElement element = chromeDriver.findElement(By.xpath("//button[text() = 'Double Click Me']"));
            action.doubleClick(element).perform();
        });

        step("13.Проверить, что появился текст «You have done a double click»", () -> {
            WebElement elementDoubleClick = chromeDriver.findElement(By.cssSelector("p[id = 'doubleClickMessage']"));
            Assertions.assertTrue(elementDoubleClick.getText().equals("You have done a double click"), "Текст появился");
        });

        step("14.Нажать на «Alerts, Frame &amp; Windows»", () -> {
            WebElement element = chromeDriver.findElement(By.xpath("//div[text() = 'Interactions']/ancestor::div[@class = 'element-group']"));
            Actions actions = new Actions(chromeDriver);
            actions.moveToElement(element).build().perform();
            chromeDriver.findElement(By.xpath("//div[text() = 'Alerts, Frame & Windows']/ancestor::div[@class = 'element-group']")).click();
        });

        step("15.Нажать на «Browser Windows»", () -> {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Browser Windows']")));
            chromeDriver.findElement(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Browser Windows']")).click();
        });

        step("16.Нажать на кнопку «New Tab»", () -> {
            WebElement newTab = chromeDriver.findElement(By.cssSelector("button[id='tabButton']"));
            newTab.click();
            mainWindow = chromeDriver.getWindowHandle();
            Set<String> tab = chromeDriver.getWindowHandles();
            for(String s : tab){
                chromeDriver.switchTo().window(s);
            }

        });

        step("17.Закрыть новую вкладку", () -> {
            chromeDriver.close();
            chromeDriver.switchTo().window(mainWindow);
        });

        step("18.Нажать на кнопку «New window»", () -> {
            WebElement newTab = chromeDriver.findElement(By.cssSelector("button[id='windowButton']"));
            newTab.click();
            mainWindow = chromeDriver.getWindowHandle();
            Set<String> tab = chromeDriver.getWindowHandles();
            for(String s : tab){
                chromeDriver.switchTo().window(s);
            }
        });

        step("19.Закрыть новое окно", () -> {
            chromeDriver.close();
            chromeDriver.switchTo().window(mainWindow);
        });

        step("20.Нажать на «Alerts»", () -> {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Alerts']")));
            chromeDriver.findElement(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Alerts']")).click();
        });

        step("21.Нажать на кнопку «Click me» рядом с Click Button to see alert", () -> {
            chromeDriver.findElement(By.cssSelector("button[id = 'alertButton']")).click();
        });

        step("22.Закрыть уведомление", () -> {
            alert = chromeDriver.switchTo().alert();
            alert.accept();
        });

        step("23.Нажать на кнопку «Click me» рядом с On button click, alert will appear after 5 seconds", () -> {
            chromeDriver.findElement(By.cssSelector("button[id = 'timerAlertButton']")).click();
            WebDriverWait waiting = new WebDriverWait(chromeDriver, 5);
            waiting.until(ExpectedConditions.alertIsPresent());
        });

        step("24.Закрыть уведомление", () -> {
            alert = chromeDriver.switchTo().alert();
            alert.accept();
        });

        step("25.Нажать на кнопку «Click me» рядом с On button click, confirm box will appear", () -> {
            chromeDriver.findElement(By.cssSelector("button[id = 'confirmButton']")).click();
        });

        step("26.Нажать на кнопку «Да» в уведомление", () -> {
            alert = chromeDriver.switchTo().alert();
            alert.accept();
        });

        step("27.Проверить, что появился текст You selected Ok", () -> {
            boolean isOk = chromeDriver.getPageSource().contains("You selected Ok");
            Assertions.assertTrue(isOk, "Текст появился");
        });

        step("28.Нажать на кнопку «Click me» рядом с On button click, prompt box will appear", () -> {
            chromeDriver.findElement(By.cssSelector("button[id = 'promtButton']")).click();
        });

        step("29.Заполнить поле в уведомление данными: Test name", () -> {
            alert = chromeDriver.switchTo().alert();
            alert.sendKeys("Test name");
            alert.accept();
        });

        step("30.Проверить, что появился текст You entered Test name", () -> {
            boolean isOk = chromeDriver.getPageSource().contains("You entered Test name");
            Assertions.assertTrue(isOk, "Текст появился");
        });
    }
}
