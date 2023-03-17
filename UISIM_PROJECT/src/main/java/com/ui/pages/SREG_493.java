package com.ui.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import stepDefinitions.commonStepDefinitions;

public class SREG_493 {

	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_493(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Source']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement sourceDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Source Type']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement sourceTypeDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceTypeDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//label[@for='mat-radio-2-input']//span[@class='mat-radio-outer-circle']")
	public WebElement selectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-form-field-infix ng-tns-c138-10']//input[@id='terminationDateId']")
	public WebElement jointAccountDissolutionDate;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='commentId']")
	public WebElement comments;
	
	@FindBy(how = How.XPATH, using = "//h2[text()='List of Members of Joint Account']")
	public WebElement pageTitleSREG_493;
	
	@FindBy(how = How.XPATH, using = "//mat-header-cell[text()=' Joint Account Administrator ']")
	public WebElement joinACAdminTableData;
	
	@FindBy(how = How.XPATH, using = "//mat-header-cell[text()=' Removal Request Date ']")
	public WebElement removalRequestDateTableData;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Maintain Joint Account Administrator']")
	public WebElement maintainJAAdminLink;
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),' Required ')]")
	public WebElement requiredText;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_address1']")
	public WebElement addressLane1Input;
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'only Alphabets, Numbers')]")
	public WebElement InvalidErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_address2']")
	public WebElement addressLane2Input;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_city']")
	public WebElement cityInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_zip']")
	public WebElement zipCodeInput;
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'Zip Code must have 5')]")
	public WebElement zipCodeError;
	
	@FindBy(how = How.XPATH, using = "//input[@id='phoneId']")
	public WebElement telephoneNumberInput;
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'Phone Number should be 10')]")
	public WebElement telephoneNumberErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//input[@id='phoneIdExt']")
	public WebElement telephoneNumberEXTInput;
	//mat-error[contains(text(),'Ext Number should be 3')]
	@FindBy(how = How.XPATH, using = "//input[@id='phoneIdExt']")
	public WebElement telephoneNumberEXTError;
	
	
	
	public void fillForm() throws InterruptedException {
		stepDef.waitForElementClicable(sourceDropDown);
		sourceDropDownValue.click();
		stepDef.waitForElementClicable(sourceTypeDropDown);
		sourceTypeDropDownValue.click();
		selectRadioButton.click();
		stepDef.doSendKeysWithWait(jointAccountDissolutionDate, "03132023");
		comments.sendKeys("Test Data");
	}
	
	public void validateMaintainJAAdminHyperlink() throws Exception {
		try {
			stepDef.waitForElementClicable(maintainJAAdminLink);
			Thread.sleep(3000);
			stepDef.screenShot("SREG493Page", "Pass", "Inside the SREG_493 Page");
			stepDef.clickButton("Previous ");
			stepDef.waitForElementClicable(maintainJAAdminLink);
			Thread.sleep(3000);
			stepDef.clickButton("Continue ");
			Thread.sleep(3000);
			stepDef.screenShot("requiredText", "Pass", "Checking Required text should display");
			String requiredTextMessage = requiredText.getText();
			System.out.println("Required text :: "+ requiredTextMessage);
			Assert.assertTrue(requiredText.isDisplayed());
			sourceDropDown.click();
			Thread.sleep(2000);
			sourceDropDownValue.click();
			Thread.sleep(2000);
			sourceTypeDropDown.click();
			Thread.sleep(2000);
			sourceTypeDropDownValue.click();
			Thread.sleep(2000);
			stepDef.screenShot("DropDownSelect", "Pass", "Selected Source and & Source Type Values");
			System.out.println("Selected both Source & Source Type value");			
			addressLane1Input.clear();
			Thread.sleep(2000);
			addressLane1Input.sendKeys("%$&%$&%$&%");
			Assert.assertTrue(InvalidErrorMessage.isDisplayed());
			addressLane1Input.clear();
			Thread.sleep(2000);
			addressLane1Input.sendKeys("Test_Data");
			Thread.sleep(3000);
			stepDef.screenShot("CheckInvalidText1", "Pass", "Validate invalid error message is displaying for Address Lane");
			addressLane2Input.clear();
			addressLane2Input.sendKeys("^%&*%&%*%&^%");
			Assert.assertTrue(InvalidErrorMessage.isDisplayed());
			addressLane2Input.clear();
			Thread.sleep(2000);
			addressLane2Input.sendKeys("Valida_Data");
			cityInput.clear();
			Thread.sleep(2000);
			cityInput.sendKeys("NewYork&&&%%");
			Assert.assertTrue(InvalidErrorMessage.isDisplayed());
			cityInput.clear();
			Thread.sleep(2000);
			cityInput.sendKeys("New_York");
			Thread.sleep(3000);
			stepDef.screenShot("CheckInvalidText2", "Pass", "Validate invalid error message is displaying for city");
			zipCodeInput.clear();
			Thread.sleep(2000);
			zipCodeInput.sendKeys("99");
			Assert.assertTrue(zipCodeError.isDisplayed());
			zipCodeInput.clear();
			Thread.sleep(2000);
			zipCodeInput.sendKeys("21312");
			Thread.sleep(3000);
			stepDef.screenShot("invalidEXT", "Pass", "Validate invalid error message is displaying for Telephone Number");
			telephoneNumberInput.clear();
			telephoneNumberInput.sendKeys("34521");
			Thread.sleep(2000);
			Assert.assertTrue(telephoneNumberErrorMessage.isDisplayed());
			telephoneNumberInput.sendKeys("0002345432");
			
			telephoneNumberEXTInput.sendKeys("9");
			Assert.assertTrue(telephoneNumberEXTError.isDisplayed());
			telephoneNumberEXTInput.sendKeys("345");
			Thread.sleep(3000);
			stepDef.screenShot("SuccessTillStepInMC", "Pass", "Validate invalid error message is displaying for Telephone EXT");
			
			
			
			
			
			
			
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
	}
}
