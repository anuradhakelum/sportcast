package testcases;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utill.HandleBrowser;

public class LoginPage_Test extends BaseTest {

    //Verify if user can login using correct username and password(assuming username = davidwillson and password = david@123)
    @Test
    public void test_login_with_correct_details(){
        loginPage.enterUserName("davidwillson");
        loginPage.enterPassword("david@123");
        HomePage homePage = loginPage.clickLoginButton();

        String name = homePage.getusersName();

        Assert.assertEquals(name,"David Willson");
    }

    // Verify that user cannot login with correct username and incorrect password
    @Test
    public void test_login_with_incorrect_password(){
        loginPage.enterUserName("davidwillson");
        loginPage.enterPassword("david123");
        loginPage.clickLoginButton();

        String valiSummary = loginPage.getValidationSummary();

        Assert.assertEquals(valiSummary,"Invalid login attempt.");
    }

    @Test
    public void test_login_with_special_characters(){
        loginPage.enterUserName("\"@!#$%^&*\"");
        loginPage.enterPassword("\"@!#$%^&*\"");
        loginPage.clickLoginButton();

        String valiSummary = loginPage.getValidationSummary();

        Assert.assertEquals(valiSummary,"Invalid login attempt.");
    }

    //Verify that user cannot login with incorrect username and correct password
    @Test
    public void test_login_with_incorrect_userName(){
        loginPage.enterUserName("davidwillsn");
        loginPage.enterPassword("david@123");
        loginPage.clickLoginButton();

        String valiSummary = loginPage.getValidationSummary();

        Assert.assertEquals(valiSummary,"Invalid login attempt.");
    }

    //Verify that user cannot login with blank username and blank password
    @Test
    public void test_login_with_blank_details(){
        loginPage.enterUserName("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        String requiredUserNameError = loginPage.getErrorRequiredUserName();
        String requiredUserPwordError = loginPage.getErrorRequiredPassword();


        Assert.assertEquals(requiredUserNameError,"The User Name field is required.");
        Assert.assertEquals(requiredUserPwordError,"The Password field is required.");


    }

    //Verify that password field is hidden
    @Test
    public void test_password_is_hidden(){
        boolean isHidden = loginPage.passwordIsHidden();

        Assert.assertTrue(isHidden);
    }

    //Verify that "Remember me" function working correctly
    // Assuming that when user login to system with "remember me" ticked
    // after user close the browser and again go to website
    // Browser will go to website without need to entering the user credentials
    @Test
    public void test_remember_me(){
        WebDriver driver;
        loginPage.enterUserName("davidwillson");
        loginPage.enterPassword("david@123");
        loginPage.clickRemeberMe();

        HomePage homePage = loginPage.clickLoginButton();
        String name = homePage.getusersName();

        HandleBrowser handleBrowser = homePage.reopenBrowser();
        driver = handleBrowser.reopenBrowser();
        HomePage homePage1 = new HomePage(driver);
        String name1 =homePage1.getusersName();
        driver.quit();

        Assert.assertEquals(name,name1);

    }

    //Verify that "Remember me" function working correctly
    // Assuming that when user login to system without "remember me" ticked
    // after user close the browser and again go to website
    // Browser will go to login page
    @Test
    public void test_remember_me_negative(){
        WebDriver driver;
        loginPage.enterUserName("davidwillson");
        loginPage.enterPassword("david@123");

        HomePage homePage = loginPage.clickLoginButton();
        String name = homePage.getusersName();

        HandleBrowser handleBrowser = homePage.reopenBrowser();
        driver = handleBrowser.reopenBrowser();
        loginPage = new LoginPage(driver);
        String title = loginPage.getTitle();
        driver.quit();
        Assert.assertEquals(title,"Sportcast Live");//Hope Home page and other pages have different name

    }

    //Verify that user receives an email after filling forgot password
    @Test
    public void test_forgot_password(){
        loginPage.clickForgotPassword();
        loginPage.enterReUserName("davidwillson");
        loginPage.clickGetEmailLink();
        String message = loginPage.getConfirmationGetLink();

        Assert.assertEquals(message,"Thank you,");
    }

}
