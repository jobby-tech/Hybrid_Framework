package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC_AccountRegister extends BaseClass {

   @Test public void verifyPrivacyError(){

       logger.info("***** Starting TC_AccountRegister *****");

       try {

           HomePage hp = new HomePage(driver);
           hp.clickMyAcc();
           hp.clickRegister();

           RegisterPage rp = new RegisterPage(driver);
           rp.setFirstName(randomString() + "Fname");
           rp.setLastName("Lname");
           rp.clickContinueBtn();

           AccountPage ap = new AccountPage(driver);

         //  Assert.assertEquals(rp.getPrivacyErrorMesg(), "Success");

           if(rp.getPrivacyErrorMesg().equals("Welcome")){
               Assert.assertTrue(true);
           }
           else Assert.assertTrue(false);


       }

       catch (Exception e){
           logger.error("Test Failed");
           logger.debug("Debug logs..");
           Assert.fail();
       }



    }


}
