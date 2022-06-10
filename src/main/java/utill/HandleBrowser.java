package utill;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

import java.util.Set;

public class HandleBrowser {
    private WebDriver driver;
    private String currentURL;
    private Set<Cookie> cookies;

    public HandleBrowser(WebDriver driver){
        this.driver = driver;
    }

    public void getCurrentURL(){
        currentURL = driver.getCurrentUrl();
    }

    public void closeTheBrowser(){
        driver.quit();
    }

    public void openBrowser(){
        driver = new ChromeDriver();
    }

    public void setCookies() {
        cookies = driver.manage().getCookies();
    }

    public void getCookies() {
        for (Cookie cookie:cookies){
            driver.manage().addCookie(cookie);
        }
    }

    public WebDriver reopenBrowser(){
        getCurrentURL();
        setCookies();
        closeTheBrowser();
        openBrowser();

        driver.navigate().to(currentURL);

        return driver;

    }
}
