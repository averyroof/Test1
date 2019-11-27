import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        Page1 page1 = mainPage.method1();
        page1.loginMethod();
        page1.passwordMethod();
        //mainPage = page1.telephoneMethod();
        // pam param
        Assert.assertEquals("rogova.nataliya-1999@yandex.ru", mainPage.myProfile());

        Assert.assertEquals("Мой профиль", mainPage.checkLogin());
    }

    @After
    public void tearDown() {

    }
}
