package com.gitlab.rmarzec.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public GooglePage goToGooglePage() {
        driver.navigate().to("https://www.google.com/");
        return this;
    }

    public GooglePage acceptGoogleCookies() {
        WebElement agreeButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Zaakceptuj wszystko']")));
        agreeButton.click();
        return this;
    }

    public GooglePage search(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("btnI")));
        return this;
    }

    public GooglePage clickLuckyButton() {
        WebElement luckyButton = driver.findElement(By.name("btnI"));
        luckyButton.click();
        return this;
    }

    public GooglePage acceptCookiesOnW3Schools() {
        WebElement agreeButton2 = driver.findElement(By.id("accept-choices"));
        agreeButton2.click();
        return this;
    }

    public GooglePage clickFirstExampleLink() {
        driver.findElement(By.xpath("//div[@class='w3-example']/a[1]")).click();
        return this;
    }

    public GooglePage switchToNewWindow() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        return this;
    }

    public GooglePage waitForHeaderText(String headerText) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(
                "//span[text()='" + headerText + "']"), headerText));
        return this;
    }

    public GooglePage printHeaderText() {
        WebElement headerElement = driver.findElement(By.xpath("//span[text()='The select element']"));
        System.out.println("Nagłówek: " + headerElement.getText());
        return this;
    }

    public GooglePage switchToIFrame(String iframeName) {
        driver.switchTo().frame(iframeName);
        return this;
    }

    public GooglePage selectCar(String carName) {
        WebElement selectElement = driver.findElement(By.id("cars"));
        selectElement.sendKeys(carName);
        return this;
    }

    public GooglePage printSelectedCarInfo(String selectedCar) {
        WebElement selectedOption = driver.findElement(By.cssSelector("option[value='" + selectedCar.toLowerCase() + "']"));
        System.out.println("Tekst: " + selectedOption.getText());
        System.out.println("Wartość 'value': " + selectedOption.getAttribute("value"));
        return this;
    }
}
