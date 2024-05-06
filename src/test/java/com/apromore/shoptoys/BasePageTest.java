package com.apromore.shoptoys;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class BasePageTest {
    public static final String SRC_TEST_RESOURCES_TEMP_SCREENSHOT = "//src//test//resources//temp//screenshot_";
    public static final String SRC_TEST_RESOURCES_EXPECTED_SCREENSHOT = "//src//test//resources//expected//screenshot_";
    public WebDriver driver;

    ImageDiffer imgDiff;

    String currDir = System.getProperty("user.dir");

    @BeforeSuite
    public void setup() {
        //Create a Chrome driver. All test classes use this.
        System.setProperty("webdriver.chrome.driver", currDir + "\\" + "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        imgDiff = new ImageDiffer();
    }

    @AfterSuite
    public void teardown() {
        driver.quit();
    }

    public String compareImageAndReturnBase64EncodedImageString(String methodName, String stepName) throws IOException {
        BufferedImage expectedImage = ImageIO.read(new File(currDir + SRC_TEST_RESOURCES_EXPECTED_SCREENSHOT + methodName + "_" + stepName + ".png"));
        SoftAssertions assertions = new SoftAssertions();
        BufferedImage actualImage = ImageIO.read(new File(currDir + SRC_TEST_RESOURCES_TEMP_SCREENSHOT + methodName + "_" + stepName + ".png"));
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);
        assertions.assertThat(diff.hasDiff());
        BufferedImage joinedImg = joinBufferedImage(expectedImage, actualImage);

        return imgToBase64String(joinedImg, "png",methodName,stepName);
    }


    public static BufferedImage joinBufferedImage(BufferedImage img1,
                                                  BufferedImage img2) {
        int offset = 2;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        return newImage;
    }

    public static String imgToBase64String(final RenderedImage img, final String formatName,
                                           String methodName, String stepName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            File file = new File(System.getProperty("user.dir")
                    + SRC_TEST_RESOURCES_TEMP_SCREENSHOT + methodName + "_" + stepName + "_merged.png");
            ImageIO.write(img, formatName, file);
            return file.getAbsolutePath();
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public static void takeScreenShot(WebDriver driver, String methodName, String stepName) throws IOException {
        String currDir = System.getProperty("user.dir");
        String screenshotFilePath = currDir + SRC_TEST_RESOURCES_TEMP_SCREENSHOT + methodName + "_" + stepName + ".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(screenshotFilePath));
    }
}
