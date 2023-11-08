package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.PDFReader;
import mainPackage.RunnerClass;


public class NorthCarolina_Format1 
{
	public static boolean northCarolina() throws Exception
	//public static void main(String [] args) throws Exception
	{
		try
		{
			File file = RunnerClass.getLastModified();
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			stripper.setStartPage(1);
			stripper.setEndPage(1);
			String firstPageText = stripper.getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
			text = text.replaceAll(" +", " ");
			firstPageText = firstPageText.replaceAll(System.lineSeparator(), " ");
			firstPageText = firstPageText.replaceAll(" +", " ");
			System.out.println("First page text:\n" + firstPageText);
			System.out.println("All pages text:\n" + text);
			document.close();
	    
	    System.out.println("------------------------------------------------------------------");
	    
	    String pattern = "\\d{1,2}/\\d{1,2}/\\d{4}"; 
	    Pattern datePattern = Pattern.compile(pattern);

	    Matcher matcher = datePattern.matcher(firstPageText);
	    String renewalExecutionDate = "";

	    
	    while (matcher.find()) {
	    	PDFReader.renewalExecutionDate = matcher.group();
	    }
	    if(PDFReader.renewalExecutionDate.isEmpty())
	    {
	    	 matcher = datePattern.matcher(text);
	    	 while (matcher.find()) {
	 	    	PDFReader.renewalExecutionDate = matcher.group();
	 	    	
	 	    }
	    }
	    String[] SplitDate = PDFReader.renewalExecutionDate.split("/");

    	 for (int i = 0; i < 2; i++) {
    	     if (SplitDate[i].length() == 1) {
    	         // Add a leading zero for single-digit values in the first two components
    	         SplitDate[i] = "0" + SplitDate[i];
    	     }
    	 }
    	 
    	 PDFReader.renewalExecutionDate= SplitDate[0]+"/"+ SplitDate[1]+"/"+SplitDate[2];


	    System.out.println("Last date mentioned on the page: " + PDFReader.renewalExecutionDate);
		    try
		    {
		    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.commencementDate_Prior)+PDFAppConfig.NorthCarolina_Format1.commencementDate_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.commencementDate_After));
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
		   
		   try {
			    PDFReader.monthlyRent = extractMonthlyRent(text, PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior);

			    if (PDFReader.monthlyRent == null || PDFReader.monthlyRent.contains("$")) {
			        PDFReader.monthlyRent = extractMonthlyRent(text, PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior1);
			    }

			    if (PDFReader.monthlyRent == null || PDFReader.monthlyRent.contains("$")) {
			        PDFReader.monthlyRent = extractMonthlyRent(text, PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior2);
			    }

			    if (PDFReader.monthlyRent != null) {
			        PDFReader.monthlyRent = PDFReader.monthlyRent.replaceAll("$", "");
			    } else {
			        PDFReader.monthlyRent = "Error";
			    }

			    System.out.println("Monthly Rent = " + PDFReader.monthlyRent);
			} catch (Exception e) {
			    System.err.println("An error occurred: " + e.getMessage());
			    e.printStackTrace();
			}
		    /*try
		    {
		    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior)+PDFAppConfig.NorthCarolina_Format1.monthlyRent_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
		    	if(PDFReader.monthlyRent.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.monthlyRent = "Error";
		    	if(PDFReader.monthlyRent.contains("$"))
		    		PDFReader.monthlyRent = PDFReader.monthlyRent.replace("$", "");
		    }
		    catch(Exception e)
		    {
		    	PDFReader.monthlyRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Monthly Rent = "+PDFReader.monthlyRent);*/
		    
		    //HVAC Air Filter Fee (OR) Resident Benefits Package
		    if(text.contains(PDFAppConfig.NorthCarolina_Format1.HVACFilterAddendumTextAvailabilityCheck))
		    {
		    	PDFReader.HVACFilterFlag = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.HVACAirFilterFee = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.HVACAirFilterFee)+PDFAppConfig.NorthCarolina_Format1.HVACAirFilterFee.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
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
		    
		    if(text.contains(PDFAppConfig.NorthCarolina_Format1.residentBenefitsPackageCheck)&&(!text.contains("Resident Benefits Package Opt-Out Addendum")||!text.contains("RESIDENT BENEFITS PACKAGE OPT-OUT ADDENDUM")))
		    {
		    	PDFReader.residentBenefitsPackageAvailabilityCheck = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.residentBenefitsPackage = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.RBP_Prior)+PDFAppConfig.NorthCarolina_Format1.RBP_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "").replaceAll("[^0-9a-zA-Z.]", "").replaceAll("[^0-9a-zA-Z.]", "").replaceAll("[^0-9a-zA-Z.]", "");
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
		    	PDFReader.proratedRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.prorateRent_Prior)+PDFAppConfig.NorthCarolina_Format1.prorateRent_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
		    	if(PDFReader.proratedRent.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.proratedRent = "Error";
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Prorate Rent = "+PDFReader.proratedRent);
		    
			//Pet Rent
		    if(text.contains(PDFAppConfig.NorthCarolina_Format1.petAgreementAvailabilityCheck))
		    {
		    	PDFReader.petFlag = true;
		    	System.out.println("Pet Addendum Available = "+PDFReader.petFlag);
		    	
		    	try
		    	{
		    		PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.petRent_Prior)+PDFAppConfig.NorthCarolina_Format1.petRent_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "").trim();
		    		if(PDFReader.petRent.matches(".*[a-zA-Z]+.*"))
			    		PDFReader.petRent = "Error";
		    	}
		    	catch(Exception e)
		    	{
		    		PDFReader.petRent = "Error";
		    	}
		    	System.out.println("Pet Rent = "+PDFReader.petRent);
		    }
		    
		    //Lease Renewal Admin Fee
		    try
	    	{
	    		PDFReader.leaseRenewalFee = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.leaseRenewalFee_Prior)+PDFAppConfig.NorthCarolina_Format1.leaseRenewalFee_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "").trim();
	    		if(PDFReader.leaseRenewalFee.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.leaseRenewalFee = "Error";
	    	}
	    	catch(Exception e)
	    	{
	    		PDFReader.leaseRenewalFee = "Error";
	    	}
	    	System.out.println("Lease Renewal Fee = "+PDFReader.leaseRenewalFee);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Issue in fetching values from PDF");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in fetching values from PDF";
			return false;
		}

	}
	public static String extractMonthlyRent(String text, String format) {
	    try {
	        String rent = text.substring(text.indexOf(format) + format.length()).trim().split(" ")[0];
	        return rent.matches(".*[a-zA-Z]+.*") ? null : rent;
	    } catch (Exception e) {
	        System.err.println("An error occurred while extracting monthly rent: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}

}
