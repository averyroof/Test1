import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

    static WebDriver webDriver;

    public Screenshots(WebDriver webDriver) {
        Screenshots.webDriver = webDriver;
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public byte[] takesScreenshot(String screenshotName) {
        return ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES); // параметр - получить скриншот как набор байт
    }
}
