import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        mainPage.clickCatalogProducts();
        mainPage.moveCursorOnCatalog();
        Toothbrushes toothbrushes = mainPage.clickProduct();
        toothbrushes.setPrice();
        Assert.assertTrue(toothbrushes.checkPrice(999, 1999));
        toothbrushes.addToBasket();
        Basket basket_ = toothbrushes.clickToBasket();
        Assert.assertTrue("Плохо случилось в проверке цен", basket_.checkPrice());

        Delivery delivery_ = basket_.goToRegistration();
        delivery_.checkPrice();
        basket_ = delivery_.backToCart();
        basket_.addToothBrushes(2999);
        Assert.assertTrue("Sum prices doesn't equal total price", basket_.checkPrice());

    }
}
