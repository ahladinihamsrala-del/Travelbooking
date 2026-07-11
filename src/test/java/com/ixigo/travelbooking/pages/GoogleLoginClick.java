package com.ixigo.travelbooking.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleLoginClick {
    public static void clickGoogleLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By googleContainer = By.xpath("//div[@id='googleLogin']");
        By googleIframe = By.xpath("//div[@id='googleLogin']//iframe");

        wait.until(ExpectedConditions.visibilityOfElementLocated(googleContainer));

        try {
            WebElement container = wait.until(
                ExpectedConditions.elementToBeClickable(googleContainer)
            );
            container.click();
            return;
        } catch (Exception e) {
            // fallback to JS click
        }

        try {
            WebElement container = wait.until(
                ExpectedConditions.presenceOfElementLocated(googleContainer)
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", container);
            return;
        } catch (Exception e) {
            // fallback to iframe
        }

        WebElement frame = wait.until(
            ExpectedConditions.presenceOfElementLocated(googleIframe)
        );
        driver.switchTo().frame(frame);

        WebElement bodyClickTarget = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//body"))
        );
        bodyClickTarget.click();

        driver.switchTo().defaultContent();
    }
}
