package Main;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.basic.BasicSliderUI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }


    public WebElement findToElement(By locator) {
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public void sendKeysToElement(By locator, String text) {

        findToElement(locator).sendKeys(text);
    }

    public void clickToElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    public void hoverElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findToElement(by)).build().perform();
    }

    public void scrollByPixel(int pixel) {


        js.executeScript("window.scrollBy(0,"+pixel+")");
    }

    public void scrollDownToButton(By by) {
        //Actions actions = new Actions(driver);
        //actions.moveToElement(findToElement(by)).perform();
        js.executeScript("arguments[0].scrollIntoView(true);",findToElement(by));
    }

    public String getTextElement(By locator) {
        String elementText = findToElement(locator).getText();
        System.out.println("Element Text Value: " + elementText);
        return elementText;
    }

    public void waitSecond(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getTextControl(By by, String text) {
        WebElement element = findToElement(by);
        String jobInfo = element.getText();
        System.out.println("Element Text Value: " + jobInfo);
        Assert.assertEquals("Info is not same with text!", jobInfo, text);
        System.out.println("Info is same with text!");
    }

    public void tabButtonClick(){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://useinsider.com/");
    }

    public void getLinkControl(String text) {
        String pageLink = driver.getCurrentUrl();
        System.out.println("Page Link: " + pageLink);
        Assert.assertTrue("Text is not equal to page link!", pageLink.contains(text));
        System.out.println("Text is equal to page link!");
    }

    List<WebElement> allProducts;

    public void selectRandomProduct(By by){
        try {
            allProducts = driver.findElements(by);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            Thread.sleep(1000);
            Random random = new Random();

            for(int i=0;i<4;i++){
                int randomProduct  = random.nextInt(allProducts.size()-1);
                executor.executeScript("arguments[0].click();", allProducts.get(randomProduct));
                allProducts.remove(randomProduct);
            }
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
}


