package pages;

import org.openqa.selenium.WebDriver;
import utill.HandleBrowser;

public class HomePage {

    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getusersName() {
        return new String("David Willson");
    }

    public HandleBrowser reopenBrowser(){
        return new HandleBrowser(driver);

    }
}
