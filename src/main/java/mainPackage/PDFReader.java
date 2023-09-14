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
    public static String RUBS= "";
    public static String proratedRentDate ="";
    public static String monthlyRent="";
    public static String petRentWithTax="";
    public static String petRent="";
    public static String petFee;
    public static String pdfText="";
    public static String renewalExecutionDate = "";
    public static String securityDeposit="";
    public static String leaseStartDate_PW="";
    public static String leaseEndDate_PW="";
    public static String prepaymentCharge;
    public static ArrayList<String> petType;
    public static ArrayList<String> petBreed;
    public static ArrayList<String> petWeight;
    public static Robot robot;
    public static boolean residentUtilityBillFlag = false;
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
		    renewalExecutionDate = "";
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
			case "Indiana":
				String pdfFormatType_indiana= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_indiana);
				if(pdfFormatType_indiana=="Format1")
				{
					if(PDFDataExtract.Indiana_Format1.indiana()==false)
						return false;
				}
				else 
					if(pdfFormatType_indiana=="Format2")
				     {
					if(PDFDataExtract.Indiana_Format2.indiana()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Little Rock":
				String pdfFormatType_littleRock= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_littleRock);
				if(pdfFormatType_littleRock=="Format1")
				{
					if(PDFDataExtract.LittleRock_Format1.littleRock()==false)
						return false;
				}
				else 
					if(pdfFormatType_littleRock=="Format2")
				     {
					if(PDFDataExtract.LittleRock_Format2.littleRock()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Tennessee":
				String pdfFormatType_tennessee= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_tennessee);
				if(pdfFormatType_tennessee=="Format1")
				{
					if(PDFDataExtract.Tennessee_Format1.tennessee()==false)
						return false;
				}
				else 
					if(pdfFormatType_tennessee=="Format2")
				     {
					if(PDFDataExtract.Tennessee_Format2.tennessee()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "California":
				String pdfFormatType_california= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_california);
				if(pdfFormatType_california=="Format1")
				{
					if(PDFDataExtract.California_Format1.california()==false)
						return false;
				}
				else 
					if(pdfFormatType_california=="Format2")
				     {
					if(PDFDataExtract.California_Format2.california()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "California PFW":
				String pdfFormatType_californiapfw = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_californiapfw);
				if(pdfFormatType_californiapfw=="Format1")
				{
					if(PDFDataExtract.California_PFW_Format1.californiapfw()==false)
						return false;
				}
				else 
					if(pdfFormatType_californiapfw=="Format2")
				     {
					if(PDFDataExtract.California_PFW_Format2.californiapfw()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Houston":
				String pdfFormatType_houston= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_houston);
				if(pdfFormatType_houston=="Format1")
				{
					if(PDFDataExtract.Houston_Format1.houston()==false)
						return false;
				}
				else 
					if(pdfFormatType_houston=="Format2")
				     {
					if(PDFDataExtract.Houston_Format2.houston()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Austin":
				String pdfFormatType_austin= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_austin);
				if(pdfFormatType_austin=="Format1")
				{
					if(PDFDataExtract.Austin_Format1.austin()==false)
						return false;
				}
				else 
					if(pdfFormatType_austin=="Format2")
				     {
					if(PDFDataExtract.Austin_Format2.austin()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;

			case "Chattanooga":
				String pdfFormatType_chattanooga= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_chattanooga);
				if(pdfFormatType_chattanooga=="Format1")
				{
					if(PDFDataExtract.Chattanooga_Format1.chattanooga()==false)
						return false;
				}
				else 
					if(pdfFormatType_chattanooga=="Format2")
				     {
					if(PDFDataExtract.Chattanooga_Format2.chattanooga()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Chicago":
				String pdfFormatType_chicago= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_chicago);
				if(pdfFormatType_chicago=="Format1")
				{
					if(PDFDataExtract.Chicago_Format1.chicago()==false)
						return false;
				}
				else 
					if(pdfFormatType_chicago=="Format2")
				     {
					if(PDFDataExtract.Chicago_Format2.chicago()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Savannah":
				String pdfFormatType_savannah= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_savannah);
				if(pdfFormatType_savannah=="Format1")
				{
					if(PDFDataExtract.Savannah_Format1.savannah()==false)
						return false;
				}
				else 
					if(pdfFormatType_savannah=="Format2")
				     {
					if(PDFDataExtract.Savannah_Format2.savannah()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "South Carolina":
				String pdfFormatType_southCarolina= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_southCarolina);
				if(pdfFormatType_southCarolina=="Format1")
				{
					if(PDFDataExtract.SouthCarolina_Format1.southCarolina()==false)
						return false;
				}
				else 
					if(pdfFormatType_southCarolina=="Format2")
				     {
					if(PDFDataExtract.SouthCarolina_Format2.southCarolina()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Tulsa":
				String pdfFormatType_tulsa= PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_tulsa);
				if(pdfFormatType_tulsa=="Format1")
				{
					if(PDFDataExtract.Tulsa_Format1.tulsa()==false)
						return false;
				}
				else 
					if(pdfFormatType_tulsa=="Format2")
				     {
					if(PDFDataExtract.Tulsa_Format2.tulsa()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Ohio":
				String pdfFormatType_ohio = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_ohio);
				if(pdfFormatType_ohio=="Format1")
				{
					if(PDFDataExtract.Ohio_Format1.ohio()==false)
						return false;
				}
				else 
					if(pdfFormatType_ohio=="Format2")
				     {
					if(PDFDataExtract.Ohio_Format2.ohio()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Maine":
				String pdfFormatType_maine = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_maine);
				if(pdfFormatType_maine=="Format1")
				{
					if(PDFDataExtract.Maine_Format1.maine()==false)
						return false;
				}
				else 
					if(pdfFormatType_maine=="Format2")
				     {
					if(PDFDataExtract.Maine_Format2.maine()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "OKC":
				String pdfFormatType_okc = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_okc);
				if(pdfFormatType_okc=="Format1")
				{
					if(PDFDataExtract.OKC_Format1.okc()==false)
						return false;
				}
				else 
					if(pdfFormatType_okc=="Format2")
				     {
					if(PDFDataExtract.OKC_Format2.okc()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "San Antonio":
				String pdfFormatType_sanAntonio = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_sanAntonio);
				if(pdfFormatType_sanAntonio=="Format1")
				{
					if(PDFDataExtract.SanAntonio_Format1.sanAntonio()==false)
						return false;
				}
				else 
					if(pdfFormatType_sanAntonio=="Format2")
				     {
					if(PDFDataExtract.SanAntonio_Format2.sanAntonio()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Pennsylvania":
				String pdfFormatType_pennsylvania = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_pennsylvania);
				if(pdfFormatType_pennsylvania=="Format1")
				{
					if(PDFDataExtract.Pennsylvania_Format1.pennsylvania()==false)
						return false;
				}
				else 
					if(pdfFormatType_pennsylvania=="Format2")
				     {
					if(PDFDataExtract.Pennsylvania_Format2.pennsylvania()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Colorado Springs":
				String pdfFormatType_coloradoSprings = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_coloradoSprings);
				if(pdfFormatType_coloradoSprings=="Format1")
				{
					if(PDFDataExtract.ColoradoSprings_Format1.coloradoSprings()==false)
						return false;
				}
				else 
					if(pdfFormatType_coloradoSprings=="Format2")
				     {
					if(PDFDataExtract.ColoradoSprings_Format2.coloradoSprings()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Kansas City":
				String pdfFormatType_kansasCity = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_kansasCity);
				if(pdfFormatType_kansasCity=="Format1")
				{
					if(PDFDataExtract.KansasCity_Format1.kansasCity()==false)
						return false;
				}
				else 
					if(pdfFormatType_kansasCity=="Format2")
				     {
					if(PDFDataExtract.KansasCity_Format2.kansasCity()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Lake Havasu":
				String pdfFormatType_lakeHavasu = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_lakeHavasu);
				if(pdfFormatType_lakeHavasu=="Format1")
				{
					if(PDFDataExtract.LakeHavasu_Format1.lakeHavasu()==false)
						return false;
				}
				else 
					if(pdfFormatType_lakeHavasu=="Format2")
				     {
					if(PDFDataExtract.LakeHavasu_Format2.lakeHavasu()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "New Mexico":
				String pdfFormatType_newMexico = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_newMexico);
				if(pdfFormatType_newMexico=="Format1")
				{
					if(PDFDataExtract.NewMexico_Format1.newMexico()==false)
						return false;
				}
				else 
					if(pdfFormatType_newMexico=="Format2")
				     {
					if(PDFDataExtract.NewMexico_Format2.newMexico()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Chicago PFW":
				String pdfFormatType_chicagoPfw = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_chicagoPfw);
				if(pdfFormatType_chicagoPfw=="Format1")
				{
					if(PDFDataExtract.ChicagoPFW_Format1.chicagoPfw()==false)
						return false;
				}
				else 
					if(pdfFormatType_chicagoPfw=="Format2")
				     {
					if(PDFDataExtract.ChicagoPFW_Format2.chicagoPfw()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Boise":
				String pdfFormatType_boise = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_boise);
				if(pdfFormatType_boise=="Format1")
				{
					if(PDFDataExtract.Boise_Format1.boise()==false)
						return false;
				}
				else 
					if(pdfFormatType_boise=="Format2")
				     {
					if(PDFDataExtract.Boise_Format2.boise()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Spokane":
				String pdfFormatType_spokane = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_spokane);
				if(pdfFormatType_spokane=="Format1")
				{
					if(PDFDataExtract.Spokane_Format1.spokane()==false)
						return false;
				}
				else 
					if(pdfFormatType_spokane=="Format2")
				     {
					if(PDFDataExtract.Spokane_Format2.spokane()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Utah":
				String pdfFormatType_utah = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_utah);
				if(pdfFormatType_utah=="Format1")
				{
					if(PDFDataExtract.Utah_Format1.utah()==false)
						return false;
				}
				else 
					if(pdfFormatType_utah=="Format2")
				     {
					if(PDFDataExtract.Utah_Format2.utah()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Hawaii":
				String pdfFormatType_hawaii = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_hawaii);
				if(pdfFormatType_hawaii=="Format1")
				{
					if(PDFDataExtract.Hawaii_Format1.hawaii()==false)
						return false;
				}
				else 
					if(pdfFormatType_hawaii=="Format2")
				     {
					if(PDFDataExtract.Hawaii_Format2.hawaii()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Columbia - St Louis":
				String pdfFormatType_saintLouis = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_saintLouis);
				if(pdfFormatType_saintLouis=="Format1")
				{
					if(PDFDataExtract.SaintLouis_Format1.saintLouis()==false)
						return false;
				}
				else 
					if(pdfFormatType_saintLouis=="Format2")
				     {
					if(PDFDataExtract.SaintLouis_Format2.saintLouis()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Idaho Falls":
				String pdfFormatType_idahoFalls = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_idahoFalls);
				if(pdfFormatType_idahoFalls=="Format1")
				{
					if(PDFDataExtract.IdahoFalls_Format1.idahoFalls()==false)
						return false;
				}
				else 
					if(pdfFormatType_idahoFalls=="Format2")
				     {
					if(PDFDataExtract.IdahoFalls_Format2.idahoFalls()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Arizona":
				String pdfFormatType_arizona = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_arizona);
				if(pdfFormatType_arizona=="Format1")
				{
					if(PDFDataExtract.Arizona_Format1.arizona()==false)
						return false;
				}
				else 
					if(pdfFormatType_arizona=="Format2")
				     {
					if(PDFDataExtract.Arizona_Format2.arizona()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Maryland":
				String pdfFormatType_maryland = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_maryland);
				if(pdfFormatType_maryland=="Format1")
				{
					if(PDFDataExtract.Maryland_Format1.maryland()==false)
						return false;
				}
				else 
					if(pdfFormatType_maryland=="Format2")
				     {
					if(PDFDataExtract.Maryland_Format2.maryland()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Virginia":
				String pdfFormatType_virginia = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_virginia);
				if(pdfFormatType_virginia=="Format1")
				{
					if(PDFDataExtract.Virginia_Format1.virginia()==false)
						return false;
				}
				else 
					if(pdfFormatType_virginia=="Format2")
				     {
					if(PDFDataExtract.Virginia_Format2.virginia()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Washington DC":
				String pdfFormatType_washingtonDC = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_washingtonDC);
				if(pdfFormatType_washingtonDC=="Format1")
				{
					if(PDFDataExtract.WashingtonDC_Format1.washingtonDC()==false)
						return false;
				}
				else 
					if(pdfFormatType_washingtonDC=="Format2")
				     {
					if(PDFDataExtract.WashingtonDC_Format2.washingtonDC()==false)
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
			case "Indiana":
		        format1Text = PDFAppConfig.PDFFormatDecider.indiana_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.indiana_Format2;
		        break;
			case "Little Rock":
		        format1Text = PDFAppConfig.PDFFormatDecider.littleRock_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.littleRock_Format2;
		        break;
			case "Tennessee":
		        format1Text = PDFAppConfig.PDFFormatDecider.tennessee_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.tennessee_Format2;
		        break;
			case "California":
		        format1Text = PDFAppConfig.PDFFormatDecider.california_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.california_Format2;
		        break;
			case "California PFW":
		        format1Text = PDFAppConfig.PDFFormatDecider.california_pfw_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.california_pfw_Format2;
		        break;
			case "Houston":
		        format1Text = PDFAppConfig.PDFFormatDecider.houston_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.houston_Format2;
		        break;
			case "Austin":
		        format1Text = PDFAppConfig.PDFFormatDecider.austin_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.austin_Format2;
		        break;
			case "Chattanooga":
		        format1Text = PDFAppConfig.PDFFormatDecider.chattanooga_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.chattanooga_Format2;
		        break;
			case "Chicago":
		        format1Text = PDFAppConfig.PDFFormatDecider.chicago_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.chicago_Format2;
		        break;
			case "Savannah":
		        format1Text = PDFAppConfig.PDFFormatDecider.savannah_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.savannah_Format2;
		        break;
			case "South Carolina":
		        format1Text = PDFAppConfig.PDFFormatDecider.southCarolina_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.southCarolina_Format2;
		        break;
			case "Tulsa":
		        format1Text = PDFAppConfig.PDFFormatDecider.tulsa_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.tulsa_Format2;
		        break;
			case "Ohio":
		        format1Text = PDFAppConfig.PDFFormatDecider.ohio_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.ohio_Format2;
		        break;
			case "Maine":
		        format1Text = PDFAppConfig.PDFFormatDecider.maine_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.maine_Format2;
		        break;
			case "OKC":
		        format1Text = PDFAppConfig.PDFFormatDecider.okc_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.okc_Format2;
		        break;
			case "San Antonio":
		        format1Text = PDFAppConfig.PDFFormatDecider.sanAntonio_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.sanAntonio_Format2;
		        break;
			case "Pennsylvania":
		        format1Text = PDFAppConfig.PDFFormatDecider.pennsylvania_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.pennsylvania_Format2;
		        break;
			case "Colorado Springs":
		        format1Text = PDFAppConfig.PDFFormatDecider.coloradoSprings_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.coloradoSprings_Format2;
		        break;
			case "Kansas City":
		        format1Text = PDFAppConfig.PDFFormatDecider.kansasCity_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.kansasCity_Format2;
		        break;
			case "Lake Havasu":
		        format1Text = PDFAppConfig.PDFFormatDecider.lakeHavasu_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.lakeHavasu_Format2;
		        break;
			case "New Mexico":
		        format1Text = PDFAppConfig.PDFFormatDecider.newMexico_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.newMexico_Format2;
		        break;
			case "Chicago PFW":
		        format1Text = PDFAppConfig.PDFFormatDecider.chicagoPfw_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.chicagoPfw_Format2;
		        break;
			case "Boise":
		        format1Text = PDFAppConfig.PDFFormatDecider.boise_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.boise_Format2;
		        break;
			case "Spokane":
		        format1Text = PDFAppConfig.PDFFormatDecider.spokane_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.spokane_Format2;
		        break;
			case "Utah":
		        format1Text = PDFAppConfig.PDFFormatDecider.utah_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.utah_Format2;
		        break;
			case "Hawaii":
		        format1Text = PDFAppConfig.PDFFormatDecider.hawaii_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.hawaii_Format2;
		        break;
			case "Saint Louis":
		        format1Text = PDFAppConfig.PDFFormatDecider.saintLouis_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.saintLouis_Format2;
		        break;
			case "Idaho Falls":
		        format1Text = PDFAppConfig.PDFFormatDecider.idahoFalls_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.idahoFalls_Format2;
		        break;
		        
			case "Arizona":
		        format1Text = PDFAppConfig.PDFFormatDecider.arizona_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.arizona_Format2;
		        break;
			case "Maryland":
		        format1Text = PDFAppConfig.PDFFormatDecider.maryland_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.maryland_Format2;
		        break;
			case "Virginia":
		        format1Text = PDFAppConfig.PDFFormatDecider.virginia_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.virginia_Format2;
		        break;
			case "Washington DC":
		        format1Text = PDFAppConfig.PDFFormatDecider.washingtonDC_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.washingtonDC_Format2;
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
