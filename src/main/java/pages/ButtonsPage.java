package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonsPage{
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

    public ButtonsPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 60);
        this.action = new Actions(chromeDriver);
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
}
