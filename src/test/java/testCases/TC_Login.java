package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_Login extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class, groups = {"Sanity","dataDriven"})
    public void verifyLogin(String UserName, String Password,String Result){
        logger.info("Starting Login Test case");

        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            AccountPage ap = new AccountPage(driver);

            hp.clickMyAcc();
            hp.clickLogin();

            Thread.sleep(5000);
            lp.setTxtEmail(UserName);
            lp.setTxtPass(Password);
            lp.clickLogin();

            Assert.assertTrue(ap.isMyAccPageExist());

            hp.clickMyAcc();
            ap.clickLogout();

            System.out.println(Result);
        }
        catch (Exception e){
            Assert.fail();
        }
    }
}
