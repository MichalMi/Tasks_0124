package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class Task4Test {

    public static Boolean isVisibleInViewport(WebElement element) {
        WebDriver driver = ((RemoteWebElement) element).getWrappedDriver();
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }

    public static void scrollToElement(JavascriptExecutor js, WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private String getText(Supplier<WebElement> element) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            try {
                return element.get().getText();
            } catch (NoSuchElementException e) {
                Thread.sleep(2_000);
            }
        }
        return "";
    }

    @Test
    public void youtubeTest() throws InterruptedException {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.manage().window().maximize();
        webDriver.get("https://www.youtube.com/");

        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(elementToBeClickable(By.xpath("//span[text()='Zaakceptuj wszystko']/../..//div[@class='yt-spec-touch-feedback-shape yt-spec-touch-feedback-shape--touch-response-inverse']")));
        WebElement acceptCookiesButton = webDriver.findElement(By.xpath("//span[text()='Zaakceptuj wszystko']/../..//div[@class='yt-spec-touch-feedback-shape yt-spec-touch-feedback-shape--touch-response-inverse']"));
        acceptCookiesButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ytd-rich-grid-media//div[@id='dismissible']/div[@id='thumbnail']/ytd-thumbnail[@loaded]")));
        webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        List<WebElement> videoTiles = webDriver.findElements(By.xpath("//ytd-rich-grid-media//div[@id='dismissible']"));

        List<YTTile> ytTilesList = new ArrayList<>();

        for (int i = 0; i < Math.min(12, videoTiles.size()); i++) {
            WebElement videoTile = videoTiles.get(i);
            if (!isVisibleInViewport(videoTile)) {
                scrollToElement(js, videoTile);
            }
            String title = videoTile.findElement(By.xpath(".//yt-formatted-string[@id='video-title']")).getText();
            String channel = videoTile.findElement(By.xpath(".//ytd-channel-name[@id='channel-name']")).getText();
            WebElement thumbnail = videoTile.findElement(By.xpath(".//ytd-thumbnail"));

            boolean isLiveVideo = thumbnail.getAttribute("is-live-video") != null;
            if (isLiveVideo) {
                ytTilesList.add(new YTTile(title, channel, "live"));
            } else {
                String length = getText(() -> videoTile.findElement(By.xpath(".//ytd-thumbnail//div[@id='time-status']//span[@id='text']")));
                ytTilesList.add(new YTTile(title, channel, length));
            }
        }

        for (YTTile ytTile : ytTilesList) {
            System.out.println("-------------------------------------");
            System.out.println("Tytuł: " + ytTile.getTitle());
            System.out.println("Channel: " + ytTile.getChannel());
            System.out.println("Duration: " + ytTile.getLength());

            webDriver.quit();
        }
    }
}
