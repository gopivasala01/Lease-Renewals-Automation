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
	public static boolean dateCheckInLedgerForMonthlyRentStartDate = false;
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
		    dateCheckInLedgerForMonthlyRentStartDate = false;
			switch(market)
			{
			case "Florida":
				String pdfFormatType_florida = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_florida);
				if(pdfFormatType_florida=="Format1")
				{
					if(PDFDataExtract.Florida_Format1.florida()==false)
						return false;
				}
				else 
					if(pdfFormatType_florida=="Format2")
				     {
					if(PDFDataExtract.Florida_Format2.florida()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Alabama":
				String pdfFormatType_alabama = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_alabama);
				if(pdfFormatType_alabama=="Format1")
				{
					if(PDFDataExtract.Alabama_Format1.alabama()==false)
						return false;
				}
				else 
					if(pdfFormatType_alabama=="Format2")
				     {
					if(PDFDataExtract.Alabama_Format2.alabama()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Dallas/Fort Worth":
				String pdfFormatType_dallasFortWorth = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_dallasFortWorth);
				if(pdfFormatType_dallasFortWorth=="Format1")
				{
					if(PDFDataExtract.DallasFortWorth_Format1.dallasFortWorth()==false)
						return false;
				}
				else 
					if(pdfFormatType_dallasFortWorth=="Format2")
				     {
					if(PDFDataExtract.DallasFortWorth_Format2.dallasFortWorth()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "North Carolina":
				String pdfFormatType = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType);
				if(pdfFormatType=="Format1")
				{
					if(PDFDataExtract.NorthCarolina_Format1.northCarolina()==false)
						return false;
				}
				else 
					if(pdfFormatType=="Format2")
				     {
					if(PDFDataExtract.NorthCarolina_Format2.northCarolina()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
                break;
			case "Arkansas":
				String pdfFormatType_arkansas = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_arkansas);
				if(pdfFormatType_arkansas=="Format1")
				{
					if(PDFDataExtract.Arkansas_Format1.arkansas()==false)
						return false;
				}
				else 
					if(pdfFormatType_arkansas=="Format2")
				     {
					if(PDFDataExtract.Arkansas_Format2.arkansas()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Georgia":
				String pdfFormatType_georgia= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_georgia);
				if(pdfFormatType_georgia=="Format1")
				{
					if(PDFDataExtract.Georgia_Format1.georgia()==false)
						return false;
				}
				else 
					if(pdfFormatType_georgia=="Format2")
				     {
					if(PDFDataExtract.Georgia_Format2.georgia()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
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
			
			case "Florida":
		        format1Text = PDFAppConfig.PDFFormatDecider.florida_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.florida_Format2;
		        break;
		        
			case "DallasFortWorth":
		        format1Text = PDFAppConfig.PDFFormatDecider.dallasFortWorth_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.dallasFortWorth_Format2;
		        break;
		        
			case "Alabama":
		        format1Text = PDFAppConfig.PDFFormatDecider.alabama_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.alabama_Format2;
		        break;
			case "Arkansas":
		        format1Text = PDFAppConfig.PDFFormatDecider.arkansas_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.arkansas_Format2;
		        break;
			case "Georgia":
		        format1Text = PDFAppConfig.PDFFormatDecider.georgia_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.georgia_Format2;
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
