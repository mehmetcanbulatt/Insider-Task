package Main;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    @Before
    public void setUp(){

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://useinsider.com/");

    }


    @After
    public void tearDown() throws InterruptedException, IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = timeStamp + ".png";
        Files.createDirectories(Paths.get("src/main/resources/screenshots"));
        File target = new File("src/main/resources/screenshots/" +fileName);
        try {
            FileHandler.copy(source, target);
            System.out.println("Found error and Screenshot saved: " + target.getAbsolutePath());
        }
        catch (IOException e)
        { System.out.println("Error about saving a screenshot: " + e.getMessage());
        }

        driver.close();
        TimeUnit.SECONDS.sleep(3);
        driver.quit();
    }
}
