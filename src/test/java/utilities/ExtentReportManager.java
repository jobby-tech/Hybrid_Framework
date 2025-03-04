package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        repName = "Test-Report-"+timeStamp+".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);

        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Regression Testing");
        sparkReporter.config().setTheme(Theme.DARK);


        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","OpenCart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Operating System", os);

        List<String > includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups",includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+" got successfully executed");
    }


    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,result.getName()+" got Failed");
        test.log(Status.INFO,result.getThrowable().getMessage());

        try {
            String impPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(impPath);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getTestClass().getName());
        test.log(Status.SKIP,result.getName()+" got Skipped");
        test.log(Status.INFO,result.getThrowable().getMessage());
    }


    public void onFinish(ITestContext testContext){
        extent.flush();  //This statement is sufficient for the report generation


        //To open the Report automatically
        String pathOfExtentReport = System.getProperty("user.dir")+"/reports"+repName;

        File extentReport =  new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        }

        catch (Exception e){
            e.printStackTrace();
        }



        //To Automatically send the report to email

        /*

        try {
            URL url = new URL("file:///"+System.getProperty("user.dir")+"/reports/"+repName);

            ImageHtmlEmail email =  new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("test@gmail.com","password"));
            email.setSSLOnConnect(true);
            email.setFrom("sender@gmail.com");
            email.setSubject("Test Results");
            email.setMsg("Please find the attachment");
            email.addTo("RecieverEmail@gmail.com");
            email.attach(url,"extent Report","Check Report");
            email.send();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        * */
    }


}
