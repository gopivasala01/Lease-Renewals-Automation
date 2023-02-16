package mainPackage;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PropertyWare_InsertData 
{
	public static String proratedRent_StartDate = "";
	public static String proratedRent_EndDate = "";
	public static String monthlyRent_StartDate = "";
	public static String increasedRent_StartDate = "";
	public static String ResidentBenefitPackage_StartDate = "";
	public static String HVACAirFilterFee_StartDate = "";
	public static String petRent_StartDate = "";
	public static String increasedRent_previousRentStartDate ="";
	
	
	//Navigate to Auto Charges Section and update existing auto charges
	public static boolean clearExistingAutoCharges() throws Exception
	{
		//Clear all values in table first
		String query = "Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = null,Amount = null,StartDate=null,EndDate=null,Flag=null";
		DataBase.updateTable(query);
		
		//Get all Required dates converted
		PDFReader.startDate = RunnerClass.convertDate(PDFReader.commencementDate);
		PDFReader.endDate = RunnerClass.convertDate(PDFReader.expirationDate);
		PDFReader.lastDayOfTheStartDate = RunnerClass.lastDateOfTheMonth(PDFReader.startDate);
		PDFReader.firstFullMonth = RunnerClass.firstDayOfMonth(PDFReader.startDate,1);
		PDFReader.secondFullMonth = RunnerClass.firstDayOfMonth(PDFReader.startDate,2);
		
		//Update dates for auto charges
		PropertyWare_ConsolidateValues.updateDates();
		//Adding values to the table
		PropertyWare_InsertData.addingValuesToTable();
		//Update Flag column in table
		PropertyWare_ConsolidateValues.decideAutoCharges();
		
		try
		{
	     RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	     RunnerClass.driver.findElement(Locators.summaryEditButton).click();
	     RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
	     
	     //Get All Auto Charges from Table
	     DataBase.getAutoCharges();
	     
	     try
			{
			List<WebElement> existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
			List<WebElement> existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
			List<WebElement> endDates = RunnerClass.driver.findElements(Locators.autoCharge_List_EndDates);
			List<WebElement> editButtons = RunnerClass.driver.findElements(Locators.autoCharge_MonthlyRentEditButton);
			List<WebElement> petRentList = RunnerClass.driver.findElements(Locators.autoCharge_petRentList);
			
			for(int i=0;i<RunnerClass.autoCharges.length;i++)
			{
				String chargeCode = RunnerClass.autoCharges[i][0];
				String amount = RunnerClass.autoCharges[i][1];
				
				for(int k=0;k<existingAutoCharges.size();k++)
				{
					String autoChargeCodes = existingAutoCharges.get(k).getText();
					String autoChargeAmount = existingAutoChargeAmounts.get(k).getText();
					String endDateAutoCharge = endDates.get(k).getText();
					//String autoChargeStartDate = startDates.get(k).getText();
					if(chargeCode.contains(autoChargeCodes)&&!autoChargeAmount.substring(1).equals(amount)&&endDateAutoCharge.trim().equals("")||amount=="Error")
					{
						PDFReader.previousMonthlyRent = autoChargeAmount;
						editButtons.get(k).click();
                		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).clear();
                		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
                		System.out.println("Existing Auto Charge is Edited");
                		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).sendKeys(PDFReader.lastDayOfTheStartDate);
                		if(AppConfig.saveButtonOnAndOff==false)
                			RunnerClass.driver.findElement(Locators.autoCharge_CancelButton).click();
                			else 
                			RunnerClass.driver.findElement(Locators.autoCharge_SaveButton).click();
					}
				}	   
	     
			} //for loop close
			}
	     
			catch(Exception e)
			{
				
			}
		}
		catch(Exception e)
		{
			RunnerClass.failedReason = "Something went wrong in clearing previous auto charges";
			System.out.println("Something went wrong in clearing previous auto charges");
			return false;
		}
	return true;
	}
	
	public static void addingNewAutoCharges() throws Exception
	{
		//Get all Auto Charges
		
		RunnerClass.driver.navigate().refresh();
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
		List<WebElement> existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
		List<WebElement> existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
		List<WebElement> startDates = RunnerClass.driver.findElements(Locators.autoCharge_List_startDates);
		List<WebElement> endDates = RunnerClass.driver.findElements(Locators.autoCharge_List_EndDates);
		for(int i=0;i<RunnerClass.autoCharges.length;i++)
		{
			boolean availabilityCheck = false;
			String chargeCode = RunnerClass.autoCharges[i][0];
			String amount = RunnerClass.autoCharges[i][1];
			String startDate = RunnerClass.autoCharges[i][2];
			String endDate = RunnerClass.autoCharges[i][3];
			String description = RunnerClass.autoCharges[i][4];
			
			for(int k=0;k<existingAutoCharges.size();k++)
			{
				String autoChargeCodes = existingAutoCharges.get(k).getText();
				String autoChargeAmount = existingAutoChargeAmounts.get(k).getText();
				String autoChargeStartDate = startDates.get(k).getText();
				String autoChargeEndDate = endDates.get(k).getText();
				if(chargeCode.contains(autoChargeCodes)&&autoChargeAmount.substring(1).equals(amount)&&(startDate.equals(autoChargeStartDate)||autoChargeEndDate.trim().equals("")))
				{
					availabilityCheck = true;
					System.out.println(description+" already available");
					break;
				}
			}
			if(availabilityCheck==false)
			{
				if(amount=="Error")
					System.out.println(description+ " is not updated");
				else
				PropertyWare_InsertData.addingAnAutoCharge(chargeCode, amount, startDate,endDate, description);
			}
			
		}
		
	}
	
	public static void addingAnAutoCharge(String accountCode, String amount, String startDate,String endDate,String description) throws Exception
	{
		RunnerClass.driver.findElement(Locators.newAutoCharge).click();
		 
	    //Charge Code
		Select autoChargesDropdown = new Select(RunnerClass.driver.findElement(Locators.accountDropdown));
		autoChargesDropdown.selectByVisibleText(accountCode); //
					
		//Start Date
		RunnerClass.driver.findElement(Locators.autoCharge_StartDate).clear();
		Thread.sleep(500);
		RunnerClass.driver.findElement(Locators.autoCharge_StartDate).sendKeys(startDate);
					
		//click this to hide calendar UI
		RunnerClass.driver.findElement(Locators.autoCharge_refField).click();
		//Amount
		RunnerClass.driver.findElement(Locators.autoCharge_Amount).click();
		RunnerClass.actions.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).build().perform();
		RunnerClass.driver.findElement(Locators.autoCharge_Amount).sendKeys(amount);
		Thread.sleep(500);
					
		//Description
		RunnerClass.driver.findElement(Locators.autoCharge_Description).sendKeys(description);
		
		//Save or Cancel
		if(AppConfig.saveButtonOnAndOff==false)
		RunnerClass.driver.findElement(Locators.autoCharge_CancelButton).click();
		else 
		RunnerClass.driver.findElement(Locators.autoCharge_SaveButton).click();
	}
	
	public static void addingValuesToTable()
	{
		String query =null;
		for(int i=1;i<=6;i++)
		{
			switch(i)
			{
			case 1:
				query = "Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getProrateRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.proratedRent+"',StartDate='"+proratedRent_StartDate+"',EndDate='' where ID=1";
				break;
			case 2:
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getMonthlyRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.monthlyRent+"',StartDate='"+monthlyRent_StartDate+"',EndDate='"+PDFReader.increasedRent_previousRentEndDate+"' where ID=2";
				break;
			case 3: 
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getIncreasedRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.increasedRent_amount+"',StartDate='"+PDFReader.increasedRent_newStartDate+"',EndDate='' where ID=3";	
				break;
			case 4:
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getResidentBenefitsPackageChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.residentBenefitsPackage+"',StartDate='"+ResidentBenefitPackage_StartDate+"',EndDate='' where ID=4";
				break;
			case 5:
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getHVACAirFilterFeeChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.HVACAirFilterFee+"',StartDate='"+HVACAirFilterFee_StartDate+"',EndDate='' where ID=5";
				break;
			case 6: 
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.petRent+"',StartDate='"+petRent_StartDate+"',EndDate='' where ID=6";
				break;
			}
		}
		DataBase.updateTable(query);
	}
}
