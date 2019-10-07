import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class class1 {

    private WebDriver webDriver;
    private String url;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\chromedriver.exe");
        webDriver = new ChromeDriver();
        url = "https://beru.ru";
        webDriver.get(url);
    }

    @Test
    public void autoTest1() {
        webDriver.findElement(By.cssSelector("a._3ioN70chUh._1FEpprw_Km._3Uc73lzxcf")).click();
        WebElement webElement = webDriver.findElement(By.cssSelector("#passp-field-login"));
        webElement.sendKeys("rogova.nataliya-1999");
        webElement.submit();
        WebElement webElement1 = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("#passp-field-passwd")));
        webElement1.sendKeys("987820NNN");
        webElement1.submit();

        WebElement webElement_ = (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<WebElement>) d -> d.findElement(By.cssSelector("button._1FEpprw_Km")));
        webElement_.click();
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_"))));
        WebElement webElement2 = webDriver.findElement(By.cssSelector("div._2SFylIV5m5 span._3l-uEDOaBN._31ia1pw_G4._3HJsMt3YC_"));
        Assert.assertEquals("rogova.nataliya-1999@yandex.ru", webElement2.getText());

        WebElement webElement3 = webDriver.findElement(By.cssSelector("span.pFhTbV17qj"));
        Assert.assertEquals("Мой профиль", webElement3.getText());

    }

    @After
    public void tearDown() {

    }
}
