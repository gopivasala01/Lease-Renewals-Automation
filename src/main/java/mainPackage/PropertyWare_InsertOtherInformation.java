package mainPackage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class PropertyWare_InsertOtherInformation 
{
	public static String renewalStatus="";
	public static String reneWalFollowupNotes = "";
	public static String renewalExecutionDate = "";
	public static String currentMonthlyRent = "";
	public static String priorMonthlyRent = "";
	public static String renewalCoordinatorName = "";
	
	//Related Activities - Lease Renewal
	public static String newStartDate = "";
	public static String newEndDate = "";
	public static String renewalOnDate = "";
	
	public static void addingOtherInformation()
	{
		
		//Clear all existing values in variables
		PropertyWare_InsertOtherInformation.clearExistingVariableValues();
		//Adding values to all variables 
		
		renewalStatus = "CHARGE RENEWAL FEE - ANNUAL";
		reneWalFollowupNotes = ""; //Need to calculate Month's difference between StartDate and EndDate
		renewalExecutionDate = RunnerClass.getCurrentDate();
		currentMonthlyRent = PDFReader.monthlyRent;
		priorMonthlyRent = PDFReader.previousMonthlyRent;
		renewalCoordinatorName = "HRG - Automation"; 
		newStartDate = PDFReader.startDate;
		newEndDate = PDFReader.endDate;
		renewalOnDate = RunnerClass.getCurrentDate();
		
		//Renewal Status
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.renewalStatusDropdown)).build().perform();
		Select renewalStatusDropdown = new Select(RunnerClass.driver.findElement(Locators.renewalStatusDropdown));
		renewalStatusDropdown.selectByValue(renewalStatus);
		}
		catch(Exception e)
		{
			
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
		
	    }
		//Renewal Execution Date
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.renewalExecutionDate)).build().perform();
		RunnerClass.driver.findElement(Locators.renewalExecutionDate).click();
		RunnerClass.driver.findElement(Locators.renewalExecutionDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.renewalExecutionDate).sendKeys(renewalExecutionDate);
		}
	    catch(Exception e)
	    {
		
	    }
        //Current Monthly Rent
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.currentMonthlyRent)).build().perform();
		RunnerClass.driver.findElement(Locators.currentMonthlyRent).click();
		RunnerClass.driver.findElement(Locators.currentMonthlyRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.currentMonthlyRent).sendKeys(currentMonthlyRent);
		}
		catch(Exception e)
		{
			
		}
		
		//Prior Monthly Rent
		try
		{
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.priorMonthlyRent)).build().perform();
		RunnerClass.driver.findElement(Locators.priorMonthlyRent).click();
		RunnerClass.driver.findElement(Locators.priorMonthlyRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.priorMonthlyRent).sendKeys(priorMonthlyRent);
		}
		catch(Exception e)
		{
			
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
					
		}
		
		RunnerClass.js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		try
		{
			if(AppConfig.saveButtonOnAndOff==true)
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).click(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).click(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		//Related Activities
		
		try
		{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.relatedActivities_LeaseRenewal)).build().perform();
		RunnerClass.driver.findElement(Locators.relatedActivities_LeaseRenewal).click();
		}
		catch(Exception e)
		{
			
		}
		//Related Activities - New Start Date
		try
		{
		RunnerClass.driver.findElement(Locators.relatedActivities_newStartDate).click();
		//RunnerClass.driver.findElement(Locators.relatedActivities_newStartDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.relatedActivities_newStartDate).sendKeys(newStartDate);
		//Click this to remove Calendar 
		RunnerClass.driver.findElement(Locators.relatedActivities_newLeaseRenewalPopUpHeading).click();
		}
		catch(Exception e)
		{
							
		}
		
		//Related Activities - New End Date
		try
		{
		RunnerClass.driver.findElement(Locators.relatedActivities_newEndDate).click();
		RunnerClass.driver.findElement(Locators.relatedActivities_newEndDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.relatedActivities_newEndDate).sendKeys(newEndDate);
		}
		catch(Exception e)
		{
									
		}
		
		//Related Activities - Renewal On Date
		try
		{
	    RunnerClass.driver.findElement(Locators.relatedActivities_renewalOnDate).click();
		RunnerClass.driver.findElement(Locators.relatedActivities_renewalOnDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		RunnerClass.driver.findElement(Locators.relatedActivities_renewalOnDate).sendKeys(renewalOnDate);
		}
		catch(Exception e)
		{
											
		}
		
		try
		{
			if(AppConfig.saveButtonOnAndOff==true)
			RunnerClass.driver.findElement(Locators.relatedActivities_save).click();
			RunnerClass.driver.findElement(Locators.relatedActivities_cancel).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
