package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ToolsPage {
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

    public ToolsPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 60);
        this.action = new Actions(chromeDriver);
    }
    @Step("3.Нажать на «Text box»")
    public void pushTextBox(){
        chromeDriver.findElement(By.cssSelector("li[id='item-0']")).click();
    }

    @Step("4.Заполнить поля: Full Name, Email, Current Address, Permanent Address")
    public void inputData(String name, String email, String address1, String address2){
        chromeDriver.findElement(By.cssSelector("input[id='userName']")).sendKeys(name);
        chromeDriver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys(email);
        chromeDriver.findElement(By.cssSelector("textarea[id='currentAddress']")).sendKeys(address1);
        chromeDriver.findElement(By.cssSelector("textarea[id='permanentAddress']")).sendKeys(address2);
    }
    @Step("5.Нажать на кнопку «Submit»")
    public void pushSubmit(){
        chromeDriver.findElement(By.cssSelector("button[id='submit']")).click();
    }

    @Step("6.Проверить, что данные в блоке сохранены корректно")
    public void checkData(String name, String email, String address1, String address2){
        List<String> array = new ArrayList<>();
        List<String> values = Arrays.asList(name, email, address1, address2);
        List<WebElement> allElements = chromeDriver.findElements(By.cssSelector("p[class = 'mb-1']"));
        for(int i = 0; i < values.size(); i++){
            if(!allElements.get(i).getText().contains(values.get(i))) {
                array.add(values.get(i));
            }
        }
        Assertions.assertTrue(array.isEmpty(), "Данные в блоке сохранены корректно");
    }

    @Step("7.Нажать на «Buttons»")
    public void pushButtons(){
        chromeDriver.findElement(By.xpath("//span[text() = 'Buttons']")).click();
    }

    @Step("8.Нажать на кнопку «Click me»")
    public void pushClickMe(){
        chromeDriver.findElement(By.xpath("//button[text() = 'Click Me']")).click();
    }

    @Step("9.Проверить, что появился текст «You have done a dynamic click»")
    public void checkDynamicClick(){
        WebElement elementClickMe = chromeDriver.findElement(By.cssSelector("p[id = 'dynamicClickMessage']"));
        Assertions.assertTrue(elementClickMe.getText().equals("You have done a dynamic click"), "Текст появился");
    }

    @Step("10.Нажать на кнопку «Right Click me»")
    public void pushRightClickMe(){
        WebElement element = chromeDriver.findElement(By.xpath("//button[text() = 'Right Click Me']"));
        action.contextClick(element).perform();
    }

    @Step("11.Проверить, что появился текст «You have done a right click»")
    public void checkRightClick(){
        WebElement elementRightClick = chromeDriver.findElement(By.cssSelector("p[id = 'rightClickMessage']"));
        Assertions.assertTrue(elementRightClick.getText().equals("You have done a right click"), "Текст появился");
    }

    @Step("12.Нажать на кнопку «Double Click me»")
    public void pushDoubleClickMe(){
        WebElement element = chromeDriver.findElement(By.xpath("//button[text() = 'Double Click Me']"));
        action.doubleClick(element).perform();
    }

    @Step("13.Проверить, что появился текст «You have done a double click»")
    public void checkDoubleClick(){
        WebElement elementDoubleClick = chromeDriver.findElement(By.cssSelector("p[id = 'doubleClickMessage']"));
        Assertions.assertTrue(elementDoubleClick.getText().equals("You have done a double click"), "Текст появился");
    }

    @Step("14.Нажать на «Alerts, Frame &amp; Windows»")
    public void pushAlertsFrameWindows(){
        WebElement element = chromeDriver.findElement(By.xpath("//div[text() = 'Interactions']/ancestor::div[@class = 'element-group']"));
        action.moveToElement(element).build().perform();
        chromeDriver.findElement(By.xpath("//div[text() = 'Alerts, Frame & Windows']/ancestor::div[@class = 'element-group']")).click();
    }

    @Step("15.Нажать на «Browser Windows»")
    public void pushBrowserWindows(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Browser Windows']")));
        chromeDriver.findElement(By.xpath("//ul[@class = 'menu-list']/li/span[text() = 'Browser Windows']")).click();
    }

    @Step("16.Нажать на кнопку «New Tab»")
    public void pushNewTab(){
        WebElement newTab = chromeDriver.findElement(By.cssSelector("button[id='tabButton']"));
        newTab.click();
        mainWindow = chromeDriver.getWindowHandle();
        Set<String> tab = chromeDriver.getWindowHandles();
        for(String s : tab){
            chromeDriver.switchTo().window(s);
        }
    }

    @Step("17.Закрыть новую вкладку")
    public void closeNewTab(){
        chromeDriver.close();
        chromeDriver.switchTo().window(mainWindow);
    }

    @Step("18.Нажать на кнопку «New window»")
    public void pushNewWindow(){
        WebElement newTab = chromeDriver.findElement(By.cssSelector("button[id='windowButton']"));
        newTab.click();
        mainWindow = chromeDriver.getWindowHandle();
        Set<String> tab = chromeDriver.getWindowHandles();
        for(String s : tab){
            chromeDriver.switchTo().window(s);
        }
    }

    @Step("19.Закрыть новое окно")
    public void closeNewWindow(){
        chromeDriver.close();
        chromeDriver.switchTo().window(mainWindow);
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























