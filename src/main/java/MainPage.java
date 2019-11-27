import org.openqa.selenium.By;
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

    public LoginPage method1() {
        webdriver.findElement(By.cssSelector("a._3ioN70chUh._1FEpprw_Km._3Uc73lzxcf")).click();
        return new LoginPage(webdriver);
    }

    public String myProfile() {
        moveCursorOnProfile();
        (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.visibilityOf(webdriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_"))));
        WebElement webElement2 = webdriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_"));
        return webElement2.getText();
    }

    public void moveCursorOnProfile() {
        WebElement signInButton2 = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("button._1FEpprw_Km")));
        //(new WebDriverWait(webdriver, 10)).until(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(signInButton2), ExpectedConditions.visibilityOf(signInButton2)));
        new Actions(webdriver).moveToElement(signInButton2).perform();
        signInButton2.click();
    }

    public String checkLogin() {
        WebElement webElement3 = webdriver.findElement(By.cssSelector("span.pFhTbV17qj"));
        return webElement3.getText();
    }

    public CityPage city() {
        webdriver.findElement(By.cssSelector("span[data-auto='region-form-opener']._2XJ6yiRp5w")).click();
        return new CityPage(webdriver);
    }

    public SettingsPage clickButtonSettings() {
        WebElement webElement = (new WebDriverWait(webdriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("a[href='/my/settings?track=menu']")));
        webElement.click();
        return new SettingsPage(webdriver);
    }

}
