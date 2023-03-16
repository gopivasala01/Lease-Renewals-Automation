package mainPackage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	//ConfigureValues
	public static boolean configureValues() throws Exception
	{
		try
		{
		//Clear all values in Auto Charges first
		String query1 = "Update automation.LeaseReneWalsAutoChargesConfiguration Set ChargeCode = null,Amount = null,StartDate=null,EndDate=null,Flag=null";
		DataBase.updateTable(query1);
				
		//Clear all values in Auto Charges first
		String query2 = "Update automation.LeaseReneWalsMoveInChargesConfiguration Set ChargeCode = null,Amount = null,StartDate=null,EndDate=null";
		DataBase.updateTable(query2);
		
		//Get all Required dates converted
		PDFReader.startDate = RunnerClass.convertDate(PDFReader.commencementDate);
		PDFReader.endDate = RunnerClass.convertDate(PDFReader.expirationDate);
		PDFReader.lastDayOfTheStartDate = RunnerClass.lastDateOfTheMonth(RunnerClass.firstDayOfMonth(PDFReader.startDate,-1));
		PDFReader.firstFullMonth = RunnerClass.firstDayOfMonth(PDFReader.startDate,1);
		PDFReader.secondFullMonth = RunnerClass.firstDayOfMonth(PDFReader.startDate,2);
		
		//Check if the Monthly Rent is already added in the Ledger, then take the Monthly Rent Start Date as "First Full Month" in Auto charges.
		PropertyWare_InsertData.verifyLedgerForMonhtlyRentStartDate();
		
		}
		catch(Exception e)
		{
			System.out.println("Issue in getting or Converting dates");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in getting or Converting dates";
			return false;
		}
		//Update dates for auto charges
		PropertyWare_ConsolidateValues.updateDates();
		//Adding values to the Auto Charges table
		if(PropertyWare_InsertData.addingValuesToTable()==false)
			return false;
		//Adding values to the Move In Charges table
		if(PropertyWare_InsertData.addingValuesToMoveInChargesTable()==false)
			return false;
		//Update Flag column in table
		PropertyWare_ConsolidateValues.decideAutoCharges();
		return true;
	}
	public static boolean verifyLedgerForMonhtlyRentStartDate()
	{
		try
		{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		RunnerClass.driver.findElement(Locators.ledgerTab).click();
		List<WebElement> existingMoveInCharges_ChargeCode = RunnerClass.driver.findElements(Locators.moveInCharges_List);
		List<WebElement> existingMoveInCharges_Date = RunnerClass.driver.findElements(Locators.moveInCharge_List_Amount);
		for(int i=0;i<existingMoveInCharges_ChargeCode.size();i++)
		{
			String chargeCode = existingMoveInCharges_ChargeCode.get(i).getText().trim();
			String date = existingMoveInCharges_Date.get(i).getText().trim();
			if(chargeCode.equals(AppConfig.getMonthlyRentChargeCode(RunnerClass.company))&&RunnerClass.startDate.equals(date))
			{
				PDFReader.dateCheckInLedgerForMonthlyRentStartDate = true;
				break;
			}
		}
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	//Add Move In Charges
	public static boolean addingMoveInCharges() throws Exception
	{
		try
		{
		 //Get All Auto Charges from Table
	     DataBase.getMoveInCharges();
		/*
		RunnerClass.driver.navigate().refresh();
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		RunnerClass.driver.findElement(Locators.ledgerTab).click();
		*/
		Thread.sleep(2000);
		List<WebElement> existingMoveInCharges_ChargeCodes = RunnerClass.driver.findElements(Locators.moveInCharges_List);
		List<WebElement> existingMoveInCharges_Amount = RunnerClass.driver.findElements(Locators.moveInCharge_List_Amount);
		
		for(int i=0;i<RunnerClass.moveInCharges.length;i++)
		{
			
				boolean availabilityCheck = false;
				String chargeCode = RunnerClass.moveInCharges[i][0];
				String amount = RunnerClass.moveInCharges[i][1];
				String startDate = RunnerClass.moveInCharges[i][2];
				String endDate = RunnerClass.moveInCharges[i][3];
				String description = RunnerClass.moveInCharges[i][4];
				
				for(int k=0;k<existingMoveInCharges_ChargeCodes.size();k++)
				{
					String autoChargeCodes = existingMoveInCharges_ChargeCodes.get(k).getText();
					String autoChargeAmount = existingMoveInCharges_Amount.get(k).getText();
					if(chargeCode.contains(autoChargeCodes)&&autoChargeAmount.substring(1).equals(amount))//&&(startDate.equals(autoChargeStartDate)||autoChargeEndDate.trim().equals("")))
					{
						availabilityCheck = true;
						System.out.println(description+" already available");
						break;
					}
				}
				//Add new Charge if it is not there
				if(availabilityCheck==false)
				{
					if(amount=="Error"||amount=="0.00")
					{
						System.out.println("Issue in Adding Move in charge - "+description);
						RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in Adding Move in charge - "+description;
						System.out.println(description+ " is not updated");
						RunnerClass.statusID=1;
					}
					
					else
					PropertyWare_InsertData.addingMoveInCharge(chargeCode, amount, startDate, endDate, description);
				}
				
		}
		return true;
		}
		catch(Exception e)
		{
			RunnerClass.statusID=1;
			e.printStackTrace();
			System.out.println("Issue in Adding Move in charges");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in Adding Move in charges";
			RunnerClass.driver.navigate().refresh();
			RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			RunnerClass.driver.findElement(Locators.summaryTab).click();
			return false;
		}
	}
	
	//Navigate to Auto Charges Section and update existing auto charges
	public static boolean clearExistingAutoCharges() throws Exception
	{
		try
		{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		RunnerClass.driver.findElement(Locators.summaryTab).click();
		Thread.sleep(2000);
	     RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	     RunnerClass.driver.findElement(Locators.summaryEditButton).click();
	     RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
	     List<WebElement> existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
	     List<WebElement> existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
		 List<WebElement> endDates = RunnerClass.driver.findElements(Locators.autoCharge_List_EndDates);
		 List<WebElement> editButtons = RunnerClass.driver.findElements(Locators.autoCharge_MonthlyRentEditButton);
	     //Get All Auto Charges from Table
	     DataBase.getAutoCharges();
	     boolean monthlyRentChargeClosed = false;
	     /*
			for(int i=0;i<RunnerClass.autoCharges.length;i++)
			{
				boolean chargeModified = false;
				String previousChargeDescription ="";
				//String autoChargeCode ="";
				String chargeCode = RunnerClass.autoCharges[i][0];
				String amount = RunnerClass.autoCharges[i][1];
				previousChargeDescription = RunnerClass.autoCharges[i][4];
				*/
				for(int k=0;k<existingAutoCharges.size();k++)
				{
					existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
				    existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
					endDates = RunnerClass.driver.findElements(Locators.autoCharge_List_EndDates);
					editButtons = RunnerClass.driver.findElements(Locators.autoCharge_MonthlyRentEditButton);
					
					existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
					
					String autoChargeCodes = existingAutoCharges.get(k).getText();
					//autoChargeCode = autoChargeCodes;
					String autoChargeAmount = existingAutoChargeAmounts.get(k).getText();
					String endDateAutoCharge = endDates.get(k).getText();
					System.out.println(autoChargeCodes +"  ||  "+autoChargeAmount +"  ||  "+endDateAutoCharge);
					//And Amount should not be Monthly Rent Amount -- Remove this condition for Demo and Prod as the current monthly rent could be previous monthly rent
					//And End Date should be Empty
				    //Or Current Month Amount can be error
					//And Amount should not be Increased Rent amount
					//And Increased Rent Should not be Empty
					
					if(endDateAutoCharge.trim().equals(""))
					{
 						if(autoChargeCodes.equals(AppConfig.getMonthlyRentChargeCode(RunnerClass.company))&&monthlyRentChargeClosed== false)
						{
							PDFReader.previousMonthlyRent = autoChargeAmount;
							editButtons.get(k).click();
							PropertyWare_InsertData.editingExistingAutoCharge();
	                		//chargeModified = true;
	                		monthlyRentChargeClosed = true;
	                		PropertyWare_InsertData.saveAnAutoCharge();
	                		continue;
	                		//break;
						}
						if((autoChargeCodes.equals(AppConfig.getHVACAirFilterFeeChargeCode(RunnerClass.company))&&PDFReader.residentBenefitsPackageAvailabilityCheck==true)&&(!autoChargeAmount.replaceAll("[^0-9]", "").equals(PDFReader.HVACAirFilterFee.replaceAll("[^0-9]", ""))||PDFReader.HVACAirFilterFee!=""))
						{
							editButtons.get(k).click();
							PropertyWare_InsertData.editingExistingAutoCharge();
							//break;
							PropertyWare_InsertData.saveAnAutoCharge();
							continue;
						}
						if((autoChargeCodes.equals(AppConfig.getResidentBenefitsPackageChargeCode(RunnerClass.company))&&PDFReader.HVACFilterFlag==true)&&(!autoChargeAmount.replaceAll("[^0-9]", "").equals(PDFReader.residentBenefitsPackage.replaceAll("[^0-9]", ""))||PDFReader.residentBenefitsPackage!=""))
						{
							editButtons.get(k).click();
							PropertyWare_InsertData.editingExistingAutoCharge();
							//break;
							PropertyWare_InsertData.saveAnAutoCharge();
							continue;
						}
						if(autoChargeCodes.equals(AppConfig.getPetRentChargeCode(RunnerClass.company))&&(!autoChargeAmount.replaceAll("[^0-9]", "").equals(PDFReader.petRent.replaceAll("[^0-9]", ""))||PDFReader.petRent!=""))
						{
							editButtons.get(k).click();
							PropertyWare_InsertData.editingExistingAutoCharge();
							//break;
							PropertyWare_InsertData.saveAnAutoCharge();
							continue;
						}
						/*
						if(chargeCode.replaceAll("[^A-Za-z0-9-]", "").contains(autoChargeCodes.replaceAll("[^A-Za-z0-9-]", ""))&&!autoChargeAmount.substring(1).replaceAll("[^0-9]", "").equals(amount.replaceAll("[^0-9]", ""))&&!autoChargeCodes.equals(AppConfig.getMonthlyRentChargeCode(RunnerClass.company)))
						{
							editButtons.get(k).click();
							PropertyWare_InsertData.editingExistingAutoCharge();
							break;
						}
						*/
					}
					
				}	   
				
				 
			//} //for loop close
			
	     return true;
		}
		catch(Exception e)
		{
			RunnerClass.statusID=1;
			e.printStackTrace();
			RunnerClass.failedReason = RunnerClass.failedReason+","+"Something went wrong in clearing previous auto charges";
			System.out.println("Something went wrong in clearing previous auto charges");
			RunnerClass.driver.navigate().refresh();
			RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			return false;
		}
	}
	
	public static void saveAnAutoCharge() throws Exception
	{
		  RunnerClass.js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		  if(AppConfig.saveButtonOnAndOff==true)
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).click(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
		  else
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).click(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
          Thread.sleep(2000);
          RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			RunnerClass.driver.findElement(Locators.summaryEditButton).click();
		     RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
          
	}
	
	public static void editingExistingAutoCharge() throws Exception
	{
		
		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).clear();
		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		System.out.println("Existing Auto Charge is Edited");
		RunnerClass.driver.findElement(Locators.autoCharge_EndDate).sendKeys(PDFReader.lastDayOfTheStartDate);
		if(AppConfig.saveButtonOnAndOff==false)
			RunnerClass.driver.findElement(Locators.autoCharge_CancelButton).click();
			else 
			RunnerClass.driver.findElement(Locators.autoCharge_SaveButton).click();
		
		Thread.sleep(2000);
		try
		{
		RunnerClass.driver.switchTo().alert().accept();
	    }
	    catch(Exception e)
		{}
		Thread.sleep(2000);
	}
	
	public static boolean addingNewAutoCharges() throws Exception
	{
      try
      {
		
		//RunnerClass.driver.navigate().refresh();
		//RunnerClass.driver.findElement(Locators.summaryEditButton).click();
		Thread.sleep(2000);
		//RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		//RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.newAutoCharge)).build().perform();
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
				existingAutoCharges = RunnerClass.driver.findElements(Locators.autoCharge_List);
				existingAutoChargeAmounts = RunnerClass.driver.findElements(Locators.autoCharge_List_Amounts);
				startDates = RunnerClass.driver.findElements(Locators.autoCharge_List_startDates);
				endDates = RunnerClass.driver.findElements(Locators.autoCharge_List_EndDates);
				
				String autoChargeCodes = existingAutoCharges.get(k).getText();
				String autoChargeAmount = existingAutoChargeAmounts.get(k).getText();
				String autoChargeStartDate = startDates.get(k).getText();
				String autoChargeEndDate = endDates.get(k).getText();
				if(chargeCode.contains(autoChargeCodes.replaceAll(".", ""))&&autoChargeAmount.substring(1).replaceAll("[^0-9]", "").equals(amount.replaceAll("[^0-9]", ""))&&(startDate.equals(autoChargeStartDate)||autoChargeEndDate.trim().equals("")))
				{
					availabilityCheck = true;
					System.out.println(description+" already available");
					break;
				}
			}
			if(availabilityCheck==false)
			{
				if(amount=="Error")
				{
					System.out.println(" issue in adding Auto Charge - "+description);
					RunnerClass.failedReason = RunnerClass.failedReason+","+" issue in adding Auto Charge - "+description;
					RunnerClass.statusID=1;
				}
				else
				PropertyWare_InsertData.addingAnAutoCharge(chargeCode, amount, startDate,endDate, description);
			}
			
		}
		 if(AppConfig.saveButtonOnAndOff==true)
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).click(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
			  else
				RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).click(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
  Thread.sleep(2000);
		return true;
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
		  RunnerClass.failedReason = RunnerClass.failedReason+","+"Something went wrong in adding auto charges";
		  System.out.println("Something went wrong in adding auto charges");
		  RunnerClass.driver.navigate().refresh();
		  return false;
      }
		
	}
	
	
	
	public static boolean addingAnAutoCharge(String accountCode, String amount, String startDate,String endDate,String description) throws Exception
	{
		try
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
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			try
			{
			e.printStackTrace();
			RunnerClass.statusID=1;
			System.out.println("Issue in adding Move in Charge"+description);
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in adding Auto Charge - "+description;
			RunnerClass.driver.findElement(Locators.autoCharge_CancelButton).click();
			return false;	
			}
			catch(Exception e2)
			{
				RunnerClass.driver.navigate().refresh();
			}
		}
		return true;
	}
	
	public static boolean addingMoveInCharge(String accountCode, String amount, String startDate,String endDate,String description) throws Exception
	{
		try
		{
		RunnerClass.driver.findElement(Locators.newCharge).click();
		Thread.sleep(2000);
		//Account code
		Select AutoChargesDropdown = new Select(RunnerClass.driver.findElement(Locators.accountDropdown));
		AutoChargesDropdown.selectByVisibleText(accountCode);
		//Reference
		Thread.sleep(2000);
		RunnerClass.driver.findElement(Locators.referenceName).sendKeys(description);
		Thread.sleep(2000);
		//Amount
		RunnerClass.driver.findElement(Locators.moveInChargeAmount).click();
		RunnerClass.actions.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(2000);
		RunnerClass.driver.findElement(Locators.moveInChargeAmount).sendKeys(amount); 
		Thread.sleep(2000);
		//Start Date
		RunnerClass.driver.findElement(Locators.moveInChargeDate).clear();
		Thread.sleep(2000);
		RunnerClass.driver.findElement(Locators.moveInChargeDate).sendKeys(startDate);
		//Save or Cancel button
		Thread.sleep(2000);
		if(AppConfig.saveButtonOnAndOff==false)
		RunnerClass.driver.findElement(Locators.moveInChargeCancel).click();
		else 
		RunnerClass.driver.findElement(Locators.moveInChargeSaveButton).click();
		Thread.sleep(2000);
		 RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(2));
		try
		{
			if(RunnerClass.driver.findElement(Locators.somethingWrongInSavingCharge).isDisplayed())
			{
				RunnerClass.driver.findElement(Locators.moveInChargeCancel).click();
			}
			
		}
		catch(Exception e)
		{}
		 RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(100));
		return true;
		}
		catch(Exception e)
		{
			RunnerClass.statusID=1;
			RunnerClass.driver.navigate().refresh();
			RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			RunnerClass.driver.findElement(Locators.summaryTab).click();
			e.printStackTrace();
			System.out.println("Issue in adding Move in Charge"+description);
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in adding Move in Charge - "+description;
			return false;	
		}
	}
	
	public static boolean addingValuesToTable()
	{
		try
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
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Issue in adding values to Auto charges table");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Internal Error - consolidating auto charges";
			return false;
		}
	}
	
	public static boolean addingValuesToMoveInChargesTable()
	{
		try
		{
		String query =null;
		for(int i=1;i<=1;i++)
		{
			switch(i)
			{
			case 1:
				query = "Update automation.LeaseReneWalsMoveInChargesConfiguration Set ChargeCode = '"+AppConfig.getResidentRenewalAdminFee(RunnerClass.company)+"',Amount = '"+PDFReader.leaseRenewalFee+"',StartDate='"+monthlyRent_StartDate+"',EndDate='' where ID=1";
				break;
			}
		}
		DataBase.updateTable(query);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Issue in adding values to Auto charges table");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Internal Error - consolidating Move In charges";
			return false;
		}
	}
}
