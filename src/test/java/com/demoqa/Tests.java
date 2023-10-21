package com.demoqa;


import helpers.Properties;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

public class Tests extends BaseTests {
    @Feature("Проверка функций на сайте demoqa.com")
    @DisplayName("Проверка различных функций на сайте demoqa.com для параметров")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerParameters")
    public void testMarket(String name, String email, String address1, String address2){
        MainPage mainPage = new MainPage(chromeDriver);
        mainPage.testMainMethod(Properties.testsProperties.demoqaComUrl(), chromeDriver, name, email, address1, address2);
    }
}
