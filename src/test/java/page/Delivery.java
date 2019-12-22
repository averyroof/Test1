package page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Delivery {
    WebDriver webdriver;

    public Delivery(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @Step("Проверка цены")
    public void checkPrice() {
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.elementToBeClickable(webdriver.findElement(By.cssSelector("div[data-auto=\"DELIVERY\"]")))).click();
        String str = webdriver.findElement(By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]")).getText();
        int totalItemsPrice = Integer.parseInt(str.replaceAll("\\D", "")); // \\D = не цифры
        str = webdriver.findElement(By.cssSelector("div[data-auto = 'total-delivery'] span[data-auto=\"value\"]")).getText();
        int totalDeliveryPrice = Integer.parseInt(str.replaceAll("\\D", ""));
        str = webdriver.findElement(By.cssSelector("div[data-auto = \"total-price\"] span._1oBlNqVHPq")).getText();
        int summaryPrice = Integer.parseInt(str.replaceAll("\\D", ""));
        Assert.assertEquals(totalItemsPrice + totalDeliveryPrice, summaryPrice);
    }


    @Step(value = "Переход обратно в корзину")
    public Basket backToCart() {
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.elementToBeClickable(webdriver.findElement(By.cssSelector("a[href=\"/my/cart\"]._3ioN70chUh._3Uc73lzxcf")))).click();
        return new Basket(webdriver);
    }
}
