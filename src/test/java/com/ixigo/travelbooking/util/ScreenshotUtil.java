
package com.ixigo.travelbooking.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private final WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public String takeScreenshot(String baseFileName) {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String safeName = baseFileName.replaceAll("[^a-zA-Z0-9-_]", "_");
        String timeStamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String finalFileName = safeName + "_" + timeStamp + ".png";

        String directory = System.getProperty("user.dir") + File.separator + "screenshots";
        String filePath = directory + File.separator + finalFileName;

        try {
            Path targetDir = Paths.get(directory);
            if (Files.notExists(targetDir)) {
                Files.createDirectories(targetDir);
            }
            Files.copy(source.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + finalFileName, e);
        }

        return filePath;
    }
}
