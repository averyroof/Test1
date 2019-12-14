package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Toothbrushes {
    WebDriver webdriver;

    public Toothbrushes(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @Step("Задание ценового диапазона")
    public void setPrice() {
        WebElement webElementMin = webdriver.findElement(By.xpath("//span[@data-auto = 'filter-range-min']//input"));
        WebElement webElementMax = webdriver.findElement(By.xpath("//span[@data-auto = 'filter-range-max']//input"));
        webElementMin.sendKeys("999");
        webElementMax.sendKeys("1999");
    }

    @Step("Добавить в корзину")
    public void addToBasket() {
        List<WebElement> buttons = webdriver.findElements(By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH.THqSbzx07u"));
        WebElement webElement = buttons.get(buttons.size() - 2);
        webElement.click();
    }

    @Step("Проверка цены")
    public boolean checkPrice(int priceFrom, int priceUp) {
        WebElement webElement = webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/SearchSerp\"]"));
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(webElement)));
        List<WebElement> prices = webElement.findElements(By.cssSelector("span._1u3j_pk1db._1pTV0mQZJz > span[data-tid='c3eaad93']:not(._3nXvrJWiZ0)"));
        for (WebElement price : prices) {
            int p = Integer.parseInt(price.getText().replaceAll(" ", ""));
            if (p < priceFrom || p > priceUp) {
                return false;
            }
        }
        return true;
    }

    @Step("Переход в корзину")
    public Basket clickToBasket() {
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/SearchSerp\"] a[href = '/my/cart']")));
        (new WebDriverWait(webdriver, 10)).until((ExpectedCondition<Boolean>) driver ->
                webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/SearchSerp\"] a[href = '/my/cart']")).getAttribute("innerHTML").contains("В корзине"));
        webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/SearchSerp\"] a[href = '/my/cart']")).click();
        return new Basket(webdriver);
    }
}
