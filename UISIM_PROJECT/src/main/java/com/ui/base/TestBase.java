package com.ui.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.checkerframework.checker.guieffect.qual.AlwaysSafe;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.beust.jcommander.Parameter;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
import com.ui.utilities.TestUtil;
import com.ui.utilities.screenShot;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
	// public static String br;

	public static ExtentReports report = new ExtentReports();
	public static ExtentSparkReporter spark = new ExtentSparkReporter("D:\\AutomationFiles\\Report\\TestAutomationReport " +new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(
			Calendar.getInstance().getTime()).toString() + ".html");
	public static ExtentTest test;
	public static WebDriver driver;
	public static Properties prop;

	public static WebDriverWait wait;

	// public static Explicit Wait wait1;

	public TestBase(WebDriver driver) {
		//wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

	}

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java"
					+ "\\com\\ui\\configuration\\config.properties");
			prop.load(ip);

			/*
			 * BatchProp=new Properties();
			 * 
			 * FileInputStream ip1=new
			 * FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java" +
			 * "\\com\\ui\\configuration\\BatchConfig.properties"); BatchProp.load(ip1);
			 */

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "browser" })

	public static void initialization(String browser) throws IOException, InterruptedException

	{
		browser = prop.getProperty("browsername");
		System.out.println("Running Browser is :" + browser);
		// String browser=prop.getProperty("browser");
		Runtime.getRuntime().exec("taskkill /F /IM edgedriver.exe /T");
 		Runtime.getRuntime().exec("taskkill /F /IM msedge.exe /T");
		

		if (browser.equalsIgnoreCase("chrome")) {
			// Process process = new ProcessBuilder("C:\\Program
			// Files\\Google\\Chrome\\Application\\chrome.exe","remote","debugging-port=9222","user-data-dir=D:\\chromeData").start();
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
			Thread.sleep(2000);
			Process p =  Runtime.getRuntime().exec("cmd /c chrome.bat", null, new File("C:\\Users\\sudarsana.kanthasamy\\Desktop"));
			// Process p = Runtime.getRuntime().exec("cmd /c chrome.bat", null, new
			// File(System.getProperty("user.dir")+"\\Driver\\chrome.bat"));
			Thread.sleep(3000);
			
			  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+
			  "\\Driver\\chromedriver.exe");
			/* 
			 * 
			 * Map<String, Object> prefs = new HashMap<String,Object>();
			 * prefs.put("profile.default_content_setting.popups", 0);
			 * prefs.put("download.default_directory",
			 * System.getProperty("user.dir")+"\\Download\\"); ChromeOptions options =new
			 * ChromeOptions(); //Map<String, Object> prefs = new HashMap<String,Object>();
			 * //prefs.put("profile.default_content_setting.popups", 0);
			 * 
			 * options.setExperimentalOption("prefs", prefs);
			 * 
			 * 
			 * options.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true); driver=new
			 * ChromeDriver(options);
			 * 
			 */
			// WebdriverManager
			/// driver = WebDriverManager.chromedriver().create();

			//WebDriverManager.chromedriver().setup();
			/*
			 * ChromeDriver driver = new ChromeDriver(); Capabilities cap =
			 * driver.getCapabilities(); Map<String, Object> mycap = cap.asMap();
			 * System.out.println(mycap);
			 */

			// Add the WebDriver proxy capability.

			ChromeOptions opt = new ChromeOptions();

//			EdgeOptions opt = new EdgeOptions();
			//opt.addArguments("--remote-allow-origins=*");
			opt.setExperimentalOption("debuggerAddress","localhost:9222 ");
			/// opt.setAcceptInsecureCerts(true);
			/// opt.setExperimentalOption("excludeSwitches", new
			/// String[]{"enable-automation"});
			/// opt.setCapability("chrome.switches",
			/// Arrays.asList("--ignore-certificate-errors"));
			driver = new ChromeDriver(opt);

		}

		else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\Driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if(browser.equals("edge")) {

			Thread.sleep(2000);

			Process p =  Runtime.getRuntime().exec("cmd /c edge.bat", null, new File("./Driver"));
			//Process p =  Runtime.getRuntime().exec("cmd /c edge.bat", null, new File("C:\\Users\\vikas.singh\\Desktop"));

			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			//options.setExperimentalOption("debuggerAddress", "localhost:9222 ");
			driver = new EdgeDriver(options);
			driver.get(prop.getProperty("applicationUrl"));
		}

		// edge

		// System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\Driver\\msedgedriver.exe");
		// driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

	}

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(@Optional("browser") String browser)
			throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		// public void LoginAccountProcess(@Optional("Abc")String name) throws
		// FileNotFoundException, IOException, InterruptedException {
		// System.out.println("Name of th login Id is ==>"+name);
		// Opening browser... Hitting URL...
		initialization(browser);
		//wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		

		driver.get(prop.getProperty("applicationUrl"));

		// ExtentTest test;
		// ExtentReports report;
		// report = new
		// ExtentReports(System.getProperty("user.dir")+"ExtentReportResults.html");
		// test = report.startTest("ExtentDemo");

		report.attachReporter(spark);

	}

	@AfterTest
	public void tearDownCT_ST() throws Exception {
		/*
		 * ExtentReports report = new
		 * ExtentReports(System.getProperty("user.dir")+"ExtentReportResults.html");
		 * 
		 * ExtentTest test = null; report.endTest(test); report.flush();
		 */

		// if(result.getStatus()==ITestResult.FAILURE) {
		// ;
		// }
		report.flush();
		driver.close();
		driver.quit();
	}

	// sleep methods
	public void sleep() throws InterruptedException {
		Thread.sleep(1000);
	}

	public void sleep(long value) throws InterruptedException {
		Thread.sleep(value);
	}

}
