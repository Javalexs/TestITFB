package com.demoqa;


import helpers.Properties;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;

public class Tests extends BaseTests {
    /**
     * Тестовый метод в рамках которого выполняется весь сценарий
     * @author Алексей Фадеев
     */
    @Feature("Проверка функций на сайте demoqa.com")
    @DisplayName("Проверка различных функций на сайте demoqa.com для параметров")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerParameters")
    public void testDemoqa(String name, String email, String address1, String address2){
        MainPage mainPage = new MainPage(chromeDriver);
        mainPage.openSite(Properties.testsProperties.demoqaComUrl());
        mainPage.pushElements();
        TextBoxPage textBoxPage = new TextBoxPage(chromeDriver);
        textBoxPage.pushTextBox();
        textBoxPage.inputData(name, email, address1, address2);
        textBoxPage.pushSubmit();
        textBoxPage.checkData(name, email, address1, address2);
        ButtonsPage buttonsPage = new ButtonsPage(chromeDriver);
        buttonsPage.pushButtons();
        buttonsPage.pushClickMe();
        buttonsPage.checkDynamicClick();
        buttonsPage.pushRightClickMe();
        buttonsPage.checkRightClick();
        buttonsPage.pushDoubleClickMe();
        buttonsPage.checkDoubleClick();
        WindowsPage windowsPage = new WindowsPage(chromeDriver);
        windowsPage.pushAlertsFrameWindows();
        windowsPage.pushBrowserWindows();
        windowsPage.pushNewTab();
        windowsPage.closeNewTab();
        windowsPage.pushNewWindow();
        windowsPage.closeNewWindow();
        AlertsPage alertsPage = new AlertsPage(chromeDriver);
        alertsPage.pushAlerts();
        alertsPage.pushClickMeAlert();
        alertsPage.closeAlert();
        alertsPage.pushClickMeAlertClock();
        alertsPage.closeAlertClock();
        alertsPage.pushClickConfirm();
        alertsPage.pushOk();
        alertsPage.checkSelectedOk();
        alertsPage.pushClickPrompt();
        alertsPage.enterTextTestName();
        alertsPage.checkTextTestName();

    }
}
