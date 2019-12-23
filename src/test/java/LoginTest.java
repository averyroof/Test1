import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
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
            screenshots.takesScreenshot("error");
        }

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            url = "https://beru.ru";
            webDriver.get(url);
            screenshots = new Screenshots(webDriver);

            EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
            eventFiringWebDriver.register(new AbstractWebDriverEventListener() {
                @Override
                public void beforeClickOn(WebElement element, WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
                }
            });
            webDriver = eventFiringWebDriver;
        }

        @Override
        protected void finished(Description description) {
            webDriver.close();
        }
    };

    @Test
    public void autoTest() {

        MainPage mainPage = new MainPage(webDriver); // главная страница

        LoginPage loginPage = mainPage.goToLoginPage(); // нажатие на кнопку "Войти в аккаунт" для перехода к окну входа в профиль или регистрации
        loginPage.loginMethod(); // ввод логина
        loginPage.passwordMethod(); // ввод пароля (после, будет произведен автоматический переход на главную страницу)

        // перемещение курсора на кнопку "Мой профиль без нажатия" и получение текущей почты
        Assert.assertEquals("rogova.nataliya1999@yandex.ru", mainPage.myProfile()); // сравнение полученной текущей почты с введенной

        Assert.assertEquals("Мой профиль", mainPage.checkLogin()); // проверка что кнопка "Войти в аккаунт" сменилась на кнопку "Мой профиль"

    }


}
