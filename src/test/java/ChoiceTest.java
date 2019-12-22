import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import page.Basket;
import page.Delivery;
import page.MainPage;
import page.Toothbrushes;

import java.util.concurrent.TimeUnit;

public class ChoiceTest {
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
            System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // применяется ко всем последующим операциям поиска неявно
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // ожидание времени загрузки страницы
            webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS); // ожидание выполнения JAVASCRIPT
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
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCatalogProducts();
        mainPage.moveCursorOnCatalog();
        Toothbrushes toothbrushes = mainPage.clickProduct();
        toothbrushes.setPrice();
        Assert.assertTrue("Не соответствует диапазону", toothbrushes.checkPrice(999, 1999));
        toothbrushes.addToBasket();
        Basket basket_ = toothbrushes.clickToBasket();
        Assert.assertTrue("Не соответствует цене", basket_.checkPrice());
        Delivery delivery_ = basket_.goToRegistration();
        delivery_.checkPrice();
        basket_ = delivery_.backToCart();
        basket_.addToothBrushes(2999);
        Assert.assertTrue("Не соответствует цене", basket_.checkPrice());
    }
}
