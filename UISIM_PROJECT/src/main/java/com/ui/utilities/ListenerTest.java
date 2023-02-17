package com.ui.utilities;
import org.testng.ITestContext ;		
import org.testng.ITestListener ;		
import org.testng.ITestResult ;

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
			screenShotPath = screenShot.takeSnapShot(driver, "target\\error.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       test.log(Status.FAIL,"Testcase Failed");
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
