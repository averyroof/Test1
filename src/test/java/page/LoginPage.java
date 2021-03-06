package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver webdriver;

    public LoginPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @Step("Ввод логина")
    public void loginMethod() {
        WebElement webElement1 = (new WebDriverWait(webdriver, 30))
            .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("#passp-field-login")));
        webElement1.sendKeys("rogova.nataliya1999@yandex.ru");
        webElement1.submit();
    }

    @Step("Ввод пароля")
    public void passwordMethod(){
        WebElement webElement1 = (new WebDriverWait(webdriver, 30))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("#passp-field-passwd")));
        webElement1.sendKeys("987820NNN");
        webElement1.submit();
    }

    public MainPage telephoneMethod() {
        WebElement webElement1 = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("#passp-field-phone")));
        webElement1.sendKeys("79297790907");
        webElement1.submit();
        return new MainPage(webdriver);
    }


}
