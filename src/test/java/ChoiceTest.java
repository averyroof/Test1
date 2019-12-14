import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        } catch (Exception e) {
            Screenshots.takesScreenshot("error");
        }
    }

    @After
    public void after() {
        webDriver.close();
    }
}
