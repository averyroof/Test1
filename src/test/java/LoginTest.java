import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;
import page.MainPage;

import java.util.concurrent.TimeUnit;

public class LoginTest {
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
    public void autoTest() {

        MainPage mainPage = new MainPage(webDriver);

        LoginPage loginPage = mainPage.goToLoginPage();
        loginPage.loginMethod();
        loginPage.passwordMethod();

        Assert.assertEquals("rogova.nataliya-1999@yandex.ru", mainPage.myProfile());

        Assert.assertEquals("Мой профиль", mainPage.checkLogin());

    }
}
