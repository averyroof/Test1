import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class class1 {

    private WebDriver webDriver;
    private String url;

    @Before
    public void start() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\chromedriver.exe");
        System.setProperty("webdriver.firefox.driver", "/usr/bin/geckodriver");
        webDriver = new FirefoxDriver();
        url = "https://beru.ru";
        webDriver.get(url);
    }

    @Test
    public void autoTest1() {
        MainPage mainPage = new MainPage(webDriver);

        LoginPage loginPage = mainPage.method1();
        loginPage.loginMethod();
        loginPage.passwordMethod();
        //mainPage = page1.telephoneMethod();

        Assert.assertEquals("rogova.nataliya-1999@yandex.ru", mainPage.myProfile());

        Assert.assertEquals("Мой профиль", mainPage.checkLogin());
    }

    @Test
    public void autoTest2() {
        MainPage mainPage2 = new MainPage(webDriver);

        CityPage cityPage = mainPage2.city();
        cityPage.enterCity();
        cityPage.chooseCity();
        mainPage2 = cityPage.clickButtonSubmit();

        LoginPage loginPage = mainPage2.method1();
        loginPage.loginMethod();
        loginPage.passwordMethod();
        //mainPage = page1.telephoneMethod();

        mainPage2.moveCursorOnProfile();
        SettingsPage page3 = mainPage2.clickButtonSettings();
        Assert.assertEquals(page3.city(), page3.myCity());
    }

    @After
    public void tearDown() {

    }
}
