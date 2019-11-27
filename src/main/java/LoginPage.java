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

    public void loginMethod() {
        WebElement webElement = webdriver.findElement(By.cssSelector("#passp-field-login"));
        webElement.sendKeys("rogova.nataliya-1999");
        webElement.submit();
    }

    public void passwordMethod(){
        WebElement webElement1 = (new WebDriverWait(webdriver, 10))
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
