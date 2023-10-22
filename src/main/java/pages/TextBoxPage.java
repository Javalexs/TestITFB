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

public class TextBoxPage {
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

    public TextBoxPage(WebDriver chromeDriver){
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
}























