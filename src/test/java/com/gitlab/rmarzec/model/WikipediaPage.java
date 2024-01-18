package com.gitlab.rmarzec.model;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WikipediaPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WikipediaPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, 10);
    }

    public WikipediaPage goToWikipediaPage() {
        driver.get("https://pl.wikipedia.org/wiki/Wiki");
        return this;
    }

    public WikipediaPage clickLanguageButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='p-lang-btn-checkbox']")));
        driver.findElement(By.xpath("//input[@id='p-lang-btn-checkbox']")).click();
        return this;
    }

    public void printDistinctLanguages() {
        List<WebElement> listOfLanguages = driver.findElements(By.xpath("//div[@class='grid uls-menu uls-wide']//ul/li"));
        listOfLanguages.stream()
                .map(webElement -> webElement.getAttribute("title"))
                .distinct()
                .forEach(languageName -> System.out.println("Language: " + languageName));
    }
}
