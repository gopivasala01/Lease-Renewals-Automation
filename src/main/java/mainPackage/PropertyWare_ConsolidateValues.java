package mainPackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PropertyWare_ConsolidateValues 
{
	public static void decideAutoCharges()
	{
		String autoCharges ="";
		String query ="";
		//if(RunnerClass.portfolioType=="MCH")
		//{
			if(PDFReader.petFlag==false)
			{
				if(PDFReader.residentBenefitsPackageAvailabilityCheck==true && RunnerClass.company!="Hawaii"&& RunnerClass.company!="Chicago PFW")
				{
					
					if(PDFReader.incrementRentFlag == true)
						query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,3,4)";
					else 
						query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,4)";
				}
				
			   else
			    {
				if(PDFReader.incrementRentFlag == true)
					query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,3)";
				else 
					query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2)";
				}
				
              // DataBase.updateTable(query);				
		     }
			else
			{
			   if(PDFReader.petFlag==true&&PDFReader.petSecurityDepositFlag==false)
				{
				   if(PDFReader.residentBenefitsPackageAvailabilityCheck==true && RunnerClass.company!="Hawaii"&& RunnerClass.company!="Chicago PFW")
					{
						
					if(PDFReader.incrementRentFlag == true)
						
						query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,3,4,6)";
					else 
						query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,4,6)";
						}
						
				}
			   
					else
					{
						if(PDFReader.incrementRentFlag == true)
							query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,3,6)";
						else 
							query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (2,6)";
					}
					
				}
			DataBase.updateTable(query);
			
			if(((RunnerClass.company.equals("Boise")||RunnerClass.company.equals("Idaho Falls"))&&PDFReader.residentUtilityBillFlag==true&&(!PDFReader.RUBS.equals("Error"))))
			{
				query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in (7)";
				DataBase.updateTable(query);
			}
			
			
			
			
			//}
		/*
		//Other Portfolios
		if(RunnerClass.portfolioType=="Others")
		{
			if(PDFReader.petFlag==false)
			{
				if(PDFReader.residentBenefitsPackageAvailabilityCheck==true)
				{
					if(PDFReader.incrementRentFlag == true)
					autoCharges = "1,2,3,4";
					else autoCharges = "1,2,4";
				}
			   else
			    {
				if(PDFReader.incrementRentFlag == true)
				autoCharges = "1,2,3,5";
				else autoCharges = "1,2,5";
				}
				query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in ("+autoCharges+")";
		     }
			else
			{
				if(PDFReader.petFlag==true&&PDFReader.petSecurityDepositFlag==false)
				{
					if(PDFReader.residentBenefitsPackageAvailabilityCheck==true)
					{
					if(PDFReader.incrementRentFlag == true)
					autoCharges = "1,2,3,4,6";
					else autoCharges = "1,2,4,6";
					}
					else
					{
						if(PDFReader.incrementRentFlag == true)
						autoCharges = "1,2,3,5,6";
						else autoCharges = "1,2,5,6";
					}
					query = "update automation.LeaseReneWalsAutoChargesConfiguration Set Flag = 1 where ID in ("+autoCharges+")";
				}
	         }
      }
      */
		
	}
	
	public static void updateDates() throws Exception
	{
		    PropertyWare_InsertData.proratedRent_StartDate ="";
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date startDate;
		    Date renewalExecutionDate;
		    startDate = dateFormat.parse(PDFReader.startDate);
			renewalExecutionDate = dateFormat.parse(PDFReader.renewalExecutionDate);
		    if(PDFReader.startDate.split("/")[1].equals("1")||PDFReader.startDate.split("/")[1].equals("01"))
		    {
		    	//If there is already a monthly rent charge in Ledger,then auto charge start date is first full month
		    	if (PDFReader.dateCheckInLedgerForMonthlyRentStartDate || startDate.before(renewalExecutionDate)) 
		    	{
		    		PropertyWare_InsertData.monthlyRent_StartDate = PDFReader.firstFullMonth;
					PropertyWare_InsertData.ResidentBenefitPackage_StartDate = PDFReader.firstFullMonth;
					PropertyWare_InsertData.petRent_StartDate = PDFReader.firstFullMonth;
		    	}
		    	else
		    	{
		    	PropertyWare_InsertData.monthlyRent_StartDate = PDFReader.startDate;
				PropertyWare_InsertData.ResidentBenefitPackage_StartDate = PDFReader.startDate;
				PropertyWare_InsertData.petRent_StartDate = PDFReader.startDate;
		    	}
		    }
		    else
		    {
			PropertyWare_InsertData.monthlyRent_StartDate = PDFReader.firstFullMonth;
			PropertyWare_InsertData.ResidentBenefitPackage_StartDate = PDFReader.firstFullMonth;
			PropertyWare_InsertData.petRent_StartDate = PDFReader.firstFullMonth;
		    }
			PropertyWare_InsertData.increasedRent_previousRentStartDate = PDFReader.startDate;
		    
	}
	public static void getRentCodeForArizona() throws Exception
	{
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		RunnerClass.driver.findElement(Locators.ledgerTab).click();
		Thread.sleep(2000);
		RunnerClass.actions.sendKeys(Keys.ESCAPE).build().perform();
		RunnerClass.driver.findElement(Locators.newCharge).click();
		Thread.sleep(2000);
		//Account code
		RunnerClass.driver.findElement(Locators.accountDropdown).click();
		List<WebElement> chargeCodes = RunnerClass.driver.findElements(Locators.chargeCodesList);
		for(int i=0;i<chargeCodes.size();i++)
		{
			String code = chargeCodes.get(i).getText();
			if(code.contains(RunnerClass.arizonaCityFromBuildingAddress))
			{
				RunnerClass.arizonaRentCode = code;
				RunnerClass.arizonaCodeAvailable = true;
				break;
				
			}
		}
	}
}
