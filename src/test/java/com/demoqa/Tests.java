package com.demoqa;


import helpers.Properties;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.ToolsPage;

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
        ToolsPage toolsPage = new ToolsPage(chromeDriver);
        toolsPage.pushTextBox();
        toolsPage.inputData(name, email, address1, address2);
        toolsPage.pushSubmit();
        toolsPage.checkData(name, email, address1, address2);
        toolsPage.pushButtons();
        toolsPage.pushClickMe();
        toolsPage.checkDynamicClick();
        toolsPage.pushRightClickMe();
        toolsPage.checkRightClick();
        toolsPage.pushDoubleClickMe();
        toolsPage.checkDoubleClick();
        toolsPage.pushAlertsFrameWindows();
        toolsPage.pushBrowserWindows();
        toolsPage.pushNewTab();
        toolsPage.closeNewTab();
        toolsPage.pushNewWindow();
        toolsPage.closeNewWindow();
        toolsPage.pushAlerts();
        toolsPage.pushClickMeAlert();
        toolsPage.closeAlert();
        toolsPage.pushClickMeAlertClock();
        toolsPage.closeAlertClock();
        toolsPage.pushClickConfirm();
        toolsPage.pushOk();
        toolsPage.checkSelectedOk();
        toolsPage.pushClickPrompt();
        toolsPage.enterTextTestName();
        toolsPage.checkTextTestName();

    }
}
