package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CityPage {
    WebDriver webdriver;

    public CityPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @Step("Ввод названия города")
    public void enterCity(String city) {
        WebElement webElement = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("div._1U2ErCeoqP  input[data-tid=\"37e0ab2d\"]")));
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        for (char c : city.toCharArray()) {
            webElement.sendKeys(String.valueOf(c));
        }
    }

    @Step("Выбор города")
    public void chooseCity() {
        String city = "Хвалынск";
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.attributeToBe(By.cssSelector("li#react-autowhatever-region--item-0 div._229JDbp_Z8"), "innerText", city));
        webdriver.findElement(By.cssSelector("li#react-autowhatever-region--item-0 div._229JDbp_Z8")).click();
    }

    @Step("Подтверждение выбора")
    public MainPage clickButtonSubmit() {
        WebElement webElement = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("button[data-tid='71e1c78d']._4qhIn2-ESi.Pjv3h3YbYr.THqSbzx07u")));
        webElement.click();
        return new MainPage(webdriver);
    }
}
