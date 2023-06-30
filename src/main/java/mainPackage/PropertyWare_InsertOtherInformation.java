package mainPackage;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertyWare_InsertOtherInformation 
{
	public static String enrolledInRBPForPMUse = "";
	public static String enrolledInRBPForPMUseNo = "";
	public static String RBPenrollmentCompleteForSNUseOnly = "";
	public static String RBPenrollmentCompleteForSNUseOnlyNo= "";
	public static String renewalStatus="";
	public static String reneWalFollowupNotes = "";
	public static String renewalExecutionDate = "";
	public static String currentMonthlyRent = "";
	public static String priorMonthlyRent = "";
	public static String renewalCoordinatorName = "";
	public static String petRentAmount = "";
	
	//Related Activities - Lease Renewal
	public static String newStartDate = "";
	public static String newEndDate = "";
	public static String renewalOnDate = "";
	
	
	
	public static void addingOtherInformation() throws Exception
	{
		
		String leaseStatus = RunnerClass.driver.findElement(Locators.status).getText();
		if(leaseStatus.equals("Active - Month to Month") || leaseStatus.equals("Active - Notice Given"))
		{
			RunnerClass.driver.findElement(Locators.summaryEditButton).click();
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.activeStatus)).build().perform();
			Select statusDropdown = new Select(RunnerClass.driver.findElement(Locators.activeStatus));
			statusDropdown.selectByVisibleText("Active");
			RunnerClass.js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			  if(AppConfig.saveButtonOnAndOff==true)
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).click(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
			  else
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).click(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
		}
		
		RunnerClass.driver.navigate().refresh();
		RunnerClass.driver.findElement(Locators.summaryEditButton).click();
		Thread.sleep(2000);
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		try
		{
		//Clear all existing values in variables
		PropertyWare_InsertOtherInformation.clearExistingVariableValues();
		//Adding values to all variables 
		
		enrolledInRBPForPMUse = AppConfig.getEnrolledINRBPForPMUse(RunnerClass.company);
		enrolledInRBPForPMUseNo = AppConfig.getEnrolledINRBPForPMUseNo(RunnerClass.company);
		RBPenrollmentCompleteForSNUseOnly = AppConfig.getRBPenrollmentCompleteForSNUseOnly(RunnerClass.company);
		RBPenrollmentCompleteForSNUseOnlyNo = AppConfig.getRBPenrollmentCompleteForSNUseOnlyNo(RunnerClass.company);
		renewalStatus = "RW-4a%3A+CHARGE+RENEWAL+FEE+-+ANNUAL";
		reneWalFollowupNotes = "Full Lease Executed From "+PDFReader.startDate+" - "+PDFReader.endDate+" - HRG Automation - "; //Need to calculate Month's difference between StartDate and EndDate
		renewalExecutionDate = PDFReader.renewalExecutionDate;
		currentMonthlyRent = PDFReader.monthlyRent;
		priorMonthlyRent = PDFReader.previousMonthlyRent;
		renewalCoordinatorName = "HRG - Automation"; 
		newStartDate = PDFReader.startDate;
		newEndDate = PDFReader.endDate;
		renewalOnDate = RunnerClass.getCurrentDate();
		petRentAmount = PDFReader.petRent;
		
		try {
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.baseRent)).click().build().perform();
		    RunnerClass.driver.findElement(Locators.baseRent).click();
		    RunnerClass.driver.findElement(Locators.baseRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.baseRent).sendKeys(currentMonthlyRent);
		    Thread.sleep(3000);
		} catch(Exception e) {
		    RunnerClass.statusID=1;
		    e.printStackTrace();
		    RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Current Monthly Rent";
		    System.out.println("Issue - Other information - Base Rent");
		}
		
		if (PDFReader.residentBenefitsPackageAvailabilityCheck == true) 
		{ // If residentBenefitsPackageAvailabilityCheck is true,
			// Select the option for "enrolled in RBP for PM use"
			try 
			{
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.enrolledInRBPForPMUse)).build().perform();
				Select renewalStatusDropdown = new Select(RunnerClass.driver.findElement(Locators.enrolledInRBPForPMUse));
				renewalStatusDropdown.selectByValue(enrolledInRBPForPMUse);
			} catch (Exception e) 
			{
				RunnerClass.statusID=1;
				e.printStackTrace();
				RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Enrolled In RBP For PM Use";
				System.out.println("Issue - Other information - Enrolled In RBP For PM Use");
			}
		} 
		
		else 
		{ // If residentBenefitsPackageAvailabilityCheck is false,
			// Select the option for "not enrolled in RBP for PM use"
			try 
			{
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.enrolledInRBPForPMUseNo)).build().perform();
				Select renewalStatusDropdown = new Select(RunnerClass.driver.findElement(Locators.enrolledInRBPForPMUseNo));
				renewalStatusDropdown.selectByValue(enrolledInRBPForPMUseNo);
			} catch (Exception e) 
			{
				RunnerClass.statusID=1;
				e.printStackTrace();
				RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Enrolled In RBP For PM Use (No)";
				System.out.println("Issue - Other information - Enrolled In RBP For PM Use (No)");
			}
			Thread.sleep(2000);
		}

		if (PDFReader.residentBenefitsPackageAvailabilityCheck == true) 
		{ // If residentBenefitsPackageAvailabilityCheck is true,
			// Select the option for "RBP enrollment complete for SN use only"
			try 
			{
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.RBPEnrollmentCompleteForSNUseOnly)).build().perform();
				Select renewalStatusDropdown = new Select(RunnerClass.driver.findElement(Locators.RBPEnrollmentCompleteForSNUseOnly));
				renewalStatusDropdown.selectByValue(RBPenrollmentCompleteForSNUseOnly);
			} catch (Exception e) 
			{
				RunnerClass.statusID=1;
				e.printStackTrace();
				RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - RBP Enrollment Complete For SN Use Only";
				System.out.println("Issue - Other information - RBP Enrollment Complete For SN Use Only");
			}
		} 
		else 
		{ // If residentBenefitsPackageAvailabilityCheck is false,
			// Select the option for "RBP enrollment not complete for SN use only"
			try 
			{
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.RBPEnrollmentCompleteForSNUseOnlyNo)).build().perform();
				Select renewalStatusDropdown = new Select(RunnerClass.driver.findElement(Locators.RBPEnrollmentCompleteForSNUseOnlyNo));
				renewalStatusDropdown.selectByValue(RBPenrollmentCompleteForSNUseOnlyNo);
			} catch (Exception e) 
			{
				RunnerClass.statusID=1;
				e.printStackTrace();
				RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - RBP Enrollment Complete For SN Use Only (No)";
				System.out.println("Issue - Other information - RBP Enrollment Complete For SN Use Only (No)");
			}
		}
		Thread.sleep(2000);
		
		//Renewal Status
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.renewalStatusDropdown)).build().perform();
		Select renewalStatusDropdown = new Select(RunnerClass.driver.findElement(Locators.renewalStatusDropdown));
		renewalStatusDropdown.selectByValue(renewalStatus);
		}
		catch(Exception e)
		{
			RunnerClass.statusID=1;
			 e.printStackTrace();
			 RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Renewal Status";
			 System.out.println("Issue - Other information - Renewal Status");
		}
		
		//Renewal Follow - up Notes
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.renewalFollowUpNotes)).build().perform();
		RunnerClass.driver.findElement(Locators.renewalFollowUpNotes).click();
		RunnerClass.driver.findElement(Locators.renewalFollowUpNotes).sendKeys(Keys.chord(Keys.HOME));
		RunnerClass.driver.findElement(Locators.renewalFollowUpNotes).sendKeys(reneWalFollowupNotes);
	    }
	    catch(Exception e)
	    {
	    	RunnerClass.statusID=1;
	    	e.printStackTrace();
			RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Renewal Follow up Notes";
			System.out.println("Issue - Other information - Renewal Follow up Notes");
	    }
		
		//Renewal Execution Date
		try {
		    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.renewalExecutionDate)).build().perform();
		    RunnerClass.driver.findElement(Locators.renewalExecutionDate).click();
		    RunnerClass.driver.findElement(Locators.renewalExecutionDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.renewalExecutionDate).sendKeys(renewalExecutionDate);
		    Thread.sleep(2000);
		} catch(Exception e) {
		    RunnerClass.statusID=1;
		    e.printStackTrace();
		    RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Renewal Execution Date";
		    System.out.println("Issue - Other information - Renewal Renewal Execution Date");
		}

		//Current Monthly Rent
		try {
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.currentMonthlyRent)).click().build().perform();
		    RunnerClass.driver.findElement(Locators.currentMonthlyRent).click();
		    RunnerClass.driver.findElement(Locators.currentMonthlyRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.currentMonthlyRent).sendKeys(currentMonthlyRent);
		    Thread.sleep(3000);
		} catch(Exception e) {
		    RunnerClass.statusID=1;
		    e.printStackTrace();
		    RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Current Monthly Rent";
		    System.out.println("Issue - Other information - Current Monthly Rent");
		}

		//Prior Monthly Rent
		try {
		    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.priorMonthlyRent)).build().perform();
		    RunnerClass.driver.findElement(Locators.priorMonthlyRent).click();
		    RunnerClass.driver.findElement(Locators.priorMonthlyRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.priorMonthlyRent).sendKeys(priorMonthlyRent);
		    Thread.sleep(3000);
		} catch(Exception e) {
		    RunnerClass.statusID=1;
		    e.printStackTrace();
		    RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Prior Monthly Rent";
		    System.out.println("Issue - Other information - Prior Monthly Rent");
		}

		
		
		//Renewal Coordinator Name
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.renewalCoordinatorName)).build().perform();
		RunnerClass.driver.findElement(Locators.renewalCoordinatorName).click();
		RunnerClass.driver.findElement(Locators.renewalCoordinatorName).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.renewalCoordinatorName).sendKeys(renewalCoordinatorName);
		}
		catch(Exception e)
		{
			RunnerClass.statusID=1;
			e.printStackTrace();
			RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Renewal Coordinator Name";
			System.out.println("Issue - Other information - Renewal Coordinator Name");		
		}
		
		// Pet Rent Amount
				if(PDFReader.petFlag== true)
				{
					try
					{
					RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.petRentAmount)).build().perform();
					RunnerClass.driver.findElement(Locators.petRentAmount).click();
					RunnerClass.driver.findElement(Locators.petRentAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					RunnerClass.driver.findElement(Locators.petRentAmount).sendKeys(petRentAmount);
					}
					catch(Exception e)
					{
						RunnerClass.statusID=1;
						e.printStackTrace();
						RunnerClass.failedReason = RunnerClass.failedReason+","+"Other information - Pet Rent Amount";
						System.out.println("Issue - Other information - Pet Rent Amount");
					}
					
				}
				
		RunnerClass.js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(3000);
		try
		{
			if(AppConfig.saveButtonOnAndOff==true)
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).click(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
			else
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).click(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			RunnerClass.statusID=1;
			e.printStackTrace();
		}
		}
		
	  catch(Exception e)
	  {
		  RunnerClass.js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		  if(AppConfig.saveButtonOnAndOff==true)
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).click(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
		  else
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).click(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
	  }
		
			
		//Related Activities
		PropertyWare_InsertOtherInformation.RelatedActivities();
		
	}
	
	public static void RelatedActivities() throws Exception
	{
		
		
		try {
		    RunnerClass.driver.navigate().refresh();
 
		    RunnerClass.driver.findElement(Locators.relatedActivities_LeaseRenewal).click();
		    Thread.sleep(2000);
		    
		    try {
		    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.removeLeasingFee)).build().perform();
		    
		    
		    if(RunnerClass.driver.findElement(Locators.removeLeasingFee).isDisplayed()) 
		    {
		    RunnerClass.driver.findElement(Locators.removeLeasingFee).click();
		    }}
		    catch (Exception e1){}
		    
		    {
		    //Related Activities - New Start Date
		    RunnerClass.driver.findElement(Locators.relatedActivities_newStartDate).click();
		    RunnerClass.driver.findElement(Locators.relatedActivities_newStartDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.relatedActivities_newStartDate).sendKeys(newStartDate);
		    
		    //Click this to remove Calendar 
		    RunnerClass.driver.findElement(Locators.relatedActivities_newLeaseRenewalPopUpHeading).click();
		    
		    //Related Activities - New End Date
		    RunnerClass.driver.findElement(Locators.relatedActivities_newEndDate).click();
		    RunnerClass.driver.findElement(Locators.relatedActivities_newEndDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.relatedActivities_newEndDate).sendKeys(newEndDate);
		    
		    //Related Activities - Renewal On Date
		    RunnerClass.driver.findElement(Locators.relatedActivities_renewalOnDate).click();
		    RunnerClass.driver.findElement(Locators.relatedActivities_renewalOnDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.relatedActivities_renewalOnDate).sendKeys(renewalOnDate);
		    Thread.sleep(3000);
		    RunnerClass.driver.findElement(By.xpath("/html/body/div[3]/div[2]/table/tbody/tr/td[2]/form/div[16]/div[2]/table[2]/caption")).click();
		    
		 // Base Rent
		    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.baseRentActivities)).click().build().perform();
		    RunnerClass.driver.findElement(Locators.baseRentActivities).click();
		    RunnerClass.driver.findElement(Locators.baseRentActivities).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.baseRentActivities).sendKeys(currentMonthlyRent);
		    Thread.sleep(3000);
		    
		    
		    if (AppConfig.saveButtonOnAndOff) 
		    {
		        RunnerClass.driver.findElement(Locators.relatedActivities_save).click();
		    } else 
		    {
		        RunnerClass.driver.findElement(Locators.relatedActivities_cancel).click();
		    }
		    Thread.sleep(3000);
		    
		    
		}
		}
		catch (Exception e) 
		{
		    RunnerClass.statusID = 1;
		    RunnerClass.failedReason = RunnerClass.failedReason + "," + "Issue in adding Related Activities";
		    System.out.println("Issue in adding Related Activities");
		    e.printStackTrace();
		    
		} finally 
		{
		    try {
		        if (RunnerClass.driver.findElement(Locators.youMustCorrectTheFollowingErrorMessage).isDisplayed()) {
		            RunnerClass.statusID = 1;
		            RunnerClass.failedReason = RunnerClass.failedReason + "," + "Issue in adding Related Activities";
		            System.out.println("Issue in adding Related Activities");
		            RunnerClass.driver.findElement(Locators.relatedActivities_cancel).click();
		        }
		    } catch (Exception e) 
		    {
		        
		    }
		}
		
		}
	
	
	public static void clearExistingVariableValues()
	{
		renewalStatus="";
		reneWalFollowupNotes = "";
		renewalExecutionDate = "";
		currentMonthlyRent = "";
		priorMonthlyRent = "";
		renewalCoordinatorName = "";
		
		//Related Activities - Lease Renewal
		newStartDate = "";
		newEndDate = "";
		renewalOnDate = "";
		
	}

}
