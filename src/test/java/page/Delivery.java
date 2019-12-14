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
    public void checkPrice(){
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.elementToBeClickable(webdriver.findElement(By.xpath("//div[contains(@data-auto, 'DELIVERY')]")))).click();
        String str = webdriver.findElement(By.xpath("//div[@data-auto = 'total-items']")).findElement(By.xpath(".//span[@data-auto = 'value']")).getText();
        int totalItemsPrice = priceToInt(str);

        int totalDiscount = 0;
        if (webdriver.findElement(By.xpath("//div[@data-zone-name='CheckoutSummaryBox']")).getAttribute("innerHTML").contains("total-discount")) {
            str = webdriver.findElement(By.xpath("//div[@data-auto='total-discount']")).findElement(By.xpath(".//span[@data-tid='52906e8d']"))
                    .getText();

            totalDiscount -= priceToInt(str);
        }

        str = webdriver.findElement(By.xpath("//div[@data-auto = 'total-delivery']")).findElement(By.xpath("(.//span[@data-auto = 'value'])")).getText();
        int totalDeliveryPrice = str.matches("[\\d\\s]+.") ? priceToInt(str) : 0;

        str = webdriver.findElement(By.xpath("//div[contains(@data-auto, 'total-price')]")).findElement(By.className("_1oBlNqVHPq")).getText();
        int summaryPrice = priceToInt(str);

        Assert.assertEquals(totalItemsPrice - totalDiscount + totalDeliveryPrice, summaryPrice);
    }

    private Integer priceToInt(String price) {
        return Integer.parseInt(price.substring(0, price.length() - 2).replaceAll("\\s+", ""));
    }

    @Step(value = "Переход обратно в корзину")
    public Basket backToCart() {
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.elementToBeClickable(webdriver.findElement(By.xpath("//a[contains(@href, 'cart')]")))).click();
        return new Basket(webdriver);
    }
}
