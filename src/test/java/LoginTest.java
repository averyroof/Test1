import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;
import page.MainPage;

import java.util.concurrent.TimeUnit;

public class LoginTest {
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
    public void autoTest() {
        try {
            MainPage mainPage = new MainPage(webDriver);

            LoginPage loginPage = mainPage.goToLoginPage();
            loginPage.loginMethod();
            loginPage.passwordMethod();

            Assert.assertEquals("rogova.nataliya-1999@yandex.ru", mainPage.myProfile());

            Assert.assertEquals("Мой профиль", mainPage.checkLogin());
        }catch (Exception e){
            Screenshots.takesScreenshot("Error");
        }
    }
    @After
    public void after(){
        webDriver.close();
    }
}
