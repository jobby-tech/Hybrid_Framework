package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{


    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "input-firstname") WebElement txtFirstName;
    @FindBy(id = "input-lastname") WebElement txtLastName;
    @FindBy(name = "agree") WebElement chkPrivacy;
    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]") WebElement lblPrivacyError;
    @FindBy(xpath = "//input[@type='submit']") WebElement btnContinue;

    public void setFirstName(String fName){
        txtFirstName.sendKeys(fName);
    }

    public void setLastName(String lName){
        txtLastName.sendKeys(lName);
    }

    public void clickPrivacyChkBox(){
        chkPrivacy.click();
    }

    public void clickContinueBtn(){btnContinue.click();}

    public String getPrivacyErrorMesg(){

        try {return (lblPrivacyError.getText()); }
        catch (Exception e){ return e.getMessage(); }
    }
}
