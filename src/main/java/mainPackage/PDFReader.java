package mainPackage;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader 
{
	public static String commencementDate ="";
    public static String expirationDate ="";
    public static String proratedRent ="";
    public static String proratedRentDate ="";
    public static String monthlyRent="";
    public static String petRentWithTax="";
    public static String petRent="";
    public static String petFee;
    public static String pdfText="";
    public static String securityDeposit="";
    public static String leaseStartDate_PW="";
    public static String leaseEndDate_PW="";
    public static String prepaymentCharge;
    public static ArrayList<String> petType;
    public static ArrayList<String> petBreed;
    public static ArrayList<String> petWeight;
    public static Robot robot;
    public static boolean concessionAddendumFlag = false;
    public static boolean petSecurityDepositFlag = false;
    public static boolean petFlag = false;
    public static String portfolioType="";
    public static boolean incrementRentFlag = false;
    public static boolean proratedRentDateIsInMoveInMonthFlag=false;
    public static String increasedRent_previousRentStartDate ="";
    public static String increasedRent_previousRentEndDate ="";
    public static String increasedRent_amount ="";
    public static String increasedRent_newStartDate ="";
    public static String increasedRent_newEndDate ="";
    public static boolean serviceAnimalFlag = false;
    public static ArrayList<String> serviceAnimalPetType;
    public static ArrayList<String> serviceAnimalPetBreed;
    public static ArrayList<String> serviceAnimalPetWeight;
    public static String lateFeeType ="";
    public static String flatFeeAmount ="";
    public static String lateFeePercentage="";
    public static boolean HVACFilterFlag = false;
    public static boolean residentBenefitsPackageAvailabilityCheck = false;
    public static String residentBenefitsPackage = "";
    public static String leaseRenewalFee = "";
    public static String startDate = "";
    public static String endDate = "";
    public static String lastDayOfTheStartDate = "";
    public static String firstFullMonth = "";
    public static String secondFullMonth = "";
    public static String HVACAirFilterFee ="";
    public static String previousMonthlyRent = "";
	 
		public static boolean readPDFPerMarket(String market) throws Exception  
		{
			//Initialize all PDF data variables
			commencementDate ="";
			expirationDate="";
			monthlyRent="";
			HVACFilterFlag = false;
			residentBenefitsPackageAvailabilityCheck = false;
			residentBenefitsPackage = "";
			proratedRent ="";
		    proratedRentDate ="";
		    petFlag = false;
		    leaseRenewalFee = "";
		    startDate = "";
		    lastDayOfTheStartDate = "";
		    firstFullMonth = "";
		    HVACAirFilterFee = "";
		    secondFullMonth = "";
		    petRent ="";
		    incrementRentFlag = false;
		    increasedRent_previousRentEndDate ="";
		    increasedRent_amount ="";
		    increasedRent_newStartDate ="";
		    increasedRent_newEndDate ="";
		    previousMonthlyRent = "";
		    
			switch(market)
			{
			case "Alabama":
				PDFDataExtract.Alabama.alabama();
				if(RunnerClass.monthlyRent=="Error"||RunnerClass.startDate=="Error")
				{
					System.out.println("Unable to fetch Monthly Rent and Start Date from Lease Agreement");
					RunnerClass.failedReaonsList.put(RunnerClass.buildingAbbreviation, "Unable to fetch Monthly Rent and Start Date from Lease Agreement");
				    RunnerClass.failedReason = "Unable to fetch Monthly Rent and Start Date from Lease Agreement";
					RunnerClass.updateStatus=1;
					return false;
				}
				else return true;
			case "Florida":
				PDFDataExtract.Florida.florida();
				if(RunnerClass.monthlyRent=="Error"||RunnerClass.startDate=="Error")
				{
					System.out.println("Unable to fetch Monthly Rent and Start Date from Lease Agreement");
					RunnerClass.failedReaonsList.put(RunnerClass.buildingAbbreviation, "Unable to fetch Monthly Rent and Start Date from Lease Agreement");
				    RunnerClass.failedReason = "Unable to fetch Monthly Rent and Start Date from Lease Agreement";
					RunnerClass.updateStatus=1;
					return false;
				}
				else return true;
			case "North Carolina":
				String pdfFormatType = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType);
				if(pdfFormatType=="Format1")
					PDFDataExtract.NorthCarolina_Format1.northCarolina();
				else if(pdfFormatType=="Format2")
				PDFDataExtract.NorthCarolina_Format2.northCarolina();
				else 
				{
					RunnerClass.failedReason = "Wrong PDF Format";
					return false;
				}

			}
			return true;
			
		}
		
		public static String decidePDFFormat(String company) throws Exception
		{
			try
			{
			String format1Text = "";
			String format2Text = "";
			switch(company)
			{
			case "North Carolina":
			format1Text  = PDFAppConfig.PDFFormatDecider.northCarolina_Format1;
			format2Text  = PDFAppConfig.PDFFormatDecider.northCarolina_Format2;
			break;
			}
			
			File file = RunnerClass.getLastModified();
			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Full_Lease_-_[6128_Creekview_Court]_-_[Wallace_-_Crawford]_-_[02.01.2023]_-_[04.30.2024].PDF_(1).pdf");
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			String text = new PDFTextStripper().getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
		    text = text.replaceAll(" +", " ");
		    if(text.contains(format1Text))
		    	return "Format1";
		    else if(text.contains(format2Text))
		    	return "Format2";
		    else return "Error";
			}
			catch(Exception e)
			{
				return "Error";
			}
		}
	  

}
