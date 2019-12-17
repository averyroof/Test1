package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver webdriver;


    public MainPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @Step("Переход на страницу Мой профиль")
    public LoginPage goToLoginPage() {
//        WebElement webElement = (new WebDriverWait(webdriver, 10))
//                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/HeaderNotAuthUserBadge\"] a")));
//        (new WebDriverWait(webdriver, 30)).until(ExpectedConditions.elementToBeClickable(webElement));
        (new WebDriverWait(webdriver, 20)).until((ExpectedCondition<Boolean>) b -> {
            {
                try {
                    b.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/HeaderNotAuthUserBadge\"] a"));
                    return true;
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }
        });


        webdriver.findElement(By.cssSelector("div[data-apiary-widget-name=\"@marketplace/HeaderNotAuthUserBadge\"] a")).click();
        return new LoginPage(webdriver);
    }

    @Step("Проверка почты")
    public String myProfile() {
        moveCursorOnProfile();
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.visibilityOf(webdriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_"))));
        WebElement webElement2 = webdriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_"));
        return webElement2.getText();
    }

    @Step("Перемещение курсора на кнопку Мой профиль")
    public void moveCursorOnProfile() {
        WebElement signInButton2 = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("button._1FEpprw_Km")));
        while (!webdriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_")).isDisplayed())
            new Actions(webdriver).moveToElement(signInButton2).build().perform();
    }

    @Step("Проверка авторизации")
    public String checkLogin() {
        WebElement webElement3 = webdriver.findElement(By.cssSelector("span.pFhTbV17qj"));
        return webElement3.getText();
    }

    @Step("Открыть окно выбора города")
    public CityPage city() {
        webdriver.findElement(By.cssSelector("div[data-zone-name=\"SubHeader\"] span[data-auto='region-form-opener'] span[data-auto='region-form-opener']")).click();
        return new CityPage(webdriver);
    }

    @Step("Переход на страницу настроек")
    public SettingsPage clickButtonSettings() {
        WebElement webElement = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("a[href='/my/settings?track=menu']")));
        webElement.click();
        return new SettingsPage(webdriver);
    }

    @Step("Нажатие на кнопку каталог товаров")
    public void clickCatalogProducts() {
        WebElement webElement = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("span._3RM4_n5whA")));
        webElement.click();
    }

    @Step("Перемещение курсора на кнопку каталог товаров")
    public void moveCursorOnCatalog() {
        WebElement signInButton3 = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("a[title=\"Красота и гигиена\"] span._19FPGVzRi9")));
        do {
            new Actions(webdriver).moveToElement(signInButton3).build().perform();
        } while (!webdriver.findElement(By.cssSelector("a[title=\"Электрические зубные щетки\"] span._27Pcf7STDj")).isDisplayed());
    }

    @Step("Нажатие на кнопку Электрические зубные щетки")
    public Toothbrushes clickProduct() {
        WebElement webElement = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("a[title=\"Электрические зубные щетки\"] span._27Pcf7STDj")));
        webElement.click();
        return new Toothbrushes(webdriver);
    }

}
