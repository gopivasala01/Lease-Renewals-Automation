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
	
	    public static boolean northCarolina() throws Exception
	   // public static void main(String args[]) throws Exception
		{
			try
			{
			File file = RunnerClass.getLastModified();
			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\RENEWAL_[3343_Buckvalley_Dr]_[Kinsela_-_Kinsela]_[02.01.23-07.31.24].pdf");
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			String text = new PDFTextStripper().getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
		    text = text.replaceAll(" +", " ");
		    System.out.println(text);
		    try
		    {
		    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.commencementDate_Prior)+PDFAppConfig.NorthCarolina_Format2.commencementDate_Prior.length());
		    	PDFReader.commencementDate =  PDFReader.commencementDate.substring(0,PDFReader.commencementDate.indexOf("(the")).trim();
		    }
		    catch(Exception e)
		    {
		    	PDFReader.commencementDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Commensement Date = "+PDFReader.commencementDate);
		   try
		    {
			   PDFReader.expirationDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.expirationDate_Prior)+PDFAppConfig.NorthCarolina_Format2.expirationDate_Prior.length());
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
		    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.monthlyRent_Prior)+PDFAppConfig.NorthCarolina_Format2.monthlyRent_Prior.length()).trim().split(" ")[0];
		    	if(PDFReader.monthlyRent.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.monthlyRent = "Error";
		    	if(PDFReader.monthlyRent.contains("$"))
		    		PDFReader.monthlyRent=PDFReader.monthlyRent.replace("$", "");
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
				    	PDFReader.HVACAirFilterFee = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.HVACAirFilterFee)+PDFAppConfig.NorthCarolina_Format2.HVACAirFilterFee.length()).trim().split(" ")[0];
				    	if(PDFReader.HVACAirFilterFee.matches(".*[a-zA-Z]+.*"))
				    		PDFReader.HVACAirFilterFee = "Error";
				    }
				    catch(Exception e)
				    {
				    	PDFReader.HVACAirFilterFee = "Error";
				    	e.printStackTrace();
				    }
				    System.out.println("HVAC Air Filter Fee = "+PDFReader.HVACAirFilterFee);
		    }
		    
		    if(text.contains(PDFAppConfig.NorthCarolina_Format2.residentBenefitsPackageCheck))
		    {
		    	PDFReader.residentBenefitsPackageAvailabilityCheck = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.residentBenefitsPackage = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.RBP_Prior)+PDFAppConfig.NorthCarolina_Format2.RBP_Prior.length()).trim().split(" ")[0];
				    	if(PDFReader.residentBenefitsPackage.matches(".*[a-zA-Z]+.*"))
				    		PDFReader.residentBenefitsPackage = "Error";
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
		    	PDFReader.proratedRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.prorateRent_Prior)+PDFAppConfig.NorthCarolina_Format2.prorateRent_Prior.length());
		    	PDFReader.proratedRent = PDFReader.proratedRent.substring(0,PDFReader.proratedRent.indexOf("as prorated rent")).trim();
		    	if(PDFReader.proratedRent.matches(".*[a-zA-Z]+.*")||PDFReader.proratedRent.equals("0.00"))
		    		PDFReader.proratedRent = "Error";
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Prorate Rent = "+PDFReader.proratedRent);
		    
			//Pet Rent
		    if(text.contains(PDFAppConfig.NorthCarolina_Format2.petAgreementAvailabilityCheck))
		    {
		    	PDFReader.petFlag = true;
		    	System.out.println("Pet Addendum Available = "+PDFReader.petFlag);
		    	
		    	try
		    	{
		    		PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.petRent_Prior)+PDFAppConfig.NorthCarolina_Format2.petRent_Prior.length()).trim().split(" ")[0].trim();
		    		if(PDFReader.petRent.matches(".*[a-zA-Z]+.*")||PDFReader.petRent.equals("0.00"))
			    		PDFReader.petRent = "Error";
		    	}
		    	catch(Exception e)
		    	{
		    		PDFReader.petRent = "Error";
		    	}
		    	System.out.println("Pet Rent = "+PDFReader.petRent);
		    }
		    
		    //Increased Rent
		    try
		    {
		    	if(text.contains("on the first 12 months"))
		    	{
		    		PDFReader.incrementRentFlag =true;
		    		//Increased Rent
		    		try
		    		{
		    		PDFReader.increasedRent_amount = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.increasedRent_Prior1)+PDFAppConfig.NorthCarolina_Format2.increasedRent_Prior1.length()).trim().split(" ")[0].trim();
		    		if(PDFReader.increasedRent_amount.matches(".*[a-zA-Z]+.*")||PDFReader.increasedRent_amount.equals("0.00"))
			    		PDFReader.increasedRent_amount = "Error";
		    		}
		    		catch(Exception e)
		    		{
		    			PDFReader.increasedRent_amount = "Error";
		    		}
		    		System.out.println("Increased Rent = "+PDFReader.increasedRent_amount);
		    		//Monthly Rent End Date
		    		try
		    		{
		    		PDFReader.increasedRent_previousRentEndDate =RunnerClass.lastDateOfTheMonth(RunnerClass.firstDayOfMonth(RunnerClass.convertDate(PDFReader.commencementDate), 11));
		    		}
		    		catch(Exception e)
		    		{
		    			PDFReader.increasedRent_previousRentEndDate = "Error";
		    		}
		    		System.out.println("Monthly rent End Date = "+PDFReader.increasedRent_previousRentEndDate);
		    		// Increased Rent Start Date
		    		try
		    		{
		    			PDFReader.increasedRent_newStartDate =RunnerClass.firstDayOfMonth(RunnerClass.convertDate(PDFReader.commencementDate), 12);
		    		}
		    		catch(Exception e)
		    		{
		    			PDFReader.increasedRent_newStartDate  = "Error";
		    		}
		    		System.out.println("Increased Rent Start Date = "+PDFReader.increasedRent_newStartDate);
		    		
		    	}
		    	if(text.contains("and, $"))
		    	{
		    		PDFReader.incrementRentFlag =true;
		    		//Increased Rent
		    		try
		    		{
		    		PDFReader.increasedRent_amount = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.increasedRent_Prior2)+PDFAppConfig.NorthCarolina_Format2.increasedRent_Prior2.length()).trim().split(" ")[0].trim();
		    		if(PDFReader.increasedRent_amount.matches(".*[a-zA-Z]+.*")||PDFReader.increasedRent_amount.equals("0.00"))
			    		PDFReader.increasedRent_amount = "Error";
		    		}
		    		catch(Exception e)
		    		{
		    			PDFReader.increasedRent_amount = "Error";
		    		}
		    		System.out.println("Increased Rent = "+PDFReader.increasedRent_amount);
		    		
		    		//Monthly Rent End Date
		    		try
		    		{
		    		PDFReader.increasedRent_previousRentEndDate = text.substring(text.indexOf(PDFReader.commencementDate+" to ")+(PDFReader.commencementDate+" to ").length(),text.indexOf("and, $")).trim();
		    		}
		    		catch(Exception e)
		    		{
		    			PDFReader.increasedRent_previousRentEndDate = "Error";
		    		}
		    		System.out.println("Monthly rent End Date = "+PDFReader.increasedRent_previousRentEndDate);
		    		
		    		// Increased Rent Start Date
		    		try
		    		{
		    			PDFReader.increasedRent_newStartDate = text.substring(text.indexOf(PDFReader.increasedRent_amount+" for ")+(PDFReader.increasedRent_amount+" for ").length()).trim();
		    			PDFReader.increasedRent_newStartDate = PDFReader.increasedRent_newStartDate.substring(0,PDFReader.increasedRent_newStartDate.indexOf("to"));
		    		}
		    		catch(Exception e)
		    		{
		    			PDFReader.increasedRent_newStartDate  = "Error";
		    		}
		    		System.out.println("Increased Rent Start Date = "+PDFReader.increasedRent_newStartDate);
		    	}
		    }
            catch(Exception e)
		    {
	
		    }
		    return true;
		    
			}	
			catch(Exception e)
			{
				System.out.println("Issue in fetching values from PDF");
				RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in fetching values from PDF";
				return false;
			}
			}
}
