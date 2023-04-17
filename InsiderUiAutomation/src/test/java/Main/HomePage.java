package Main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public HomePage (WebDriver driver){
        super(driver);
    }


public void homePageMethod() throws InterruptedException {
    getLinkControl("https://useinsider.com/"); // Control website link
    clickToElement(By.id("wt-cli-accept-all-btn"));
    clickToElement(By.xpath("//span[.='More']")); //Click to More button
    clickToElement(By.xpath("//h5[.='Careers']")); //Click to Career button
    logger.info("Career page opened");
    TimeUnit.SECONDS.sleep(3);
    scrollByPixel(2400); // Scroll down until 'see all teams' button visible
    TimeUnit.SECONDS.sleep(3);
    clickToElement(By.xpath("//a[.='See all teams']")); //Click to all teams button
    scrollByPixel(2000); // Scroll down until 'quality assurance' button visible
    clickToElement(By.xpath("//h3[.='Quality Assurance']")); //Click to quality assurance
    clickToElement(By.xpath("//a[.='See all QA jobs']")); //Click to see all qa jobs
    logger.info("Quality Assurance page opened");
    TimeUnit.SECONDS.sleep(3);
    scrollDownToButton(By.id("select2-filter-by-location-container"));//Scroll down until 'filter' button visible
    clickToElement(By.id("select2-filter-by-location-container")); //Click to filter button
    clickToElement(By.xpath("//li[.='Istanbul, Turkey']")); //Select Istanbul option
    TimeUnit.SECONDS.sleep(3);
    getTextControl(By.xpath("//p[.='Senior Software Quality Assurance Engineer - Remote']"),
            "Senior Software Quality Assurance Engineer - Remote"); // Control position
    getTextControl(By.xpath("//div[@id='jobs-list']/div[1]//span[@class='position-department text-large font-weight-600 text-primary']"),
            "Quality Assurance"); //Control departmant
    getTextControl(By.xpath("//div[@id='jobs-list']/div[1]//div[@class='position-location text-large']"),
            "Istanbul, Turkey"); //Control location
    TimeUnit.SECONDS.sleep(3);
    scrollDownToButton(By.xpath("//h3[@class='mb-0']"));//Scroll down until 'apply now' button visible
    hoverElement(By.xpath("//a[@href='https://jobs.lever.co/useinsider/78ddbec0-16bf-4eab-b5a6-04facb993ddc']")); //Hover to apply now button
    clickToElement(By.xpath("//a[@href='https://jobs.lever.co/useinsider/78ddbec0-16bf-4eab-b5a6-04facb993ddc']")); // Click apply now button
    logger.info("Application page opened");



}

}
