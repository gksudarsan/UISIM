package stepDefinitions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.net.URL;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.screenShot;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class commonStepDefinitions extends TestBase {

	public void login(String userName, String password) throws Exception {

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Thread.sleep(2000);
		screenShot("LoginPage", "Pass", "Home Page");
		try {
			loginPage.loginLink.click();
			Thread.sleep(5000);

//		test.log(Status.PASS, "User Launched website");
//		driver.navigate().refresh();
//		Thread.sleep(2000);
//		screenShot("AfterRefreshPage", "Pass", "AfterRefresh");
//		driver.navigate().to(driver.getCurrentUrl());
//		Thread.sleep(5000);
//		driver.get(driver.getCurrentUrl());
			Thread.sleep(5000);

			enterTextbox("Username", userName);
//			test.log(Status.PASS, "User entered Username");
			enterTextbox("Password", password);
//			test.log(Status.PASS, "User entered Password");


			sleep(2000);
			screenShot("LoginPage", "Pass", "Logged in with \"" + userName.toUpperCase() + "\"");
			
			
			//Add this to your TC after login function : test.log(Status.PASS, "Login with userRole is successful");
			
			Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();

			Thread.sleep(2000);

			screenShot("okPopUpButton", "Pass", "Clicked on Ok - PopUp button");

		//Thread.sleep(10000);
		}catch(Exception e) {}
		
		try {
		driver.navigate().refresh();
		Thread.sleep(2000);
		waitForLoadingIconToDisappear();
		driver.navigate().refresh();
		Thread.sleep(2000);
		waitForLoadingIconToDisappear();
		
		//Thread.sleep(2000);
		//waitForLoadingIconToDisappear();
		
//		loginPage.okPopUpButton.click();
		if (driver.findElements(By.xpath("//*[.=' OK '][@class='mat-button-wrapper']")).size() > 0) {
			loginPage.okPopUpButton.click();
//			Thread.sleep(3000);
			waitForLoadingIconToDisappear();
		}
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		waitForLoadingIconToDisappear();
		if (driver.findElements(By.xpath("//*[.=' OK '][@class='mat-button-wrapper']")).size() > 0) {
			sleep(2000);
			loginPage.okPopUpButton.click();
//			Thread.sleep(3000);
			waitForLoadingIconToDisappear();
		}
		}
		catch(Exception e) {}
		}
	
	// Methods
	public void enterTextbox(String xpathParameter, String value) {
		By element = By.xpath("//*[.='" + xpathParameter + "']//following::input[1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			forceClearText(ele);
			ele.sendKeys(value);
		} catch (final Exception e) {
		}
	}

	public void enterTextboxContains(String xpathParameter, String value) {
		By element = By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			forceClearText(ele);
			ele.sendKeys(value);
		} catch (final Exception e) {
		}
	}
	
	public void enterCommentBoxContains(String value) {
		driver.findElement(By.xpath("//*[@id='commentId']")).clear();
		driver.findElement(By.xpath("//*[@id='commentId']")).sendKeys(value);
	}
	
	public void clickHyperlink(String xpathParameter) {
		driver.findElement(By.xpath("//a[contains(@aria-label, '" + xpathParameter + "')]")).click();
	}

	public void clickButton(String xpathParameter) throws Exception {
		By element = By.xpath("//button[.='" + xpathParameter + "'][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		//try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			safeJavaScriptClick(ele);
			sleep(2000);
		//} catch (final Exception e) 
			{
		}
	}

	public void clickButtonContains(String xpathParameter) throws InterruptedException {	
		By element = By.xpath("//button[contains(.,'" + xpathParameter + "')][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		//try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			ele.click();
		//} catch (final Exception e) 
			{
		}
	}
	
	public void clickOnLink1(String xpathParameter) {
		driver.findElement(By.xpath("//a[contains(.,'" + xpathParameter + "')][1]")).click();
	}
	
	public void clickButtonContains(String xpathParameter, int value) {
		driver.findElement(By.xpath("(//button[contains(.,'" + xpathParameter + "')])[" + value + "]")).click();
	}

	public void clickMenu(String xpathParameter) {
		By element = By.xpath("//*[text()='" + xpathParameter + "'][@class!='hideview'][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			ele.click();
		} catch (final Exception e) {
		}
	}

	public void selectRadio(String xpathParameter) {
			By element = By.xpath("//*[contains(.,'" + xpathParameter + "')][@class='mat-radio-label']//preceding::*[@class='mdc-radio__native-control'][1]");

			final WebDriverWait wait = new WebDriverWait(driver, 10);
			try {
				WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//				highLightWebElement(driver, ele);
				safeJavaScriptClick(ele);
			} catch (final Exception e) {
			}
	}

	public void selectRadioQuestions(String xpathQuestions, String xpathParameter) {
		
		By element = By.xpath("//*[.='" + xpathQuestions + "']//following::*[contains(.,'" + xpathParameter
				+ "')][1]//preceding::input[2][@class='mdc-radio__native-control'][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			//safeJavaScriptClick(ele);
			ele.click();
		} catch (final Exception e) {
		}
	}

	public void populateListbox(String xpathParameter, String value) {
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "']//following::textarea[1]")).sendKeys(value);
	}

	public void selectDropdown(String xpathParameter, String value) {
		By element = By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-select[1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			safeJavaScriptClick(ele);
			driver.findElement(By.xpath("//*[contains(.,'" + value + "')][@class='mdc-list-item__primary-text']")).click();
		} catch (final Exception e) {
		}

	}
	
	public void selectDropdownThirdParty(String value) {
		driver.findElement(By.xpath("//mat-select[@id='designationTypeId']")).click();
		driver.findElement(By.xpath("//*[contains(.,'" + value + "')][@class='mdc-list-item__primary-text']")).click();
	}
	
	public void selectDropdownUsingSearch(String xpathParameter, String value) throws InterruptedException {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-select[1]"))
				.click();
		driver.findElement(By.xpath("//*[@placeholder='search']")).sendKeys(value.trim());
		sleep(2000);
		driver.findElement(By.xpath("//*[contains(.,'" + value + "')][@class='mdc-list-item__primary-text']")).click();

	}
	

	public void errorLabel(String xpathParameter) {
		driver.findElement(By.xpath("//mat-error[.='" + xpathParameter + "'][1]")).isDisplayed();
	}
	
	public void Label(String xpathParameter) {
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "'][1]")).isDisplayed();
	}
	
	public void matLabel(String xpathParameter) {
		driver.findElement(By.xpath("//mat-label[.='" + xpathParameter + "'][1]")).isDisplayed();
	}
	public void errorLabelFollowingField(String xpathParameter, String fieldName) {
		driver.findElement(By.xpath("//*[.='"+fieldName+"']//following::mat-error[.='" + xpathParameter + "'][1]")).isDisplayed();
	}

	public void errorLabelContains(String xpathParameter, String fieldName) {
		driver.findElement(By.xpath("//*[.='"+fieldName+"']//following::mat-error[contains(.,'" + xpathParameter + "')][1]")).isDisplayed();
	}

	public void errorContent(String xpathParameter) {
		driver.findElement(By.xpath("//*[.='" + xpathParameter + "'][@id='businessError0'][1]")).isDisplayed();

	}

	public void ScrollMenu(String xpathParameter) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//*[text()='" + xpathParameter + "'][1]")));
		Thread.sleep(500);
	}

	public void clickElement(WebElement ele) throws InterruptedException {
		ele.click();
		Thread.sleep(2000);
	}

	public void screenShot(String fileName, String status, String message) throws Exception {
		String screenshotMode = prop.getProperty("screenshotMode");
		if(screenshotMode.equalsIgnoreCase("Development")) {
		screenShot screen = new screenShot();
		String screenShotPath = screenShot.takeSnapShot(driver,
				"D:\\AutomationFiles\\Screenshots\\"
						+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()).toString()
						+ "_" + fileName + ".png");
		if (status.equalsIgnoreCase("Pass")) {
			test.log(Status.PASS, message);
		}else {
			test.log(Status.FAIL, message);
		}
		test.addScreenCaptureFromPath(screenShotPath);
		}
		else {
		//Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.viewportRetina(100,0,0,2), 1000)).takeScreenshot(driver);
		String screenShotTime=new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()).toString()+ "_" + fileName ;
		String screenShotPath ="D:\\AutomationFiles\\Screenshots\\"+screenShotTime;
		//ImageIO.write(screenshot.getImage(), "jpg", new File(screenShotPath));
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE_CHROME, 500, true).withName(screenShotTime).save("D:\\AutomationFiles\\Screenshots");
					if (status.equalsIgnoreCase("Pass")) {
			test.log(Status.PASS, message);
		}else {
			test.log(Status.FAIL, message);
		}
					
			
		File scrFile = new File(screenShotPath+".png");
	    String encodedBase64 = null;
	    FileInputStream fileInputStreamReader = null;
	    try {
	        fileInputStreamReader = new FileInputStream(scrFile);
	        byte[] bytes = new byte[(int)scrFile.length()];
	        fileInputStreamReader.read(bytes);
	        encodedBase64 = new String(Base64.encodeBase64(bytes));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    screenShotPath= "data:image/png;base64,"+encodedBase64;
			     
				     
		// test.info(message);
		//test.addScreenCaptureFromPath(screenShotPath+".png");
		test.addScreenCaptureFromPath(screenShotPath);
		}
					

	}
	
	/*public void screenShot(String fileName, String status, String message) throws Exception {
		Date currentDate = new Date();
		String screenShotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver ).getScreenshotAs(OutputType.FILE);
		//File destinationFile = new File(".//screenshot"+screenShotFileName+".png");
		File destinationFile = new File("D:\\AutomationFiles\\Screenshots\\TestAutomationReport"+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()).toString()+ "_" + fileName + ".png");
		
		String absolutePath = destinationFile.getAbsolutePath();
		FileUtils.copyFile(screenshotFile, destinationFile);
		
		if (status.equalsIgnoreCase("Pass")) {
			test.log(Status.PASS, message);
		}else {
			test.log(Status.FAIL, message);
		}
		test.addScreenCaptureFromPath(absolutePath);
		
	}*/
	
	public void selectTable(String ssnValue, int columnValue, int tableId, String tableName) {
		WebElement table = driver
				.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableId'][" + tableId + "]"));

		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableId'][" + tableId
							+ "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]/a[1]")).click();
					break label1;
				}
			}

		}
	}

	public void selectTableTypeList(String ssnValue, int columnValue, int tableId, String tableName) {
		WebElement table = driver.findElement(
				By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableTypeListId'][" + tableId + "]"));

		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableTypeListId']["
							+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]/a[1]")).click();
					break label1;
				}
			}

		}
	}

	public void selectRadioInTable(String ssnValue, int columnValue, int tableId, String tableName) {
		WebElement table = driver.findElement(
				By.xpath("//*[.='" + tableName + "']//following::*[contains(@id ,'dataTable')][" + tableId + "]"));

		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[contains(@id ,'dataTable')]["
							+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue)
							+ "]//following::*[@class='mat-radio-container']")).click();
					break label1;
				}
			}

		}
	}

	public void selectTableWithoutId(String ssnValue, int columnValue, int tableId, String tableName) {
		WebElement table = driver
				.findElement(By.xpath("//*[.='" + tableName + "']//following::mat-table[" + tableId + "]"));

		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.contains(ssnValue)) {
					driver.findElement(By.xpath("//*[.='" + tableName + "']//following::mat-table[" + tableId
							+ "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//a[1]")).click();
					break label1;
				}
			}

		}
	}

	public void selectTableParameterizedId(String ssnValue, int columnValue, int tableId, String tableName,
			String tableHtmlId) {
		WebElement table = driver.findElement(
				By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "'][" + tableId + "]"));

		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
							+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]/a[1]")).click();
					break label1;
				}
			}

		}
	}

	public void selectDateInTable(String ssnValue, int columnValue, int tableId, String tableName, String value) {
		WebElement table = driver.findElement(
				By.xpath("//*[.='" + tableName + "']//following::*[contains(@id ,'dataTable')][" + tableId + "]"));

		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[contains(@id ,'dataTable')]["
							+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//input[1]"))
							.sendKeys(value);
					break label1;
				}
			}

		}
	}

	// *[.='Joint Employment/Management Agreement Arrangement
	// ']//following::*[contains(@id
	// ,'dataTable')][1]/mat-row[1]/mat-cell[5]//input[1]
	public String retrieveValueFromTable(String ssnValue, int columnValue, int tableId, String tableName) {
		WebElement table = driver
				.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableId'][" + tableId + "]"));
		String value = "";
		List<WebElement> rows = table.findElements(By.tagName("mat-row"));

		int row_count = rows.size();
		System.out.println("Total Row: " + row_count);
		label1: for (int row = 0; row < row_count; row = row + 1) {
			List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
			int columns_count = columns.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = columns.get(column).getText();
				if (celtext.equals(ssnValue)) {
					value = driver
							.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='dataTableId']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]"))
							.getText();

					break label1;
				}
			}

		}
		return value;
	}

	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}

	public void enterCurrentDate(WebElement ele) {
		String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		ele.sendKeys(date);
	}

	public void waitForElementClicable(WebElement ele) throws InterruptedException {
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		Thread.sleep(3000);
		ele.click();
	}

	public void doSendKeysWithWait(WebElement ele, String data) throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
		Thread.sleep(2000);
		ele.sendKeys(data);
	}

	public static long createRandomInteger(long aStart, long aEnd) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		Random aRandom = new Random();
		long range = aEnd - (long) aStart + 1;

		long fraction = (long) (range * aRandom.nextDouble());

		long randomNumber = fraction + (long) aStart;
		System.out.println("Generated : " + randomNumber);
		return randomNumber;

	}

	public void uploadDoc(String fileName) throws AWTException {
		Robot rb = new Robot();
		StringSelection str = new StringSelection("D:\\AutomationFiles\\" + fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}

	public void selectCheckbox(String xpathParameter) {

		By element = By.xpath("//mat-label[contains(.,'" + xpathParameter
				+ "')]//preceding::*[@class='mat-checkbox-inner-container'][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			safeJavaScriptClick(ele);
		} catch (final Exception e) {
		}
	}

	public void selectLink(String xpathParameter, String value) {
		driver.findElement(
				By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::*[.='" + value + "'][1]"))
				.click();
	}

	public void clickOnLink(String xpathParameter) {
		driver.findElement(By.xpath("//u[contains(.,'" + xpathParameter + "')][1]")).click();

	}
	
	public void clickOnLinkBasisClass(String xpathParameter) {
		driver.findElement(By.xpath("//u[contains(@class,'" + xpathParameter + "')][1]")).click();

	}
	
	public void clickOnLinkAnchorTag(String xpathParameter) {
		By element = By.xpath("//a[contains(.,'" + xpathParameter + "')][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			safeJavaScriptClick(ele);
		} catch (final Exception e) {
		}
	}

	public void database_UpdateQuery(String query) throws SQLException, InterruptedException {
	
		System.out.println(query);

		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");
		PreparedStatement p = null;
		// Statement stmt=con.createStatement();
		// stmt.executeQuery(query);
		Thread.sleep(100000);	
		p = con.prepareStatement(query);
		Thread.sleep(2000);
		p.execute();
		Thread.sleep(2000);
		con.close();
	}

	public Map<String, String> database_SelectQuery(String query) throws SQLException {

		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {

			results.put("Fein", rs.getString("FEIN"));
			results.put("Ean", rs.getString("EAN"));
			break;
		}

		con.close();
		return results;
	}

	public Map<String, String> database_SelectQuerySingleColumn(String query, String ColumnName) throws SQLException {

		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int i = 0;
		while (rs.next()) {
			i = i + 1;
			if (i > 0) {
				results.put(ColumnName, rs.getString(ColumnName));
				break;
			}
		}

		con.close();
		return results;
	}

	public void selectDropdownEquals(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-select[1]"))
				.click();
		driver.findElement(By.xpath("//*[.='" + value + "'][@class='mdc-list-item__primary-text']")).click();

	}

	public void selectRadioWithFeinValue(String feinValue) throws Exception {
	WebElement radio = 	driver.findElement(By.xpath("//mat-label[text()='"+feinValue+"']/../../../preceding-sibling::mat-cell/mat-radio-group/mat-radio-button/label/span/span[@class='mat-radio-outer-circle']"));
		safeJavaScriptClick(radio);
	}

	public void clearTextboxContains(String xpathParameter) throws Exception {
	WebElement radio =	driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]"));
	safeJavaScriptClick(radio);
	}

	public void loginPeoAdmin(String userName, String password) throws Exception {

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		Thread.sleep(2000);
		screenShot("LoginPage", "Pass", "HomePage");
		loginPage.loginLink.click();
		Thread.sleep(2000);
		Thread.sleep(5000);

		enterTextbox("Username", userName);
		test.log(Status.PASS, "User entered Username");
		enterTextbox("Password", password);
		test.log(Status.PASS, "User entered Password");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();

		sleep(3000);
		// driver.navigate().refresh();
		sleep(3000);
		sleep(10000);
		driver.navigate().refresh();
		sleep(10000);
		driver.navigate().refresh();
		sleep(5000);
		waitForLoadingIconToDisappear();

		screenShot("PeoAdminPopup", "Pass", "peoAdminPopUp");
		peoPage.PeoUserSelect.click();
		/*selectRadio(
				"I am a Professional Employer Organization that needs to create an online account for maintaining my client’s associations and Professional Employer Organization registration status.");*/
		clickButtonContains("Continue");
		sleep(2000);
		if (driver.findElements(By.xpath("//*[.=' OK '][@class='mat-button-wrapper']")).size() > 0) {
			loginPage.okPopUpButton.click();
			Thread.sleep(3000);
		}
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		if (driver.findElements(By.xpath("//*[.=' OK '][@class='mat-button-wrapper']")).size() > 0) {
		 loginPage.okPopUpButton.click();
		 Thread.sleep(3000);
		 }

	}

	public void logoutAndLogin(String userName, String password) throws Exception {
		HomePage HomePage = PageFactory.initElements(driver, HomePage.class);
		clickMenu("LOG OUT");
		sleep(4000);
		clickMenu("Go to Homepage");
		sleep(2000);		
		waitForLoadingIconToDisappear();
		try {
		HomePage.menuLogout.click();
		sleep(2000);
		HomePage.signOut.click();
		sleep(5000);
		enterTextbox("Username", userName);
//		test.log(Status.PASS, "User entered Username");
		enterTextbox("Password", password);
//		test.log(Status.PASS, "User entered Password");
//		driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();
		sleep();
		screenShot("LoginPage", "Pass", "Logged in with \"" + userName.toUpperCase() + "\"");
		driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.get(prop.getProperty("applicationUrl"));
		login(userName, password);
	}
		catch(Exception e) {}
	}

	public void enterFutureDate(String xpathParameter, int daysAdded) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, daysAdded);
		String output = sdf.format(c.getTime());
		System.out.println(output);
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]"))
				.sendKeys(output);
		String fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
		test.log(Status.INFO, fieldName+" : : "+output);
	}
	

	public void selectFromDropdown(String xpathParameter) {
		driver.findElement(By.xpath("//*[contains(.,'" + xpathParameter + "')][@class='mdc-list-item__primary-text']")).click();

	}
	
	public String enterRandomString(String xpathParameter) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int index = (int) (alphabet.length() * Math.random());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		System.out.println(randomString);
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]"))
		.sendKeys(randomString);
		String fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
		test.log(Status.INFO, fieldName+" : : "+randomString);
		return randomString;

	}

	public String enterRandomStringLegalName(String xpathParameter) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 15; i++) {
			int index = (int) (alphabet.length() * Math.random());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		System.out.println(randomString);
		driver.findElement(By.xpath("//mat-label[text()='"+ xpathParameter +"']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")).clear();
		driver.findElement(By.xpath("//mat-label[text()='"+ xpathParameter +"']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea"))
		.sendKeys(randomString);
		String fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
		test.log(Status.INFO, fieldName+" : : "+randomString);
		return randomString;
	}
	
	public void waitForLoadingIconToDisappear() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[text()='Loading...']")));
		sleep(2000);
	}

	public void enterPastDate(String xpathParameter, int daysSub) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -daysSub);
		String output = sdf.format(c.getTime());
		System.out.println(output);
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]"))
				.sendKeys(output);
		String fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
		test.log(Status.INFO, fieldName+" : : "+output);
	}
	
	public void enterDateOfCurrentQuaterFirstMonth(String xpathParameter) {
		String fieldName;
		LocalDate myLocal = LocalDate.now();
		int quarter = myLocal.get(IsoFields.QUARTER_OF_YEAR);
		String date = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		
		switch (quarter) {
		case 1:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("0101"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"01/01/"+date+"");
			break;
		case 2:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("0401"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"04/01/"+date+"");
			break;
		case 3:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("0701"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"07/01/"+date+"");
			break;
		case 4:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("1001"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"10/01/"+date+"");
			break;
		default:
			System.out.println("Error in code");
			break;
		}
		
	}
	
	public void forceClearTextWithElement(String xpathParameter) {
		WebElement ele = driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(ele).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
	}
	
	public void forceClearText(WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
	}
	

	public void selectDropdownSecondBox(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-select[2]"))
				.click();
		driver.findElement(By.xpath("//*[contains(.,'" + value + "')][@class='mdc-list-item__primary-text']")).click();

	}
	public void enterTextboxContainsThirdBox(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[3]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[3]"))
				.sendKeys(value);
	
	}
	

	public void enterDateOfCurrentQuaterFirstMonthPlusOneDay(String xpathParameter) {
		String fieldName;
		LocalDate myLocal = LocalDate.now();
		int quarter = myLocal.get(IsoFields.QUARTER_OF_YEAR);
		String date = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		
		switch (quarter) {
		case 1:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("0102"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"01/02/"+date+"");
			break;
		case 2:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("0402"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"04/02/"+date+"");
			break;
		case 3:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("0702"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"07/02/"+date+"");
			break;
		case 4:
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys("1002"+date+"");
			fieldName = driver.findElement(By.xpath("//*[contains(text(),'"+xpathParameter+"')]")).getText();
			test.log(Status.INFO, fieldName+" : : "+"10/02/"+date+"");
			break;
		default:
			System.out.println("Error in code");
			break;
		}
		
	}
	public void logout(String userName, String password) throws Exception {
		
		HomePage HomePage = PageFactory.initElements(driver, HomePage.class);
		clickMenu("LOG OUT");
		sleep(4000);
		clickMenu("Go to Homepage");
		sleep(4000);
		HomePage.menuLogout.click();
		HomePage.signOut.click();
		sleep(5000);
	}

	
	public void LogoutAndLoginIfOktaPageDisplayed(String userName, String password) throws Exception {
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			HomePage HomePage = PageFactory.initElements(driver, HomePage.class);
			clickMenu("LOG OUT");
			clickMenu("Go to Homepage");

			sleep(3000);

			sleep(4000);

			HomePage.menuLogout.click();
			HomePage.signOut.click();
			sleep(5000);
			enterTextbox("Username", userName);
			test.log(Status.PASS, "User entered Username");
			enterTextbox("Password", password);
			test.log(Status.PASS, "User entered Password");
			Thread.sleep(2000);	
			//driver.findElement(By.xpath("//button[@name='loginform:altSubmit']//preceding::span[1]")).click();
	        driver.navigate().refresh();
			driver.get(prop.getProperty("applicationUrl"));
			loginPage.loginLink.click();
			sleep(2000);

	}
	
	public void validateTextIsDisplayed(String xpathParameter) {
		driver.findElement(By.xpath("//*[contains(text(),'" + xpathParameter + "')]")).getText();

	}

//	public  String captureScreenShot() throws IOException {
//	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	File destinationFile = new File("src/../images"+System.currentTimeMillis()+"png");
//	String absolutePathScreen =  destinationFile.getAbsolutePath();
//	FileUtils.copyFile(scrFile, destinationFile);
//	return absolutePathScreen;
//}
	 public void selectInactiveLinkInTable(String fname, String lname) {
			driver.findElement(By.xpath("//mat-label[text()='"+fname+" "+lname+"']/ancestor::mat-row//mat-cell/a/u[text()='inactive']")).click();
		}
		public void enterCurrentDate(String xpathParameter) {
			String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).clear();
			driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]"))
					.sendKeys(date);
		}
		
		public void secondErrorContent(String xpathParameter) {
			driver.findElement(By.xpath("//*[.='" + xpathParameter + "'][@id='businessError1'][1]")).isDisplayed();

		}
		
		public void selectDeleteLinkInTable(String fname, String lname) {
			driver.findElement(By.xpath("//mat-label[text()='"+fname+" "+lname+"']/ancestor::mat-row//mat-cell/a/u[text()='Delete']")).click();
		}

		public void highLightWebElement(final WebDriver driver, final WebElement element) throws Exception {

			final JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: red; border: 2px solid green;");
			Thread.sleep(1000);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
		
		
		public void validateNextPageNumber(String pageNo) {
			WebElement element = 	driver.findElement(By.xpath("//*[contains(text(),'"+pageNo+"')]"));
			try {
				WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
				String pageNum = ele.getText();
				highLightWebElement(driver, ele);
				Assert.assertEquals(pageNum.trim(), pageNo.trim());
				test.log(Status.INFO, "Page number validated : : "+pageNo.trim());
				System.out.println("Navigated to "+pageNo+"");
			} catch (final Exception e) {
			}
		}
		
		public void searchForworkItem(WebElement ele) {
            Actions a = new Actions(driver);
            a.moveToElement(ele).doubleClick().sendKeys("DOL");
        }
		
		//by Devanshu
		public String retrieveValue(String xpathParameter) {
	        By element = By.xpath("//mat-label[text()='" + xpathParameter + "']//following::mat-label[1]");
	        final WebDriverWait wait = new WebDriverWait(driver, 10);
	        String actualValue="";
	        try {
	            WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));                
	            actualValue=ele.getAttribute("aria-label");                
	        } catch (final Exception e) {
	        }
	        return actualValue;
	    }

		public String retrieveTextboxContains(String xpathParameter) {
			By element = By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]");
			final WebDriverWait wait = new WebDriverWait(driver, 10);
			String actualValue="";
			try {
				WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));				
				actualValue=ele.getAttribute("value");				
			} catch (final Exception e) {
			}
			return actualValue;
		}
		
		public void selectActionTableParameterizedId(String ssnValue, int columnValue, int tableId, String tableName,
				String tableHtmlId,String action,String value) {
			WebElement table = driver.findElement(
					By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "'][" + tableId + "]"));

			List<WebElement> rows = table.findElements(By.tagName("mat-row"));

			int row_count = rows.size();
			System.out.println("Total Row: " + row_count);
			label1: for (int row = 0; row < row_count; row = row + 1) {
				List<WebElement> columns = rows.get(row).findElements(By.tagName("mat-cell"));
				int columns_count = columns.size();
				System.out.println("Number of cells In Row " + row + " are " + columns_count);
				for (int column = 0; column < columns_count; column++) {
					String celtext = columns.get(column).getText();
					String attributeValue;
					if (celtext.equals(ssnValue)) {
						if(action.equalsIgnoreCase("checkBox")) {
							 attributeValue =  driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//mat-checkbox[1]//input[@type='checkbox']")).getAttribute("tabindex"); 
						if(attributeValue.equalsIgnoreCase("0")) {
							driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
								+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//mat-checkbox[1]")).click();
						break label1;}
						}
						else if(action.equalsIgnoreCase("textBox")) {
							attributeValue = driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//following::input[1][@type='text'][1]")).getAttribute("disabled");
							if(attributeValue==null) {
							driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//following::input[1][@type='text'][1]")).sendKeys(value);
							break label1;}
							}
						else if(action.equalsIgnoreCase("link")) {
							if(!driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//a["+value+"]")).getAttribute("class").equalsIgnoreCase("disabled")) {
							driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//a["+value+"]")).click();
							break label1;}
							}
						else if(action.equalsIgnoreCase("listBox")) {
							attributeValue = driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//textarea[1]")).getAttribute("disabled");
							if(attributeValue==null) {
							driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//textarea[1]")).sendKeys(value);
							break label1;}
							}
						else if(action.equalsIgnoreCase("getText")) {
							//attributeValue = driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
							//		+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//textarea[1]")).getAttribute("disabled");
							//if(attributeValue==null) {
							boolean actualResult;
							if(value.equalsIgnoreCase("notNull")) {
							String actualValue=driver.findElement(By.xpath("//*[.='" + tableName + "']//following::*[@id='" + tableHtmlId + "']["
									+ tableId + "]/mat-row[" + (row + 1) + "]/mat-cell[" + (columnValue) + "]//mat-label[1]")).getText();
							if(actualValue == null || actualValue.length() == 0) {actualResult = false;}
							else {actualResult=true;}
							Assert.assertTrue(actualResult, "Interest rate is populated");
							}
							//break label1;}
							}
					}
				}

			}
		}

		public String retrieveLabelContains(String xpathParameter) {
			By element = By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::mat-label[1]");
			final WebDriverWait wait = new WebDriverWait(driver, 10);
			String actualValue="";
			try {
				WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));				
				actualValue=ele.getText();				
			} catch (final Exception e) {
			}
			return actualValue;
		}
		
		
		public void addComment(String value) {
			driver.findElement(By.xpath("//*[@id='remarksId']")).clear();
			driver.findElement(By.xpath("//*[@id='remarksId']")).sendKeys(value);
		}
		
		public void switchTab() {
			Set<String> allHandles = driver.getWindowHandles();
			Iterator<String> l1 = allHandles.iterator();
			String parent = l1.next();
			System.out.println(parent);
			String Child = l1.next();
			System.out.println(Child);
			driver.switchTo().window(Child);
		}

		public static int getPageCount(PDDocument doc) {
			// get the total number of pages in the pdf document
			int pageCount = doc.getNumberOfPages();
			return pageCount;

		}

		public static String readPdfContent(String url) throws IOException {

			URL pdfUrl = new URL(url);
			InputStream in = pdfUrl.openStream();
			BufferedInputStream bf = new BufferedInputStream(in);
			PDDocument doc = PDDocument.load(bf);
			int numberOfPages = getPageCount(doc);
			System.out.println("The total number of pages " + numberOfPages);
			String content = new PDFTextStripper().getText(doc);
			doc.close();

			return content;
		}

		public void verifyContentInPDf( String xpathParameter) throws Exception {
			// specify the url of the pdf file
			switchTab();
			screenShot("PDF Screenshot", "Pass", "PDF Document");
			String pdfUrl = driver.getCurrentUrl();
			System.out.println(driver.getCurrentUrl());
			driver.get(pdfUrl);
			try {
				String pdfContent = readPdfContent(pdfUrl);
				Assert.assertTrue(pdfContent.contains(xpathParameter));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			
		}

}