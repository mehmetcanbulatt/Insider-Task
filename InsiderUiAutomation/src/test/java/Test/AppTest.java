package Test;

import Main.BaseTest;
import Main.HomePage;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance
        (TestInstance.Lifecycle.PER_CLASS)
public class AppTest extends BaseTest {
    HomePage homePage;

    @Test
    public void testApp() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.homePageMethod();

    }
}
