package com.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import setup.Keywords;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private static final String SCREENSHOT_DIR = "target/screenshots/";

    public static String capture(WebDriver driver,String testName) {
     
        try {
            File dir = new File(SCREENSHOT_DIR);
            if (!dir.exists()) dir.mkdirs();

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";

            File src = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(dir, fileName);

            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
}