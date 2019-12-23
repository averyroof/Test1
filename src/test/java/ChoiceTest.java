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
    public TestWatcher testWatcher = new TestWatcher() { // TestWatcher позволяет переопределить методы JUNIT, которые вызываются автоматически при прохождении теста

        @Override
        protected void failed(Throwable e, Description description) {
            screenshots.takesScreenshot("error");
        }

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\chromedriver.exe");
            webDriver = new ChromeDriver();
            // неявные ожидания (устанавливается один раз в коде вне операции и действительно до изменения)
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // применяется ко всем последующим операциям поиска неявно
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // ожидание времени загрузки страницы
            webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS); // ожидание выполнения JAVASCRIPT
            webDriver.manage().window().maximize();
            url = "https://beru.ru";
            webDriver.get(url); // открытие тестируемого сайта
            screenshots = new Screenshots(webDriver);

            EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver); // класс EventFiringWebDriver представляет собой обертку для любой реализации веб-драйвера и позволяет регистрировать объект WebDriverEventListener
            eventFiringWebDriver.register(new AbstractWebDriverEventListener() { // все методы, предоставляемые AbstractWebDriverEventListener, пустые (не содержат кода). Поэтому можно переопределять не все, а только интересующие методы
                @Override
                public void beforeClickOn(WebElement element, WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) webDriver; // интерфейс JavascriptExecutor предоставляет метод executeScript
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
                }
            });
            webDriver = eventFiringWebDriver;
        }

        @Override
        protected void finished(Description description) {
            webDriver.close(); // закрытие браузера по окончанию теста
        }
    };

    @Test
    public void autoTest() {
        MainPage mainPage = new MainPage(webDriver); // главная страница сайта
        mainPage.clickCatalogProducts(); // нажатие на кнопку "Каталог товаров"
        mainPage.moveCursorOnCatalog(); // наведение курсора на кнопку "Красота и гигиена" в выпадающем меню

        Toothbrushes toothbrushes = mainPage.clickProduct(); // переход на страницу с электрическими зубными щетками нажатием кнопки "Электрические зубные щетки"
        toothbrushes.setPrice(); // ввод максимальной и минимальной цены в полях ценового диапазона на странице с зубными щетками
        // Assert.assertTrue принимает аргумент boolean и проверяет что он имеет значение true. Если нет, генерируется AssertionError
        Assert.assertTrue("Не соответствует диапазону", toothbrushes.checkPrice(999, 1999)); // проверка соответствия цен всех щеток заданному диапазону
        toothbrushes.addToBasket(); // нажатие на кнопку "В корзину" для добавления щетки в корзину

        Basket basket_ = toothbrushes.clickToBasket(); // нажатие на кнопку "В корзине" для перехода в корзину с добавленной щеткой
        Assert.assertTrue("Не соответствует цене", basket_.checkPrice()); // проверка соответствия цены товара с итоговой ценой в корзине

        Delivery delivery_ = basket_.goToRegistration(); // нажатие на кнопку "Перейти к оформлению" для учитывания доставки
        delivery_.checkPrice(); // сравнение итоговой цены заказа с ценой равной сумме цены товара и цены доставки курьером (при оформлении заказа)

        basket_ = delivery_.backToCart(); // нажатие на кнопку "Изменить" для возвращения в корзину
        basket_.addToothBrushes(2999); // добавление щеток в корзину до тех пор, пока итоговая цена заказа станет больше 2999 (в случае когда на складе больше 1 товара)
        Assert.assertTrue("Не соответствует цене", basket_.checkPrice()); // проверка соответствия цены товара с итоговой ценой в корзине
    }
}
