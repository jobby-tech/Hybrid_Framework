package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;

    public Properties prop;
    @BeforeClass
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException {
        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()){
            case "chrome": driver = new ChromeDriver();break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            default: System.out.println("Incorrect browser");return;
        }

        //Loading Config.properties File
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        prop=new Properties();
        prop.load(file);



        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("appURL"));  //Reading URL from Properties File

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


    public String randomString(){
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return  generatedString;
    }
}
