package mainPackage;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PropertyWare_InsertData 
{
	public static String proratedRent_StartDate = "";
	public static String monthlyRent_StartDate = "";
	public static String increasedRent_StartDate = "";
	public static String ResidentBenefitPackage_StartDate = "";
	public static String HVACAirFilterFee_StartDate = "";
	public static String petRent_StartDate = "";
	
	//Navigate to Auto Charges Section and update existing auto charges
	public static boolean clearExistingAutoCharges() throws Exception
	{
		//Get all Required dates converted
		PDFReader.startDate = RunnerClass.convertDate(PDFReader.commencementDate);
		PDFReader.lastDayOfTheStartDate = RunnerClass.lastDateOfTheMonth(PDFReader.startDate);
		PDFReader.firstFullMonth = RunnerClass.firstDayOfMonth(PDFReader.startDate);
		
		
		
		proratedRent_StartDate ="";
		monthlyRent_StartDate = "";
		increasedRent_StartDate = "";
		ResidentBenefitPackage_StartDate = "";
		HVACAirFilterFee_StartDate = "";
		petRent_StartDate = "";
		
		try
		{
	     RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	     RunnerClass.driver.findElement(Locators.summaryEditButton).click();
	     RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
	     
	     try
			{
			List<WebElement> existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
			List<WebElement> existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
			List<WebElement> endDates = RunnerClass.driver.findElements(Locators.autoCharge_List_EndDates);
			List<WebElement> editButtons = RunnerClass.driver.findElements(Locators.autoCharge_MonthlyRentEditButton);
			List<WebElement> petRentList = RunnerClass.driver.findElements(Locators.autoCharge_petRentList);
			for(int k=0;k<existingAutoCharges.size();k++)
			{
				String autoChargeCodes = existingAutoCharges.get(k).getText();
				String autoChargeAmount = existingAutoChargeAmounts.get(k).getText();
				String petRentAmount = petRentList.get(k).getText();
				String endDate = endDates.get(k).getText();
                if(autoChargeCodes.trim().contains(AppConfig.getMonthlyRentChargeCode(RunnerClass.company)))				
				{
                	if(endDates.get(k).getText().trim().equals("")&&!autoChargeAmount.contains(PDFReader.monthlyRent))
                	{
                		editButtons.get(k).click();
                		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).clear();
                		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
                		System.out.println("Existing Auto Charge is Edited");
                		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).sendKeys(PDFReader.lastDayOfTheStartDate);
                		if(AppConfig.saveButtonOnAndOff==false)
                			RunnerClass.driver.findElement(Locators.autoCharge_CancelButton).click();
                			else 
                			RunnerClass.driver.findElement(Locators.autoCharge_SaveButton).click();
                		continue;
                	}
				}
               // Comparing Pet Rent in PDF and Auto Charges list
        		if(autoChargeCodes.trim().contains(AppConfig.getPetRentChargeCode(RunnerClass.company)))				
                {
        		 if(petRentAmount.substring(1).equalsIgnoreCase(PDFReader.petRent))
        		 {
        		  System.out.println("Pet Rent is same for Renewal");
        		 }
        		 else 
        		 {
        		 System.out.println("Pet Rent is not Matched");
        		 PropertyWare_InsertData.addingAnAutoCharge(AppConfig.getPetRentChargeCode(RunnerClass.company), PDFReader.petRent, PDFReader.firstFullMonth, "Pet Rent");
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
	
	public static void addingNewAutoCharges()
	{
		boolean monthlyRentAvailabilityCheck = false;
		try
		{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
		List<WebElement> existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
		List<WebElement> existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
		for(int k=0;k<existingAutoCharges.size();k++)
		{
			String autoChargeCodes = existingAutoCharges.get(k).getText();
			String autoChargeAmount = existingAutoChargeAmounts.get(k).getText();
			if(autoChargeCodes.trim().contains(AppConfig.getMonthlyRentChargeCode(RunnerClass.company))&&autoChargeAmount.substring(1).equals(PDFReader.monthlyRent))
			{
				System.out.println("Monthly rent is already available");
				monthlyRentAvailabilityCheck= true;
				break;
			}
		}
		if(monthlyRentAvailabilityCheck==false)
        {
			PropertyWare_InsertData.addingAnAutoCharge(AppConfig.getPetRentChargeCode(RunnerClass.company), PDFReader.monthlyRent, PDFReader.firstFullMonth, "Monthly Rent");
	    }
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong in adding new auto charges");
		}
	}
	
	public static void addingAnAutoCharge(String accountCode, String amount, String startDate,String description) throws Exception
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
		for(int i=1;i<=7;i++)
		{
			switch(i)
			{
			case 1:
				query = "Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.proratedRent+"',StartDate='"+proratedRent_StartDate+"',EndDate='' where ID=1";
				break;
			case 2:
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.monthlyRent+"',StartDate='"+monthlyRent_StartDate+"',EndDate='' where ID=2";
				break;
			case 3: 
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.increasedRent_amount+"',StartDate='"+increasedRent_StartDate+"',EndDate='' where ID=3";	
				break;
			case 4:
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.residentBenefitsPackage+"',StartDate='"+ResidentBenefitPackage_StartDate+"',EndDate='' where ID=4";
				break;
			case 5:
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.HVACAirFilterFee+"',StartDate='"+HVACAirFilterFee_StartDate+"',EndDate='' where ID=5";
				break;
			case 6: 
				query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = '"+AppConfig.getPetRentChargeCode(RunnerClass.company)+"',Amount = '"+PDFReader.petRent+"',StartDate='"+petRent_StartDate+"',EndDate='' where ID=6";
				break;
			}
			DataBase.updateTable(query);
		}
	}
}
