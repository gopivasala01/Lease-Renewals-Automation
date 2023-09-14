package mainPackage;

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
				if(PDFReader.residentBenefitsPackageAvailabilityCheck==true)
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
					if(PDFReader.residentBenefitsPackageAvailabilityCheck==true)
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
			if((RunnerClass.company.equals("Boise")||RunnerClass.company.equals("Idaho Falls"))&&PDFReader.residentUtilityBillFlag==true&&!PDFReader.RUBS.equals("Error"))
			{
				autoCharges = autoCharges+",14";
			}
			
			
			DataBase.updateTable(query);
			
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
	
	public static void updateDates()
	{
		    PropertyWare_InsertData.proratedRent_StartDate ="";
		    if(PDFReader.startDate.split("/")[1].equals("1")||PDFReader.startDate.split("/")[1].equals("01"))
		    {
		    	//If there is already a monthly rent charge in Ledger,then auto charge start date is first full month
		    	if(PDFReader.dateCheckInLedgerForMonthlyRentStartDate == true)
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
}
