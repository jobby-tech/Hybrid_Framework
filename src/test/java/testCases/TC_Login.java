package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.DataProviders;

public class TC_Login extends BaseClass{

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
    public void verifyLogin(String email, String pass,String status){
        logger.info("Starting Login Test case");

        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            AccountPage ap = new AccountPage(driver);

            hp.clickMyAcc();
            hp.clickLogin();

            lp.setTxtEmail(email);
            lp.setTxtPass(pass);
            lp.clickLogin();

            Assert.assertTrue(ap.isMyAccPageExist());
            ap.clickLogout();
        }
        catch (Exception e){
            Assert.fail();
        }
    }
}
