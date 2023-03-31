package mainPackage;

public class AppConfig 
{
	public static boolean saveButtonOnAndOff= false;
	
	public static String username= "mds0418@gmail.com";
	public static String password="HomeRiver1#";
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
			   return "4000 - Rent";
		   case "Alabama":
			   return "4000 - Rent";
		   case "North Carolina":
			   return "4000 - Rent";
		   case "Dallas/Fort Worth":
			   return "4000 - Rent";
		   case "Arkansas":
			   return "4000 - Rent";
		   case "Indiana":
			   return "4700 - Rent";
		   case "Little Rock":
			   return "4000 - Rent";
		   case "Georgia":
			   return "4000 - Rent";
		   case "Tennessee":
			   return "4700 - Rent";
		   }
		   return "";
	   }
	   
	   public static String getPetRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "4311 - Pet Rent";
		   case "Alabama":
			   return "4311 - Pet Rent";
		   case "North Carolina":
			   return "4311 - Pet Rent";
		   case "Dallas/Fort Worth":
			   return "4311 - Pet Rent";
		   case "Arkansas":
			   return "4311 - Pet Rent";
		   case "Georgia":
			   return "4311 - Pet Rent";
		   case "Indiana":
			   return "4311 - Pet Rent";
		   case "Little Rock":
			   return "4311 - Pet Rent";
		   case "Tennessee":
			   return "4311 - Pet Rent";
		   }
		   return "";
	   }
	   
	   public static String getProrateRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "4311 - Pet Rent";
		   case "Alabama":
			   return "4311 - Pet Rent";
		   case "North Carolina":
			   return "4311 - Pet Rent";
		   case "Dallas/Fort Worth":
			   return "4311 - Pet Rent";
		   case "Arkansas":
			   return "4311 - Pet Rent";
		   case "Georgia":
			   return "4311 - Pet Rent";
		   case "Indiana":
			   return "4311 - Pet Rent";
		   case "Little Rock":
			   return "4311 - Pet Rent";
		   case "Tennessee":
			   return "4311 - Pet Rent";
		   }
		   return "";
	   }
	   public static String getIncreasedRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "4000 - Rent";
		   case "Alabama":
			   return "4000 - Rent";
		   case "North Carolina":
			   return "4000 - Rent";
		   case "Dallas/Fort Worth":
			   return "4000 - Rent";
		   case "Arkansas":
			   return "4000 - Rent";
		   case "Georgia":
			   return "4000 - Rent";
		   case "Indiana":
			   return "4700 - Rent";
		   case "Little Rock	":
			   return "4000 - Rent";
		   case "Tennessee	":
			   return "4700 - Rent";
		   }
		   return "";
	   }
	   public static String getHVACAirFilterFeeChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "4102 - Air Filter Fee";
		   case "Alabama":
			   return "4102 - Air Filter Fee";
		   case "North Carolina":
			   return "4102 - Air Filter Fee";
		   case "Dallas/Fort Worth":
			   return "4102 - Air Filter Fee";
		   case "Arkansas":
			   return "4102 - Air Filter Fee";
		   case "Georgia":
			   return "4102 - Air Filter Fee";
		   case "Indiana":
			   return "4102 - Air FIlter Fee";
		   case "Little Rock":
			   return "4102 - Air Filter Fee";
		   case "Tennessee":
			   return "4102 - Air Filter Fee";
		   }
		   return "";
	   }
	   
	   public static String getResidentBenefitsPackageChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "4318 - Resident Benefits Package";
		   case "Alabama":
			   return "4318 - Resident Benefits Package";
		   case "North Carolina":
			   return "4318 - Resident Benefits Package";
		   case "Dallas/Fort Worth":
			   return "4318 - Resident Benefits Package";
		   case "Arkansas":
			   return "4318 - Resident Benefits Package";
		   case "Georgia":
			   return "4318 - Resident Benefits Package";
		   case "Indiana":
			   return "4318 - Resident Benefits Package";
		   case "Little Rock":
			   return "4318 - Resident Benefits Package";
		   case "Tennessee":
			   return "4318 - Resident Benefits Package";
		   }
		   return "";
	   }
	   public static String getResidentRenewalAdminFee(String company)
	   {
		   switch(company)
		   {
		   case "Florida":
			   return "4315 - Resident Renewal Admin Fee";
		   case "Alabama":
			   return "4315 - Resident Renewal Admin Fee";
		   case "North Carolina":
			   return "4315 - Resident Renewal Admin Fee";
		   case "Dallas/Fort Worth":
			   return "4315 - Resident Renewal Admin Fee"; 
		   case "Arkansas":
			   return "4315 - Resident Renewal Admin Fee"; 
		   case "Georgia":
			   return "4315 - Resident Renewal Admin Fee"; 
		   case "Indiana":
			   return "4315 - Resident Renewal Admin Fee"; 
		   case "Little Rock":
			   return "4315 - Resident Renewal Admin Fee"; 
		   case "Tennessee":
			   return "4315 - Resident Renewal Admin Fee"; 
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
		   }
		   return "";
	   }
   

}
