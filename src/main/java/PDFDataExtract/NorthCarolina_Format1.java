package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.PDFReader;
import mainPackage.RunnerClass;


public class NorthCarolina_Format1 
{
	public static void northCarolina() throws Exception
	//public static void main(String [] args) throws Exception
	{
			File file = RunnerClass.getLastModified();
			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\RENEWAL_[12134_Old_Dulin_Farms_Way]_[Frazier-Frazier]_[01.01.23-12.31.24].pdf");
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			String text = new PDFTextStripper().getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
		    text = text.replaceAll(" +", " ");
		    System.out.println(text);
		    
		    System.out.println("------------------------------------------------------------------");
		    try
		    {
		    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.commencementDate_Prior)+PDFAppConfig.NorthCarolina_Format1.commencementDate_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.commencementDate_After));
		    	//PDFReader.commencementDate = PDFReader.commencementDate.substring(0,PDFReader.commencementDate.indexOf("(the")).trim();
		    }
		    catch(Exception e)
		    {
		    	PDFReader.commencementDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Commensement Date = "+PDFReader.commencementDate);
		   try
		    {
			   PDFReader.expirationDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.expirationDate_Prior)+PDFAppConfig.NorthCarolina_Format1.expirationDate_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.expirationDate_After));
		    	//PDFReader.expirationDate = PDFReader.expirationDate.substring(0,PDFReader.expirationDate.indexOf("(the")).trim();
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
		    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior)+PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior.length()).trim().split(" ")[0];
		    }
		    catch(Exception e)
		    {
		    	PDFReader.monthlyRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Monthly Rent = "+PDFReader.monthlyRent);
		    
		    //HVAC Air Filter Fee (OR) Resident Benefits Package
		    if(text.contains(PDFAppConfig.NorthCarolina_Format1.HVACFilterAddendumTextAvailabilityCheck))
		    {
		    	PDFReader.HVACFilterFlag = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.airFilterFee = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.HVACAirFilterFee)+PDFAppConfig.NorthCarolina_Format1.HVACAirFilterFee.length()).trim().split(" ")[0];
				    }
				    catch(Exception e)
				    {
				    	PDFReader.airFilterFee = "Error";
				    	e.printStackTrace();
				    }
				    System.out.println("HVAC Air Filter Fee = "+PDFReader.airFilterFee);
		    }
		    /*
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
		    
		    */
		    //Prorate Rent
		    try
		    {
		    	PDFReader.proratedRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.prorateRent_Prior)+PDFAppConfig.NorthCarolina_Format1.prorateRent_Prior.length()).trim().split(" ")[0];
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
		    	PDFReader.proratedRentDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.prorateRentDate_Prior)+PDFAppConfig.NorthCarolina_Format1.prorateRentDate_Prior.length()+1,text.indexOf(PDFAppConfig.NorthCarolina_Format1.prorateRentDate_After)).trim();
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRentDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Prorate Rent Date = "+PDFReader.proratedRentDate);
		    
			
			

}

}
