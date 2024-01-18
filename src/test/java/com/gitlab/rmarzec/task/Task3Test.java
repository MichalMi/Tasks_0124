package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.GooglePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task3Test {

    private GooglePage googlePage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        googlePage = new GooglePage(DriverFactory.getDriver());
    }

    @Test
    public void googleW3schoolsTest() throws InterruptedException{
        googlePage.goToGooglePage()
                .acceptGoogleCookies()
                .search("HTML select tag W3Schools")
                .clickLuckyButton()
                .acceptCookiesOnW3Schools()
                .clickFirstExampleLink()
                .switchToNewWindow()
                .waitForHeaderText("The select element")
                .printHeaderText()
                .switchToIFrame("iframeResult")
                .selectCar("Opel")
                .printSelectedCarInfo("Opel");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
