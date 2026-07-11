package com.ixigo.travelbooking.util;


import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowUtils {

 public static String switchToNewTabAfterClick(WebDriver driver, By clickLocator, Duration timeout) {
     WebDriverWait wait = new WebDriverWait(driver, timeout);

     Set<String> oldHandles = driver.getWindowHandles();
  //   WebElement element = wait.until(ExpectedConditions.elementToBeClickable(clickLocator));
   //  element.click();
     WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(clickLocator));
     
     JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
       //element.click();

     wait.until(ExpectedConditions.numberOfWindowsToBe(oldHandles.size() + 1));

     return switchToNewHandle(driver, oldHandles);
 }

 public static String switchToNewTabAfterAction(WebDriver driver, Runnable action, Duration timeout) {
     WebDriverWait wait = new WebDriverWait(driver, timeout);

     Set<String> oldHandles = driver.getWindowHandles();
     action.run();

     wait.until(ExpectedConditions.numberOfWindowsToBe(oldHandles.size() + 1));

     return switchToNewHandle(driver, oldHandles);
 }

 public static void switchBackToParent(WebDriver driver, String parentHandle) {
     driver.switchTo().window(parentHandle);
 }

 public static void closeCurrentTabAndSwitchBack(WebDriver driver, String parentHandle) {
     driver.close();
     driver.switchTo().window(parentHandle);
 }

 private static String switchToNewHandle(WebDriver driver, Set<String> oldHandles) {
     for (String handle : driver.getWindowHandles()) {
         if (!oldHandles.contains(handle)) {
             driver.switchTo().window(handle);
             return handle;
         }
     }
     throw new RuntimeException("No new tab/window found.");
 }
}

