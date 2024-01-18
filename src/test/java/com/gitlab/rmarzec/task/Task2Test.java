package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.WikipediaPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2Test {

    private WikipediaPage wikipediaPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        wikipediaPage = new WikipediaPage();
    }

    @Test
    public void wikipediaTest() throws InterruptedException{
        wikipediaPage.goToWikipediaPage()
                .clickLanguageButton()
                .printDistinctLanguages();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
