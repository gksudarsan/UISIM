package com.ui.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.testng.ITestContext ;		
import org.testng.ITestListener ;		
import org.testng.ITestResult ;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;		

public class ListenerTest extends TestBase implements ITestListener {
	public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    public void onStart(ITestContext arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

   		
    public void onTestFailure(ITestResult Result) {					
        // TODO Auto-generated method stub				
    	System.out.println("The name of the testcase failed is :"+Result.getName());
    	 screenShot screenShot=new screenShot();
		 String screenShotPath;
		 screenShotPath="";
		try {
			//screenShotPath = screenShot.takeSnapShot(driver, "target\\error.jpg");
			String screenShotTime=new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()).toString()+ "_" + "ErrorScreenshot" ;
			 screenShotPath ="D:\\AutomationFiles\\Screenshots\\"+screenShotTime;
			//ImageIO.write(screenshot.getImage(), "jpg", new File(screenShotPath));
			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE_CHROME, 1000, true).withName(screenShotTime).save("D:\\AutomationFiles\\Screenshots");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       test.log(Status.FAIL,"Testcase Failed");
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
	       test.addScreenCaptureFromPath(screenShotPath);
    }		

    		
    public void onTestSkipped(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		
		
    public void onTestStart(ITestResult Result) {					
        // TODO Auto-generated method stub				
    	System.out.println("The name of the testcase started is :"+Result.getName());		
    }		

    	
    public void onTestSuccess(ITestResult Result) {					
        // TODO Auto-generated method stub				
    	System.out.println("The name of the testcase Passed is :"+Result.getName());		
    }		
}
