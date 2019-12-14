import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.CityPage;
import page.LoginPage;
import page.MainPage;
import page.SettingsPage;

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





    @After
    public void tearDown() {

    }
}
