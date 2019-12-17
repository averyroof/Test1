package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage {
    WebDriver webdriver;

    public SettingsPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public String city() {
        return webdriver.findElement(By.cssSelector("div[data-zone-name=\"SubHeader\"] span[data-auto='region-form-opener'] span[data-auto='region-form-opener']")).getAttribute("innerText");
    }

    public String myCity() {
        return webdriver.findElement(By.cssSelector("div.TyYugfiSCL._1sbUn_k_xX div[data-apiary-widget-name=\"@marketplace/RegionLink\"] span[data-tid=\"52906e8d\"]._3l-uEDOaBN.tdrs43E7Xn._3HJsMt3YC_")).getAttribute("innerText");
    }
}
