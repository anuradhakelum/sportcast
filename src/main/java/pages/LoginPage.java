package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private By userName = By.id("login-username");
    private By password = By.id("login-password");
    private By checkbox = By.xpath("//*[@id=\"login-form\"]/form/div[1]/div[2]/div/label");
    private By loginButton = By.id("login-btn");
    private By forgotPassword = By.id("email-link");
    private By validationSummary = By.xpath("//*[@id=\"validation-summary\"]/ul/li");
    private By requiredUName = By.id("login-username-error");
    private By requiredPword = By.id("login-password-error");
    private By reUserName = By.xpath("//*[@id=\"forgot-password\"]//*[@id=\"login-username\"]");
    private By getEmail = By.xpath("//*[@id=\"forgot-password\"]//*[@id=\"login-btn\"]");
    private  By confirmationGetLink =
            By.xpath("//*[@id=\"forgot-password-response\"]/div/span[1]");
    private  By nameGetLink =
            By.xpath("//*[@id=\"forgot-password-response\"]/div/span[1]");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUserName(String userName_Text){
        driver.findElement(userName).sendKeys(userName_Text);
    }

    public void enterPassword(String password_Text){
        driver.findElement(password).sendKeys(password_Text);
    }

    public boolean passwordIsHidden(){
        return driver.findElement(password).getAttribute("type").equals("password");
    }

    public void clickRemeberMe(){
        driver.findElement(checkbox).click();
    }

    public HomePage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }

    public String getValidationSummary(){
        return driver.findElement(validationSummary).getText();
    }

    public String getErrorRequiredUserName(){
        return driver.findElement(requiredUName).getText();
    }

    public String getErrorRequiredPassword(){
        return driver.findElement(requiredPword).getText();
    }

    public void clickForgotPassword(){
        driver.findElement(forgotPassword).click();
    }

    public void enterReUserName(String username_text){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(reUserName));
        driver.findElement(reUserName).sendKeys(username_text);
    }

    public void clickGetEmailLink(){
        driver.findElement(getEmail).click();
    }

    public String getConfirmationGetLink(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(confirmationGetLink));
        String message =  driver.findElement(confirmationGetLink).getText();
        return message;
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
