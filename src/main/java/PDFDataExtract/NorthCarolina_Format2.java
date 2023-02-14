package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.AppConfig;
import mainPackage.PDFReader;
import mainPackage.RunnerClass;

public class NorthCarolina_Format2 
{
	public static void northCarolina()
	
	    //public static void northCarolina() throws Exception
		{
			try
			{
			File file = RunnerClass.getLastModified();
			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Full_Lease_-_[6128_Creekview_Court]_-_[Wallace_-_Crawford]_-_[02.01.2023]_-_[04.30.2024].PDF_(1).pdf");
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			String text = new PDFTextStripper().getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
		    text = text.replaceAll(" +", " ");
		    System.out.println(text);
		    String commencementDate_Prior="";
		    String expirationDate_Prior ="";
		    String monthlyRent_Prior ="";
		    String HVACAirFilterFee ="";
		    String prorateRent = "";
		    String prorateRentDate_Prior = "";
		    String prorateRentDate_After = "";
		    if(text.contains(AppConfig.PDFFormat2ConfirmationText))
		    {
                //Commencement Date 
		    	commencementDate_Prior = PDFAppConfig.NorthCarolina_Format2.commencementDate_Prior;
		    	//Expiration Date
		    	expirationDate_Prior = PDFAppConfig.NorthCarolina_Format2.expirationDate_Prior;
		    	//Monthly Rent
		    	monthlyRent_Prior = PDFAppConfig.NorthCarolina_Format2.monthlyRent_Prior;
		    	//HVAC Air Filter Fee
		    	HVACAirFilterFee = PDFAppConfig.NorthCarolina_Format2.HVACAirFilter_prior;
		    	//Prorate Rent
		    	prorateRent = PDFAppConfig.NorthCarolina_Format2.proratedRent_Prior;
		    	//Prorate Rent Date
		    	prorateRentDate_Prior = PDFAppConfig.NorthCarolina_Format2.proratedRentDate_Prior;
		    	prorateRentDate_After = PDFAppConfig.NorthCarolina_Format2.proratedRentDate_After;
		    }
		    else if(text.contains(AppConfig.PDFFormatConfirmationText))
		    {
		    	monthlyRent_Prior = PDFAppConfig.Florida_Format2.Florida_monthlyRent_Format2_Prior;
		    }
		    
		    System.out.println("------------------------------------------------------------------");
		  //Commencement Date 
		    try
		    {
		    	PDFReader.commencementDate = text.substring(text.indexOf(commencementDate_Prior)+commencementDate_Prior.length()).trim().replaceAll(" +", " ");
		    	PDFReader.commencementDate = PDFReader.commencementDate.substring(0,PDFReader.commencementDate.indexOf("(the")).trim();
		    }
		    catch(Exception e)
		    {
		    	PDFReader.commencementDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Commensement Date = "+PDFReader.commencementDate);
		    
		    //Expiration Date
		    try
		    {
		    	PDFReader.expirationDate = text.substring(text.indexOf(expirationDate_Prior)+expirationDate_Prior.length()).trim().replaceAll(" +", " ");
		    	PDFReader.expirationDate = PDFReader.expirationDate.substring(0,PDFReader.expirationDate.indexOf("(the")).trim();
		    }
		    catch(Exception e)
		    {
		    	PDFReader.expirationDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Expiration Date = "+PDFReader.expirationDate);
		    
		    //Monthly Rent
		    try
		    {
		    	PDFReader.monthlyRent = text.substring(text.indexOf(monthlyRent_Prior)+monthlyRent_Prior.length()).trim().split(" ")[0];
		    }
		    catch(Exception e)
		    {
		    	PDFReader.monthlyRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Monthly Rent = "+PDFReader.monthlyRent);
		    
		    //HVAC Air Filter Fee (OR) Resident Benefits Package
		    if(text.contains(PDFAppConfig.NorthCarolina_Format2.HVACFilterAddendumTextAvailabilityCheck))
		    {
		    	PDFReader.HVACFilterFlag = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.airFilterFee = text.substring(text.indexOf(HVACAirFilterFee)+HVACAirFilterFee.length()).trim().split(" ")[0];
				    }
				    catch(Exception e)
				    {
				    	PDFReader.airFilterFee = "Error";
				    	e.printStackTrace();
				    }
				    System.out.println("HVAC Air Filter Fee = "+PDFReader.airFilterFee);
		    }
		    
		    if(text.contains(PDFAppConfig.NorthCarolina_Format2.residentBenefitsPackageCheck))
		    {
		    	PDFReader.residentBenefitsPackageAvailabilityCheck = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.residentBenefitsPackage = text.substring(text.indexOf(HVACAirFilterFee)+HVACAirFilterFee.length()).trim().split(" ")[0];
				    }
				    catch(Exception e)
				    {
				    	PDFReader.residentBenefitsPackage = "Error";
				    	e.printStackTrace();
				    }
				    System.out.println("Resident Benefits Package = "+PDFReader.residentBenefitsPackage);
		    }
		    
		    
		    //Prorate Rent
		    try
		    {
		    	PDFReader.proratedRent = text.substring(text.indexOf(prorateRent)+prorateRent.length()).trim().split(" ")[0];
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Prorate Rent = "+PDFReader.proratedRent);
		    
		    //Prorate Rent Date 
		    try
		    {
		    	PDFReader.proratedRentDate = text.substring(text.indexOf(prorateRentDate_Prior)+prorateRentDate_Prior.length()+1,text.indexOf(prorateRentDate_After)).trim();
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRentDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Prorate Rent Date = "+PDFReader.proratedRentDate);
		    
			}
		    catch(Exception e)
		    {
		    	System.out.println("Wrong PDF Format");
		    	RunnerClass.monthlyRent = "Error";
		    	RunnerClass.startDate = "Error";
		    }
		   
		}
		

}
