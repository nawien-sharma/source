package swip.ch04examiningapage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=htmlunit", "browserName=firefox"})
public class CssShorthandIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void cssShortHandBackground() throws Exception {
        driver.get(baseUrl + "/styled-elements.html");

        WebElement div = driver.findElement(By.id("shorthand"));

        assertEquals("rgba(0, 0, 0, 0) none repeat scroll 0% 0% / auto padding-box border-box", div.getCssValue("background"));
    }
}