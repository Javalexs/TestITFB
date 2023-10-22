package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class WindowsPage {
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

    public WindowsPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 60);
        this.action = new Actions(chromeDriver);
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
}
