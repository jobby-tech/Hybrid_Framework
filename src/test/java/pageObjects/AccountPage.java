package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{

    public AccountPage(WebDriver driver) {        super(driver);    }

    @FindBy (xpath = "//div[@id='content']/h2[text()='My Account']")    WebElement lblAcc;
    @FindBy(xpath = "//a[text()='Logout']") WebElement lnkLogout;

    public void clickLogout(){lnkLogout.click();}

    public boolean isMyAccPageExist(){
        try{
        return lblAcc.isDisplayed();}
        catch (Exception e){
            return false;
        }
    }
}
