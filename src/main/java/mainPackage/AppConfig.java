package mainPackage;

public class AppConfig 
{
	public static boolean saveButtonOnAndOff= false ;
	
	public static String username= "mds0418@gmail.com";
	public static String password="KRm#V39fecMDGg#";
	public static String URL="https://app.propertyware.com/pw/login.jsp";
	public static String[] Buildings= {"SABA2399"};
	public static String[] Names= {"Baxter - Hernandez"};
	
   public static String pendingRenewalLeases = "Select  Company,buildingName,OwnerName from Automation.LeaseRenewalAutomation where Status = 'In Progress'";
		   //"Select  Company,buildingabbreviation,LeaseName from LeaseFact_dashboard where DATEDIFF(month, StartDate, GETDATE()) = 1 and Company ='Florida'  order by id asc";
   //public static String lastMonthLeases1 = "Select  Company,buildingabbreviation,LeaseName from LeaseFact_dashboard where DATEDIFF(month, StartDate, GETDATE()) = 1 and Company ='Alabama'  order by id asc";
	//public static String lastMonthLeases = "Select  Company,buildingabbreviation,LeaseName from [Automation].[leaseAuditAutomation] where notes = 'Values did not match'";
	public static String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
    public static String downloadFilePath = "C:\\SantoshMurthyP\\Lease Audit Automation";
    
    public static  String PDFFormatConfirmationText = "The parties to this lease are:";
	public static  String PDFFormat2ConfirmationText = "THIS RESIDENTIAL LEASE AGREEMENT";
	
	//Mail credentials
	   public static String fromEmail = "bireports@beetlerim.com";
	   public static String fromEmailPassword = "Welcome@123";
	   
	   public static String toEmail ="gopi.v@beetlerim.com,Santosh.p@beetlerim.com";
	   public static String CCEmail = "santosh.t@beetlerim.com";
	   
	   public static String mailSubject = "Lease Audit for the Month of   ";
	   
	   public static String excelFileLocation = "E:\\Automation\\Gopi\\Lease Audit Automation";
	   
	   public static String getAutoCharges = "Select ChargeCode, Amount, StartDate,EndDate,Description from automation.LeaseReneWalsAutoChargesConfiguration Where Flag =1";
	   
	   public static String getMoveInCharges = "Select ChargeCode, Amount, StartDate,EndDate,Description from automation.LeaseReneWalsMoveInChargesConfiguration Where Flag =1";
	   
	   public static String[] IAGClientList = {"510","AVE","BTH","CAP","FOR","HRG","HS","MAN","MCH","OFF","PIN","RF","SFR3","TH","HH","Lofty.Ai"};
	
	   public static String getMonthlyRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "40010 - Rent Income";
		   case "Alabama":
			  return "40010 - Rent Income";
		   case "North Carolina":
			   return "40010 - Rent Income";
		   case "Dallas/Fort Worth":
			   return "40010 - Rent Income";
		   case "Arkansas":
			   return "40010 - Rent Income";
		   case "Indiana":
			   return "40010 - Rent Income";
		   case "Little Rock":
			   return "40010 - Rent Income";
		   case "Georgia":
			   return "40010 - Rent Income";
		   case "Tennessee":
			   return "40010 - Rent Income";
		   case "California":
			   return "40010 - Rent Income";
		   case "California PFW":
			   return "40010 - Rent Income";
		   case "Houston":
			   return "40010 - Rent Income";
		   case "Austin":
			   return "40010 - Rent Income";
		   case "Chattanooga":
			   return "40010 - Rent Income";
		   case "Chicago":
			   return "40010 - Rent Income";
		   case "Savannah":
			   return "40010 - Rent Income";
		   case "South Carolina":
			   return "40010 - Rent Income";
		   case "Tulsa":
			   return "40010 - Rent Income";
		   case "Ohio":
			   return "40010 - Rent Income";
		   case "Maine":
				  return "40010 - Rent Income";
		   case "OKC":
				  return "40010 - Rent Income";
		   case "San Antonio":
				  return "40010 - Rent Income";
		   case "Pennsylvania":
				  return "40010 - Rent Income";
		   case "Colorado Springs":
				  return "40010 - Rent Income";
		   case "Kansas City":
				  return "40010 - Rent Income";
		   case "Lake Havasu":
				  return "40010 - Rent Income";
		   case "New Mexico":
				  return "40010 - Rent Income";
		   case "Chicago PFW":
				  return "40010 - Rent Income";
		   case "Boise":
				  return "40010 - Rent Income";
		   case "Spokane":
				  return "40010 - Rent Income";
		   case "Utah":
				  return "40010 - Rent Income";
		   case "Hawaii":
				  return "40010 - Rent Income";
		   case "Saint Louis":
				  return "40010 - Rent Income";
		   case "Idaho Falls":
				  return "40010 - Rent Income";
		   case "Arizona":
				  return "40010 - Rent Income";
		   case "Maryland":
				  return "40010 - Rent Income";
		   case "Virginia":
				  return "40010 - Rent Income";
		   }
		   return "";
	   }
	   
	   public static String getPetRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "40230 - Pet Rent";
		   case "Alabama":
			   return "40230 - Pet Rent";
		   case "North Carolina":
			   return "40230 - Pet Rent";
		   case "Dallas/Fort Worth":
			   return "40230 - Pet Rent";
		   case "Arkansas":
			   return "40230 - Pet Rent";
		   case "Georgia":
			   return "40230 - Pet Rent";
		   case "Indiana":
			   return "40230 - Pet Rent";
		   case "Little Rock":
			   return "40230 - Pet Rent";
		   case "Tennessee":
			   return "40230 - Pet Rent";
		   case "California":
			   return "40230 - Pet Rent";
		   case "California PFW":
			   return "40230 - Pet Rent";
		   case "Houston":
			   return "40230 - Pet Rent";
		   case "Austin":
			   return "40230 - Pet Rent";
		   case "Chattanooga":
			   return "40230 - Pet Rent";
		   case "Chicago":
			   return "40230 - Pet Rent";
		   case "Savannah":
			   return "40230 - Pet Rent";
		   case "South Carolina":
			   return "40230 - Pet Rent";
		   case "Tulsa":
			   return "40230 - Pet Rent";
		   case "Ohio":
			   return "40230 - Pet Rent";
		   case "Maine":
			   return "40230 - Pet Rent";
		   case "OKC":
			   return "40230 - Pet Rent";
		   case "San Antonio":
			   return "40230 - Pet Rent";
		   case "Pennsylvania":
			   return "40230 - Pet Rent";
		   case "Colorado Springs":
			   return "40230 - Pet Rent";
		   case "Kansas City":
			   return "40230 - Pet Rent";
		   case "Lake Havasu":
			   return "40230 - Pet Rent";
		   case "New Mexico":
			   return "40230 - Pet Rent";
		   case "Chicago PFW":
			   return "40230 - Pet Rent";
		   case "Boise":
			   return "40230 - Pet Rent";
		   case "Spokane":
			   return "40230 - Pet Rent";
		   case "Utah":
			   return "40230 - Pet Rent";
		   case "Hawaii":
			   return "40230 - Pet Rent";
		   case "Saint Louis":
			   return "40230 - Pet Rent";
		   case "Idaho Falls":
			   return "40230 - Pet Rent";
		   case "Arizona":
			   return "40230 - Pet Rent";
		   case "Maryland":
			   return "40230 - Pet Rent";
		   case "Virginia":
			   return "40230 - Pet Rent";
		   }
		   return "";
	   }
	   
	   public static String getProrateRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "40230 - Pet Rent";
		   case "Alabama":
			   return "40230 - Pet Rent";
		   case "North Carolina":
			   return "40230 - Pet Rent";
		   case "Dallas/Fort Worth":
			   return "40230 - Pet Rent";
		   case "Arkansas":
			   return "40230 - Pet Rent";
		   case "Georgia":
			   return "40230 - Pet Rent";
		   case "Indiana":
			   return "40230 - Pet Rent";
		   case "Little Rock":
			   return "40230 - Pet Rent";
		   case "Tennessee":
			   return "40230 - Pet Rent";
		   case "California":
			   return "40230 - Pet Rent";
		   case "California PFW":
			   return "40230 - Pet Rent";
		   case "Houston":
			   return "40230 - Pet Rent";
		   case "Austin":
			   return "40230 - Pet Rent";
		   case "Chattanooga":
			   return "40230 - Pet Rent";
		   case "Chicago":
			   return "40230 - Pet Rent";
		   case "Savannah":
			   return "40230 - Pet Rent";
		   case "South Carolina":
			   return "40230 - Pet Rent";
		   case "Tulsa":
			   return "40230 - Pet Rent";
		   case "Ohio":
			   return "40230 - Pet Rent";
		   case "Maine":
			   return "40230 - Pet Rent";
		   case "OKC":
			   return "40230 - Pet Rent";
		   case "San Antonio":
			   return "40230 - Pet Rent";
		   case "Pennsylvania":
			   return "40230 - Pet Rent";
		   case "Colorado Springs":
			   return "40230 - Pet Rent";
		   case "Kansas City":
			   return "40230 - Pet Rent";
		   case "Lake Havasu":
			   return "40230 - Pet Rent";
		   case "New Mexico":
			   return "40230 - Pet Rent";
		   case "Chicago PFW":
			   return "40230 - Pet Rent";
		   case "Boise":
			   return "40230 - Pet Rent";
		   case "Spokane":
			   return "40230 - Pet Rent";
		   case "Utah":
			   return "40230 - Pet Rent";
		   case "Hawaii":
			   return "40230 - Pet Rent";
		   case "Saint Louis":
			   return "40230 - Pet Rent";
		   case "Idaho Falls":
			   return "40230 - Pet Rent";
		   case "Arizona":
			   return "40230 - Pet Rent";
		   case "Maryland":
			   return "40230 - Pet Rent";
		   case "Virginia":
			   return "40230 - Pet Rent";
		   }
		   return "";
	   }
	   public static String getIncreasedRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "40010 - Rent Income";
		   case "Alabama":
			   return "40010 - Rent Income";
		   case "North Carolina":
			   return "40010 - Rent Income";
		   case "Dallas/Fort Worth":
			   return "40010 - Rent Income";
		   case "Arkansas":
			   return "40010 - Rent Income";
		   case "Georgia":
			   return "40010 - Rent Income";
		   case "Indiana":
			   return "40010 - Rent Income";
		   case "Little Rock	":
			   return "40010 - Rent Income";
		   case "Tennessee	":
			   return "40010 - Rent Income";
		   case "California":
			   return "40010 - Rent Income";
		   case "California PFW":
			   return "40010 - Rent Income";
		   case "Houston":
			   return "40010 - Rent Income";
		   case "Austin":
			   return "40010 - Rent Income";
		   case "Chattanooga":
			   return "40010 - Rent Income";
		   case "Chicago":
			   return "40010 - Rent Income";
		   case "Savannah":
			   return "40010 - Rent Income";
		   case "South Carolina":
			   return "40010 - Rent Income";
		   case "Tulsa":
			   return "40010 - Rent Income";
		   case "Ohio":
			   return "40010 - Rent Income";
		   case "Maine":
			   return "40010 - Rent Income";
		   case "OKC":
			   return "40010 - Rent Income";
		   case "San Antonio":
			   return "40010 - Rent Income";
		   case "Pennsylvania":
			   return "40010 - Rent Income";
		   case "Colorado Springs":
			   return "40010 - Rent Income";
		   case "Kansas City":
			   return "40010 - Rent Income";
		   case "Lake Havasu":
			   return "40010 - Rent Income";
		   case "New Mexico":
			   return "40010 - Rent Income";
		   case "Chicago PFW":
			   return "40010 - Rent Income";
		   case "Boise":
			   return "40010 - Rent Income";
		   case "Spokane":
			   return "40010 - Rent Income";
		   case "Utah":
			   return "40010 - Rent Income";
		   case "Hawaii":
			   return "40010 - Rent Income";
		   case "Saint Louis":
			   return "40010 - Rent Income";
		   case "Idaho Falls":
			   return "40010 - Rent Income";
		   case "Arizona":
			   return "40010 - Rent Income";
		   case "Maryland":
			   return "40010 - Rent Income";
		   case "Virginia":
			   return "40010 - Rent Income";
		   }
		   return "";
	   }
	   public static String getHVACAirFilterFeeChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "43060 - Filter Fee";
		   case "Alabama":
			   return "43060 - Filter Fee";
		   case "North Carolina":
			   return "43060 - Filter Fee";
		   case "Dallas/Fort Worth":
			   return "43060 - Filter Fee";
		   case "Arkansas":
			   return "43060 - Filter Fee";
		   case "Georgia":
			   return "43060 - Filter Fee";
		   case "Indiana":
			   return "43060 - Filter Fee";
		   case "Little Rock":
			   return "43060 - Filter Fee";
		   case "Tennessee":
			   return "43060 - Filter Fee";
		   case "California":
			   return "43060 - Filter Fee";
		   case "California PFW":
			   return "43060 - Filter Fee";
		   case "Houston":
			   return "43060 - Filter Fee";
		   case "Austin":
			   return "43060 - Filter Fee";
		   case "Chattanooga":
			   return "43060 - Filter Fee";
		   case "Chicago":
			   return "43060 - Filter Fee";
		   case "Savannah":
			   return "43060 - Filter Fee";
		   case "South Carolina":
			   return "43060 - Filter Fee";
		   case "Tulsa":
			   return "43060 - Filter Fee";
		   case "Ohio":
			   return "43060 - Filter Fee";
		   case "Maine":
			   return "43060 - Filter Fee";
		   case "OKC":
			   return "43060 - Filter Fee";
		   case "San Antonio":
			   return "43060 - Filter Fee";
		   case "Pennsylvania":
			   return "43060 - Filter Fee";
		   case "Colorado Springs":
			   return "43060 - Filter Fee";
		   case "Kansas City":
			   return "43060 - Filter Fee";
		   case "Lake Havasu":
			   return "43060 - Filter Fee";
		   case "New Mexico":
			   return "43060 - Filter Fee";
		   case "Chicago PFW":
				   return "43060 - Filter Fee";
		   case "Boise":
			   return "43060 - Filter Fee";
		   case "Spokane":
			   return "43060 - Filter Fee";
		   case "Utah":
			   return "43060 - Filter Fee";
		   case "Hawaii":
			   return "43060 - Filter Fee";
		   case "Saint Louis":
			   return "43060 - Filter Fee";
		   case "Idaho Falls":
			   return "43060 - Filter Fee";
		   case "Arizona":
			   return "43060 - Filter Fee";
		   case "Maryland":
			   return "43060 - Filter Fee";
		   case "Virginia":
			   return "43060 - Filter Fee";
			   
		   }
		   return "";
	   }
	   
	   public static String getResidentBenefitsPackageChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "43070 - Resident Benefit Package Fee";
		   case "Alabama":
			   return "43070 - Resident Benefit Package Fee";
		   case "North Carolina":
			   return "43070 - Resident Benefit Package Fee";
		   case "Dallas/Fort Worth":
			   return "43070 - Resident Benefit Package Fee";
		   case "Arkansas":
			   return "43070 - Resident Benefits Package";
		   case "Georgia":
			   return "43070 - Resident Benefit Package Fee";
		   case "Indiana":
			   return "43070 - Resident Benefit Package Fee";
		   case "Little Rock":
			   return "43070 - Resident Benefit Package Fee";
		   case "Tennessee":
			   return "43070 - Resident Benefit Package Fee";
		   case "California":
			   return "43070 - Resident Benefit Package Fee";
		   case "California PFW":
			   return "43070 - Resident Benefit Package Fee";
		   case "Houston":
			   return "43070 - Resident Benefit Package Fee";
		   case "Austin":
			   return "43070 - Resident Benefit Package Fee";
		   case "Chattanooga":
			   return "43070 - Resident Benefit Package Fee";
		   case "Chicago":
			   return "43070 - Resident Benefit Package Fee";
		   case "Savannah":
			   return "43070 - Resident Benefit Package Fee";
		   case "South Carolina":
			   return "43070 - Resident Benefit Package Fee";
		   case "Tulsa":
			   return "43070 - Resident Benefit Package Fee";
		   case "Ohio":
			   return "43070 - Resident Benefit Package Fee";
		   case "Maine":
			   return "43070 - Resident Benefit Package Fee";
		   case "OKC":
			   return "43070 - Resident Benefit Package Fee";
		   case "San Antonio":
			   return "43070 - Resident Benefit Package Fee";
		   case "Pennsylvania":
			   return "43070 - Resident Benefit Package Fee";
		   case "Colorado Springs":
			   return "43070 - Resident Benefit Package Fee";
		   case "Kansas City":
			   return "43070 - Resident Benefit Package Fee";
		   case "Lake Havasu":
			   return "43070 - Resident Benefit Package Fee";
		   case "New Mexico":
			   return "43070 - Resident Benefit Package Fee";
		   case "Chicago PFW":
			   return "43070 - Resident Benefit Package Fee";
		   case "Boise":
			   return "43070 - Resident Benefit Package Fee";
		   case "Spokane":
			   return "43070 - Resident Benefit Package Fee";
		   case "Utah":
			   return "43070 - Resident Benefit Package Fee";
		   case "Hawaii":
			   return "43070 - Resident Benefit Package Fee";
		   case "Saint Louis":
			   return "43070 - Resident Benefit Package Fee";
		   case "Idaho Falls":
			   return "43070 - Resident Benefit Package Fee";
		   case "Arizona":
			   return "43070 - Resident Benefit Package Fee";
		   case "Maryland":
			   return "43070 - Resident Benefit Package Fee";
		   case "Virginia":
			   return "43070 - Resident Benefit Package Fee";
		   }
		   return "";
	   }
	   public static String getResidentRenewalAdminFee(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Alabama":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "North Carolina":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Dallas/Fort Worth":
			   return "43040 - Resident Renewal Admin Fee";  
		   case "Arkansas":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Georgia":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Indiana":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Little Rock":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Tennessee":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "California":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "California PFW":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Houston":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Austin":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Chattanooga":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Chicago":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Savannah":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "South Carolina":
			   return "43040 - Resident Renewal Admin Fee"; 
		   case "Tulsa":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Ohio":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Maine":
			   return "43040 - Resident Renewal Admin Fee";
		   case "OKC":
			   return "43040 - Resident Renewal Admin Fee";
		   case "San Antonio":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Pennsylvania":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Colorado Springs":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Kansas City":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Lake Havasu":
			   return "43040 - Resident Renewal Admin Fee";
		   case "New Mexico":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Chicago PFW":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Boise":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Spokane":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Utah":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Hawaii":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Saint Louis":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Idaho Falls":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Arizona":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Maryland":
			   return "43040 - Resident Renewal Admin Fee";
		   case "Virginia":
			   return "43040 - Resident Renewal Admin Fee";
		    
		   }
		   return "";
	   }
	   public static String getEnrolledINRBPForPMUse(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "YES";
		   case "Alabama":
			   return "YES";
		   case "North Carolina":
			   return "YES";
		   case "Dallas/Fort Worth":
			   return "YES"; 
		   case "Arkansas":
			   return "YES"; 
		   case "Georgia":
			   return "YES"; 
		   case "Indiana":
			   return "YES"; 
		   case "Little Rock":
			   return "YES"; 
		   case "Tennessee":
			   return "YES"; 
		   case "California":
			   return "YES";
		   case "California PFW":
			   return "YES";
		   case "Houston":
			   return "YES";
		   case "Austin":
			   return "YES";
		   case "Chattanooga":
			   return "YES";
		   case "Chicago":
			   return "YES";
		   case "Savannah":
			   return "YES";
		   case "South Carolina":
			   return "YES";
		   case "Tulsa":
			   return "YES";
		   case "Ohio":
			   return "YES";
		   case "Maine":
			   return "YES";
		   case "OKC":
			   return "YES";
		   case "San Antonio":
			   return "YES";
		   case "Pennsylvania":
			   return "YES";
		   case "Colorado Springs":
			   return "YES";
		   case "Kansas City":
			   return "YES";
		   case "Lake Havasu":
			   return "YES";
		   case "New Mexico":
			   return "YES";
		   case "Chicago PFW":
			   return "YES";
		   case "Boise":
			   return "YES";
		   case "Spokane":
			   return "YES";
		   case "Utah":
			   return "YES";
		   case "Hawaii":
			   return "YES";
		   case "Saint Louis":
			   return "YES";
		   case "Idaho Falls":
			   return "YES";
		   case "Arizona":
			   return "YES";
		   case "Maryland":
			   return "YES";
		   case "Virginia":
			   return "YES";
		   }
		   return "";
	   }
	   public static String getRBPenrollmentCompleteForSNUseOnly(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "Yes";
		   case "Alabama":
			   return "Yes";
		   case "North Carolina":
			   return "Yes";
		   case "Dallas/Fort Worth":
			   return "Yes"; 
		   case "Arkansas":
			   return "Yes";
		   case "Georgia":
			   return "Yes";
		   case "Indiana":
			   return "Yes";
		   case "Little Rock":
			   return "Yes";
		   case "Tennessee":
			   return "Yes";
		   case "California":
			   return "Yes";
		   case "California PFW":
			   return "Yes";
		   case "Houston":
			   return "Yes";
		   case "Austin":
			   return "Yes";
		   case "Chattanooga":
			   return "Yes";
		   case "Chicago":
			   return "Yes";
		   case "Savannah":
			   return "Yes";
		   case "South Carolina":
			   return "Yes";
		   case "Tulsa":
			   return "Yes";
		   case "Ohio":
			   return "Yes";
		   case "Maine":
			   return "Yes";
		   case "OKC":
			   return "Yes";
		   case "San Antonio":
			   return "Yes";
		   case "Pennsylvania":
			   return "Yes";
		   case "Colorado Springs":
			   return "Yes";
		   case "Kansas City":
			   return "Yes";
		   case "Lake Havasu":
			   return "Yes";
		   case "New Mexico":
			   return "Yes";
		   case "Chicago PFW":
			   return "Yes";
		   case "Boise":
			   return "Yes";
		   case "Spokane":
			   return "Yes";
		   case "Utah":
			   return "Yes";
		   case "Hawaii":
			   return "Yes";
		   case "Saint Louis":
			   return "Yes";
		   case "Idaho Falls":
			   return "Yes";
		   case "Arizona":
			   return "Yes";
		   case "Maryland":
			   return "Yes";
		   case "Virginia":
			   return "Yes";
		   }
		   return "";    
	   }
	   
	   public static String getEnrolledINRBPForPMUseNo(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "NO";
		   case "Alabama":
			   return "NO";
		   case "North Carolina":
			   return "NO";
		   case "Dallas/Fort Worth":
			   return "NO"; 
		   case "Arkansas":
			   return "NO"; 
		   case "Georgia":
			   return "NO"; 
		   case "Indiana":
			   return "NO"; 
		   case "Little Rock":
			   return "NO"; 
		   case "Tennessee":
			   return "NO"; 
		   case "California":
			   return "NO";
		   case "California PFW":
			   return "NO";
		   case "Houston":
			   return "NO";
		   case "Austin":
			   return "NO";
		   case "Chattanooga":
			   return "NO";
		   case "Chicago":
			   return "NO";
		   case "Savannah":
			   return "NO";
		   case "South Carolina":
			   return "NO";
		   case "Tulsa":
			   return "NO";
		   case "Ohio":
			   return "NO";
		   case "Maine":
			   return "NO";
		   case "OKC":
			   return "NO";
		   case "San Antonio":
			   return "NO";
		   case "Pennsylvania":
			   return "NO";
		   case "Colorado Springs":
			   return "NO";
		   case "Kansas City":
			   return "NO";
		   case "Lake Havasu":
			   return "NO";
		   case "New Mexico":
			   return "NO";
		   case "Chicago PFW":
			   return "NO";
		   case "Boise":
			   return "NO";
		   case "Spokane":
			   return "NO";
		   case "Utah":
			   return "NO";
		   case "Hawaii":
			   return "NO";
		   case "Saint Louis":
			   return "NO";
		   case "Idaho Falls":
			   return "NO";
		   case "Arizona":
			   return "NO";
		   case "Maryland":
			   return "NO";
		   case "Virginia":
			   return "NO";
		   }
		   return "";
	   }
		   public static String getRBPenrollmentCompleteForSNUseOnlyNo(String company)
		   {
			   switch(company)
			   {
			   case "Florida":
				   return "No";
			   case "Alabama":
				   return "No";
			   case "North Carolina":
				   return "No";
			   case "Dallas/Fort Worth":
				   return "No"; 
			   case "Arkansas":
				   return "No";
			   case "Georgia":
				   return "No";
			   case "Indiana":
				   return "No";
			   case "Little Rock":
				   return "No";
			   case "Tennessee":
				   return "No";
			   case "California":
				   return "No";
			   case "California PFW":
				   return "No";
			   case "Houston":
				   return "No";
			   case "Austin":
				   return "No";
			   case "Chattanooga":
				   return "No";
			   case "Chicago":
				   return "No";
			   case "Savannah":
				   return "No";
			   case "South Carolina":
				   return "No";
			   case "Tulsa":
				   return "No";
			   case "Ohio":
				   return "No";
			   case "Maine":
				   return "No";
			   case "OKC":
				   return "No";
			   case "San Antonio":
				   return "No";
			   case "Pennsylvania":
				   return "No";
			   case "Colorado Springs":
				   return "No";
			   case "Kansas City":
				   return "No";
			   case "Lake Havasu":
				   return "No";
			   case "New Mexico":
				   return "No";
			   case "Chicago PFW":
				   return "No";
			   case "Boise":
				   return "No";
			   case "Spokane":
				   return "No";
			   case "Utah":
				   return "No";
			   case "Hawaii":
				   return "No";
			   case "Saint Louis":
				   return "No";
			   case "Idaho Falls":
				   return "No";
			   case "Arizona":
				   return "No";
			   case "Maryland":
				   return "No";
			   case "Virginia":
				   return "No";
			   }
			   return "";    
		   }
	   
      /*public static String getRenewalStatus (String company)
      {
    	  switch(company)
    	  {
    	  case "California":
    	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Georgia":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Alabama":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Tennessee":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "North Carolina":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Dallas/Fort Worth":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Little Rock":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Indiana":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Florida":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	  case "Arkansas":
   	       return "RW-4a: CHARGE RENEWAL FEE - ANNUAL";
    	       
    	  }
    	  return"";*/
    	  		
      }