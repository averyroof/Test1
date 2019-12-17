import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
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

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            Screenshots.takesScreenshot("error");
        }

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            url = "https://beru.ru";
            webDriver.get(url);
            screenshots = new Screenshots(webDriver);
        }

        @Override
        protected void finished(Description description) {
            webDriver.close();
        }
    };

    @Test
    @DataProvider(value = {"Саратов", "Хвалынск", "Энгельс"})
    public void autoTest(String s) {
            MainPage mainPage2 = new MainPage(webDriver);

            CityPage cityPage = mainPage2.city();
            cityPage.enterCity(s);
            cityPage.chooseCity(s);
            mainPage2 = cityPage.clickButtonSubmit();

            LoginPage loginPage = mainPage2.goToLoginPage();
            loginPage.loginMethod();
            loginPage.passwordMethod();

            mainPage2.moveCursorOnProfile();
            SettingsPage page3 = mainPage2.clickButtonSettings();
            Assert.assertEquals(page3.city(), page3.myCity());

    }
}
