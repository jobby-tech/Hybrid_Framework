package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {        super(driver);    }

    @FindBy (id = "input-email")    WebElement txtEmail;
    @FindBy (id = "input-password") WebElement txtPass;
    @FindBy(xpath = "//input[@type='submit']") WebElement btnLogin;

    public void setTxtEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setTxtPass(String pass){
        txtPass.sendKeys(pass);
    }
    public void clickLogin(){
        btnLogin.click();
    }

}
