package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basket {
    WebDriver webdriver;

    public Basket(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @Step("Проверить цену")
    public boolean checkPrice() {
        WebElement products = webdriver.findElement(By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]"));
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(products)));
        WebElement final_ = webdriver.findElement(By.cssSelector("div[data-auto=\"total-price\"] span._1oBlNqVHPq"));
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(final_)));
        return products.getText().equals(final_.getText());
    }

    @Step("Переход к странице с оформление заказа")
    public Delivery goToRegistration() {

        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.or(ExpectedConditions.stalenessOf(webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/CartTotalInformation\"] div[data-apiary-widget-name=\"@marketplace/CartCheckoutControl\"] button"))),
                ExpectedConditions.elementToBeClickable(webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/CartTotalInformation\"] div[data-apiary-widget-name=\"@marketplace/CartCheckoutControl\"] button")))));
        webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/CartTotalInformation\"] div[data-apiary-widget-name=\"@marketplace/CartCheckoutControl\"] button")).click();
        return new Delivery(webdriver);
    }

    @Step("Добавить щетки в корзину")
    public void addToothBrushes(int finalPrice) {
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(webdriver.findElement(By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]")))));
        String p = webdriver.findElement(By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]")).getText();
        int price = Integer.parseInt(p.replaceAll("\\D", ""));
        for (int i = price; i <= finalPrice; i += price) {
            String prevPrice = webdriver.findElement(By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]")).getText();
            (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-auto=\"CartOffer\"] button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA"))).click();
            (new WebDriverWait(webdriver, 10)).until((ExpectedCondition<Boolean>) d -> !webdriver.findElement(By.cssSelector("div[data-auto=\"total-items\"] span[data-auto=\"value\"]")).getText().equals(prevPrice));
        }
    }
}
