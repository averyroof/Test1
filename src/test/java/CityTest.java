import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.CityPage;
import page.LoginPage;
import page.MainPage;
import page.SettingsPage;

import java.util.concurrent.TimeUnit;

@RunWith(DataProviderRunner.class)
public class CityTest {
    private WebDriver webDriver;
    private String url;
    private Screenshots screenshots;
    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        url = "https://beru.ru";
        webDriver.get(url);
        screenshots = new Screenshots(webDriver);
    }

    @Test
    @DataProvider(value = {"Саратов", "Хвалынск", "Энгельс"})
    public void autoTest(String s) {
        try {
            MainPage mainPage2 = new MainPage(webDriver);

            CityPage cityPage = mainPage2.city();
            cityPage.enterCity(s);
            cityPage.chooseCity();
            mainPage2 = cityPage.clickButtonSubmit();

            LoginPage loginPage = mainPage2.goToLoginPage();
            loginPage.loginMethod();
            loginPage.passwordMethod();

            mainPage2.moveCursorOnProfile();
            SettingsPage page3 = mainPage2.clickButtonSettings();
            Assert.assertEquals(page3.city(), page3.myCity());
        }catch(Exception e) {
            Screenshots.takesScreenshot("error");
        }
    }

    @After
    public void after() {
        webDriver.close();
    }
}
