package com.ui.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ui.base.TestBase;

public class employerManagementLocators extends TestBase {

	public WebElement menuButton (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Menu']")));
		return driver.findElement(By.xpath("//span[text()='Menu']]"));

	}
	public WebElement AccountMaintenance (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Account Maintenance']")));
		return driver.findElement(By.xpath("//span[text()='Account Maintenance']"));

	}
	
	public WebElement MaintainAddress (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Maintain Address']]")));
		return driver.findElement(By.xpath("//span[text()='Maintain Address']]"));

	}
	public WebElement editButtonBusinessPhyLoc (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-label[text()='Business Physical Location']//following::u[text()='Edit']")));
		return driver.findElement(By.xpath("//mat-label[text()='Business Physical Location']//following::u[text()='Edit']"));

	}
	
	public WebElement editButtonPriBusinessPhyLoc (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-label[text()='Primary Business Physical Address']//following::u[text()='Edit'][1]")));
		return driver.findElement(By.xpath("//mat-label[text()='Primary Business Physical Address']//following::u[text()='Edit'][1]"));

	}
	public WebElement addressLine1 (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='address1']")));
		return driver.findElement(By.xpath("//input[@formcontrolname='address1']"));

	}
	public WebElement city (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //input[@formcontrolname='city']")));
		return driver.findElement(By.xpath(" //input[@formcontrolname='city']"));

	}
	public WebElement addressLine2 (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='address2']")));
		return driver.findElement(By.xpath("//input[@formcontrolname='address2']"));

	}
	public WebElement pageTitle (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='titleId']]")));
		return driver.findElement(By.xpath("//h2[@id='titleId']"));

	}
	public WebElement submitButton (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		return driver.findElement(By.xpath("//button[@type='submit']"));

	}
	
	public WebElement EAN (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='ean']")));
		return driver.findElement(By.xpath("//input[@formcontrolname='ean']"));

	} 
	public WebElement continueButton (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Continue ']")));
		return driver.findElement(By.xpath("//span[text()='Continue ']"));

	} 
	public WebElement select (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='--SELECT--']")));
		return driver.findElement(By.xpath("//span[text()='--SELECT--']"));

	}  
	
	public WebElement selectCorres (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Correspondence/Email ']")));
		return driver.findElement(By.xpath("//span[text()=' Correspondence/Email ']"));

	}
	
	public WebElement successMsgSuc002 (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-card[@id='successMatCardId']")));
		return driver.findElement(By.xpath("//mat-card[@id='successMatCardId']"));

	}
	
	public WebElement businessActivity (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Business Principal Activity Details ']")));
		return driver.findElement(By.xpath("//a[text()=' Business Principal Activity Details ']"));

	}
	
	public WebElement addClientDetails (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ADD CLIENT DETAILS MANUALLY']")));
		return driver.findElement(By.xpath("//span[text()='ADD CLIENT DETAILS MANUALLY']"));

	}
	
	public WebElement enterERN (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Employee Registration Number']")));
		return driver.findElement(By.xpath("//input[@aria-label='Employee Registration Number']"));
	}
	
	public WebElement search (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='search']")));
		return driver.findElement(By.xpath("//mat-icon[text()='search']"));
	}

	public WebElement peoName (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label= 'PEO Name']")));
		return driver.findElement(By.xpath("//input[@aria-label= 'PEO Name']"));
	}
	
	public WebElement selectpeoName (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-label[text()='Pending New']//preceding::span[@class='mat-radio-outer-circle'][1]")));
		return driver.findElement(By.xpath("//mat-label[text()='Pending New']//preceding::span[@class='mat-radio-outer-circle'][1]"));
	}
	
	public WebElement selectradio_locationofbooks (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lbraAddrIndId_BPPA\"]/label/span[1]/span[1]")));
		return driver.findElement(By.xpath("//*[@id=\"lbraAddrIndId_BPPA\"]/label/span[1]/span[1]"));
	}
	
	public WebElement selectBrowse(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='Browse']")));
		return driver.findElement(By.xpath("//strong[text()='Browse']"));
	}
	
	public WebElement searchWI(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mat-input-161']")));
		return driver.findElement(By.xpath("//input[@id='mat-input-161']"));
	}
	
	public WebElement FEIN (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@arIa-label='FEIN']")));
		return driver.findElement(By.xpath("//input[@arIa-label='FEIN']"));

	} 
	
	public WebElement selectradio_noticeofpotentialcharges (){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"npcclaimAddrIndId_BPPA\"]/label/span[1]/span[1]")));
		return driver.findElement(By.xpath("//*[@id=\"npcclaimAddrIndId_BPPA\"]/label/span[1]/span[1]"));
	}
}




